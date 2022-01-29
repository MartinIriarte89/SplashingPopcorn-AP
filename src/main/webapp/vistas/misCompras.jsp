<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/estiloMisCompras.css">
<link rel="stylesheet" type="text/css" href="./css/botones.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.pajinate.js"></script>
<script type="text/javascript" src="./js/jquery.resize.js"></script>
<script type="text/javascript" src="./js/funciones.js"></script>
<script type="text/javascript" src="./js/completarModales.js" defer></script>
<title>Mis Compras || Splashing Popcorn</title>

</head>
<body>
	<header>
		<!-- ELEMENTO HEADER -->
		<jsp:include page="../parciales/header.jsp"></jsp:include>
	</header>
	<main>

		<!-- SEPARADOR -->

		<div id="separador" style="height: 225px;">
			<div class="contenedor d-flex ">

				<!-- TITULO -->
				<div class=" mx-auto align-self-end mb-4 font-lato" id="titulo">Mis
					Compras</div>

			</div>
		</div>
		<!-- CONTENEDOR DEL CUERPO -->
		<div class="container-fluid">

			<!-- CONTENEDOR DE LAS CARS Y COMPAGINADOR -->
			<div id="contenedor-paginador-xxl"
				class="row justify-content-xl-center me-xl-3  m-0">

				<div
					class="d-flex col-xl-9 col-12 flex-column mt-xl-0 mt-5 mb-3 p-0"
					style="min-height: calc(100vh - 225px);">

					<!-- TABLA DE CARDS -->
					<div class="row justify-content-around contenido m-0 mb-4">

						<c:forEach items="${itinerario.compras}" var="compra">
							<!-- CARD -->
							<div
								class="display-flex justify-content-center col-xl-12 col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2">
								<div
									class="row flex-xl-row flex-column fondo-backdrop carta h-100 m-0"
									style='background-image:${compra.esPromocion() ? "linear-gradient(130deg , rgb(0 0 0) 30%, rgb(13 13 13/ 88%) 80%)" : "linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 0.5) 80%)"} , url("./${compra.urlFondo}");'>
									<!-- IMAGEN CARD -->
									<div class="col-auto ms-xl-5 mx-auto">
										<div class="fondo-portada"
											style="background-image: url('./${compra.urlPortada}');">
											<a
												href='./${compra.esPromocion() ? "listarDetallePromocion" : "listarDetallePelicula"}?id=${compra.id}'><img
												class="w-100 h-100" style="opacity: 0%"></a>
										</div>
									</div>
									<!-- DESCRIPCION CARD -->
									<div class="col d-flex p-0">
										<div class="row m-0 my-xl-4 my-2" id="contenedorDescrip">
											<div class="d-flex">
												<p
													class="titulo-carta h3 mx-xl-0 mx-auto text-center text-white">
													${compra.titulo} <span> <c:if
															test="${!compra.esPromocion()}">(${compra.anioLanzamiento})</c:if></span>
												</p>
											</div>
											<div class="d-flex align-items-start mt-3 d-none d-xl-block">
												<p class="descripcion overflow-auto font-merriweather"
													style="max-height: 120px;">${compra.descripcion}</p>
											</div>
											<div class="d-flex mt-auto justify-content-center">
												<button type="button" class="btn btn-neon"
													data-bs-toggle="modal" 
													data-bs-target="#compra"
													data-bs-titulo="${compra.titulo}"
													data-bs-genero="${compra.genero.nombre}"
													data-bs-costo="${compra.precio}"
													data-bs-duracion="${compra.duracion}">
													<span id="span1"></span> <span id="span2"></span> <span
														id="span3"></span> <span id="span4"></span>Detalle compra
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

					<!-- COMPAGINADOR -->
					<div class="row text-center m-0 mt-auto p-4 border-top border-dark">
						<div class="page_navigation"></div>
					</div>
				</div>
			</div>

			<!-- MODAL DETALLE DE COMPRA -->
			<div class="modal fade" id="compra" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">

						<!-- ENCABEZADO DEL MODAL -->
						<div class="modal-header border-bottom border-dark border-2">
							<h5 class="modal-title ms-auto fs-2 font-merriweather"
								id="exampleModalLabel">Detalle de tu compra</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<!-- CUERPO DEL MODAL -->
						<div class="modal-body p-0 m-0">
							<div class="container-fluid px-0 py-3 text-modal">
								<div class="row pt-2 text-modal">
									<div class="col-4 fs-4 text-end font-merriweather-light">Título:</div>
									<div
										class="col-8 text-center align-self-center font-merriweather-light"
										id="modalTitulo"></div>
								</div>

								<div class="row mt-3 text-modal ">
									<div class="col-4 fs-4 text-end font-merriweather-light ">Género:</div>
									<div
										class="col-8 text-center align-self-center font-merriweather-light"
										id="modalGenero"></div>
								</div>

								<div class="row mt-3 text-modal">
									<div class="col-4 fs-4 text-end font-merriweather-light">Costo:</div>
									<div
										class="col-8 text-center align-self-center font-merriweather-light"
										id="modalPrecio"></div>
								</div>

								<div class="row mt-3 text-modal">
									<div class="col-4 fs-4 text-end font-merriweather-light">Duración:</div>
									<div
										class="col-8 text-center align-self-center font-merriweather-light"
										id="modalDuracion"></div>
								</div>
							</div>

						</div>

						<!-- PIE DEL MODAL -->
						<div class="modal-footer border-bottom border-dark border-2">
							<button type="button" class="btn btn-secondary mx-auto"
								data-bs-dismiss="modal">CERRAR</button>
						</div>
					</div>
				</div>
			</div>

		</div>

	</main>
	<footer class="m-1">
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="../parciales/footer.jsp"></jsp:include>
	</footer>

</body>
</html>