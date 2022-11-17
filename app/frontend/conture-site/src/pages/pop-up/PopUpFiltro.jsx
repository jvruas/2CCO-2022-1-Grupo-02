import '../../html-css-template/css/pop-up/PopUpFiltro.css';
import fechar from "../../html-css-template/imagens/x-lg1.svg";
import { Link } from "react-router-dom";
import { useState } from 'react';

function PopUpFiltro() {

    const [idSelecionado, setIdSelecionado] = useState(0);

    return (
        <> <section id='filtro'>
            <div className='div_filtro'>
                <div className='div_sup_filtro'><p>Filtrar Produto</p><Link to={`/disponivel-pessoal/${idSelecionado}`}><img src={fechar} alt="" /></Link></div>
                <div className='div_meio_filtro'>
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
                        <label for="horns">Tablet</label></div>
                </div>
                <div className='div_sup'><button className='btn_filtrar'>Filtrar</button></div>
            </div>
            </section>
        </>
    )
}

export default PopUpFiltro;