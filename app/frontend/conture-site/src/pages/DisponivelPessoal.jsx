import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuDisponivelPessoal"
import '../html-css-template/css/ProdutoDisponivelPage.css'
import Footer from "../components/Footer"
import Card from "../components/ProdutoPessoalCop"
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"


import { useParams } from "react-router-dom";

function DisponivelPessoal() {
    const params = useParams();

    const [produtos, setProdutos] = useState([]);
    const [imgProd, setImg] = useState([]);
    // const imgTratada = `url(data:image;base64,${imgProd})`
    var produtosImg = [];
    // const [usuarioImg, setUsuarioImg] = useState([]);

    useEffect
        (() => {
            let idUsuario = sessionStorage.getItem('idUsuarioLogado');
            apiProdutos.get(`disponiveis?idDoador=${idUsuario}`).then((resposta) => {
                try {
                    console.log("RESPOSTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                    console.log(resposta.data)
                    produtosImg = resposta.data
                    for (let i = 0; i < resposta.data.length; i++) {
                        // apiProdutos.get(`${produtos.idProdutoDoacao}/imagem-principal`,
                        // { responseType: 'blob' }).then((respostaImg) => {
                        //         let imgUrl = URL.createObjectURL(respostaImg.data)
                        //         produtosImg[i].image = imgUrl    
                        //         console.log("testetetete",resposta.data.image)                     
                        //     }).catch((error) => {
                        //             // console.log(error)
                        //         })
                        console.log("ahhhhhhhhhhhhhhhhhhhhhhh",resposta.data[i].idProdutoDoacao)
                        apiProdutos.get(`${resposta.data[i].idProdutoDoacao}/imagem-principal`,{ responseType: 'blob' }).then((respostaImg) => {
                            let imgUrl = URL.createObjectURL(respostaImg.data)
                            produtosImg[i].imagem = imgUrl;
                            console.log("fdssssssssssssssssdfsdfsf",produtosImg)
                            // setImg(imgUrl.data[i])
                            console.log("teydfkjsdjsk",respostaImg.data[i]) 
                        
                        }).catch((error) => {
                            console.log("error")
                            console.log("error")
                            console.log("error")
                            console.log("error")
                            console.log("error")
                            console.log(error)
                        })
                    }
                    console.log("fiudsdsfnidsfnfdsjdsjjdsfk",imgProd)
                    setProdutos(produtosImg)
                    console.log(produtosImg)
                } catch (error) {
                    console.log(error)
                }
            })
        }, [])

    useEffect(() => {
        apiProdutos.get(`${produtos.idProdutoDoacao}/imagem-principal`,
            { responseType: 'blob' }).then((respostaImg) => {
                let imgUrl = URL.createObjectURL(respostaImg.data)
                document.getElementById("img_foto").src = imgUrl;
            }).catch((error) => {
                // console.log(error)
            })
    })

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
                                    foto={produtosImg.imagem}
                                    fkDoador={prod.fkDoador}
                                    produto={prod.idProdutoDoacao}
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

export default DisponivelPessoal;