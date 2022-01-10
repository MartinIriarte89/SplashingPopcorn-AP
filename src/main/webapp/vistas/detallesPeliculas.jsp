<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>

<jsp:include page="../parciales/librerias.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/index.css">
<link rel="stylesheet" type="text/css" href="./css/botones.css">
<link rel="stylesheet" type="text/css" href="./css/detalles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/modales.js"></script>
<script type="text/javascript" src="./js/funciones.js" defer></script>

<title>Detalle de Película</title>

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
					id="contenidoDetalle"
					style="background-image: linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 0.5) 80%), url('${pelicula.urlFondo}');">
					<!-- IMAGEN -->
					<div class="col-xxl-4 col-lg-5 col-12 my-3 m-0 p-0">
						<div id="foto"
							class="d-flex p-0 justify-content-center align-items-center animate__animated animate__backInLeft"
							style="background-image: url('${pelicula.urlPortada}');">
							<c:if
								test='${!usuario.itinerario.noTieneA(pelicula) && (sessionScope.usuario != null)}'>
								<div id="comprada">COMPRADA</div>
							</c:if>
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
								class="h1 col d-flex justify-content-center align-items-center font-lato">
								${pelicula.titulo} <span class="mt-4 ms-3 h6">(${pelicula.anioLanzamiento})</span>
							</div>

							<!-- GENERO -->
							<div id="genero" class="col-auto mb-xl-3 mb-0 mt-lg-2 mt-4 h4">
								Género: ${pelicula.genero.nombre} <span class="mt-4 ms-3 h6">duración:
									${pelicula.duracion} min.</span>
							</div>

							<!-- LEMA -->
							<div id="lema"
								class="col h3 d-flex align-items-end mb-xl-4 mb-3 mt-lg-0 mt-4">${pelicula.lema}</div>

							<!-- DESCRIPCION -->
							<div id="descripcion"
								class="col-lg col-12 mb-4 overflow-auto font-merriweather"
								style="max-height: 120px;">${pelicula.descripcion}</div>

							<div class="col mt-xl-4 mt-lg-2 mt-4">
								<!-- PROMOS -->
								<div id="div-promos">Se encuentra en las siguientes
									promociones:</div>
								<div id="promos"
									class="w-100 overflow-auto border border-2 d-flex">
									<c:forEach items="${promos}" var="promo">
										<a
											href="/Webapp_Proyecto_Final/listarDetallePromocion?id=${promo.id}"
											class="link overflow-hidden my-auto ms-2 align-items-center d-flex"
											style="background-image: url('${promo.urlPortada}');">${promo.titulo}</a>
									</c:forEach>
								</div>
							</div>
							<!-- PRECIO -->
							<div class="col-auto my-xxl-5 my-xl-4 my-lg-3 my-4 d-flex">
								<div id="precio" class="mx-auto d-inline-flex">
									<div class="me-1" style="color: lightyellow;">Precio:</div>
									$${pelicula.precio}
								</div>
							</div>
						</div>
						<!-- BOTONES -->
						<div class="row mt-auto m-0 p-0 mb-3">
							<div id="boton-comprar" class="text-center">
								<a type="button"
									class='btn btn-neon ${usuario.puedeComprarA(pelicula) && pelicula.tieneStock() && !usuario.esAdmin() ? "" : " disabled" }'
									href="/Webapp_Proyecto_Final/comprarPelicula.do?id=${pelicula.id}"><span
									id="span1"></span> <span id="span2"></span> <span id="span3"></span>
									<span id="span4"></span>Comprar</a>
							</div>
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

	<c:if test="${error != null }">
		<script type="text/javascript">
			abrirModalError();
		</script>
	</c:if>
</body>
</html>