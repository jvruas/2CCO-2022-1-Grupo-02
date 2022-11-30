import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"

import { Link, useNavigate } from "react-router-dom";

function Produto(props) {

    const navegar = useNavigate();
    function DeletarRedirect(){
        sessionStorage.setItem("idProdutoDoacao", props.produto);
        sessionStorage.setItem("fkDoador",props.fkDoador);
        navegar("/popup-deletar");
    }

    return (
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img src={trash} alt="" onClick={DeletarRedirect} /> <div class="visualizacao"><img src={vector} alt="" /><p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={foto} alt="" /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto
