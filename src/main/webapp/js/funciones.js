//PARA CAMBIAR LAS FOTOS EN LOS DETALLES DE LAS PROMOCIONES

if (!!document.getElementById('contenidoDetalle')) {
	var contenido = document.querySelector('#contenidoDetalle');
	var foto = document.querySelector('#foto');

	var copiaFondo = contenido.style.backgroundImage.split('),')[1];
	var copiaPortada = foto.style.backgroundImage;
	$(document).ready(function() {

		let imagenFondo = window.getComputedStyle(contenido).getPropertyValue('background-image');
		let imagenPortada = window.getComputedStyle(foto).getPropertyValue('background-image');

		imagenFondo = imagenFondo.split('),')

		if ($(window).width() < 992) {
			contenido.style.backgroundImage = 'linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 0.5) 80%), ' + imagenPortada;
			foto.style.backgroundImage = imagenFondo[1];
		}
	});

	$(window).resize(function() {

		if ($(window).width() > 992) {

			contenido.style.backgroundImage = 'linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 0.5) 80%), ' + copiaFondo;
			foto.style.backgroundImage = copiaPortada;

		}
		if ($(window).width() <= 992) {
			contenido.style.backgroundImage = 'linear-gradient(90deg, rgba(22, 26, 29, 1) 30%, rgba(22, 26, 29, 0.5) 80%), ' + copiaPortada;
			foto.style.backgroundImage = copiaFondo;

		}

	});
}

if (!!document.getElementById('contenidoDetallePromo')) {
	var contenido = document.querySelector('#contenidoDetallePromo');
	var foto = document.querySelector('#foto');

	var copiaFondo = contenido.style.backgroundImage;
	var copiaPortada = foto.style.backgroundImage;
	$(document).ready(function() {

		let imagenFondo = window.getComputedStyle(contenido).getPropertyValue('background-image');
		let imagenPortada = window.getComputedStyle(foto).getPropertyValue('background-image');

		if ($(window).width() < 992) {
			contenido.style.backgroundImage = imagenPortada;
			foto.style.backgroundImage = imagenFondo;
		}
	});

	$(window).resize(function() {

		if ($(window).width() > 992) {

			contenido.style.backgroundImage = copiaFondo;
			foto.style.backgroundImage = copiaPortada;

		}
		if ($(window).width() <= 992) {
			contenido.style.backgroundImage = copiaPortada;
			foto.style.backgroundImage = copiaFondo;

		}

	});
}


if (!!document.getElementById('foto')) {
	var foto = document.querySelector('#foto');

	$(document).ready(function() {

		if ($(window).width() >= 576) {
			foto.classList.add("animate__animated");
			foto.classList.add("animate__backInLeft")
		}
	});
}

if (!!document.getElementById('contenedor-descripcion')) {
	var contenedorDescripcion = document.querySelector('#contenedor-descripcion');

	$(document).ready(function() {

		if ($(window).width() >= 576) {
			contenedorDescripcion.classList.add("animate__animated");
			contenedorDescripcion.classList.add("animate__backInRight")
		}
	});
}