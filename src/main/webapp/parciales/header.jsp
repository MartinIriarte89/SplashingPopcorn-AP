<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- BARRA NAV -->
<div id="barra" class="d-flex shadow-lg p-0 rounded-1 fixed-top mx-1">
	<nav
		class="flex-fill navbar navbar-expand-md navbar-dark py-md-2 nav-padding">
		<button class="navbar-toggler ms-2" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul
				class="nav me-auto mt-md-0 mt-2 mb-2 mb-md-0 ms-md-2 d-inline-flex flex-md-row flex-column itemsbarra">


				<li><a href="/Webapp_Proyecto_Final/inicio"
					class="nav-link px-2 link-light opacity-50 fs-5" id="inicio">Inicio</a></li>
				<li><a href="/Webapp_Proyecto_Final/peliculas"
					class="nav-link px-2 link-light opacity-50 fs-5" id="peli">Películas</a></li>
				<li><a href="/Webapp_Proyecto_Final/promociones"
					class="nav-link px-2 link-light opacity-50 fs-5" id="promo">Promociones</a></li>
			</ul>
		</div>
	</nav>

	<c:if test="${usuario == null}">
		<!-- BOTON INICIAR SESIÓN -->
		<div class="text-end text-wrap my-md-auto boton-mt">
			<button type="button" class="btn me-4 boton-inicio"
				data-bs-toggle="modal" data-bs-target="#modalInicioSesion">Iniciar
				Sesión</button>
		</div>
	</c:if>

	<c:if test="${usuario != null }">

		<c:if test="${!usuario.esAdmin()}">
			<div id="info-barra" class="me-md-5 me-2 my-md-auto font-lato">
				<i class="bi bi-currency-exchange"></i> ${usuario.dineroDisponible}
				<i class="bi bi-hourglass-split"></i> ${usuario.tiempoDisponible}<span
					class="ms-1">min.</span>
			</div>
		</c:if>

		<div class="dropdown dropstart">
			<a href="#"
				class="d-block rounded-circle link-dark text-decoration-none dropdown me-4"
				data-bs-toggle="dropdown" aria-expanded="false"
				style="height: 60px; width: 60px; background: url('/Webapp_Proyecto_Final/${usuario.urlPerfil}'); background-size: cover; background-position: center;">
			</a>

			<c:choose>
				<c:when test="${usuario.esAdmin()}">
					<ul class="dropdown-menu dropdown-menu-end dropdown-menu-dark">
						<li><a class="dropdown-item"
							href="/Webapp_Proyecto_Final/listarUsuarios.ad">Usuarios</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item"
							href="/Webapp_Proyecto_Final/cerrarSesion">Cerrar sesión</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="dropdown-menu dropdown-menu-end dropdown-menu-dark">
						<li><a class="dropdown-item"
							href="/Webapp_Proyecto_Final/perfilUsuario.do">Mi perfil</a></li>
						<li><a class="dropdown-item"
							href="/Webapp_Proyecto_Final/listarItinerario.do">Mis compras</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item"
							href="/Webapp_Proyecto_Final/cerrarSesion">Cerrar sesión</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>

	</c:if>
</div>
