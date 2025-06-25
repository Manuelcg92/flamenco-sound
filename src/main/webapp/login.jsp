<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>Inicio de sesión</title>
</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>Sistema de gestión de usuarios
				</h3>
				<h2>&nbsp;</h2>
			</div>

			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form id="login-usuarios" class="credentials" method="post"
									action="${pageContext.request.contextPath}/usuarios/login"
									autocomplete="off">

									<h2>Acceso de usuarios</h2>
									Nombre de usuario:
									<div class="input-group" style="margin-top: 10px;">

										<input name="usuario" type="text"
											class="form-control fontAwesome" placeholder="Usuario"
											required />
									</div>
									<p></p>
									Contraseña:
									<div class="input-group" style="margin-top: 10px;">

										<input name="password" type="password"
											class="form-control fontAwesome" placeholder="Contraseña"
											required />

									</div>
									<p></p>
									<div
										style="display: flex; justify-content: space-between; align-items: center;">
										<a href="${pageContext.request.contextPath}/usuarios/alta">NUEVO
											REGISTRO</a>
										<button type="submit" class="btn btn-info">Login</button>
									</div>
									<c:if test="${not empty error}">
										<div class="text-danger">${error}</div>
									</c:if>
								</form>
							</div>

						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

</body>
</html>
