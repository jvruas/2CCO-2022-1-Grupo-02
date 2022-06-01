import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Cadastro1.css'
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import imgEtapa from "../html-css-template/imagens/Etapas.png"
import iconInfoSenha from "../html-css-template/imagens/info-circle 1.png"
import iconSeta from "../html-css-template/imagens/seta.png"
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import { Link } from "react-router-dom";

function CadastrarPt1() {

    const handleClick = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye4");
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
        var senha = document.getElementById("confSenha");
        var img = document.getElementById("eye5");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        } 
        else{
            senha.type = "password";
            img.src = iconClose;
        }
    }

    return (
        <>
            <MenuSimples />
            <section class="section-cadastro1">
                <form id="form-cadastro1" action="../html/Cadastro2.html" method="post">
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
                            onClick={handleClick} />
                    </div>

                    <div class="divisao input">
                        <label for="confSenha">Confirmar senha</label>
                        <input type="password" name="confSenha" id="confSenha" />
                        <img src={iconSenha} alt="Ícone senha escondida" class="eye" id="eye5"
                            onClick={handleClick2} />
                    </div>
                    <div class="divisao direita">
                        <Link className="link" to="/finalizar-cadastro"><button className="btn-cadastro1" type="submit">PRÓXIMO<img src={iconSeta} alt="Ícone de próximo" /></button></Link>
                    </div>

                </form>

            </section>
        </>
    );
}

export default CadastrarPt1;
