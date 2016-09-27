<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<main>

<form:form action="save" commandName="usuario">
	<c:if test="${usuario.id} > 0">
		<div class="form-group">
			<form:label path="id">
				<spring:message text="id" />
			</form:label>
			<form:input path="id" readonly="true" size="10" disabled="true" />
			<form:hidden path="id" />
		</div>
	</c:if>
	<div class="form-group">
		<form:label path="nombre">
					<spring:message text="Nombre: " />:
				</form:label>
		<form:input path="nombre" readonly="" size="10" disabled="" cssErrorClass=""/>
		<form:errors cssClass="" path="nombre" />
	</div>
	<div class="form-group">
		<form:label path="apellidos">
			<spring:message text="Apellidos: " />
		</form:label>
		<form:input path="apellidos" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="apellidos" />
	</div>
	<div class="form-group">
		<form:label path="email">
			<spring:message text="Email: " />
		</form:label>
		<form:input path="email" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="email" />
	</div>
	<div class="form-group">
		<form:label path="password">
			<spring:message text="Password: " />
		</form:label>
		<form:input type="password" path="password" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="password" />
	</div>
	<div class="form-group">
		<form:label path="fNacimiento" cssClass="sr-only">
			<spring:message text="Fecha de Nacimiento: " />
		</form:label>
		<form:input path="fNacimiento" placeholder="dd/MM/yyyy" readonly="" size="10" disabled="" cssErrorClass="" />
		<form:errors cssClass="" path="fNacimiento" />
	</div>
		
	<div class="form-group">
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