(function(){
	
	angular.module('hello').service('AuthService', ['$rootScope', '$http', '$q', '$location', function($rootScope, $http, $q, $location){
		
	var service = {};

	var user = undefined;
	
	service.getUser = function(){
		return user;
	};
	
	service.restrictIfAuthAsync = function(){
		var deferred = $q.defer();
		
		service.checkSession().then(function(){
			$location.path('/');
			deferred.reject();
		}, function(err){
			deferred.resolve();
		});
		
		return deferred.promise;
	};
	
	service.checkSession = function(){
		var deferred = $q.defer();
		$http.get('user').then(function(res){
			$rootScope.loggedIn = true;
			user = res.data;
			deferred.resolve();
		}, function(err){
			user = undefined;
			deferred.reject();
		})
		return deferred.promise;
	}
	
	service.restrictAuthenticatedAsync = function(){
		var deferred = $q.defer();
		user ? deferred.reject() : deferred.resolve();
		return deferred.promise;
	};
		
  	  service.authenticate = function(credentials) {

  		var deferred = $q.defer();
  		
        //set auth header
	    var headers = {
	    		authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)
	    };
	    
	    var payload = {
	    	username : credentials.username,
	    	password : credentials.password
	    };
  		
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