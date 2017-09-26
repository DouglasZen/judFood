(function(){
	'use-strict';
	
	angular.module('app', ['ui.bootstrap']).controller('CategoriaController', CategoriaController);
	
	CategoriaController.$inject = ['$scope', '$http', '$window'];
	
	function CategoriaController($scope, $http, $window){
		$scope.categoria = {};
		$scope.imagem;
		
		$scope.setImage = function(imagem){
			$scope.imagem = imagem[0];
			console.log($scope.imagem);
		}
		
		$scope.salvar = function(){
			var params = new FormData();
			params.append('categoria', angular.toJson($scope.categoria));
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
	}
})()