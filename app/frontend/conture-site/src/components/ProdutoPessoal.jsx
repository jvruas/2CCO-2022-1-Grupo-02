import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/vector.svg"
import { Link, useNavigate } from "react-router-dom";

function Produto(props) {

    function excluirProduto(){
        sessionStorage.setItem("idProduto",props.idProduto);
        sessionStorage.setItem("idDoador",props.idDoador);
        navegar("/popup-deletar");
    }

    return(
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img src={trash} onClick={excluirProduto} alt=""/><div class="visualizacao"><img src={vector} alt=""/> <p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={props.foto} alt=""/></div>
                <div className="inferior"><p>{props.texto}</p></div>
            </div>
        </div>
    );
}

export default Produto
