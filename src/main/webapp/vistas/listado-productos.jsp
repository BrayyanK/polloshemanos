<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pollos Hermanos</title>
<link rel="stylesheet" type="text/css" href="../estilos.css" />
</head>
<body>
		<h1>Listado de Productos</h1>


		<table class="tabla">
			<tr>
				<th>Codigo</th>
				<th>Descripción</th>
				<th>Activo</th>
			</tr>
			<c:forEach var="producto" items="${productos}">
			<tr>
				<td>${producto.codigo}</td>
				<td>${producto.nombre} ${producto.descripcion} ${producto.fechaAlta} ${producto.precio} ${producto.familia} </td>
				<td>${producto.descatalogado}</td>
			</tr>
			</c:forEach>
		</table>

	</body>
</html>