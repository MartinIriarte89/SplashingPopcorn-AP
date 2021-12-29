<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/index.css">
<link rel="stylesheet" type="text/css" href="./css/botones.css">
<link rel="stylesheet" type="text/css" href="./css/detalles.css">

<script type="text/javascript" src="./js/boton_comprar_mje_error.js"
	defer></script>

<title>Detalle Promoción || Splashing Popcorn</title>

</head>
<body>
	<header>
		<!-- ELEMENTO HEADER -->
		<jsp:include page="../parciales/header.jsp"></jsp:include>
	</header>
	<main>
		<c:if test="${usuario == null }">
			<jsp:include page="../parciales/inicioSesionModal.jsp"></jsp:include>
		</c:if>
		<!-- CONTENEDOR DEL CUERPO -->
		<div class="container" id="detalles">
			<div class="row row-cols-md-2 row-cols-sm1 flex-colum">
				<!-- LADO IZQUIERZO PANTALLA: IMAGEN PELÍCULA -->
				<div class="d-flex col-sm-12 col-md-10 col-xl-9">
					<section id="cardsPeliculas" class="row">
						<div class="row contenido">
							<!-- CARDS -->
							<c:forEach items="${peliculas}" var="pelicula">

								<div class="col-6 col-md-4 col-lg-4 display-flex">
									<div class="card" data-type="${pelicula.genero}">
										<div class="card-body">
											<img src="${pelicula.urlPortada}" class="d-block w-100"
												alt="">
											<h5 class="card-title">
												<c:out value="${pelicula.titulo}"></c:out>
											</h5>
											<p class="card-text">
												<c:out value="Duración: ${pelicula.duracion}"></c:out>
											</p>
											<p class="card-text">
												<c:out value="Precio: ${pelicula.precio}"></c:out>
											</p>
											<a
												href="/TP_3_SplashingPopcorn_Entrega_Final2/listarDetallePelicula?id=${pelicula.id}"
												class="btn btn-primary" id="botonDetalles">Ver</a>

											<c:if test="${usuario.esAdmin()}">
												<a
													href="controlador/pelicula/editarPelicula?id=${pelicula.id}"
													data-bs-toggle="modal"
													data-bs-target="#modalEditarPelicula"
													class="btn btn-success" id="botonEditar">Editar</a>

												<a
													href="controlador/pelicula/borrarPelicula?id=${pelicula.id}"
													data-bs-toggle="modal" data-bs-target="#modalEliminar"
													class="btn btn-success" id="botonEliminar">Eliminar</a>

											</c:if>

										</div>
									</div>
								</div>


							</c:forEach>
						</div>
					</section>
				</div>

				<!-- LADO DERECHO: PRECIO/BOTONES -->
				<div class="d-flex col-sm-12 col-md-2 col-xl-3" id="botones">
					<div class="row row-cols-1">
						<!-- PRECIO PELÍCULA -->
						<div class="col" id="precio-bloque">
							<div class="align-self-center" id="precio">
								<h2>
									<c:out value="$ ${promocion.precio}0"></c:out>
								</h2>
							</div>
						</div>
						<!-- ACCESO COMPRA/EDICIÓN -->
						<c:choose>
							<c:when test="${usuario.esAdmin()}">
								<div class="d-flex col">
									<div class="btn-group align-self-center" role="group"
										id="acceso_compra">
										<a
											href="/TP_3_SplashingPopcorn_Entrega_Final2/promocion/editarPromocion.ad"
											role="button" class="btn btn-lg" data-bs-toggle="modal"
											data-bs-target="#modalEditar" id="boton-editar"> <strong>Editar</strong>
										</a> <a
											href="/TP_3_SplashingPopcorn_Entrega_Final2/promocion/borrarPromocion.ad"
											role="button" class="btn btn-lg" data-bs-toggle="modal"
											data-bs-target="#modalEliminar" id="boton-eliminar"><strong>Eliminar</strong>
										</a>
									</div>

								</div>
							</c:when>
							<c:when test="${usuario!=null && !usuario.esAdmin()}">
								<div class="col">
									<div class="align-self-center" id="comprar">
										<c:if
											test="${usuario.puedeComprarA(promocion) && promocion.tieneStock()}">
											<a class="btn btn-lg"
												href="/TP_3_SplashingPopcorn_Entrega_Final2/comprarPromocion.do?id=${promocion.id}&usuario=${usuario.id}"
												role="button" id="boton-comprar"><strong>Comprar</strong></a>
										</c:if>
										<c:if test="${!usuario.puedeComprarA(promocion) && !promocion.tieneStock()}">
											<button class="btn btn-lg" type="button" id="boton-comprar"
												disabled>Comprar</button>
										</c:if>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col">
									<div class="align-self-center" id="comprar">
										<button type="button" class="btn btn-lg" id="boton-comprar">
											<strong>Comprar</strong>
										</button>
									</div>
									<div class="align-self-center" id="mensaje_error"></div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>


			<!-- 	<!-- MODAL COMPRAR -->
			<div class="modal fade" id="modalComprar" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-5 shadow"
						id="ventanaModalComprar">
						<div class="modal-body">
							<p>Su compra se ha registrado existosamente.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>

						</div>
					</div>
				</div>
			</div>
			-->
			<!-- MODAL ELIMINAR -->
			<div class="modal fade" id="modalEliminar" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-5 shadow">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Eliminar
								promoción</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p>¿Está seguro de eliminar esta promoción?</p>
						</div>
						<div class="modal-footer">
							<a class="btn btn-primary"
								href="controlador/promociones/borrarPromocion.do?id=${promocion.id}"
								role="button" id="aceptar-eliminar">Aceptar</a>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>
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
							<form action="controlador/promociones/editarPromocion.do"
								method="post">
								<label for="titulo">Titulo de promoción</label>
								<div class="form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="titulo"
										name="titulo" required value="${promocion.titulo}">
									<div class="invalid-feedback">Introduzca su nombre por
										favor</div>
								</div>

								<div class="form-floating mt-5 mb-3">
									<label for="peliculasEnPromos">Elija las películas:</label> <input
										list="listaPeliculas" id="peliculasEnPromos"
										name="peliculasEnPromos" />

									<datalist id="listaPeliculas">
										<c:forEach items="${peliculas}" var="peliculas">
											<option value="${peliculas.titulo}">
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


								<button type="submit"
									class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Guardar
									cambios</button>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer class="m-1">
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="/parciales/footer.jsp"></jsp:include>
	</footer>
</body>
</html>