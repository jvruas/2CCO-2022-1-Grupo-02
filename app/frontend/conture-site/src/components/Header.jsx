import logo from '../html-css-template/imagens/logo-conture.png';
import lupa from '../html-css-template/imagens/icon-lupa.png';
import fotoLogado from '../html-css-template/imagens/icon-logado-sem-foto.png';
import fotoDeslogado from '../html-css-template/imagens/imagem-deslogado.png';
import setaBaixo from '../html-css-template/imagens/chevron-down1.svg';
import interesse from '../html-css-template/imagens/interesses.svg';
import mensagem from '../html-css-template/imagens/icon-mensagem.svg';
import notificacao from '../html-css-template/imagens/icon-notificacao.svg';
import adicionarProduto from '../html-css-template/imagens/subtract.svg';
import '../html-css-template/css/Style.css'
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import apiUsuario from "../apiUsuario.js";
import api from '../api';

function Header() {

    const navegar = useNavigate();

    const [usuarioHeader, setUsuarioHeader] = useState([]);

    /* Puxa as informações do usuário logado */
    useEffect(() => {
        let idUsuarioHeader = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`/${idUsuarioHeader}`).then((resposta) => {
            try {
                // console.log(resposta.data)
                setUsuarioHeader(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })

    }, [])

    /* Puxa as fotos do usuário logado */
    useEffect(() => {
        let param = sessionStorage.getItem('logado');
        let idUsuarioHeader = sessionStorage.getItem('idUsuarioLogado');
        apiUsuario.get(`${idUsuarioHeader}/imagem?tipoImagem=P`,
            { responseType: 'blob' }).then((respostaImg) => {
                let imgUrl = URL.createObjectURL(respostaImg.data)
                document.getElementById("img_foto").src = imgUrl;
            }).catch((error) => {
                // console.log(error)
                if (param == "OK") {
                    document.getElementById("img_foto").src = `${fotoLogado}`;
                }
            })
        if (param == "OK") {
            document.getElementById("nome_usuario").innerHTML = `${usuarioHeader.nome}`;
        } else {
            document.getElementById("nome_usuario").innerHTML = "Usuário";
            document.getElementById("img_foto").src = `${fotoDeslogado}`;
        }
    })

    /* Faz logoff do usuário logado */
    function logoff(event) {
        event.preventDefault()
        sessionStorage.setItem('logado', "")
        var menu = document.getElementById("he-tooltip");
        let param = sessionStorage.getItem('idUsuarioLogado')
        apiUsuario.delete(`/${param}/login`, {
        }).then((resposta) => {
            console.log(resposta.status)
            menu.style.visibility = "hidden";
            setInterval(navegar("/"), 0.1);
            sessionStorage.setItem('idUsuarioLogado', "")
            sessionStorage.setItem('idEmailLogado', "")
        }).catch((error) => {
            console.log(error)
        })

    }

    /* Mostra o menu dependendo se tem usuário está logado ou não */
    const mostrarMenu = () => {
        var menu = document.getElementById("he-tooltip");
        let param = sessionStorage.getItem('logado');
        var menuLogado = document.getElementById("menuzinho-logado");
        var menuDeslogado = document.getElementById("menuzinho-deslogado");
        if (menu.style.visibility == "hidden") {
            menu.style.visibility = "visible";
            if (param == "OK") {
                menuLogado.style.display = "flex";
                menuDeslogado.style.display = "none";
            } else {
                menuDeslogado.style.display = "flex";
                menuLogado.style.display = "none";
            }
        }
        else {
            menu.style.visibility = "hidden";
        }
    }

    /* Direciona para página de cadastro de produto dependendo se tem usuário está logado ou não */
    const redirecionarDoar = () => {
        let param = sessionStorage.getItem('logado');
        if (param == "OK") {
            navegar("/cadastro-produto")
        } else {
            navegar("/login")
        }
    }

    /* Direciona para página mensagem direta dependendo se tem usuário está logado ou não */
    const redirecionarMensagemD = () => {
        let param = sessionStorage.getItem('logado');
        if (param == "OK") {
            navegar("/mensagem-direta")
        } else {
            navegar("/login")
        }
    }

    function PesquisarRedirect() {
        const value = document.getElementById("input_pesq").value;
        sessionStorage.setItem("pesquisa", value);
        navegar("/pesquisa");
        document.location.reload(true);
        console.log(value);
    }
    
    function Redirect(event){
 
        if(event.target.id != ""){
            
            sessionStorage.setItem("tipoCategoria", event.target.id);
            navegar("/pesquisa")
            document.location.reload(true);
        }
        else{
            sessionStorage.setItem("tipoCategoria", event.nativeEvent.path[1].id);
            navegar("/pesquisa")
            document.location.reload(true);
        }
    }


    return (
        <>
            <header id="header_completo">
                <div id="header_sup">
                    <div class="container_completo">
                        <Link to="/"><img src={logo} alt="Logo Conture" className="logo" /></Link>
                        <div id="input_pesquisar">
                            <input type="text" id="input_pesq" />
                            <button onClick={PesquisarRedirect}>
                                <img src={lupa} alt="Lupa de pesquisa" />
                            </button>
                        </div>
                        <div id="div_usuario" onClick={mostrarMenu}>
                            <img src={fotoDeslogado} alt="" id="img_foto" />
                            <p id="nome_usuario">Usuário</p>
                            <img src={setaBaixo} alt="" className="img_seta" id="seta_menu" />
                            <div id="he-tooltip">
                                <div className="menuzinho">
                                    <div id="triangulo-para-cima">
                                    </div>
                                    <div id="menuzinho-deslogado">
                                        <div className="mn-parte-um">
                                            <button><Link className="he-link-btn" to="/login">Entrar</Link></button>
                                        </div>
                                        <div className="mn-parte-dois">
                                            <p>Não é cadastrado ainda?</p>
                                            <button><Link className="he-link-btn" to="/cadastro">Cadastre-se</Link></button>
                                        </div>
                                    </div>
                                    <div id="menuzinho-logado">
                                        <p><Link className="he-link-p" to="/disponivel-pessoal">Minha conta</Link></p>
                                        <p><Link className="he-link-p" to="/editar-perfil">Configurações</Link></p>
                                        <button type="button" onClick={logoff}>SAIR</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="div_icones">
                            <img src={interesse} alt="Ícone de matchs" />
                            <img src={mensagem} alt="Ícone de mensagem direta" onClick={redirecionarMensagemD} />
                            <img src={notificacao} alt="Ícone de notificação" />
                        </div>
                        <button type="button" id="btn_doacao" onClick={redirecionarDoar}>
                            <p>DOAR PRODUTO</p>
                            <img src={adicionarProduto} alt="Ícone de soma" />
                        </button>
                    </div>
                </div>
                <div id="header_inferior">
                    <div id="div_produto">
                        <p>Produtos</p>
                        <img src={setaBaixo} alt="" className="img_seta" />
                    </div>
                    <div id="div_produtos">
                        <p id='7' onClick={((event) => { Redirect(event) })}>Notebook</p>
                        <p id='1' onClick={((event) => { Redirect(event) })}>Celular</p>
                        <p id='8' onClick={((event) => { Redirect(event) })}>Tablet</p>
                        <p id='2' onClick={((event) => { Redirect(event) })}>Desktop</p>
                    </div>
                </div>

            </header>

        </>
    );
}

export default Header;
