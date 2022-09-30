import estrela from '../html-css-template/imagens/Star 7.svg';
import foto from '../html-css-template/imagens/foto.jpg';
import '../html-css-template/css/avaliacao.css'

function Avaliacao() {

    return (
        <>
      <div class="avaliacao">
        <div class="avaliacao_sup">
            <div class="container_perfil">
                <div class="ava_esq">
                    <img className='' src={foto} alt=""/>
                    <p>5.0</p>
                </div>
                <div class="ava_dir">
                    <div class="informacaoes">
                        <p>Cleiton</p>
                        <p>São Paulo - SP</p>
                    </div>i
                    <div class="estrela">
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                        <img src={estrela} alt=""/>
                    </div>

                </div>
            </div>
            <p class="data">25/11/2021</p>
        </div>
        <div class="avaliação_inf">
            <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Enim velit est tempora
                aperiam soluta ullam
                incidunt, nulla, voluptate, maxime molestiae recusandae temporibus veniam laudantium eum ea in. Atque,
                sunt
                facere.
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eligendi, rem aut provident exercitationem
                ipsum
                
            </p>
        </div>
    </div>
        </>
    );
}

export default Avaliacao;