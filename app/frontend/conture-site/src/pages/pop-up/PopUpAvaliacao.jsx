import '../../html-css-template/css/pop-up/PopUpAvaliacao.css';
import seta from "../../html-css-template/imagens/arrow.svg";
import fechar from "../../html-css-template/imagens/x-lg 1.svg";


function PopUpAvaliacao() {
    return (
        <>
            <section id='avaliacao'>
                <div className='div_conteiner'>
                    <div className='div_sup'><p>Avalie o processo de doação</p><img src={fechar} alt="" /></div>
                    <div className='div_avaliacao'><b>Avalie o processo de doação e o doador.</b>
                        <div class="estrelas">
                            <input type="radio" id="cm_star-empty" name="fb" value="" checked />
                            <label for="cm_star-1"><i class="fa"></i></label>
                            <input type="radio" id="cm_star-1" name="fb" value="1" />
                            <label for="cm_star-2"><i class="fa"></i></label>
                            <input type="radio" id="cm_star-2" name="fb" value="2" />
                            <label for="cm_star-3"><i class="fa"></i></label>
                            <input type="radio" id="cm_star-3" name="fb" value="3" />
                            <label for="cm_star-4"><i class="fa"></i></label>
                            <input type="radio" id="cm_star-4" name="fb" value="4" />
                            <label for="cm_star-5"><i class="fa"></i></label>
                            <input type="radio" id="cm_star-5" name="fb" value="5" />
                        </div>

                    </div>
                    <div className='div_inf'>
                        <div><p>Digite um comentário</p></div>
                        <input type="text" />

                        <div className='div_btn'><button className='btn_avaliacao'>Avaliar</button></div>
                    </div>

                </div>
            </section>
        </>
    )
}

export default PopUpAvaliacao;