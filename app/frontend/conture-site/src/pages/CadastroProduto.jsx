import Header from "../components/Header";
import Footer from "../components/Footer";
import Perfil from "../components/Perfil";
import MenuPerfil from "../components/MenuHistorico";
import '../html-css-template/css/CadastroProduto.css';
import { useState, useEffect } from "react";
import apiProduto from '../apiProduto';

function CadastroProduto() {

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
                    <div className="ca-campos-produto">
                        <div className="ca-campos-fotos">
                            <label>Foto</label>
                            <input type="file" name="" id="" />
                            <input type="file" name="" id="" />
                            <input type="file" name="" id="" />
                            <input type="file" name="" id="" />
                            <input type="file" name="" id="" />
                        </div>
                        <div className="ca-campos-info">
                            <div className="ca-campos-info-pUm">
                                <label htmlFor="">Nome <span>*</span></label>
                                <input type="text" />
                                <label htmlFor="">Categoria <span>*</span></label>
                                <select name="" id="">
                                    <option value=""></option>
                                    {
                                        categorias.map((categoria) => (
                                            <option value={categoria.idCategoria}>{categoria.nome}</option>
                                        ))
                                    }
                                </select>
                                <label htmlFor="">Marca <span>*</span></label>
                                <input type="text" />
                                <label htmlFor="">Modelo <span>*</span></label>
                                <input type="text" />
                                <label htmlFor="">Descrição <span>*</span></label>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>
                            <div className="ca-campos-info-pDois">
                                <label htmlFor="">Defeito</label>
                                <input type="checkbox" name="" id="" />
                                <label htmlFor="">Entrega</label>
                                <input type="checkbox" name="" id="" />
                            </div>
                        </div>
                    </div>
                    <div>
                        <div>
                            <label htmlFor="">Preferência de doação<span>*</span></label>
                            <input type="checkbox" name="" id="" />
                        </div>
                        <div>
                            <div>
                                <label htmlFor="">Estado civil <span>*</span></label>
                                <select name="" id=""></select>
                                <label htmlFor="">Escolaridade <span>*</span></label>
                                <select name="" id=""></select>
                                <label htmlFor="">Faixa etária <span>*</span></label>
                                <select name="" id=""></select>
                                <label htmlFor="">Situação atual <span>*</span></label>
                                <select name="" id=""></select>
                            </div>
                            <div>
                                <button>CADASTRAR PRODUTO</button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )

}

export default CadastroProduto;