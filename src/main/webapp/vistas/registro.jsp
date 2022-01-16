<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/Webapp_Proyecto_Final/css/registro.css">

<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/modales.js"></script>

<title>Registro</title>
</head>
<body>
	<main>
		<div class="container d-flex align-items-center"
			style="min-height: 100vh;">

			<div class="col m-0 p-0">
				<div class="row m-0 p-0 mb-4">
					<h1
						class="col-md-8 col-11 mx-auto text-center text-white font-lato titulo-form">Formulario
						de registro</h1>
				</div>

				<div class="row m-0 p-0">
					<div class="col-md-8 col-11 mx-auto">
						<form action="" method="post">
							<div class="form-floating mb-4">
								<input type="text"
									class="form-control rounded-4 font-merriweather" id="nombre"
									placeholder="Nombre" required="required" name="nombre"
									value='${usuarioTemp.nombre}${datosPrecargados.get("nombre")}'>
								<label
									class='${usuarioTemp.errores.get("nombre") != null ? "is-invalid" : ""}'
									for="usuario">Ingrese su nombre y apellido</label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioTemp.errores.get("nombre")}'></c:out>
								</div>
							</div>

							<div class="form-floating mb-4">
								<input type="text"
									class="form-control rounded-4 font-merriweather" id="usuario"
									placeholder="User" required="required" name="usuario"
									value='${usuarioTemp.usuario}${datosPrecargados.get("usuario")}'>
								<label
									class='${usuarioTemp.errores.get("usuario") != null || usuarioExistente != null ? "is-invalid" : ""}'
									for="usuario">Ingrese un nick de usuario</label>
								<div class="invalid-tooltip">
									<c:out
										value='${usuarioTemp.errores.get("usuario")} ${usuarioExistente}'></c:out>
								</div>
							</div>

							<div class="form-floating mb-4">
								<input type="password"
									class="form-control rounded-4 font-merriweather"
									id="contrasena" placeholder="Password" required="required"
									name="contrasena"> <label
									class='${usuarioTemp.errores.get("contrasena") != null || erroresContrasena.get("contrasenaNueva") !=null ? "is-invalid" : ""}'
									for="contrasena">Ingrese su contraseña</label>
								<div class="invalid-tooltip">
									<c:out
										value='${usuarioTemp.errores.get("contrasena")} ${erroresContrasena.get("contrasenaNueva")}'></c:out>
								</div>
							</div>
							<div class="form-floating mb-4">
								<input type="password"
									class="form-control rounded-4 font-merriweather"
									id="contrasenaRepetida" placeholder="Password"
									required="required" name="contrasenaRepetida"> <label
									class='${usuarioTemp.errores.get("contrasena") != null || erroresContrasena.get("contrasenaRepetida") !=null ? "is-invalid" : ""}'
									for="contrasenaRepetida">Repita la contraseña</label>
								<div class="invalid-tooltip">
									<c:out
										value='${usuarioTemp.errores.get("contrasena")} ${erroresContrasena.get("contrasenaRepetida")}'></c:out>
								</div>
							</div>

							<div class="form-floating mb-4">
								<select class="form-select pt-2 font-merriweather"
									aria-label="Default select example" name="genero" id="genero">
									<option selected="selected">Elija su genero preferido
									</option>
									<c:forEach items="${generos}" var="genero">
										<option value="${genero.nombre}">${genero.nombre}</option>
									</c:forEach>
								</select> <label
									class='${usuarioTemp.errores.get("genero") != null ? "is-invalid" : ""}'
									for="genero"></label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioTemp.errores.get("genero")}'></c:out>
								</div>
							</div>

							<button type="submit"
								class=" boton w-100 mt-4 btn btn-lg rounded-4 btn-warning">Registrarse</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>