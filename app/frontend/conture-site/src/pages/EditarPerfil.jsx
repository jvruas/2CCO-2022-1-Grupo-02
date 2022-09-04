import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/EditarPerfil.css';
import iconSalvar from "../html-css-template/imagens/folder-plus.png";
import iconX from "../html-css-template/imagens/icon-X.svg";

function EditarPerfil() {


    return (
        <>
            <Header />
            <section>
                <div className="grid">
                    <div id="ep-parte-um">
                        <div className="ep-opcao ep-opcao-selecionada">
                            <p>Editar perfil</p>
                        </div>
                        <div className="ep-opcao">
                            <p>Trocar senha</p>
                        </div>
                        <div className="ep-opcao">
                            <p>Validação</p>
                        </div>
                    </div>
                    <div id="ep-parte-dois">
                        <div className="ep-titulo">
                            <h2>EDITAR PERFIL</h2>
                        </div>
                        <div className="ep-campos">
                            <div className="ep-campo">
                                <label for="estado-civil">Estado civil</label>
                                <select name="estado-civil" id="estado-civil"></select>
                            </div>
                            <div className="ep-campo">
                                <label for="telefone">Telefone</label>
                                <input type="tel" name="telefone" id="telefone" />
                            </div>
                            <div className="ep-campo">
                                <label for="genero">Gênero</label>
                                <select name="genero" id="genero"></select>
                            </div>
                            <div className="ep-campo">
                                <label for="cep">CEP</label>
                                <input type="text" name="cep" id="cep" />

                            </div>
                            <div className="ep-campo">
                                <label for="escolaridade">Escolaridade</label>
                                <select name="escolaridade" id="escolaridade"></select>
                            </div>
                            <div className="ep-campo">
                                <label for="situacao-atual">Situação atual</label>
                                <select name="situacao-atual" id="situacao-atual"></select>
                            </div>
                        </div>
                        <div className="ep-btns">
                            <a href=""><div>Desabilitar conta<img src={iconX} alt="" /></div></a>
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