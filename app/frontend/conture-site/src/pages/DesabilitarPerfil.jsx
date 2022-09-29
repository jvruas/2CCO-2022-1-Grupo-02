import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/DesabilitarPerfil.css';
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png";
import iconClose from "../html-css-template/imagens/eye-slash-closed.png";
import iconSad from "../html-css-template/imagens/icon-sad.svg";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import apiUsuario from "../apiUsuario.js";


function dataUsuario() {
    return {
        motivo: "",
        email: "",
        senha: ""
    }
}

function DesabilitarPerfil() {

    const navegar = useNavigate();

    const [valuesUsuario, setValuesUsuario] = useState(dataUsuario)

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuario({ ...valuesUsuario, [name]: value, })
        console.log(valuesUsuario)
    }



    function handleSubmit(event) {
        event.preventDefault()

        let senha = document.getElementById("senha");

        let usuario = {
            email: sessionStorage.getItem('idEmailLogado'),
            senha: valuesUsuario.senha
        }


        if (senha.value == "") {
            document.getElementById("msg-alerta").innerHTML = `Preencha os campos vazios`
        } else {
            apiUsuario.delete(`?motivoDesligamento=${valuesUsuario.motivo}`, {
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    email: usuario.email,
                    senha: usuario.senha
                }
            }).then((resposta) => {
                sessionStorage.setItem('logado', "")
                sessionStorage.setItem('idUsuarioLogado', "")
                sessionStorage.setItem('idEmailLogado', "")
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

    return (
        <>
            <Header />
            <section id="dc-section">
                <div className="grid">
                    <div id="dc-parte-um">
                        <div className="dc-opcao dc-opcao-selecionada">
                            <Link className="link-p" to="/editar-perfil"><p>Editar perfil</p></Link>
                        </div>
                        <div className="dc-opcao">
                            <Link className="link-p" to="/alterar-senha"><p>Trocar senha</p></Link>
                        </div>
                        <div className="dc-opcao">
                            <Link className="link-p" to="/validacao-usuario"><p>Validação</p></Link>
                        </div>
                    </div>
                    <div id="dc-parte-dois">
                        <div className="dc-titulo">
                            <h2>DESABILITAR PERFIL</h2>
                        </div>

                        <div className="dc-campos">
                            <p>Olá <span>Yan</span>,<br />
                                Você tem certeza que gostaria desativar sua conta permanentemente?</p>
                            <div className="dc-campo">
                                <label htmlFor="motivo">Por que você está desativando sua conta?</label>
                                <select name="motivo" id="motivo" value={valuesUsuario.motivo} required onChange={handleChangeUser}>
                                    <option value=""></option>
                                    <option value="A">Anúncios em excesso</option>
                                    <option value="T">Preciso dar um tempo</option>
                                    <option value="Q">Questões de privacidade</option>
                                    <option value="P">Problemas em utilizar o site</option>
                                    <option value="N">Não consigo achar os eletrônicos que preciso</option>
                                    <option value="X">Outro motivo</option>
                                </select>
                            </div>
                            <div className="dc-campo">
                                <label htmlFor="senha">Insira sua senha</label>
                                <input type="password" name="senha" className="senha" id="senha" value={valuesUsuario.senha} required onChange={handleChangeUser} />
                                <img src={iconClose} alt="" className="eye" id="eye1" onClick={ocultarSenha} />
                            </div>
                        </div>
                        <div className="dc-aviso">
                            <p id="msg-alerta"></p>
                        </div>
                        <div className="dc-btns">
                            <Link to="/editar-perfil"><div>VOLTAR</div></Link>
                            <button type="button" onClick={handleSubmit}>
                                <p>DESABILITAR</p><img src={iconSad} alt="Ícone de tristeza" />
                            </button>
                        </div>

                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default DesabilitarPerfil;