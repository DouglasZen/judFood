<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
$(document).ready(function(){
	$('.data').datepicker({
	    format: "dd/mm/yyyy",
	    language: "pt-BR"
	});
})
</script>
<script type="text/javascript" src="/restaurante/views/js/controllers/cadastro.promocao.controller.js" charset="UTF-8"></script>
<div class="container" ng-controller="CadastroPromocaoController">
	<input type="hidden" ng-model="promocao.codigo" value="${requestScope.codigo}" id="codigopromocao"/>
	<div class="row col-sm-offset-2">
		<div class="col-lg-8">
			<div class="alert alert-danger" id="error" ng-if="mensagem">
                {{mensagem}}
            </div>
			<div>
				<label>Título *</label>
				<input class="form-control" ng-model="promocao.titulo" id="titulo">
			</div>
			<div>
				<label>Descrição *</label>
				<textarea class="form-control" ng-model="promocao.descricao" rows="5" id="descricao"></textarea>
			</div>
			<div>
				<label>Data de início *</label>
				<input type="text" class="form-control data" ng-model="promocao.data_inicio_str" id="dt_ini">
				
			</div>
			<div>
				<label>Data final *</label>
				<input type="text" class="form-control data" ng-model="promocao.data_fim_str" id="dt_fim">
			</div>
			<div>
				<label>* Imagem:</label>
				<input type="file" ng-model="promocao.imagem" onchange="angular.element(this).scope().setImage(this.files)">
			</div>
			<div>
				<img alt="" src="data:image/png;base64,{{promocao.imagem}}" id="imagemSalva" height="300" width="300">
			</div>
			<div style="margin-left: 260px;">
				<button type="button" class="btn btn-default" id="btSalvar" ng-click="salvar(promocao)">Salvar</button>
				<a href="/restaurante/promocao/consulta/"><button type="button" class="btn btn-default">Voltar</button></a>
			</div>
		</div>
		
	</div>
	
	
</div>
	

 

	