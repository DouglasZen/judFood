(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('ConsultaPromocaoController', ConsultaPromocaoController);
	
	ConsultaPromocaoController.$inject = ['$scope', '$http', '$window'];
	
	function ConsultaPromocaoController($scope, $http, $window){
		$scope.promocoes = [];
		//init();
		
		$scope.editar = function(codigo){
			$window.location.href = '/restaurante/promocao/editar/' + codigo;
		}
		
		$scope.ativar = function(codigo){
			$http.get('/restaurante/promocao/setStatus/' + codigo + '/1' ).success(function(data){
				$window.location.reload()
			})
		}
		
		$scope.desativar = function(codigo){
			$http.get('/restaurante/promocao/setStatus/' + codigo + '/0' ).success(function(data){
				$window.location.reload()
			})
		}
		
		function init(){
			listarPromocoes();
		}
		
		function listarPromocoes(){
			$http.get('/restaurante/promocao/listarpromocao').success(function(data){
				$scope.promocoes = data;
			})
		}
		
		
		
	}
})();