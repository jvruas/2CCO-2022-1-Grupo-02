import "../html-css-template/css/DescricaoProduto.css"

function Comentarios(props){
    setTimeout(function respostas(){
        console.log("esntrou")
        const resposta = document.getElementById(`div-respostas${props.index}`);
        resposta.innerHTML = " ";
        try {
            for(var i=0; i<props.mensagemResposta.length;i++){
                if(props.mensagemResposta[i]!==undefined){
                    resposta.innerHTML += `<div className="message-answer-description"
                    style="background: #FFFFFF;
                    border: 1px solid #7724BE;
                    border-radius: 10px 10px 10px 0px;
                    padding: 5px;
                    width: 70%;
                    margin-left: auto;
                    margin-right: 4%;
                    margin-top: 2%;"
                    >${props.mensagemResposta[i].mensagem}</div>`;
                }
            }  
        } catch (error) {
            console.log("tudo certo", error)
        }
         
    },100)

    function definitionMessageReply(){
        sessionStorage.setItem("fkMensagemPrincipal",props.idMensagemPrincipal)
    }
    
    return(
        <>
        {sessionStorage.setItem("fkMensagemPrincipal",null)}
                <div className="message-main-description">
                    {props.mensagemPrincipal}
                </div>
                <div className="div-reply-coment-description">
                <button className="button-reply-coment-description" onClick={definitionMessageReply}>Responder</button>
                </div>
                
                
                <div id={`div-respostas${props.index}`} ></div>
        </>
    )
}

export default Comentarios