import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Cadastro2.css'
import imgEtapa from "../html-css-template/imagens/Etapas (1).png"
import iconSeta from "../html-css-template/imagens/seta 2.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"
import { Link } from "react-router-dom";
import api from "../api.js";
import { useEffect, useState } from "react";
import axios from "axios";

function CadastrarPt2() {
     const [situacao, setSituacao] = useState([]);

     useEffect(() => {
         api.get("/situacao-atual").then((resposta) => {
             console.log(resposta.data)
             setSituacao(resposta.data)
         })
     }, [])

    // React.useEffect(() => {
    //     api.get("/").then((resposta) => 
    //     {setPost(resposta.data);});}, []);

    // const data = ({
    //     email: "igor.picolo@gmail.com",
    //     senha: "123456",
    //     nome: "Igor",
    //     sobrenome: "Picolo",
    //     cpf: "14873883067",
    //     genero: "X",
    //     dataNascimento: "1054336458",
    //     estadoCivil: "$",
    //     telefone: "11927316913",
    //     cep: "76804004",
    //     grauEscolaridade: "E",
    //     fkSituacaoAtual: 1
    // })

    // const handleClick = data => axios.post("http://localhost:8080/usuarios/adicionar", data)
    //     .then(() => {
    //         console.log("Deu certo")
    //     })
    //     .catch(() => {
    //         console.log("Deu errado")
    //     })





    return (
        <>
            <MenuSimples />
            <section className="section-cadastro2">
                <form id="form-cadastro2" action="">
                    <div className="divisao centralizado">
                        <h1>CADASTRO DE USUÁRIO</h1>
                    </div>
                    <div className="divisao centralizado">
                        <img className="imp_progresso" src={imgEtapa} alt="Barra de progresso de cadastro" />
                    </div>
                    <div className="divisao input maior">
                        <label htmlFor="nome">Nome</label>
                        <input type="text" name="nome" />
                    </div>
                    <div className="divisao input maior">
                        <label htmlFor="sobrenome">Sobrenome</label>
                        <input type="text" name="sobrenome" />
                    </div>
                    <div className="input_double">
                        <div className="input menor">
                            <label htmlFor="cpf">CPF</label>
                            <input type="txt" name="cpf" />
                        </div>
                        <div className="input menor">
                            <label htmlFor="dataNasc">Data de nascimento</label>
                            <input type="date" name="dataNasc" />
                        </div>
                    </div>
                    <div className="input_double">
                        <div className="input menor">
                            <label htmlFor="genero">Gênero</label>
                            <select name="genero" id="genero">
                                <option value=""></option>
                                <option value="1">Feminino</option>
                                <option value="2">Masculino</option>
                                <option value="3">Outro</option>
                            </select>
                        </div>
                        <div className="input menor">
                            <label htmlFor="estadoCivil">Estado civil</label>
                            <select name="estadoCivil" id="estadoCivil">
                                <option value=""></option>
                                <option value="1">Solteiro(a)</option>
                                <option value="2">Casado(a)</option>
                                <option value="3">Divorciado(a)</option>
                                <option value="4">Viúvo(a)</option>
                            </select>
                        </div>
                    </div>
                    <div className="input_double">
                        <div className="input menor">
                            <label htmlFor="telefone">Telefone</label>
                            <input type="text" name="telefone" />
                        </div>
                        <div className="input menor">
                            <label htmlFor="cep">CEP</label>
                            <input type="text" name="cep" />
                        </div>
                    </div>
                    <div className="divisao input maior">
                        <label htmlFor="escolaridade">Escolaridade</label>
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
                    <div className="divisao input maior">
                        <label htmlFor="situacaoAtual">Situação atual</label>
                        <select name="situacaoAtual" id="situacaoAtual">
                            <option value=""></option>
                            {
                                situacao.map((valor) => (
                                    <option value={valor.id}>{valor.name}</option>
                                ))
                            }
                        </select>
                    </div>
                    <div className="input_double">
                        <Link to="/cadastro"><div className="btn btn_voltar"><img src={iconSeta} alt="Ícone de voltar" />VOLTAR</div></Link>
                        <button className="btn btn_cadastrar" onClick="">CADASTRAR<img src={iconOk} alt="Ícone de confirmação para cadastrar o usuário" /></button>
                    </div>
                </form>
            </section>
        </>
    );
}

export default CadastrarPt2;
