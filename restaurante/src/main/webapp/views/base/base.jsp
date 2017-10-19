<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html ng-app="app">
	<head>
		<meta charset="utf-8" >
    	
    	
    	<title>
			<tiles:getAsString name="titulo" ignore="true"/>
		</title>
    	
    	<link rel="stylesheet" href="/restaurante/views/css/bootstrap.min.css">
    	<link href="/restaurante/views/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    	<link rel="stylesheet" href="/restaurante/views/vendor/font-awesome/css/font-awesome.min.css"/>
    	<link href="/restaurante/views/vendor/sb-admin-2.css" rel="stylesheet">
    	<link href="/restaurante/views/css/bootstrap-iso.css" rel="stylesheet">
    	<link href="/restaurante/views/css/bootstrap-datepicker3.css" rel="stylesheet">
    	<link href="/restaurante/views/css/dataTables.bootstrap.min.css" rel="stylesheet">
    	<link href="/restaurante/views/css/bootstrap-datepicker.css" rel="stylesheet">
    	
    	<script src="/restaurante/views/js/angular.min-1.5.8.js"></script>
    	<script src="/restaurante/views/js/angular-sanitize-1.5.8.js"></script>
    	<script src="/restaurante/views/js/ui-bootstrap-tpls-2.2.0.min.js"></script>
    	
    	<script src="/restaurante/views/js/jquery.js"></script>
    	
    	<script src="/restaurante/views/js/bootstrap.js"></script>
    	<script src="/restaurante/views/js/angular-focusmanager.js"></script>
   
    	
    	<script src="/restaurante/views/js/main.js"></script>
    	<script src="/restaurante/views/vendor/formden.js"></script>
    	<script src="/restaurante/views/js/datatables.min.js"></script>
   		<script src="/restaurante/views/js/bootstrap-datetimepicker.pt-BR.js"></script>
   		<script src="/restaurante/views/js/bootstrap-datepicker.js"></script>
   		
    	
    	
    	
    	
    </head>
	<body>
		<div class="modal fade" id="modal-mensagem-sucesso">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span>×</span></button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row col-sm-offset-3">
							<div class="col-md-3">
                            	<i class="fa fa-check fa-5x" style="color: green;"></i>
                         	</div>
                         	<div class="col-md-9">
                            	<p style="margin-top: 28px;">Registro salvo com sucesso!</p>
                         	</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modal-mensagem-error">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span>×</span></button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row col-sm-offset-3">
							<div class="col-md-3">
                            	<i class="fa fa-times fa-5x" style="color: red;"></i>
                         	</div>
                         	<div class="col-md-9">
                            	<p style="margin-top: 28px;">Ops, tivemos um problema!</p>
                         	</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modal-mensagem-alert">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span>×</span></button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="row col-sm-offset-2">
							<div class="col-md-3">
                            	<i class="fa fa-times fa-5x" style="color: red;"></i>
                         	</div>
                         	<div class="col-md-9">
                            	<p style="margin-top: 28px;">Ops! Verificamos que não tem um restaurante</p>
                         	</div>
						</div>
					</div>
					<div class="modal-footer">
						<a href="/restaurante/restaurante/cadastro"><button type="button" class="btn btn-default">Cadastrar Restaurante</button></a>
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper">
			<!-- Navigation -->
        	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        		<div class="navbar-header">
	                <a class="navbar-brand" href="index.html">JudFOOD</a>
	            </div>
	            <ul class="nav navbar-top-links navbar-right">
	            	<li><a href="/restaurante/login/logout"><i class="fa fa-sign-out fa-2x"></i></a>
	            </ul>
        	</nav>
        	<div id="page-wrapper">
				<div class="body">  
					<tiles:insertAttribute name="body" ignore="true" />
		        </div>
		    </div>
        </div>
	</body>
</html>