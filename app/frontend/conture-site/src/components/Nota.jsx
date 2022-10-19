import estrela from '../html-css-template/imagens/star3.svg';
import '../html-css-template/css/Circulo.css'
import apiProdutos from '../apiProduto';
import { useState, useEffect } from "react";


function Nota() {

    const [avaliacao, setAvaliacao] = useState([]);


    useEffect
    (() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`avaliacao/stats?idDoador=${idUsuario}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setAvaliacao(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    return (
        <>
            <body>
            <div id="circulo">
        <div class="circulo_informacoes">
            <p id="nota">{avaliacao.mediaAvaliacoes}</p>
            <div class="estrelas_maior">
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
            </div>
            <p id="avaliacoes">{avaliacao.quantidadeAvaliacoes} avaliação no total</p>
        </div>
    </div>
            </body>
        </>
    );
}

export default Nota;