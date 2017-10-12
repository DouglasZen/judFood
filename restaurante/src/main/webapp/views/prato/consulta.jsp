<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/consulta.prato.controller.js" charset="UTF-8"></script>
<div ng-controller="ConsultaPratoController">
	<a href="/restaurante/prato/cadastro" class="btn btn-primary btn-sm" >Novo</a>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nome</th>
				<th>Visualizar</th>
				
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="prato in pratos">
				<td>{{prato.id}}</td>
				<td>{{prato.nome}}</td>
				<td><a href="" class="btn btn-primary btn-sm" ng-click="editar(prato.id)" >Prato</a></td>
			</tr>
		</tbody>
	</table>	
</div>
	

 

	