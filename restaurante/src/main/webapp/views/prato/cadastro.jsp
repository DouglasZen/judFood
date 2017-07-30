<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/PratoController.js" charset="UTF-8"></script>
<div ng-controller="PratoController">
	<div>
		<label>Nome</label>
		<input type="text" ng-model="prato.nome" value="${prato.nome}" id="nome">
	</div>
	<div>
		<label>Categoria</label>
		<select name="categoria" id="comboCategoria" ng-model="prato.categoria.codigo">
			<option>selecione</option>
			<option ng-repeat="option in categoria" value="{{option.codigo}}">{{option.descricao}}</option>
		</select>
	</div>
	<div>
		<label>Descrição:</label>
		<input type="text" ng-model="prato.descricao">
	</div>
	<div>
		<label>Imagem:</label>
		<input type="file" ng-model="prato.imagem" onchange="angular.element(this).scope().setImage(this.files)">
	</div>
	<div>
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="salvar()">Salvar</button>
	</div>
</div>
 
 

	