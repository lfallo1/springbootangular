angular.module('hello')
        .controller('HomeCtrl', ["$rootScope", "$scope", "$http", function ($rootScope, $scope, $http) {
        	
        	$rootScope.currentPage = 'home';
        	
        	$http.get('api/resource').then(function(res){
                $scope.greeting = res.data;	
        	})
        	
}]);