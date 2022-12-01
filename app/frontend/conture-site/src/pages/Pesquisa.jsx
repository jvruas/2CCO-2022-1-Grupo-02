import Header from "../components/Header"
import Footer from "../components/Footer"

import Produto from "../components/Produto";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Pesquisa.css'
import { useEffect, useState } from "react";
import apiProduto from "../apiProduto.js";
import apiUsuario from "../apiUsuario";
import { useNavigate } from "react-router-dom";

function Pesquisa() {

    const navegar = useNavigate();
    const [Produtos, setProdutos] = useState([]);
    let busca = sessionStorage.getItem('nomeBuscado');
    useEffect
        (() => {
            let pesquisa = sessionStorage.getItem('pesquisa');
            apiProduto.get(`/nome?nome=${pesquisa}`).then((resposta) => {
                try {
                    console.log(resposta.data)
                    setProdutos(resposta.data)
                } catch (error) {
                    console.log(error)
                }
            })

            let categoria = sessionStorage.getItem('tipoCategoria');
            apiProduto.get(`/categoria?idCategoria=${categoria}`).then((resposta) => {
                try {
                    console.log(resposta.data)
                    navegar("/pesquisa")
                    setProdutos(resposta.data)

                } catch (error) {
                    console.log(error)
                }
            })
        }, []
        )


    return (
        <>
            <Header></Header>
            <section id="section-pesquisa">
            <div className="conteiner-pesquisa">
                <div className="div_sup_pesq"><b><p>Resultados para {busca}</p></b></div>
                    <div className="div_inf_pesq">
                        <div className="div_card">
                            {Produtos != undefined && Produtos.length > 0 ? Produtos.map((prod) => (
                                <Produto
                                    visualizacao={prod.quantidadeVisualizacao}
                                    nome={prod.nome}
                                    idProduto={prod.idProduto}
                                />
                            )) : ""}


                        </div>
                    </div>
                </div>
            </section>
            <Footer></Footer>

        </>


    )
}

export default Pesquisa;