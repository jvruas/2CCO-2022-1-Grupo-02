import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/EsqueciSenha.css'
import '../html-css-template/js/EsqueciSenha.js'
import iconInfoSenha from "../html-css-template/imagens/info-circle 1.png"
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import iconSalvar from "../html-css-template/imagens/folder-plus.png"
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"

function EsqueciSenha() {

    const handleClick = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye2");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        } 
        else{
            senha.type = "password";
            img.src = iconClose;
        }
    }

    const handleClick2 = () => {
        var senha2 = document.getElementById("senha2");
        var img2 = document.getElementById("eye3");
        if (senha2.type == "password") {
            senha2.type = "text";
            img2.src = iconOpen;
        }
        else{
            senha2.type = "password";
            img2.src = iconClose;
        }
    }

    return (
        <>
            <MenuSimples />
            <section className="section-esqueci-senha centralizado">
                <form id="form-esqueci-senha" action="" >
                    <div className="divisao centralizado">
                        <h1>ALTERAR SENHA</h1>
                    </div>

                    <div className="divisao input">

                        <label htmlFor="senha">Senha
                            <div className="tooltip"><img src={iconInfoSenha} alt="Ícone de confirmação" />
                                <span className="tooltiptext"><b>Dicas para criar uma senha segura:</b>
                                    <br />Use no mínimo 6 caracteres
                                    <br />Combine letras maiúsculas e minúsculas, símbolos e números
                                    <br />Não use informações pessoais
                                </span>
                            </div>
                        </label>

                        <input type="password" name="senha" id="senha" />
                        <img src={iconSenha} alt="Ícone senha escondida" className="eye" id="eye2" onClick={handleClick} />
                    </div>
                    <div className="divisao input">
                        <label htmlFor="confSenha">Confirmar sua senha</label>
                        <input type="password" name="confSenha" id="senha2" />
                        <img src={iconSenha} alt="Ícone senha escondida" className="eye" id="eye3"
                            onClick={handleClick2}/>
                    </div>
                    <div className="divisao centralizado">
                        <button className="btn-esqc">
                            <p>SALVAR</p><img src={iconSalvar} alt="Ícone de confirmação" />
                        </button>
                    </div>
                </form>
            </section>

        </>
    );
}

export default EsqueciSenha;