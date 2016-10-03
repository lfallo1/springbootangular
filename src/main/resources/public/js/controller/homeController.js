angular.module('hello')
        .controller('HomeCtrl', ["$rootScope", "$scope", function ($rootScope, $scope) {
                $scope.greeting = {
                	id : 123,
                	content : "This is a greeting message"
                };
            }]);