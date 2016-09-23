<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@page import="com.ipartek.formacion.dao.persistence.Usuario"%>
<%@page import="java.util.List"%>
    
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>

<a class="btn btn-success" href="usuarios/addUsuario">Crear usuario</a>
<a class="btn btn-success" href="usuarios/restclients">Usando Rest</a>

<%List<Usuario>usuarios=(List<Usuario>)request.getAttribute("listado-usuarios");
	if(usuarios!=null){%>
	
<div class="row">
	<div class="col-xs-12">
		<div id="usuarios">
			<table class="table">
				<div class="row"><div class="col-xs-4">Nombre</div><div class="col-xs-4">Apellidos</div></div>
	<% 
		for(Usuario usuario: usuarios){%>

				<div class="row">
					<div class="col-xs-4"> <%=usuario.getNombre() %></div>
					<div class="col-xs-4"> <%=usuario.getApellidos() %></div>
					<div class="col-xs-1"><a class="reservar btn btn-info" href="reservas/<%=usuario.getId() %>">Reservar libro</a></div>
					<div class="col-xs-1"><a class="editar btn btn-warning" href="usuarios/<%=usuario.getId() %>">Editar</a></div>
					<div class="col-xs-1"><a class="borrar btn btn-danger" href="usuarios/deleteUsuario/<%=usuario.getId() %>">Borrar</a></div>
					
				</div>

<%
		}
	}else{
		%><p>No se han encontrado ejemplares en la base de datos.</p><%
	}%>

</table></div></div></div>


</body>
</html>