<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/botones.css">
<link rel="stylesheet" type="text/css" href="./css/detalles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<style type="text/css">
#promo {
	opacity: 1 !important;
}

#promos {
	height: 250px;
}

#promos a {
	width: 130px;
	height: 230px;
}

@media ( min-width : 0px) and (max-width: 991.99px) {
}
</style>

<script type="text/javascript"
	src="./js/modales.js"></script>
<script type="text/javascript" src="./js/funciones.js" defer></script>

<title>Detalle de Promociones || Splashing Popcorn</title>

</head>
<body>
	<header>
		<!-- ELEMENTO HEADER -->
		<jsp:include page="../parciales/header.jsp"></jsp:include>
	</header>
	<main>
		<!-- MODAL INICIO SESION -->
		<jsp:include page="../parciales/inicioSesionModal.jsp"></jsp:include>
		<!-- MODAL ERROR -->
		<jsp:include page="../parciales/modalesCompra.jsp"></jsp:include>

		<!-- CONTENEDOR DEL CUERPO -->
		<div class="container-fluid m-0 p-0">
			<div id="cuerpo" class="mb-5">
				<div class="row justify-content-around m-0 p-0"
					id="contenidoDetallePromo"
					style="background-image: linear-gradient(145deg, rgb(22 26 29/ 98%) 25%, rgb(85 28 59/ 86%) 90%);)">
					<!-- IMAGEN -->
					<div class="col-xxl-4 col-lg-5 col-12 my-3 m-0 p-0">
						<div id="foto"
							class="d-flex p-0 justify-content-center align-items-center animate__animated animate__backInLeft"
							style="background-image: url('./${promocion.urlPortada}');">
							<c:choose>
								<c:when
									test='${!usuario.itinerario.noTieneA(promocion) && (sessionScope.usuario != null)}'>
									<div id="comprada" class="font-lato">INHABILITADA</div>
								</c:when>
								<c:when
									test='${usuario.itinerario.noTieneA(promocion) && (!promocion.tieneStock()) || (!promocion.tieneStock()) && (sessionScope.usuario == null)}'>
									<div id="comprada" class="font-lato">SIN STOCK</div>
								</c:when>
							</c:choose>
						</div>
					</div>
					<!-- DESCRIPCION DE PELICULA-->
					<div
						class="col-xxl-7 col-lg-6 col-12 p-0 me-lg-auto ms-lg-4 my-3 mt-lg-0 mt-5 d-flex flex-column animate__animated animate__backInRight"
						id="contenedor-descripcion">
						<!-- DESCRIPCION -->
						<div class="row flex-column h-100 m-0 p-0">
							<!-- TITULO -->
							<div id="tituloDescrip"
								class="h1 col d-flex mt-lg-3 mx-auto justify-content-center align-items-center font-lato">
								${promocion.titulo}</div>

							<!-- GENERO -->
							<div id="genero" class="col-auto mb-lg-4 mb-0 mt-lg-2 mt-4 h4">
								Género: ${promocion.genero.nombre} <span class="mt-4 ms-3 h6">duración:
									${promocion.getDuracion()} min.</span>
							</div>

							<!-- DESCRIPCION -->
							<div id="descripcion"
								class="col-lg col-12 mb-3 overflow-auto font-merriweather"
								style="max-height: 120px;">${promocion.descripcion}</div>

							<div class="col mt-xl-4 mt-lg-2 mt-4">
								<!-- PROMOS -->
								<div id="div-promos">Películas:</div>
								<div id="promos"
									class="w-100 overflow-auto border border-2 d-flex">
									<c:forEach items="${promocion.getPeliculas()}" var="pelicula">
										<a
											href="./listarDetallePelicula?id=${pelicula.id}"
											class="col-4 link overflow-hidden my-auto ms-2 p-0 justify-content-center align-items-center d-flex"
											style="background-image: url('./	${pelicula.urlPortada}');">
											<c:choose>
												<c:when
													test='${!usuario.itinerario.noTieneA(pelicula) && (sessionScope.usuario != null)}'>
													<span
														class="w-100 span font-lato d-flex justify-content-center align-items-center">COMPRADA</span>
												</c:when>
												<c:when
													test='${usuario.itinerario.noTieneA(pelicula) && (!pelicula.tieneStock()) || (!pelicula.tieneStock()) && (sessionScope.usuario == null)}'>
													<span
														class="w-100 span font-lato d-flex justify-content-center align-items-center">SIN
														STOCK</span>
												</c:when>
												<c:otherwise>${pelicula.titulo}</c:otherwise>
											</c:choose>
										</a>
									</c:forEach>
								</div>
							</div>

							<c:set var="precioSinDesc" value="${0}" />
							<c:forEach var="pelicula" items="${promocion.getPeliculas()}">
								<c:set var="precioSinDesc"
									value="${precioSinDesc + pelicula.precio}" />
							</c:forEach>

							<!-- PRECIO -->
							<div class="col-auto my-xxl-5 my-xl-4 my-lg-2 my-4 d-flex">
								<div id="precio"
									class="mx-auto d-inline-flex font-lato align-items-end"
									style="height: 55px;">
									<div class="me-1" style="color: lightyellow;">Precio:</div>
									$${promocion.getPrecio()} <span class="ms-2 mb-auto"
										style="text-decoration: line-through; text-decoration-color: red;">
										$${precioSinDesc}</span>
								</div>

							</div>
						</div>
						<!-- BOTONES -->
						<div class="row mt-auto m-0 p-0 mb-2">
							<div id="boton-comprar" class="text-center">
								<a type="button"
									class='btn btn-neon font-lato ${usuario.puedeComprarA(promocion) && promocion.tieneStock() && !usuario.esAdmin() ? "" : " disabled" }'
									data-bs-toggle="modal" data-bs-target="#modalConfirmacion"><span
									id="span1"></span> <span id="span2"></span> <span id="span3"></span>
									<span id="span4"></span>Comprar</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- MODAL CONFIRMACION COMPRA -->
		<div class="modal fade" id="modalConfirmacion"
			data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel1" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content bg-warning">
					<div class="modal-header border-0">
						<h5 class="modal-title mx-auto fw-bold" id="exampleModalLabel"
							style="text-decoration: underline;">¡ATENCIÓN!</h5>
					</div>
					<div class="modal-body mx-auto fw-bold">Estas a punto de
						comprar esta promoción. ¿Estas seguro?</div>
					<div class="modal-footer d-flex border-0 justify-content-center">
						<a id="botonConfirm" type="button"
							href="./comprarPromocion.do?id=${promocion.id}"
							class="btn btn-success">Aceptar</a>
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

	<c:if test="${error != null }">
		<script type="text/javascript">
			abrirModalError();
		</script>
	</c:if>
</body>
</html>