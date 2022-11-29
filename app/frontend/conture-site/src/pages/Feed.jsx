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


    function pegarImagemPro(idProduto){
        apiProduto.get(`${idProduto}/imagem-principal`, 
        {responseType: 'blob'}).then((respostaImg) => {
            let imgUrl = URL.createObjectURL(respostaImg.data)
            return imgUrl;
        }).catch((error) => {
            console.log(error)
        })
    }
        
    
    const [produtosNote, setProdutosNote] = useState([]);
    const [produtosCelular, setProdutosCelular] = useState([]);
    const [produtosTablet, setProdutosTablet] = useState([]);
    const [imgProduto, setImg] = useState([]);

    var imgProd = [];
    useEffect(() => {
            apiProduto.get("/categoria?idCategoria=7").then((resposta) => {
                console.log("cat1", resposta.data);
                for(let i=0; i<resposta.data.length; i++){
                    apiUsuario.get(`/${resposta.data[i].fkDoador}`).then((response) => {
                        resposta.data[i].nomeDoador=response.data.nome
                        setProdutosNote(resposta.data);
                    })
                    apiProduto.get(`${resposta.data[i].idProdutoDoacao}/imagem-principal`, 
                    {responseType: 'blob'}).then((respostaImg) => {
                        let imgUrl = URL.createObjectURL(respostaImg.data)
                        // console.log("Console oieee",respostaImg.data)
                        // setImg(respostaImg.data)
                        imgProd[i] = respostaImg.data;
                        console.log(imgProd);
                    }).catch((error) => {
                        console.log(error)
                    })    
                    
                }
                console.log("resposta.data");
                console.log(resposta.data);
            })

            apiProduto.get("/categoria?idCategoria=8").then((resposta) => {
                console.log("cat2", resposta.data);
                for(let i=0; i<resposta.data.length; i++){
                    apiUsuario.get(`/${resposta.data[i].fkDoador}`).then((response) => {
                        resposta.data[i].nomeDoador=response.data.nome
                        setProdutosTablet(resposta.data);
                    })
                    // apiUsuario.get(`/${resposta.data[i].fkDoador}/imagem?tipoImagem=P
                    // `).then((response) => {
                    //     resposta.data[i].imagem="data:image/png;base64,"+response.data
                    // })
                }
                console.log(resposta.data);
            })
       
            apiProduto.get("/categoria?idCategoria=1").then((resposta) => {
                console.log("cat3", resposta.data);
                for(let i=0; i<resposta.data.length; i++){
                    apiUsuario.get(`/${resposta.data[i].fkDoador}`).then((response) => {
                        resposta.data[i].nomeDoador=response.data.nome
                        setProdutosCelular(resposta.data);
                    })
                    // apiUsuario.get(`/${resposta.data[i].fkDoador}/imagem?tipoImagem=P
                    // `).then((response) => {
                    //     resposta.data[i].imagem="data:image/png;base64,"+response.data
                    // })
                }
                console.log(resposta.data);
            })
    }, [])


    return (
        <>
            <Header></Header>
            <Anuncio></Anuncio>

            <h2 className="title-feed">Notebook</h2>
            <Carousel
                qtdItens={4}
                card1=
                {
                    produtosNote.map((itemProduto) => (
                        <Produto
                            idProduto={itemProduto.idProdutoDoacao}
                            idDoador={itemProduto.fkDoador}
                            visualizacao={itemProduto.quantidadeVisualizacao}
                            nome={itemProduto.nome}
                            nomeDoador={itemProduto.nomeDoador}
                            foto={imgProduto.imagem}
                        />
                    ))
                }
            />

            <h2 className="title-feed">Celular</h2>
            <Carousel
                qtdItens={4}
                card1=
                {
                    produtosCelular.map((itemProduto) => (
                        <Produto
                        idProduto={itemProduto.idProdutoDoacao}
                        idDoador={itemProduto.fkDoador}
                        visualizacao={itemProduto.quantidadeVisualizacao}
                        nome={itemProduto.nome}
                        nomeDoador={itemProduto.nomeDoador}
                        // imagem={itemProduto.imagem}
                        />
                    ))
                }
            />


            <h2 className="title-feed">Tablet</h2>
            <Carousel
                qtdItens={4}
                card1=
                {
                    produtosTablet.map((itemProduto) => (
                        <Produto
                        idProduto={itemProduto.idProdutoDoacao}
                        idDoador={itemProduto.fkDoador}
                        visualizacao={itemProduto.quantidadeVisualizacao}
                        nome={itemProduto.nome}
                        nomeDoador={itemProduto.nomeDoador}
                        // imagem={itemProduto.imagem}
                        />
                    ))
                }
            />

            <Footer></Footer>

        </>


    )
}

export default Feed;