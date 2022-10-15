import estrela from '../html-css-template/imagens/Star 3.svg';
import '../html-css-template/css/Circulo.css'
import apiProdutos from '../apiProduto';
import { useState, useEffect } from "react";


function Nota() {

    const [avaliacao, setAvaliacao] = useState([]);


    // useEffect
    // (() => {
    //     let idUsuario = sessionStorage.getItem('idUsuarioLogado');
    //     apiProdutos.get(`avaliacao?idDoador=${idUsuario}`).then((resposta) => {
    //         try {
    //             console.log(resposta.data)
    //             setAvaliacao(resposta.data)
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }, [])

    return (
        <>
            <body>
            <div id="circulo">
        <div class="circulo_informacoes">
            <p id="nota">{avaliacao.valor}</p>
            <div class="estrelas_maior">
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
            </div>
            <p id="avaliacoes">3 Avaliações no total</p>
        </div>
    </div>
            </body>
        </>
    );
}

export default Nota;