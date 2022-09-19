import Header from "../components/Header"
import Perfil from "../components/PerfilSimples"
import MenuPerfil from "../components/MenuDisponivel"
import '../html-css-template/css/ProdutoDisponivelPage.css'
import Footer from "../components/Footer"
import Card from "../components/ProdutoCop"
import Filtro from "../html-css-template/imagens/Filtro.svg";


function Disponivel() {
    return (
        <>
            <Header></Header>
            <section id="disp-section">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>

                <div className="conteiner-produto">
                    <div className="div_sup"><b><p>Disponíveis</p></b> <img src={Filtro} /></div>
                    <div className="div_inf">
                        <div className="div_card">
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