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

<title>Usuario - Perfil</title>

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
					<i class="fa fa-user"></i> Mi perfil
				</h3>
			</div>

			<!-- Espacio para los datos del usuario -->
			<div class="form-row">
				<div class="col-1"></div>
				<div class="col-10 user-data-view">
					<div class="form-group">
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">Nombre:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.nombre}</p>
							</div>
						</div>
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">Apellido:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.apellido}</p>
							</div>
						</div>
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">Usuario:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.usuario}</p>
							</div>
						</div>
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">DNI:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.dni}</p>
							</div>
						</div>
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">Teléfono:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.telefono}</p>
							</div>
						</div>
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">Dirección:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.direccion}</p>
							</div>
						</div>
						<div class="form-row mb-2">
							<label class="col-sm-2 col-form-label">Email:</label>
							<div class="col-sm-10">
								<p class="form-control-plaintext">${usuario.email}</p>
							</div>
						</div>
					</div>
					<div class="input-group">
						<a
							href="${pageContext.request.contextPath}/usuarios/modificar?usuarioId=${usuario.usuarioId}">
							<input type="button" value="Modificar"
							class="btn btn-info btn-sm btn-modificar" />
						</a> <a
							href="${pageContext.request.contextPath}/usuarios/eliminar?usuarioId=${usuario.usuarioId}">
							<input type="button" value="Eliminar"
							class="btn btn-info btn-sm btn-eliminar"
							onclick="if(!(confirm('Vas a eliminar tu propio perfil, ¿Estas seguro?'))) return false" />
						</a>
					</div>
				</div>
			</div>

		</div>

	</div>
	<div class="col-1"></div>

	<p>
		<br>
	</p>

</body>

</html>