import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuDisponivel"
// import '../html-css-template/css/Style.css'
import '../html-css-template/css/css-pages/Disponivel.css'
import Footer from "../components/Footer"
import Card from "../components/Produto"


function Disponivel() {
    return (
        <>
            <Header></Header>
            <section className="section">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>

                <div className="conteiner-produto">
                    <div className="div_sup"><b><p>Disponíveis</p></b></div>
                    <div className="div_inf">
                        <div className="div_card">
                            <Card></Card>
                            <Card></Card>
                            <Card></Card>
                            <Card></Card>
                        </div>
                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default Disponivel;