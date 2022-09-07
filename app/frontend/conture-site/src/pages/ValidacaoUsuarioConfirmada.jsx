import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/ValidacaoUsuarioConfirmada.css';
import ImagemValidado from "../html-css-template/imagens/imagem-validado.svg";
import IconComemoracao from "../html-css-template/imagens/icon-comemoracao.svg";

function ValidacaoUsuarioConfirmada() {

    return (
        <>
            <Header />
            <section id="vlc-section">
                <div className="grid">
                    <div id="vlc-parte-um">
                        <div className="vlc-opcao">
                            <p>Editar perfil</p>
                        </div>
                        <div className="vlc-opcao">
                            <p>Trocar senha</p>
                        </div>
                        <div className="vlc-opcao vlc-opcao-selecionada">
                            <p>Validação</p>
                        </div>
                    </div>
                    <div id="vlc-parte-dois">
                        <div className="vlc-titulo">
                            <h2>VALIDAÇÃO</h2>
                        </div>
                        <div className="vlc-mensagem">
                            <p>Sua conta foi validada com sucesso, a partir de agora você terá<br/>
                                mais chances de receber doações através de Matchs.</p>
                            <img src={ImagemValidado} alt="" className="vlc-imagem"/>
                            <p>Obrigado por validar sua conta <span>Yan </span><img src={IconComemoracao} alt="" className="vlc-icon"/></p>
                        </div>
                        <div className="vlc-btns">
                            <button>
                                Voltar para a Home
                            </button>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default ValidacaoUsuarioConfirmada;
