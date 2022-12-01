
import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuHistoricoPessoal"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/HistoricoPessoalPage.css'
import Footer from "../components/Footer"
import Historico from "../components/HistoricoMaior"
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto"
import apiUsuario from "../apiUsuario"
import { useNavigate } from "react-router-dom"

function HistoricoDonatario() {

    const [historico, setHistorico] = useState([]);
    const [usuario, setUsuario] = useState([]);
    let idUsuario = sessionStorage.getItem('idUsuarioLogado');


    let status = sessionStorage.getItem('status')
    let navegar = useNavigate();
    useEffect(() => {

        apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
            try {
                console.log("uijhhjh", resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
        let dataCad = new Date(historico.dataConclusao);

        apiUsuario.get(`/${idUsuario}`).then((usuarioResposta) => {
            try {
                console.log(usuarioResposta.data)
                setUsuario(usuarioResposta.data)
            } catch (error) {
                console.log(error)
            }
        })

    }, [])

    const getFiltro = () => {
        let status = sessionStorage.getItem('status');
        apiProdutos.get(`/status?idDoador=${idUsuario}&status=${status}`).then((resposta) => {
            try {
                console.log("uijhhjh", resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }

    function Redirect(event) {
        console.log(event)
        if (event.target.value != "") {
            sessionStorage.setItem("status", event.target.value);
            getFiltro();
        }
    }




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
                            <select onChange={((event) => { Redirect(event) })} name="select_opcoes" id="select_opcoes">
                                <option value='todos'>Todos</option>
                                <option value='andamento'>Processo</option>
                                <option value='doado'>Doados</option>
                                <option value='recebido'>Recebidos</option>
                            </select>
                        </div>

                    </div>
                    <div className="div_historicos">
                        {historico != undefined && historico.length > 0 ? historico.map((historico) => (
                            <Historico
                                tipo={historico.tipo}
                                tipoEquipamento={historico.categoriaProduto}
                                equipamento={historico.nome}
                                negociante={usuario.nome}
                                dataCon={historico.dataConclusao}
                            />
                        )) : ""}


                    </div>
                </div>
            </section>
            <Footer></Footer>
        </>
    )
}

export default HistoricoDonatario;