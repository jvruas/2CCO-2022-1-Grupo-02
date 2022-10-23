import "../html-css-template/css/SideBar.css"
import logo from "../html-css-template/imagens/logo-conture.png"

function SideBar(){
    return(
        <>
            <div className="side-bar">
                <div className="div-image">
                    <img src={logo} />
                </div>
                <div className="div-title-side-bar">
                    <b>Administrador</b>
                </div>

                <div className="div-topic">
                    <b>GRÁFICOS BI</b>
                    <br />
                    <br />
                    ANALYTICS
                </div>
            </div>
        </>
    );
}

export default SideBar