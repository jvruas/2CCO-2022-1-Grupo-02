import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash 2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/Rectangle 44.svg"

function Produto(props) {


    return(
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img alt=""/> <div class="visualizacao"><img src={vector} alt=""/> <p>250</p></div></div>
                <div className="meio"><img src={foto} alt=""/></div>
                <div className="inferior"><p>Notebook Lenovo S145</p></div>
            </div>
        </div>
    );
}

export default Produto
