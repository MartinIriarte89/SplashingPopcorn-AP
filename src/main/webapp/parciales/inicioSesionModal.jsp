<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade" id="modalInicioSesion" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content rounded-5 shadow">
			<!-- ENCABEZADO DEL MODAL -->
			<div class="modal-header p-5 pb-4 border-bottom-0">
				<div class="d-inline-flex">
					<img alt="" src="/Webapp_Proyecto_Final/imagenes/logo.png"
						style="width: 30%; margin: -20px;">
					<h2 class="align-self-center">Splashing Popcorn</h2>
				</div>

				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<!-- CUERPO DEL MODAL -->
			<div class="modal-body px-5 py-3 pt-0">
				<!-- FORLMULARIO DEL MODAL -->
				<form action="inicioSesion" method="post">
					<div class="form-floating mb-3">
						<input type="text" class="form-control rounded-4" id="usuario"
							placeholder="User" required="required" name="usuario"
							value="${usuario.usuario}"> <label
							class='${erroresSesion.get("usuario") != null ? "is-invalid" : ""}'
							for="usuario">Usuario</label>
						<div class="invalid-tooltip">
							${erroresSesion.get("usuario")}</div>
					</div>
					<div class="form-floating mt-5 mb-3">
						<input type="password" class="form-control rounded-4"
							id="contrasena" placeholder="Password" required="required"
							name="contrasena"> <label
							class='${erroresSesion.get("contrasena") != null ? "is-invalid" : ""}'
							for="contrasena">Contraseña</label>
						<div class="invalid-tooltip">${erroresSesion.get("contrasena")}</div>
					</div>
					<!-- BOTON DE INICIO -->
					<button type="submit"
						class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Iniciar
						Sesión</button>
					<small class="text-muted"> Al hacer click en Iniciar
						Sesión, acepta los términos de uso.</small>
				</form>
				<hr>
				<div class="text-center h-4">Si no estas registrado, regístrate gratis aquí </div>
				<div class="d-flex mt-3"> <a class="mx-auto btn btn-secondary" href="/Webapp_Proyecto_Final/registro" style="font-size: 12px">Regístrate</a> </div>
			</div>
		</div>
	</div>
</div>