<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
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

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Mi perfil - Actualizar datos</title>

</head>

<body class="fondo">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/index"> <i
				class="fa fa-music"></i> Flamenco Sound
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/grupos/lista"> <i
				class="fa fa-user"></i> Grupos
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/eventos/buscar"> <i
				class="fa fa-calendar"></i> Eventos
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<ul class="navbar-nav ml-auto">
				<c:if test="${not empty sessionScope.nombre}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle"
						href="${pageContext.request.contextPath}/usuarios/detalle?usuarioId=${sessionScope.usuarioId}"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="fa fa-user-circle-o"></i> ¡Hola ${sessionScope.nombre}!
					</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/usuarios/detalle?usuarioId=${sessionScope.usuarioId}">Mi
									perfil</a></li>
							<c:if test="${sessionScope.admin}">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/usuarios/lista">Lista
										de usuarios</a></li>
							</c:if>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/usuarios/logout">Logout</a></li>
							<li><hr class="dropdown-divider"></li>
						</ul></li>
				</c:if>
			</ul>

		</div>
	</nav>
	<div class="container">
		<div class="signup-form-container">

			<!-- Espacio de cabecera -->
			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i> Editar usuario
				</h3>
			</div>

			<!-- Espacio para modificar los datos -->
			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<c:set var="formAction"
									value="${pageContext.request.contextPath}/usuarios/actualizar" />
								<c:if test="${origen == 'admin'}">
									<c:set var="formAction"
										value="${pageContext.request.contextPath}/usuarios/actualizarAdmin" />
								</c:if>
								<form:form role="form" id="login-usuarios" autocomplete="off"
									class="credentials" method="post" action="${formAction}"
									modelAttribute="usuario">
									<h4>Actualizar datos de mi perfil</h4>
									<p></p>

									<div class="form-group mb-3">
										<form:label path="usuarioId" cssClass="form-label">ID de usuario: </form:label>
										<form:input path="usuarioId" type="text"
											cssClass="form-control fontAwesome" readonly="true" />
									</div>

									<div class="form-group mb-3">
										<form:label path="nombre" cssClass="form-label">Nombre: </form:label>
										<form:input path="nombre" type="text"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="nombre" cssClass="text-danger" />

									<p></p>

									<div class="form-group mb-3">
										<form:label path="apellido" cssClass="form-label">Apellido: </form:label>
										<form:input path="apellido" type="text"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="apellido" cssClass="text-danger" />

									<p></p>

									<div class="form-group mb-3">
										<form:label path="usuario" cssClass="form-label">Nombre de usuario: </form:label>
										<form:input path="usuario" type="text"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="usuario" cssClass="text-danger" />

									<div class="form-group mb-3">
										<form:label path="password" cssClass="form-label">Contraseña: </form:label>
										<form:input path="password" type="password"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="password" cssClass="text-danger" />

									<div class="form-group mb-3">
										<form:label path="dni" cssClass="form-label">DNI: </form:label>
										<form:input path="dni" type="text" id="dni"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="dni" cssClass="text-danger" />

									<div class="form-group mb-3">
										<form:label path="telefono" cssClass="form-label">Teléfono: </form:label>
										<form:input path="telefono" type="text"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="telefono" cssClass="text-danger" />

									<div class="form-group mb-3">
										<form:label path="email" cssClass="form-label">Correo electrónico: </form:label>
										<form:input path="email" type="text"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="email" cssClass="text-danger" />

									<div class="form-group mb-3">
										<form:label path="direccion" cssClass="form-label">Dirección: </form:label>
										<form:input path="direccion" type="text"
											cssClass="form-control fontAwesome" />
									</div>
									<form:errors path="direccion" cssClass="text-danger" />

									<p></p>

									<div class="form-group mb-3">
										<form:input path="admin" type="hidden" cssClass="form-control" />
									</div>
									<form:errors path="admin" cssClass="text-danger" />

									<span class="input-group-btn">
										<button type="submit" class="btn btn-info">Guardar</button>
									</span>

									<c:set var="urlCancelar"
										value="${pageContext.request.contextPath}/usuarios/detalle?usuarioId=${usuario.usuarioId}" />
									<c:if test="${origen == 'admin'}">
										<c:set var="urlCancelar"
											value="${pageContext.request.contextPath}/usuarios/lista" />
									</c:if>

									<span class="input-group-btn">
										<button type="reset" class="btn btn-info"
											onclick="window.location.href='${urlCancelar}'">Cancelar</button>
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