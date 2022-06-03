import logo from '../html-css-template/imagens/logo-conture.png'

function MenuSimples() {
    return (
        <header>
            <div className="container">
                <img src={logo} alt="Logo Conture" className="logo" />
            </div>
        </header>
    );
}

export default MenuSimples;