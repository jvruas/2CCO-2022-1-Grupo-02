import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuAvaliacaoPessoal"
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

            for (let i = 0; i < resposta.data.fila.length; i++) {
                apiUsuario.get(`/${resposta.data.fila[i].fkDonatario}`).then((usuarioResposta) => {
                    try {
                        // console.log(usuarioResposta.data)
                        aval[i].nome = usuarioResposta.data.nome;
                
                        fetch(`https://viacep.com.br/ws/${usuarioResposta.data.cep}/json/`)
                            .then(res => res.json()).then(data => {
                                console.log(data)
                                aval[i].cidade = data.localidade;
                                aval[i].uf = data.uf;
                            })
                    } catch (error) {
                        console.log(error)
                    }
                })
            }
            console.log("Avaliação completa: " + aval)
            setUsuarioAva(aval)
            console.log("usaurio aval", aval)
            
            try {
            } catch (error) {
                console.log(error)
            }
            
        })
    },[])


    // function getAvaliacoes() {
    //     let idDoador = sessionStorage.getItem('idUsuarioLogado');
    //     apiProdutos.get(`/avaliacao?idDoador=${idDoador}`).then((resposta) => {

    //         console.log("Fila: " + resposta.data.fila)
    //         aval = resposta.data.fila;

    //         for (let i = 0; i < resposta.data.fila.length; i++) {
    //             apiUsuario.get(`/${resposta.data.fila[i].fkDonatario}`).then((usuarioResposta) => {
    //                 try {
    //                     // console.log(usuarioResposta.data)
    //                     aval[i].nome = usuarioResposta.data.nome;
                
    //                     fetch(`https://viacep.com.br/ws/${usuarioResposta.data.cep}/json/`)
    //                         .then(res => res.json()).then(data => {
    //                             console.log(data)
    //                             aval[i].cidade = data.localidade;
    //                             aval[i].uf = data.uf;
    //                         })
    //                 } catch (error) {
    //                     console.log(error)
    //                 }
    //             })
    //         }
    //         console.log("Avaliação completa: " + aval)
    //         setUsuarioAva(aval)
    //         console.log("usaurio aval", aval)
    //         try {
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }


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
                           
                                nota={ava.valor}
                                comentario={ava.comentario}
                                donatario={ava.nome}
                                cidade={ava.cidade}
                                estado={ava.uf}
                                dataCon={ava.data}

                            />
                        )): ""}


                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default AvaliacaoPessoal;