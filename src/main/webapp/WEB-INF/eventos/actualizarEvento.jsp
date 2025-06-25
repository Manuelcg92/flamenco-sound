<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

<title>Eventos - Actualizar Evento</title>

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
					<i class="fa fa-rebel"></i>Actualizar Evento
				</h3>
			</div>

			<!-- Espacio de enlace de Volver-->
			<div class="form-row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/eventos/lista">Volver</a>
				</div>
			</div>

			<p></p>

			<!-- Espacio para modificar los datos -->
			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form:form role="form" id="login-usuarios" autocomplete="off"
									class="credentials" method="post" action="actualizar"
									modelAttribute="evento">
									
									ID del evento:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="eventoId" type="text" readonly="true"
											class="form-control fontAwesome" />
									</div>
									<p></p>

									Nombre del evento:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="nombre" type="text"
											class="form-control fontAwesome"
											placeholder="Nombre del evento" value="${evento.nombre }" />
									</div>
									<form:errors path="nombre" cssClass="text-danger" />
									<p></p>

									Descripción:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="descripcion" type="text"
											class="form-control fontAwesome" placeholder="Descripción"
											value="${evento.descripcion}" />
									</div>
									<form:errors path="descripcion" cssClass="text-danger" />
									<p></p>

									Lugar:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="lugar" type="text"
											class="form-control fontAwesome" placeholder="Lugar"
											value="${evento.lugar }" />
									</div>
									<form:errors path="lugar" cssClass="text-danger" />
									<p></p>

									Duración:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="duracion" type="text"
											class="form-control fontAwesome" placeholder="Duración"
											value="${evento.duracion }" />
									</div>
									<form:errors path="duracion" cssClass="text-danger" />
									<p></p>

									Tipo de evento:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="tipoEvento" type="text"
											class="form-control fontAwesome" placeholder="Tipo de evento"
											value="${evento.tipoEvento }" />
									</div>
									<form:errors path="tipoEvento" cssClass="text-danger" />
									<p></p>

									Asientos disponibles:
									<div class="input-group" style="margin-top: 10px;">
										<form:input path="asientosDisponibles" type="number"
											class="form-control fontAwesome"
											placeholder="Asientos disponibles"
											value="${evento.asientosDisponibles }" />
									</div>
									<form:errors path="asientosDisponibles" cssClass="text-danger" />
									<p></p>

									<!-- Campo grupoId -->
									ID del grupo:
									<!-- Mostrar grupoId visible pero no editable -->
									<div class="input-group" style="margin-top: 10px;">
										<input type="number" class="form-control fontAwesome"
											value="${evento.grupoId}" readonly />
									</div>
									<p></p>

									<!-- Campo oculto que sí se envía con el formulario -->
									<form:hidden path="grupoId" />
									<p></p>

									<span class="input-group-btn">
										<button type="submit" class="btn btn-info">Guardar</button>
									</span>
									<span class="input-group-btn">
										<button type="reset" class="btn btn-info"
											onclick="window.location.href='lista'">Cancelar</button>
									</span>

								</form:form>
							</div>

						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

</body>
</html>
