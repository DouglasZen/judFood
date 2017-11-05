(function(){
	'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('RestauranteController', RestauranteController);
	
	RestauranteController.$inject = ['$scope', '$http', '$window'];
	
	function RestauranteController($scope, $http, $window){
		$scope.restaurante = {};
		$scope.usuarios = [];
		$scope.editar = false;
		$scope.mensagem;
		init();
		
		function init(){
			var codigo = $("#codigoRestaurante").val();
			if(codigo != ''){
				getRestaurante(codigo);
				listarUsuarios(codigo);
			}
			
			
		}
		
		$scope.salvar = function(restaurante){
			if(verificarEntrada()){
				if($("#codigoRestaurante").val() != ''){
					$scope.editar = true;
					restaurante.codigo = $("#codigoRestaurante").val();
				}
				$http.post('/restaurante/restaurante/cadastro/salvar', restaurante).success(function(data){
					if(data){
						$("#codigoRestaurante").val(data.codigo)
						if(data.codigo != '' && $scope.editar){
							listarUsuarios(data.codigo);
							$("#modal-mensagem-sucesso").modal();
						}else if(data.codigo != '' && !$scope.editar){
							$window.location.href = '/restaurante/home/';						
						}
	
					}
				})
			}else{
				$scope.mensagem = 'Campos obrigatórios não preenchidos!';
			}
		}
		
		$scope.editar = function(codigo){
			$window.location.href = '/restaurante/usuario/editar/' + codigo;
		}
		
		function listarUsuarios(codigo){
			if(codigo != null && codigo != ''){
				$http.get('/restaurante/usuario/listausuarios/' + codigo)
					 .success(function(data){
						 $scope.usuarios = data;
					 })
			}
		}
		
		function getRestaurante(codigo){
			$http.get('/restaurante/restaurante/getRestaurante/' + codigo).success(function(data){
				$scope.restaurante = data;
			})
		}
		
		function verificarEntrada(){
			var validador = false;
			var nome = $("#nome").val();
			var endereco = $("#endereo").val();
			if(nome != '' && endereco != ''){
				validador = true;
			}
			return validador;
		}
	}
})();