import '../html-css-template/css/Menu.css'
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function MenuAvalicao() {
    return (
    <>
        <div id="menu">
            
                <div className='sub-menu'>
                    <Link to="/disponivel"><p>Disponíveis</p></Link>
                </div>
                <div className='sub-menu'>
                    <Link to="/historico-pessoal"><p>Histórico</p></Link>
                </div>
                <div className='sub-menu'>
                   <p className='text-bold'>Avaliações</p>
                </div>
        </div>
        </>
    );
}
export default MenuAvalicao;