import "../html-css-template/css/DescricaoProduto.css"

function Comentarios(props){
    function respostas(){
        console.log("esntrou")
        const resposta = document.getElementById("div-respostas");
        // resposta.innerHTML = " a";
        console.log("aaa",props.mensagemResposta)
        for(var j=0; j<props.mensagemResposta.length;j++){
            if(props.mensagemResposta[j]!=undefined){
                for(var i=0; i<props.mensagemResposta[j].length;i++){
                    resposta.innerHTML += `${props.mensagemResposta[i].mensagem}<br>`;
                }
            }
        }   
    }

    return(
        <>
        {console.log("componete")}
                <h5 onLoad={respostas()}>{props.mensagemPrincipal}</h5>
                <div id="div-respostas"></div>
        </>
    )
}

export default Comentarios