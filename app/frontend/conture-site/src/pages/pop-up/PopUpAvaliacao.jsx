import '../../html-css-template/css/pop-up/PopUpAvaliacao.css';
import seta from "../../html-css-template/imagens/arrow.svg";
import fechar from "../../html-css-template/imagens/x-lg1.svg";
import { useState, useEffect } from "react";
import apiProdutos from "../../apiProduto.js"

function dataAvaliacao() {
    return {
        valor: "",
        comentario: ""
    }
}

function PopUpAvaliacao() {

    // Função para chamar o endpoint para avaliar doação
    const [valuesAvaliacao, setValuesAvaliacao] = useState(dataAvaliacao)

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesAvaliacao({ ...valuesAvaliacao, [name]: value, })
    }

    function notaAval(nota) {
        valuesAvaliacao.valor = nota;
    }

    function handleSubmit(event) {
        event.preventDefault()

        let json = {
            valor: valuesAvaliacao.valor,
            comentario: valuesAvaliacao.comentario
        }

        console.log(json)
        apiProdutos.post(`1/avaliacao?idDonatario=2`, json, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((resposta) => {
            console.log(resposta.status)
        }).catch((error) => {
            console.log(error)
        })
    }


        return (
            <>
                <section id='avaliacao'>
                    <div className='div_conteiner_ava'>
                        <div className='div_sup_ava'><p>Avalie o processo de doação</p><img src={fechar} alt="" /></div>
                        <div className='div_avaliacao'><b>Avalie o processo de doação e o doador.</b>
                            <div class="estrelas">
                                <input type="radio" id="cm_star-empty" name="fb" value="" checked />
                                <label for="cm_star-1"><i class="fa"></i></label>
                                <input type="radio" id="cm_star-1" name="fb" value="1" onClick={notaAval(1)} />
                                <label for="cm_star-2"><i class="fa"></i></label>
                                <input type="radio" id="cm_star-2" name="fb" value="2" onClick={notaAval(2)} />
                                <label for="cm_star-3"><i class="fa"></i></label>
                                <input type="radio" id="cm_star-3" name="fb" value="3" onClick={notaAval(3)} />
                                <label for="cm_star-4"><i class="fa"></i></label>
                                <input type="radio" id="cm_star-4" name="fb" value="4" onClick={notaAval(4)} />
                                <label for="cm_star-5"><i class="fa"></i></label>
                                <input type="radio" id="cm_star-5" name="fb" value="5" onClick={notaAval(5)} />
                            </div>

                        </div>
                        <div className="div_inf_ava">
                            <div><p>Digite um comentário</p></div>
                            <input type="text" name="comentario" value={valuesAvaliacao.comentario} onChange={handleChangeUser} />

                            <div className="div_btn"><button className='btn_avaliacao' onClick={handleSubmit}>AVALIAR</button></div>
                        </div>

                    </div>
                </section>
            </>
        )
    }

    export default PopUpAvaliacao;