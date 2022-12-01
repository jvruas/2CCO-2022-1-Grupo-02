import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuAvaliacaoPessoal"
import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';
import '../html-css-template/css/Style.css'
import '../html-css-template/css/AvaliacaoPage.css'
import Footer from "../components/Footer"
import Nota from "../components/Nota"
import Comentarios from "../components/Avaliacao"
import apiProdutos from "../apiProduto.js"
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js"


function AvaliacaoPessoal() {

    const [produtos, setProdutos] = useState([]);
    const [usuarioAva, setUsuarioAva] = useState([]);
    const [endereco, setEndereco] = useState([]);
    var aval = [];

    useEffect(() => {
        let idDoador = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`/avaliacao?idDoador=${idDoador}`).then((resposta) => {

            console.log("Fila: " + resposta.data.fila)
            aval = resposta.data.fila;

            // for(var i = 0; i < resposta.data.file.length; i++){
            //     apiUsuario.get(`${resposta.data.fila[i].fkDonatario}/imagem?tipoImagem=P`,
            //     { responseType: 'blob' }).then((respostaImg) => {
            //         let imgUrl = URL.createObjectURL(respostaImg.data)
            //         resposta.data.file[i].perfilImage = imgUrl;
            //     }).catch((error) => {
                    
            //     })
            // }
    
            setUsuarioAva(resposta.data.fila)
            console.log("simsim",resposta.data.fila)
            try {
            } catch (error) {
                console.log(error)
            }

        })
    }, [])

    function formatacaoId(id) {
        if (id < 10) {
            return "#000" + id;
        } else if (id < 100) {
            return "#00" + id;
        } else if (id < 1000) {
            return "#0" + id;
        } else {
            return "#" + id;
        }
    }

    return (
        <>
            <Header></Header>
            <section id="ava-section">
                <Perfil />
                <MenuPerfil></MenuPerfil>
                <div className="conteiner-avaliacao">
                    <div className="div_superior_ava"><b><p>Avaliação</p></b></div>
                    <div className="div_inferior">
                        <Nota></Nota>
                    </div>
                    <div className="comentarios">
                        {usuarioAva != undefined && usuarioAva.length > 0 ? usuarioAva.map((ava) => (
                            <Comentarios
                                idDoador={formatacaoId(ava.fkDonatario)}
                                otherId={ava.fkDonatario}
                                nota={ava.valor}
                                comentario={ava.comentario}
                                donatario={ava.nome}
                                dataCon={ava.data}
                                foto={ava.perfilImage != null || ava.perfilImage != undefined ? ava.perfilImage : fotoLogado}
                            />
                        )) : ""}


                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default AvaliacaoPessoal;