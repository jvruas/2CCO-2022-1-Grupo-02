import '../html-css-template/css/Menu.css'
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function MenuDisponivel() {
    return (
        <div id="menu">
            
                <div className='sub-menu'>
                    <b><p className='text-bold'>Disponíveis</p></b>
                </div>
                <div className='sub-menu'>
                    <Link to="/HistoricoPessoal"><p>Histórico</p></Link>
                </div>
                <div className='sub-menu'>
                <Link to="/Avaliacao"><p>Avaliações</p></Link>
                </div>
        </div>
    );
}
export default MenuDisponivel;