(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('PratoController', PratoController);
	
	PratoController.$inject = ['$scope', '$http', '$window'];
	
	function PratoController($scope, $http, $window){
		$scope.prato = {};
		$scope.categoria = [];
		$scope.comentario = [];
		$scope.respostas = [];
		$scope.imagem;
		$scope.codigo_comentario;
		$scope.codigo_prato;
		listCategoria();
		init();
		
		$scope.setImage = function(imagem){
			$scope.imagem = imagem[0];
			$scope.prato.imagem = '';
			$("#imagemSalva").attr("src", '');
		}
		
		$scope.abrirRespostas = function(codigo, codigo_prato){
			$("#textResposta").show();
			$scope.respostas = [];
			$scope.codigo_comentario = codigo;
			$scope.codigo_prato = codigo_prato;
			$http.get('/restaurante/comentario/getcomentario/' + codigo).success(function(data){
				$scope.respostas = data.respostas;
			});
		}
		
		$scope.responder = function(resposta){
			$http.post('/restaurante/comentario/responder',{
				codigo: $scope.codigo_comentario,
				codigo_prato : $scope.codigo_prato,
				resposta : resposta
			}).success(function(data){
				$scope.comentario.resposta = "";
				listarRespostas($scope.codigo_comentario);
			});
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
				headers: {'Content-Type': undefined },
				data: params,
				transformRequest: function(data, headersGetterFunction){
					return data;
				}
			}).success(function(data){
				if(data.id != ''){
					$("#codigoprato").val(data.id);
					$scope.prato.imagem = data.imagem;
					$("#modal-mensagem-sucesso").modal();
				}else{
					$("#modal-mensagem-error").modal();
				}
				
			}).error(function(){
				$("#modal-mensagem-error").modal();
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
					
				});
				
				$http.get('/restaurante/comentario/comentarioprato/' + codigo).success(function(data){
					$scope.comentario = data;
				});
			}
		}
		
		function listarRespostas(codigo){
			$http.get('/restaurante/comentario/getcomentario/' + codigo).success(function(data){
				$scope.respostas = data.respostas;
			});
		}
		
		
	}
})();