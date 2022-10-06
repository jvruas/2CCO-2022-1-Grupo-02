import Header from "../components/Header"
import Footer from "../components/Footer"
import "../html-css-template/css/DescricaoProduto.css"
import CarouselProdutos from "../components/CarouselProdutos"
import Comentarios from "../components/Comentarios"
import apiProdutos from "../apiProduto"
import apiUsuario from "../apiUsuario"
import { useEffect, useState } from "react";

function DescricaoProduto(){
    const [produto, setProduto] = useState([]);
    const [usuario, setUsuario] = useState([]);

    useEffect(() => {
        apiProdutos.get(`/produtos/${sessionStorage.getItem("idProduto")}`).then((resposta) => {
            setProduto(resposta.data)
        })

        apiUsuario.get(`/usuarios/${sessionStorage.getItem("idUsuario")}`).then((resposta) => {
            setUsuario(resposta.data)
        })
    })


    return(
        <>
            <Header></Header>
            <div class="container div-crumbs">
                <span class="span-crumbs-dad">
                    <span class="span-crumbs">Produtos</span>
                    <span class="span-crumbs">></span>
                    <span class="span-crumbs">Notebook</span> 
                    <span class="span-crumbs">></span>
                    <span class="span-crumbs"><b>Nome notebook</b></span>
                </span>
            </div>

            
            <CarouselProdutos qtdItens={1}></CarouselProdutos>
            
            <Comentarios></Comentarios>

            <div class="container product-title">
                <span class="span-product-title">
                   <b> Nome Notebook - ahhsahs</b>
                </span>
            </div>
            
            <div className="container more-info-description-product">
                <div className="block-left">
                    <div className="card-description">
                        <div className="title-description">
                            <b>Descrição</b>
                        </div>
                    </div>
                    <div className="button-i"> 
                    </div>
                </div>
                <div className="card-info-user">
                    <div className="div-name-user">
                        <div className="infos-user">
                            <div className="photo-user">
                            
                            </div>
                            <b className="name-user">Patrícia</b>
                        </div>
                    </div>
                    <div className="div-location-user">

                    </div>
                    <div className="div-numbers-user">

                    </div>
                </div>
            </div>
        
            <Footer></Footer>
        </>
    )
}

export default DescricaoProduto;