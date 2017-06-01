<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript" src="/restaurante/views/js/controllers/RestauranteController.js" charset="UTF-8"></script>
<div ng-controller="RestauranteController">
	<label>Nome</label>
	<input type="text" ng-model="restaurante.nome" value="${restaurante.nome}" id="nome">
	<button type="button" class="btn btn-success" id="btSalvar">Salvar</button>
</div>