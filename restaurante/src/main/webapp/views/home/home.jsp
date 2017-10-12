<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript" src="/restaurante/views/js/controllers/home.controller.js" charset="UTF-8"></script>
<div ng-controller="HomeController">
	Home
	<div>
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="direcionarPrato()">Pratos</button>
		<button type="button" class="btn btn-success" id="btSalvar" ng-click="direcionarPromocao()">Promoções</button>
	</div>
</div>