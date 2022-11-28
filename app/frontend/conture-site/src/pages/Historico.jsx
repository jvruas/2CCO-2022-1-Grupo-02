
import Header from "../components/Header"
import Perfil from "../components/PerfilSimples"
import MenuPerfil from "../components/MenuHistorico"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/HistoricoPessoalPage.css'
import Footer from "../components/Footer"
import Historico from "../components/HistoricoMenor"
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"
import apiUsuario from "../apiUsuario"


function HistoricoDonatario() {

    
    const [historico, setHistorico] = useState([]);
    const [usuarioDon, setUsuarioDon] = useState([]);
    const opcoes = document.getElementById("select_opcoes").value;
    useEffect(() => {
        let idDoador= sessionStorage.getItem('idDoador');
            apiProdutos.get(`/status?idDoador=${idDoador}&status=todos`).then((resposta) => {
            try {
                console.log("uijhhjh", resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
        let dataCad = new Date(historico.dataConclusao);

    if(opcoes == 0){
        apiProdutos.get(`/status?idDoador=${idDoador}?todos`).then((resposta) => {
            try {
                console.log(resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }
    if(opcoes == 1){
        apiProdutos.get(`/status?idDoador=${idDoador}?andamento`).then((resposta) => {
            try {
                console.log(resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }
    if(opcoes == 2){
        apiProdutos.get(`/status?idDoador=${idDoador}?doado`).then((resposta) => {
            try {
                console.log(resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }
    if(opcoes == 3){
        apiProdutos.get(`/status?idDoador=${idDoador}?recebido`).then((resposta) => {
            try {
                console.log(resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }


    // if(select_opcoes.value == 0){
    //     apiProdutos.get(`/status?idDoador=${idUsuario}?todos`).then((resposta) => {
    //         try {
    //             console.log(resposta.data)
    //             setHistorico(resposta.data)
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }

    apiUsuario.get(`/${idDoador}`).then((usuarioResposta) => {
        try {
            console.log(usuarioResposta.data)
            setUsuarioDon(usuarioResposta.data)
        } catch (error) {
            console.log(error)
        }
    })
    
    }, [])


    return (
        <>
            <Header></Header>
            <section id="hp-section">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>
                <div id="conteiner">
                    <div id="div_sup">
                        <b><p>Histórico</p></b>

                        <div id="div_filtro"><p>Filtrar por</p>

                            <select name="" id="select_opcoes">
                                <option value="0">Todos</option>
                                <option value="1">Processo</option>
                                <option value="2">Doados</option>
                                <option value="3">Recebidos</option>
                            </select>
                        </div>

                    </div>
                    <div className="div_historicos">
                    {historico != undefined && historico.length > 0 ?  historico.map((historico)=>(
                            <Historico 
                            tipo={historico.tipo}
                            tipoEquipamento={historico.categoriaProduto}
                            dataCon={historico.dataConclusao}
                             />
                            )): ""}
                        
                    </div>
                </div>
            </section>
            <Footer></Footer>
        </>
    )
}

export default HistoricoDonatario;