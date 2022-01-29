<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet" href="./css/perfil.css" />

<script type="text/javascript" src="./js/modales.js"></script>
<script type="text/javascript" src="./js/completarModales.js" defer></script>

<title>Perfil || Splashing Popcorn</title>
</head>
<body>
	<header>
		<!-- ELEMENTO HEADER -->
		<jsp:include page="../parciales/header.jsp"></jsp:include>
	</header>
	<main>

		<!-- MODAL SUCCESS -->
		<div class="modal fade" id="success" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content bg-success">
					<div class="modal-body mx-auto my-4 text-white fw-bold fs-5">${success}</div>
					<div class="modal-footer d-flex border-0">
						<button type="button" class="btn btn-outline-light mx-auto"
							data-bs-dismiss="modal">Aceptar</button>
					</div>
				</div>
			</div>
		</div>

		<!-- CONTENEDOR PRINCIPAL-->
		<div
			class="container-fluid m-0 p-0 d-flex flex-column justify-content-center"
			style="min-height: 100vh;">

			<div
				class="row mx-5 p-0 flex-column flex-md-row justify-content-xl-center"
				style="margin-top: 60px;">

				<!--FOTO DE PERFIL-->
				<div
					class='d-flex col  ${usuario.esAdmin() ? "col-xl-10 col-md-12":"col-md-6 col-xl-5 pe-md-1" }'>
					<div
						class="row flex-column m-0 p-0 contenedor w-100 justify-content-between">
						<div id="fotoPerfil"
							class='col-7 col-lg-10 mx-auto rounded-circle p-0 ${usuario.esAdmin() ? "my-5":"" }'
							style="background-image: url('./${usuarioPerfil !=null ?  usuarioPerfil.urlPerfil : usuario.urlPerfil}');"></div>
						<c:if test="${!usuario.esAdmin()}">
							<button id="boton" type="button"
								class="btn btn-warning mx-auto my-4 px-1 col-4 col-sm-2 col-md-4 col-lg-3 col-xl-2 font-lato"
								data-bs-toggle="modal" data-bs-target="#modalEditarFoto">Cambiar
								foto</button>
						</c:if>
					</div>
				</div>
				<!--DATOS PERSONALES-->
				<div class="col col-md-12 col-xl-10 order-md-2 mt-1">
					<div class="row m-0 p-0 font-merriweather">
						<div id="" class="col-12 text-center contenedor titulo">
							<c:out
								value='${usuarioPerfil !=null ? usuarioPerfil.nombre : usuario.nombre}'></c:out>
						</div>
						<div class="row m-0 mt-1 p-0 contenedor justify-content-md-center">
							<div class="col-6 col-md-5 text-center texto mt-1">Usuario:</div>
							<div class="col-6 col-md-5 text-center texto mt-1">Preferencia:</div>
							<div class="col-6 col-md-5 text-center texto">
								<c:out
									value='${usuarioPerfil !=null ?  usuarioPerfil.usuario : usuario.usuario }'></c:out>
							</div>
							<div class="col-6 col-md-5 text-center texto">
								<c:out
									value='${usuarioPerfil !=null ?  usuarioPerfil.preferencia.nombre : usuario.preferencia.nombre }'></c:out>
							</div>
						</div>
						<div class="row m-0 mt-1 p-0 contenedor justify-content-md-center">
							<div class="col-6 col-md-5 text-center texto mt-1">Tu
								dinero</div>
							<div class="col-6 col-md-5 text-center texto mt-1">Tu
								tiempo</div>
							<div class="col-6 col-md-5 text-center texto texto-numero">
								<c:out
									value='$${usuarioPerfil !=null ?  usuarioPerfil.dineroDisponible : usuario.dineroDisponible }'></c:out>
							</div>
							<div class="col-6 col-md-5 text-center texto texto-numero">
								<c:out
									value='${usuarioPerfil !=null ?  usuarioPerfil.tiempoDisponible : usuario.tiempoDisponible }'></c:out>
								<span style="font-variant: small-caps;"> min.</span>
							</div>
						</div>
						<c:if test="${!usuario.esAdmin()}">
							<div class="row m-0 mt-1 p-0 contenedor">
								<div
									class="col-8 d-flex justify-content-center align-items-center texto">Editá
									tus datos personales</div>
								<div
									class="col-4 col-sm-2 text-center font-lato texto mt-1 py-3">
									<button id="boton" type="button" class="btn btn-warning"
										data-bs-toggle="modal"
										data-bs-target="#modalEditarDatosPersonales"
										data-bs-nombre='${usuarioPerfil !=null ?  usuarioPerfil.nombre : usuario.nombre }'
										data-bs-usuario='${usuarioPerfil !=null ?  usuarioPerfil.usuario : usuario.usuario }'
										data-bs-preferencia='${usuarioPerfil !=null ?  usuarioPerfil.preferencia.nombre : usuario.preferencia.nombre }'>Editar</button>
								</div>
							</div>
						</c:if>
					</div>
				</div>
				<!--CAMBIO DE CONTRASEÑA-->
				<c:if test="${!usuario.esAdmin()}">
					<div
						class="col col-md-6 col-xl-5 order-md-1 mt-1 mt-md-0 d-md-flex ps-md-0">
						<div
							class="row m-0 p-0 contenedor font-merriweather align-items-md-center">
							<div class="col-12 col-lg-10 mx-lg-auto text-center titulo">Cambia
								tu contraseña</div>
							<form action="./editarUsuario.do" method="post">
								<div class="col-12 col-lg-9 mx-lg-auto mb-lg-5">
									<div
										class="row m-0 p-0 mb-2 justify-content-start justify-content-sm-center">
										<label
											class='col-6 col-sm-4 col-md-12 texto text-sm-end text-md-start'
											for="contrasenaActual">Contraseña actual</label>
										<div
											class="col-6 col-sm-4 offset-sm-2  col-md-12 offset-md-0 p-0 align-self-center overflow-hidden">
											<input type="password"
												class='form-control form-control-sm p-0 ${contrasenaActual!= null ? "is-invalid" : ""}'
												id="contrasenaActual" name="contrasenaActual"
												aria-describedby="contraActual" required="required">
											<div id="contraActual" class="invalid-feedback">
												<c:out value='${contrasenaActual}'></c:out>
											</div>
										</div>

										<label
											class="col-6 col-sm-4 col-md-12 texto text-sm-end text-md-start"
											for="contrasenaNueva">Contraseña nueva</label>
										<div
											class="col-6 col-sm-4 offset-sm-2  col-md-12 offset-md-0  p-0 align-self-center overflow-hidden">
											<input type="password"
												class='form-control form-control-sm p-0 ${erroresContrasena.get("contrasenaNueva") != null ? "is-invalid" : ""}'
												id="contrasenaNueva" name="contrasenaNueva"
												aria-describedby="contraNueva" required="required">
											<div id="contraNueva" class="invalid-feedback">
												<c:out value='${erroresContrasena.get("contrasenaNueva")}'></c:out>
											</div>
										</div>

										<label
											class="col-6 col-sm-4 col-md-12 texto text-sm-end text-md-start"
											for="contrasenaRepetida">Repetir contraseña</label>
										<div
											class="col-6 col-sm-4 offset-sm-2  col-md-12 offset-md-0  p-0 align-self-center overflow-hidden">
											<input type="password"
												class='form-control form-control-sm p-0 ${erroresContrasena.get("contrasenaRepetida") != null ? "is-invalid" : ""}'
												id="contrasenaRepetida" name="contrasenaRepetida"
												aria-describedby="contraRepetida" required="required">
											<div id="contraRepetida" class="invalid-feedback">
												<c:out
													value='${erroresContrasena.get("contrasenaRepetida")}'></c:out>
											</div>
										</div>
									</div>
								</div>
								<div
									class="col-4 col-sm-2 col-md-4 col-lg-3 col-xl-2 mx-auto text-center mt-3 mb-4 mb-md-2">
									<button id="boton" type="submit"
										class="btn btn-warning font-lato">Confirmar</button>
								</div>
							</form>
						</div>
					</div>
				</c:if>

			</div>

			<!-- MODAL EDITAR USUARIO -->
			<div class="modal fade" id="modalEditarDatosPersonales"
				data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-5 shadow">
						<!-- ENCABEZADO DEL MODAL -->
						<div class="modal-header p-5 pb-4 border-bottom-0">
							<div class="d-inline-flex">
								<h2 class="align-self-center">Editar Datos Personales</h2>
							</div>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<!-- CUERPO DEL MODAL -->
						<div class="modal-body p-5 pt-0">
							<!-- FORLMULARIO DEL MODAL -->
							<form action="./editarUsuario.do" method="post">
								<div class="form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="nombre"
										placeholder="Nombre" required="required" name="nombre"
										value="${usuarioEditar.nombre}"> <label
										class='${usuarioEditar.errores.get("nombre") != null ? "is-invalid" : ""}'
										for="nombre">Nombre y apellido</label>
									<div class="invalid-feedback">
										<c:out value='${usuarioEditar.errores.get("nombre")}'></c:out>
									</div>
								</div>

								<div class="form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="usuario"
										placeholder="User" required="required" name="usuario"
										value="${usuarioEditar.usuario}"> <label
										class='${usuarioEditar.errores.get("usuario") != null ? "is-invalid" : ""}'
										for="usuario">Usuario</label>
									<div class="invalid-feedback">
										<c:out value='${usuarioEditar.errores.get("usuario")}'></c:out>
									</div>
								</div>

								<div class="form-floating my-3">
									<select class="form-select p-3"
										aria-label="Default select example" name="genero" id="genero">
										<option selected>Elegir una preferencia</option>
										<c:forEach items="${generos}" var="genero">
											<option value="${genero.nombre}">${genero.nombre}</option>
										</c:forEach>
									</select> <label
										class='${usuarioEditar.errores.get("genero") != null ? "is-invalid" : ""}'
										for="genero"></label>
									<div class="invalid-feedback">
										<c:out value='${usuarioEditar.errores.get("genero")}'></c:out>
									</div>
								</div>

								<button type="submit"
									class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Editar</button>
							</form>
						</div>
					</div>
				</div>
			</div>

			<!-- MODAL EDITAR FOTO -->
			<div class="modal fade" id="modalEditarFoto"
				data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-5 shadow">
						<!-- ENCABEZADO DEL MODAL -->
						<div class="modal-header p-5 pb-4 border-bottom-0">
							<div class="d-inline-flex">
								<h2 class="align-self-center">Editar Foto</h2>
							</div>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<!-- CUERPO DEL MODAL -->
						<div class="modal-body p-5 pt-0">
							<!-- FORLMULARIO DEL MODAL -->
							<form action="./editarUsuario.do" method="post"
								enctype="multipart/form-data">
								<div class="mb-3 ">
									<label for="formFile"
										class='form-label ${errorImagen.get("error") != null ? "is-invalid" : "" }'>Seleccione
										un formato de imagen válido</label> <input class="form-control"
										type="file" id="formFile" name="urlPerfil"
										accept="image/.jpeg,.png,.jpg,.gif,.webp">
									<div class="invalid-feedback">
										<c:out value='${errorImagen.get("error")}'></c:out>
									</div>
								</div>

								<button type="submit"
									class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Editar</button>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
	</main>
	<footer>
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="../parciales/footer.jsp"></jsp:include>
	</footer>

	<c:if test="${usuarioEditar != null }">
		<script type="text/javascript">
			abrirModalEditarDatosPersonales();
		</script>
	</c:if>
	<c:if test="${success != null}">
		<script type="text/javascript">
			abrirModalSuccess();
		</script>
	</c:if>
	<c:if test="${errorImagen != null}">
		<script type="text/javascript">
			abrirModalFoto();
		</script>
	</c:if>
</body>
</html>