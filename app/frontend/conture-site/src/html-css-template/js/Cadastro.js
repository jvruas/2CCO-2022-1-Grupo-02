function mostrarOcultarSenha4(){
    var senha = document.getElementById("senha");
    var img = document.getElementById("eye4");
    if(senha.type=="password"){
        senha.type="text";
        img.src="../imagens/eye-slash-opened.png";
    }else{
        senha.type="password";
        img.src="../imagens/eye-slash-closed.png";
    }
}

function mostrarOcultarSenha5(){
    var senha = document.getElementById("confSenha");
    var img = document.getElementById("eye5");
    if(senha.type=="password"){
        senha.type="text";
        img.src="../imagens/eye-slash-opened.png";
    }else{
        senha.type="password";
        img.src="../imagens/eye-slash-closed.png";
    }
}