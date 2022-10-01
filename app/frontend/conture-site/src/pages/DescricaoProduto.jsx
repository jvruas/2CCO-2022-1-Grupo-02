import Header from "../components/Header"
import Footer from "../components/Footer"
import "../html-css-template/css/DescricaoProduto.css"
import CarouselProdutos from "../components/CarouselProdutos"
import Comentarios from "../components/Comentarios"

function DescricaoProduto(){
    return(
        <>
            <Header></Header>
            <div class="container div-crumbs">
                <span class="span-crumbs-dad">
                    <span class="span-crumbs">Produtos</span>
                    <span class="span-crumbs">></span>
                    <span class="span-crumbs">Notebook</span> 
                    <span class="span-crumbs">></span>
                    <span class="span-crumbs"><b>Nome notebook</b></span>
                </span>
            </div>

            
            <CarouselProdutos qtdItens={1}></CarouselProdutos>
            
            <Comentarios></Comentarios>

            <div class="container product-title">
                <span class="span-product-title">
                   <b> Nome Notebook - ahhsahs</b>
                </span>
            </div>
            
            <div className="container">
                <div className="card-descricao">
                </div>
            </div>
        
            <Footer></Footer>
        </>
    )
}

export default DescricaoProduto;