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

<title>Grupos Musicales - Detalle</title>


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

	<!-- Espacio de cabecera -->
	<div class="form-header">
		<h3 class="registration">
			<i class="fa fa-user"></i>Detalles del grupo
		</h3>
	</div>

	<!-- Espacio de enlace de Volver -->
	<div class="form-row">
		<div class="col">
			<a href="${pageContext.request.contextPath}/grupos/lista">Volver</a>
		</div>
	</div>

	<p></p>

	<!-- Espacio para los datos del grupo -->
	<div class="form-row">
		<div class="col-1"></div>
		<div class="col-10">
			<div class="form-body">
				<div class="form-group">
					<div>
						<form role="form" id="datos-usuario" autocomplete="off"
							class="credentials">
							<div class="input-group">Id del grupo:
								${detalleGrupo.grupoId}</div>
							<div class="input-group">Nombre del grupo:
								${detalleGrupo.nombre}</div>
							<div class="input-group">Año de creación del grupo:
								${detalleGrupo.creacion}</div>
							<div class="input-group">Lugar de origen del grupo:
								${detalleGrupo.origen}</div>
							<div class="input-group">Género musical del grupo:
								${detalleGrupo.genero}</div>

						</form>
					</div>

				</div>
			</div>

		</div>

		<div class="col-1"></div>
	</div>

	<p>
		<br>
	</p>

	<!-- Espacio para la lista de componentes -->
	<c:if test="${not empty detalleGrupo.componentes}">

		<div class="release">
			<span class="welcome">LISTADO DE COMPONENTES DEL GRUPO MUSICAL</span>
			<br> <br>

			<table class="table table-bordered table-striped"
				style="width: 100%; font-size: small; text-align: all-center">
				<thead class="tableHeaderAll">
					<tr>
						<th>Nombre del componente</th>
						<th>Instrumento</th>
						<c:if test="${sessionScope.admin}">
							<th>Acciones</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="componente" items="${detalleGrupo.componentes}">
						<tr>
							<td>${componente.nombre}</td>
							<td>${componente.instrumento}</td>
							<c:if test="${sessionScope.admin}">
								<td><a
									href="${pageContext.request.contextPath}/componentes/modificar?componenteId=${componente.componenteId}"
									class="btn btn-info btn-sm btn-modificar"> <i
										class="fa fa-pencil"></i>
								</a> <a
									href="${pageContext.request.contextPath}/componentes/eliminar?componenteId=${componente.componenteId}"
									class="btn btn-info btn-sm btn-eliminar"
									onclick="return confirm('¿Estás seguro de que deseas eliminar este componente?')">
										<i class="fa fa-trash"></i>
								</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</c:if>

	<!-- Espacio para el botón de Añadir grupo -->
	<c:if test="${sessionScope.admin}">
		<div class="form-row">
			<div class="col-1"></div>
			<span class="input-group-btn">
				<button class="btn btn-info"
					onclick="window.location.href='${pageContext.request.contextPath}/grupos/alta?grupoId=${detalleGrupo.grupoId}'">
					<i class="fa fa-plus">Añadir Grupo</i>
				</button>
			</span>
		</div>
	</c:if>
</body>
</html>