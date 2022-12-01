import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuDisponivel"
import '../html-css-template/css/Match.css'
import Footer from "../components/Footer"
import Card from "../components/CardMatch"
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"
import apiUsuario from "../apiUsuario.js"

function Match() {
 
    const [produtos, setProdutos] = useState([]);
    // const [matchs, setmatchs] = useState([]);
    const [usuario, setUsuario] = useState([]);
    var match= [];
    useEffect(() => {
        let idDoador = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`disponiveis?idDoador=${idDoador}`).then((resposta) => {
            try {
                
                console.log("asfdsdfsdsadaaaaasdadaddas",resposta.data)
                for (let i = 0; i < resposta.data.length; i++) {
                    apiProdutos.get(`${resposta.data[i].idProdutoDoacao}/match?idDoador=${idDoador}`).then((resposta) => {
                        try {
                            match[i] = resposta.data
                            console.log("bbbbbbbbbbbbbbbasfdsdfssdadaddas",resposta.data)
                            apiUsuario.get(`${resposta.data[i].fkDonatario}`).then((respostas) => {
                                try {
                                    match[i].nome = resposta.data[i].nome
                                    match[i].uf = resposta.data[i].uf
                                    match[i].nota = resposta.data[i].nota
                                    console.log("asdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadaddas",respostas.data)
                                } catch (error) {
                                    console.log("error")    
                                }
                            })
                        } catch (error) {
                            console.log(error)
                        }
                    })
                }
               
                setProdutos(match)
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

                {produtos != undefined && produtos.length > 0 ?  produtos.map((prod)=>(
                            <Card 
                            foto={prod.imagem}
                            nome={prod.nome}
                            cidade={prod.cidade}
                            uf={prod.uf}
                            nota={prod.nota}
                            modelo={prod.modelo}
                            data={prod.data}
                             />
                            )): ""}
                   
                    
                </div>
                </div>
          
            </section>
            <Footer></Footer>
        </>
    )
}

export default Match;