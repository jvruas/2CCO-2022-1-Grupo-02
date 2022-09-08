import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash 2.svg"
import vector from "../html-css-template/imagens/Vector.svg"

function Produto(props) {
    return(
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img src={trash} alt=""/> <div class="visualizacao"><img src={vector} alt=""/> <p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={props.foto} alt=""/></div>
                <div className="inferior"><p>{props.texto}</p></div>
            </div>
        </div>
    );
}

export default Produto
