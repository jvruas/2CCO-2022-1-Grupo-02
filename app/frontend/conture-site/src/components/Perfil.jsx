import logo from '../html-css-template/imagens/logo-conture.png'
import foto from '../html-css-template/imagens/foto.jpg';
import estrela from '../html-css-template/imagens/Star 16.svg';
import fotoLogado from '../html-css-template/imagens/foto.jpg';
import fotoDeslogado from '../html-css-template/imagens/icone_pessoa.png';
import imgPerfil from '../html-css-template/imagens/imagem_fundo.jpg';
import '../html-css-template/css/Perfil.css'
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";

function Perfil() {

    const navegar = useNavigate();

    const [usuario, setUsuario] = useState([]);
    // const [usuarioImg, setUsuarioImg] = useState([]);

    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`/${idUsuario}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setUsuario(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    return (
        <>
                <div id="perfil">
                    <div id="perfil_imagem">
                        <img src={imgPerfil} alt="" />
                    </div>
                    <div id="perfil_inf">
                        <div id="perfil_informacao">
                            <div id="perfil_conteudo">
                                <div id="foto">
                                    <img src={fotoDeslogado} alt="" />
                                </div>
                                <div id="perfil_texto">
                                    <div class="inf">
                                        <b>
                                            <p>{usuario.nome}</p>
                                        </b>
                                        <p>#0001</p>
                                    </div>
                                    <div class="inf">
                                        <p>Guarulhos - SP</p>
                                        <p>Desde 10/2021</p>
                                    </div>
                                    <div>
                                        <img src={estrela} alt="" />
                                        <p>5.0</p>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
        </>
    );
}

export default Perfil;