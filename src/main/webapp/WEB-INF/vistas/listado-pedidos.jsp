<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedidos</title>
<link rel="stylesheet" type="text/css" href="../css/estilos.css" />
</head>
<body>
	<jsp:include page="header.jsp"/>

	<h2>Listado de pedidos</h2>

	<table class="tabla">
		<tr>
			<th>Codigo</th>
			<th>Fecha y Hora</th>
			<th>Empleado</th>
			<th>Estado</th>
			<th></th>
		</tr>
		<c:forEach var="pedido" items="${pedidos}">
			<tr>
				<td>${pedido.codigo}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.fechaHora}" /></td>
				<td>${pedido.empleado.dni}</td>
				
				<td>
					<c:if test="${pedido.estado == 'ENTREGADO'}">
						<span style="color:green;">ENTREGADO</span>
					</c:if>
					<c:if test="${pedido.estado == 'EN_PROCESO'}">
						<span style="color:red;">EN PROCESO</span>
					</c:if>
				</td>
				<td><a href="/polloshermanos/ficha-pedido?codigo=${pedido.codigo}">Ver Detalles</a></td>
			</tr>
			
		</c:forEach>
	</table>

</body>
</html>