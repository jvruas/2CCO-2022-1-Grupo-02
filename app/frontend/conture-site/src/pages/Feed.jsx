lines (85 sloc)  3.03 KB

import Carousel from "../components/Carousel";
import Header from "../components/Header"
import Footer from "../components/Footer"
import Produto from "../components/Produto";
import Anuncio from "../components/Anuncio";
import '../html-css-template/css/Style.css'
import { useEffect, useState } from "react";
import apiProduto from "../apiProduto.js";

function Feed() {

    
    // const [categorias, setCategoria] = useState([]);

    // useEffect(() => {
    //     apiProduto.get("/todas-categorias").then((resposta) => {
    //         try {
    //             console.log(resposta.data)
    //             setCategoria(resposta.data)
    //             console.log(resposta.data[0].nome)
    //             console.log(resposta.data[1].nome)
    //             console.log(resposta.data[2].nome)

    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }, [])

    const [produtosNote, setProdutosNote] = useState([]);
    const [produtosCelular, setProdutosCelular] = useState([]);
    const [produtosTablet, setProdutosTablet] = useState([]);
    useEffect(() => {
            apiProduto.get("/nome?nome=Notebook").then((resposta) => {
                setProdutosNote(resposta.data);
                console.log(resposta.data);
            })

            apiProduto.get("/nome?nome=Tablet").then((resposta) => {
                setProdutosTablet(resposta.data);
                console.log(resposta.data);
            })
       
            apiProduto.get("/nome?nome=Celular").then((resposta) => {
                setProdutosCelular(resposta.data);
                console.log(resposta.data);
            })
    }, [])


    return (
        <>
            <Header></Header>
            <Anuncio></Anuncio>

            <h2 className="title-feed">Notebook</h2>
            <Carousel
                card1=
                {
                    produtosNote.map((itemProduto) => (
                        <Produto
                            visualizacao={itemProduto.quantidadeVisualizacao}
                            nome={itemProduto.nome}
                        />
                    ))
                }
            />

            <h2 className="title-feed">Celular</h2>
            <Carousel
                card1=
                {
                    produtosCelular.map((itemProduto) => (
                        <Produto
                            visualizacao={itemProduto.quantidadeVisualizacao}
                            nome={itemProduto.nome}
                        />
                    ))
                }
            />


            <h2 className="title-feed">Tablet</h2>
            <Carousel
                card1=
                {
                    produtosTablet.map((itemProduto) => (
                        <Produto
                            visualizacao={itemProduto.quantidadeVisualizacao}
                            nome={itemProduto.nome}
                        />
                    ))
                }
            />

            <Footer></Footer>

        </>


    )
}

export default Feed;