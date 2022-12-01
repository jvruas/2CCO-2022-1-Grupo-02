import '../../html-css-template/css/MensagemDireta.css';
import fotoPatricia from "../../html-css-template/imagens/patricia.jpg"
import iconLixo from "../../html-css-template/imagens/icon-lixo.svg"
import { useEffect, useState } from "react";
import apiMensagemDireto from '../../apiMensagemDireta';
import ListarMensagens from './ListarMensagens';

function Chat(props) {


    const [mensagens, setMensagens] = useState([]);

    function getMensagens(){
        let idUsuarioLogado = sessionStorage.getItem('idUsuarioLogado');

        sessionStorage.setItem(`tamanhoMensa${idUsuarioLogado}`,mensagens.length)

        for(var i =0;i<mensagens.length;i++){
            sessionStorage.setItem(`mensa${idUsuarioLogado}.${i}`, mensagens[i].mensagem);
        }
        
        function sleep(ms) {
            return new Promise(resolve => setTimeout(resolve, ms));
        }

        async function delayedGreeting() {
            await sleep(300);
            window.location.reload(true);
        }
        delayedGreeting()
    }

    useEffect(() => {
        let idUsuarioLogado = sessionStorage.getItem('idUsuarioLogado');
        apiMensagemDireto.get(`?fkUsuarioRemetente=${props.idUsuario}&fkUsuarioDonatario=${idUsuarioLogado}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setMensagens(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    },[]);

    return (
        <>
            <div className="md-chat" onClick={getMensagens}>
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