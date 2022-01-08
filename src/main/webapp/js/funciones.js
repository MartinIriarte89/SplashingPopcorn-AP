
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

	xhr.open('GET', '../getProducto.jsp?id=' + id + '&esPromo=' + esPromo);

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







