<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Películas</title>

<jsp:include page="../parciales/librerias.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
	href="/TP_3_SplashingPopcorn_Entrega_Final2/css/botones.css">
<link rel="stylesheet" type="text/css"
	href="/TP_3_SplashingPopcorn_Entrega_Final2/css/estiloPeliculasYPromos.css">

<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/filtrarGeneros.js" defer></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/jquery.pajinate.js"></script>
<script type="text/javascript"
	src="/TP_3_SplashingPopcorn_Entrega_Final2/js/jquery.resize2.js"></script>

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
			<div class="h1 text-center font-lato m-0 titulo">Películas</div>
			<div class="triangulo"></div>

			<c:if test="${usuario.esAdmin()}">
				<!-- BOTONES DE USUARIOS -->
				<div id="menuAdmin"
					class="nav nav-pills position-absolute top-0 end-0">
					<a href="/TP_3_SplashingPopcorn_Entrega_Final2/crearPelicula.ad"
						type="button" class=" mb-2 btn btn-success">Crear película</a> <a
						href="/TP_3_SplashingPopcorn_Entrega_Final2/crearGenero.ad"
						type="button" class="btn btn-success">Crear género</a>

				</div>
			</c:if>

			<!-- CONTENEDOR GENERO, CARDS Y PAGINADOR -->
			<div class="container-fluid p-0">

				<div class="row flex-lg-row flex-column mx-0">

					<!-- LISTADO DE GÉNEROS -->
					<div
						class="col-xxl-3 col-xl-3 col-11 mt-5 d-flex justify-content-center mx-auto bg-warning">
						<div class="row w-100 flex-column">
							<div class="my-5 text-center border-botton">Elige un género</div>
							<div class="overflow-auto bg-dark" style="height: 500px;">
								<div class="row text-center lista-generos" role="group"
									aria-label="Basic radio toggle button group">
									<div class="col-12 mt-5">
										<input type="radio" class="btn-check genero-item" data-type="todos" name="btnradio"
											id="btn1" autocomplete="off"> <label
											class="col-5 btn btn-outline-primary" for="btn1">Todas</label>
									</div>
									<c:forEach items="${generos}" var="genero">
										<div class="col-6 mt-4">
											<input type="radio" class="btn-check genero-item" data-type="${genero.nombre}" name="btnradio"
												id="btnradio1" autocomplete="off"> <label
												class="col-10 btn btn-outline-primary" for="btnradio1">${genero.nombre}</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>

					<!-- CONTENEDOR DE CARDS -->
					<div class="d-flex col-xl-9 col-12 flex-column mt-0 mb-3"
						id="contenedor-paginador-xxl">

						<!-- TABLA DE CARDS -->
						<div class="row justify-content-around contenido m-0 mb-4">
							<!-- CARD -->
							<c:forEach items="${peliculas}" var="pelicula">
								<div
									class="display-flex justify-content-center col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2">
									<!-- BACKDROP IMAGEN -->
									<div class="row flex-column fondo-backdrop carta mx-auto m-0"
										data-type="${pelicula.genero}"
										style="background-image: linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 0.5) 80%), url('${pelicula.urlFondo}');">
										<!-- IMAGEN CARD -->
										<div class="fondo-portada mx-auto"
											style="background-image: url('${pelicula.urlPortada}');">
										</div>

										<!-- DESCRIPCION CARD -->
										<div class="col d-flex p-0">
											<div class="row m-0 my-2 w-100">
												<!-- TITULO CARD -->
												<div class="d-flex align-items-center" style="height: 60px">
													<p class="titulo-carta h3 mx-auto text-center text-white">
														${pelicula.titulo}</p>
												</div>

												<!-- PRECIO Y DURACION CARD -->
												<div class="row text-white text-center mb-3 mx-auto">
													<div class="col-6 border-bottom border-1">
														<div class="row flex-column">
															<div class="col-12">Precio:</div>
															<span class="h5 text-danger precio-duracion">$${pelicula.precio}</span>
														</div>
													</div>
													<div class="col-6">
														<div class="row flex-column border-bottom border-1">
															<div class="col-12">Duración:</div>
															<span class="h5 text-danger precio-duracion">${pelicula.duracion}
																min.</span>
														</div>
													</div>
												</div>
												<!-- BOTONES CARD -->
												<div class="d-flex mt-auto justify-content-center mb-2">
													<a
														href="/TP_3_SplashingPopcorn_Entrega_Final2/listarDetallePelicula?id=${pelicula.id}"
														class="botondes learn-more d-flex"> <span
														class="circle" aria-hidden="true"> <span
															class="icon arrow"></span>
													</span> <span class="button-text mx-auto">Ver</span>
													</a>
												</div>

												<c:if test="${usuario.esAdmin()}">
													<div class="d-flex mt-auto justify-content-center">
														<a
															href="/TP_3_SplashingPopcorn_Entrega_Final2/peliculas/editarPelicula.ad"
															class="botondes learn-more d-flex"> <span
															class="circle" aria-hidden="true" id="botonAdmin">
																<span class="icon arrow"></span>
														</span> <span class="button-text mx-auto">Editar</span>
														</a>
													</div>
													<div class="d-flex mt-auto justify-content-center">
														<a
															href="/TP_3_SplashingPopcorn_Entrega_Final2/peliculas/borrarPelicula.ad"
															class="botondes learn-more d-flex"> <span
															class="circle" aria-hidden="true" id="botonAdmin">>
																<span class="icon arrow"></span>
														</span> <span class="button-text mx-auto">Eliminar</span>
														</a>
													</div>
												</c:if>

											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<!-- PAGINADOR -->
						<div class="row text-center mt-auto pt-4 border-top border-dark">
							<div class="page_navigation"></div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- MODAL CREAR PELÍCULA -->
		<div class="modal fade" id="modalCrearPelicula" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<img alt="" src="imagenes/logo.png"
								style="width: 30%; margin: -20px;">
							<h2 class="align-self-center">Crear película</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body p-5 pt-0">
						<form
							action="/TP_3_SplashingPopcorn_Entrega_Final2/peliculas/crearPelicula.ad"
							method="post">

							<label for="titulo">Titulo de película</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="titulo"
									name="titulo" required value="${pelicula.titulo}">
								<div class="invalid-feedback">Introduzca su nombre por
									favor</div>
							</div>
							<label for="precio"
								class='col-form-label ${pelicula.errores.get("precio") != null ? "es-invalido" : "" }'>Precio</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4" id="precio"
									name="precio" required value="${pelicula.precio}">
								<div class="invalid-feedback">
									<c:out value='${pelicula.errores.get("precio")}'></c:out>
								</div>
							</div>
							<label for="duracion"
								class='col-form-label ${pelicula.errores.get("duracion") != null ? "es-invalido" : "" }'>Duración</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4"
									id="duracion" name="duracion" required
									value="${pelicula.duracion}">
								<div class="invalid-feedback">
									<c:out value='${pelicula.errores.get("duracion")}'></c:out>
								</div>
							</div>

							<label for="stock"
								class='col-form-label ${pelicula.errores.get("stock") != null ? "es-invalido" : "" }'>Stock
								disponible</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4" id="stock"
									name="stock" required value="${pelicula.stock}">
								<div class="invalid-feedback">
									<c:out value='${pelicula.errores.get("stock")}'></c:out>
								</div>
							</div>

							<label for="genero">Elija un género</label>
							<div class="form-floating mb-3">
								<select class="form-select" aria-label="Default select example">
									<c:forEach items="${generos}" var="genero">
										<option value="${genero.nombre}"></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-floating mb-3">
								<label for="urlPortada">Foto de fondo</label> <input type="file"
									class="form-control" id="urlPortada" name="urlPortada" required
									value="${pelicula.urlPortada}">
								<div class="invalid-feedback">Introduzca una imagen por
									favor</div>
							</div>
							<div class="form-floating mb-3">
								<label for="urlPortada">Foto de portada</label> <input
									type="file" class="form-control" id="urlPortada"
									name="urlPortada" required value="${pelicula.urlPortada}">
								<div class="invalid-feedback">Introduzca una imagen por
									favor</div>
							</div>
							<label for="descripcion">Descripción de película</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4"
									id="descripcion" name="descripcion" required
									value="${pelicula.descripcion}">
								<div class="invalid-feedback">Introduzca la descripción
									por favor</div>
							</div>
							<label for=anioLanzamiento>Año de estreno</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4"
									id="anioLanzamiento" name="anioLanzamiento" required
									value="${pelicula.anioLanzamiento}">
								<div class="invalid-feedback">Introduzca el año por favor</div>
							</div>
							<label for="lema">Lema de película</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="lema"
									name="lema" required value="${pelicula.lema}">
								<div class="invalid-feedback">Introduzca el lema por favor</div>
							</div>

							<button type="submit"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Crear
								película</button>

						</form>
					</div>
				</div>
			</div>
		</div>


		<!-- MODAL CREAR GÉNERO -->
		<div class="modal fade" id="modalCrearGenero" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<img alt="" src="imagenes/logo.png"
								style="width: 30%; margin: -20px;">
							<h2 class="align-self-center">Crear género</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>


					<div class="modal-body p-5 pt-0">
						<form
							action="/TP_3_SplashingPopcorn_Entrega_Final2/genero/crearGenero.ad"
							method="post">

							<label for="genero">Nuevo género</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="genero"
									name="genero" required value="${genero.nombre}">
								<div class="invalid-feedback">Introduzca el nombre del
									nuevo género por favor</div>
							</div>
							<button type="submit"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Crear
								género</button>


						</form>
					</div>
				</div>
			</div>
		</div>



		<!-- MODAL EDITAR PELÍCULA -->
		<div class="modal fade" id="modalEditarPelicula" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content rounded-5 shadow">
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<img alt="" src="imagenes/logo.png"
								style="width: 30%; margin: -20px;">
							<h2 class="align-self-center">Editar película</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body p-5 pt-0">
						<form
							action="/TP_3_SplashingPopcorn_Entrega_Final2/peliculas/editarPelicula.ad"
							method="post">
							<label for="titulo">Titulo de película</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="titulo"
									name="titulo" placeholder="${pelicula.titulo}" required
									value="${pelicula.titulo}">
								<div class="invalid-feedback">Introduzca su nombre por
									favor</div>
							</div>

							<label for="precio"
								class='col-form-label ${pelicula.errores.get("precio") != null ? "es-invalido" : "" }'>Precio</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4" id="precio"
									name="precio" placeholder="${pelicula.precio}" required
									value="${pelicula.precio}">
								<div class="invalid-feedback">
									<c:out value='${pelicula.errores.get("precio")}'></c:out>
								</div>
							</div>
							<label for="duracion"
								class='col-form-label ${pelicula.errores.get("duracion") != null ? "es-invalido" : "" }'>Duración</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4"
									id="duracion" name="duracion"
									placeholder="${pelicula.duracion}" required
									value="${pelicula.duracion}">
								<div class="invalid-feedback">
									<c:out value='${pelicula.errores.get("duracion")}'></c:out>
								</div>
							</div>


							<label for="stock"
								class='col-form-label ${pelicula.errores.get("stock") != null ? "es-invalido" : "" }'>Stock
								disponible</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4" id="stock"
									name="stock" placeholder="${pelicula.stock}" required
									value="${pelicula.stock}">
								<div class="invalid-feedback">
									<c:out value='${pelicula.errores.get("stock")}'></c:out>
								</div>
							</div>

							<label for="genero">Elija un género</label>
							<div class="form-floating mb-3">
								<select class="form-select" aria-label="Default select example">
									<c:forEach items="${generos}" var="genero">
										<option value="${genero.nombre}">"${genero.nombre}"</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-floating mb-3">
								<input type="image" class="form-control rounded-4" id="urlFondo"
									name="urlFondo" required value="${pelicula.urlFondo}">
								<label for="urlFondo">Introduzca imagen</label>
								<div class="invalid-feedback">Introduzca la imagen por
									favor</div>
							</div>
							<div class="form-floating mb-3">
								<input type="image" class="form-control rounded-4"
									id="urlPortada" name="urlPortada" required
									value="${pelicula.urlPortada}"> <label for="urlPortada">Nombre
									de película</label>
								<div class="invalid-feedback">Introduzca la imagen por
									favor</div>
							</div>

							<label for="descripcion">Descripción de película</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4"
									id="descripcion" name="descripcion"
									placeholder="${pelicula.descripcion}" required
									value="${pelicula.descripcion}">
								<div class="invalid-feedback">Introduzca la descripción
									por favor</div>
							</div>
							<label for="anioLanzamiento">Año de película</label>
							<div class="form-floating mb-3">
								<input type="number" class="form-control rounded-4"
									id="anioLanzamiento" name="descripcion"
									placeholder="${pelicula.anioLanzamiento}" required
									value="${pelicula.anioLanzamiento}">
								<div class="invalid-feedback">Introduzca el año por favor</div>
							</div>
							<label for="lema">Lema de película</label>
							<div class="form-floating mb-3">
								<input type="text" class="form-control rounded-4" id="lema"
									name="lema" placeholder="${pelicula.lema}" required
									value="${pelicula.lema}">
								<div class="invalid-feedback">Introduzca el lema por favor</div>
							</div>

							<button type="submit"
								class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Guardar
								cambios</button>

						</form>
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