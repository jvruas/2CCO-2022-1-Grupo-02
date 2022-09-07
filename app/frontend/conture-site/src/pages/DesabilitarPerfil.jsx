import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/DesabilitarPerfil.css';
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png";
import iconClose from "../html-css-template/imagens/eye-slash-closed.png";
import iconSad from "../html-css-template/imagens/icon-sad.svg";

function DesabilitarPerfil() {

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
                            <p>Editar perfil</p>
                        </div>
                        <div className="dc-opcao">
                            <p>Trocar senha</p>
                        </div>
                        <div className="dc-opcao">
                            <p>Validação</p>
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
                                <label htmlFor="motivo-desabilitar">Por que você está desativando sua conta?</label>
                                <select name="motivo-desabilitar" id="motivo-desabilitar"></select>
                            </div>
                            <div className="dc-campo">
                                <label htmlFor="senha">Insira sua senha</label>
                                <input type="password" name="senha-desabilitar" className="senha-desabilitar" id="senha" />
                                <img src={iconClose} alt="" className="eye" id="eye1" onClick={ocultarSenha} />
                            </div>
                        </div>
                        <div className="dc-aviso">
                            <p></p>
                        </div>
                        <div className="dc-btns">
                            <a href=""><div>VOLTAR</div></a>
                            <button>
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