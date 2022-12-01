// import '../html-css-template/css/Style.css' 
import estrela from '../html-css-template/imagens/star16.svg';
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';
import aceitar from '../html-css-template/imagens/accept.svg';
import rejeitar from '../html-css-template/imagens/reject.svg';
import '../html-css-template/css/CardMatch.css'
// import Header from './headerMatch';

function CardMatch(props) {
    return (
        <>
            <div className='div_match'>
                <div className='perfil_inf'>
                    <div className='div_imagem'>
                        <img src={props.foto} alt="" />
                    </div>
                    <div className='div_informacao'>
                        <div className='inf_sup'><b>
                            <p>{props.nome}</p>
                        </b></div>
                        <div className='inf_meio'><p>{props.cidade} - {props.uf}</p>
                            </div>
                        <div className='div_inf'><img src={estrela} alt="" />
                            <p>{props.nota}</p></div>
                    </div>
                </div>
                <div className='div_aceitar'>
                    <div className='div_text'>
                    <div className='text_match'>{props.modelo}</div>
                    <div className='text_match'>{props.data}</div>
                    </div>
                        <div className='imagens'>
                            <button className='btn_aceitar'>ACEITAR</button>
                            <button className='btn_recusar'>RECUSAR</button>
                            </div>
                    

                </div>
            </div>
        </>
    );

}
export default CardMatch;