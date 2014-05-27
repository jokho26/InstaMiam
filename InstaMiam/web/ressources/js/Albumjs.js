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

$(function() {

    var myDropzone = new Dropzone("#my-awesome-dropzone");

    // On ajoute un listener sur la fonction RemoveFiles qui envoi une requete ajax pour retirer l'image
    myDropzone.on("removedfile", function(file) {
        removeFile(file);
    });
});

$(document).ready(function() {
});
//$(".popOverMiam").popover({animation: "true", placement: "auto top", content: "Vous avez déjà partagé cet album à cette personne."});  