(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('PratoController', PratoController);
	
	PratoController.$inject = ['$scope', '$http', '$window'];
	
	function PratoController($scope, $http, $window){
		$scope.prato = {};
		$scope.categoria = [];
		listCategoria();
		
		$("#btSalvar").click(function(e){
			console.log($scope.prato)
			save($scope.prato);
		})
		
		function listCategoria(){
			$http.get('/restaurante/categoria/listCategoria').success(function(data){
				$scope.categoria = data;
			})
		}
		
		function save(prato){
			$http.post('./cadastro/salvar', prato).success(function(){
				$scope.prato = {};
			})
		}
		
	}
})();