<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="es">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet"
	href="/Webapp_Proyecto_Final/css/estilosCompartidos.css">


<title>Usuarios Registrados</title>
<link rel="shortcut icon" href="img/logo.png" sizes="75px;">

<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/modales.js"></script>
<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/completarModales.js" defer></script>

</head>
<body>
	<header>
		<jsp:include page="../parciales/header.jsp"></jsp:include>
	</header>

	<main
		style="background-image: url('/Webapp_Proyecto_Final/imagenes/spikes.png');">

		<!-- MODAL ERROR -->
		<jsp:include page="../parciales/modalesCompra.jsp"></jsp:include>

		<div class="container pt-5 " style="min-height: 100vh;">
			<div class="f1 h1 text-center mt-5 mb-3 font-lato">Tabla de
				Usuarios</div>
			<div class="row align-items-start justify-content-center mt-4">

				<table class="ms-2 table table-dark table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nombre</th>
							<th scope="col">Usuario</th>
							<th scope="col">Handle</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuarios}" var="user">
							<tr>
								<th scope="row">${user.id}</th>
								<td>${user.nombre}</td>
								<td>${user.usuario}</td>
								<td><a href=""><i class="far fa-eye"></i></a> <a
									href="/Webapp_Proyecto_Final/listarItinerario.do?id=${user.id}"><i
										class="fas fa-shopping-cart"></i></a> <a href=""><i
										class="fas fa-pencil-alt"></i></a> <a href=""
									data-bs-toggle="modal" data-bs-target="#modalEliminarUsuario"
									data-bs-id="${user.id}"> <i class="fas fa-trash-alt"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row">
				<button type="button"
					class="btn btn-outline-primary col-lg-2 col-sm-3 col-4 px-0 py-2"
					data-bs-toggle="modal" data-bs-target="#modalCrearUsuario">Crear
					Usuario</button>
			</div>
		</div>

		<!-- MODAL CREAR USUARIO -->
		<div class="modal fade" id="modalCrearUsuario"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<!-- ENCABEZADO DEL MODAL -->
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<h2 class="align-self-center">Crear Usuario</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<!-- CUERPO DEL MODAL -->
					<div class="modal-body p-5 pt-0">
						<!-- FORLMULARIO DEL MODAL -->
						<form action="crearUsuario.ad" method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="nombre"
									placeholder="Nombre" required="required" name="nombre"
									value="${usuarioCrear.nombre}"> <label
									class='${usuarioCrear.errores.get("nombre") != null ? "is-invalid" : ""}'
									for="usuario">Nombre y apellido</label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioCrear.errores.get("nombre")}'></c:out>
								</div>
							</div>

							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="usuario"
									placeholder="User" required="required" name="usuario"
									value="${usuarioCrear.usuario}"> <label
									class='${usuarioCrear.errores.get("usuario") != null ? "is-invalid" : ""}'
									for="usuario">Usuario</label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioCrear.errores.get("usuario")}'></c:out>
								</div>
							</div>

							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4"
									id="contrasena" placeholder="Password" required="required"
									name="contrasena"> <label
									class='${usuarioCrear.errores.get("contrasena") != null ? "is-invalid" : ""}'
									for="contrasena">Contraseña</label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioCrear.errores.get("contrasena")}'></c:out>
								</div>
							</div>

							<div class="form-check">
								<input class="form-check-input" type="radio" value="admin"
									name="admin" id="administrador"> <label
									class="form-check-label" for="administrador">
									Administrador </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" value="usuario"
									name="admin" id="noAdministrador" checked> <label
									class="form-check-label" for="noAdministrador"> Usuario
								</label>
							</div>

							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4" id="dinero"
									placeholder="Dinero" required="required" name="dinero"
									value="${usuarioCrear.dineroDisponible}"> <label
									class='${usuarioCrear.errores.get("dinero") != null ? "is-invalid" : ""}'
									for="dinero">Dinero</label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioCrear.errores.get("dinero")}'></c:out>
								</div>
							</div>

							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4" id="tiempo"
									placeholder="Tiempo" required="required" name="tiempo"
									value="${usuarioCrear.tiempoDisponible}"> <label
									class='${usuarioCrear.errores.get("tiempo") != null ? "is-invalid" : ""}'
									for="tiempo">Tiempo</label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioCrear.errores.get("tiempo")}'></c:out>
								</div>
							</div>
							<div>
								<select class="form-select" aria-label="Default select example"
									name="genero" id="genero">
									<option selected>Elegir una preferencia</option>
									<c:forEach items="${generos}" var="genero" varStatus="itemLoop">
										<option value="${genero.nombre}"><c:out
												value="${genero.nombre}"></c:out></option>
									</c:forEach>

								</select> <label
									class='${usuarioCrear.errores.get("genero") != null ? "is-invalid" : ""}'
									for="genero"></label>
								<div class="invalid-tooltip">
									<c:out value='${usuarioCrear.errores.get("genero")}'></c:out>
								</div>
							</div>

							<div class="mb-3 mt-3">
								<label for="fotoPerfil" class="form-label">Foto de
									perfil</label> <input class="form-control" type="file" id="fotoPerfil">
							</div>

							<button type="submit"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Crear
								Usuario</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL ELIMINAR USUARIO -->
		<div class="modal fade" id="modalEliminarUsuario"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel1" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content bg-warning">
					<div class="modal-header border-0">
						<h5 class="modal-title mx-auto fw-bold" id="exampleModalLabel"
							style="text-decoration: underline;">¡ATENCIÓN!</h5>
					</div>
					<div class="modal-body mx-auto fw-bold">Estas a punto de
						eliminar este usuario. ¿Estas seguro?</div>
					<div class="modal-footer d-flex border-0 justify-content-center">
						<a id="botonElim" type="button" href="" class="btn btn-success">Aceptar</a>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<jsp:include page="../parciales/footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
		abrirModalUsuario('${serv}');
	</script>
</body>
</html>