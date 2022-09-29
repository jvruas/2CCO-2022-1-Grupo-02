import logo from '../html-css-template/imagens/logo-conture.png'
import foto from '../html-css-template/imagens/foto.jpg';
import estrela from '../html-css-template/imagens/Star 16.svg';
import imgPerfil from '../html-css-template/imagens/imagem_fundo.jpg';
import '../html-css-template/css/Perfil.css'
import report from '../html-css-template/imagens/report.svg'
import { Link } from "react-router-dom";

function PerfilSimples() {

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
                                            <p>Yan</p>
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
                        <Link to="/popup-reporte" ><img src={report} alt="" className='img_reporte'/></Link>
                        </div>
                    </div>
                </div>
        </>
    );
}

export default PerfilSimples;