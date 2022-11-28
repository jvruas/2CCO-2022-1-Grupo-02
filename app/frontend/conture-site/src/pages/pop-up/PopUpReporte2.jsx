import '../../html-css-template/css/pop-up/PopUpFiltro.css';
import fechar from "../../html-css-template/imagens/x-lg1.svg";
import { Link,useNavigate } from "react-router-dom";
import { useState } from 'react';
import apiUsuario from '../../apiUsuario';

function PopUpFiltro() {

    const [reporte, setReporte] = useState(0);
    const navegar = useNavigate();

    let rep = sessionStorage.getItem('reporte');
    let remetente = sessionStorage.getItem("idUsuarioLogado")
    let reportado = sessionStorage.getItem("reportado")
  

    function handleSubmit() {
        
        apiUsuario.post(`/reporte`,{ 
            fkReportador:remetente,
            fkReportado:reportado,
            fkTipoReporte:rep
        }).then((resposta) => {
            try {
                console.log(resposta.data)
                setReporte(resposta.data)
                navegar("/disponivel")
            } catch (error) {
                console.log(error)
            }
        })
    }


    return (
        <> <section id='filtro'>
            <div className='div_filtro'>
                <div className='div_sup_filtro'><p>Confirmar reporte</p><Link to={`/disponivel `}><img src={fechar} alt="" /></Link></div>
                <div className='div_inf'><button className='btn_filtrar' onClick={(() => {handleSubmit()})}>Confirmar</button></div>
            </div>
            </section>
        </>
    )
}

export default PopUpFiltro;