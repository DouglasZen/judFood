<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript" src="/restaurante/views/js/controllers/login.controller.js" charset="UTF-8"></script>

<div class="container" ng-controller="LoginController">
	<div class="row col-sm-offset-3">
		<div class="col-lg-8">
            <h1 class="page-header">Cadastro de Usuário</h1>
        </div>
	</div>
	<div class="row col-sm-offset-3">
		<input type="hidden" ng-model="usuario.codigo" id="codigoUsuario" value="${requestScope.codigo}"/>
		
		<div class="col-lg-8">
			<div class="alert alert-danger" id="error" ng-if="mensagem">
                {{mensagem}}
            </div>
			<div>
				<label>Nome *</label>
				<input class="form-control" ng-model="usuario.nome"  id="nome" name="required">
			</div>	
			<div>
				<label>E-mail *</label>
				<input class="form-control" ng-model="usuario.email"  id="email">
			</div>
			<div>
				<label>Senha *</label>
				<input type="password" class="form-control" ng-model="usuario.senha" id="senha">
			</div>
			<div>
				<label>Confirmar Senha *</label>
				<input type="password" class="form-control" ng-blur="verificarSenha(usuario)" ng-model="confsenha">
			</div>
		</div>
		
	</div>
	<div class="row col-sm-offset-5">
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="cadastrar(usuario)" style="margin-top: 10px;margin-left: 55px;">Salvar</button>
	</div>	
</div>
