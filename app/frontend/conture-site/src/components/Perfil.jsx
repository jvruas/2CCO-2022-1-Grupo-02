import estrela from '../html-css-template/imagens/star16.svg';
import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';
import fotoCapa from '../html-css-template/imagens/capa-fundo.jpg';
import '../html-css-template/css/Perfil.css'
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";
import apiProdutos from "../apiProduto.js";
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';


function Perfil() {

    const months = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    const navegar = useNavigate();

    const [usuario, setUsuario] = useState([]);
    const [endereco, setEndereco] = useState([]);
    const [nota, setNota] = useState([]);

    /* Puxa as informações do usuário logado */
    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`/${idUsuario}`).then((usuarioResposta) => {
            try {
                console.log(usuarioResposta.data)
                setUsuario(usuarioResposta.data)
                fetch(`https://viacep.com.br/ws/${usuarioResposta.data.cep}/json/`)
                    .then(res => res.json()).then(data => {
                        setEndereco(data)
                    })
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    /* Puxa as fotos do usuário logado */
    useEffect(() => {
        let param = sessionStorage.getItem('logado');
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`${idUsuario}/imagem?tipoImagem=P`,
            { responseType: 'blob' }).then((respostaImg) => {
                let imgUrl = URL.createObjectURL(respostaImg.data)
                document.getElementById("img_perfil").src = imgUrl;
                sessionStorage.setItem('fotoUsuario', "Sim")
            }).catch((error) => {
                if (param == "OK") {
                    document.getElementById("img_perfil").src = `${fotoLogado}`;
                    sessionStorage.setItem('fotoUsuario', "Não")
                }
            })

        apiUsuario.get(`${idUsuario}/imagem?tipoImagem=B`,
            { responseType: 'blob' }).then((respostaImg) => {
                let imgUrl = URL.createObjectURL(respostaImg.data)
                document.getElementById("img_banner").src = imgUrl;
                sessionStorage.setItem('fotoBanner', "Sim")
            }).catch((error) => {
                if (param == "OK") {
                    document.getElementById("img_banner").src = `${fotoCapa}`;
                    sessionStorage.setItem('fotoBanner', "Não")
                }
            })
    }, [])

    /* Puxa as avaliações do usuário logado */
    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`avaliacao/stats?idDoador=${idUsuario}`).then((resposta) => {
            try {
                setNota(resposta.data)
            } catch (error) {
                //console.log(error)
            }
        })
    }, [])

    
    function formatacaoId(id) {
        if (id < 10) {
            return "#000" + id;
        } else if (id < 100) {
            return "#00" + id;
        } else if (id < 1000) {
            return "#0" + id;
        } else {
            return "#" + id;
        }
    }

    /* Muda a foto do banner */
    // useEffect(() => {

    //         let idUsuario = sessionStorage.getItem('idUsuarioLogado');
    //         var fotoBanner = document.getElementById("fotoBanner");
    //         let formData = new FormData();
    //         formData.append("file", fotoBanner.files[0]);

    //         let config = {
    //             headers: {
    //                 'Content-Type': 'multipart/form-data'
    //             },
    //         }

    //         if (sessionStorage.getItem('fotoBanner') == "Sim") {
    //             apiUsuario.post(`/${idUsuario}/imagem?tipoImagem=B`, formData, config)
    //                 .then((respostaImg) => {
    //                     try {
    //                         document.location.reload(true)
    //                     } catch (error) {
    //                         console.log(error)
    //                     }
    //                 })
    //                 .catch((error) => {
    //                     document.location.reload(true)
    //                 })
    //         } else {
    //             apiUsuario.patch(`/${idUsuario}/imagem?tipoImagem=B`, formData, config)
    //                 .then((respostaImg) => {
    //                     try {
    //                         document.location.reload(true)
    //                     } catch (error) {
    //                         console.log(error)
    //                     }
    //                 })
    //                 .catch((error) => {
    //                     document.location.reload(true)
    //                 })
    //         }
        
    // })

    let dataCad = new Date(usuario.dataCadastro);

    return (
        <>
            <div id="perfil">
                <div id="perfil_imagem">
                    <label htmlFor="fotoBanner"><img src={fotoCapa} alt="" id="img_banner" /></label>
                    <input type="file" accept="image/*" name="fotoBanner" id="fotoBanner" />
                </div>
                <div id="perfil_inf">
                    <div id="perfil_informacao">
                        <div id="perfil_conteudo">
                            <div id="foto">
                                <label htmlFor="fotoPerfilUsuario"><img src={fotoLogado} alt="" id="img_perfil" /></label>
                                <input type="file" accept="image/*" name="fotoPerfilUsuario" id="fotoPerfilUsuario" />
                            </div>
                            <div id="perfil_texto">
                                <div class="inf">
                                    <b>
                                        <p>{usuario.nome}</p>
                                    </b>
                                    <p>{formatacaoId(usuario.idUsuario)}</p>
                                </div>
                                <div class="inf_cidade_data">
                                    <p>{endereco.localidade} - {endereco.uf}</p>
                                    <p>Desde {months[dataCad.getMonth()]}/{dataCad.getFullYear()}</p>
                                </div>
                                <div>
                                    <img src={estrela} alt="Estrela de avaliação" />
                                    <p className="nota_avaliacao">{nota.mediaAvaliacoes == undefined ? "5.0" : nota.mediaAvaliacoes}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Perfil;