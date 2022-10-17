import estrela from '../html-css-template/imagens/Star 7.svg';
import foto from '../html-css-template/imagens/foto.jpg';
import '../html-css-template/css/avaliacao.css'


function Avaliacao(props) {

    return (
        <>
      <div class="avaliacao">
        <div class="avaliacao_sup">
            <div class="container_perfil">
                <div class="ava_esq">
                    <img className='' src={foto} alt=""/>
                    <p>{props.nota}</p>
                </div>
                <div class="ava_dir">
                    <div class="informacaoes">
                        <p>{props.donatario}</p>
                        <p>{props.cidade} - {props.estado}</p>
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
            <p class="data">{props.data}</p>
        </div>
        <div class="avaliação_inf">
            <p> {props.comentario}
            </p>
        </div>
    </div>
        </>
    );
}

export default Avaliacao;