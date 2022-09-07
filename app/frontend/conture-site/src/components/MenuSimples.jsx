import logo from '../html-css-template/imagens/logo-conture.png'
import { Link } from "react-router-dom";

function MenuSimples() {
    return (
        <header>
            <div className="container">
                <Link to="/"><img src={logo} alt="Logo Conture" className="logo" /></Link>
            </div>
        </header>
    );
}

export default MenuSimples;