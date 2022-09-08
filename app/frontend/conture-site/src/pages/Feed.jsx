import Carousel from "../components/Carousel";
import Header from "../components/Header"
import Footer from "../components/Footer"
import Produto from "../components/Produto";
import Anuncio from "../components/Anuncio";
import '../html-css-template/css/Style.css'
import { useState, useEffect } from "react";
import apiProduto from '../apiProduto';

function Feed() {

    // const [produtos, setProdutos] = useState([]);
    const [categorias, setCategoria] = useState([]);

    // useEffect(() => {
    //     apiProduto.get("/nome?nome=Notebook").then((resposta) => {
    //         setProdutos(resposta.data);
    //         console.log(resposta.data);
    //     })
    // }, [])

    useEffect(() => {
        apiProduto.get("/todas-categorias").then((resposta) => {
            try {
                console.log(resposta.data)
                setCategoria(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    return (
        <>
            <Header></Header>

            <Anuncio></Anuncio>

            <h2 className="title-feed">Notebook</h2>

            <Carousel
                card1={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card2={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card3={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card4={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card5={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card6={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card7={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card8={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card9={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card10={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
            />

            <h2 className="title-feed">Celular</h2>

            <Carousel
                card1={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card2={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card3={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card4={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card5={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card6={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card7={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card8={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card9={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card10={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
            />


            <h2 className="title-feed">Tablet</h2>

            <Carousel
                card1={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card2={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card3={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card4={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card5={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card6={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card7={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card8={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card9={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
                card10={<Produto
                    visualizacao="427"
                    texto="Notebook Lenovo Ideapad A120"
                ></Produto>}
            />

            <Footer></Footer>

        </>


    )
}

export default Feed;