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
            albums : "Mes albums"
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
            albums : "My albums"
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

