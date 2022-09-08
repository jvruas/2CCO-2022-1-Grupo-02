import '../html-css-template/css/Menu.css'
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function MenuHistorico() {
    return (
        <div id="menu">
            
                <div className='sub-menu'>
                <Link to="/disponivel"><p>Disponíveis</p></Link>
                </div>
                <div className='sub-menu'>
                    <b><p className='text-bold'>Histórico</p></b>
                </div>
                <div className='sub-menu'>
                    <Link to="/avaliacao"><p>Avaliações</p></Link>
                </div>
        </div>
    );
}
export default MenuHistorico;