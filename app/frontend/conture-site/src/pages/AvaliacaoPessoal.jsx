import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuAvaliacaoPessoal"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/AvaliacaoPage.css'
import Footer from "../components/Footer"
import Nota from "../components/Nota"
import Comentarios from "../components/Avaliacao"
import apiProdutos from "../apiProduto.js"
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js"


function AvaliacaoPessoal() {

    const [produtos, setProdutos] = useState([]);
    const [usuario, setUsuario] = useState([]);
    const [endereco, setEndereco] = useState([]);

    
    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`/avaliacao?idDoador=${idUsuario}`).then((resposta) => {

            console.log("pppdgdg", resposta.data.fila)
            setProdutos(resposta.data.fila)
            
            for(let i=0; i<resposta.data.fila.length; i++){
            apiUsuario.get(`/${resposta.data.fila[i].fkDonatario}`).then((usuarioResposta) => {
                try {
                    console.log(usuarioResposta.data)
                    setUsuario(usuarioResposta.data)
                    fetch(`https://viacep.com.br/ws/${usuarioResposta.data.cep}/json/`)
                    .then(res => res.json()).then(data => {
                        console.log(data)
                        setEndereco(data)
                    })
                } catch (error) {
                    console.log(error)
                }
            })
        }
            try {
            } catch (error) {
                console.log(error)
            }
        })
    }, [])



    return (
        <>
            <Header></Header>
            <section id="ava-section">
                <Perfil />
                <MenuPerfil></MenuPerfil>
                <div className="conteiner-avaliacao">
                    <div className="div_superior_ava"><b><p>Avaliação</p></b></div>
                    <div className="div_inferior">

                        <Nota></Nota>
                    </div>
                    <div className="comentarios">
                        {produtos != undefined && produtos.length > 0 ? produtos.map((ava) => (
                            <Comentarios 
                           
                                nota={ava.valor}
                                comentario={ava.comentario}
                                donatario={usuario.nome}
                                cidade={endereco.localidade}
                                estado={endereco.uf}
                                dataCon={ava.data}

                            />
                        )): ""}


                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default AvaliacaoPessoal;