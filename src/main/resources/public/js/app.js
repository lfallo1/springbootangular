'use strict';

angular.module('hello', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {

        	$routeProvider.
	        	when('/', {
	                controller: 'HomeCtrl'
	            }).
	            otherwise({
                    redirectTo: '/'
                });
        	
        }])
        .run(['$rootScope', function ($rootScope) {

        	//TODO
        	
            }]);
