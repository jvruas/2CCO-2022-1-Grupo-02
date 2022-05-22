import MenuSimples from "./MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/EsqueciSenha.css'

import iconInfoSenha from "../html-css-template/imagens/info-circle 1.png"
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import iconSalvar from "../html-css-template/imagens/folder-plus.png"

function EsqueciSenha() {
    return (
        <>
            <MenuSimples />
            <section class="centralizado">
        <form id="" action="" onsubmit="">
            <div class="divisao centralizado">
                <h1>ALTERAR SENHA</h1>
            </div>

            <div class="divisao input">

                <label for="senha">Senha
                    <div class="tooltip"><img src={iconInfoSenha} alt="Ícone deconfirmação"/>
                        <span class="tooltiptext"><b>Dicas para criar uma senha segura:</b>
                            <br/>Use no mínimo 6 caracteres
                            <br/>Combine letras maiúsculas e minúsculas, símbolos e números
                            <br/>Não use informações pessoais
                        </span>
                    </div>
                </label>

                <input type="password" name="senha" id="senha"/>
                <img src={iconSenha} alt="Ícone senha escondida" class="eye" id="eye2"
                    onClick="mostrarOcultarSenha2()"/>
            </div>
            <div class="divisao input">
                <label for="confSenha">Confirmar sua senha</label>
                <input type="password" name="confSenha" id="confSenha"/>
                <img src={iconSenha} alt="Ícone senha escondida" class="eye" id="eye3"
                    onClick="mostrarOcultarSenha3()"/>
            </div>
            <div class="divisao centralizado">
                <button>
                    <p>SALVAR</p><img src={iconSalvar} alt="Ícone de confirmação"/>
                </button>
            </div>
        </form>
    </section>
            
        </>
    );
}

export default EsqueciSenha;