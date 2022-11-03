import Header from "../components/Header";
import Footer from "../components/Footer";
import Perfil from "../components/Perfil";
import MenuPerfil from "../components/MenuHistorico";
import '../html-css-template/css/CadastroProduto.css';
import iconCamera from "../html-css-template/imagens/Icon-camera.svg"
import iconCheck from "../html-css-template/imagens/icon-check.png"
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiProduto from '../apiProduto';
import apiUsuario from "../apiUsuario.js";


function dataProduto() {
    return {
        nome: "",
        marca: "",
        modelo: "",
        descricao: "",
        defeito: "",
        entrega: "",
        fkCategoriaProduto: "",
        idDoador: ""
    }
}

function CadastroProduto() {

    const navegar = useNavigate();

    /* Função que chama o endPoint que traz todas as categorias */
    const [categorias, setCategoria] = useState([]);

    useEffect(() => {
        apiProduto.get("/todas-categorias").then((resposta) => {
            try {
                console.log(resposta.data)
                setCategoria(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])


    /* Função que chama o endPoint que traz todas as situações atuais */
    const [fkSituacaoAtual, setSituacaoAtual] = useState([]);

    useEffect(() => {
        apiUsuario.get("/situacao-atual").then((resposta) => {
            try {
                console.log(resposta.data)
                setSituacaoAtual(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])


    // Função para chamar o endPoint para cadastrar produto
    const [valuesProduto, setValuesProduto] = useState(dataProduto)

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesProduto({ ...valuesProduto, [name]: value, })
        console.log(valuesProduto)
    }

    function handleSubmit(event) {
        event.preventDefault()

        var defeito = document.getElementById("defeito");
        var entrega = document.getElementById("entrega");

        let json = {
            nome: valuesProduto.nome,
            marca: valuesProduto.marca,
            modelo: valuesProduto.modelo,
            descricao: valuesProduto.descricao,
            defeito: defeito.checked ? true : false,
            entrega: entrega.checked ? true : false,
            fkCategoriaProduto: valuesProduto.fkCategoriaProduto,
            idDoador: sessionStorage.getItem('idUsuarioLogado')
        }

        apiProduto.post("/", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((resposta) => {
            console.log(resposta.data)
            
            // apiProduto.post(`/${resposta.data}/imagem-principal?idDoador=${idDoador}`
            // .then((resposta) => {
            //     console.log(resposta)
            // }).catch((error) => {
            //     console.log(error)
            // })

            // apiProduto.post(`/${resposta.data}/imagem-extra?idDoador=${idDoador}`
            // .then((resposta) => {
            //     console.log(resposta)
            // }).catch((error) => {
            //     console.log(error)
            // })

        }).catch((error) => {
            console.log(error)
            console.log(json)
        })

    }

    // function preferenciasDesligadas(){
    //     var preferencia = document.getElementById("preferencia");
    //     var span = document.querySelectorAll("span");
    //     if(preferencia.checked){
    //         span.style.color = "#000000";
    //     }
    // }

    function mudarImg() {
        var arquivo = document.getElementById("foto1");
        var img = document.getElementById("icon-camera-1");
        if (arquivo.files.length != 0) {
            img.src = iconCheck;
        }
    }

    return (
        <>
            <Header />
            <section id="ca-section">
                <div className="ca-grid">
                    <Perfil />
                    <MenuPerfil />
                    <div className="ca-titulo">
                        <h2>CADASTRO DE PRODUTO</h2>
                    </div>
                    <form action="">
                        <div className="ca-campos-produto">
                            <div className="ca-campos-fotos">
                                <div className="ca-fotos">
                                    <h4>Fotos</h4>
                                    <label htmlFor="foto1" id="label-foto1" onClick={mudarImg}><img src={iconCamera} alt="Ícone de câmera" id="icon-camera-1" /></label>
                                    <input type="file" accept="image/*" name="foto1" id="foto1" />
                                    <div className="ca-quatro-fotos">
                                        <label htmlFor="foto2" id="label-foto2"><img src={iconCamera} alt="Ícone de câmera" /></label>
                                        <input type="file" accept="image/*" name="foto2" id="foto2" />
                                        <label htmlFor="foto3" id="label-foto3"><img src={iconCamera} alt="Ícone de câmera" /></label>
                                        <input type="file" accept="image/*" name="foto3" id="foto3" />
                                        <label htmlFor="foto4" id="label-foto4"><img src={iconCamera} alt="Ícone de câmera" /></label>
                                        <input type="file" accept="image/*" name="foto4" id="foto4" />
                                        <label htmlFor="foto5" id="label-foto5"><img src={iconCamera} alt="Ícone de câmera" /></label>
                                        <input type="file" accept="image/*" name="foto5" id="foto5" />
                                    </div>
                                </div>
                            </div>
                            <div className="ca-campos-info">
                                <div className="ca-campos-info-pUm">
                                    <label htmlFor="nome">Nome <span>*</span></label>
                                    <input type="text" name="nome" id="nome" onChange={handleChangeUser}/>
                                    <label htmlFor="fkCategoriaProduto">Categoria <span>*</span></label>
                                    <select name="fkCategoriaProduto" id="fkCategoriaProduto" onChange={handleChangeUser}>
                                        <option value="" selected disabled hidden></option>
                                        {
                                            categorias.map((categoria) => (
                                                <option value={categoria.idCategoriaProduto}>{categoria.nome}</option>
                                            ))
                                        }
                                    </select>
                                    <label htmlFor="marca">Marca <span>*</span></label>
                                    <input type="text" name="marca" id="marca" onChange={handleChangeUser}/>
                                    <label htmlFor="modelo">Modelo <span>*</span></label>
                                    <input type="text" name="modelo" id="modelo" onChange={handleChangeUser}/>
                                    <label htmlFor="descricao">Descrição <span>*</span></label>
                                    <textarea name="descricao" id="descricao" cols="30" rows="10" onChange={handleChangeUser}></textarea>
                                </div>
                                <div className="ca-campos-info-pDois">
                                    <div className="ca-toggle">
                                        <h4>Defeito</h4>
                                        <div class="toggle">
                                            <input type="checkbox" name="defeito" id="defeito" onChange={handleChangeUser}/>
                                            <label for="defeito"></label>
                                        </div>
                                    </div>
                                    <div className="ca-toggle">
                                        <h4>Entrega</h4>
                                        <div class="toggle">
                                            <input type="checkbox" name="entrega" id="entrega" onChange={handleChangeUser}/>
                                            <label for="entrega"></label>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div className="ca-preferencia">
                            <div className="ca-preferencia-titulo">
                                <h4>Preferência de doação <span>*</span></h4>
                                <div class="toggle">
                                    <input type="checkbox" id="preferencia" />
                                    <label for="preferencia"></label>
                                </div>
                            </div>
                            <div className="ca-campos-preferencia">
                                <div className="ca-campos-pref">
                                    <div>
                                        <label htmlFor="ca-estado-civil">Estado civil <span>*</span></label>
                                        <select name="ca-estado-civil" id="ca-estado-civi">
                                            <option value="" selected disabled hidden></option>
                                            <option value="T">Todos(as)</option>
                                            <option value="S">Solteiro(a)</option>
                                            <option value="C">Casado(a)</option>
                                            <option value="D">Divorciado(a)</option>
                                            <option value="V">Viúvo(a)</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label htmlFor="ca-escolaridade">Escolaridade <span>*</span></label>
                                        <select name="ca-escolaridade" id="ca-escolaridade">
                                            <option value="" selected disabled hidden></option>
                                            <option value="T">Todos(as)</option>
                                            <option value="A">Analfabeto</option>
                                            <option value="I">Educação infantil</option>
                                            <option value="F">Fundamental</option>
                                            <option value="M">Médio</option>
                                            <option value="S">Superior (Graduação)</option>
                                            <option value="P">Pós-graduação</option>
                                            <option value="E">Mestrado</option>
                                            <option value="D">Doutorado</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label htmlFor="ca-faixa">Faixa etária <span>*</span></label>
                                        <select name="ca-faixa" id="ca-faixa">

                                        </select>
                                    </div>
                                    <div>
                                        <label htmlFor="fkSituacaoAtual">Situação atual <span>*</span></label>
                                        <select name="fkSituacaoAtual" id="fkSituacaoAtual">
                                            <option value="" selected disabled hidden></option>
                                            {
                                                fkSituacaoAtual.map((situacao) => (
                                                    <option value={situacao.idSituacaoAtual}>{situacao.nome}</option>
                                                ))
                                            }
                                        </select>
                                    </div>
                                </div>
                                <div className="ca-pref-btn">
                                    <button type="button" onClick={handleSubmit}>CADASTRAR PRODUTO</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
            <Footer />
        </>
    )

}

export default CadastroProduto;