<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable();
});
</script>
<script type="text/javascript" src="/restaurante/views/js/controllers/consulta.prato.controller.js" charset="UTF-8"></script>
<div ng-controller="ConsultaPratoController">
	<div class="row col-sm-offset-3">
		<div class="col-lg-8">
            <h1 class="page-header">Consultar Pratos</h1>
        </div>
	</div>
	<div class="row col-sm-offset-4">
		<div class="col-lg-8">
			<div class="col-lg-4 col-md-6 ">
             <div class="panel panel-primary">
                 <div class="panel-heading">
                     <div class="row">
                         <div class="col-xs-3">
                             <i class="fa fa-cutlery fa-5x"></i>
                         </div>
                         <div class="col-xs-9 text-right">
                             <div>Pratos</div>
                         </div>
                     </div>
                 </div>
                 <a href="/restaurante/prato/cadastro">
                     <div class="panel-footer">
                         <span class="pull-left">Novo prato</span>
                         <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                         <div class="clearfix"></div>
                     </div>
                 </a>
             </div>
         </div>
         <div class="col-lg-4 col-md-6 ">
             <div class="panel panel-primary">
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
						 <c:forEach items="${pratos}" var="prato">
							 <tr>
								<td>${prato.id}</td>
								<td>${prato.nome}</td>
								<td><a href="" ng-click="editar(${prato.id})"><i class="fa fa-pencil"></i></a></td> 
							</tr>		 
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
</div>
	

 

	