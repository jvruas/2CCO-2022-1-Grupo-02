import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Cadastro.css'
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import imgEtapa from "../html-css-template/imagens/etapas.png"
import imgEtapa2 from "../html-css-template/imagens/etapas2.png"
import imgEtapa3 from "../html-css-template/imagens/etapas3.png"
import iconCamera from "../html-css-template/imagens/icon-camera.svg"
import iconInfoSenha from "../html-css-template/imagens/info-circle1.png"
import iconSeta from "../html-css-template/imagens/seta.png"
import iconSeta2 from "../html-css-template/imagens/seta2.png"
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { Link, useNavigate } from "react-router-dom";
import iconOk from "../html-css-template/imagens/icon-ok.png"
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
        // uf: "",
        grauEscolaridade: "",
        fkSituacaoAtual: ""
    }
}

function Cadastro() {

    // Função para chamar o endPoint para trazer as "situações atuais"
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
    const [valuesUsuario, setValuesUsuario] = useState(dataUsuario);
    var fotoPerfil = document.getElementById("fotoPerfil");

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
        console.log(valuesUsuario)
    }

    function epoch(date) {
        return Date.parse(date)
    }

    function handleChangeData(dataNasc) {
        var input_dataNasc = document.getElementById("dataNasc");
        dataUsuario.dataNasc = input_dataNasc.value
        // console.log(epoch(input_dataNasc.value))
    }

    const navegar = useNavigate();

    function handleSubmit(event) {
        var input_dataNasc = document.getElementById("dataNasc");
        var data_uf = "";
        event.preventDefault()

        fetch(`https://viacep.com.br/ws/${valuesUsuario.cep}/json/`)
            .then(res => res.json()).then(data => {
                console.log(data.uf)
                data_uf = data;
            })
            .catch((error) => {
                console.log(error)
            })

        let json = {
            email: valuesUsuario.email,
            senha: valuesUsuario.senha,
            nome: valuesUsuario.nome,
            sobrenome: valuesUsuario.sobrenome,
            cpf: valuesUsuario.cpf,
            genero: valuesUsuario.genero,
            dataNascimento: epoch(input_dataNasc.value),
            estadoCivil: valuesUsuario.estadoCivil,
            telefone: valuesUsuario.telefone,
            cep: valuesUsuario.cep,
            uf: "SP",
            grauEscolaridade: valuesUsuario.grauEscolaridade,
            fkSituacaoAtual: valuesUsuario.fkSituacaoAtual
        }

        apiUsuario.post("/", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((resposta) => {
            console.log("ta caindo aqui")
            var idUsuario = resposta.data;
            console.log("fotoPerfil", fotoPerfil.files[0])

            let formData = new FormData();
            formData.append("file", fotoPerfil.files[0]);

            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            }

            // let fotoPerfilSelecionada = fotoPerfil.value == "" ? fotoPerfil.value = "../html-css-template/imagens/foto.jpg" : fotoPerfil.files[0];
            apiUsuario.post(`/${idUsuario}/imagem?tipoImagem=P`, formData, config)
                .then((respostaImg) => {
                    try {
                        console.log(respostaImg)
                    } catch (error) {
                        console.log(error)
                    }
                })
        }).catch((error) => {
            console.log(error)
            if (error.status == "409") {
                document.getElementById("alerta-img2").style.display = "flex"
                document.getElementById("msg-alerta2").innerHTML = `E-mail ou CPF já está sendo utilizado`
            } else if (error.status == "400") {
                document.getElementById("alerta-img2").style.display = "flex"
                document.getElementById("msg-alerta2").innerHTML = `Os campos CPF, Telefone e CEP não podem conter - e .`
            }
        })
    }


    // Função para mudar o formulário de cadastro
    const mostrarForm = () => {
        var form1 = document.getElementById("form-cadastro1");
        var form2 = document.getElementById("form-cadastro2");
        var form3 = document.getElementById("section-foto");

        var input_email = document.getElementById("email");

        var input_senha = document.getElementById("senha");
        var input_confSenha = document.getElementById("confSenha");

        if (input_email.value == "" || input_senha.value == "" || input_confSenha.value == "") {
            document.getElementById("alerta-img").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `Preencha os campos vazios`
        } else if (input_senha.value != input_confSenha.value) {
            document.getElementById("alerta-img").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `As senhas não correspondem`
        } else if ((input_senha.value).length < 6) {
            document.getElementById("alerta-img").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `A senha deve ter mais que 6 caracteres`
        } else {
            document.getElementById("alerta-img").style.display = "none"
            document.getElementById("msg-alerta").innerHTML = ``
            document.getElementById("alerta-img2").style.display = "none"
            document.getElementById("msg-alerta2").innerHTML = ``
            if (form1.style.display == "grid") {
                form1.style.display = "none"
                form2.style.display = "grid"
                form3.style.display = "none"
            }
            else {
                form1.style.display = "grid"
                form2.style.display = "none"
                form3.style.display = "none"
            }
        }
    }

    const mostrarForm2 = () => {
        var input_dataNasc = document.getElementById("dataNasc");

        var section1 = document.getElementById("section-cadastro1");
        var form2 = document.getElementById("form-cadastro2");
        var section2 = document.getElementById("section-foto");

        fetch(`https://viacep.com.br/ws/${valuesUsuario.cep}/json/`)
            .then(res => res.json()).then(data => {
                console.log(data)
            })
            .catch((error) => {
                console.log(error)
                document.getElementById("alerta-img2").style.display = "flex"
                document.getElementById("msg-alerta2").innerHTML = `CEP inválido`
            })

        if (valuesUsuario.nome == "" || valuesUsuario.sobrenome == "" || valuesUsuario.cpf == "" || valuesUsuario.genero == "" || input_dataNasc.value == "" || valuesUsuario.estadoCivil == "" || valuesUsuario.telefone == "" || valuesUsuario.cep == "" || valuesUsuario.grauEscolaridade == "" || valuesUsuario.fkSituacaoAtual == "") {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta2").innerHTML = `Preencha os campos vazios`
        } else if (valuesUsuario.cpf.length < 11) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta2").innerHTML = `CPF inválido`
        } else if (valuesUsuario.telefone.length < 11) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta2").innerHTML = `Telefone inválido`
        } else if (valuesUsuario.cep.length < 8) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta2").innerHTML = `CEP inválido`
        } else {
            document.getElementById("alerta-img").style.display = "none"
            document.getElementById("msg-alerta").innerHTML = ``
            document.getElementById("alerta-img2").style.display = "none"
            document.getElementById("msg-alerta2").innerHTML = ``
            if (section1.style.display == "flex") {
                section1.style.display = "none"
                form2.style.display = "none"
                section2.style.display = "flex"
            } else {
                section1.style.display = "flex"
                form2.style.display = "grid"
                section2.style.display = "none"
            }
        }
    }

    // Função para mostrar e ocultar a senha
    const ocultarSenha = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye4");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        }
        else {
            senha.type = "password";
            img.src = iconClose;
        }
    }

    const ocultarSenha2 = () => {
        var senha = document.getElementById("confSenha");
        var img = document.getElementById("eye5");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        }
        else {
            senha.type = "password";
            img.src = iconClose;
        }
    }

    return (
        <>
            <MenuSimples />
            <div id="section-cadastro1">
                <div id="form-cadastro1">
                    <div className="divisao centralizado">
                        <h1>CADASTRO DE USUÁRIO</h1>
                    </div>
                    <div className="divisao centralizado">
                        <img className="imp_progresso" src={imgEtapa} alt="Barra de progresso de cadastro" />
                    </div>
                    <div className="divisao input">
                        <label htmlFor="email">E-mail</label>
                        <input id="email" type="email" name="email" size="80" maxLength="80" value={valuesUsuario.email} required onChange={handleChangeUser} />
                    </div>

                    <div className="divisao input">
                        <label htmlFor="senha">
                            <div className="tooltip">Senha<img src={iconInfoSenha} alt="Ícone deconfirmação" />
                                <span className="tooltiptext">
                                    <b>Dicas para criar uma senha segura:</b>
                                    <br />Use no mínimo 6 caracteres
                                    <br />Combine letras maiúsculas e minúsculas, símbolos e números
                                    <br />Não use informações pessoais
                                </span>
                            </div>
                        </label>
                        <input id="senha" type="password" name="senha" size="18" maxLength="18" minLength="6" value={valuesUsuario.senha} required onChange={handleChangeUser} />
                        <img src={iconSenha} alt="Ícone senha escondida" className="ca-eye" id="eye4"
                            onClick={ocultarSenha} />
                    </div>
                    <div className="divisao input">
                        <label htmlFor="confSenha">Confirmar senha</label>
                        <input type="password" name="confSenha" id="confSenha" />
                        <img src={iconSenha} alt="Ícone senha escondida" className="ca-eye" id="eye5"
                            onClick={ocultarSenha2} />
                    </div>
                    <div id="alerta" className="coluna">
                        <img src={iconError} id="alerta-img" /><p id="msg-alerta"></p>
                    </div>
                    <div className="divisao direita">
                        <button className="btn-cadastro1" type="button" onClick={mostrarForm}>PRÓXIMO<img src={iconSeta} alt="Ícone de próximo" /></button>
                    </div>
                </div>

                <div id="form-cadastro2">
                    <div className="divisao centralizado">
                        <h1>CADASTRO DE USUÁRIO</h1>
                    </div>
                    <div className="divisao centralizado">
                        <img className="imp_progresso" src={imgEtapa2} alt="Barra de progresso de cadastro" />
                    </div>
                    <div className="divisao input maior">
                        <label htmlFor="nome">Nome</label>
                        <input id="nome" type="text" name="nome" size="45" maxLength="45" value={valuesUsuario.nome} required onChange={handleChangeUser} />
                    </div>
                    <div className="divisao input maior">
                        <label htmlFor="sobrenome">Sobrenome</label>
                        <input id="sobrenome" type="text" name="sobrenome" size="60" maxLength="60" value={valuesUsuario.sobrenome} required onChange={handleChangeUser} />
                    </div>
                    <div className="input_double">
                        <div className="input menor">
                            <label htmlFor="cpf">CPF</label>
                            <input id="cpf" type="text" name="cpf" size="11" maxLength="11" minLength="11" value={valuesUsuario.cpf} required onChange={handleChangeUser} />
                        </div>
                        <div className="input menor">
                            <label htmlFor="dataNasc">Data de nascimento</label>
                            <input id="dataNasc" type="date" name="dataNasc" required onChange={handleChangeData} />
                        </div>
                    </div>
                    <div className="input_double">
                        <div className="input menor">
                            <label htmlFor="genero">Gênero</label>
                            <select name="genero" id="genero" value={valuesUsuario.genero} required onChange={handleChangeUser}>
                                <option value="" selected disabled hidden></option>
                                <option value="F">Feminino</option>
                                <option value="M">Masculino</option>
                                <option value="X">Outro</option>
                            </select>
                        </div>
                        <div className="input menor">
                            <label htmlFor="estadoCivil">Estado civil</label>
                            <select name="estadoCivil" id="estadoCivil" value={valuesUsuario.estadoCivil} required onChange={handleChangeUser}>
                                <option value="" selected disabled hidden></option>
                                <option value="S">Solteiro(a)</option>
                                <option value="C">Casado(a)</option>
                                <option value="D">Divorciado(a)</option>
                                <option value="V">Viúvo(a)</option>
                            </select>
                        </div>
                    </div>
                    <div className="input_double">
                        <div className="input menor">
                            <label htmlFor="telefone">Telefone</label>
                            <input id="telefone" type="text" name="telefone" size="11" maxLength="11" minLength="11" pattern="^[0-9]+$" value={valuesUsuario.telefone} required onChange={handleChangeUser} />
                        </div>
                        <div className="input menor">
                            <label htmlFor="cep">CEP</label>
                            <input id="cep" type="text" name="cep" size="8" maxLength="8" minLength="8" pattern="^[0-9]+$" value={valuesUsuario.cep} required onChange={handleChangeUser} />
                        </div>
                    </div>
                    <div className="divisao input maior">
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
                    <div className="divisao input maior">
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
                    <div id="alerta" className="coluna">
                        <img src={iconError} id="alerta-img2" /><p id="msg-alerta2"></p>
                    </div>
                    <div className="input_double">
                        <button className="btn btn_voltar" type="button" onClick={mostrarForm}><img src={iconSeta2} alt="Ícone de voltar" /><p>VOLTAR</p></button>
                        <button className="btn btn_cadastrar" onClick={mostrarForm2}><p>PRÓXIMO</p><img src={iconSeta} alt="Ícone de confirmação para cadastrar o usuário" /></button>
                    </div>
                </div>
            </div>
            <div id="section-foto">
                <div id="cadastrar-foto">
                    <div className="divisao centralizado">
                        <h1>CADASTRO DE USUÁRIO</h1>
                    </div>
                    <div className="divisao centralizado">
                        <img className="imp_progresso" src={imgEtapa3} alt="Barra de progresso de cadastro" />
                    </div>
                    <div className="divisao flex-centralizada">
                        <p>Foto</p>
                        <div className="input-fotoPerfil">
                            <label htmlFor="fotoPerfil" id="label-fotoPerfil"><img src={iconCamera} alt="Ícone de câmera" id="icon-camera-1" /></label>
                            <input type="file" accept="image/*" name="fotoPerfil" id="fotoPerfil" />
                        </div>
                        <p className="aviso-foto">*Opcional. Caso não insira uma foto, será adicionado uma foto padrão.</p>
                    </div>
                    <div className="input_double">
                        <button className="btn btn_voltar" type="button" onClick={mostrarForm2}><img src={iconSeta2} alt="Ícone de voltar" /><p>VOLTAR</p></button>
                        <button className="btn btn_cadastrar" onClick={handleSubmit}><p>CADASTRAR</p><img src={iconOk} alt="Ícone de confirmação para cadastrar o usuário" /></button>
                    </div>
                </div>
            </div>
        </>
    )
}
export default Cadastro;
