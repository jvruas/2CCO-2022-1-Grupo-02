import logo from '../html-css-template/imagens/logo-conture.png';
import lupa from '../html-css-template/imagens/icon-lupa.png';
import perfil from '../html-css-template/imagens/foto.jpg';
import setaBaixo from '../html-css-template/imagens/chevron-down 1.svg';
import interesse from '../html-css-template/imagens/Interesses.svg';
import mensagem from '../html-css-template/imagens/Icon mensagem.svg';
import notificacao from '../html-css-template/imagens/Icon notificação.svg';
import adicionarProduto from '../html-css-template/imagens/Subtract.svg';
import '../html-css-template/css/Style.css'

function Header() {
    return (
        <>
            <header id="header_completo">
                <div id="header_sup">
                    <div class="container_completo">
                        <img src={logo} alt="" class="logo" />
                        <div id="input_pesquisar">
                            <input type="text" />
                            <button>
                                <img src={lupa} alt="Lupa de pesquisa" />
                            </button>
                        </div>
                        <div id="div_usuario">
                            <img src={perfil} alt="" id="img_foto" />
                            <p>Cleiton</p>
                            <img src={setaBaixo} alt="" className="img_seta" />
                        </div>
                        <div id="div_icones">
                            <img src={interesse} alt="" />
                            <img src={mensagem} alt="" />
                            <img src={notificacao} alt="" />
                        </div>
                        <button id="btn_doacao">
                            <p>DOAR PRODUTO</p>
                            <img src={adicionarProduto} alt="" />
                        </button>
                    </div>
                </div>
                <div id="header_inferior">
                    <div id="div_produto">
                        <p>Produtos</p>
                        <img src={setaBaixo} alt="" className="img_seta" />
                    </div>
                    <div id="div_produtos">
                        <p>Notebook</p>
                        <p>Celular</p>
                        <p>Tablet</p>
                        <p>Desktop</p>
                    </div>
                </div>

            </header>
        </>
    );
}

export default Header;
