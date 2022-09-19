import '../../html-css-template/css/pop-up/PopUpFiltro.css';
import Filtro from "../../html-css-template/imagens/x-lg 1.svg";

function PopUpFiltro() {
    return (
        <>
            <div className='div_filtro'>
                <div className='div_sup'><p>Filtrar Produto</p><img src={Filtro} alt="" /></div>
                <div className='div_meio'>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Celular</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Computador</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Headset</label></div>
                    
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Mouse</label></div>
                    <div><input type="checkbox" name="horns" />
                        <label for="horns">Notebook</label></div>
                        <div><input type="checkbox" name="horns" />
                        <label for="horns">Tablet</label></div>
                </div>
                <div className='div_sup'><button className='btn_filtrar'>Filtrar</button></div>
            </div>
        </>
    )
}

export default PopUpFiltro;