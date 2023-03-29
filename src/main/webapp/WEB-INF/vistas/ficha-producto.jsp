<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pollos Hermanos</title>
		<link rel="stylesheet" type="text/css" href="../estilos.css" />
	</head>
	<body>
		<h1>Listado de productos</h1>

		<table class="tabla">
			<tr>
				<th>Código</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Fecha de Alta</th>
				<th>Familia</th>
				<th>Descatalogado</th>
			</tr>
			<c:forEach var="producto" items="${productos}">
			<tr>
				<td>${producto.codigo}</td>
				<td>${producto.nombre}</td> 
				<td>${producto.precio}</td>
				<td>${producto.fechaAlta}</td>
				<td>${producto.familia}</td>
				<td>${producto.descatalogado}</td>
			</tr>
			</c:forEach>
		</table>

	</body>
</html>