import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/EditarPerfil.css';
import iconSalvar from "../html-css-template/imagens/folder-plus.png";
import iconX from "../html-css-template/imagens/icon-X.svg";
import { Link } from "react-router-dom";

function EditarPerfil() {


    return (
        <>
            <Header />
            <section id="ep-section">
                <div className="grid">
                    <div id="ep-parte-um">
                        <div className="ep-opcao ep-opcao-selecionada">
                            <Link className="link-p" to="/editar-perfil"><p>Editar perfil</p></Link>
                        </div>
                        <div className="ep-opcao">
                            <Link className="link-p" to="/alterar-senha"><p>Trocar senha</p></Link>
                        </div>
                        <div className="ep-opcao">
                            <Link className="link-p" to="/validacao-usuario"><p>Validação</p></Link>
                        </div>
                    </div>
                    <div id="ep-parte-dois">
                        <div className="ep-titulo">
                            <h2>EDITAR PERFIL</h2>
                        </div>
                        <div className="ep-campos">
                            <div className="ep-campo">
                                <label htmlFor="estado-civil">Estado civil</label>
                                <select name="estado-civil" id="estado-civil"></select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="telefone">Telefone</label>
                                <input type="tel" name="telefone" id="telefone" />
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="genero">Gênero</label>
                                <select name="genero" id="genero"></select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="cep">CEP</label>
                                <input type="text" name="cep" id="cep" />

                            </div>
                            <div className="ep-campo">
                                <label htmlFor="escolaridade">Escolaridade</label>
                                <select name="escolaridade" id="escolaridade"></select>
                            </div>
                            <div className="ep-campo">
                                <label htmlFor="situacao-atual">Situação atual</label>
                                <select name="situacao-atual" id="situacao-atual"></select>
                            </div>
                        </div>
                        <div className="ep-btns">
                            <Link to="/desabilitar-perfil"><div>Desabilitar conta<img src={iconX} alt="" /></div></Link>
                            <button>
                                <p>SALVAR</p><img src={iconSalvar} alt="Ícone de salvar" />
                            </button>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default EditarPerfil;