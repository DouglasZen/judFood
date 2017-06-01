<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/PratoController.js" charset="UTF-8"></script>
<div ng-controller="PratoController">
	<label>Nome</label>
	<input type="text" ng-model="prato.descricao" value="${prato.descricao}" id="nome">
	<label>Categoria</label>
	<select name="categoria" id="comboCategoria" ng-model="prato.categoria.codigo">
		<option>selecione</option>
		<option ng-repeat="option in categoria" value="{{option.codigo}}">{{option.descricao}}</option>
	</select>
	<button type="button" class="btn btn-success" id="btSalvar">Salvar</button>
</div>
 
 

	