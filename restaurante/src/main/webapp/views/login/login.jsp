<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript" src="/restaurante/views/js/controllers/login.controller.js" charset="UTF-8"></script>

<div class="container" ng-controller="LoginController">
		<div class="row" style="margin-top: 200px;">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
                        <i class="fa fa-bar-chart-o fa-fw"></i>
                    </div>
					<div class="panel-body">
						<input type="hidden" ng-model="usuario.codigo" id="codigoUsuario"/>
						<div class="alert alert-danger" id="error" ng-if="mensagem">
			                {{mensagem}}
			            </div>
						<div>
							<label>E-mail</label>
							<input class="form-control" ng-model="usuario.email"  id="email">
						</div>
						<div>
							<label>Senha</label>
							<input type="password" class="form-control" ng-model="usuario.senha" id="senha">
							
						</div>
						<br/>
						<div class="col-md-16 text-center">
							<button type="button" class="btn btn-default" id="btSalvar" ng-click="logar(usuario)">Entrar</button>
						</div>
						<div class="col-md-16 text-center">
							<a href="/restaurante/login/cadastro">Cadastre-se</a>
						</div>
						
					</div>
				</div>
			</div>
		</div>
