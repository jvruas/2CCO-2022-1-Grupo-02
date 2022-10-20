import { useNavigate } from "react-router-dom";
import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"


function Produto(props) {

    const navegar = useNavigate();

    function DescricaoProdutoRedirect(idProduto, idDoador){
        sessionStorage.setItem("idProduto",idProduto);
        sessionStorage.setItem("idDoador",idDoador);
        navegar("/descricao-produto");
    }
    
    return(
        <div className="container-produto" 
        // onClick={DescricaoProdutoRedirect(props.idProduto, props.idDoador)}
        >
            <div className="informacoes-produto"
            >
                <div className="superior"><img alt=""/><p>{props.nomeDoador}</p> <div class="visualizacao"><img src={vector} alt=""/> <p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={foto} alt=""/></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto
