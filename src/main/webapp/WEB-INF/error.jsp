<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<title>Error - Flamenco Sound</title>

</head>

<body class="fondo">

	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/index">Inicio</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active"
						href="${pageContext.request.contextPath}/grupos/lista">Grupos</a>
					<a class="nav-link"
						href="${pageContext.request.contextPath}/eventos/buscar">Eventos</a>
					<c:if test="${sessionScope.admin}">
						<a class="nav-link"
							href="${pageContext.request.contextPath}/usuarios/lista">Lista
							usuarios</a>
					</c:if>
				</div>
				<div class="navbar-nav">
					<a class="nav-link active"
						href="${pageContext.request.contextPath}/usuarios/detalle?usuarioId=${sessionScope.usuarioId}">Mi
						perfil</a> <a class="nav-link active"
						href="${pageContext.request.contextPath}/usuarios/logout">Logout</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container mt-5">
		<div class="signup-form-container">
			<div class="form-header text-center">
				<h3 class="text-danger">
					<i class="fa fa-exclamation-triangle"></i> ¡Ha ocurrido un error!
				</h3>
			</div>

			<div class="form-body text-center mt-4">
				<p class="text-muted">Lo sentimos, se ha producido un error
					inesperado.</p>
				<p class="text-muted">

					<strong>Detalles:</strong>
					<c:out value="${requestScope.error}"></c:out>

				</p>

				<p class="text-muted">Puedes volver a la página principal o
					contactar con el soporte si el problema persiste.</p>

				<a href="${pageContext.request.contextPath}/index"
					class="btn btn-info mt-3"> <i class="fa fa-home"></i> Volver al
					Inicio
				</a>
			</div>
		</div>
	</div>

</body>
</html>
