import Header from "../components/Header";
import Footer from "../components/Footer";
import Chat from "../components/mensagem-direta/Chat";
import MensagemOutro from "../components/mensagem-direta/MensagemOutro";
import MensagemUsuario from "../components/mensagem-direta/MensagemUsuario";
import '../html-css-template/css/MensagemDireta.css';
import iconLupaPreta from "../html-css-template/imagens/icon-lupa-preta.svg"
import iconSend from "../html-css-template/imagens/icon-send.svg"
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";


function MensagemDireta() {


    const navegar = useNavigate();

    const [usuario, setUsuario] = useState([]);
    const [usuarioImg, setUsuarioImg] = useState([]);

    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado')
        apiUsuario.get(`/?idUsuario=${idUsuario}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setUsuario(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })

        apiUsuario.get(`/?idUsuario=${idUsuario}/imagem`).then((resposta) => {
            try {
                console.log(resposta.data)
                setUsuarioImg(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])


    return (
        <>
            <Header nome={usuario.nome} imagem_usuario={usuarioImg.imagem_usuario}/>
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
                               <Chat/>
            
                            </div>
                        </div>
                    </div>
                    <div id="md-parte-dois">
                        <div id="md-cabecalho">
                            <img src="../img/foto.jpg" alt="Foto do outro usuário" />
                            <div>
                                <h3>Patrícia</h3>
                            </div>
                            <div>
                                <p>#0002</p>
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