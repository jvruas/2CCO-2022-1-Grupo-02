import MensagemUsuario from "./MensagemUsuario";
import iconSend from "../../html-css-template/imagens/icon-send.svg"
import { useState, useEffect } from "react";

function ListarMensagens(){

    let idUsuarioLogado = sessionStorage.getItem('idUsuarioLogado');

    const [teste, setTeste] = useState([]);


    useEffect(() => {
        var listTeste=[]
        for(var i = 0; i<parseInt(sessionStorage.getItem(`tamanhoMensa${idUsuarioLogado}`)); i++){
            listTeste.push(sessionStorage.getItem(`mensa${idUsuarioLogado}.${i}`))
        }

        setTimeout(function carregarImagens() {
            setTeste(listTeste)
            console.log("sima",teste)
        }, 500)
    },[]);

    

    return(
<>
        <div id="md-mensagens" className="scroll">
            {teste != undefined && teste.length > 0 ? teste.map((itemMensagem) =>
            <MensagemUsuario
                mensagem={itemMensagem}
            >

            </MensagemUsuario>
            ):""}

        </div>

<div id="md-enviar-mensagem">
<input type="text" id="input-mensagem" />
<button>
    <img src={iconSend} alt="Ícone de enviar mensagem" />
</button>
</div>
</>
    );
}


export default ListarMensagens