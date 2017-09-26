<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/CategoriaController.js" charset="UTF-8"></script>
<div ng-controller="CategoriaController">
	<div>
		<label>Descrição:</label>
		<input type="text" ng-model="categoria.descricao">
	</div>
	<div>
		<label>Imagem:</label>
		<input type="file" ng-model="categoria.imagem" onchange="angular.element(this).scope().setImage(this.files)">
	</div>
	<div>
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="salvar()">Salvar</button>
	</div>
</div>
 