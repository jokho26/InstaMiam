var app = angular.module('InstaMiam', ['ngSanitize']);



app.controller('InstaMiamCtrl', function($scope) {
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
            albumsDe: "Albums de"
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
            changerImage: "Changer d'image"
        },
        notifications: {
            albumPartage: "Cet utilisateur vous a partagé un album ! GET DA COOKIES",
            photoAjoute: "Cet utilisateur a ajouté une photo à un album qu'il vous a partagé !",
            titre: "Vos notifications",
        }
        

    };

    /*   Tableau avec les traductions en anglais  */
    $scope.tab_en = {
        langue: {
            fr: "French",
            en: "English",
            title: "Language"
        },
        nav_bar: {
            actu: "News",
            mur: "My wall",
            albums: "My albums",
            inscription: "Inscription"
        }

    };


    $scope.langueFr = function() {
        $scope.tab_lang = $scope.tab_fr;
    }

    $scope.langueEn = function() {
        $scope.tab_lang = $scope.tab_en;
    }

    $scope.tab_lang = $scope.tab_fr;

    $(".imageMosaique").each(function(index) {
        $(this).popover();
    });
});

