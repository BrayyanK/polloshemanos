<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pollos Hermanos</title>
	</head>
	<body style="margin-left:50px;font-style: italic;">
		<div style="width:fit-content; padding:25px 25px 25px 25px ; background-color: thistle; border-radius: 25px">
			<h1>Ficha del Pedido</h1>
			<div style="margin-left:15px;">
				<p>Código: ${pedido.codigo}</p>
				<p>Empleado: ${pedido.empleado.nombre} ${pedido.empleado.apellido1} ${pedido.empleado.apellido2}</p>
				<p>Estado: ${pedido.estado}</p>
				<p>Fecha: ${pedido.fechaHora}</p>
			</div>
		</div>
	</body>
</html>