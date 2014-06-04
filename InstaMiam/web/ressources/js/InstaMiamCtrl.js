var app = angular.module('InstaMiam', ['ngSanitize']);



app.controller('InstaMiamCtrl', function($scope) {
    $scope.tab_en = {
        langue: {
            fr: "French",
            en: "English",
            title: "Language"
        },
        recherche: {
            recherche: "Look for a user ..."
        },
        nav_bar: {
            actu: "Recent activities",
            albums: "My albums",
            inscription: "Sign up",
            connexion: "Sign in",
            deconnexion: "Sign out",
            profil: "My profile"
        },
        inscription: {
            login: "Login",
            nom: "Last name",
            prenom: "First name",
            email: "Email",
            mdp: "Password",
            valider: "Submit",
            connexion: "Connect"
        },
        mes_albums: {
            titre: "My albums",
            nouvel_album: "New album",
            ajouter_nouvel_album: "Add a new album",
            creer_album: "Create the album",
            ajouter_photo: "Add a photo",
            commentaires: "comment",
            nomAlbum: "Name of the album",
            albumPrive: "Private album",
            albumPublique: "Public album",
            albumsDe: "Albums of",
            titrePartage: "Albums shared with you",
            de: "user :"

        },
        album: {
            posterCommentaire: "Your comment ...",
            modifierAlbum: "Modify",
            supprimer: "Delete",
            valider_modification: "Submit changes",
            partager_album: "Share album :",
            badges_partage: "Users shared :",
            nomAlbum: "Name of the album :",
            fermerModale: "Close",
            upload: "Send"


        },
        photo: {
            modifierPhoto: "Modify photo",
            valider_modification: "Submit changes les modifications",
            supprimer: "Delete",
            definirPhotoCouverture: "Define as album cover",
            couverture: "(album cover)",
            nomPhoto: "Name of the photo :",
            descriptionPhoto: "Description of the photo :"
        },
        profil: {
            nom: "Last name",
            prenom: "First name",
            login: "Login",
            mdp: "Password",
            email: "Email",
            modifier: "Change",
            messageModification: "Your profile has been updated.",
            messageErreur: "There has been an error while updating your profile.",
            changerImage: "Change the image",
            titre: "My profile"
        },
        notifications: {
            albumPartage: "This user shared an album with you !",
            photoAjoute: "This user added a photo to an album he shared with you !",
            titre: "Your notifications",
            chargerPlus: "Load more notifications",
            commentaireAlbum:"This user added a commentary to an album !",
            commentairePhoto:"This user added a commentary to a photo !"
        }


    };

    /*   Tableau avec les traductions en anglais  */
    $scope.tab_fr = {
        langue: {
            fr: "Français",
            en: "Anglais",
            title: "Langue"
        },
        recherche: {
            recherche: "Chercher un utilisateur ..."
        },
        nav_bar: {
            actu: "Actualités",
            mur: "Mon mur",
            albums: "Mes albums",
            inscription: "Inscription",
            connexion: "Connexion",
            deconnexion: "Déconnexion",
            profil: "Mon profil"
        },
        inscription: {
            login: "Login",
            nom: "Nom",
            prenom: "Prénom",
            email: "Email",
            mdp: "Mot de passe",
            valider: "Valider",
            connexion: "Connexion"
        },
        mes_albums: {
            titre: "Mes albums",
            nouvel_album: "Nouvel album",
            ajouter_nouvel_album: "Ajouter un nouvel album",
            creer_album: "Créer l'album",
            ajouter_photo: "Ajouter une photo",
            commentaires: "commentaire",
            nomAlbum: "Nom de l'album",
            albumPrive: "Album privé",
            albumPublique: "Album publique",
            albumsDe: "Albums de",
            titrePartage: "Les albums partagés avec vous",
            de: "utilisateur :"

        },
        album: {
            posterCommentaire: "Votre commentaire ...",
            modifierAlbum: "Modifier",
            supprimer: "Supprimer",
            valider_modification: "Valider les modifications",
            partager_album: "Partager l'album :",
            badges_partage: "Utilisateurs partagés :",
            nomAlbum: "Nom de l'album :",
            fermerModale: "Fermer",
            upload: "Envoyer"


        },
        photo: {
            modifierPhoto: "Modifier photo",
            valider_modification: "Valider les modifications",
            supprimer: "Supprimer",
            definirPhotoCouverture: "Définir comme photo de couverture",
            couverture: "(photo de couverture)",
            nomPhoto: "Nom de la photo :",
            descriptionPhoto: "Description de la photo :"
        },
        profil: {
            nom: "Nom",
            prenom: "Prénom",
            login: "Login",
            mdp: "Mot de passe",
            email: "Email",
            modifier: "Modifier",
            messageModification: "Votre profil a bien été modifié.",
            messageErreur: "Erreur lors de la modification de votre profil.",
            changerImage: "Changer d'image",
            titre: "Mon profil"
        },
        notifications: {
            albumPartage: "Cet utilisateur vous a partagé un album !",
            photoAjoute: "Cet utilisateur a ajouté une photo à un album qu'il vous a partagé !",
            titre: "Vos notifications",
            chargerPlus: "Charger plus de notifications",
            commentaireAlbum:"Cet utilisateur a ajouté un commentaire à un album !",
            commentairePhoto:"Cet utilisateur a ajouté un commentaire à une photo !"
        }

    };
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name) == 0)
                return c.substring(name.length, c.length);
        }
        return "";
    }

    $scope.langueFr = function() {
        $scope.tab_lang = $scope.tab_fr;
        document.cookie = "instaLangue=fr";
    }

    $scope.langueEn = function() {
        $scope.tab_lang = $scope.tab_en;
        document.cookie = "instaLangue=en";

    }

    if (getCookie("instaLangue") === "fr" || getCookie("instaLangue") === "")
        $scope.tab_lang = $scope.tab_fr;
    else if (getCookie("instaLangue") === "en")
        $scope.tab_lang = $scope.tab_en;

    $(".imageMosaique").each(function(index) {
        $(this).popover();
    });
});

