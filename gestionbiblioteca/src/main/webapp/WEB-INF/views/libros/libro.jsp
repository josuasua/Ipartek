<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:url value="/resources/css/bootstrap.min.css" var="cssBootstrap" />
<spring:url value="/resources/css/font-awesome.min.css" var="cssFont" />
<spring:url value="/resources/css/styles.css" var="cssStyle" />
<spring:url value="/resources/js/bootstrap.min.js" var="jsBootstrap" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión biblioteca Ipartek</title>
<!-- CARGAMOS LOS BASICOS DE BOOTSTRAP -->
<link rel="stylesheet" href="${cssBootstrap}" />
<!-- CARGAMOS LAS FUENTES -->
<link rel="stylesheet" href="${cssFont}">
<!-- CARGAMOS NUESTROS ESTILOS -->
<link rel="stylesheet" href="${cssStyle}">
<!-- CARGAMOS JQUERY -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- CARGAMOS LAS LIBRERIAS JS DE BOOTSTRAP -->
<script src="${jsBootstrap}"></script>
<script src="${cssFont}"></script>
<script src="${cssStyle}"></script>


</head>
<body>

</body>
</html>