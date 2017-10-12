(function(){
	'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('LoginController', LoginController);
	
	LoginController.$inject = ['$scope', '$http', '$window'];
	
	function LoginController($scope, $http, $window){
		$scope.usuario = {};
		
		$scope.logar = function(usuario){
			console.log(usuario);
			$http.post('/restaurante/login/logar', usuario)
			.success(function(data){
				if(data){
					$window.location.href = '/restaurante/home/';
				}
			})
			
		}
		
		$scope.cadastrar = function(usuario){
			$http.post('/restaurante/login/salvar', usuario)
			.success(function(data){
				
				
			})
		}
	}
})();

