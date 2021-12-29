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

<title>Detalle Película || Splashing Popcorn</title>

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
				<div class="d-flex col-sm-12 col-md-6 col-xl-4" id="imagen_pelicula">
					<div class="row">
						<img src="${pelicula.urlPortada}" alt="${pelicula.titulo}"
							id="imagen-pelicula">
					</div>

				</div>
				<!-- LADO DERECHO PANTALLA: INFORMACIÓN -->
				<div class="d-flex col-sm-12 col-md-6 col-xl-8" id="informacion">
					<div class="row row-cols-1 flex-colum">
						<!-- DATOS PELÍCULA -->
						<div class="col">
							<div class="header_poster_wrapper true">
								<section class="header_poster">
									<div class="title ott_true">
										<h2 id="titulo">
											<c:out value="${pelicula.titulo}"></c:out>
											<span class="tag release_date"><c:out
													value="(${pelicula.anioLanzamiento})"></c:out> </span>
										</h2>
										<div class="datos">
											<h3>
												<span class="genero"><c:out
														value="${pelicula.genero}"></c:out></span> <span class="duracion">
													- <c:out value="${pelicula.duracion} minutos."></c:out>
												</span>
											</h3>
										</div>
									</div>
									<div class="header_info">
										<h4 class="tagline">
											<c:out value="${pelicula.lema}"></c:out>
										</h4>
										<div class="resumen">
											<p>
												<c:out value="${pelicula.descripcion}"></c:out>
											</p>
										</div>
									</div>
								</section>
							</div>
						</div>
						<!-- PRECIO PELÍCULA -->
						<div class="col" id="precio-bloque">
							<div class="align-self-center" id="precio">
								<h2>
									<c:out value="$ ${pelicula.precio}0"></c:out>
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
											href="/TP_3_SplashingPopcorn_Entrega_Final2/peliculas/editarPelicula.ad"
											role="button" class="btn btn-lg" data-bs-toggle="modal"
											data-bs-target="#modalEditar" id="boton-editar"> <strong>Editar</strong>
										</a> <a
											href="/TP_3_SplashingPopcorn_Entrega_Final2/peliculas/borrarPelicula.ad"
											role="button" class="btn btn-lg" data-bs-toggle="modal"
											data-bs-target="#modalEliminar" id="boton-eliminar"><strong>Eliminar</strong>
										</a>
									</div>
								</div>
							</c:when>
							<c:when test="${usuario!=null && !usuario.esAdmin()}">
								<div class="col">
									<div class="align-self-center" id="comprar">

										<c:if test="${usuario.puedeComprarA(pelicula) && pelicula.tieneStock()}">
											<a class="btn btn-lg"
												href="/TP_3_SplashingPopcorn_Entrega_Final2/comprarPelicula.do?id=${pelicula.id}"
												role="button" id="boton-comprar"><strong>Comprar</strong></a>
										</c:if>
										<c:if test="${!usuario.puedeComprarA(pelicula) && !pelicula.tieneStock()}">
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
			<!-- MODAL COMPRAR -->
			<%-- <div class="modal fade" id="modalComprar" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-5 shadow"
						id="ventanaModalComprar">
						<div class="modal-body">
							<p><c:out value="${?????}"></c:out></p>						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>

						</div>
					</div>
				</div>
			</div> --%>

			<!-- MODAL EDITAR -->
			<div class="modal fade" id="modalEditar" tabindex="-1"
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
							<form action="editarPelicula.do" method="post">
								<label for="titulo">Titulo de película</label>
								<div class="form-floating mb-3">
									<input type="text" class="form-control rounded-4" id="titulo"
										name="titulo" required value="${pelicula.titulo}">
									<div class="invalid-feedback">Introduzca su nombre por
										favor</div>
								</div>

								<label for="precio">Precio</label>
								<div class="form-floating mb-3">
									<input type="number" class="form-control rounded-4" id="precio"
										name="precio" placeholder="${pelicula.precio}" required
										value="${pelicula.precio}">
									<div class="invalid-feedback">Introduzca su precio por
										favor</div>
								</div>
								<label for="duracion">Duración</label>
								<div class="form-floating mb-3">
									<input type="number" class="form-control rounded-4"
										id="duracion" name="duracion"
										placeholder="${pelicula.duracion}" required
										value="${pelicula.duracion}">
									<div class="invalid-feedback">Introduzca su duración por
										favor</div>
								</div>
								<label for="genero">Elija un género</label>
								<div class="form-floating mb-3">
									<select class="form-select" aria-label="Default select example">
										<c:forEach items="${generos}" var="genero">
											<option value="${genero.nombre}">"${genero.nombre}"</option>
										</c:forEach>
									</select>
								</div>
								<label for="stock">Stock disponible</label>
								<div class="form-floating mb-3">
									<input type="number" class="form-control rounded-4" id="stock"
										name="stock" placeholder="${pelicula.stock}" required
										value="${pelicula.stock}">
									<div class="invalid-feedback">Introduzca su stock por
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

			<!-- MODAL ELIMINAR -->
			<div class="modal fade" id="modalEliminar" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-5 shadow">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Eliminar
								película</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p>¿Está seguro de eliminar esta película?</p>
						</div>
						<div class="modal-footer">
							<a class="btn btn-primary"
								href="controlador/peliculas/borrarPelicula.do?id=${pelicula.id}"
								role="button" id="aceptar-eliminar">Aceptar</a>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>
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