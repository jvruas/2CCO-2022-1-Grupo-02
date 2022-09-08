import '../../html-css-template/css/MensagemDireta.css';
import fotoPatricia from "../../html-css-template/imagens/patricia.jpg"

function Chat() {

    return (
        <>
            <div className="md-chat">
                <div className="identificacao">
                    <img src={fotoPatricia} alt="" id="md-chats-foto" />
                    <div>
                        <h4>Patrícia</h4>
                        <p>#0002</p>
                    </div>
                </div>
                <button>
                    <img src="../img/icon-lixo.svg" alt="" id="md-chats-icon" />
                </button>
            </div>
        </>
    )
}

export default Chat;