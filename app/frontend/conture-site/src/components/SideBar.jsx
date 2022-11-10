import "../html-css-template/css/SideBar.css"
import logo from "../html-css-template/imagens/logo-conture.png"
import iconBi from "../html-css-template/imagens/bi.svg"
import iconAna from "../html-css-template/imagens/analytics.svg"
import iconSair from "../html-css-template/imagens/sair.svg"

function SideBar() {
    return (
        <>
            <div className="side-bar">
                <div className="div-image">
                    <img src={logo} />
                </div>
                <div className="div-title-side-bar">
                    <b>Administrador</b>
                </div>

                <div className="div-topic dvi">
                    <div className="side-dash-opcoes">
                        <img src={iconBi} alt="Ícone de BI" /><p>GRÁFICOS BI</p>
                    </div>
                    <div className="side-dash-opcoes">
                        <img src={iconAna} alt="Ícone de Analytics" /><p>ANALYTICS</p>
                    </div>
                </div>
                <div className="div-sair">
                    <img src={iconSair} alt="Ícone de sair" /><p>SAIR</p>
                </div>
            </div>
        </>
    );
}

export default SideBar;