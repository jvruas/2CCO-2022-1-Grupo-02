import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Login.css'
import '../html-css-template/js/Login.js'
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import { Link } from "react-router-dom";

function Login() {

    const handleClick = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        } 
        else{
            senha.type = "password";
            img.src = iconClose;
        }
    }

    return (
        <>
            <MenuSimples />
            <section className="section-login centralizado">
                <form id="form-login" action="" onsubmit="">
                    <div className="divisao centralizado">
                        <h1>LOGIN</h1>
                    </div>
                    <div className="divisao input">
                        <label for="nome">E-mail</label>
                        <input type="email" name="nome" />
                    </div>
                    <div className="divisao input">
                        <label for="senha">Senha</label>
                        <input type="password" name="senha" id="senha" />
                        <img src={iconSenha} alt="Ícone senha escondida" className="eye" id="eye" onClick={handleClick} />
                    </div>
                    <div className="divisao">
                        <Link class="link-esqc" to="/email-esqueci-senha"><a>Esqueceu sua senha?</a></Link>
                    </div>
                    <div className="divisao centralizado">
                        <button className="btn-login"><p>ENTRAR</p><img src={iconOk} alt="Ícone de confirmação" /></button>
                    </div>

                </form>
                <div className="cadastre centralizado">
                    <p>Ainda não é cadastrado?<Link className="link-cadastro" to="/cadastro"><a><b class="purple"> Cadastre-se</b></a></Link></p>
                </div>
            </section>
        </>
    );
}

export default Login;