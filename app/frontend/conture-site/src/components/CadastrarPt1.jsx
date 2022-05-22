import MenuSimples from "./MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Cadastro1.css'

import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import imgEtapa from "../html-css-template/imagens/Etapas.png"
import iconInfoSenha from "../html-css-template/imagens/info-circle 1.png"
import iconSeta from "../html-css-template/imagens/seta.png"

function CadastrarPt1() {
    return (
        <>
            <MenuSimples />
            <section>
                <form action="../html/Cadastro2.html" method="post">
                    <div class="divisao centralizado">
                        <h1>CADASTRO DE USUÁRIO</h1>
                    </div>
                    <div class="divisao centralizado">
                        <img class="imp_progresso" src={imgEtapa} alt="Barra de progresso de cadastro" />
                    </div>
                    <div class="divisao input">
                        <label for="nome">E-mail</label>
                        <input type="email" name="nome" />
                    </div>

                    <div class="divisao input">

                        <label for="senha">
                            <div class="tooltip">Senha<img src={iconInfoSenha} alt="Ícone deconfirmação" />
                                <span class="tooltiptext">
                                    <b>Dicas para criar uma senha segura:</b>
                                    <br />Use no mínimo 6 caracteres
                                    <br />Combine letras maiúsculas e minúsculas, símbolos e números
                                    <br />Não use informações pessoais
                                </span>
                            </div>
                        </label>

                        <input type="password" name="senha" id="senha" />
                        <img src={iconSenha} alt="Ícone senha escondida" class="eye" id="eye4"
                            onClick="mostrarOcultarSenha4()" />
                    </div>

                    <div class="divisao input">
                        <label for="confSenha">Confirmar senha</label>
                        <input type="password" name="confSenha" id="confSenha" />
                        <img src={iconSenha} alt="Ícone senha escondida" class="eye" id="eye5"
                            onClick="mostrarOcultarSenha5()" />
                    </div>
                    <div class="divisao direita">
                        <button type="submit">PRÓXIMO<img src={iconSeta} alt="Ícone de próximo" /></button>
                    </div>

                </form>

            </section>
        </>
    );
}

export default CadastrarPt1;
