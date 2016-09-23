<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@page import="com.ipartek.formacion.dao.persistence.Ejemplar"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"/><!-- Aqui se compila previamente, tiene codigo java, se tiene que meter en el include.
Esto hace que tarde más en cargar, pero puede ser interesante a la larga -->
<main>

<a class="btn btn-success" href="ejemplares/addEjemplar">Crear ejemplar</a>
<a class="btn btn-success" href="ejemplares/restclients">Usando Rest</a>

<%List<Ejemplar>ejemplares=(List<Ejemplar>)request.getAttribute("listado-ejemplares");
	if(ejemplares!=null){%>
<div class="row">
	<div class="col-xs-12">
		<div id="ejemplares">
			<table class="table">
				<div class="row"><div class="col-xs-4">Editorial</div><div class="col-xs-4">Numero de paginas</div></div>
	<%
		for(Ejemplar ejemplar: ejemplares){%>

				<div class="row">
					<div class="col-xs-4"> <%=ejemplar.getEditorial() %></div>
					<div class="col-xs-4"> <%=ejemplar.getNumeropaginas() %></div>
					<div class="col-xs-1"><button class="editar btn btn-warning" href="ejemplares/<%=ejemplar.getId() %>">Editar</button></div>
					<div class="col-xs-1"><button class="borrar btn btn-danger" href="ejemplares/deleteEjemplar/<%=ejemplar.getId() %>">Borrar</button></div>
				</div>

<%
		}
	}else{
		%><p>No se han encontrado ejemplares en la base de datos.</p><%
	}%>

</table></div></div></div>


</body>
</html>