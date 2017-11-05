(function(){
	'use strict';
	angular.module('app', ['ui.bootstrap']).controller('HomeController', HomeController);
	
	HomeController.$inject = ['$scope', '$http', '$window'];
	
	function HomeController($scope, $http, $window){
		$scope.comentarios = [];
		if($("#hasrest").val() == ''){
			$("#modal-mensagem-alert").modal();
			$scope.direcionarRestaurante = function(){
				$window.location.href = '/restaurante/restaurante/cadastro';
			}
		}else{
			listarComentarios();
		
			$scope.direcionarPrato = function(){
				$window.location.href = '/restaurante/prato/consulta/';
			}
			
			$scope.direcionarPromocao = function(){
				$window.location.href = '/restaurante/promocao/consulta/';
			}
			
			$scope.direcionarRestaurante = function(){
				$window.location.href = '/restaurante/restaurante/editar/';
			}
			
			$scope.comentar = function(resposta, codigo, codigo_prato){
				$http.post('/restaurante/comentario/responder',{
					codigo: codigo,
					codigo_prato : codigo_prato,
					resposta : resposta
				}).success(function(data){
					listarComentarios();
				});
				console.log(resposta, codigo);
			}
		}
		function listarComentarios(){
			$http.get('/restaurante/comentario/comentariosrestaurante').success(function(data){
				$scope.comentarios = data;
			})
		}
	}
})()