import "../html-css-template/css/SideBar.css"
import logo from "../html-css-template/imagens/logo-conture.png"
import iconBi from "../html-css-template/imagens/bi.svg"
import iconAna from "../html-css-template/imagens/analytics.svg"
import iconSair from "../html-css-template/imagens/sair.svg"
import Analytics from "../pages/Analytics";
import { Link, useNavigate } from "react-router-dom";

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
                        <img src={iconBi} alt="Ícone de BI" /><Link to="/dashboard"><p>Gráficos BI</p></Link>
                    </div>
                    <div className="side-dash-opcoes">
                        <img src={iconAna} alt="Ícone de Analytics" /><Link to="/analytics"><p>Analytics</p></Link>
                    </div>
                </div>
                <div className="div-sair">
                    <img src={iconSair} alt="Ícone de sair" /><Link to="/feed"><p>Sair</p></Link>
                </div>
            </div>
        </>
    );
}

export default SideBar;