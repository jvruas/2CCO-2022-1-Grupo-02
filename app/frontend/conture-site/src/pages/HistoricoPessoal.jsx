
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
    let idUsuario;
    
    let navegar = useNavigate();
    useEffect(() => {
        idUsuario = sessionStorage.getItem('idUsuarioLogado');
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
                console.log("ofdoidsfodsfoifdsj", usuarioResposta.data)
                setUsuario(usuarioResposta.data)
            } catch (error) {
                console.log(error)
            }
        })

    }, [])

    function Redirect(event){
 
        sessionStorage.setItem("status", event.nativeEvent.path[1].id);
        let status = sessionStorage.getItem("status");
            apiProdutos.get(`/status?idDoador=${idUsuario}&status=${status}`).then((resposta) => {
                try {
                    console.log("pgiffnfd", resposta.data)
                    setHistorico(resposta.data)
                } catch (error) {
                    console.log(error)
                }
            })
        navegar("/historico-pessoal")
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
                            <select name="select_opcoes" id="select_opcoes">
                                <option id='todos' onClick={((event) =>{Redirect(event)})}>Todos</option>
                                <option id='andamento' onClick={((event) =>{Redirect(event)})}>Processo</option>
                                <option id='doado' onClick={((event) =>{Redirect(event)})}>Doados</option>
                                <option id='recebido' onClick={((event) =>{Redirect(event)})}>Recebidos</option>
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