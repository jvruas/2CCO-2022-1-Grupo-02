import '../html-css-template/css/Style.css'
import '../html-css-template/css/Feed.css'
import gifConstrucao from "../html-css-template/imagens/construcao.gif"
import { useState } from "react";
import { Link } from "react-router-dom";
import api from "../api.js";

function Feed(){
    return(
        <>
            <section className="section-feed">
            <img src={gifConstrucao} id="gif-contrucao" className="gif-construcao" alt="Gif de dois personagens desenvolvendo site"/>
            <h1>SITE EM DESENVOLVIMENTO</h1>
            <Link class="link-feed" to="/login"><button type="button">Retornar para página de login</button></Link>
            </section>
        </>
    )
}

export default Feed;