(function(){
	'use strict';
	angular.module('app', ['ui.bootstrap']).controller('HomeController', HomeController);
	
	HomeController.$inject = ['$scope', '$http', '$window'];
	
	function HomeController($scope, $http, $window){
		$scope.direcionarPrato = function(){
			$window.location.href = '/restaurante/prato/consulta/';
		}
		
		$scope.direcionarPromocao = function(){
			$window.location.href = '/restaurante/promocao/consulta/';
		}
	}
})()