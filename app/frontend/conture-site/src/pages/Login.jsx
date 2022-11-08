import MenuSimples from "../components/MenuSimples";
import '../html-css-template/css/Style.css'
import '../html-css-template/css/Login.css'
import iconSenha from "../html-css-template/imagens/eye-slash-closed.png"
import iconOk from "../html-css-template/imagens/icon-ok.png"
import iconOpen from "../html-css-template/imagens/eye-slash-opened.png"
import iconClose from "../html-css-template/imagens/eye-slash-closed.png"
import iconError from "../html-css-template/imagens/exclamation-circle-fill.svg"
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import apiUsuario from "../apiUsuario.js";

function dataUsuarioLogin() {
    return {
        email: "",
        senha: ""
    }
}

function Login() {


    // Função para chamar o endPoint para logar o usuário
    const [valuesUsuarioLogin, setValuesUsuarioLogin] = useState(dataUsuarioLogin)

    function handleChangeUser(event) {
        const { value, name } = event.target
        setValuesUsuarioLogin({ ...valuesUsuarioLogin, [name]: value, })
        console.log(valuesUsuarioLogin)
    }

    const navegar = useNavigate();
    
    function handleSubmit(event) {
        event.preventDefault()
        let json = {
            email: valuesUsuarioLogin.email,
            senha: valuesUsuarioLogin.senha
        }

        console.log(json)
        apiUsuario.post("/login", json, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((resposta) => {
            console.log(resposta.status)
            console.log(resposta.data.idUsuario)
            sessionStorage.setItem('idUsuarioLogado', resposta.data.idUsuario)
            sessionStorage.setItem('idEmailLogado', resposta.data.email)
            sessionStorage.setItem('logado', "OK")
            if(resposta.data.email == "admin@admin.com"){
                navegar("/dashboard-admin-bi")
            }else{
                navegar("/")
            }
        }).catch((error) => { 
            if(error.status == "409"){
                navegar("/")
            }else{
                if(valuesUsuarioLogin.email == "" || valuesUsuarioLogin.senha == ""){
                    document.getElementById("alerta-img").style.display = "flex";
                    document.getElementById("msg-alerta").innerHTML = `Preencha os campos vazios`;
                }else{
                    document.getElementById("alerta-img").style.display = "flex";
                    document.getElementById("msg-alerta").innerHTML = `Usuário ou senha inválida`;
                }      
            }
         })
    }

    const ocultarSenha = () => {
        var senha = document.getElementById("senha");
        var img = document.getElementById("eye");
        if (senha.type == "password") {
            senha.type = "text";
            img.src = iconOpen;
        } 
        else{
            senha.type = "password";
            img.src = iconClose;
        }
    }

    return (
        <>
            <MenuSimples />
            <section className="section-login centralizado">
                <form id="form-login" action="">
                    <div className="divisao centralizado">
                        <h1>LOGIN</h1>
                    </div>
                    <div className="divisao input">
                        <label htmlFor="nome">E-mail</label>
                        <input id="email" type="email" name="email" size="80" maxLength="80" value={valuesUsuarioLogin.email} required onChange={handleChangeUser} />
                    </div>
                    <div className="divisao input">
                        <label htmlFor="senha">Senha</label>
                        <input id="senha" type="password" name="senha" size="18" maxLength="18" minLength="6" value={valuesUsuarioLogin.senha} required onChange={handleChangeUser} />
                        <img src={iconSenha} alt="Ícone senha escondida" className="lo-eye" id="eye" onClick={ocultarSenha} />
                    </div>
                    <div className="divisao">
                        <Link className="link-esqc" to="/email-esqueci-senha">Esqueceu sua senha?</Link>
                    </div>
                    <div id="alerta" className="coluna">
                        <img src={iconError} id="alerta-img"/><p id="msg-alerta"></p>
                    </div>
                    <div className="divisao centralizado">
                        <button className="btn-login" type="submit" onClick={handleSubmit}><p>ENTRAR</p><img src={iconOk} alt="Ícone de confirmação" /></button>
                    </div>

                </form>
                <div className="cadastre centralizado">
                    <p>Ainda não é cadastrado?<Link className="link-cadastro" to="/cadastro"><b className="purple"> Cadastre-se</b></Link></p>
                </div>
            </section>
        </>
    )
}

export default Login;