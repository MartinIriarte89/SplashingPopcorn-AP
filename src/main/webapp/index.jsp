
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="es">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/estilosCompartidos.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/botones.css">



<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,700;1,900&family=Merriweather:wght@300;400&display=swap"
	rel="stylesheet">

<script type="text/javascript" src="js/bootstrap.bundle.min.js" defer></script>

<link rel="shortcut icon" href="imagenes/logo.png" sizes="75px;">

<style>
.font-lato {
	font-family: Lato;
}

.font-merriweather {
	font-family: Merriweather;
	font-size: 14px;
}

.font-merriweather-light {
	font-family: Merriweather;
	font-size: 14px;
}
</style>

<title>Mis Compras</title>

</head>

<body>
	<header>
		<!-- ELEMENTO HEADER -->
		<jsp:include page="parciales/header.jsp"></jsp:include>
	</header>
	<main>

		<!-- MODAL INICIO SESION -->
		<c:if test="${usuario == null }">
			<jsp:include page="parciales/inicioSesionModal.jsp"></jsp:include>
		</c:if>
		<div class="container-fluid">

			<!-- CARRUSEL -->
			<div id="carouselExampleCaptions" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner">

					<div class="carousel-item active">
						<img src="imagenes/cine.jpg" class="d-block w-100"
							alt="${promocion.titulo}">
						<div class="carousel-caption d-none d-md-block">
							<h1>${promocion.titulo}</h1>
						</div>
					</div>
					<c:forEach items="${promociones}" var="promocion">
						<!-- ITEMS DE CARRUSEL -->
						<div class="carousel-item">
							<img src="${promocion.urlPortada}" class="d-block w-100"
								alt="${promocion.titulo}">
							<div class="carousel-caption d-none d-md-block">
								<h1>${promocion.titulo}</h1>
							</div>
						</div>
					</c:forEach>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<!-- CONTENEDOR FONDO POCHOCLOS -->
		<div id="fondo-pochoclos">
			<div class="titulo text-center color-principal">
				<h1>Estas son nuestras PROMOS para vos!</h1>
			</div>

			<!-- CONTENEDOR DE CARS -->

			<div class="d-flex col-12 flex-column mt-5 mb-3 p-0">

				<!-- TABLA DE CARDS -->
				<div class="row justify-content-around contenido m-0 mb-4">

					<!-- CARD -->
					<div
						class="display-flex justify-content-center col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2">
						<div
							class="row flex-column fondo-backdrop carta mx-auto h-100 m-0"
							style="background-image: linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 1) 80%);">
							<!-- IMAGEN CARD -->

							<div class="fondo-portada mx-auto"
								style="background-image: url('imagenes/1_peli_portada.jpg');">
							</div>

							<!-- DESCRIPCION CARD -->
							<div class="col d-flex p-0">
								<div class="row m-0 my-2 w-100" id="contenedorDescrip">
									<div class="d-flex">
										<p class="titulo-carta h3 mx-auto text-center text-white">
											Super descuentos</p>
									</div>
									<div class="d-flex mt-auto justify-content-center">
										<a href="#" class="botondes learn-more d-flex"> <span
											class="circle" aria-hidden="true"> <span
												class="icon arrow"></span>
										</span> <span class="button-text mx-auto">Ver</span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- CARD -->
					<div
						class="display-flex justify-content-center col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2">
						<div
							class="row flex-column fondo-backdrop carta mx-auto h-100 m-0"
							style="background-image: linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 1) 80%);">
							<!-- IMAGEN CARD -->

							<div class="fondo-portada mx-auto"
								style="background-image: url('imagenes/1_peli_portada.jpg');">
							</div>

							<!-- DESCRIPCION CARD -->
							<div class="col d-flex p-0">
								<div class="row m-0 my-2 w-100" id="contenedorDescrip">
									<div class="d-flex">
										<p class="titulo-carta h3 mx-auto text-center text-white">
											Precios locos</p>
									</div>
									<div class="d-flex mt-auto justify-content-center">
										<a href="#" class="botondes learn-more d-flex"> <span
											class="circle" aria-hidden="true"> <span
												class="icon arrow"></span>
										</span> <span class="button-text mx-auto">Ver</span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- CARD -->
					<div
						class="display-flex justify-content-center col-md-4 col-5 mt-5 px-md-4 px-sm-5 px-2">
						<div
							class="row flex-column fondo-backdrop carta mx-auto h-100 m-0"
							style="background-image: linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 1) 80%);">
							<!-- IMAGEN CARD -->

							<div class="fondo-portada mx-auto"
								style="background-image: url('imagenes/1_peli_portada.jpg');">
							</div>

							<!-- DESCRIPCION CARD -->
							<div class="col d-flex p-0">
								<div class="row m-0 my-2 w-100" id="contenedorDescrip">
									<div class="d-flex">
										<p class="titulo-carta h3 mx-auto text-center text-white">
											Una de regalo</p>
									</div>
									<div class="d-flex mt-auto justify-content-center">
										<a href="#" class="botondes learn-more d-flex"> <span
											class="circle" aria-hidden="true"> <span
												class="icon arrow"></span>
										</span> <span class="button-text mx-auto">Ver</span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</main>
	<footer class="m-1">
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="parciales/footer.jsp"></jsp:include>
	</footer>
</body>

</html>
