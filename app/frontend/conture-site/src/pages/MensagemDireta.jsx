import Header from "../components/Header";
import Footer from "../components/Footer";
import Chat from "../components/mensagem-direta/Chat";
import MensagemOutro from "../components/mensagem-direta/MensagemOutro";
import MensagemUsuario from "../components/mensagem-direta/MensagemUsuario";
import '../html-css-template/css/MensagemDireta.css';
import iconLupaPreta from "../html-css-template/imagens/icon-lupa-preta.svg"
import iconSend from "../html-css-template/imagens/icon-send.svg"
import fotoPatricia from "../html-css-template/imagens/patricia.jpg"
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";
import apiMensagemDireto from "../apiMensagemDireta";



function MensagemDireta() {

    const [chat, setChat] = useState([]);
    const [remetente, setRemetente] = useState([]);

    useEffect(() => {
        let idUsuarioLogado = sessionStorage.getItem('idUsuarioLogado');
        apiMensagemDireto.get(`chat/all?fkUsuarioRemetente=${idUsuarioLogado}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setChat(resposta.data)
            } catch (error) {
                console.log(error)  
            }
        })
    })

    useEffect(() => {
        apiUsuario.get(`/${chat.idUsuario}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setRemetente(resposta.data)
            } catch (error) {
                console.log(error)  
            }
        })
    })

    function formatacaoId(id){
        console.log("tamanho" + id.length)
        if(id.length == 1){
            return "#000" + id;
        }else if(id.length == 2){
            return "#00" + id;
        }else if(id.length == 3){
            return "#0" + id;
        }else{
            return "#" + id;
        }
    }

    return (
        <>
            <Header/>
            <section id="md-section">
                <div className="grid">
                    <div id="md-parte-um">
                        <div id="md-pequisa">
                            <h2>MENSAGEM</h2>
                            <div>
                                <button>
                                    <img src={iconLupaPreta} alt="Ícone de lupa para pesquisa" />
                                </button>
                                <input type="text" />
                            </div>
                        </div>
                        <div id="md-chats">
                            <div className="md-chats-interno scroll">
                            {chat != undefined && chat.length > 0 ? chat.map((chat) => (
                            <Chat
                                // foto={chat.}
                                nome={chat.nome}
                                id={formatacaoId(chat.idUsuario)}
                            />
                        )) : ""}
                               
                            </div>
                        </div>
                    </div>
                    <div id="md-parte-dois">
                        <div id="md-cabecalho">
                            <img src={fotoPatricia} alt="Foto do outro usuário" />
                            <div>
                                <h3>{remetente.nome}</h3>
                            </div>
                            <div>
                                <p>#{remetente.id}</p>
                            </div>
                        </div>
                        <div id="md-mensagens" className="scroll">
                           <MensagemOutro/>
                           <MensagemUsuario/>
                    
                        </div>
                        <div id="md-enviar-mensagem">
                            <input type="text" />
                            <button>
                                <img src={iconSend} alt="Ícone de enviar mensagem" />
                            </button>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default MensagemDireta;