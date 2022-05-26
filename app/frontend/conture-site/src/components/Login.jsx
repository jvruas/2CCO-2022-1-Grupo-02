import MenuSimples from "./MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Login.css'
import '../html-css-template/js/Login.js'
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"

function Login() {
    return (
        <>
            <MenuSimples />
            <section className="centralizado">
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
                        <img src={iconSenha} alt="Ícone senha escondida" className="eye" id="eye" onClick="mostrarOcultarSenha()" />
                    </div>
                    <div className="divisao">
                        <a href="EsqueciSenha.html">Esqueceu sua senha?</a>
                    </div>
                    <div className="divisao centralizado">
                        <button><p>ENTRAR</p><img src={iconOk} alt="Ícone de confirmação" /></button>
                    </div>

                </form>
                <div className="cadastre centralizado">
                    <p>Ainda não é cadastrado? <a href="Cadastro1.html"><b>Cadastre-se</b></a></p>
                </div>
            </section>
        </>
    );
}

export default Login;