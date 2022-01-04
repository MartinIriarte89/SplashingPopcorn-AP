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
			<div class="modal-body p-5 pt-0">
				<!-- FORLMULARIO DEL MODAL -->
				<form action="inicioSesion" method="post">
					<div class="form-floating mb-3">
						<input type="text" class="form-control rounded-4" id="usuario"
							placeholder="User" required="required" name="usuario"> <label
							for="usuario">Usuario</label>
						<div class="invalid-feedback">Introduzca su nombre de
							usuario por favor</div>
					</div>
					<div class="form-floating mt-5 mb-3">
						<input type="password" class="form-control rounded-4"
							id="contrasena" placeholder="Password" required="required" name="contrasena">
						<label for="contrasena">Contraseña</label>
						<div class="invalid-feedback">Introduzca su contraseña por
							favor</div>
					</div>
					<!-- BOTON DE INICIO -->
					<button type="submit"
						class="w-100 mb-2 btn btn-lg rounded-4 btn-warning">Iniciar
						Sesión</button>
					<small class="text-muted"> Al hacer click en Iniciar
						Sesión, acepta los términos de uso.</small>
				</form>
			</div>
		</div>
	</div>
</div>