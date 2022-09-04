import estrela from '../html-css-template/imagens/Star 7.svg';
import estrela from '../html-css-template/imagens/foto.jpg';
import '../html-css-template/css/Circulo.css'

function avaliacao() {

    return (
        <>
      <div class="avaliacao">
        <div class="avaliacao_sup">
            <div class="container_perfil">
                <div class="ava_esq">
                    <img src={foto} alt=""/>
                    <p>5.0</p>
                </div>
                <div class="ava_dir">
                    <div class="informacaoes">
                        <p>Cleiton</p>
                        <p>São Paulo - SP</p>
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
                fugiat repellendus ratione ex facere quasi quod illum labore impedit ea cupiditate adipisci dignissimos
                veniam amet!
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga consectetur architecto impedit quisquam?
                Aliquid, ab suscipit autem ea ad odio modi eos obcaecati voluptates incidunt, ipsum corrupti culpa ipsam
                repudiandae.
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Eum provident dolorum explicabo ratione,
                recusandae perspiciatis, repellat quas unde magni aspernatur ipsam. In optio ipsa amet architecto
                blanditiis doloremque deleniti quos?
                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Perferendis architecto consequatur nulla
                necessitatibus qui cum culpa et vero molestiae quisquam porro nemo accusamus perspiciatis aperiam quo
                obcaecati dolorem, corrupti quasi?
            </p>
        </div>
    </div>
        </>
    );
}

export default Nota;