import '../../html-css-template/css/MensagemDireta.css';
import fotoPatricia from "../../html-css-template/imagens/patricia.jpg"
import iconLixo from "../../html-css-template/imagens/icon-lixo.svg"

function Chat(props) {

    return (
        <>
            <div className="md-chat">
                <div className="identificacao">
                    <img src={props.foto} alt="" id="md-chats-foto" />
                    <div>
                        <h4>{props.nome}</h4>
                        <p>{props.id}</p>
                    </div>
                </div>
                <button>
                    <img src={iconLixo} alt="" id="md-chats-icon" />
                </button>
            </div>
        </>
    )
}

export default Chat;