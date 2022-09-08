import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/EditarPerfil.css';
import iconSalvar from "../html-css-template/imagens/folder-plus.png";
import iconX from "../html-css-template/imagens/icon-X.svg";
import { Link, useNavigate } from "react-router-dom";
import apiUsuario from "../apiUsuario.js";
import { useEffect, useState } from "react";


function dataUsuario() {
    return {
        email: "",
        senha: "",
        nome: "",
        sobrenome: "",
        cpf: "",
        genero: "",
        dataNascimento: "",
        estadoCivil: "",
        telefone: "",
        cep: "",
        grauEscolaridade: "",
        fkSituacaoAtual: ""
    }
}

function EditarPerfil() {

    const [fkSituacaoAtual, setSituacaoAtual] = useState([]);

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

    // Função para chamar o endPoint para cadastrar o usuário
    const [valuesUsuario, setValuesUsuario] = useState(dataUsuario)

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
        console.log(valuesUsuario)
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
                                    <option value=""></option>
                                    <option value="S">Solteiro(a)</option>
                                    <option value="C">Casado(a)</option>
                                    <option value="D">Divorciado(a)</option>
                                    <option value="V">Viúvo(a)</option>
                                </select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="telefone">Telefone</label>
                                <input id="telefone" type="text" name="telefone" size="11" maxLength="11" minLength="11" pattern="^[0-9]+$" value={valuesUsuario.telefone} required onChange={handleChangeUser} />
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="genero">Gênero</label>
                                <select name="genero" id="genero" value={valuesUsuario.genero} required onChange={handleChangeUser}>
                                    <option value=""></option>
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
                                    <option value=""></option>
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
                                    <option value=""></option>
                                    {
                                        fkSituacaoAtual.map((situacao) => (
                                            <option value={situacao.idSituacaoAtual}>{situacao.nome}</option>
                                        ))
                                    }
                                </select>
                            </div>
                        </div>
                        <div className="ep-btns">
                            <Link to="/desabilitar-perfil"><div>Desabilitar conta<img src={iconX} alt="" /></div></Link>
                            <button>
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