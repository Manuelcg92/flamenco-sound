<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.flamenco.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flamenco.css">

<title>Nuevo Usuario</title>
</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i> Registro
				</h3>
			</div>

			<p></p>

			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form:form role="form" id="login-usuarios" autocomplete="off"
									class="credentials" method="post"
									action="${pageContext.request.contextPath}/usuarios/insertar"
									modelAttribute="usuario">

    								Nuevo Usuario / Login
    								<p></p>
    								Nombre:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i class="fa fa-user"></i></span>
										</div>
										<form:input path="nombre" cssClass="form-control"
											placeholder="Nombre" />
									</div>
									<form:errors path="nombre" cssClass="text-danger" />
									<p></p>
									Apellido:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i class="fa fa-user-o"></i></span>
										</div>
										<form:input path="apellido" cssClass="form-control"
											placeholder="Apellido" />
									</div>
									<form:errors path="apellido" cssClass="text-danger" />
									<p></p>
									Dirección:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="fa fa-address-card"></i></span>
										</div>
										<form:input path="dni" cssClass="form-control"
											placeholder="DNI" id="dni" maxlength="9" />
									</div>
									<form:errors path="dni" cssClass="text-danger" />
									<p></p>
									Correo electrónico:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="fa fa-envelope-o"></i></span>
										</div>
										<form:input path="email" cssClass="form-control"
											placeholder="Email" />
									</div>
									<form:errors path="email" cssClass="text-danger" />
									<p></p>
									Número de teléfono:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i class="fa fa-phone"></i></span>
										</div>
										<form:input path="telefono" cssClass="form-control"
											placeholder="Teléfono" />
									</div>
									<form:errors path="telefono" cssClass="text-danger" />
									<p></p>
									Dirección:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="fa fa-address-book"></i></span>
										</div>
										<form:input path="direccion" cssClass="form-control"
											placeholder="Dirección" />
									</div>
									<form:errors path="direccion" cssClass="text-danger" />
									<p></p>
									Nombre de usuario:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i
												class="fa fa-user-circle"></i></span>
										</div>
										<form:input path="usuario" cssClass="form-control"
											placeholder="Usuario" />
									</div>
									<form:errors path="usuario" cssClass="text-danger" />
									<p></p>
									Contraseña:
									<div class="input-group" style="margin-top: 10px;">
										<div class="input-group-prepend">
											<span class="input-group-text"><i class="fa fa-key"></i></span>
										</div>
										<form:input path="password" cssClass="form-control" type="password"
											placeholder="Contraseña" />
									</div>
									<form:errors path="password" cssClass="text-danger" />
									<p></p>

									<div class="input-group">
										<form:input path="admin" type="hidden" />
									</div>
									<form:errors path="admin" cssClass="text-danger" />
									<p></p>

									<span class="input-group-btn">
										<button type="submit" class="btn btn-info">Registrar</button>
									</span>
									<span class="input-group-btn">
										<button type="reset" class="btn btn-info"
											onclick="window.location.href='${pageContext.request.contextPath}/login.jsp'">Cancelar</button>
									</span>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script>
		function calcularLetraDNIenCampoUnico() {
			const letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			const input = document.getElementById("dni");
			const valor = input.value;

			if (/^\d{8}$/.test(valor)) {
				const numero = parseInt(valor, 10);
				const letra = letras.charAt(numero % 23);
				input.value = valor + letra;
			}
		}

		document.addEventListener("DOMContentLoaded", function() {
			const dniInput = document.getElementById("dni");

			dniInput.addEventListener("blur", calcularLetraDNIenCampoUnico);
		});
	</script>

</body>
</html>