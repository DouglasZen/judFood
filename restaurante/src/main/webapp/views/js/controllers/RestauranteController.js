(function(){
	'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('RestauranteController', RestauranteController);
	
	RestauranteController.$inject = ['$scope', '$http', '$window'];
	
	function RestauranteController($scope, $http, $window){
		$scope.restaurante = {};
		init();
		
		function init(){
			listarUsuarios($("#codigoRestaurante").val());
		}
		
		$("#btSalvar").click(function(e){
			console.log($scope.restaurante)
			save($scope.restaurante);
		})
		
		
		function save(restaurante){
			$http.post('./cadastro/salvar', restaurante).success(function(){
				$scope.restaurante = {};
			})
		}
		
		function listaUsuarios(codigo){
			if(codigo != null && codigo != ''){
				
			}
		}
	}
})();