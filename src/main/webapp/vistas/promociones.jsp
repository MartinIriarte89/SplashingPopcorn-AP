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
	href="/TP_3_SplashingPopcorn_Entrega_Final2/css/botones.css">
<link rel="stylesheet" type="text/css"
	href="/TP_3_SplashingPopcorn_Entrega_Final2/css/estiloPeliculasYPromos.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" defer></script>
<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/filtrarGeneros.js" defer></script>
<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/jquery.pajinate.js" defer></script>
<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/jquery.resize2.js" defer></script>
<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/funciones.js" defer></script>

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
								<div class="row px-0 m-0 align-items-center border-bottom border-2" id="contenedor-btn-admin">
									<div
										class="col-xl-12 col-6 text-center my-xl-5 mt-2 mb-4 h1 text-white font-lato titulo-filtro">Menu
										administrador</div>
									<div class="col-xl-12 col-6">

										<div class="col-12 row m-0">
											<div class="col-6 p-0">
												<a type="button"
													class="boton-admin d-flex col-10 btn btn-danger text-center mx-auto align-items-center justify-content-center"
													data-bs-toggle="modal" data-bs-target="#modalCrearPromocion">Añadir
													promoción</a>
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
					<div
						class="d-flex col-xl-9 col-12 flex-column mt-0"
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
														href="/TP_3_SplashingPopcorn_Entrega_Final2/listarDetallePromocion?id=${promocion.id}"
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
																data-bs-target="#modalEditarPelicula"
																data-bs-id="${promocion.id}"
																data-bs-titulo="${promocion.titulo}"
																data-bs-precio="${promocion.precio}"
																data-bs-duracion="${promocion.duracion}"
																data-bs-stock="${promocion.stock}"
																data-bs-genero="${promocion.genero}"
																data-bs-descripcion="${promocion.descripcion}"><i
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
		<div class="modal fade" id="modalCrearPromocion" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<img alt="" src="imagenes/logo.png"
								style="width: 30%; margin: -20px;">
							<h2 class="align-self-center">Crear promoción</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body p-5 pt-0">
						<form
							action="/TP_3_SplashingPopcorn_Entrega_Final2/promocion/crearPromocion.ad"
							method="post">

							<label for="titulo">Titulo de promoción</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="titulo"
									name="titulo" required value="${promocion.titulo}">
								<div class="invalid-feedback">Introduzca su nombre por
									favor</div>
							</div>

							<div class="form-floating mt-5 mb-3">
								<label for="pelicula1">Elija una película:</label> <input
									list="listaPeliculas" id="pelicula1" name="pelicula1" />

								<datalist id="listaPeliculas">
									<c:forEach items="${peliculas}" var="pelicula1">
										<option value="${pelicula1.titulo}">
									</c:forEach>
								</datalist>

							</div>
							<div class="form-floating mt-5 mb-3">
								<label for="pelicula2">Elija una película:</label> <input
									list="listaPeliculas" id="peliculasEnPromos"
									name="peliculasEnPromos" />

								<datalist id="listaPeliculas">
									<c:forEach items="${peliculas}" var="pelicula2">
										<option value="${pelicula2.titulo}">
									</c:forEach>
								</datalist>

							</div>
							<div class="form-floating mt-5 mb-3">
								<label for="pelicula3">Elija una película:</label> <input
									list="listaPeliculas" id="pelicula3" name="peliculasEnPromos" />

								<datalist id="listaPeliculas">
									<c:forEach items="${peliculas}" var="pelicula3">
										<option value="${pelicula3.titulo}">
									</c:forEach>
								</datalist>

							</div>

							<label for="descripcion">Descripción de promoción</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4"
									id="descripcion" name="descripcion" required
									value="${promocion.descripcion}">
								<div class="invalid-feedback">Introduzca la descripción
									por favor</div>
							</div>

							<div class="form-floating mb-3">
								<label for="urlPortada">Foto de portada</label> <input
									type="file" class="form-control" id="urlPortada"
									name="urlPortada" required value="${promocion.urlPortada}">
								<div class="invalid-feedback">Introduzca una imagen por
									favor</div>
							</div>

							<button type="submit"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Crear
								promoción</button>

						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL EDITAR PROMOCIÓN -->
		<div class="modal fade" id="modalEditarPromocion" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<img alt="" src="imagenes/logo.png"
								style="width: 30%; margin: -20px;">
							<h2 class="align-self-center">Editar promoción</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body p-5 pt-0">
						<form
							action="/TP_3_SplashingPopcorn_Entrega_Final2/promocion/editarPromocion.ad"
							method="post">
							<label for="titulo">Titulo de promoción</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="titulo"
									name="titulo" required value="${promocion.titulo}">
								<div class="invalid-feedback">Introduzca su nombre por
									favor</div>
							</div>

							<div class="form-floating mt-5 mb-3">
								<label for="pelicula1">Elija una película</label> <input
									list="listaPeliculas" id="pelicula1" name="peliculasEnPromos" />

								<datalist id="listaPeliculas">
									<c:forEach items="${peliculas}" var="pelicula1">
										<option value="${pelicula1.titulo}">
									</c:forEach>
								</datalist>

							</div>
							<div class="form-floating mt-5 mb-3">
								<label for="pelicula2">Elija una película</label> <input
									list="listaPeliculas" id="pelicula2" name="peliculasEnPromos" />

								<datalist id="listaPeliculas">
									<c:forEach items="${peliculas}" var="pelicula2">
										<option value="${pelicula2.titulo}">
									</c:forEach>
								</datalist>

							</div>
							<div class="form-floating mt-5 mb-3">
								<label for="pelicula3">Elija una película</label> <input
									list="listaPeliculas" id="pelicula3" name="peliculasEnPromos" />

								<datalist id="listaPeliculas">
									<c:forEach items="${peliculas}" var="pelicula3">
										<option value="${pelicula3.titulo}">
									</c:forEach>
								</datalist>

							</div>
							<label for="descripcion">Descripción de promoción</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4"
									id="descripcion" name="descripcion" required
									value="${promocion.descripcion}">
								<div class="invalid-feedback">Introduzca la descripción
									por favor</div>
							</div>

							<div class="form-floating mb-3">
								<label for="urlPortada">Foto de portada</label> <input
									type="file" class="form-control" id="urlPortada"
									name="urlPortada" required value="${promocion.urlPortada}">
								<div class="invalid-feedback">Introduzca una imagen por
									favor</div>
							</div>


							<button type="submit"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Guardar
								cambios</button>

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