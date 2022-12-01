import estrela from '../html-css-template/imagens/star7.svg';
import foto from '../html-css-template/imagens/foto.jpg';
import '../html-css-template/css/avaliacao.css'
import { memo } from 'react';


function Avaliacao(props) {

    let dataCad = new Date(props.dataCon);

    
    
    return (
        <>
      <div class="avaliacao">
        <div class="avaliacao_sup">
            <div class="container_perfil">
                <div class="ava_esq">
                    <img className='' src={props.foto} alt=""/>
                    <p>{(props.nota).toFixed(1)}</p>
                </div>
                <div class="ava_dir">
                    <div class="informacaoes">
                        <p><b>{props.donatario}</b></p>
                        <p>{props.idDoador}</p>
                    </div>
                    <div class="estrela">
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                    </div>

                </div>
            </div>
            <p class="data">{dataCad.getDate()}/{dataCad.getMonth()}/{dataCad.getFullYear()}</p>
        </div>
        <div class="avaliação_inf">
            <p> {props.comentario}
            </p>
        </div>
    </div>
        </>
    );
}

export default memo(Avaliacao);
