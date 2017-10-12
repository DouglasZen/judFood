<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/cadastro.promocao.controller.js" charset="UTF-8"></script>
<div ng-controller="CadastroPromocaoController">
	
	<input type="hidden" ng-model="promocao.codigo" value="${requestScope.codigo}" id="codigopromocao"/>
	<div>
		<label>Nome</label>
		<input type="text" ng-model="promocao.descricao" id="descricao">
	</div>
	<div>
		<label>Data Inicio</label>
		<input type="date" date-format="dd/MM/yyyy"  ng-model="promocao.data_inicio" value="{{promocao.data_inicio | date: 'dd/MM/yyyy' }}"/>
	</div>
	<div>
		<label>Data Final</label>
		<input type="date" ng-model="promocao.data_fim"/>
	</div>
	<div>
		<label>Imagem:</label>
		<input type="file" ng-model="promocao.imagem" onchange="angular.element(this).scope().setImage(this.files)">
	</div>
	<div>
		<img alt="" src="data:image/png;base64,{{promocao.imagem}}" id="imagemSalva">
	</div>
	<div>
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="salvar(promocao)">Salvar</button>
	</div>
</div>
	

 

	