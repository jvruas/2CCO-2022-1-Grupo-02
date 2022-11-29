import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';
import fotoCapa from '../html-css-template/imagens/capa-fundo.jpg';
import estrela from '../html-css-template/imagens/star16.svg';
import imgPerfil from '../html-css-template/imagens/imagem_fundo.jpg';
import '../html-css-template/css/Perfil.css'
import report from '../html-css-template/imagens/report.svg'
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";
import apiProdutos from '../apiProduto';
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';


function PerfilSimples() {

    const months = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];

    const navegar = useNavigate();

    const [usuarioPerfil, setUsuarioPerfil] = useState([]);
    const [endereco, setEndereco] = useState([]);
    const [nota, setNota] = useState([]);

    /* Puxa as informações do usuário doador */
    useEffect(() => {
        let idUsuarioPerfil = sessionStorage.getItem('idDoador');
        apiUsuario.get(`/${idUsuarioPerfil}`).then((usuarioResposta) => {
            try {
                console.log(usuarioResposta.data)
                setUsuarioPerfil(usuarioResposta.data)
                fetch(`https://viacep.com.br/ws/${usuarioResposta.data.cep}/json/`)
                    .then(res => res.json()).then(data => {
                        console.log(data)
                        setEndereco(data)
                    })
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    /* Puxa as fotos do usuário doador */
    useEffect(() => {
        let param = sessionStorage.getItem('logado');
        let idUsuarioDoador = sessionStorage.getItem('idDoador');
        apiUsuario.get(`${idUsuarioDoador}/imagem?tipoImagem=P`,
            { responseType: 'blob' }).then((respostaImg) => {
                let imgUrl = URL.createObjectURL(respostaImg.data)
                document.getElementById("img_perfil").src = imgUrl;
            }).catch((error) => {
                if (param == "OK") {
                    document.getElementById("img_perfil").src = `${fotoLogado}`;
                }
            })

        apiUsuario.get(`${idUsuarioDoador}/imagem?tipoImagem=B`,
            { responseType: 'blob' }).then((respostaImg) => {
                let imgUrl = URL.createObjectURL(respostaImg.data)
                document.getElementById("img_banner").src = imgUrl;
            }).catch((error) => {
                if (param == "OK") {
                    document.getElementById("img_banner").src = `${fotoCapa}`;
                }
            })
    })

    /* Puxa as avaliações do usuario doador */ 
    useEffect(() => {
        let idUsuarioPerfil = sessionStorage.getItem('idDoador');
        apiProdutos.get(`avaliacao/stats?idDoador=${idUsuarioPerfil}`).then((resposta) => {
            try {
                console.log("teste", resposta.data)
                setNota(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])

    /* Redireciona para o popUp de reporte */
    function RedirectReport() {

        sessionStorage.setItem("reportado", usuarioPerfil.idUsuario);
        navegar("/popup-reporte");
    }

    let dataCad = new Date(usuarioPerfil.dataCadastro);

    return (
        <>
            <div id="perfil">
                <div id="perfil_imagem">
                    <img src={imgPerfil} alt="" id="img_banner" />
                </div>
                <div id="perfil_inf">
                    <div id="perfil_informacao">
                        <div id="perfil_conteudo">
                            <div id="foto">
                                <img src={fotoDeslogado} alt="" id="img_perfil" />
                            </div>
                            <div id="perfil_texto">
                                <div class="inf">
                                    <b>
                                        <p>{usuarioPerfil.nome}</p>
                                    </b>
                                    <p>#0{usuarioPerfil.idUsuario}</p>
                                </div>
                                <div class="inf">
                                    <p>{endereco.localidade} - {endereco.uf}</p>
                                    <p>Desde {months[dataCad.getMonth()]}/{dataCad.getFullYear()}</p>
                                </div>
                                <div>
                                    <img src={estrela} alt="" />
                                    <p className="nota_avaliacao">{nota.mediaAvaliacoes == undefined ? "5.0" : nota.mediaAvaliacoes}</p>
                                </div>
                            </div>
                        </div>
                        <img src={report} alt="" className='img_reporte' onClick={RedirectReport} />
                    </div>
                </div>
            </div>
        </>
    );
}

export default PerfilSimples;