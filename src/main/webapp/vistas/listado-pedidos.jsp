<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedidos</title>
<link rel="stylesheet" type="text/css" href="../estilos.css" />
</head>
<body>
	<h1>Listado de pedidos</h1>


	<table class="tabla">
		<tr>
			<th>Codigo</th>
			<th>Pedido</th>
			<th>Empleado</th>
		</tr>
		<c:forEach var="pedidos" items="${pedidos}">
			<tr>
				<td>${pedido.codigo}</td>
				<td>${pedido.fechaHora}${pedido.empleado.dni}</td>
				<td>${pedido.estado}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>