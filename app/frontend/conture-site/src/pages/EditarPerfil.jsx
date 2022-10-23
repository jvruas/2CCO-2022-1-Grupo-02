import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/EditarPerfil.css';
import iconSalvar from "../html-css-template/imagens/folder-plus.png";
import iconX from "../html-css-template/imagens/icon-X.svg";
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { Link, useNavigate } from "react-router-dom";
import apiUsuario from "../apiUsuario.js";
import { useEffect, useState } from "react";


function dataEdicaoUsuario() {
    return {
        genero: "",
        estadoCivil: "",
        cep: "",
        grauEscolaridade: "",
        telefone: "",
        fkSituacaoAtual: ""
    }
}

function EditarPerfil() {

    const navegar = useNavigate();

    const [fkSituacaoAtual, setSituacaoAtual] = useState([]);

    /* Função que chama o endPoint que traz todos as situações atuais */
    useEffect(() => {
        apiUsuario.get("/situacao-atual").then((resposta) => {
            try {
                console.log(resposta.data)
                setSituacaoAtual(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])


    const [valuesUsuario, setValuesUsuario] = useState(dataEdicaoUsuario)

    /* Função para alterar a constante valuesUsuario conforme o input do usuário */
    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
        console.log(valuesUsuario)
    }

    /* Função para chamar o endPoint que muda as informações do usuário */
    function handleSubmit(event) {

        event.preventDefault();

        let json = {
            genero: valuesUsuario.genero,
            estadoCivil: valuesUsuario.estadoCivil,
            cep: valuesUsuario.cep,
            grauEscolaridade: valuesUsuario.grauEscolaridade,
            telefone: valuesUsuario.telefone,
            fkSituacaoAtual: valuesUsuario.fkSituacaoAtual
        }

        fetch(`https://viacep.com.br/ws/${valuesUsuario.cep}/json/`)
            .then(res => res.json()).then(data => {
                console.log(data)
            })
            .catch((error) => {
                console.log(error)
                document.getElementById("alerta-img2").style.display = "flex"
                document.getElementById("msg-alerta").innerHTML = `CEP inválido`
            })

        if (valuesUsuario.genero == "" || valuesUsuario.estadoCivil == "" || valuesUsuario.telefone == "" || valuesUsuario.cep == "" || valuesUsuario.grauEscolaridade == "" || valuesUsuario.fkSituacaoAtual == "") {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `Preencha os campos vazios`
        } else if (valuesUsuario.telefone.length < 11) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `Telefone inválido`
        } else if (valuesUsuario.cep.length < 8) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `CEP inválido`
        } else {
            apiUsuario.put(`${sessionStorage.getItem('idUsuarioLogado')}/perfil`, json, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((resposta) => {
                navegar("/")
                console.log(resposta.status)
            }).catch((error) => {
                console.log(error)
                console.log(json)
                document.getElementById("alerta-img2").style.display = "flex"
                document.getElementById("msg-alerta").innerHTML = `Erro`
            })
        }
    }

    /* Função para pegar os dados do usuário logado */
    const [usuarioLogado, setUsuario] = useState([]);

    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`/${idUsuario}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setUsuario(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    /* Função para enviar o código de validação e enviar o usuário para a página de validação */
    function validacao(event) {
        event.preventDefault()

        apiUsuario.post(`conta/validacao-email?emailDestinatario=${usuarioLogado.email}&idUsuario=${usuarioLogado.idUsuario}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((resposta) => {
            navegar("/validacao-usuario")
            console.log(resposta.status)
        }).catch((error) => {
            console.log(error)
        })
    }

    return (
        <>
            <Header />
            <section id="ep-section">
                <div className="grid">
                    <div id="ep-parte-um">
                        <div className="ep-opcao ep-opcao-selecionada">
                            <Link className="link-p" to="/editar-perfil"><p>Editar perfil</p></Link>
                        </div>
                        <div className="ep-opcao">
                            <Link className="link-p" to="/alterar-senha"><p>Trocar senha</p></Link>
                        </div>
                        <div className="ep-opcao">
                            <Link className="link-p" to="/validacao-usuario"><p>Validação</p></Link>
                        </div>
                    </div>
                    <div id="ep-parte-dois">
                        <div className="ep-titulo">
                            <h2>EDITAR PERFIL</h2>
                        </div>
                        <div className="ep-campos">
                            <div className="ep-campo">
                                <label htmlFor="estadoCivil">Estado civil</label>
                                <select name="estadoCivil" id="estadoCivil" value={valuesUsuario.estadoCivil} required onChange={handleChangeUser}>
                                    <option value="" selected disabled hidden></option>
                                    <option value="S">Solteiro(a)</option>
                                    <option value="C">Casado(a)</option>
                                    <option value="D">Divorciado(a)</option>
                                    <option value="V">Viúvo(a)</option>
                                </select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="telefone">Telefone</label>
                                <input id="telefone" type="text" name="telefone" size="11" maxLength="11" minLength="11" pattern="^[0-9]+$" value={valuesUsuario.telefone} onChange={handleChangeUser} />
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="genero">Gênero</label>
                                <select name="genero" id="genero" value={valuesUsuario.genero} placeholder={usuarioLogado.genero} onChange={handleChangeUser}>
                                    <option value="" selected disabled hidden></option>
                                    <option value="F">Feminino</option>
                                    <option value="M">Masculino</option>
                                    <option value="X">Outro</option>
                                </select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="cep">CEP</label>
                                <input id="cep" type="text" name="cep" size="8" maxLength="8" minLength="8" pattern="^[0-9]+$" value={valuesUsuario.cep} required onChange={handleChangeUser} />
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="grauEscolaridade">Escolaridade</label>
                                <select id="grauEscolaridade" name="grauEscolaridade" value={valuesUsuario.grauEscolaridade} required onChange={handleChangeUser}>
                                    <option value="" selected disabled hidden></option>
                                    <option value="A">Analfabeto</option>
                                    <option value="I">Educação infantil</option>
                                    <option value="F">Fundamental</option>
                                    <option value="M">Médio</option>
                                    <option value="S">Superior (Graduação)</option>
                                    <option value="P">Pós-graduação</option>
                                    <option value="E">Mestrado</option>
                                    <option value="D">Doutorado</option>
                                </select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="fkSituacaoAtual">Situação atual</label>
                                <select name="fkSituacaoAtual" id="fkSituacaoAtual" value={valuesUsuario.fkSituacaoAtual} required onChange={handleChangeUser}>
                                    <option value="" selected disabled hidden></option>
                                    {
                                        fkSituacaoAtual.map((situacao) => (
                                            <option value={situacao.idSituacaoAtual}>{situacao.nome}</option>
                                        ))
                                    }
                                </select>
                            </div>
                        </div>
                        <div className="ep-aviso">
                            <img src={iconError} id="alerta-img2" /><p id="msg-alerta"></p>
                        </div>
                        <div className="ep-btns">
                            <Link to="/desabilitar-perfil"><div>Desabilitar conta<img src={iconX} alt="" /></div></Link>
                            <button type="button" onClick={handleSubmit}>
                                <p>SALVAR</p><img src={iconSalvar} alt="Ícone de salvar" />
                            </button>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default EditarPerfil;