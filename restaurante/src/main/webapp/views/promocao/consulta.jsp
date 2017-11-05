<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable();
});
</script>
<script type="text/javascript" src="/restaurante/views/js/controllers/consulta.promocao.controller.js" charset="UTF-8"></script>
<div ng-controller="ConsultaPromocaoController">
	<div class="row col-sm-offset-3">
		<div class="col-lg-8">
            <h1 class="page-header">Consultar Promoções</h1>
        </div>
	</div>
	<div class="row col-sm-offset-4">
		<div class="col-lg-8">
			<div class="col-lg-4 col-md-6 ">
             <div class="panel panel-green">
                 <div class="panel-heading">
                     <div class="row">
                         <div class="col-xs-3">
                             <i class="fa fa-ticket fa-5x"></i>
                         </div>
                         <div class="col-xs-9 text-right">
                             <div>Promoções</div>
                         </div>
                     </div>
                 </div>
                 <a href="/restaurante/promocao/cadastro">
                     <div class="panel-footer">
                         <span class="pull-left">Nova promoção</span>
                         <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                         <div class="clearfix"></div>
                     </div>
                 </a>
             </div>
         </div>
         <div class="col-lg-4 col-md-6 ">
             <div class="panel panel-green">
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
						<c:forEach items="${promocoes}" var="promocao">
						<tr>
							<td>${promocao.codigo}</td>
							<td>${promocao.titulo}</td>
							<td>
								<a href="" ng-click="editar(${promocao.codigo})"><i class="fa fa-pencil"></i></a>
								<a href="" ng-if="${promocao.status } == 0" ng-click="ativar(${promocao.codigo})"><i class="fa fa-check" style="color: green;"></i></a>
								<a href="" ng-if="${promocao.status } == 1" ng-click="desativar(${promocao.codigo})"><i class="fa fa-times" style="color: red;"></i></a>
							</td>
						</tr>	
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
</div>
	

 

	