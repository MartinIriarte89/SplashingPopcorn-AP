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
			contenido.style.backgroundImage =  imagenPortada;
			foto.style.backgroundImage = imagenFondo;
		}
	});

	$(window).resize(function() {

		if ($(window).width() > 992) {

			contenido.style.backgroundImage = copiaFondo;
			foto.style.backgroundImage = copiaPortada;

		}
		if ($(window).width() <= 992) {
			contenido.style.backgroundImage =  copiaPortada;
			foto.style.backgroundImage = copiaFondo;

		}

	});
}





/* este metodo es de fede hay que borrarlo */
function mostrarPelicula(id, esPromo) {
	const xhr = new XMLHttpRequest();
	const fd = new FormData;

	const funcion_error = function(event) {
		alert("ERROR");
	}

	// Definiciones de nuestro objeto de XHR
	xhr.timeout = 3000; // ms
	xhr.addEventListener("timeout", funcion_error);
	xhr.addEventListener("error", funcion_error);
	xhr.addEventListener("load", function(e) {
		let json = JSON.parse(e.target.responseText);
		cargarModal(json);
	});

	xhr.open('GET', './getProducto.jsp?id=' + id + '&esPromo=' + esPromo);

	// Realizamos el pedido
	xhr.send(fd);
}

function cargarModal(json) {
	var myModal = new bootstrap.Modal(document.getElementById('compra'));

	let modalTitulo = document.querySelector("#modalTitulo");
	modalTitulo.innerHTML = json.titulo;

	let modalGenero = document.querySelector("#modalGenero");
	modalGenero.innerHTML = json.genero;

	let modalPrecio = document.querySelector("#modalPrecio");
	modalPrecio.innerHTML = json.costo;

	let modalDuracion = document.querySelector("#modalDuracion");
	modalDuracion.innerHTML = json.duracion;

	myModal.show();
}







