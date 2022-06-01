import '../html-css-template/css/PopUp.css'
import iconClosePop from "../html-css-template/imagens/../imagens/x-lg 1.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"
import { Link } from "react-router-dom";

function PopUp() {
    return (
        <>
            <section id="esqueceu_senha">
                <div class="pop_up">
                    <form id="popUp" action="" onsubmit="">
                        <div class="divisao centralizado">
                            <h2>REDEFINIR A SENHA</h2>
                            <Link className="link-fechar" to="/login"><img class="btn_fechar" src={iconClosePop} alt="Ícone fechar" /></Link>
                        </div>
                        <div class="paragrafo">
                            <p>Identifique-se para receber um e-mail com as instruções e o link para criar uma nova senha.</p>
                        </div>
                        <div class="divisao input">
                            <label for="email">E-mail</label>
                            <input type="email" name="email" />
                        </div>
                        <div class="divisao centralizado">
                            <Link className="link-popUp" to="/esqueci-senha"><button className="btn-popUp"><p>CONFIRMAR</p><img class="icon" src={iconOk} alt="Ícone de confirmação" /></button></Link>
                        </div>
                    </form>
                </div>
            </section>
        </>
    );
}

export default PopUp;