<%@page import="com.ipartek.formacion.dao.persistence.Usuario"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@page import="com.ipartek.formacion.dao.persistence.Ejemplar"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>



<%List<Ejemplar>ejemplares=(List<Ejemplar>)request.getAttribute("listado-ejemplares");
	Ejemplar ejemplar = (Ejemplar)request.getAttribute("ejemplar");
	if(ejemplar!=null){%>
	<div class="row">
		<div class="col-xs-12">
			<div id="reservas">
				<table class="table">
					<div class="row"><div class="col-xs-3">Autor</div><div class="col-xs-3">Titulo</div><div class="col-xs-3">Editorial</div><div class="col-xs-1">Numero de paginas</div></div>
				<div class="row">
						
						<div class="col-xs-3"> <%=ejemplar.getAutor() %></div>
						<div class="col-xs-3"> <%=ejemplar.getTitulo() %></div>
						<div class="col-xs-3"> <%=ejemplar.getEditorial() %></div>
						<div class="col-xs-1"> <%=ejemplar.getNumeropaginas() %></div>
						<c:if test="${ejemplar.usuario.id > 0}">
							<div class="col-xs-1"><a class="devolver btn btn-success" href="devolverLibro/${ejemplar.usuario.id}/${ejemplar.id}/">Devolver libro</a></div>					
						</c:if>
						<c:if test="${ejemplar.usuario.id == 0}">
							<div class="col-xs-1"><a class="reservar btn btn-success" href="reservarLibro/${ejemplar.usuario.id}/${ejemplar.id}/">Reservar libro</a></div>
						</c:if>
					</div>
				<%
			
		}else{
				if(ejemplares!=null){%>
	<div class="row">
		<div class="col-xs-12">
			<div id="reservas">
				<table class="table">
					<div class="row"><div class="col-xs-3">Autor</div><div class="col-xs-3">Titulo</div><div class="col-xs-3">Editorial</div><div class="col-xs-1">Numero de paginas</div></div>
		<%
			for(Ejemplar ejemplar1: ejemplares){%>

					<div class="row">
						
						<div class="col-xs-3"> <%=ejemplar1.getAutor() %></div>
						<div class="col-xs-3"> <%=ejemplar1.getTitulo() %></div>
						<div class="col-xs-3"> <%=ejemplar1.getEditorial() %></div>
						<div class="col-xs-1"> <%=ejemplar1.getNumeropaginas() %></div>
						<div class="col-xs-1"><a class="reservar btn btn-success" href="reservas/reservarLibro/${usuario.id}/<%=ejemplar1.getId() %>">Reservar libro</a></div>

					</div>

			<%}
			}else{
		%><p>No se han encontrado reservas en la base de datos.</p><%
			}
	}%>

</table></div></div></div>


</body>
</html>