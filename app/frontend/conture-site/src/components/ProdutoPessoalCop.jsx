import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash2.svg"
import vector from "../html-css-template/imagens/vector.svg"
import foto from "../html-css-template/imagens/ft-card.png"

import { Link, useNavigate } from "react-router-dom";

function Produto(props) {

    const navegar = useNavigate();
    function DeletarRedirect(){
        sessionStorage.setItem("idProduto",props.idProduto);
        sessionStorage.setItem("idDoador",props.idDoador);
        navegar("/popup-deletar");
    }



    return (
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><Link to='/popup-deletar'><img src={trash} alt="" onClick={DeletarRedirect} /></Link> <div class="visualizacao"><img src={vector} alt="" /><p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={foto} alt="" /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto
