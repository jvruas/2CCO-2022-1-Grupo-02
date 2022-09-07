import logo from '../html-css-template/imagens/logo-conture.png';
import lupa from '../html-css-template/imagens/icon-lupa.png';
import perfil from '../html-css-template/imagens/foto.jpg';
import setaBaixo from '../html-css-template/imagens/chevron-down 1.svg';
import interesse from '../html-css-template/imagens/Interesses.svg';
import mensagem from '../html-css-template/imagens/Icon mensagem.svg';
import notificacao from '../html-css-template/imagens/Icon notificação.svg';
import adicionarProduto from '../html-css-template/imagens/Subtract.svg';
import '../html-css-template/css/Style.css'
import { Link } from "react-router-dom";

function Header() {

    const mostrarMenu = () => {
        var menu = document.getElementById("he-tooltip");
        if (menu.style.visibility == "hidden") {
            menu.style.visibility = "visible";
        }
        else {
            menu.style.visibility = "hidden";
        }
    }

    return (
        <>
            <header id="header_completo">
                <div id="header_sup">
                    <div class="container_completo">
                        <Link to="/"><img src={logo} alt="Logo Conture" className="logo" /></Link>
                        <div id="input_pesquisar">
                            <input type="text" />
                            <button>
                                <img src={lupa} alt="Lupa de pesquisa" />
                            </button>
                        </div>
                        <div id="div_usuario">
                            <img src={perfil} alt="" id="img_foto" />
                            <p id="nome_usuario">Cleiton</p>
                            <img src={setaBaixo} alt="" className="img_seta" id="seta_menu" onClick={mostrarMenu} />
                            <div id="he-tooltip">
                                <div className="menuzinho">
                                    <div id="triangulo-para-cima">
                                    </div>
                                    {/* <div id="menuzinho-deslogado">
                                        <div className="mn-parte-um">
                                            <button><Link className="he-link-btn" to="/login">Entrar</Link></button>
                                        </div>
                                        <div className="mn-parte-dois">
                                            <p>Não é cadastrado ainda?</p>
                                            <button><Link className="he-link-btn" to="/cadastro">Cadastre-se</Link></button>
                                        </div>
                                    </div> */}

                                    <div id="menuzinho-logado">
                                        <p><Link className="he-link-p" to="/disponivel">Minha conta</Link></p>
                                        <p><Link className="he-link-p" to="/editar-perfil">Configurações</Link></p>
                                        <button><Link className="he-link-btn" to="/">SAIR</Link></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="div_icones">
                            <img src={interesse} alt="" />
                            <Link class="he-link-img" to="/mensagem-direta"><img src={mensagem} alt="" /></Link>
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
