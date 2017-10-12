<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript" src="/restaurante/views/js/controllers/login.controller.js" charset="UTF-8"></script>
<div ng-controller="LoginController">
	<div>
		<label>Nome</label>
		<input type="text" ng-model="usuario.nome"  id="nome">
	</div>
	<div>
		<label>E-mail</label>
		<input type="text" ng-model="usuario.email"  id="email">
	</div>
	<div>
		<label>Senha</label>
		<input type="password" ng-model="usuario.senha" id="senha">
	</div>
	<div>
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="cadastrar(usuario)">Salvar</button>
	</div>
</div>
