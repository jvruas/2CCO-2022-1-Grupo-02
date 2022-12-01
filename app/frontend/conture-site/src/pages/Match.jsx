import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuDisponivel"
import '../html-css-template/css/Match.css'
import '../html-css-template/css/CardMatch.css'
import Footer from "../components/Footer"
import Card from "../components/CardMatch"
import Card2 from "../components/CardMatch"
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"
import apiUsuario from "../apiUsuario.js"
import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';
import estrela from '../html-css-template/imagens/star16.svg';

function Match() {

    const [produtos, setProdutos] = useState([]);
    // const [matchs, setmatchs] = useState([]);
    const [usuario, setUsuario] = useState([]);
    var match = [];
   
    const navegar = useNavigate();   

    function aceitarMatch(){
        apiProdutos.patch(`${sessionStorage.getItem("idProdutoAvaliacao")}?idDoador=${sessionStorage.getItem("idUsuarioLogado")}&idDonatario=${sessionStorage.getItem("doadorAval")}`).then((resposta) => {
            // sessionStorage.setItem("produtoAvaliado","23")
            document.getElementById("card-match").style.visibility = "hidden";
            navegar("/popup-avaliacao")
        }).catch((error) => { 
                console.log(error)
         })

    }


    function retirarMatch() {
        apiProdutos.delete(`${sessionStorage.getItem("idProdutoAvaliacao")}/match?idDonatario=${sessionStorage.getItem("doadorAval")}`,
          {
            headers: {
              'Content-Type': 'application/json'
            }
          }).then((resposta) => {
            //console.log(resposta.status)
            document.getElementById("card-match").style.visibility = "hidden";
            navegar("/")
            // document.location.reload(true)
          }).catch((error) => {
            console.log(error)
          })
      }

    // useEffect(() => {
    //     let idDoador = sessionStorage.getItem('idUsuarioLogado');
    //     apiProdutos.get(`disponiveis?idDoador=${idDoador}`).then((resposta) => {
    //         try {
    //             console.log("asfdsdfsdsadaaaaasdadaddas", resposta.data)
    //             for (let i = 0; i < resposta.data.length; i++) {
    //                 apiProdutos.get(`${resposta.data[i].}/match?idDoador=${idDoador}`).then((respostas) => {
    //                     // match.push(respostas.data)


    //                     match[i] = resposta.data
    //                 })
    //             }
    //             console.log("array match:", produtos)
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }, [])


    return (
        <>
            <Header></Header>
            <section className="section-match">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>
                <div className="div_matchs">
                    <div className="text_matchs"><b><p>Matchs</p></b></div>

                    <div className="cards">
                        <div className='div_match' id="card-match">
                            <div className='perfil_inf'>
                                <div className='div_imagem'>
                                    <img src={fotoLogado} alt="" />
                                </div>
                                <div className='div_informacao'>
                                    <div className='inf_sup'><b>
                                        <p>{sessionStorage
                                        .getItem("idDoadorNomeAval")}</p>
                                    </b></div>
                                    <div className='inf_meio'><p>{sessionStorage
                                        .getItem("idDoadorCidadeAval")} - {sessionStorage
                                            .getItem("idDoadorUfAval")}</p>
                                    </div>
                                    <div className='div_inf'><img src={estrela} alt="" />
                                        <p>5.0</p></div>
                                </div>
                            </div>
                            <div className='div_aceitar'>
                                <div className='div_text'>
                                    <div className='text_match'>{sessionStorage
                                            .getItem("modeloAval")}</div>
                                    <div className='text_match'>01/12/2022</div>
                                </div>
                                <div className='imagens'>
                                    <button className='btn_aceitar' onClick={aceitarMatch}>ACEITAR</button>
                                    <button className='btn_recusar' onClick={retirarMatch}>RECUSAR</button>
                                </div>


                            </div>
                        </div>




                    </div>
                </div>

            </section>
            <Footer></Footer>
        </>
    )
}

export default Match;