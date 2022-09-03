import banner from "../html-css-template/imagens/feed-banner.png"
import '../html-css-template/css/Style.css'

function Anuncio() {
    return(
        <div className="container-banner">
            <img src={banner}/>
        </div>
    );
}

export default Anuncio