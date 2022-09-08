import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/AlterarSenha.css';
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import iconSalvar from "../html-css-template/imagens/folder-plus.png"
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import apiUsuario from "../apiUsuario.js";


function dataUsuario() {
    return {
        idUsuario: "",
        senhaAtual: "",
        senhaNova: ""
    }
}

function AlterarSenha() {

    const navegar = useNavigate();

    // Função para chamar o endPoint para cadastrar o usuário
    const [valuesUsuario, setValuesUsuario] = useState(dataUsuario)

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
        valuesUsuario.idUsuario = sessionStorage.getItem('idUsuarioLogado');
        console.log(valuesUsuario)
    }

    function handleSubmit(event) {
        event.preventDefault()
        
        let senhaAtual = document.getElementById("senha");
        let senhaNova = document.getElementById("senha2");
        var input_senha2 = document.getElementById("senha3");

        
        let json = {
            idUsuario: valuesUsuario.idUsuario,
            senhaAtual: valuesUsuario.senhaAtual,
            senhaNova: valuesUsuario.senhaNova
        }

        if (senhaAtual.value == "" || senhaNova.value == "" || input_senha2.value == "") {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `Preencha os campos vazios`
        } else if (senhaNova.value != input_senha2.value) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `As senhas não correspondem`
        } else if ((senhaNova.value).length >= 1 && (senhaNova.value).length < 6) {
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `A senha deve ter mais que 6 caracteres`
        } else if(senhaAtual.value == input_senha2.value){
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta").innerHTML = `A nova senha não pode ser igual senha atual`
        }else {
            apiUsuario.patch("/senha", json, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((resposta) => {
                    navegar("/")
                    console.log(resposta.status)
                }).catch((error) => {
                    console.log(error)
                    document.getElementById("msg-alerta").innerHTML = `Erro`
                })
        }
    }

    const ocultarSenha = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye1");
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
        var senha2 = document.getElementById("senha2");
        var img2 = document.getElementById("eye2");
        if (senha2.type == "password") {
            senha2.type = "text";
            img2.src = iconOpen;
        }
        else {
            senha2.type = "password";
            img2.src = iconClose;
        }
    }

    const ocultarSenha3 = () => {
        var senha2 = document.getElementById("senha3");
        var img2 = document.getElementById("eye3");
        if (senha2.type == "password") {
            senha2.type = "text";
            img2.src = iconOpen;
        }
        else {
            senha2.type = "password";
            img2.src = iconClose;
        }
    }

    return (
        <>
            <Header />
            <section id="as-section">
                <div className="grid">
                    <div id="as-parte-um">
                        <div className="as-opcao">
                            <Link className="link-p" to="/editar-perfil"><p>Editar perfil</p></Link>
                        </div>
                        <div className="as-opcao as-opcao-selecionada">
                            <Link className="link-p" to="/alterar-senha"><p>Trocar senha</p></Link>
                        </div>
                        <div className="as-opcao">
                            <Link className="link-p" to="/validacao-usuario"><p>Validação</p></Link>
                        </div>
                    </div>
                    <div id="as-parte-dois">
                        <div className="as-titulo">
                            <h2>TROCAR SENHA</h2>
                        </div>
                        <div className="as-campos">
                            <div className="as-campo">
                                <label htmlFor="senhaAtual">Insira sua senha</label>
                                <input type="password" name="senhaAtual" className="senha-desabilitar" id="senha" size="18" maxLength="18" minLength="6" value={valuesUsuario.senhaAtual} required onChange={handleChangeUser} />
                                <img src={iconClose} alt="" className="eye" id="eye1" onClick={ocultarSenha} />
                            </div>
                            <div className="as-campo">
                                <label htmlFor="senhaNova">Insira sua senha</label>
                                <input type="password" name="senhaNova" className="senha-desabilitar" id="senha2" size="18" maxLength="18" minLength="6" value={valuesUsuario.senhaNova} required onChange={handleChangeUser} />
                                <img src={iconClose} alt="" className="eye" id="eye2" onClick={ocultarSenha2} />
                            </div>
                            <div className="as-campo">
                                <label htmlFor="senhaSeguranca">Insira sua senha</label>
                                <input type="password" name="senhaSeguranca" className="senha-desabilitar" id="senha3" size="18" maxLength="18" minLength="6" required />
                                <img src={iconClose} alt="" className="eye" id="eye3" onClick={ocultarSenha3} />
                            </div>
                        </div>
                        <div className="as-aviso">
                            <img src={iconError} id="alerta-img2"/><p id="msg-alerta"></p>
                        </div>
                        <div className="as-btns">
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

export default AlterarSenha;
