import '../../html-css-template/css/pop-up/PopUpDeletar.css';
import fechar from "../../html-css-template/imagens/x-lg1.svg";
import { Link, useNavigate } from "react-router-dom";
import apiProdutos from '../../apiProduto';
import { useState, useEffect } from "react";

function PopUpDeletar(props) {

    const navegar = useNavigate();

    
    const [produto, setProduto] = useState([]);
    

    function handleSubmit() {
        let prod = sessionStorage.getItem('idProdutoDoacao');
        let doador = sessionStorage.getItem('fkDoador');
        apiProdutos.delete(`/${prod}?idDoador=${doador}`).then((produtoResposta) => {
            try {
                console.log(produtoResposta)

                setProduto(produtoResposta)
                navegar("/disponivel-pessoal");
            } catch (error) {
                console.log(error)
            }
        })
    }
    
        // let idProduto = sessionStorage.getItem('idProduto');
        // apiProdutos.delete(`/${produto}?idDoador=${doador}`).then((produtoResposta) => {
        //     try {
        //         console.log(produtoResposta)
        //         setProduto(produtoResposta)
        //         navegar("/disponivel-pessoal");
        //     } catch (error) {
        //         console.log(error)
        //     }
        // })
   
    


    return (
        <>
            <section id='deletar'>
                <div className='div_conteiner'>
                    <div className='div_sup_deletar'><p></p><Link to='/disponivel-pessoal'><img src={fechar} alt="" /></Link></div>
                    <div className='div_deletar'><b>Confirmar para deletar equipamento</b>
                    <div>Você realmente deseja deletar a publicação de doação referente ao equipamento</div>
                    </div>
                       
                    <div className='div_inf_deletar'>
                        <div className='div_btn'><Link to='/disponivel-pessoal'><button className='btn_confirmar' onClick={(()=> {handleSubmit()})}>Confirmar</button>
                        </Link></div>
                    </div>

                </div>
            </section>
        </>
    )
}

export default PopUpDeletar;