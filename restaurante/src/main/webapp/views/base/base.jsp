<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html ng-app="app">
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<title>
			<tiles:getAsString name="titulo" ignore="true"/>
		</title>
    	
    	<link rel="stylesheet" href="/restaurante/views/css/bootstrap.min.css">
    	<script src="/restaurante/views/js/angular.min-1.5.8.js"></script>
    	<script src="/restaurante/views/js/angular-sanitize-1.5.8.js"></script>
    	<script src="/restaurante/views/js/ui-bootstrap-tpls-2.2.0.min.js"></script>
    	<script src="/restaurante/views/js/jquery.js"></script>
    	
    	<script src="/restaurante/views/js/bootstrap.js"></script>
    	<script src="/restaurante/views/js/main.js"></script>
    </head>
	<body>
		<div class="body">  
			<tiles:insertAttribute name="body" ignore="true" />
        </div>
	</body>
</html>