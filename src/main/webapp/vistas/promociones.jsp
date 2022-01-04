<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Promociones</title>

<jsp:include page="../parciales/librerias.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
	href="/Webapp_Proyecto_Final/css/botones.css">
<link rel="stylesheet" type="text/css"
	href="/Webapp_Proyecto_Final/css/estiloPeliculasYPromos.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"
	defer></script>
<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/filtrarGeneros.js" defer></script>
<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/jquery.pajinate.js" defer></script>
<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/jquery.resize2.js" defer></script>
<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/funciones.js" defer></script>

</head>

<body>
	<header>
		<!-- ELEMENTO HEADER -->
		<jsp:include page="../parciales/header.jsp"></jsp:include>
	</header>

	<main>

		<!-- MODAL INICIO SESION -->
		<jsp:include page="../parciales/inicioSesionModal.jsp"></jsp:include>

		<div class="container-fluid p-0">
			<!-- TITULO -->
			<div class="h1 text-center font-lato m-0 titulo">
				<div class="animate__animated animate__backInLeft">Promociones</div>
			</div>
			<div class="triangulo"></div>



			<!-- CONTENEDOR GENERO, CARDS Y PAGINADOR -->
			<div class="container-fluid p-0 mt-5 mb-3 ">
				<div class="row flex-lg-row flex-column mx-0">
					<!-- LISTADO DE GÉNEROS -->
					<div
						class="col-xl-3 col-11 p-xxl-0 mt-xl-5 d-flex justify-content-center mx-xl-auto mx-0 columna-filtros">
						<div class="row w-100 flex-column">
							<!-- BOTONES DE ADMIN -->
							<c:if test="${usuario.esAdmin()}">
								<div
									class="row px-0 m-0 align-items-center border-bottom border-2"
									id="contenedor-btn-admin">
									<div
										class="col-xl-12 col-6 text-center my-xl-5 mt-2 mb-4 h1 text-white font-lato titulo-filtro">Menu
										administrador</div>
									<div class="col-xl-12 col-6">

										<div class="col-12 row m-0">
											<div class="col-6 p-0">
												<a type="button"
													class="boton-admin d-flex col-10 btn btn-danger text-center mx-auto align-items-center justify-content-center"
													data-bs-toggle="modal"
													data-bs-target="#modalCrearPromocion">Añadir promoción</a>
											</div>

											<div class="col-6 p-0 ">
												<a href="#" type="button"
													class="boton-admin d-flex col-10 btn btn-danger text-center mx-auto align-items-center justify-content-center">Añadir
													genero</a>
											</div>
										</div>
									</div>
								</div>
							</c:if>

							<div
								class="text-center my-xl-5 mt-2 mb-4 h1 text-white font-lato titulo-filtro">Filtrar
								por genero</div>
							<div class="overflow-auto" id="contenedor-filtros">
								<div
									class="row text-center justify-content-xl-start justify-content-center lista-generos"
									role="group" aria-label="Basic radio toggle button group">

									<div
										class="col-xl-12 col-auto mt-xl-4 mb-xl-0 mt-md-2 mb-2 mt-1">
										<input type="radio" class="btn-check genero-item"
											data-type="todos" name="btnradio" id="btn1"
											autocomplete="off"> <label
											class="boton d-flex col-10 btn btn-danger text-center mx-auto align-items-center justify-content-center"
											id="todos" for="btn1">Todas</label>
									</div>
									<c:forEach items="${generos}" var="genero">
										<div
											class="col-xl-6 p-xl-0 col-auto mt-xl-4 mb-xl-0 mt-md-2 mb-2 mt-1">
											<input type="radio" class="btn-check genero-item"
												data-type="${genero.nombre}" name="btnradio"
												id="${genero.nombre}" autocomplete="off"> <label
												class="boton d-flex col-10 btn btn-danger text-center mx-auto align-items-center justify-content-center"
												for="${genero.nombre}">${genero.nombre}</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>

					<!-- CONTENEDOR DE CARDS -->
					<div class="d-flex col-xl-9 col-12 flex-column mt-0"
						id="contenedor-paginador-xxl">

						<!-- TABLA DE CARDS -->
						<div class="row justify-content-around contenido m-0 mb-4">
							<!-- CARD -->
							<c:forEach items="${promociones}" var="promocion">
								<div
									class="inicio display-flex justify-content-center col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2"
									data-type="${promocion.genero}">
									<!-- BACKDROP IMAGEN -->
									<div class="row flex-column fondo-backdrop carta mx-auto m-0"
										data-type="${promocion.genero}"
										style="background-image: linear-gradient(130deg, rgb(0 0 0) 30%, rgb(13 13 13/ 88%) 80%);">
										<!-- IMAGEN CARD -->
										<div class="fondo-portada mx-auto"
											style="background-image: url('${promocion.urlPortada}');">
										</div>

										<!-- DESCRIPCION CARD -->
										<div class="col d-flex p-0">
											<div class="row m-0 my-2 w-100">
												<!-- TITULO CARD -->
												<div class="d-flex align-items-center" style="height: 60px">
													<p class="titulo-carta h3 mx-auto text-center text-white">
														${promocion.titulo}</p>
												</div>

												<!-- PRECIO Y DURACION CARD -->
												<div class="row text-white text-center mb-3 mx-auto">
													<div class="col-6 border-bottom border-1">
														<div class="row flex-column">
															<div class="col-12">Precio:</div>
															<span class="h5 text-danger precio-duracion">$${promocion.precio}</span>
														</div>
													</div>
													<div class="col-6">
														<div class="row flex-column border-bottom border-1">
															<div class="col-12">Duración:</div>
															<span class="h5 text-danger precio-duracion">${promocion.duracion}
																min.</span>
														</div>
													</div>
												</div>
												<!-- BOTONES CARD -->
												<div class="d-flex mt-auto justify-content-center mb-2">
													<a
														href="/Webapp_Proyecto_Final/listarDetallePromocion?id=${promocion.id}"
														class="botondes learn-more d-flex"> <span
														class="circle" aria-hidden="true"> <span
															class="icon arrow"></span>
													</span> <span class="button-text mx-auto">Detalle</span>
													</a>
												</div>

												<c:if test="${usuario.esAdmin()}">
													<div class="row p-0 m-0 my-2">
														<div
															class="col-6 d-flex justify-content-center align-items-center">
															<a href="" type="button" class="boton-card-edit"
																data-bs-toggle="modal"
																data-bs-target="#modalEditarPromocion"
																data-bs-id="${promocion.id}"
																data-bs-titulo="${promocion.titulo}"
																data-bs-genero="${promocion.genero}"
																data-bs-descripcion="${promocion.descripcion}"
																data-bs-beneficio="${promocion.beneficio}"
																data-bs-tipoPromocion="${promocion.tipoPromocion}"
																data-bs-idPelis="${promocion.getIdPelis()}"><i
																class="bi bi-pencil-square"></i></a> <span>editar</span>
														</div>
														<div
															class="col-6 d-flex justify-content-center align-items-center">
															<a type="button" data-bs-toggle="modal"
																data-bs-target="#modalEliminarPromocion"
																data-bs-id="${promocion.id}" class="boton-card-elim"><i
																class="bi bi-file-x"></i></a> <span>eliminar</span>
														</div>
													</div>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<!-- PAGINADOR -->
						<div
							class="row text-center mx-2 mt-auto pt-4 pb-4 border-top border-dark">
							<div class="page_navigation"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL CREAR PROMOCIÓN -->
		<div class="modal fade" id="modalCrearPromocion"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<!-- ENCABEZADO DEL MODAL -->
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<h2 class="align-self-center">CREAR PROMOCIÓN</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<!-- CUERPO DEL MODAL -->
					<div class="modal-body p-5 pt-0">
						<!-- FORLMULARIO DEL MODAL -->
						<form action="crearPromocion.ad" method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="titulo"
									placeholder="Titulo" required="required" name="titulo">
								<label for="titulo">Nombre</label>
								<div class="invalid-feedback">Introduzca un nombre válido</div>
							</div>
							<div class="row align-items-end mb-3">
								<div class="col-7 form-floating me-auto">
									<select onchange="cambiar()" class="form-select"
										name="tipoPromocion" id="tipoPromocion"
										aria-label="Floating label select example" required="required">
										<option value="porcentual">Super descuentos</option>
										<option value="absoluta">Precios locos</option>
										<option value="axb">Regaladas</option>
									</select> <label for="tipoPromocion">Tipo de promoción</label>
								</div>

								<div class="row col-5">
									<small id="descrip-beneficio"
										class="col-12 text-center mb-1 p-0"
										style="font-size: 10.9px; color: #e1ac0d">Ingresar un
										%</small>
									<div class="col-12 form-floating">
										<input type="number" class="form-control rounded-4"
											id="beneficio" placeholder="beneficio" required="required"
											name="beneficio"> <label for="beneficio">Beneficio</label>
										<div class="invalid-feedback">Introduzca un beneficio
											válido</div>
									</div>
								</div>
							</div>

							<div class="input-group row m-0">
								<small class="col-12 mb-1 text-center p-0"
									style="font-size: 10.9px; color: #e1ac0d;">Las
									películas aparecerán en función del género de la primer
									película seleccionada</small> <select
									class="form-select form-select-sm col-9" id="selectPelicula"
									name="pelicula" aria-label=".form-select-sm example">
									<c:forEach items="${peliculas}" var="pelicula">
										<option value="${pelicula.id}" data-type="${pelicula.genero}">${pelicula.titulo}</option>
									</c:forEach>
								</select>
								<button onclick="seleccionarPelicula()"
									class="col-3 btn btn-primary" type="button">Agregar</button>
							</div>

							<div class="form-floating row flex-column m-0 my-3">
								<textarea readonly="readonly"
									class="col-10 mx-auto form-control" placeholder="peliculas"
									name="peliculas" id="peliculas" required="required"
									style="height: 150px"></textarea>
								<label for="peliculas">Películas agregadas</label>
								<button onclick="resetear()"
									class="col-3 mx-auto mt-2 btn btn-primary" type="button">Vaciar</button>
							</div>

							<div>
								<input type="text" class="form-control d-none" id="idPeliculas"
									placeholder="idPeliculas" required="required"
									name="idPeliculas">
							</div>

							<div class="">
								<label for="fotoDePortada" class="form-label">Foto de
									portada</label> <input class="form-control" type="file"
									id="fotoDePortada">
							</div>

							<div class="form-floating my-3 col-12 mx-auto">
								<textarea class="form-control"
									placeholder="Leave a comment here" name="descripcion"
									id="descripcion" style="height: 100px"></textarea>
								<label for="descripcion">Descripción de la promoción</label>
							</div>

							<div class="row">
								<button type="submit"
									class="col-6 mx-auto mb-2 btn btn rounded-4 btn-warning">Crear
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL EDITAR PROMOCIÓN -->
		<div class="modal fade" id="modalEditarPromocion"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<!-- ENCABEZADO DEL MODAL -->
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<h2 class="align-self-center">EDITAR PROMOCIÓN</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close" onclick="resetearEdit()"></button>
					</div>

					<!-- CUERPO DEL MODAL -->
					<div class="modal-body p-5 pt-0">
						<!-- FORLMULARIO DEL MODAL -->
						<form action="editarPromocion.ad" method="post">
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4 d-none"
									id="idEdit" placeholder="Titulo" required="required"
									name="idEdit">
							</div>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4"
									id="tituloEdit" placeholder="Titulo" required="required"
									name="tituloEdit"> <label for="tituloEdit">Nombre</label>
								<div class="invalid-feedback">Introduzca un nombre válido</div>
							</div>
							<div class="row align-items-end mb-3">
								<div class="col-7 form-floating me-auto">
									<select onchange="cambiarEdit()" class="form-select"
										name="tipoPromocionEdit" id="tipoPromocionEdit"
										aria-label="Floating label select example" required="required">
										<option value="porcentual">Super descuentos</option>
										<option value="absoluta">Precios locos</option>
										<option value="axb">Regaladas</option>
									</select> <label for="tipoPromocionEdit">Tipo de promoción</label>
								</div>

								<div class="row col-5">
									<small id="descrip-beneficioEdit"
										class="col-12 text-center mb-1 p-0"
										style="font-size: 10.9px; color: #e1ac0d">Ingresar un
										%</small>
									<div class="col-12 form-floating">
										<input type="number" class="form-control rounded-4"
											id="beneficioEdit" placeholder="beneficio"
											required="required" name="beneficioEdit"> <label
											for="beneficioEdit">Beneficio</label>
										<div class="invalid-feedback">Introduzca un beneficio
											válido</div>
									</div>
								</div>
							</div>

							<div class="input-group row m-0">
								<small class="col-12 mb-1 text-center p-0"
									style="font-size: 10.9px; color: #e1ac0d;">Las
									películas aparecerán en función del género de la primer
									película seleccionada</small> <select
									class="form-select form-select-sm col-9"
									id="selectPeliculaEdit" name="selectPeliculaEdit"
									aria-label=".form-select-sm example">
									<c:forEach items="${peliculas}" var="pelicula">
										<option value="${pelicula.id}" data-type="${pelicula.genero}">${pelicula.titulo}</option>
									</c:forEach>
								</select>
								<button onclick="seleccionarPeliculaEdit()"
									class="col-3 btn btn-primary" type="button">Agregar</button>
							</div>

							<div class="form-floating row flex-column m-0 my-3">
								<textarea readonly="readonly"
									class="col-10 mx-auto form-control" placeholder="peliculas"
									name="peliculasEdit" id="peliculasEdit" required="required"
									style="height: 150px"></textarea>
								<label for="peliculasEdit">Películas agregadas</label>
								<button onclick="resetearEdit()"
									class="col-3 mx-auto mt-2 btn btn-primary" type="button">Vaciar</button>
							</div>

							<div>
								<input type="text" class="form-control d-none"
									id="idPeliculasEdit" placeholder="idPeliculas"
									required="required" name="idPeliculasEdit">
							</div>

							<div class="">
								<label for="fotoDePortadaEdit" class="form-label">Foto
									de portada</label> <input class="form-control" type="file"
									id="fotoDePortadaEdit">
							</div>

							<div class="form-floating my-3 col-12 mx-auto">
								<textarea class="form-control"
									placeholder="Leave a comment here" name="descripcionEdit"
									id="descripcionEdit" style="height: 100px"></textarea>
								<label for="descripcionEdit">Descripción de la promoción</label>
							</div>

							<div class="row">
								<button type="submit" id="botonEdit"
									class="col-6 mx-auto mb-2 btn btn rounded-4 btn-warning">Editar
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL ELIMINAR PROMOCIÓN -->
		<div class="modal fade" id="modalEliminarPromocion"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel1" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel1">Atención</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">Estas a punto de eliminar esta
						promoción. ¿Estas seguro?</div>
					<div class="modal-footer">
						<a id="botonElim" type="button" href="" class="btn btn-primary">Aceptar</a>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

	</main>

	<footer>
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="../parciales/footer.jsp"></jsp:include>
	</footer>
</body>
</html>