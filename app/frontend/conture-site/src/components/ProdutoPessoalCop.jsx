import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"
import apiProduto from "../apiProduto"

import { Link, useNavigate } from "react-router-dom";

function Produto(props) {

    const navegar = useNavigate();
    function DeletarRedirect(){
        sessionStorage.setItem("idProdutoDoacao", props.produto);
        sessionStorage.setItem("fkDoador",props.fkDoador);
        navegar("/popup-deletar");
    }

    console.log("idcorreto",props.idProduto)

    var imgstr;



    apiProduto.get(`${props.idProduto}/imagem-principal`, 
                    {responseType: 'blob'}).then((respostaImg) => {
                        let imgPUrl = URL.createObjectURL(respostaImg.data)
                        console.log("Console oieee",imgPUrl)
                        // setImg(respostaImg.data)
                        // imgProd[0] = respostaImg.data;
                        imgstr=imgPUrl
                        // sessionStorage.setItem(`fotinha${props.idProduto}`, imgstr)
                        // console.log(imgProd);
                    }).catch((error) => {
                        console.log(error)
    })
    
    setTimeout(function carregarImagens() {
        document.getElementById(`img_foto_produto_dp${props.idProduto}`).src = imgstr;
        console.log(imgstr)
    }, 500)

    return (
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img src={trash} alt="" onClick={DeletarRedirect} /> <div class="visualizacao"><img src={vector} alt="" /><p>{props.visualizacao}</p></div></div>
                <div className="meio"><img id={`img_foto_produto_dp${props.idProduto}`} src={props.foto} alt="" /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto
