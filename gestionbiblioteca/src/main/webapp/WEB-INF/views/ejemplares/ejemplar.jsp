<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page import="com.ipartek.formacion.dao.persistence.Ejemplar"%>
<%@page import="java.util.List"%>

    
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>

<form:form action="save" commandName="ejemplar">
	<c:if test="${ejemplar.id > 0}">
		<div>
			<form:label path="id">
				<spring:message text="id" />
			</form:label>
			<form:input path="id" readonly="true" size="10" disabled="true" />
			<form:hidden path="id" />
		</div>
	</c:if>
	<div>
		<form:label path="editorial">
			<spring:message text="Editorial: " />
		</form:label>
		<form:input path="editorial" value="${ejemplar.editorial }" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="editorial" />
		
	</div>
	<div>
		<form:label path="numeropaginas">
			<spring:message text="Numero de paginas: " />
		</form:label>
		<form:input path="numeropaginas" value="${ejemplar.numeropaginas }" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="numeropaginas" />
		
	</div>

	<div>
		<c:if test="${ejemplar.id > 0}">
			<button class="col-xs-2 btn btn-success">Editar ejemplar</button>
		</c:if>
		<c:if test="${ejemplar.id == 0 || ejemplar.id < 0}">
			<button class="col-xs-2 btn btn-success">Crear ejemplar</button>
		</c:if>
	</div>

</form:form>


</body>
</html>