import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuPerfil"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/css-pages/Disponivel.css'
import Footer from "../components/Footer"
import Card from "../components/Produto"



function Disponivel() {
    return (
        <>
            <Header></Header>
            <section>
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>

                <div className="conteiner">
                    <div className="div_sup"><p>Disponiveis</p></div>
                    <div className="div_inf">
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>   
                    
                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default Disponivel;