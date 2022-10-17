import '../../html-css-template/css/pop-up/PopUpDeletar.css';
import fechar from "../../html-css-template/imagens/x-lg 1.svg";
import { Link, useNavigate } from "react-router-dom";
import apiProdutos from '../../apiProduto';

function PopUpDeletar(props) {

    const navegar = useNavigate();
    let produto = sessionStorage.getItem('idProduto');
    function handleSubmit(event) {
        event.preventDefault()
        let param = document.getElementById();
        apiProdutos.delete(`${produto}`, {
    
        }).then((resposta) => {
            sessionStorage.setItem('idProduto', resposta.data)
            console.log(resposta.status)
        })
    }


    return (
        <>
            <section id='deletar'>
                <div className='div_conteiner'>
                    <div className='div_sup_deletar'><p></p><Link to='/disponivel-pessoal'><img src={fechar} alt="" /></Link></div>
                    <div className='div_deletar'><b>Confirmar para deletar publicação</b>
                    <div>Você realmente deseja deletar a publicação de doação referente ao equipamento</div>
                    </div>
                       
                    <div className='div_inf_deletar'>
                        <div className='div_btn'><button className='btn_confirmar' onClick={handleSubmit}>Confirmar</button></div>
                    </div>

                </div>
            </section>
        </>
    )
}

export default PopUpDeletar;