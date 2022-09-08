import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/ValidacaoUsuario.css';
import IconCheck from "../html-css-template/imagens/icon-check.svg";
import { Link } from "react-router-dom";

function ValidacaoUsuario() {

    return (
        <>
            <Header />
            <section id="vl-section">
                <div className="grid">
                    <div id="vl-parte-um">
                        <div className="vl-opcao">
                            <Link className="link-p" to="/editar-perfil"><p>Editar perfil</p></Link>
                        </div>
                        <div className="vl-opcao">
                            <Link className="link-p" to="/alterar-senha"><p>Trocar senha</p></Link>
                        </div>
                        <div className="vl-opcao vl-opcao-selecionada">
                            <Link className="link-p" to="/validacao-usuario"><p>Validação</p></Link>
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
                            <label htmlFor="codigo-validacao">Coloque o código</label>
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