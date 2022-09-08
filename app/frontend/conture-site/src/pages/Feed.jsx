import Carousel from "../components/Carousel";
import Header from "../components/Header"
import Footer from "../components/Footer"
import Produto from "../components/Produto";
import Anuncio from "../components/Anuncio";
import '../html-css-template/css/Style.css'
import { useEffect, useState } from "react";
import apiProduto from "../apiProduto.js";
import apiUsuario from "../apiUsuario";

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
            apiProduto.get("/categoria?idCategoria=1").then((resposta) => {
                for(let i=0; i<resposta.data.length; i++){
                    apiUsuario.get(`/${resposta.data[i].fkDoador}`).then((response) => {
                        resposta.data[i].nomeDoador=response.data.nome
                    })
                    apiUsuario.get(`/${resposta.data[i].fkDoador}/imagem?tipoImagem=P
                    `).then((response) => {
                        resposta.data[i].imagem="data:image/png;base64,"+response.data
                    })
                }
                setProdutosNote(resposta.data);
                console.log("resposta.data");
                console.log(resposta.data);
            })

            apiProduto.get("/categoria?idCategoria=2").then((resposta) => {
                for(let i=0; i<resposta.data.length; i++){
                    apiUsuario.get(`/${resposta.data[i].fkDoador}`).then((response) => {
                        resposta.data[i].nomeDoador=response.data.nome
                    })
                    apiUsuario.get(`/${resposta.data[i].fkDoador}/imagem?tipoImagem=P
                    `).then((response) => {
                        resposta.data[i].imagem="data:image/png;base64,"+response.data
                    })
                }
                setProdutosTablet(resposta.data);
                console.log(resposta.data);
            })
       
            apiProduto.get("/categoria?idCategoria=3").then((resposta) => {
                for(let i=0; i<resposta.data.length; i++){
                    apiUsuario.get(`/${resposta.data[i].fkDoador}`).then((response) => {
                        resposta.data[i].nomeDoador=response.data.nome
                    })
                    apiUsuario.get(`/${resposta.data[i].fkDoador}/imagem?tipoImagem=P
                    `).then((response) => {
                        resposta.data[i].imagem="data:image/png;base64,"+response.data
                    })
                }
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
                            nomeDoador={itemProduto.nomeDoador}
                            imagem={itemProduto.imagem}
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
                            nomeDoador={itemProduto.nomeDoador}
                            imagem={itemProduto.imagem}
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
                            nomeDoador={itemProduto.nomeDoador}
                            imagem={itemProduto.imagem}
                        />
                    ))
                }
            />

            <Footer></Footer>

        </>


    )
}

export default Feed;