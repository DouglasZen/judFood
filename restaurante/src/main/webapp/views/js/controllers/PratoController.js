(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('PratoController', PratoController);
	
	PratoController.$inject = ['$scope', '$http', '$window'];
	
	function PratoController($scope, $http, $window){
		$scope.prato = {};
		$scope.categoria = [];
		$scope.imagem;
		listCategoria();
		
		
		$scope.setImage = function(imagem){
			$scope.imagem = imagem[0];
			console.log($scope.imagem);
		}
		
		$scope.salvar = function(){
			var params = new FormData();
			params.append('prato', angular.toJson($scope.prato));
			params.append('imagem', $scope.imagem);
			$http({
				method: 'POST',
				url: './cadastro/salvar',
				headers: {'Content-Type': undefined},
				data: params,
				transformRequest: function(data, headersGetterFunction){
					return data;
				}
			}).success(function(data){
				
			}).error(function(){
				
			});
		}
		
		function listCategoria(){
			$http.get('/restaurante/categoria/listCategoria').success(function(data){
				$scope.categoria = data;
			})
		}
		
		
		
	}
})();