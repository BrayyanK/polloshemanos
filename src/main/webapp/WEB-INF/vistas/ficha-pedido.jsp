<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pollos Hermanos</title>
		<link rel="stylesheet" type="text/css" href="../css/estilos.css" />
	</head>
	<body>
	<jsp:include page="header.jsp"/>
		<div style="width:fit-content; padding:25px 25px 25px 25px ; background-color: thistle; margin-top: 20px;">
			<h1>Ficha del Pedido</h1>
			<div style="margin-left:15px;">
				<p>Código: ${pedido.codigo}</p>
				<p>Empleado: ${pedido.empleado.nombre} ${pedido.empleado.apellido1} ${pedido.empleado.apellido2}</p>
				<p>Estado: ${pedido.estado}</p>
				<p>Fecha: <fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.fechaHora}" /></p>
			</div>
		</div>
	</body>
</html>