import Header from "../components/Header"

import Perfil from "../components/PerfilSimples"

import MenuPerfil from "../components/MenuAvaliacao"

import '../html-css-template/css/Style.css'

import '../html-css-template/css/AvaliacaoPage.css'

import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';

import Footer from "../components/Footer"

import Nota from "../components/NotaCop"

import Comentarios from "../components/Avaliacao"

import { useState, useEffect } from "react";

import apiProdutos from "../apiProduto";

import apiUsuario from "../apiUsuario"





function Avaliacao() {



    const [produtos, setProdutos] = useState([]);

    const [usuarioAva, setUsuarioAva] = useState([]);

    const [endereco, setEndereco] = useState([]);

    var aval = [];



    useEffect(() => {

        let idDoador = sessionStorage.getItem('idDoador');

        apiProdutos.get(`/avaliacao?idDoador=${idDoador}`).then((resposta) => {



            console.log(resposta.data.fila)

            setProdutos(resposta.data.fila)



            setUsuarioAva(aval)

            console.log("usaurio aval", aval)

            try {

            } catch (error) {

                console.log(error)

            }

        })

    }, [])



    function formatacaoId(id) {

        if (id < 10) {

            return "#000" + id;

        } else if (id < 100) {

            return "#00" + id;

        } else if (id < 1000) {

            return "#0" + id;

        } else {

            return "#" + id;

        }

    }



    return (

        <>

            <Header></Header>

            <section id="ava-section">

                <Perfil />

                <MenuPerfil></MenuPerfil>

                <div className="conteiner-avaliacao">

                    <div className="div_superior_ava"><b>Avaliação</b></div>

                    <div className="div_inferior">

                        <Nota></Nota>

                    </div>

                    <div className="comentarios">

                        {produtos != undefined && produtos.length > 0 ? produtos.map((ava) => (

                            <Comentarios

                            idDoador={formatacaoId(ava.fkDonatario)}

                            otherId={ava.fkDonatario}

                            nota={ava.valor}

                            comentario={ava.comentario}

                            donatario={ava.nome}

                            dataCon={ava.data}

                            foto={ava.perfilImage != null || ava.perfilImage != undefined ? ava.perfilImage : fotoLogado}

                            />

                        )) : ""}



                    </div>

                </div>



            </section>

            <Footer></Footer>

        </>

    )

}



export default Avaliacao;