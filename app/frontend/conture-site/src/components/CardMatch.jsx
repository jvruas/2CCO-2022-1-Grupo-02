import '../html-css-template/css/Style.css' 
import estrela from '../html-css-template/imagens/star16.svg';
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';
import aceitar from '../html-css-template/imagens/accept.svg';
import rejeitar from '../html-css-template/imagens/reject.svg';
import '../html-css-template/css/CardMatch.css'
// import Header from './headerMatch';

function Match() {
    return (
        <>
    <div className='div_match'>
    <div className="perfil_inf">
                        <div classname="perfil_informacao">
                            <div classname="perfil_conteudo">
                                <div classname="foto">
                                    <img src={fotoDeslogado} alt="" id="img_perfil"/>
                                </div>
                                <div classname="perfil_texto">
                                <div classname="inf">
                                        <b>
                                            <p>Igor</p>
                                        </b>
                                       
                                    </div>
                                    <div classname="inf">
                                        <p>São Paulo - SP</p>
                                        <p>10/08/21</p>
                                    </div>
                                    <div>
                                        <img src={estrela} alt="" />
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div id='div_resultado'>
                        <div className='text_match'>Match</div>
                        <div className='div_circulo'>
                        <div className='circulo'><p>90%</p></div>
                        <div className='imagens'>
                        <img src={aceitar} alt="" />
                        <img src={rejeitar} alt="" /></div>
                        </div>

                    </div>
    </div>
        </>
    );

}
export default Match;