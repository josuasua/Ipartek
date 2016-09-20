<%@page import="com.ipartek.formacion.webservices.LibroRestClient"%>
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

<script src="https://code.jquery.com/jquery-3.1.0.js"
	integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk="
	crossorigin="anonymous">
 </script>
 <style>
 #formLibro{
 display:none;
 }
 </style>
<script>
$.noConflict();
jQuery(document).ready(function($){
	cargarDatos();
	function mostrarDatos(data){
		var texto = "";
		texto += "<div class='row'><div class='col-xs-12'><div id='libros'><table class='table'>";
		texto += "<div class='row'><div class='col-xs-3'>Titulo</div><div class='col-xs-3'>Autor</div><div class='col-xs-2'>ISBN</div></div>";
		for(var i = 0; i <data.length; i++){
			var titulo = data[i].titulo;
			var autor = data[i].autor;	
			var isbn = data[i].isbn;		
 			
			texto += "<div class='row'><div class='col-xs-4'>"+ titulo + "</div><div class='col-xs-3'>" + autor + "</div><div class='col-xs-3'>"+ isbn + "</div>";
			texto += "<div class='col-xs-1'><button class='editar btn btn-warning' data-id="+ data[i].id +">EDITAR</button></div>";
			texto += "<div class='col-xs-1'><button class='borrar btn btn-danger' data-id="+ data[i].id +">Eliminar</button></div></div>";
		}
		texto += "</div></div></div>";
		texto += "</table></div></div></div>";
		$("#listado").html(texto);
	}
	function mostrarMensaje(e){
		$("listado").text("No existen libros en base de datos" + e);
	}
	$("#listado").on("click","a", function(){
		
		var id = $(this).attr("data-id");
		console.log(id);
		//llamada a AJAX
		var url = "<%=LibroRestClient.REST_SERVICE_URI+"libros/"%>"+parseInt(id, 10);
		$.ajax({
			type : "GET",
	        contentType : "application/json",
	        url : url,
	        dataType : 'json',
	        timeout : 100000,
	        success : function(data) {
	            console.log("SUCCESS: ", data);
	            $("#id").val(data.id);
	            $("#titulo").val(data.titulo);
	            $("#autor").val(data.autor);
	            $("#isbn").val(data.isbn);
	           
	        },
	        error : function(e) {
	            console.log("ERROR: ", e);
	           
	        },
	        done : function(e) {
	            console.log("DONE");
	           
	        }
		});
		//poner los datos
		
		//mostrar el formulario
		$("#formLibro").show();
		//ocultar la lista
		$("#listado").hide();
		
	});
	$("main > a").click(function(){
		$("input").val("");
		$("#formLibro").show();
		$("#listado").hide();	
	});
	$(".cancelar").click(function() {
		$("input").val("");
		$("#formLibro").hide();
		$("#listado").show();
							
		return false;
	});
	$("button.crear").click(function(){
		$("input").val("");
		$("#formLibro").show();
		$("#listado").hide();

		return false;
	});
	$("#listado").on("click","button.editar" ,function(){
		var id = $(this).attr("data-id");
		//llamada a AJAX
		var url = "<%=LibroRestClient.REST_SERVICE_URI+"libros/"%>"+parseInt(id, 10);
		$.ajax({
			type : "GET",
	        contentType : "application/json",
	        url : url,
	        dataType : 'json',
	        timeout : 100000,
	        success : function(data) {
	            console.log("SUCCESS: ", data);
	            $("#id").val(data.id);
	            $("#titulo").val(data.titulo);
	            $("#autor").val(data.autor);
	            $("#isbn").val(data.isbn);
	           
	        },
	        error : function(e) {
	            console.log("ERROR: ", e);
	           
	        },
	        done : function(e) {
	            console.log("DONE");
	           
	        }
		});
		//poner los datos
		
		//mostrar el formulario
		$("#formLibro").show();
		//ocultar la lista
		$("#listado").hide();
		
	});
	function cargarCurso(codigo) {
		$.ajax({
			type : "GET", //GET, PUT, POST, DELETE
			contentType : "application/json",
			url : '${uriListado}' + "/" + codigo,
			dataType : "json",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				mostrarModulo(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(d) {
				console.log("DONE");
			}
		});
		return false;
	}
	function mostrarCurso(data) {
		$("#id").val(data.id);
		$("#titulo").val(data.titulo);
		$("#autor").val(data.autor);
		$("#isbn").val(data.isbn);
		$("#formLibro").show();
		$("#listado").hide();

		return false;
	}
	$("#listado").on("click","button.borrar" ,function(){
		var id = $(this).attr("data-id");
		console.log(id);
		$.ajax({
			type: "DELETE",
	        contentType : "application/json",
	        //data: JSON.stringify(curso),
	        url: "<%=LibroRestClient.REST_SERVICE_URI+"libros"%>/"+parseInt(id, 10),
	        dataType : 'json',
	        timeout : 100000,
	        success : function(data) {
	          //  console.log("SUCCESS: ", data);
	            alert("El curso ha sido eliminado correctamente");
	            cargarDatos();
	        },
	        error : function(e) {
	            console.log("ERROR: ", e);	           
	        },
	        done : function(e) {
	            console.log("DONE");	           
	        }
		});
		cargarDatos();
		return false;
	});
	$("button.guardar").click(function(){
		//recoger los datos
		var curso = {};
		curso['id'] = $("#id").val();
		curso['titulo'] = $("#titulo").val();
		curso['autor'] = $("#autor").val();
		curso['isbn'] = $("#isbn").val();
		console.log(JSON.stringify(libro));
		// update o insert (codigo), en nuestro caso no haremos nada
		if(curso['codigo']!=""){ //update
			$.ajax({
				type: "PUT",
		        contentType : "application/json",
		        data: JSON.stringify(libro),
		        url: "<%=LibroRestClient.REST_SERVICE_URI+"libros"%>/"+parseInt(libro['id'], 10),
		        dataType : 'json',
		        timeout : 100000,
		        success : function(data) {
		          //  console.log("SUCCESS: ", data);
		            alert("El libro ha sido actualizado correctamente");
		            cargarDatos();
		        },
		        error : function(e) {
		            console.log("ERROR: ", e);	           
		        },
		        done : function(e) {
		            console.log("DONE");	           
		        }
			});
		}else{//insert
			$.ajax({
				type : "POST",
		        contentType : "application/json",
		        url : "<%=LibroRestClient.REST_SERVICE_URI+"libros"%>",
		        dataType : 'json',
		        data : JSON.stringify(libro),
		        timeout : 100000,
		        success : function(data) {
		            console.log("SUCCESS: ", data);
		            alert("El libro ha sido creado correctamente");   
		            cargarDatos();    
		        },
		        error : function(e) {
		            console.log("ERROR: ", e);		           
		        },
		        done : function(e) {
		            console.log("DONE");  
		        }
			});
		}	
		$("#formLibro").hide();
		$("#listado").show();
		cargarDatos();
		return false;
	});
	//aquí todo el código jQuery y no ocasionará conflicto
	//hace llamada AJAX con jQuery
	function cargarDatos(){
		$.ajax({
			type : "GET",
	        contentType : "application/json",
	        url : "<%=LibroRestClient.REST_SERVICE_URI+"libros"%>",
	        dataType : 'json',
	        timeout : 100000,
	        success : function(data) {
	            console.log("SUCCESS: ", data);
	            mostrarDatos(data);
	        },
	        error : function(e) {
	            console.log("ERROR: ", e);
	            mostrarMensaje(e);
	        },
	        done : function(e) {
	            console.log("DONE");
	            //enableSearchButton(true);
	        }
		});
	}
});
</script>
</head>
<body>

	<header> </header>
	<button type="button" class="crear btn btn-success">Crear libro</button>
	<a role="button" href="http://localhost:8080/gestionbiblioteca/" class="btn btn-info">Volver a la pagina de inicio</a>
	<article id="resultados">
		
		<section id="listado">
			<header>Listado de libros desde RestClient</header>
		</section>
		<section id="formCurso">
			<header>Formulario libros desde RestClient</header>
			<form action="#" method="post">
				<input type="hidden" name="id" id="id">
				<input type="text" name="titulo" id="titulo">
				<input type="text" name="autor" id="autor">
				<input type="text" name="isbn" id="isbn">
				<button type="button" class="cancelar btn btn-danger">Volver</button>
				<button type="button" class="guardar btn btn-success">Guardar</button>			
			</form>
		</section>		
	</article>
	
	</main>
	<footer> </footer>
</body>
</html>