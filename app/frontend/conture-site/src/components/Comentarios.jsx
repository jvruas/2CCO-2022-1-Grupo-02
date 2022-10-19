import "../html-css-template/css/DescricaoProduto.css"

function Comentarios(props){
    setTimeout(function respostas(){
        console.log("esntrou")
        const resposta = document.getElementById(`div-respostas${props.index}`);
        resposta.innerHTML = " ";
        try {
            for(var i=0; i<props.mensagemResposta.length;i++){
                if(props.mensagemResposta[i]!==undefined){
                    resposta.innerHTML += `${props.mensagemResposta[i].mensagem}<br>`;
                }
            }  
        } catch (error) {
            console.log("tudo certo", error)
        }
         
    },100)
    
    return(
        <>
                <h5 
                >{props.mensagemPrincipal}</h5>
                <div id={`div-respostas${props.index}`}></div>
        </>
    )
}

export default Comentarios