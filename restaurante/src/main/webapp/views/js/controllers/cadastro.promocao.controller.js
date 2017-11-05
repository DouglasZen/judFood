(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('CadastroPromocaoController', CadastroPromocaoController);
	
	CadastroPromocaoController.$inject = ['$scope', '$http', '$window'];
	
	function CadastroPromocaoController($scope, $http, $window){
		$scope.promocao = {};
		$scope.imagem = '';
		$scope.mensagem;
		$scope.isImagem = false;
		init();
		
		$scope.setImage = function(imagem){
			$scope.imagem = imagem[0];
			$scope.isImagem = true;
			$scope.promocao.imagem = '';
			$("#imagemSalva").attr("src", '');
		}
		
		$scope.salvar = function(promocao){
			if(verificarEntrada()){
				
				if($("#dt_ini").val() > $("#dt_fim").val()){
					$scope.mensagem = 'Data de inicio maior que data final';
				}else{
					$scope.mensagem = '';
					var params = new FormData();
					if($("#codigopromocao").val() != ''){
						promocao.codigo = $("#codigopromocao").val();
						promocao.data_inicio_str = $("#dt_ini").val();
						promocao.data_fim_str = $("#dt_fim").val();
						params.append('promocao', angular.toJson(promocao));
						if(promocao.imagem == ''){
							params.append('imagem', $scope.imagem)
						}
					}else{
						promocao.data_inicio_str = $("#dt_ini").val();
						promocao.data_fim_str = $("#dt_fim").val();
						params.append('promocao', angular.toJson(promocao));
						params.append('imagem', $scope.imagem);
					}
					
					$http({
						method: 'POST',
						url: '/restaurante/promocao/cadastro/salvar',
						headers: {'Content-Type' : undefined},
						data: params,
						transformRequest: function(data, headersGetterFunction){
							return data;
						}
					}).success(function(data){
						$("#codigopromocao").val(data.codigo);
						$scope.promocao.imagem = data.imagem;
						$("#modal-mensagem-sucesso").modal();
					}).error(function(){
						$("#modal-mensagem-error").modal();
					});
				}
			}else{
				$scope.mensagem = 'Campos obrigatórios não preenchidos!';
			}
		}
		
		
		function init(){
			var codigo = $("#codigopromocao").val();
			if(codigo != ''){
				$http.get('/restaurante/promocao/getPromocao/' + codigo).success(function(data){
					$scope.promocao = data;
					$scope.isImagem = true;
				})
			}
		}
		
		function verificarEntrada(){
			var validador = false;
			var titulo = $("#titulo").val();
			var descricao = $("#descricao").val();
			var dtini = $("#dt_ini").val();
			var dtfim = $("#dt_fim").val();
			if(titulo != '' && descricao != '' && dtini != '' && dtfim != '' && $scope.isImagem == true){
				validador = true;
			}
			return validador;
		}
	}
})();