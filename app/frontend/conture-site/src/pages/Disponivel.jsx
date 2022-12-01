import Header from "../components/Header"
import Perfil from "../components/PerfilSimples"
import MenuPerfil from "../components/MenuDisponivel"
import '../html-css-template/css/ProdutoDisponivelPage.css'
import Footer from "../components/Footer"
import Card from "../components/ProdutoCop"
import Filtro from "../html-css-template/imagens/filtro.svg";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"

function Disponivel() {

    const [produtos, setProdutos] = useState([]);
    useEffect(() => {
        let idDoador = sessionStorage.getItem('idDoador');
        apiProdutos.get(`disponiveis?idDoador=${idDoador}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setProdutos(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    return (
        <>
            <Header></Header>
            <section id="disp-section">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>

                <div className="conteiner-produto">
                    <div className="div_sup_disp"><b><p>Disponíveis</p></b></div>
                    <div className="div_inf_disp">
                        <div className="div_card">
                            {produtos != undefined && produtos.length > 0 ? produtos.map((prod) => (
                                <Card
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

export default Disponivel;