(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('ConsultaPratoController', ConsultaPratoController);
	
	ConsultaPratoController.$inject = ['$scope', '$http', '$window'];
	
	function ConsultaPratoController($scope, $http, $window){
		$scope.pratos = [];
		//init();
		
		$scope.editar = function(codigo){
			$window.location.href = '/restaurante/prato/editar/' + codigo;
		}
		
		$scope.ativar = function(codigo){
			$http.get('/restaurante/prato/setStatus/' + codigo + '/1' ).success(function(data){
				$window.location.reload()
			})
		}
		
		$scope.desativar = function(codigo){
			$http.get('/restaurante/prato/setStatus/' + codigo + '/0' ).success(function(data){
				$window.location.reload()
			})
		}
		
		function init(){
			listarPratos();
		}
		
		function listarPratos(){
			$http.get('/restaurante/prato/listarpratos').success(function(data){
				$scope.pratos = data;
				
			})
		}
		
		
		
	}
})();