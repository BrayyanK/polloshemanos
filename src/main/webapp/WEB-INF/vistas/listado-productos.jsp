<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pollos Hermanos</title>
<link rel="stylesheet" type="text/css" href="../css/estilos.css" />
</head>
<body>
	<jsp:include page="header.jsp"/>

		<h2>Listado de productos</h2>
		<table class="tabla">
			<tr>
				<th>Codigo</th>
				<th>Descripción</th>
				<th>Activo</th>
			</tr>
			<c:forEach var="producto" items="${productos}">
				<tr>
				<td>${producto.codigo}</td>
				<td>${producto.nombre}  </td>
				<td>${producto.descripcion}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${producto.fechaAlta}" /></td>
				<td>${producto.precio}</td>
				<td>${producto.familia}</td>
				<td>${producto.descatalogado}</td>
				<td>
					<c:if test="${producto.descatalogado}">
						<span style="color:red;">DESCATALOGADO</span>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>

	</body>
</html>