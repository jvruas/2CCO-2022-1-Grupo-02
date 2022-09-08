import Header from "../components/Header"
import Perfil from "../components/Perfil"
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
            <section id="av-section">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>
                <div className="conteiner-avaliacao">
                    <div className="div_superior"><b><p>Avaliação</p></b></div>
                    <div className="div_inferior">
                        <Nota></Nota>
                    </div>
                    <div className="comentarios">
                    <Comentarios></Comentarios>
                    <Comentarios></Comentarios>
                    <Comentarios></Comentarios>
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