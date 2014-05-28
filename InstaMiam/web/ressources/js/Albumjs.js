Dropzone.options.myAwesomeDropzone = {
    paramName: "file", // Le nom de l'input contenant le fichier
    maxFilesize: 3, // Mo
    accept: function(file, done) {
        // TODO : mettre une verif sur jpg/png ...
        if (file.name == "justinbieber.jpg") {
            done("Naha, you don't.");
        }
        else {
            done();
        }
    },
    addRemoveLinks: true
};



// Disabling autoDiscover, otherwise Dropzone will try to attach twice.
Dropzone.autoDiscover = false;
function GetURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}
$(function() {

    

    var myDropzone = new Dropzone("#my-awesome-dropzone");

    // On ajoute un listener sur la fonction RemoveFiles qui envoi une requete ajax pour retirer l'image
    myDropzone.on("removedfile", function(file) {
        removeFile(file);
    });
});

$(document).ready(function() {
    if (GetURLParameter('action') == "validUpload") {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: '40%',
                left: ($(window).width() - 200) / 2 + 'px',
                width: '200px'
            }
        });

        var angle = 0;
        var interval = setInterval(function() {
            angle += 2;
            $("#displayBox").rotate(angle);
        }, 10);
    }

});

window.onload = function() {
    $.unblockUI();
    //setTimeout(clearTimeout(interval), 2000);

}
//$(".popOverMiam").popover({animation: "true", placement: "auto top", content: "Vous avez déjà partagé cet album à cette personne."});  