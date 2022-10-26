
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
    const [usuario, setUsuario] = useState([]);
    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idDoador');
            apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
            try {
                console.log("uijhhjh", resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
        let dataCad = new Date(historico.dataConclusao);

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

    apiUsuario.get(`/${idUsuario}`).then((usuarioResposta) => {
        try {
            console.log("ofdoidsfodsfoifdsj",usuarioResposta.data)
            setUsuario(usuarioResposta.data)
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
                                <option value="">Todos</option>
                                <option value="">Processo</option>
                                <option value="">Doados</option>
                                <option value="">Recebidos</option>
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