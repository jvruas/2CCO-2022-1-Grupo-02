import logo from '../html-css-template/imagens/logo-conture.png'
import foto from '../html-css-template/imagens/foto.jpg';
import estrela from '../html-css-template/imagens/Star 16.svg';
import '../html-css-template/css/Perfil.css'

function Perfil() {

    return (
        <>
                <div id="perfil">
                    <div id="perfil_imagem">
                        <img src="imagem_fundo.jpg" alt="" />
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
                            <button>
                                CADASTRAR PRODUTO
                            </button>
                        </div>
                    </div>
                </div>
        </>
    );
}

export default Perfil;