(function(){
	'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('LoginController', LoginController);
	
	LoginController.$inject = ['$scope', '$http', '$window'];
	
	function LoginController($scope, $http, $window){
		$scope.usuario = '';
		$scope.confsenha;
		$scope.mensagem;
		
		var codigo = $("#codigoUsuario").val();
		if(codigo != ''){
			$scope.usuario = [];
			getUsuario(codigo);
		}
		
		$scope.logar = function(usuario){
			$scope.mensagem = '';
			$http.post('/restaurante/login/logar', usuario)
			.success(function(data){
				if(data){
					$window.location.href = '/restaurante/home/';
				}else{
					$scope.mensagem = 'Dados inválidos!'
				}
			})
			
		}
		
		$scope.cadastrar = function(usuario){
			if(verificarEntrada()){
				$scope.mensagem = '';
				$http.post('/restaurante/login/salvar', usuario)
				.success(function(data){
					if(data){
						$window.location.href = '/restaurante/restaurante/cadastro';
					}
				})
			}else{
				$scope.mensagem = 'Campos obrigatórios não preenchidos!';
			}
		}
		
		$scope.verificarSenha = function(usuario){
			if(usuario.senha != $scope.confsenha){
				$scope.mensagem = 'Senha digitadas são diferentes!';
				$("#btSalvar").hide();
			}else{
				$scope.mensagem = '';
				$("#btSalvar").show();
			}
		}
		
		function getUsuario(codigo){
			$http.get('/restaurante/usuario/getUsuario/' + codigo)
			     .success(function(data){
			    	 $scope.usuario = data;
			    	 $scope.confsenha = data.senha;
			     })
		}
		
		function verificarEntrada(){
			var validador = false;
			var nome = $("#nome").val();
			var email = $("#email").val();
			var senha = $("#senha").val();
			if(nome != '' && email != '' && senha != '' && $scope.confsenha != ''){
				validador = true;
			}
			return validador;
		}
	}
})();

