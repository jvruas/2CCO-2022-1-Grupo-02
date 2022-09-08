
import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuHistoricoPessoal"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/HistoricoPessoalPage.css'
import Footer from "../components/Footer"
import Historico from "../components/HistoricoMaior"



function HistoricoDonatario() {
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
                        <Historico></Historico>
                        <Historico></Historico>
                        <Historico></Historico>
                        <Historico></Historico>
                        
                    </div>
                </div>
                </section>
                <Footer></Footer>
        </>
    )
}

export default HistoricoDonatario;