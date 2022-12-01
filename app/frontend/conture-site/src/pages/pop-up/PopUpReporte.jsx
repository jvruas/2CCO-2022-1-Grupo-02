import '../../html-css-template/css/pop-up/PopUpReporte.css';
import seta from "../../html-css-template/imagens/arrow.svg";
import fechar from "../../html-css-template/imagens/x-lg1.svg";
import { Link,useNavigate } from "react-router-dom";
import { useState} from 'react';


function PopUpReporte() {
    const [idSelecionado, setIdSelecionado] = useState(0);
    const navegar = useNavigate();

    function Redirect(event){
        console.log("teste event: ",event)
        // const value = document.getElementById("input_pesq").value;
        // sessionStorage.setItem("reporte", 0.value);
        if(event.target.id != ""){
            
            sessionStorage.setItem("reporte", event.target.id);
            navegar("/popup-reporte-confirma")
        }
        else{
            sessionStorage.setItem("reporte", event.nativeEvent.path[1].id);
            navegar("/popup-reporte-confirma")
        }

    }

    return (
        
        <>
        <section id='report'>
            <div className='div_Report'>
                <div className='div_superior'><p>Reportar</p><Link to={`/disponivel`}><img src={fechar} alt="" /></Link></div>
                <div className='div_meio_reporte'><b><p>Reportar usuário aos admistradores</p></b>
                    <div><p>Informe aos administradores o que há de errado com esse usuário. Não contaremos
                        à pessoa que você a denunciou.</p></div>
                        </div>
                <div className='div_inf_reporte'>
                    <div id='1' onClick={((event) =>{Redirect(event)})}><p>Assédio</p><img src={seta} alt="" /></div >
                    <div id='3' onClick={((event) =>{Redirect(event)})}><p>Informações do perfil</p><img src={seta} alt="" /></div>
                    <div id='4' onClick={((event) =>{Redirect(event)})}><p>Nudez ou atividade sexual</p><img src={seta} alt="" /></div>
                    <div id='5' onClick={((event) =>{Redirect(event)})}><p>Produto falso</p><img src={seta} alt="" /></div>
                    <div id='6' onClick={((event) =>{Redirect(event)})}><p>Spam</p><img src={seta} alt="" /></div>
                </div>

            </div>
            </section>
        </>
    )
}

export default PopUpReporte;