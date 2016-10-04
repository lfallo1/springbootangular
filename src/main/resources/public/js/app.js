'use strict';

angular.module('hello', ['ngRoute'])
        .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

        	$routeProvider.
	        	when('/', {
	        		templateUrl : 'templates/home.html',
	                controller: 'HomeCtrl'
	            }).
	            when('/login', {
	                templateUrl : 'templates/login.html',
	                controller : 'NavigationCtrl',
	                resolve : {
	                	allow : function(AuthService){
	                		return AuthService.restrictIfAuthAsync();
		                }
	                }
	            }).
	            otherwise({
                    redirectTo: '/'
                });
        	
        	 $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        	
        }])
        .run(['$rootScope', 'AuthService', function ($rootScope, AuthService) {
        	
        		AuthService.checkSession();
        		
            }]);
