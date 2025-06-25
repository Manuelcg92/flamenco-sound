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

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Grupos Musicales</title>

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
		
			<p>
				<br>
			</p>

			<!-- Espacio para la lista de Grupos musicales -->
			<div class="release">
				<span class="welcome">LISTADO DE GRUPOS MUSICALES</span> <br> <br>

				<table class="table table-bordered table-striped"
					style="width: 100%; font-size: small;">
					<thead class="tableHeaderAll">
						<tr>
							<th>Id</th>
							<th>Nombre del grupo</th>
							<th>Año de creación</th>
							<th>Origen</th>
							<th>Género musical</th>
							<th>Acciones</th>
							<!-- SIEMPRE se muestra la cabecera -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="grupoTemp" items="${grupos}">
							<c:url var="linkDetalle" value="detalle">
								<c:param name="grupoId" value="${grupoTemp.grupoId}" />
							</c:url>
							<c:url var="linkActualizar" value="modificar">
								<c:param name="grupoId" value="${grupoTemp.grupoId}" />
							</c:url>
							<c:url var="linkEliminar" value="eliminar">
								<c:param name="grupoId" value="${grupoTemp.grupoId}" />
							</c:url>

							<tr class="tableHeaderUser">
								<td>${grupoTemp.grupoId}</td>
								<td>${grupoTemp.nombre}</td>
								<td>${grupoTemp.creacion}</td>
								<td>${grupoTemp.origen}</td>
								<td>${grupoTemp.genero}</td>
								<td><a href="${linkDetalle}"
									class="btn btn-info btn-sm btn-detalle"><i
										class="fa fa-search"></i></a> <c:if test="${sessionScope.admin}">
									&nbsp;|&nbsp;
									<a href="${linkActualizar}"
											class="btn btn-info btn-sm btn-modificar"> <i
											class="fa fa-pencil"></i>
										</a>
										<a href="${linkEliminar}"
											class="btn btn-info btn-sm btn-eliminar"
											onclick="return confirm('¿Estás seguro de que deseas eliminar este grupo?')">
											<i class="fa fa-trash"></i>
										</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


			<p></p>

			<!-- Espacio para el botón de Añadir grupo -->
			<c:if test="${sessionScope.admin}">
				<div class="form-row">
					<div class="col-1"></div>
					<span class="input-group-btn">
						<button class="btn btn-info" onclick="window.location.href='alta'">
							<i class="fa fa-plus"> Añadir Grupo</i>
						</button>
					</span>
				</div>
			</c:if>


		</div>
	</div>

</body>

</html>