import { useNavigate } from "react-router-dom";
import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"
import apiProduto from "../apiProduto";
import { useState, useEffect } from "react";


function Produto(props) {

    const navegar = useNavigate();

    var imgProd = [];
    var imgstr;



    apiProduto.get(`${props.idProduto}/imagem-principal`, 
                    {responseType: 'blob'}).then((respostaImg) => {
                        let imgPUrl = URL.createObjectURL(respostaImg.data)
                        console.log("Console oieee",imgPUrl)
                        // setImg(respostaImg.data)
                        // imgProd[0] = respostaImg.data;
                        imgstr=imgPUrl
                        // sessionStorage.setItem(`fotinha${props.idProduto}`, imgstr)
                        console.log(imgProd);
                    }).catch((error) => {
                        console.log(error)
                    })  

    function DescricaoProdutoRedirect() {
        sessionStorage.setItem("idProduto", props.idProduto);
        sessionStorage.setItem("idDoador", props.idDoador);

        apiProduto.patch(`/${props.idProduto}/visualizacao?quantidadeVisualizacao=1`)
            .then((resposta) => {
                console.log(resposta.status)
            }).catch((error) => {
                console.log(error)
            })

        navegar("/descricao-produto");

    }
     


    // setInterval(() => {
    //         document.getElementById("img_foto_produto_p").src = imgstr;
    //         alert(imgstr)
    // }, 1000);


    setTimeout(function carregarImagens() {
        document.getElementById(`img_foto_produto_p${props.idProduto}`).src = imgstr;
        console.log(imgstr)
    }, 500)


    return (
        <div className="container-produto" onClick={DescricaoProdutoRedirect}>
            <div className="informacoes-produto">
                <div className="superior"><img alt="" /><p>{props.nomeDoador}</p> <div class="visualizacao"><img src={vector} alt="" /> <p>{props.visualizacao}</p></div></div>
                <div className="meio" ><img id={`img_foto_produto_p${props.idProduto}`} /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto;
