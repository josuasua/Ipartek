<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<main>

<form:form action="save" commandName="usuario">
	<c:if test="${usuario.id > 0}">
		<div>
			<form:label path="id">
				<spring:message text="id" />
			</form:label>
			<form:input path="id" readonly="true" size="10" disabled="true" />
			<form:hidden path="id" />
		</div>
	</c:if>
	<div>
		<form:label path="nombre">
			<spring:message text="Nombre: " />
		</form:label>
		<form:input path="nombre" value="${usuario.nombre }" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="nombre" />
		
	</div>
	<div>
		<form:label path="apellidos">
			<spring:message text="Apellidos: " />
		</form:label>
		<form:input path="apellidos" value="${usuario.apellidos }" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="apellidos" />
		
	</div>	
	<div>
		<form:label path="email">
			<spring:message text="Email: " />
		</form:label>
		<form:input path="email" value="${usuario.email }" readonly="" size="50" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="email" />
		
	</div>
	<div>
		<form:label path="fNacimiento">
			<spring:message text="Fecha de nacimiento: " />
		</form:label>
		<!--<form:input type="calendar" path="fNacimiento" value="${usuario.fNacimiento }" readonly="" size="50" disabled="" cssErrorClass="" />-->
		
		<form:errors cssClass="" path="fNacimiento" />
		
	</div>
		<div>
		<form:label path="password">
			<spring:message text="Password: " />
		</form:label>
		<form:input type="password" path="password" value="${usuario.password }" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="password" />
		
	</div>
		
	<div>
		<c:if test="${usuario.id > 0}">
			<button class="col-xs-2 btn btn-success">Editar usuario</button>
		</c:if>
		<c:if test="${usuario.id == 0|| usuario.id < 0}">
			<button class="col-xs-2 btn btn-success">Crear usuario</button>
		</c:if>
	</div>

</form:form>

</body>
</html>