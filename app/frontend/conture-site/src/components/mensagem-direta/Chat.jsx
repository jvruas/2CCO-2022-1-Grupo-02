import '../../html-css-template/css/MensagemDireta.css';
import apiMensagemDireto from "../../apiMensagemDireta";
import { useState } from 'react';

function Chat(props) {

    const [mensagens, setMensagens] = useState([]);

    function getMensagens(idRemetente) {
        let idUsuarioLogado = sessionStorage.getItem('idUsuarioLogado');
        apiMensagemDireto.get(`?fkUsuarioRemetente=${idRemetente}&fkUsuarioDonatario=${idUsuarioLogado}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setMensagens(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }

    return (
        <>
            <div className="md-chat" onClick={getMensagens(props.idUsuario)}>
                <div className="identificacao">
                    <img src={props.foto} alt="Foto do usuário" id="md-chats-foto" />
                    <div>
                        <h4>{props.nome}</h4>
                        <p>{props.id}</p>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Chat;