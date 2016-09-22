<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@page import="com.ipartek.formacion.dao.persistence.Libro"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>

<a class="btn btn-success" href="libros/addLibro">Crear libro</a>
<a class="btn btn-success" href="libros/restclients">Usando Rest</a>



<%List<Libro>libros=(List<Libro>)request.getAttribute("listado-libros");
	if(libros!=null){%>
<div class="row">
	<div class="col-xs-12">
		<div id="libros">
			<table class="table">
				<div class="row"><div class="col-xs-4">Titulo</div><div class="col-xs-4">Autor</div><div class="col-xs-2">ISBN</div></div>
		<%for(Libro libro: libros){%>

				<div class="row">
					<div class="col-xs-4"> <%=libro.getTitulo() %></div>
					<div class="col-xs-4"> <%=libro.getAutor() %></div>
					<div class="col-xs-2"> <%=libro.getIsbn() %> </div>
					<div class="col-xs-1"><a class="editar btn btn-warning" href="libros/<%=libro.getId()%>">Editar</a></div>				
					<div class="col-xs-1"><a class="editar btn btn-danger" href="libros/deleteLibro/<%=libro.getId()%>">Borrar</a></div>
				</div>

<%
		}
	}else{
		%><p>No se han encontrado libros en la base de datos.</p><%
	}%>

</table></div></div></div>

</body>
</html>