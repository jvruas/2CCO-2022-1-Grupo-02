import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash 2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/Rectangle 44.svg"
import apiProduto from "../apiProduto.js";
import { useState, useEffect } from "react";

function Produto(props) {

    // const [produto, setProduto] = useState([]);
    // const [usuarioImg, setUsuarioImg] = useState([]);

    // useEffect(() => {
    //     let idUsuario = sessionStorage.getItem('idUsuarioLogado');
    //     apiProduto.get(`/listarProdutosUsuarios${idUsuario}`).then((resposta) => {
    //         try {
    //             console.log(resposta.data)
    //             setUsuario(resposta.data)
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }, [])

    return (

        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img src={trash} alt="" /> <div class="visualizacao"><img src={vector} alt="" /> <p></p></div></div>
                <div className="meio"><img src={foto} alt="" /></div>
                <div className="inferior"><p>Notebook Lenovo S145</p></div>
            </div>
        </div>
    );
}

export default Produto
