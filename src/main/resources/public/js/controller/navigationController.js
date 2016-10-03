angular.module('hello')
        .controller('NavigationCtrl', ["$rootScope", "$scope", "$location", "AuthService", function ($rootScope, $scope, $location, AuthService) {

        	$rootScope.currentPage = 'login';
        	
        	  $scope.credentials = {};
        	  $scope.login = function() {
        	      AuthService.authenticate($scope.credentials).then(function() {
        	    	  $location.path("/");
        	    	  $scope.error = false;
        	      }, function(err){
        	    	  $scope.error = true;
        	      });
        	  };
        	  
        	  $scope.logout = AuthService.logout;
        	  
}]);