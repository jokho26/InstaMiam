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
            creer_album : "Créer un album",
            ajouter_photo : "Ajouter une photo"
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

