(function(){
	
	angular.module('hello').service('AuthService', ['$rootScope', '$http', '$q', '$location', function($rootScope, $http, $q, $location){
		
	var service = {};

	var user = undefined;
	
	service.authenticatedAsync = function(restrict){
		var deferred = $q.defer();
		
		//if restrict flag, then reject if the logic is true (i.e., used for restricting to a login page)
		if(restrict){
			user ? deferred.reject() : deferred.resolve();
		} else{
			user ? deferred.resolve(user) : deferred.reject();
		}
		
		return deferred.promise;
	};
	
	service.restrictAuthenticatedAsync = function(){
		var deferred = $q.defer();
		user ? deferred.reject() : deferred.resolve();
		return deferred.promise;
	};
		
  	  service.authenticate = function(credentials) {

  		var deferred = $q.defer();
  		  
  	    var headers = credentials ? {authorization : "Basic "
  	        + btoa(credentials.username + ":" + credentials.password)
  	    } : {};

  	    $http.get('user', {headers : headers}).then(function(response) {
  	      if (response.data.name) {
  	    	$rootScope.loggedIn = true;
  	    	user = response.data;
  	        deferred.resolve(user);
  	      } else {
  	    	 deferred.reject();
  	      }
  	    }, function(err) {
  	      deferred.reject(err);
  	    });
  	    
  	    return deferred.promise;

  	  };
  	  
	  service.logout = function() {
		  $http.post('logout', {}).finally(function() {
		    user = undefined;
		    $rootScope.loggedIn = false;
		    $location.path("/");
		  });
	  };
  	  
  	  return service;
		
	}]);
	
})();