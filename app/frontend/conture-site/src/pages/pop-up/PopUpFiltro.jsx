import '../../html-css-template/css/pop-up/PopUpFiltro.css';
import fechar from "../../html-css-template/imagens/x-lg 1.svg";
import { Link } from "react-router-dom";

function PopUpFiltro() {



    return (
        <> <section id='filtro'>
            <div className='div_filtro'>
                <div className='div_sup_filtro'><p>Filtrar Produto</p><Link to="/disponivel-pessoal"><img src={fechar} alt="" /></Link></div>
                <div className='div_meio_filtro'>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Todos</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Celular</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Computador</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Headset</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Notebook</label></div>
                        <div><input type="checkbox" name="horns" />
                        <label for="horns">Tablet</label></div>
                </div>
                <div className='div_sup'><button className='btn_filtrar'>Filtrar</button></div>
            </div>
            </section>
        </>
    )
}

export default PopUpFiltro;