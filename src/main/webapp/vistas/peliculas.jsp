<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="servicios.ServicioPelicula"%>
<jsp:useBean id="servicioPelicula" class="servicios.ServicioPelicula" />
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
<link rel="stylesheet" 
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
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
				<div class="animate__animated animate__backInLeft">Películas</div>
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
												<a href="#" type="button"
													class="boton-admin d-flex col-10 btn btn-danger text-center mx-auto align-items-center justify-content-center"
													data-bs-toggle="modal" data-bs-target="#modalCrearPelicula">Añadir
													película</a>
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
							<c:forEach items="${peliculas}" var="pelicula">
								<div
									class="inicio display-flex justify-content-center col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2"
									id="${pelicula.titulo}" data-type="${pelicula.genero}">
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
																data-bs-id="${pelicula.id}"
																data-bs-titulo="${pelicula.titulo}"
																data-bs-lema="${pelicula.getLema()}"
																data-bs-precio="${pelicula.precio}"
																data-bs-duracion="${pelicula.duracion}"
																data-bs-stock="${pelicula.stock}"
																data-bs-genero="${pelicula.genero}"
																data-bs-anioLanzamiento="${pelicula.anioLanzamiento}"
																data-bs-descripcion="${pelicula.descripcion}"><i
																class="bi bi-pencil-square"></i></a> <span>editar</span>
														</div>
														<div
															class="col-6 d-flex justify-content-center align-items-center">
															<a type="button" data-bs-toggle="modal"
																data-bs-target="#modalEliminarPelicula"
																data-bs-id="${pelicula.id}" class="boton-card-elim"><i
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

		<c:if test="${param.id != null }">
			<c:set var="pelicula"
				value="${servicioPelicula.buscarPor(Integer.parseInt(param.id))}"></c:set>
		</c:if>
		<!-- MODAL CREAR PELÍCULA -->
		<div class="modal fade" id="modalCrearPelicula"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content rounded-5 shadow">
					<!-- ENCABEZADO DEL MODAL -->
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<h2 class="align-self-center">CREAR PELÍCULA</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<!-- CUERPO DEL MODAL -->
					<div class="modal-body p-5 pt-0">
						<!-- FORLMULARIO DEL MODAL -->
						<form action="crearPelicula.ad" method="post">
							<div class="row mb-3">
								<div class="col-6 form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="titulo"
										placeholder="Titulo" required="required" name="titulo">
									<label for="titulo">Título</label>
									<div class="invalid-feedback">Introduzca un título válido</div>
								</div>

								<div class="col-6 form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="lema"
										placeholder="Lema" required="required" name="lema"> <label
										for="lema">Lema</label>
									<div class="invalid-feedback">Introduzca un texto válido</div>
								</div>

							</div>
							<div class="row mb-3">
								<div class="col-4 form-floating mb-3">
									<input type="number" class="form-control rounded-4" id="precio"
										placeholder="Precio" required="required" name="precio">
									<label for="precio">Precio</label>
									<div class="invalid-feedback">Introduzca un precio válido</div>
								</div>


								<div class="col-4 form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="duracion" placeholder="Duracion" required="required"
										name="duracion"> <label for="duracion">Duración
										en min.</label>
									<div class="invalid-feedback">Introduzca una duración
										válida</div>
								</div>
								<div class="col-4 form-floating mb-3">
									<input type="number" class="form-control rounded-4" id="stock"
										placeholder="Stock" required="required" name="stock">
									<label for="stock">Stock</label>
									<div class="invalid-feedback">Introduzca una stock válido</div>
								</div>
							</div>

							<div class="row mb-3">
								<div class="col-6 form-floating mb-3">
									<select class="form-select" name="genero" id="floatingSelect"
										aria-label="Floating label select example">
										<c:forEach items="${generos}" var="genero">
											<option value="${genero.nombre}">${genero.nombre}</option>
										</c:forEach>
									</select> <label for="floatingSelect">Género</label>
								</div>

								<div class="col-4 mx-auto form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="anioLanzamiento" placeholder="AnioLanzamiento"
										required="required" name="anioLanzamiento"> <label
										for="anioLanzamiento">Año de estreno</label>
									<div class="invalid-feedback">Introduzca un año válido</div>
								</div>
							</div>

							<div class="row mb-3">
								<div class=" col-md-6 col-12">
									<label for="fotoDeFondo" class="form-label">Foto de
										fondo</label> <input class="form-control" type="file" id="fotoDeFondo">
								</div>
								<div class=" col-md-6 col-12">
									<label for="fotoDePortada" class="form-label">Foto de
										portada</label> <input class="form-control" type="file"
										id="fotoDePortada">
								</div>
							</div>
							<div class="form-floating my-3 col-md-10 col-12 mx-auto">
								<textarea class="form-control"
									placeholder="Leave a comment here" name="descripcion"
									id="descripcion" style="height: 100px"></textarea>
								<label for="descripcion">Descripción</label>
							</div>

							<hr class="my-4">

							<div class="row">
								<button type="submit"
									class="col-md-4  col-8 mx-auto mb-2 btn btn-lg rounded-4 btn-warning">Crear
									película</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL EDITAR PELÍCULA -->
		<div class="modal fade" id="modalEditarPelicula"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content rounded-5 shadow">
					<!-- ENCABEZADO DEL MODAL -->
					<div class="modal-header p-5 pb-4 border-bottom-0">
						<div class="d-inline-flex">
							<h2 class="align-self-center">EDITAR PELÍCULA</h2>
						</div>

						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<!-- CUERPO DEL MODAL -->
					<div class="modal-body p-5 pt-0">
						<!-- FORLMULARIO DEL MODAL -->
						<form action="editarPelicula.ad" method="post">
							<div class="col-6 form-floating mb-3">
									<input type="text" class="form-control rounded-4 d-none"
										id="idEdit" placeholder="Titulo" required="required"
										name="id">
								</div>
							
							<div class="row mb-3">
								<div class="col-6 form-floating mb-3">
									<input type="text" class="form-control rounded-4"
										id="tituloEdit" placeholder="Titulo" required="required"
										name="titulo"> <label for="tituloEdit">Título</label>
									<div class="invalid-feedback">Introduzca un título válido</div>
								</div>

								<div class="col-6 form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="lemaEdit"
										placeholder="Lema" required="required" name="lema"> <label
										for="lemaEdit">Lema</label>
									<div class="invalid-feedback">Introduzca un texto válido</div>
								</div>

							</div>
							<div class="row mb-3">
								<div class="col-4 form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="precioEdit" placeholder="Precio" required="required"
										name="precio"> <label for="precioEdit">Precio</label>
									<div class="invalid-feedback">Introduzca un precio válido</div>
								</div>


								<div class="col-4 form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="duracionEdit" placeholder="Duracion" required="required"
										name="duracion"> <label for="duracionEdit">Duración
										en min.</label>
									<div class="invalid-feedback">Introduzca una duración
										válida</div>
								</div>
								<div class="col-4 form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="stockEdit" placeholder="Stock" required="required"
										name="stock"> <label for="stockEdit">Stock</label>
									<div class="invalid-feedback">Introduzca una stock válido</div>
								</div>
							</div>

							<div class="row mb-3">
								<div class="col-6 form-floating mb-3">
									<select class="form-select" name="genero" id="generoEdit"
										aria-label="Floating label select example">
										<c:forEach items="${generos}" var="genero">
											<option value="${genero.nombre}">${genero.nombre}</option>
										</c:forEach>
									</select> <label for="generoEdit">Género</label>
								</div>

								<div class="col-4 mx-auto form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="anioLanzamientoEdit" placeholder="AnioLanzamiento"
										required="required" name="anioLanzamiento"> <label
										for="anioLanzamientoEdit">Año de estreno</label>
									<div class="invalid-feedback">Introduzca un año válido</div>
								</div>
							</div>

							<div class="row mb-3">
								<div class=" col-md-6 col-12">
									<label for="fotoDeFondoEdit" class="form-label">Foto de
										fondo</label> <input class="form-control" type="file"
										id="fotoDeFondoEdit">
								</div>
								<div class=" col-md-6 col-12">
									<label for="fotoDePortadaEdit" class="form-label">Foto
										de portada</label> <input class="form-control" type="file"
										id="fotoDePortadaEdit">
								</div>
							</div>
							<div class="form-floating my-3 col-md-10 col-12 mx-auto">
								<textarea class="form-control"
									placeholder="Leave a comment here" name="descripcion"
									id="descripcionEdit" style="height: 100px"></textarea>
								<label for="descripcionEdit">Descripción</label>
							</div>

							<hr class="my-4">

							<div class="row">
								<button type="submit"
									class="col-md-4  col-8 mx-auto mb-2 btn btn-lg rounded-4 btn-warning"
									id="botonEdit">Editar película</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>



		<!-- MODAL ELIMINAR PELÍCULA -->
		<div class="modal fade" id="modalEliminarPelicula"
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
						película. ¿Estas seguro?</div>
					<div class="modal-footer">
						<a id="botonElim" type="button" href="" class="btn btn-primary">Aceptar</a>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<!--href="/TP_3_SplashingPopcorn_Entrega_Final2/borrarPelicula.ad?id=${pelicula.id}"-->

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

	</main>

	<footer>
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="../parciales/footer.jsp"></jsp:include>
	</footer>
</body>
</html>