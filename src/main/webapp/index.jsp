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
<link rel="stylesheet" type="text/css" href="css/botones.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,700;1,900&family=Merriweather:wght@300;400&display=swap"
	rel="stylesheet">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="/Webapp_Proyecto_Final/js/modales.js"></script>

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
	height: 250px;
	overflow: hidden;
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
		<!-- CONTENEDOR FONDO -->
		<div id="fondo" class="py-5">
			<div class="wave" style="top: 0;">
				<svg viewBox="0 0 500 150" preserveAspectRatio="none"
					style="height: 100%; width: 100%;">
						<path
						d="M-1.12,110.12 C96.51,218.66 283.30,-47.75 502.82,77.55 L500.00,0.00 L0.00,0.00 Z"
						style="stroke: none; fill: #3b125b;"></path></svg>
			</div>

			<!-- CONTENEDOR DE CARS -->
			<div class="d-flex col-12 flex-column mt-5 mb-3 px-0"
				style="padding: 130px;">

				<div class="titulo text-center color-principal">
					<h1 class="m-0 p-1">Estas son nuestras promos para vos!</h1>
				</div>

				<!-- CARDS -->
				<div class="row m-0 mt-1">
					<div
						class="d-flex col-lg-4 col-sm-6 col-12 mt-lg-5 mb-lg-5 mt-sm-4 mt-3 mb-4 ps-sm-2 pe-sm-3 p-0 justify-content-center">
						<div
							class="card flex-sm-grow-0 flex-fill text-center border-0 box-imagen-card">
							<img src="imagenes/fondoBarra.jpg" class="card-img-top rounded-3"
								alt="descuento">
							<div
								class="card-body col-sm-12 col-11 align-self-center p-0 pt-sm-3">
								<h5
									class="card-title text-center color-principal rounded-1 fs-3">Super
									Descuentos</h5>
								<a href="#" class="botondes learn-more"> <span
									class="circle" aria-hidden="true"> <span
										class="icon arrow"></span>
								</span> <span class="button-text">Ir a ver</span>
								</a>
							</div>
						</div>
					</div>

					<div
						class="d-flex col-lg-4 col-sm-6 col-12 mt-lg-5 mb-lg-5 mt-sm-4 mt-3 mb-4 ps-sm-2 pe-sm-3 p-0 justify-content-center">
						<div
							class="card flex-sm-grow-0 flex-fill text-center border-0 box-imagen-card">
							<img src="imagenes/fondoBarra.jpg" class="card-img-top rounded-3"
								alt="descuento">
							<div
								class="card-body col-sm-12 col-11 align-self-center p-0 pt-sm-3">
								<h5
									class="card-title text-center color-principal rounded-1 fs-3">Una
									de Regalo</h5>
								<a href="#" class="botondes learn-more"> <span
									class="circle" aria-hidden="true"> <span
										class="icon arrow"></span>
								</span> <span class="button-text">Ir a ver</span>
								</a>
							</div>
						</div>
					</div>

					<div
						class="d-flex  col-lg-4 col-sm-6 col-12 mt-lg-5 mb-lg-5 mb-3 m-auto ps-sm-2 pe-sm-3 p-0 justify-content-center">
						<div
							class="card flex-sm-grow-0 flex-fill text-center border-0 box-imagen-card">
							<img src="imagenes/fondoBarra.jpg" class="card-img-top rounded-3"
								alt="descuento">
							<div
								class="card-body col-sm-12 col-11 align-self-center p-0 pt-sm-3">
								<h5
									class="card-title text-center color-principal fs-3 rounded-1">Precios
									Locos</h5>
								<a href="#" class="botondes learn-more"> <span
									class="circle" aria-hidden="true"> <span
										class="icon arrow"></span>
								</span> <span class="button-text">Ir a ver</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="wave" style="bottom: 0;">
					<svg viewBox="0 0 500 150" preserveAspectRatio="none"
						style="height: 100%; width: 100%;">
						<path
							d="M-16.36,59.78 C169.87,193.00 408.58,-76.37 502.82,54.85 L500.00,149.99 L0.00,149.99 Z"
							style="stroke: none; fill: #3b125b;"></path></svg>
				</div>
			</div>
			<!-- MODAL SUCCESS -->
			<div class="modal fade" id="success" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content bg-success">
						<div
							class="modal-body mx-auto my-4 text-center text-white fw-bold fs-5">${success}</div>
						<div class="modal-footer d-flex border-0">
							<a type="button" class="btn btn-outline-light mx-auto"
								href="/Webapp_Proyecto_Final/inicio" data-bs-dismiss="modal">Aceptar</a>
						</div>
					</div>
				</div>
			</div>
	</main>
	<footer>
		<!-- ELEMENTO FOOTER -->
		<jsp:include page="parciales/footer.jsp"></jsp:include>
	</footer>

	<c:if test="${erroresSesion != null}">
		<script type="text/javascript">
			abrirModalInicioSesion();
		</script>
	</c:if>
	<c:if test="${success != null}">
		<script type="text/javascript">
			abrirModalSuccess();
		</script>
	</c:if>
</body>
</html>
