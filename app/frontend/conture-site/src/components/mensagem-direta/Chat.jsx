import '../../html-css-template/css/MensagemDireta.css';

function Chat() {

    return (
        <>
            <div className="md-chat">
                <div className="identificacao">
                    <img src="../img/foto.jpg" alt="" id="md-chats-foto" />
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