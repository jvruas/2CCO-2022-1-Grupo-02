import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash 2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/Rectangle 44.svg"
import { Link, useNavigate } from "react-router-dom";

function Produto(props) {


    let produto = { idProduto: props.idProduto, nome: props.nome }
    // Transformar o objeto em string e salvar em localStorage
    sessionStorage.setItem('produto', JSON.stringify(produto));
    // Receber a string
    let pessoaString = sessionStorage.getItem('produto');
    // transformar em objeto novamente
    let produtoObj = JSON.parse(pessoaString);
    console.log(produtoObj.idProduto);

    return (
        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><Link to='/popup-deletar'><img src={trash} alt="" /></Link> <div class="visualizacao"><img src={vector} alt="" /><p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={foto} alt="" /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto
