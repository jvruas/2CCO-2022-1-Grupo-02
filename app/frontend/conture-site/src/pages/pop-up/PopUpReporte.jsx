import '../../html-css-template/css/pop-up/PopUpReporte.css';
import seta from "../../html-css-template/imagens/arrow.svg";
import fechar from "../../html-css-template/imagens/x-lg1.svg";
import { Link } from "react-router-dom";



function PopUpReporte() {
    return (
        <>
        <section id='report'>
            <div className='div_Report'>
                <div className='div_superior'><p>Reportar</p><Link to="/disponivel"><img src={fechar} alt="" /></Link></div>
                <div className='div_meio_reporte'><b><p>Reportar usuário aos admistradores</p></b>
                    <div><p>Informe aos administradores o que há de errado com esse usuário. Não contaremos
                        à pessoa que você a denunciou.</p></div>
                        </div>
                <div className='div_inf_reporte'>
                    <div><p>Assédio</p><img src={seta} alt="" /></div>
                    <div><p>Discurso de ódio</p><img src={seta} alt="" /></div>
                    <div><p>Informações do perfil</p><img src={seta} alt="" /></div>
                    <div><p>Nudez ou atividade sexual</p><img src={seta} alt="" /></div>
                    <div><p>Produto falso</p><img src={seta} alt="" /></div>
                    <div><p>Spam</p><img src={seta} alt="" /></div>
                </div>

            </div>
            </section>
        </>
    )
}

export default PopUpReporte;