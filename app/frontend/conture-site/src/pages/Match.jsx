import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuDisponivel"
import '../html-css-template/css/Match.css'
import Footer from "../components/Footer"
import Card from "../components/CardMatch"
import Card2 from "../components/CardMatch"
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"
import apiUsuario from "../apiUsuario.js"
import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';

function Match() {

    const [produtos, setProdutos] = useState([]);
    // const [matchs, setmatchs] = useState([]);
    const [usuario, setUsuario] = useState([]);
    var match = [];
    // useEffect(() => {
    //     let idDoador = sessionStorage.getItem('idUsuarioLogado');
    //     apiProdutos.get(`disponiveis?idDoador=${idDoador}`).then((resposta) => {
    //         try {
    //             console.log("asfdsdfsdsadaaaaasdadaddas",resposta.data)
    //             for (let i = 0; i < resposta.data.length; i++) {
    //                 apiProdutos.get(`${resposta.data[i].idProdutoDoacao}/match?idDoador=${idDoador}`).then((resposta) => {
    //                     try {
    //                         match[i] = resposta.data
    //                         console.log("bbbbbbbbbbbbbbbasfdsdfssdadaddas",resposta.data)
    //                         apiUsuario.get(`${resposta.data[i].fkDonatario}`).then((respostas) => {
    //                             try {
    //                                 match[i].nome = resposta.data.nome
    //                                 match[i].uf = resposta.data.uf
    //                                 match[i].nota = resposta.data.nota
    //                                 console.log("asdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadaddas",respostas.data)
    //                             } catch (error) {
    //                                 console.log("error")    
    //                             }
    //                         })
    //                     } catch (error) {
    //                         console.log(error)
    //                     }
    //                 })
    //             }
    //             setProdutos(match)
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }, [])

    useEffect(() => {
        let idDoador = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`disponiveis?idDoador=${idDoador}`).then((resposta) => {
            try {
                console.log("asfdsdfsdsadaaaaasdadaddas", resposta.data)
                for (let i = 0; i < resposta.data.length; i++) {
                    apiProdutos.get(`${resposta.data[i].idProdutoDoacao}/match?idDoador=${idDoador}`).then((respostas) => {
                            // match.push(respostas.data)

                     
                            match[i] = resposta.data
                    })
                }

                console.log("array match:",produtos)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])


    return (
        <>
            <Header></Header>
            <section className="section-match">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>
                <div className="div_matchs">
                    <div className="text_matchs"><b><p>Matchs</p></b></div>

                    <div className="cards">
                    <Card2
                     foto={fotoLogado}
                     nome={"Flávia"}
                     cidade={"São Paulo"}
                     uf={"SP"}
                     nota={"5.0"}
                     modelo={"Ideapad 3i"}
                     data={"01/12/2022"}
                    ></Card2>
                    {/* {produtos != undefined && produtos.length > 0 ? produtos.map((prod) => (
                            <Card2
                                foto={}
                                // nome={prod.nome}
                                cidade={prod.cidade}
                                // uf={prod.uf}
                                // nota={prod.nota}
                                modelo={prod.modelo}
                                data={prod.data}
                            />
                        )) : ""} */}

                        {produtos != undefined && produtos.length > 0 ? produtos.map((prod) => (
                            <Card
                                foto={prod.imagem}
                                nome={prod.nome}
                                cidade={prod.cidade}
                                // uf={prod.uf}
                                // nota={prod.nota}
                                modelo={prod.modelo}
                                data={prod.data}
                            />
                        )) : ""}

                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default Match;