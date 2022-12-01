// import '../html-css-template/css/Style.css' 
import estrela from '../html-css-template/imagens/star16.svg';
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';
import aceitar from '../html-css-template/imagens/accept.svg';
import rejeitar from '../html-css-template/imagens/reject.svg';
import '../html-css-template/css/CardMatch.css'
import apiProdutos from '../apiProduto';
import { useNavigate } from 'react-router-dom';
// import Header from './headerMatch';




function CardMatch(props) {

    const navegar = useNavigate();   

    function aceitarMatch(){
        apiProdutos.patch(`26?idDoador=6&idDonatario=7`).then((resposta) => {
            // sessionStorage.setItem("produtoAvaliado","23")
            navegar("/popup-avaliacao")
        }).catch((error) => { 
                console.log(error)
         })

    }


    function retirarMatch() {
        apiProdutos.delete(`28/match?idDonatario=7`,
          {
            headers: {
              'Content-Type': 'application/json'
            }
          }).then((resposta) => {
            //console.log(resposta.status)
            navegar("/")
            // document.location.reload(true)
          }).catch((error) => {
            console.log(error)
          })
      }

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
                            <button className='btn_aceitar' onClick={aceitarMatch}>ACEITAR</button>
                            <button className='btn_recusar' onClick={retirarMatch}>RECUSAR</button>
                            </div>
                    

                </div>
            </div>
        </>
    );

}
export default CardMatch;