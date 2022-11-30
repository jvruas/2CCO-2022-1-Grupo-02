import Header from "../components/Header";
import Footer from "../components/Footer";
import Perfil from "../components/Perfil";
import MenuPerfil from "../components/MenuHistorico";
import '../html-css-template/css/CadastroProduto.css';
import iconCamera from "../html-css-template/imagens/icon-camera.svg"
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

function dataPreferencia() {
    return {
        faixaEtaria: "",
        estadoCivil: "",
        grauEscolaridade: "",
        fkSituacaoAtual: "",
        fkProdutoDoacao: ""
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
    const [valuesProduto, setValuesProduto] = useState(dataProduto);
    const [valuesPreferencia, setValuesPreferencia] = useState(dataPreferencia);
    var fotoPrincipal = document.getElementById("foto1");
    var foto2 = document.getElementById("foto2");
    var foto3 = document.getElementById("foto3");
    var foto4 = document.getElementById("foto4");
    var foto5 = document.getElementById("foto5");

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesProduto({ ...valuesProduto, [name]: value, })
        console.log(valuesProduto)
    }

    function handleChangePref(event) {
        const { value, name } = event.target
        setValuesPreferencia({ ...valuesPreferencia, [name]: value, })
        console.log(valuesPreferencia)
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

            let formData = new FormData();
            formData.append("file", fotoPrincipal.files[0]);

            let formData2 = new FormData();
            formData2.append("file", foto2.files[0]);

            let formData3 = new FormData();
            formData3.append("file", foto3.files[0]);

            let formData4 = new FormData();
            formData4.append("file", foto4.files[0]);

            let formData5 = new FormData();
            formData5.append("file", foto5.files[0]);

            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            }

            apiProduto.post(`/${resposta.data}/imagem-principal?idDoador=${sessionStorage.getItem('idUsuarioLogado')}`, formData, config)
                .then((resposta) => {
                    console.log(resposta)
                    navegar("/")
                }).catch((error) => {
                    console.log(error)
                })

            apiProduto.post(`/${resposta.data}/imagem-extra?idDoador=${sessionStorage.getItem('idUsuarioLogado')}`, formData2, config)
                .then((resposta) => {
                    console.log(resposta)
                }).catch((error) => {
                    console.log(error)
                })

            apiProduto.post(`/${resposta.data}/imagem-extra?idDoador=${sessionStorage.getItem('idUsuarioLogado')}`, formData3, config)
                .then((resposta) => {
                    console.log(resposta)
                }).catch((error) => {
                    console.log(error)
                })

            apiProduto.post(`/${resposta.data}/imagem-extra?idDoador=${sessionStorage.getItem('idUsuarioLogado')}`, formData4, config)
                .then((resposta) => {
                    console.log(resposta)
                }).catch((error) => {
                    console.log(error)
                })

            apiProduto.post(`/${resposta.data}/imagem-extra?idDoador=${sessionStorage.getItem('idUsuarioLogado')}`, formData5, config)
                .then((resposta) => {
                    console.log(resposta)
                }).catch((error) => {
                    console.log(error)
                })

            let jsonPreferencias = {
                faixaEtaria: valuesPreferencia.faixaEtaria,
                estadoCivil: valuesPreferencia.estadoCivil,
                grauEscolaridade: valuesPreferencia.grauEscolaridade,
                fkSituacaoAtual: valuesPreferencia.fkSituacaoAtual,
                fkProdutoDoacao: resposta.data
            }

            apiUsuario.post("/preferencia", jsonPreferencias, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((resposta) => {
                console.log(resposta.status)
            }).catch((error) => {
                console.log(error)
            })

        }).catch((error) => {
            console.log(error)
            console.log(json)
        })

    }

    function preferenciasDesligadas() {
        var preferencia = document.getElementById("preferencia");

        if (!preferencia.checked) {
            document.getElementById('label-ec').style.color = "#CACACA";
            document.getElementById('EC1').selected = true;
            document.getElementById('EC1').disabled = true;
            document.getElementById('EC2').disabled = true;
            document.getElementById('EC3').disabled = true;
            document.getElementById('EC4').disabled = true;
            document.getElementById('EC5').disabled = true;

            document.getElementById('label-es').style.color = "#CACACA";
            document.getElementById('ES1').selected = true;
            document.getElementById('ES1').disabled = true;
            document.getElementById('ES2').disabled = true;
            document.getElementById('ES3').disabled = true;
            document.getElementById('ES4').disabled = true;
            document.getElementById('ES5').disabled = true;
            document.getElementById('ES6').disabled = true;
            document.getElementById('ES7').disabled = true;
            document.getElementById('ES8').disabled = true;
            document.getElementById('ES9').disabled = true;

            document.getElementById('label-fx').style.color = "#CACACA";
            document.getElementById('FX1').selected = true;
            document.getElementById('FX1').disabled = true;
            document.getElementById('FX2').disabled = true;
            document.getElementById('FX3').disabled = true;
            document.getElementById('FX4').disabled = true;

            document.getElementById('label-sa').style.color = "#CACACA";
            document.getElementById('0').selected = true;
            document.getElementById('0').disabled = true;
            document.getElementById('1').disabled = true;
            document.getElementById('2').disabled = true;
            document.getElementById('3').disabled = true;
            document.getElementById('4').disabled = true;
            document.getElementById('5').disabled = true;
            document.getElementById('6').disabled = true;
            document.getElementById('7').disabled = true;
            document.getElementById('8').disabled = true;
            document.getElementById('9').disabled = true;
            document.getElementById('10').disabled = true;
        }else if(preferencia.checked){
            document.getElementById('label-ec').style.color = "#000000";
            document.getElementById('EC1').disabled = false;
            document.getElementById('EC2').disabled = false;
            document.getElementById('EC3').disabled = false;
            document.getElementById('EC4').disabled = false;
            document.getElementById('EC5').disabled = false;

            document.getElementById('label-es').style.color = "#000000";
            document.getElementById('ES1').disabled = false;
            document.getElementById('ES2').disabled = false;
            document.getElementById('ES3').disabled = false;
            document.getElementById('ES4').disabled = false;
            document.getElementById('ES5').disabled = false;
            document.getElementById('ES6').disabled = false;
            document.getElementById('ES7').disabled = false;
            document.getElementById('ES8').disabled = false;
            document.getElementById('ES9').disabled = false;
            
            document.getElementById('label-fx').style.color = "#000000";
            document.getElementById('FX1').disabled = false;
            document.getElementById('FX2').disabled = false;
            document.getElementById('FX3').disabled = false;
            document.getElementById('FX4').disabled = false;

            document.getElementById('label-sa').style.color = "#000000";
            document.getElementById('0').disabled = false;
            document.getElementById('1').disabled = false;
            document.getElementById('2').disabled = false;
            document.getElementById('3').disabled = false;
            document.getElementById('4').disabled = false;
            document.getElementById('5').disabled = false;
            document.getElementById('6').disabled = false;
            document.getElementById('7').disabled = false;
            document.getElementById('8').disabled = false;
            document.getElementById('9').disabled = false;
            document.getElementById('10').disabled = false;
        }
    }

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
                                    <input type="text" name="nome" id="nome" onChange={handleChangeUser} />
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
                                    <input type="text" name="marca" id="marca" onChange={handleChangeUser} />
                                    <label htmlFor="modelo">Modelo <span>*</span></label>
                                    <input type="text" name="modelo" id="modelo" onChange={handleChangeUser} />
                                    <label htmlFor="descricao">Descrição <span>*</span></label>
                                    <textarea name="descricao" id="descricao" cols="30" rows="10" onChange={handleChangeUser}></textarea>
                                </div>
                                <div className="ca-campos-info-pDois">
                                    <div className="ca-toggle">
                                        <h4>Defeito</h4>
                                        <div class="toggle">
                                            <input type="checkbox" name="defeito" id="defeito" onChange={handleChangeUser} />
                                            <label for="defeito"></label>
                                        </div>
                                    </div>
                                    <div className="ca-toggle">
                                        <h4>Entrega</h4>
                                        <div class="toggle">
                                            <input type="checkbox" name="entrega" id="entrega" onChange={handleChangeUser} />
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
                                    <input type="checkbox" id="preferencia" onChange={preferenciasDesligadas}/>
                                    <label for="preferencia"></label>
                                </div>
                            </div>
                            <div className="ca-campos-preferencia">
                                <div className="ca-campos-pref">
                                    <div>
                                        <label htmlFor="ca-estado-civil" id="label-ec">Estado civil <span>*</span></label>
                                        <select name="ca-estado-civil" id="ca-estado-civi" onChange={handleChangePref}>
                                            <option value="X" selected disabled hidden></option>
                                            <option id="EC1" value="X">Todos(as)</option>
                                            <option id="EC2" value="S">Solteiro(a)</option>
                                            <option id="EC3" value="C">Casado(a)</option>
                                            <option id="EC4" value="D">Divorciado(a)</option>
                                            <option id="EC5" value="V">Viúvo(a)</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label htmlFor="ca-escolaridade" id="label-es">Escolaridade <span>*</span></label>
                                        <select name="ca-escolaridade" id="ca-escolaridade" onChange={handleChangePref}>
                                            <option value="X" selected disabled hidden></option>
                                            <option id="ES1" value="X">Todos(as)</option>
                                            <option id="ES2" value="A">Analfabeto</option>
                                            <option id="ES3" value="I">Educação infantil</option>
                                            <option id="ES4" value="F">Fundamental</option>
                                            <option id="ES5" value="M">Médio</option>
                                            <option id="ES6" value="S">Superior (Graduação)</option>
                                            <option id="ES7" value="P">Pós-graduação</option>
                                            <option id="ES8" value="E">Mestrado</option>
                                            <option id="ES9" value="D">Doutorado</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label htmlFor="ca-faixa" id="label-fx">Faixa etária <span>*</span></label>
                                        <select name="ca-faixa" id="ca-faixa" onChange={handleChangePref}>
                                            <option value="X" selected disabled hidden></option>
                                            <option id="FX1" value="X">Todos(as)</option>
                                            <option id="FX2" value="J">Jovem (até 19 anos)</option>
                                            <option id="FX3" value="A">Adulto (20 até 59 anos)</option>
                                            <option id="FX4" value="I">Idoso (acima de 60 anos)</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label htmlFor="fkSituacaoAtual" id="label-sa">Situação atual <span>*</span></label>
                                        <select name="fkSituacaoAtual" id="fkSituacaoAtual" onChange={handleChangePref}>
                                            <option value="X" selected disabled hidden></option>
                                            <option id="0" value="X">Todos(as)</option>
                                            {
                                                fkSituacaoAtual.map((situacao) => (
                                                    <option id={situacao.idSituacaoAtual} value={situacao.idSituacaoAtual}>{situacao.nome}</option>
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