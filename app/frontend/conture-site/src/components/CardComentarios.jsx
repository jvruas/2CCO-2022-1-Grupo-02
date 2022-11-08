import apiMensagemGrupo from "../apiMensagemGrupo";
import "../html-css-template/css/DescricaoProduto.css";
import send from "../html-css-template/imagens/send.svg"

function CardComentarios(props){

    function sendQuestion(){
        const message = document.getElementById("button-send")

        let json = {
            mensagem: message.value,
            data: "2022-10-19T04:01:47.042Z",
            fkUsuario: sessionStorage.getItem("idUsuarioLogado"),
            fkProdutoDoacao: sessionStorage.getItem("idProduto"),
            fkMensagemPrincipal: sessionStorage.getItem("fkMensagemPrincipal")
        }

        console.log("SEND-MESSAGE",json)

        apiMensagemGrupo.post("", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        })

        window.location.reload(true);
    }


    return(
    <>
    <div className="card-coment">
        <div className="div-title-coment-description">
            <h2 className="title-coment-description">Pergunte ao doador</h2>
        </div>
        <div className="div-coments-description">
            {props.comentarios}
        </div>
        <div className="div-send-coment-description">
        <input id="button-send" className="input-send-coment-description"/>
        <button className="button-send-coment-description" onClick={sendQuestion}>
            <img src={send} alt="" />
        </button>
        </div>
        
    </div>
    </>
    )
}

export default CardComentarios