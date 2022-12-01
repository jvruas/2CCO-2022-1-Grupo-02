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
import Computador from "../html-css-template/imagens/ideapad.webp"
import FotoPerfil from "../html-css-template/imagens/mulher.jpeg"
import apiViaCep from "../apiViaCep";
import IconLocation from "../html-css-template/imagens/iconLocation.svg";
import FotoPadrao from "../html-css-template/imagens/foto.jpg"
import { Link, useNavigate } from "react-router-dom";
import ErrorBoundary from "./ErrorBoundary"
import ImgProdutos from "../components/ImgProdutos";

var produtosDoacao = 0;
var produtosDoados = 0;

function DescricaoProduto() {
  const [produto, setProduto] = useState([]);
  const [usuario, setUsuario] = useState([]);
  const [mensagem, setMensagem] = useState([]);
  const [cep, setCep] = useState([]);
  const [img, setImg] = useState([]);

  var listaImg= []

  useEffect(() => {

    async function createBlob(base64) {
      let res =    await fetch(base64)
      let myBlob = await res.blob()
    
    return myBlob
  } 

    apiProdutos
      .get(`${sessionStorage.getItem("idProduto")}/imagem-principal`, 
      {responseType: 'blob'}).then((respostaImg) => {
        let imgPUrl = URL.createObjectURL(respostaImg.data)
        console.log("Console oieee1",respostaImg.data)
        // setImg(respostaImg.data)
        // imgProd[0] = respostaImg.data;
        // setImg([imgPUrl]);
        listaImg.push(imgPUrl)
        // sessionStorage.setItem(`fotinha${props.idProduto}`, imgstr)
        // console.log(imgProd);
    }).catch((error) => {
        console.log(error)
  }) 

  let _data = {
    title: "foo",
    body: "bar"
  }

  // fetch(`http://localhost:8081/produtos/${sessionStorage.getItem("idProduto")}/imagem-extra`, {
  //   method: 'GET',
  //   headers: {
  //       'Content-Type': 'application/json;charset=UTF-8'
  //   },
  //      }).then((respostaImg) => respostaImg.json())
  //      .then(json => json[0])
  //     .then((myBlob) => {
  //       const str2blob = new Blob([myBlob], {
  //         type: 'image/jpeg'
  //     });
  //       console.log("Console ja",str2blob)
  //       let imgPUrl = URL.createObjectURL(str2blob)
  //       // setImg(respostaImg.data)
  //       // imgProd[0] = respostaImg.data;
  //       // setImg([imgPUrl]);
  //       listaImg.push(imgPUrl)
  //       // sessionStorage.setItem(`fotinha${props.idProduto}`, imgstr)
  //       // console.log(imgProd);
  //   }).catch((error) => {
  //       console.log(error)
  // })

  setImg(listaImg)

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
        apiViaCep
          .get(`/${resposta.data.cep}/json`)
          .then((response) => {
            setCep(response.data);
            console.log("teste", response.data);
          });
      });

    apiMensagemGrupo
      .get(`/${sessionStorage.getItem("idProduto")}`)
      .then((resposta) => {
        try{
          if(resposta.data.trim()==""){
            var a = [[{
              "idMensagemGrupo": "",
              "mensagem": "Faça aqui sua pergunta ao doador!",
              "data": "2022-06-01T14:50:27.886+00:00",
              "fkUsuario": 900,
              "fkProdutoDoacao": "",
              "fkMensagemPrincipal": null
            },,
            ]]
            setMensagem(a)

          }
          else{
            setMensagem(resposta.data);
          }
        }catch(e){
          setMensagem(resposta.data)
        }
        
        console.log("teste", resposta.data);
      });

    apiProdutos
      .get(`/disponiveis?idDoador=${sessionStorage.getItem("idDoador")}`)
      .then((resposta) => {
        console.log("TESTE", resposta.data);
        produtosDoacao = 0;
        produtosDoados = 0;
        for (var i = 0; i < resposta.data.length; i++) {
          if (resposta.data[i].dataConclusao == null) {
            produtosDoacao++;
          } else {
            produtosDoados++;
          }
        }
      });

    let idUsuario = sessionStorage.getItem('idDoador');

    try {

      // document.getElementById("nome_usuario").innerHTML = `${usuario.nome}`;
      document.getElementById("img_perfil").src = `http://localhost:8080/usuarios/${idUsuario}/imagem?tipoImagem=P`;
      // document.getElementById("img_banner").src = `http://localhost:8080/usuarios/${idUsuario}/imagem?tipoImagem=B`;  

    } catch (error) {
      // document.getElementById("nome_usuario").innerHTML = "Usuário";  
      document.getElementById("img_perfil").src = `${FotoPadrao}`;

    }
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

      <CarouselProdutos
        qtdItens={1}
        content={
          img.map((itemImg) =>
          <ImgProdutos
            image={itemImg}
          >
          </ImgProdutos>
        )
        }
      ></CarouselProdutos>


      <ErrorBoundary>
      <CardComentarios
        comentarios=
        {mensagem.map((itemMensagem) =>
          <Comentarios
            mensagemPrincipal={itemMensagem[0].mensagem}
            mensagemResposta={itemMensagem[1]}
            index={itemMensagem[0].idMensagemGrupo}
            idMensagemPrincipal={itemMensagem[0].fkUsuario}
          >
          </Comentarios>
        )}
      >
      </CardComentarios>

      </ErrorBoundary>

      

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

        <Link to={"/disponivel"}>
          <div className="card-info-user">
            <div className="div-name-user">
              <div className="infos-user">
                <div className="photo-user">
                  <img id="img_perfil" src={FotoPerfil} className="image-description" />
                </div>
                <b className="name-user">{usuario.nome} {usuario.sobreNome}</b>
              </div>
            </div>

            <div className="div-location-user">
              <div className="div-location-uf-img">
                <img className="" src={IconLocation} alt="" />
                <b>{cep.localidade} - {cep.uf}</b>
              </div>
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
                <h2 id="data">{
                  // usuario.dataCadastro.substring(0, 10)
                }</h2>
              </div>
            </div>
          </div>
        </Link>

      </div>

      <Footer></Footer>
    </>
  );
}

export default DescricaoProduto;
