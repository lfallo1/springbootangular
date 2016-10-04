angular.module('hello')
        .controller('HomeCtrl', ["$rootScope", "$scope", "$http", "AuthService", function ($rootScope, $scope, $http, AuthService) {
        	
        	$rootScope.currentPage = 'home';
        	
        	$http.get('api/resource').then(function(res){
                $scope.greeting = res.data;	
        	})
        	
        	$scope.accessApi = function(){
        		if(AuthService.getUser()){
            		$http.get('auth/v1/users/' + AuthService.getUser().principal.username).then(function(res){
            			console.log(res);
            		}, function(err){
            			console.log(err);
            		});
        		}
        	}
        	
}]);