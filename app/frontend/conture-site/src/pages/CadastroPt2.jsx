import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Cadastro2.css'

import imgEtapa from "../html-css-template/imagens/Etapas (1).png"
import iconSeta from "../html-css-template/imagens/seta 2.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"

function CadastrarPt2() {
    return (
        <>
            <MenuSimples />
            <section>
        <form id="form-cadastro2" action="" onsubmit="">
            <div class="divisao centralizado">
                <h1>CADASTRO DE USUÁRIO</h1>
            </div>
            <div class="divisao centralizado">
                <img class="imp_progresso" src={imgEtapa} alt="Barra de progresso de cadastro"/>
            </div>
            <div class="divisao input maior">
                <label for="nome">Nome</label>
                <input type="text" name="nome"/>
            </div>
            <div class="divisao input maior">
                <label for="sobrenome">Sobrenome</label>
                <input type="text" name="sobrenome"/>
            </div>
            <div class="input_double">
                <div class="input menor">
                    <label for="cpf">CPF</label>
                    <input type="txt" name="cpf"/>
                </div>
                <div class="input menor">
                    <label for="dataNasc">Data de nascimento</label>
                    <input type="date" name="dataNasc"/>
                </div>
            </div>
            <div class="input_double">
                <div class="input menor">
                    <label for="genero">Gênero</label>
                    <select name="genero" id="genero">
                        <option value=""></option>
                        <option value="1">Feminino</option>
                        <option value="2">Masculino</option>
                        <option value="3">Outro</option>
                    </select>
                </div>
                <div class="input menor">
                    <label for="estadoCivil">Estado civil</label>
                    <select name="estadoCivil" id="estadoCivil">
                        <option value=""></option>
                        <option value="1">Solteiro(a)</option>
                        <option value="2">Casado(a)</option>
                        <option value="3">Divorciado(a)</option>
                        <option value="4">Viúvo(a)</option>
                    </select>
                </div>
            </div>
            <div class="input_double">
                <div class="input menor">
                    <label for="telefone">Telefone</label>
                    <input type="text" name="telefone"/>
                </div>
                <div class="input menor">
                    <label for="cep">CEP</label>
                    <input type="text" name="cep"/>
                </div>
            </div>
            <div class="divisao input maior">
                <label for="escolaridade">Escolaridade</label>
                <select name="escolaridade" id="escolaridade">
                    <option value=""></option>
                    <option value="1">Superior completo</option>
                    <option value="2">Superior incompleto</option>
                    <option value="3">Ensino médio completo</option>
                    <option value="4">Ensino médio incompleto</option>
                    <option value="5">Ensino fundamental completo</option>
                    <option value="6">Ensino fundamental incompleto</option>
                </select>
            </div>
            <div class="divisao input maior">
                <label for="situacaoAtual">Situação atual</label>
                <select name="situacaoAtual" id="situacaoAtual">
                    <option value=""></option>
                    <option value="1">Estudante</option>
                    <option value="2">Desempregado</option>
                    <option value="3">Empregado</option>
                    <option value="4">Aposentado</option>
                </select>
            </div>
            <div class="input_double">
                <div class="btn btn_voltar"><img src={iconSeta} alt=""/><a href="../html/Cadastro1.html">VOLTAR</a></div>
                <button class="btn btn_cadastrar">CADASTRAR<img src={iconOk} alt=""/></button>
            </div>
        </form>
        </section>
        </>
    );
}

export default CadastrarPt2;
