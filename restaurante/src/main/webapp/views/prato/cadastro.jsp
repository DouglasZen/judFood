<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/PratoController.js" charset="UTF-8"></script>
<div class="container" ng-controller="PratoController">
	<input type="hidden" ng-model="prato.id" value="${requestScope.codigo}" id="codigoprato"/>
	<div class="row">
		<div class="col-lg-12">
			<div class="col-lg-6 col-md-6 ">
				<div>
					<label>Nome</label>
					<input class="form-control" ng-model="prato.nome">
				</div>
				<div>
					<label>Descrição</label>
					<textarea class="form-control" ng-model="prato.descricao" rows="5"></textarea>
				</div>
				<div>
					<label>Categoria</label>
					<select name="categoria" class="form-control" 
							ng-model="prato.categoria.codigo" 
							ng-options="option.codigo as option.descricao for option in categoria" material-select watch
							>
						
						<option value="">Selecione</option>
					</select>
				</div>
				
			</div>
			<div class="col-lg-6 col-md-6 ">
				<div>
					<label>Imagem:</label>
					<input type="file" ng-model="prato.imagem" onchange="angular.element(this).scope().setImage(this.files)">
				</div>
				<div>
					<img alt="" src="data:image/png;base64,{{prato.imagem}}" id="imagemSalva" height="300" width="300">
				</div>
			</div>
		</div>
		<div style="margin-top: 350px;margin-left: 500px;;">
			<button type="button" class="btn btn-default" id="btSalvar" ng-click="salvar(prato)">Salvar</button>
			<a href="/restaurante/prato/consulta/"><button type="button" class="btn btn-default">Voltar</button></a>
		</div>
	</div>
	<div class="row ">
		<div class="col-lg-5" ng-if="comentario.length > 0">
           	<ul class="timeline">
           		<li ng-repeat="c in comentario" class="timeline-inverted">
                    <div class="timeline-badge info">
                    <i class="fa fa-comment-o" style="margin-top: 15px;"></i>
                    </div>
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <p><small class="text-muted"><i class="fa fa-user"></i> {{c.pessoa.nome}}</small>
                            </p>
                        </div>
                        <div class="timeline-body">
                        	<p>{{c.comentario}}</p>
                        	<hr>
                        	<a href="" ng-click="abrirRespostas(c.codigo, c.prato.id)">responder</a>
                        </div>
                    </div>
                </li>
           	</ul>
		</div>
		<div class="col-lg-5" >
			<div style="margin-top: 20px; display: none;" id="textResposta">
				<input class="form-control" ng-model="comentario.resposta"><span class="pull-right"><a href="" ng-click="responder(comentario.resposta)"><i class="fa fa-arrow-circle-right fa-2x" ></i></a></span>
			</div>
           	<br/>
           	<br/>
           	<ul class="timeline" style="bottom: auto;" ng-if="respostas.length > 0">
           		<li ng-repeat="r in respostas">
                    
                    <div class="timeline-panel">
                        <div class="timeline-heading">
                            <p><small class="text-muted"><i class="fa fa-user"></i> {{r.pessoa.nome}}</small>
                            </p>
                        </div>
                        <div class="timeline-body">
                        	<p>{{r.comentario}}</p>
                        </div>
                    </div>
                </li>
           	</ul>
		</div>
	</div>
</div>




