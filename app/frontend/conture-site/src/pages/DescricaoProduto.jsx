import Header from "../components/Header";
import Footer from "../components/Footer";
import "../html-css-template/css/DescricaoProduto.css";
import CarouselProdutos from "../components/CarouselProdutos";
import Comentarios from "../components/Comentarios";
import apiProdutos from "../apiProduto";
import apiUsuario from "../apiUsuario";
import { useEffect, useState } from "react";
import apiMensagemGrupo from "../apiMensagemGrupo";
import Cadastro from "./Cadastro";
import CardComentarios from "../components/CardComentarios";

var produtosDoacao = 0;
var produtosDoados = 0;

function DescricaoProduto() {
  const [produto, setProduto] = useState([]);
  const [usuario, setUsuario] = useState([]);
  const [mensagem, setMensagem] = useState([]);

  setTimeout(function dataCadastro(){
    var data = document.getElementById("data")
    data.innerHTML=usuario.dataCadastro.substring(0, 10)
  },500)
  

  useEffect(() => {
    apiProdutos
      .get(`/${sessionStorage.getItem("idProduto")}`)
      .then((resposta) => {
        setProduto(resposta.data);
        console.log(resposta.data);
      });

    apiUsuario
      .get(`/${sessionStorage.getItem("idDoador")}`)
      .then((resposta) => {
        setUsuario(resposta.data);
        console.log(resposta.data);
      });

    apiMensagemGrupo
      .get(`/${sessionStorage.getItem("idProduto")}`)
      .then((resposta) => {
        setMensagem(resposta.data);
        console.log("teste", resposta.data);
      });

    apiProdutos
      .get(`/disponiveis?idDoador=${sessionStorage.getItem("idDoador")}`)
      .then((resposta) => {
        console.log("TESTE",resposta.data);
        produtosDoacao=0;
        produtosDoados=0;
        for (var i = 0; i < resposta.data.length; i++) {
          if (resposta.data[i].dataConclusao == null) {
            produtosDoacao++;
          } else {
            produtosDoados++;
          }
        }
        // produtosDoacao = produtosDoacao/2;
        // produtosDoados = produtosDoados/2;
      });
  }, []);
  

  return (
    <>
      <Header></Header>
      <div className="container-desc-produto div-crumbs">
        <span className="span-crumbs-dad">
          <span className="span-crumbs">Produtos</span>
          <span className="span-crumbs">{">"}</span>
          <span className="span-crumbs">{produto.categoriaProduto}</span>
          <span className="span-crumbs">{">"}</span>
          <span className="span-crumbs">
            <b>{produto.nome}</b>
          </span>
        </span>
      </div>

      {
        // console.log("mensagemPrincipal", mensagem[0][0].mensagem)
      }

      

      <CarouselProdutos qtdItens={1}></CarouselProdutos>

      <CardComentarios
      comentarios=
        {mensagem.map((itemMensagem) =>
          <Comentarios
            mensagemPrincipal={itemMensagem[0].mensagem}  
            mensagemResposta={itemMensagem[1]}
            index={itemMensagem[0].idMensagemGrupo}
            idMensagemPrincipal={itemMensagem[0].idMensagemGrupo}
        >
        </Comentarios>
        )}
      >

      </CardComentarios>
      

      

        {/* <Comentarios
            mensagemPrincipal={mensagem.map((itemMensagem) =>
                itemMensagem[0].mensagem  
            )}
            mensagemResposta = {mensagem.map((itemMensagem) =>
                itemMensagem[1]
              )}
        >
        </Comentarios> */}

      {/* {mensagem.map((itemMensagem) =>
        {if(itemMensagem[1]!=undefined) {
            console.log("DEU CERTO",itemMensagem[1]);
            const comentario= document.getElementsByClassName("card-coment")
            comentario.innerHTML=`${<Comentarios
                mensagemPrincipal={itemMensagem[0].mensagem}
                mensagemResposta = {itemMensagem[1]}
            ></Comentarios>}`
        } else {
            console.log("DEU ERRO",itemMensagem[0]);
            const comentario= document.getElementsByClassName("card-coment")
            comentario.innerHTML=`${<Comentarios
                mensagemPrincipal={itemMensagem[0].mensagem}
                mensagemResposta = "{itemMensagem[1]}"
            ></Comentarios>}`
        }}
      )} */}

      <div className="container-desc-produto product-title">
        <span className="span-product-title">
          <b>{produto.nome}</b>
        </span>
      </div>

      <div className="container-desc-produto more-info-description-product">
        <div className="block-left">
          <div className="card-description">
            <div className="title-description">
              <b>Descrição</b>
            </div>
            <div className="text-description">
              <p>{produto.descricao}</p>
            </div>
          </div>
          <div className="div-button">
          <button className="button-i-a">Tenho Interesse</button>
          </div>
        </div>
        <div className="card-info-user">
          <div className="div-name-user">
            <div className="infos-user">
              <div className="photo-user"></div>
              <b className="name-user">{usuario.nome}</b>
            </div>
          </div>
          <div className="div-location-user"></div>
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
              <h2 id="data">{
              // usuario.dataCadastro.substring(0, 10)
              }</h2>
            </div>
          </div>
        </div>
      </div>

      <Footer></Footer>
    </>
  );
}

export default DescricaoProduto;
