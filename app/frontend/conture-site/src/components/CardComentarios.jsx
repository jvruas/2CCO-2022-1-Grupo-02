import "../html-css-template/css/DescricaoProduto.css";

function CardComentarios(props){
    return(
    <>
    <div className="card-coment">
        <div className="div-title-coment-description">
            <h2 className="title-coment-description">Pergunte ao doador</h2>
        </div>
        {props.comentarios}
        <div className="div-send-coment-description">
            <div className="button-send-coment-description">
                
            </div>
        </div>
    </div>
    </>
    )
}

export default CardComentarios