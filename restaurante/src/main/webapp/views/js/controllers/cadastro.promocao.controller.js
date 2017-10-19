(function(){
'use strict';
	
	angular.module('app', ['ui.bootstrap']).controller('CadastroPromocaoController', CadastroPromocaoController);
	
	CadastroPromocaoController.$inject = ['$scope', '$http', '$window'];
	
	function CadastroPromocaoController($scope, $http, $window){
		$scope.promocao = {};
		$scope.imagem;
		$scope.mensagem;
		init();
		
		$scope.setImage = function(imagem){
			$scope.imagem = imagem[0];
			$scope.promocao.imagem = '';
			$("#imagemSalva").attr("src", '');
		}
		
		$scope.salvar = function(promocao){
			if($("#dt_ini").val() > $("#dt_fim").val()){
				$scope.mensagem = 'Data de inicio maior que data final';
			}else{
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
				}).error(function(){
					
				});
			}
		}
		
		
		function init(){
			var codigo = $("#codigopromocao").val();
			if(codigo != ''){
				$http.get('/restaurante/promocao/getPromocao/' + codigo).success(function(data){
					$scope.promocao = data;
					
				})
			}
		}
		
	}
})();