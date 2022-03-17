var header = $('#header');
var logoheader = $('#header img');
$(document).scroll(function() {
  var scroll = $(this).scrollTop();

if (scroll > 400) {
    header.removeClass('home');
    logoheader.attr('src', './img/logo black.png');
} else {
    header.addClass('home');
    logoheader.attr('src', './img/Full white logo.png');
}

if(scroll<400){
    bold(1)
}
else if(scroll > 400 && scroll < 800){
    bold(2);
}
else if(scroll > 800 && scroll < 1400){
    bold(3);
}
else if(scroll>1500){
    bold(4)
}

});

function bold(number){
    var home = $('#a_home');
    var sobre = $('#a_sobre');
    var historia = $('#a_historia');
    var contato = $('#a_contato');

    switch(number){
        case 1:
            home.addClass('nav-link-selected');
            sobre.removeClass('nav-link-selected');
            historia.removeClass('nav-link-selected');
            contato.removeClass('nav-link-selected');
            break;

        case 2:
            home.removeClass('nav-link-selected');
            sobre.addClass('nav-link-selected');
            historia.removeClass('nav-link-selected');
            contato.removeClass('nav-link-selected');
            break;

        case 3:
            home.removeClass('nav-link-selected');
            sobre.removeClass('nav-link-selected');
            historia.addClass('nav-link-selected');
            contato.removeClass('nav-link-selected');
            break;

        case 4:
            home.removeClass('nav-link-selected');
            sobre.removeClass('nav-link-selected');
            historia.removeClass('nav-link-selected');
            contato.addClass('nav-link-selected');
            break;
    }
}


function mudarHistoria(number){
    switch(number){
        case 1:
            $('#historiaP').css('visibility', 'hidden');
            $('#historiaS').css('visibility', 'visible');            
            break;

        case 2:
            $('#historiaP').css('visibility', 'visible');
            $('#historiaS').css('visibility', 'hidden');   
            break;
    }
}
