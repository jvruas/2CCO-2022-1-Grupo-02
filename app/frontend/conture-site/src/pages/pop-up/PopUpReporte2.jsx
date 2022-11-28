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
                {/* <div className='div_meio_filtro'>
                    <div>
                        <input type="checkbox" name="horns" id='0' onClick={() => setIdSelecionado(0)}/>
                        <label for="horns">Todos</label></div>
                    <div><input type="checkbox" name="horns" id='1' onClick={() => setIdSelecionado(1)}/>
                        <label for="horns">Celular</label></div>
                    <div><input type="checkbox" name="horns" id='2' onClick={() => setIdSelecionado(2)}/>
                        <label for="horns">Computador</label></div>
                    <div><input type="checkbox" name="horns" id='3' onClick={() => setIdSelecionado(3)}/>
                        <label for="horns">Headset</label></div>
                    <div><input type="checkbox" name="horns" id='4' onClick={() => setIdSelecionado(4)}/>
                        <label for="horns">Notebook</label></div>
                        <div><input type="checkbox" name="horns" id='5' onClick={() => setIdSelecionado(5)}/>
                        <label for="horns">Tablet</label>

<button className='btn_filtrar'>Confirmar</button>
                        </div>
                </div> */}

                <div className='div_inf'><button className='btn_filtrar' onClick={(() => {handleSubmit()})}>Confirmar</button></div>
            </div>
            </section>
        </>
    )
}

export default PopUpFiltro;