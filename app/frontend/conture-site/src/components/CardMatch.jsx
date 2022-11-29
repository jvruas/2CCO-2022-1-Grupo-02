// import '../html-css-template/css/Style.css' 
import estrela from '../html-css-template/imagens/star16.svg';
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';
import aceitar from '../html-css-template/imagens/accept.svg';
import rejeitar from '../html-css-template/imagens/reject.svg';
import '../html-css-template/css/CardMatch.css'
// import Header from './headerMatch';

function CardMatch() {
    return (
        <>
            <div className='div_match'>
                <div className='perfil_inf'>
                    <div className='div_imagem'>
                        <img src={fotoDeslogado} alt="" />
                    </div>
                    <div className='div_informacao'>
                        <div className='inf_sup'><b>
                            <p>Igor</p>
                        </b></div>
                        <div className='inf_meio'><p>São Paulo - SP</p>
                            <p>10/08/21</p></div>
                        <div className='div_inf'><img src={estrela} alt="" />
                            <p>3.0</p></div>
                    </div>

                    {/* <div classname="foto_match">
                                    <img src={fotoDeslogado} alt="" />
                                </div>
                                <div classname='perfil_texto_match'>
                                <div classname="inf_match">
                                        <b>
                                            <p>Igor</p>
                                        </b>
                                    </div>
                                    <div classname='inf'>
                                        <p>São Paulo - SP</p>
                                        <p>10/08/21</p>
                                    </div>
                                    <div className=''>
                                        <img src={estrela} alt="" />
                                        <p>3.0</p>
                                    </div>
                                </div> */}

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
export default CardMatch;