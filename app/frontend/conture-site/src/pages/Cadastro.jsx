import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Cadastro.css'
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import imgEtapa from "../html-css-template/imagens/Etapas.png"
import imgEtapa2 from "../html-css-template/imagens/Etapas (1).png"
import iconInfoSenha from "../html-css-template/imagens/info-circle 1.png"
import iconSeta from "../html-css-template/imagens/seta.png"
import iconSeta2 from "../html-css-template/imagens/seta 2.png"
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { Link } from "react-router-dom";
import iconOk from "../html-css-template/imagens/icon-ok.png"
import api from "../api.js";
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

function Cadastro() {

    // Função para chamar o endPoint para trazer as "situações atuais"
    const [fkSituacaoAtual, setSituacaoAtual] = useState([]);

    useEffect(() => {
        api.get("/situacao-atual").then((resposta) => {
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

    function epoch(date) {
        return Date.parse(date)
    }

    function handleChangeData(dataNasc) {
        var input_dataNasc = document.getElementById("dataNasc");
        dataUsuario.dataNasc = input_dataNasc.value
        console.log(epoch(input_dataNasc.value))
    }

    function handleSubmit(event) {
        var input_dataNasc = document.getElementById("dataNasc");
        event.preventDefault()
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
            grauEscolaridade: valuesUsuario.grauEscolaridade,
            fkSituacaoAtual: valuesUsuario.fkSituacaoAtual
        }
        console.log(json)
        api.post("/", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((resposta) => {
                 console.log(resposta.status)
        }).catch((error) => { console.log(error) })
    }

    // Função para mudar o formulário de cadastro
    const mostrarForm = () => {
        var form1 = document.getElementById("form-cadastro1");
        var form2 = document.getElementById("form-cadastro2");

        var input_email = document.getElementById("email");

        var input_senha = document.getElementById("senha");
        var input_confSenha = document.getElementById("confSenha");

        if (input_senha.value != input_confSenha.value) {
            alert("As senhas não correspondem")
        } else if ((input_senha.value).length < 6) {
            alert("A senha deve ter mais que 6 caracteres")
        } else if ((input_email.value).search("@") == -1) {
            alert("E-mail inválido")
        } else {
            if (form1.style.display == "grid") {
                form1.style.display = "none"
                form2.style.display = "grid"
            }
            else {
                form1.style.display = "grid"
                form2.style.display = "none"
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
            <form className="section-cadastro1">
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
                        {/* <div>e-mail inválido</div> */}
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
                        <img src={iconSenha} alt="Ícone senha escondida" className="eye" id="eye4"
                            onClick={ocultarSenha} />
                    </div>

                    <div className="divisao input">
                        <label htmlFor="confSenha">Confirmar senha</label>
                        <input type="password" name="confSenha" id="confSenha" />
                        <img src={iconSenha} alt="Ícone senha escondida" className="eye" id="eye5"
                            onClick={ocultarSenha2} />
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
                                <option value=""></option>
                                <option value="F">Feminino</option>
                                <option value="M">Masculino</option>
                                <option value="X">Outro</option>
                            </select>
                        </div>
                        <div className="input menor">
                            <label htmlFor="estadoCivil">Estado civil</label>
                            <select name="estadoCivil" id="estadoCivil" value={valuesUsuario.estadoCivil} required onChange={handleChangeUser}>
                                <option value=""></option>
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
                    <div className="divisao input maior">
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
                    <div className="input_double">
                        <button className="btn btn_voltar" type="button" onClick={mostrarForm}><img src={iconSeta2} alt="Ícone de voltar" /><p>VOLTAR</p></button>
                        <button className="btn btn_cadastrar" onClick={handleSubmit}><p>CADASTRAR</p><img src={iconOk} alt="Ícone de confirmação para cadastrar o usuário" /></button>
                    </div>
                </div>
            </form>
        </>
    )
}

export default Cadastro;
