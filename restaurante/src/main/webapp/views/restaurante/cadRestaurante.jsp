<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript" src="/restaurante/views/js/controllers/RestauranteController.js" charset="UTF-8"></script>
<div class="container" ng-controller="RestauranteController">
	<div class="row col-sm-offset-3">
		<div class="col-lg-8">
            <h1 class="page-header">Restaurante</h1>
        </div>
	</div>
	<div class="row">
		<div class="col-lg-8  col-sm-offset-4">
			<div class="col-lg-4 col-md-6 " style="margin-left: 55px;">
            	<div class="panel panel-yellow">
                	<div class="panel-heading">
                    	<div class="row">
                        	<div class="col-xs-3">
                            	<i class="fa fa-home fa-5x"></i>
                        	</div>
                         	<div class="col-xs-9 text-right">
                            	<div>Home</div>
                        	</div>
                     	</div>
                 	</div>
                 	<a href="/restaurante/home/">
                     	<div class="panel-footer">
                         	<span class="pull-left"><i class="fa fa-arrow-circle-left"></i> Voltar para home</span>
                         	<div class="clearfix"></div>
                     	</div>
                 	</a>
             	</div>
         	</div>
		</div>
	</div>
	<div class="row col-sm-offset-3">
		<input type="hidden" ng-model="restaurante.codigo" id="codigoRestaurante" value="${requestScope.codigo}"/>
		<div class="col-lg-8">
			<div>
				<label>Nome</label>
				<input class="form-control" ng-model="restaurante.nome">
			</div>
			<div>
				<label>Endereço</label>
				<input class="form-control" ng-model="restaurante.endereco" id="endereco">
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: 535px;margin-top: 10px;">
		<button type="button" class="btn btn-default" ng-click="salvar(restaurante)">Salvar</button>
	</div>
	
	<div class="row col-sm-offset-3">
		<div class="col-lg-8">
			<div class="panel-body">
				<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Nome</th>
							<th>Editar</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="usuario in usuarios">
							<td>{{usuario.codigo}}</td>
							<td>{{usuario.nome}}</td>
							<td><a href="" ng-click="editar(usuario.codigo)"><i class="fa fa-pencil"></i></a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>