import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"
import { useNavigate } from "react-router-dom";
import apiProduto from "../apiProduto";

function Produto(props) {

    const navegar = useNavigate();

    function DescricaoProdutoRedirect(){
        sessionStorage.setItem("idProduto",props.idProduto);
        sessionStorage.setItem("idDoador",props.idDoador);
        navegar("/descricao-produto");
    }

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
        document.getElementById(`img_foto_produto_d${props.idProduto}`).src = imgstr;
        console.log(imgstr)
    }, 500)
 

    return(
        <div className="container-produto" onClick={DescricaoProdutoRedirect}>
            <div className="informacoes-produto">
                <div className="superior"><img alt=""/> <div class="visualizacao"><p>{props.visualizacao}</p><img src={vector} alt="" /></div></div>
                <div className="meio"><img id={`img_foto_produto_d${props.idProduto}`} /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}
export default Produto
