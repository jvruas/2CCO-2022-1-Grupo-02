import "../html-css-template/css/Dashboard.css"
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
            </div>
        </>
    );
}

export default SideBar