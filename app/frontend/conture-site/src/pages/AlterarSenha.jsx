import Header from "../components/Header";
import Footer from "../components/Footer";
import '../html-css-template/css/AlterarSenha.css';
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import iconSalvar from "../html-css-template/imagens/folder-plus.png"

function AlterarSenha() {

    const ocultarSenha = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye1");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        }
        else {
            senha.type = "password";
            img.src = iconClose;
        }
    }

    const ocultarSenha2 = () => {
        var senha2 = document.getElementById("senha2");
        var img2 = document.getElementById("eye2");
        if (senha2.type == "password") {
            senha2.type = "text";
            img2.src = iconOpen;
        }
        else {
            senha2.type = "password";
            img2.src = iconClose;
        }
    }

    const ocultarSenha3 = () => {
        var senha2 = document.getElementById("senha3");
        var img2 = document.getElementById("eye3");
        if (senha2.type == "password") {
            senha2.type = "text";
            img2.src = iconOpen;
        }
        else {
            senha2.type = "password";
            img2.src = iconClose;
        }
    }

    return (
        <>
            <Header />
            <section id="as-section">
                <div className="grid">
                    <div id="as-parte-um">
                        <div className="as-opcao">
                            <p>Editar perfil</p>
                        </div>
                        <div className="as-opcao as-opcao-selecionada">
                            <p>Trocar senha</p>
                        </div>
                        <div className="as-opcao">
                            <p>Validação</p>
                        </div>
                    </div>
                    <div id="as-parte-dois">
                        <div className="as-titulo">
                            <h2>TROCAR SENHA</h2>
                        </div>
                        <div className="as-campos">
                            <div className="as-campo">
                                <label htmlFor="senha-desabilitar">Insira sua senha</label>
                                <input type="password" name="senha-desabilitar" className="senha-desabilitar" id="senha" />
                                <img src={iconClose} alt="" className="eye" id="eye1" onClick={ocultarSenha} />
                            </div>
                            <div className="as-campo">
                                <label htmlFor="senha-desabilitar">Insira sua senha</label>
                                <input type="password" name="senha-desabilitar" className="senha-desabilitar" id="senha2" />
                                <img src={iconClose} alt="" className="eye" id="eye2" onClick={ocultarSenha2} />
                            </div>
                            <div className="as-campo">
                                <label htmlFor="senha-desabilitar">Insira sua senha</label>
                                <input type="password" name="senha-desabilitar" className="senha-desabilitar" id="senha3" />
                                <img src={iconClose} alt="" className="eye" id="eye3" onClick={ocultarSenha3} />
                            </div>
                        </div>
                        <div className="as-aviso">
                            <p></p>
                        </div>
                        <div className="as-btns">
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

export default AlterarSenha;
