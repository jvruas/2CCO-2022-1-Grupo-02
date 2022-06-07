import '../html-css-template/css/PopUp.css'
import iconClosePop from "../html-css-template/imagens/../imagens/x-lg 1.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import api from "../api.js";


function PopUp() {

    const navegar = useNavigate();

    function handleSubmit(event) {
        event.preventDefault()
        let param = document.getElementById("email");
        api.get("/email", {
            params: {
                email: param.value
            }
        }).then((resposta) => {
            sessionStorage.setItem('idUsuario', resposta.data)
            console.log(resposta.status)
            navegar("/esqueci-senha")
        }).catch((error) => { 
            console.log(error)
            if(param.value == ""){
                document.getElementById("alerta-img").style.display = "flex"
                document.getElementById("msg-alerta").innerHTML = `Preencha o campo vazios`
            }else{
                document.getElementById("alerta-img").style.display = "flex"
                document.getElementById("msg-alerta").innerHTML = `E-mail inválido`
            }     
        })
    }

    return (
        <>
            <section id="esqueceu_senha">
                <div className="pop_up">
                    <form id="popUp">
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
                            <img src={iconError} id="alerta-img"/><p id="msg-alerta"></p>
                        </div>
                        <div className="divisao centralizado">                          
                            <button className="btn-popUp" type="submit" onClick={handleSubmit}><p>CONFIRMAR</p><img className="icon" src={iconOk} alt="Ícone de confirmação" /></button>
                        </div>
                    </form>
                </div>
            </section>
        </>
    )
}

export default PopUp;