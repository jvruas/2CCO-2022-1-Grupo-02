import Header from "../components/Header"
import Footer from "../components/Footer"
import "../html-css-template/css/DescricaoProduto.css"
import CarouselProdutos from "../components/CarouselProdutos"
import Comentarios from "../components/Comentarios"
import apiProdutos from "../apiProduto"
import apiUsuario from "../apiUsuario"
import { useEffect, useState } from "react";
import apiMensagemGrupo from "../apiMensagemGrupo"


var produtosDoacao = 0;
var produtosDoados = 0;

function DescricaoProduto(){
    const [produto, setProduto] = useState([]);
    const [usuario, setUsuario] = useState([]);
    const [mensagem, setMensagem] = useState([]);

    
    useEffect(() => {
        apiProdutos.get(`/${sessionStorage.getItem("idProduto")}`).then((resposta) => {
            setProduto(resposta.data)
            console.log(resposta.data)
        })

        apiUsuario.get(`/${sessionStorage.getItem("idDoador")}`).then((resposta) => {
            setUsuario(resposta.data)
            console.log(resposta.data)
        })

        apiMensagemGrupo.get(`/${sessionStorage.getItem("idProduto")}`).then((resposta) => {
            setMensagem(resposta.data)
            console.log("teste", resposta.data);
        })

        apiProdutos.get(`/disponiveis?idDoador=${sessionStorage.getItem("idDoador")}`).then((resposta) => {
            console.log(resposta.data)
            for(var i=0;i<resposta.data.length;i++){
                if(resposta.data[i].dataConclusao==null){
                    produtosDoacao++;
                }
                else{
                    produtosDoados++;
                }
            }
        })
    },[])


    return(
        <>
            <Header></Header>
            <div class="container div-crumbs">
                <span class="span-crumbs-dad">
                    <span class="span-crumbs">Produtos</span>
                    <span class="span-crumbs">></span>
                    <span class="span-crumbs">{produto.categoriaProduto}</span> 
                    <span class="span-crumbs">></span>
                    <span class="span-crumbs"><b>{produto.nome}</b></span>
                </span>
            </div>

            {
            // console.log("mensagemPrincipal", mensagem[0][0].mensagem)
            
            }

            {
                mensagem.map((itemMensagem) => (
                    console.log("mensagemPrincipalDoMap", itemMensagem[0].mensagem)
                ))
            }

{
                mensagem.map((itemMensagem) => (
                    console.log("mensagemRespostaDoMap", itemMensagem)
                ))
            }
            
            <CarouselProdutos qtdItens={1}></CarouselProdutos>
            
            <Comentarios></Comentarios>

            <div class="container product-title">
                <span class="span-product-title">
                   <b>{produto.nome}</b>
                </span>
            </div>
            
            <div className="container more-info-description-product">
                <div className="block-left">
                    <div className="card-description">
                        <div className="title-description">
                            <b>Descrição</b>
                        </div>
                        <div className="text-description">
                            <p>{produto.descricao}</p>
                        </div>
                    </div>
                    <div className="button-i"> 
                    </div>
                </div>
                <div className="card-info-user">
                    <div className="div-name-user">
                        <div className="infos-user">
                            <div className="photo-user">
                            
                            </div>
                            <b className="name-user">{usuario.nome}</b>
                        </div>
                    </div>
                    <div className="div-location-user">

                    </div>
                    <div className="div-numbers-user">
                        <div className="div-produtos-doacao">
                            <h3>Para doação</h3>
                            <h2>{produtosDoacao}</h2>
                        </div>
                        <div className="div-produtos-doados">
                            <h3>Doados</h3>
                            <h2>{produtosDoados}</h2>
                        </div>
                        <div className="div-cadastro">
                            <h3>Cadastrado desde</h3>
                            {/* <h2>{usuario.dataCadastro.substring(0,10)}</h2> */}
                        </div>
                    </div>
                </div>
            </div>
        
            <Footer></Footer>
        </>
    )
}

export default DescricaoProduto;