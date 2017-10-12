(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('PratoController', PratoController);
	
	PratoController.$inject = ['$scope', '$http', '$window'];
	
	function PratoController($scope, $http, $window){
		$scope.prato = {};
		$scope.categoria = [];
		$scope.imagem;
		listCategoria();
		init();
		
		$scope.setImage = function(imagem){
			$scope.imagem = imagem[0];
			$scope.prato.imagem = '';
			$("#imagemSalva").attr("src", '');
		}
		
		$scope.salvar = function(prato){
			var params = new FormData();
			
			if($("#codigoprato").val() != ''){
				prato.id = $("#codigoprato").val();
				params.append('prato', angular.toJson(prato));
				if(prato.imagem == ''){
					params.append('imagem', $scope.imagem);
				}
				console.log(prato.imagem);
				
			}else{
				params.append('prato', angular.toJson($scope.prato));
				params.append('imagem', $scope.imagem);
			}
			
			
			$http({
				method: 'POST',
				url: '/restaurante/prato/cadastro/salvar',
				headers: {'Content-Type': undefined},
				data: params,
				transformRequest: function(data, headersGetterFunction){
					return data;
				}
			}).success(function(data){
				$("#codigoprato").val(data.id);
				$scope.prato.imagem = data.imagem;
				
			}).error(function(){
				
			});
		}
		
		function listCategoria(){
			$http.get('/restaurante/categoria/listCategoria').success(function(data){
				$scope.categoria = data;
			})
		}
		
		function init(){
			var codigo = $("#codigoprato").val();
			if(codigo != ''){
				$http.get('/restaurante/prato/getPrato/' + codigo).success(function(data){
					$scope.prato = data;
					
				})
			}
		}
		
		
	}
})();