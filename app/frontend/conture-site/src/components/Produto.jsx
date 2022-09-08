import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash 2.svg"
import vector from "../html-css-template/imagens/Vector.svg"

function Produto(props) {
    return (
        <div class="container-produto">
            <div class="informacoes-produto">
                <div class="superior">
                    <img src={props.imagem}/>
                    <p>{props.nomeDoador}</p>
                        <div class="visualizacao">
                            <img src={vector} alt="" /> 
                            <p>{props.visualizacao}</p>
                        </div>
                </div>
                <div class="meio">
                    <img src="#" alt="" />
                </div>
                <div class="inferior">
                    <p>{props.nome}</p>
                </div>
            </div>
        </div>
    );
}

export default Produto