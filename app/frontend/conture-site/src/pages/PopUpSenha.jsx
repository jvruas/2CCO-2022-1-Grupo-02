import '../html-css-template/css/PopUp.css'
import iconClosePop from "../html-css-template/imagens/../imagens/x-lg1.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import apiUsuario from "../apiUsuario.js";


function PopUp() {

    const navegar = useNavigate();

    function validacaoEmail(event) {
        event.preventDefault()
        let input_email = document.getElementById("email");
        apiUsuario.get(`/email?email=${input_email.value}`, {
        }).then((respostaEmail) => {
            console.log("Esse é status do e-mail reconhecido: " + respostaEmail.status)
            let idUsuarioRetornado = respostaEmail.data;
            sessionStorage.setItem('idUsuarioRetornado', respostaEmail.data)
            apiUsuario.post(`codigo-senha/email?idUsuario=${idUsuarioRetornado}`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((respostaCodigo) => {
                console.log("Esse é status do código enviado: " + respostaCodigo.status)
                document.getElementById("pop_up").style.display = "none"
                document.getElementById("pop_up-dois").style.display = "flex"
            }).catch((error) => {
                console.log("Esse é status do código enviado: " + error)
            })
        }).catch((error) => {
            console.log("Esse é status do e-mail reconhecido: " + error)
            if (input_email.value == "") {
                document.getElementById("alerta-img").style.display = "flex"
                document.getElementById("msg-alerta").innerHTML = `Preencha o campo vazio`
            } else {
                document.getElementById("alerta-img").style.display = "flex"
                document.getElementById("msg-alerta").innerHTML = `E-mail inválido`
            }
        })
    }

    function validacaoCodigo(event) {
        event.preventDefault()
        let input_codigo = document.getElementById("codigo");

        if(input_codigo.value == ""){
            document.getElementById("alerta-img2").style.display = "flex"
            document.getElementById("msg-alerta2").innerHTML = `Preencha o campo vazio`
        }else{
            apiUsuario.post(`codigo-senha?idUsuario=${sessionStorage.getItem('idUsuarioRetornado')}&codigo=${input_codigo.value}`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((resposta) => {
                console.log("Esse é status do código enviado: " + resposta.status)
                navegar("/esqueci-senha")
            }).catch((error) => {
                console.log("Esse é status do código enviado: " + error)
                document.getElementById("alerta-img2").style.display = "flex"
                document.getElementById("msg-alerta2").innerHTML = `Código incorreto`
            })
        }
    }


    return (
        <>
            <section id="esqueceu_senha">
                <div id="pop_up">
                    <form className="popUp">
                        <div className="divisao centralizado">
                            <h2>REDEFINIR A SENHA</h2>
                            <Link className="link-fechar" to="/login"><img className="btn_fechar" src={iconClosePop} alt="Ícone fechar" /></Link>
                        </div>
                        <div className="paragrafo">
                            <p>Identifique-se para receber um e-mail com as instruções e o link para criar uma nova senha.</p>
                        </div>
                        <div className="divisao input">
                            <label htmlFor="email">E-mail</label>
                            <input id="email" type="email" name="email" size="80" maxLength="80" required />
                        </div>
                        <div id="alerta" className="coluna">
                            <img src={iconError} id="alerta-img" /><p id="msg-alerta"></p>
                        </div>
                        <div className="divisao centralizado">
                            <button className="btn-popUp" type="submit" onClick={validacaoEmail}><p>CONFIRMAR</p><img className="icon" src={iconOk} alt="Ícone de confirmação" /></button>
                        </div>
                    </form>
                </div>
                <div id="pop_up-dois">
                    <form className="popUp">
                        <div className="divisao centralizado">
                            <h2>REDEFINIR A SENHA</h2>
                            <Link className="link-fechar" to="/login"><img className="btn_fechar" src={iconClosePop} alt="Ícone fechar" /></Link>
                        </div>
                        <div className="paragrafo">
                            <p>Insira o código que foi enviado para seu e-mail para dar continuidade no processo.</p>
                        </div>
                        <div className="divisao input">
                            <label htmlFor="codigo">Código</label>
                            <input id="codigo" type="text" name="codigo" size="8" maxLength="8" required />
                        </div>
                        <div id="alerta-dois" className="coluna">
                            <img src={iconError} id="alerta-img2" /><p id="msg-alerta2"></p>
                        </div>
                        <div className="divisao centralizado">
                            <button className="btn-popUp" type="submit" onClick={validacaoCodigo}><p>CONFIRMAR</p><img className="icon" src={iconOk} alt="Ícone de confirmação" /></button>
                        </div>
                    </form>
                </div>
            </section>
        </>
    )
}

export default PopUp;