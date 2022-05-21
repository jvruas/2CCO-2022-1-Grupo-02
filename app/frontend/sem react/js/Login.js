function mostrarOcultarSenha(){
    var senha = document.getElementById("senha");
    var img = document.getElementById("eye");
    if(senha.type=="password"){
        senha.type="text";
        img.src="../imagens/eye-slash-opened.png";
    }else{
        senha.type="password";
        img.src="../imagens/eye-slash-closed.png";
    }
}