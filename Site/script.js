window.onscroll = function(){
    scroll();
}
const $html = document.querySelector('html')

function scroll (){    
    if(document.documentElement.scrollTop>90){
        a_titulo.style.color = "black";      
    }   else {
        a_titulo.style.color = "white";
    }
}

