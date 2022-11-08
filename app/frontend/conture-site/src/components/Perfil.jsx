import logo from '../html-css-template/imagens/logo-conture.png'
import foto from '../html-css-template/imagens/foto.jpg';
import estrela from '../html-css-template/imagens/star16.svg';
import fotoLogado from '../html-css-template/imagens/foto.jpg';
// import fotoDeslogado from '../html-css-template/imagens/icone_pessoa.png';
import imgPerfil from '../html-css-template/imagens/imagem_fundo.jpg';
import '../html-css-template/css/Perfil.css'
import { Link, useNavigate } from "react-router-dom";
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
    const [usuarioImg, setUsuarioImg] = useState([]);

    useEffect(() => {
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`/${idUsuario}`).then((usuarioResposta) => {
            try {
                console.log(usuarioResposta.data)
                setUsuario(usuarioResposta.data)
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
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`avaliacao/stats?idDoador=${idUsuario}`).then((resposta) => {
            try {
                console.log(resposta.data)
                setNota(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
    }, [])



    useEffect(() => {
        let param = sessionStorage.getItem('logado');
        let idUsuario = sessionStorage.getItem('idUsuarioLogado');
        if(param == "OK"){
            document.getElementById("nome_usuario").innerHTML = `${usuario.nome}`; 
            document.getElementById("img_perfil").src = `http://localhost:8080/usuarios/${idUsuario}/imagem?tipoImagem=P`;
            document.getElementById("img_banner").src = `http://localhost:8080/usuarios/${idUsuario}/imagem?tipoImagem=B`;  
        }else{
            document.getElementById("nome_usuario").innerHTML = "Usuário";  
            document.getElementById("img_perfil").src = `${fotoDeslogado}`;
        }
    })
    
  let dataCad = new Date(usuario.dataCadastro);
    

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
                                            <p>{usuario.nome}</p>
                                        </b>
                                        <p>#0{usuario.idUsuario}</p>
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
                            
                        </div>
                    </div>
                </div>
        </>
    );
}

export default Perfil;