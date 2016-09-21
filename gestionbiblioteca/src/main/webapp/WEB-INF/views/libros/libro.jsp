<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page import="com.ipartek.formacion.dao.persistence.Libro"%>
<%@page import="java.util.List"%>
    
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>

<form:form action="save" commandName="libro">
	<c:if test="${libro.id > 0}">
		<div>
			<form:label path="id">
				<spring:message text="id" />
			</form:label>
			<form:input path="id" readonly="true" size="15" disabled="true" />
			<form:hidden path="id" />
		</div>
	</c:if>
	<div>
		<form:label path="titulo">
			<spring:message text="Titulo: " />
		</form:label>
		<form:input path="titulo" value="${libro.titulo }" readonly="" size="50" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="titulo" />
		
	</div>
	<div>
		<form:label path="autor">
			<spring:message text="Autor: " />
		</form:label>
		<form:input path="autor" value="${libro.autor }" readonly="" size="20" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="autor" />
		
	</div>
	<div>
		<form:label path="isbn">
			<spring:message text="ISBN: " />
		</form:label>
		<form:input path="isbn" value="${libro.isbn }" readonly="" size="15" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="isbn" />
		
	</div>

	<div>
		<c:if test="${libro.id > 0}">
			<button class="col-xs-2 btn btn-success">Editar libro</button>
		</c:if>
		<c:if test="${libro.id == 0 || libro.id < 0}">
			<button class="col-xs-2 btn btn-success">Crear libro</button>
		</c:if>
	</div>

</form:form>


</body>
</html>