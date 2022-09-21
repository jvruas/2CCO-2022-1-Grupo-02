import '../../html-css-template/css/pop-up/PopUpReporte.css';
import seta from "../../html-css-template/imagens/arrow.svg";
import fechar from "../../html-css-template/imagens/x-lg 1.svg";



function PopUpReporte() {
    return (
        <>
            <div className='div_Report'>
                <div className='div_sup'><p>Reportar</p><img src={fechar} alt="" /></div>
                <div className='div_meio'><b><p>Reportar usuário aos admistradores</p></b>
                    <div><p>Informe aos administradores o que há de errado com esse usuário. Não contaremos
                        à pessoa que você a denunciou.</p></div>
                        </div>
                <div className='div_inf'>
                    <div><p>Assédio</p><img src={seta} alt="" /></div>
                    <div><p>Discurso de ódio</p><img src={seta} alt="" /></div>
                    <div><p>Informações do perfil</p><img src={seta} alt="" /></div>
                    <div><p>Nudez ou atividade sexual</p><img src={seta} alt="" /></div>
                    <div><p>Produto falso</p><img src={seta} alt="" /></div>
                    <div><p>Spam</p><img src={seta} alt="" /></div>
                </div>

            </div>
        </>
    )
}

export default PopUpReporte;