import logo from '../html-css-template/imagens/logo-conture.png'
import foto from '../html-css-template/imagens/foto.jpg';
import estrela from '../html-css-template/imagens/Star 16.svg';
import imgPerfil from '../html-css-template/imagens/imagem_fundo.jpg';
import '../html-css-template/css/Perfil.css'
import report from '../html-css-template/imagens/report.svg'
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";
    

function PerfilSimples() {

    const months = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    const navegar = useNavigate();

    const [usuario, setUsuario] = useState([]);
    const [endereco, setEndereco] = useState([]);
    // const [usuarioImg, setUsuarioImg] = useState([]);

    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`/${idUsuario}`).then((usuarioResposta) => {
            try {
                console.log(usuarioResposta.data)
                setUsuario(usuarioResposta.data)
                fetch(`https://viacep.com.br/ws/${usuarioResposta.data.cep}/json/`)
                .then(res => res.json()).then(data => {
                    console.log(data)
                    setEndereco(data)
                })
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    
  let dataCad = new Date(usuario.dataCadastro);
    

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
                                    <img src={foto} alt="" />
                                </div>
                                <div id="perfil_texto">
                                    <div class="inf">
                                        <b>
                                            <p>{usuario.nome}</p>
                                        </b>
                                        <p>#0{usuario.idUsuario}</p>
                                    </div>
                                    <div class="inf">
                                        <p>{endereco.localidade} - {endereco.uf}</p>
                                        <p>Desde {months[dataCad.getMonth()]}/{dataCad.getFullYear()}</p>
                                    </div>
                                    <div>
                                        <img src={estrela} alt="" />
                                        <p>{usuario.avaliacao}</p>
                                    </div>
                                </div>
                            </div>
                        <Link to="/popup-reporte" ><img src={report} alt="" className='img_reporte'/></Link>
                        </div>
                    </div>
                </div>
        </>
    );
}

export default PerfilSimples;