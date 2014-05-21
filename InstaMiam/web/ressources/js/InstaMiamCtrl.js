angular.module('InstaMiam', ['ngSanitize'])

.controller('InstaMiamCtrl', function ($scope) {
    $scope.tab_fr = {

        langue : {
            fr : "Français",
            en : "Anglais",
            title : "Langue"
        },

        nav_bar : {
            actu : "Actualités",
            mur : "Mon mur",
            albums : "Mes albums",
            inscription : "Inscription",
            connexion : "Connexion",
            deconnexion : "Déconnexion"
        },
        
        inscription : {
            login: "Login",
            nom : "Nom",
            prenom:"Prénom",
            email:"Email",
            mdp:"Mot de passe",
            valider:"Valider",
            connexion:"Connexion"
        },
        
        mes_albums : {
            titre : "Mes albums",
            nouvel_album : "Nouvel album",
            ajouter_nouvel_album : "Ajouter un nouvel album",
            creer_album : "Créer l'album",
            ajouter_photo : "Ajouter une photo",
            commentaires : "commentaire(s)",
            nomAlbum : "Nom de l'album",
            albumPrive : "Album privé",
            albumPublique : "Album publique"
        },
        
        album : {
            posterCommentaire : "Votre commentaire ..."
        },
        
        photo : {
            modifierPhoto : "Modifier la photo",
            valider_modification : "Valider les modifications",
            supprimer : "Supprimer la photo"
        }

    };

    /*   Tableau avec les traductions en anglais  */
    $scope.tab_en = {

         langue : {
            fr : "French",
            en : "English",
            title : "Language"
        },

        nav_bar : {
            actu : "News",
            mur : "My wall",
            albums : "My albums",
            inscription : "Inscription"
        }
        
    };
     

    $scope.langueFr = function() {
        $scope.tab_lang = $scope.tab_fr;
    }

    $scope.langueEn = function() {
        $scope.tab_lang = $scope.tab_en;
    }

    $scope.tab_lang = $scope.tab_fr;
});

