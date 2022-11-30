import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"
import { useNavigate } from "react-router-dom";

function Produto(props) {

    const navegar = useNavigate();

    function DescricaoProdutoRedirect(){
        sessionStorage.setItem("idProduto",props.idProduto);
        sessionStorage.setItem("idDoador",props.idDoador);
        navegar("/descricao-produto");
    }

    return(
        <div className="container-produto" onClick={DescricaoProdutoRedirect}>
            <div className="informacoes-produto">
                <div className="superior"><img alt=""/> <div class="visualizacao"><p>{props.visualizacao}</p><img src={vector} alt="" /></div></div>
                <div className="meio"><img src={props.foto} alt=""/></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}
export default Produto
