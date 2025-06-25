<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Lista de Eventos</title>
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
			<p>
				<br>
			</p>
	</div>

			<!-- Espacio de enlace de Volver y Cerrar sesión -->
			<div class="form-row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/eventos/buscar">Volver</a>
				</div>
			</div>

			<p></p>

			<c:if test="${not empty mensajeExito}">
				<div class="alert alert-success" role="alert">${mensajeExito}
				</div>
			</c:if>

			<p></p>

			<!-- Espacio para la lista de Eventos -->
			<div class="release">
				<span class="welcome">LISTADO DE EVENTOS</span>
				<div class="table-wrapper" style="padding: 0.5rem;">
					<table class="table table-bordered table-striped"
						style="width: auto%; font-size: small;">
						<thead class="tableHeaderAll">
							<tr>
								<th>Id</th>
								<th>Nombre</th>
								<th>Descripcion</th>
								<th>Lugar</th>
								<th>Duración</th>
								<th>Tipo</th>
								<th>Asientos disponibles</th>
								<c:if test="${sessionScope.admin}">
									<th>Acciones</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="evento" items="${eventos}">
								<tr class="tableHeaderUser">
									<td>${evento.eventoId}</td>
									<td>${evento.nombre}</td>
									<td>${evento.descripcion}</td>
									<td>${evento.lugar}</td>
									<td>${evento.duracion}</td>
									<td>${evento.tipoEvento}</td>
									<td>${evento.asientosDisponibles}</td>

									<td><a
										href="${pageContext.request.contextPath}/entradas/alta?eventoId=${evento.eventoId}&usuarioId=${sessionScope.usuarioId}"
										class="btn btn-sm btn-primary"> <i class="fa fa-ticket"></i>
											Comprar
									</a> <c:if test="${sessionScope.admin}">
										&nbsp;|&nbsp;
										<a href="modificar?eventoId=${evento.eventoId}"
												class="btn btn-info btn-sm btn-modificar"> <i
												class="fa fa-pencil"></i>
											</a>
											<a href="eliminar?eventoId=${evento.eventoId}"
												class="btn btn-info btn-sm btn-eliminar"
												onclick="return confirm('Vas a eliminar un registro, ¿Estás seguro?')">
												<i class="fa fa-trash"></i>
											</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


				<!-- Espacio para el botón de Añadir evento-->
				<c:if test="${sessionScope.admin}">
					<div class="form-row">
						<div class="col-1"></div>
						<span class="input-group-btn"> <input type="button"
							class="btn btn-custom" value="Agregar Evento"
							onclick="window.location.href='alta'; return false;" />
						</span>
					</div>
				</c:if>

				<p>
					<br>
				</p>

				<!-- Espacio para el mensaje -->
				<div class="form-row">
					<div class="col error">
						<c:out value="${requestScope.error}"></c:out>
					</div>
				</div>

			</div>
</body>
</html>
