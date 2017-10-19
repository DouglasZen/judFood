<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/home.controller.js" charset="UTF-8"></script>
<div ng-controller="HomeController">
	<input type="hidden" id="hasrest" value="${requestScope.rest}"/>
	<div class="row col-sm-offset-3 col-lg-8">
	</div>
	<div class="row col-sm-offset-3 col-lg-8">
		<div class="col-lg-3 col-md-6 ">
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
                 <a href="#" ng-click="direcionarPrato()">
                     <div class="panel-footer">
                         <span class="pull-left">Pratos Cadastrados</span>
                         <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                         <div class="clearfix"></div>
                     </div>
                 </a>
             </div>
         </div>
         <div class="col-lg-3 col-md-6 ">
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
                 <a href="#" ng-click="direcionarPromocao()">
                     <div class="panel-footer">
                         <span class="pull-left">Acessar promoções</span>
                         <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                         <div class="clearfix"></div>
                     </div>
                 </a>
             </div>
         </div>
         <div class="col-lg-3 col-md-6 ">
             <div class="panel panel-yellow">
                 <div class="panel-heading">
                     <div class="row">
                         <div class="col-xs-3">
                             <i class="fa fa-glass fa-5x"></i>
                         </div>
                         <div class="col-xs-9 text-right">
                             <div>Restaurante</div>
                         </div>
                     </div>
                 </div>
                 <a href="#" ng-click="direcionarRestaurante()">
                     <div class="panel-footer">
                         <span class="pull-left">Alterar dados do restaurante</span>
                         <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                         <div class="clearfix"></div>
                     </div>
                 </a>
             </div>
         </div>
	</div>
	<div class="row col-sm-offset-2 col-lg-8">
		<div class="panel panel-default">
			<div class="panel-heading">
                <i class="fa fa-pencil fa-fw"></i>Últimos comentários
            </div>
			<div class="panel-body">
				<div class="row" >
					<div class="col-lg-4" ng-repeat="comentario in comentarios">
	                    <div class="panel panel-primary">
	                        <div class="panel-heading">
	                            {{comentario.prato.nome}}
	                        </div>
	                        
	                        <div class="panel-body">
	                        	<p><b>{{comentario.pessoa.nome}}</b></p>
	                        	<p>{{comentario.comentario}}</p>
	                        	<input class="form-control" ng-model="comentario.resposta">
	                        </div>
	                        <a href="#" ng-click="comentar(comentario.resposta, comentario.codigo, comentario.prato.id)">
		                        <div class="panel-footer">
		                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i> responder</span>
			                        <div class="clearfix"></div>
		                        </div>
		                    </a>
	                    </div>
	                </div>
	              </div>
	              
			</div>
		</div>
	</div>
</div>