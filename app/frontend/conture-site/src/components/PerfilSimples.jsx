import logo from '../html-css-template/imagens/logo-conture.png'
import foto from '../html-css-template/imagens/foto.jpg';
import estrela from '../html-css-template/imagens/star16.svg';
import imgPerfil from '../html-css-template/imagens/imagem_fundo.jpg';
import '../html-css-template/css/Perfil.css'
import report from '../html-css-template/imagens/report.svg'
import { Link, useNavigate } from "react-router-dom";
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
    const [usuarioImg, setUsuarioImg] = useState([]);

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

    useEffect(() => {
        let idUsuarioPerfil = sessionStorage.getItem('idDoador');
        apiProdutos.get(`avaliacao/stats?idDoador=${idUsuarioPerfil}`).then((resposta) => {
            try {
                console.log("teste",resposta.data)
                setNota(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])



    useEffect(() => {
        let param = sessionStorage.getItem('logado');
        let idUsuarioPerfil = sessionStorage.getItem('idDoador');
        if(param == "OK"){
            document.getElementById("nome_usuario").innerHTML = `${usuarioPerfil.nome}`; 
            document.getElementById("img_perfil").src = `http://localhost:8080/usuarios/${idUsuarioPerfil}/imagem?tipoImagem=P`;
            document.getElementById("img_banner").src = `http://localhost:8080/usuarios/${idUsuarioPerfil}/imagem?tipoImagem=B`;  
        }else{
            document.getElementById("nome_usuario").innerHTML = "Usuário";  
            document.getElementById("img_perfil").src = `${fotoDeslogado}`;
        }
    })
    
    function RedirectReport(){
        
        sessionStorage.setItem("reportado",usuarioPerfil.idUsuario);
        navegar("/popup-reporte");
    }

  let dataCad = new Date(usuarioPerfil.dataCadastro);

    return (
        <>
                <div id="perfil">
                    <div id="perfil_imagem">
                        <img src={imgPerfil} alt="" id="img_banner"/>
                    </div>
                    <div id="perfil_inf">
                        <div id="perfil_informacao">
                            <div id="perfil_conteudo">
                                <div id="foto">
                                    <img src={fotoDeslogado} alt="" id="img_perfil"/>
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
                                        <p>{nota.mediaAvaliacoes}</p>
                                    </div>
                                </div>
                            </div>
                        <img src={report} alt="" className='img_reporte' onClick={RedirectReport}/>
                        </div>
                    </div>
                </div>
        </>
    );
}

export default PerfilSimples;