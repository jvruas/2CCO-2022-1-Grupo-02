import Header from "../components/Header"
import Perfil from "../components/PerfilSimples"
import MenuPerfil from "../components/MenuAvaliacao"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/AvaliacaoPage.css'
import Footer from "../components/Footer"
import Nota from "../components/Nota"
import Comentarios from "../components/Avaliacao"



function Avaliacao() {
    return (
        <>
            <Header></Header>
            <section id="ava-section">
                <Perfil/>
                <MenuPerfil></MenuPerfil>
                <div className="conteiner-avaliacao">
                    <div className="div_superior_ava"><b>Avaliação</b></div>
                    <div className="div_inferior">
                        <Nota></Nota>
                    </div>
                    <div className="comentarios">
                    <Comentarios></Comentarios>
                    <Comentarios></Comentarios>
                
                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default Avaliacao;