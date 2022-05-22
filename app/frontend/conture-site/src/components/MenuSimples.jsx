import logo from '../html-css-template/imagens/logo-conture.png'

function MenuSimples() {
    return (
        <header>
            <div class="container">
                <img src={logo} alt="Logo Conture" class="logo" />
            </div>
        </header>
    );
}

export default MenuSimples;