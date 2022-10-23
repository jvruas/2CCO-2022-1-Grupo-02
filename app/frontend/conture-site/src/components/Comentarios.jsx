import apiUsuario from "../apiUsuario";
import { useEffect, useState } from "react";
import "../html-css-template/css/DescricaoProduto.css"

function Comentarios(props){

    const [nome, setNome] = useState([]);

    useEffect(() => {
        apiUsuario
                    .get(`/${props.idMensagemPrincipal}`)
                    .then((resposta) => {
                        setNome(resposta.data)
                        console.log("Quem é?",resposta.data)
                    })
    },[]);
    
    const indice = props.index
    const [nomeResposta, setNomeResposta] = useState([]);

    setTimeout(function respostas(){
        const resposta = document.getElementById(`div-respostas${indice}`);
        resposta.innerHTML = " ";
        try {
            for(var i=0; i<props.mensagemResposta.length;i++){
                if(props.mensagemResposta[i]!==undefined){
                    apiUsuario
                    .get(`/${props.mensagemResposta[i].fkUsuario}`)
                    .then((resposta) => {
                        setNomeResposta(resposta.data)
                    });
                    resposta.innerHTML += `<div className="message-answer-description"
                    style="background: #FFFFFF;
                    border: 1px solid #7724BE;
                    border-radius: 10px 10px 10px 0px;
                    padding: 5px;
                    width: 70%;
                    margin-left: auto;
                    margin-right: 4%;
                    margin-top: 2%;
                    padding-left: 4%;"
                    >
                    <b
                    style="font-size: 10px;"
                    >${nomeResposta.nome} ${nomeResposta.sobrenome}:</b>
                    <br>
                    <span style="margin-left: 2%;" >${props.mensagemResposta[i].mensagem}</span></div>`;
                }
            }  
        } catch (error) {
            console.log("tudo certo", error)
        }
         
    },500)

    function definitionMessageReply(){
        const buttonFocus = document.getElementById("button-send")
        buttonFocus.focus() 
        sessionStorage.setItem("fkMensagemPrincipal",props.idMensagemPrincipal)
    }
    
    return(
        <>
        {sessionStorage.setItem("fkMensagemPrincipal",null)}
                <div className="message-main-description">
                <b className="name-coment-description">{nome.nome} {nome.sobrenome}:</b>
                    <br />
                    <span className="message-coment-description">{props.mensagemPrincipal}</span>
                </div>
                <div className="div-reply-coment-description">
                <button className="button-reply-coment-description" onClick={definitionMessageReply}>Responder</button>
                </div>
                
                
                <div id={`div-respostas${indice}`} ></div>
        </>
    )
}

export default Comentarios