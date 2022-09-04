import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/ValidacaoUsuario.css';
import IconCheck from "../html-css-template/imagens/icon-check.svg";

function ValidacaoUsuario() {

    return (
        <>
            <Header />
            <section>
                <div className="grid">
                    <div id="vl-parte-um">
                        <div className="vl-opcao">
                            <p>Editar perfil</p>
                        </div>
                        <div className="vl-opcao">
                            <p>Trocar senha</p>
                        </div>
                        <div className="vl-opcao vl-opcao-selecionada">
                            <p>Validação</p>
                        </div>
                    </div>
                    <div id="vl-parte-dois">
                        <div className="vl-titulo">
                            <h2>VALIDAÇÃO</h2>
                        </div>
                        <div className="vl-mensagem">
                            <p>Olá <span>Yan</span>,</p>
                            <p>Para ajudar a manter sua conta segura, a Conture quer ter certeza de que é realmente você que está autenticado.</p>
                            <p>Um e-mail com um código de verificação sera enviado para <b><span>yan••••••••@gmail.com</span></b></p>
                        </div>
                        <div className="vl-campos">
                            <label for="codigo-validacao">Coloque o código</label>
                            <input type="text" name="codigo-validacao" id="codigo-validacao" />
                        </div>
                        <div className="vl-aviso">
                            <p></p>
                        </div>
                        <div className="vl-btns">
                            <button>
                                <p>CONFIRMAR</p><img src={IconCheck} alt="Ícone confirmar" />
                            </button>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default ValidacaoUsuario;