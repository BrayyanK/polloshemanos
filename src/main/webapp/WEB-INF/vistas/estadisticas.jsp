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

	<h2>Estadisticas</h2>
	<table class="tabla">
		<tr>
			<th>Familia</th>
			<th>Numero de Productos</th>
			<th>Precio Medio</th>
		</tr>
		<c:forEach var="familia" items="${familias}">
			<tr>
				<td>${familia}</td>
				<td>${familiaNumProductosMap[familia]}</td>
				<td>${familiaNumPrecio[familia]}
					<c:if test="${familiaNumPrecio[familia]==null}">
						<span>0</span>
					</c:if>
				</td>
				
			</tr>
		</c:forEach>
	</table>

</body>
</html>