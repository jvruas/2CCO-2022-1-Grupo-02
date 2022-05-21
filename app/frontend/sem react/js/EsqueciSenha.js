function mostrarOcultarSenha2(){
    var senha = document.getElementById("senha");
    var img = document.getElementById("eye2");
    if(senha.type=="password"){
        senha.type="text";
        img.src="../imagens/eye-slash-opened.png";
    }else{
        senha.type="password";
        img.src="../imagens/eye-slash-closed.png";
    }
}

function mostrarOcultarSenha3(){
    var senha = document.getElementById("confSenha");
    var img = document.getElementById("eye3");
    if(senha.type=="password"){
        senha.type="text";
        img.src="../imagens/eye-slash-opened.png";
    }else{
        senha.type="password";
        img.src="../imagens/eye-slash-closed.png";
    }
}