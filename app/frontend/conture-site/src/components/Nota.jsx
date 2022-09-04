
import estrela from '../html-css-template/imagens/Star 3.svg';
import '../html-css-template/css/Circulo.css'

function Nota() {

    return (
        <>
            <body>
            <div id="circulo">
        <div class="circulo_informacoes">
            <p id="nota">4,6</p>
            <div class="estrelas_maior">
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
                <img src={estrela} alt=""/>
            </div>
            <p id="avaliacoes">3 Avaliações no total</p>
        </div>
    </div>
            </body>
        </>
    );
}

export default Nota;