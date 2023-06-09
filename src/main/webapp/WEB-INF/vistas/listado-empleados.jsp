<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pollos Hermanos</title>
<link rel="stylesheet" type="text/css" href="../css/estilos.css" />
</head>
<body>
	<jsp:include page="header.jsp"/>

	<h2>Listado de empleados</h2>
	<table class="tabla">
		<tr>
			<th>DNI</th>
			<th>Nombre Completo</th>
			<th>Activo</th>
		</tr>
		<c:forEach var="empleado" items="${empleados}">
			<tr>
				<td>${empleado.dni}</td>
				<td>${empleado.nombre}${empleado.apellido1}
					${empleado.apellido2}</td>
				<td>
					<c:if test="${empleado.activo}">
						<span style="color:green;">ACTIVO</span>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>