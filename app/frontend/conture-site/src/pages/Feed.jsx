import '../html-css-template/css/Style.css'
import '../html-css-template/css/Feed.css'
import gifConstrucao from "../html-css-template/imagens/construcao.gif"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api.js";

function Feed() {

    const navegar = useNavigate();

    function handleSubmit(event) {
        event.preventDefault()
        let param = sessionStorage.getItem('idUsuarioLogado')
        api.delete(`/${param}/login`, {
        }).then((resposta) => {
            console.log(resposta.status)
            navegar("/")
        }).catch((error) => {
            console.log(error)
        })
    }

    return (
        <>
            <section className="section-feed">
                <img src={gifConstrucao} id="gif-contrucao" className="gif-construcao" alt="Gif de dois personagens desenvolvendo site" />
                <h1>SITE EM DESENVOLVIMENTO</h1>
                <button type="button" onClick={handleSubmit}>Retornar para página de login</button>
            </section>
        </>
    )
}

export default Feed;