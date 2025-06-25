<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Confirmar Compra de Entrada</title>
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

			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-ticket"></i> Confirmar Compra de Entrada
				</h3>
			</div>

			<div class="form-row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/eventos/lista">Volver</a>
				</div>
			</div>

			<p></p>

			<div class="release">
				<span class="welcome">DETALLES DEL EVENTO</span>
				<table class="table table-bordered table-striped"
					style="width: 100%; font-size: small;">
					<tbody>
						<tr>
							<th class="tableHeaderAll" style="width: 30%;">Nombre:</th>
							<td><c:out value="${evento.nombre}" /></td>
						</tr>
						<tr>
							<th class="tableHeaderAll">Descripción:</th>
							<td><c:out value="${evento.descripcion}" /></td>
						</tr>
						<tr>
							<th class="tableHeaderAll">Lugar:</th>
							<td><c:out value="${evento.lugar}" /></td>
						</tr>
						<tr>
							<th class="tableHeaderAll">Duración:</th>
							<td><c:out value="${evento.duracion}" /></td>
						</tr>
						<tr>
							<th class="tableHeaderAll">Tipo de Evento:</th>
							<td><c:out value="${evento.tipoEvento}" /></td>
						</tr>
						<tr>
							<th class="tableHeaderAll">Entradas Disponibles:</th>
							<td><c:out value="${evento.asientosDisponibles}" /></td>
						</tr>
					</tbody>
				</table>
			</div>

			<p></p>

			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<div class="form-body">
									<div class="form-group">
										<div>
											<form:form
												action="${pageContext.request.contextPath}/entradas/comprarEntrada"
												method="post" modelAttribute="entrada" class="credentials">
                								Información de la Entrada
                								<p></p>

												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text"><i
															class="fa fa-user"></i></span>
													</div>
													<input type="text" class="form-control"
														value="Comprador: ${usuario.nombre} ${usuario.apellido}"
														readonly />
												</div>
												<p></p>

												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text"><i class="fa fa-eur"></i></span>
													</div>
													<input type="text" class="form-control"
														value="Precio por Entrada: <c:out value="${entrada.precio}" /> €"
														readonly />
												</div>
												<p></p>

												<form:hidden path="evento.eventoId" />
												<form:hidden path="usuario.usuarioId" />

												<form:hidden path="precio" />

												<form:errors path="*" class="text-danger" />
												<c:if test="${not empty error}">
													<div class="alert alert-danger text-center" role="alert"
														style="color: red;">${error}</div>
												</c:if>

												<p></p>
												<span class="input-group-btn">
													<button type="submit" class="btn btn-success btn-lg">
														<i class="fa fa-check-circle"></i> Confirmar Compra
													</button>
												</span>
											</form:form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>