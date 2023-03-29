<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pollos Hermanos</title>
	</head>
	<body style="margin-left:50px; font-style: italic;">
		<div style="width:fit-content; padding:25px 25px 25px 25px ; background-color: thistle; border-radius: 25px">
			<h1>Ficha de Empleado</h1>
			<div style="margin-left:15px;">
				<p><b style="font-size: large;">DNI:</b> ${empleado.dni}</p>
				<p><b style="font-size: large;">Nombre:</b> ${empleado.nombre}</p>
				<p><b style="font-size: large;">Apellido 1:</b> ${empleado.apellido1}</p>
				<p><b style="font-size: large;">Apellido 2:</b> ${empleado.apellido2}</p>
				<p><b style="font-size: large;">Activo:</b> ${empleado.activo}</p>
			</div>
		</div>
	</body>
</html>