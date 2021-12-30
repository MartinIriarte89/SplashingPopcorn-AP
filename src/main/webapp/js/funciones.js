

if (!!document.getElementById('modalEliminarPelicula')) {

	var modalBorrar = document.getElementById('modalEliminarPelicula')
	modalBorrar.addEventListener('show.bs.modal', function(event) {
		
		let boton = event.relatedTarget

		let id = boton.getAttribute('data-bs-id')

		let botonEnviar = modalBorrar.querySelector('#botonElim')

		botonEnviar.setAttribute('href', "/TP_3_SplashingPopcorn_Entrega_Final2/borrarPelicula.ad?id=" + id)
	});
}

if (!!document.getElementById('modalEditarPelicula')) {

	var modalEditar = document.getElementById('modalEditarPelicula')
	modalEditar.addEventListener('show.bs.modal', function(event) {
		
		let boton = event.relatedTarget
		
		let id = boton.getAttribute('data-bs-id')
		let titulo = boton.getAttribute('data-bs-titulo')
		let lema = boton.getAttribute('data-bs-lema')
		let precio = boton.getAttribute('data-bs-precio')
		let duracion = boton.getAttribute('data-bs-duracion')
		let stock = boton.getAttribute('data-bs-stock')
		let genero = boton.getAttribute('data-bs-genero')
		let anioLanzamiento = boton.getAttribute('data-bs-anioLanzamiento')
		let descripcion = boton.getAttribute('data-bs-descripcion')
		
		
		let botonEnviar = modalEditar.querySelector('#botonEdit')
		let idEdit = modalEditar.querySelector('#idEdit')
		let tituloEdit = modalEditar.querySelector('#tituloEdit')
		let lemaEdit = modalEditar.querySelector('#lemaEdit')
		let precioEdit = modalEditar.querySelector('#precioEdit')
		let duracionEdit = modalEditar.querySelector('#duracionEdit')
		let stockEdit = modalEditar.querySelector('#stockEdit')
		let generoEdit = modalEditar.querySelector('#generoEdit')
		let anioLanzamientoEdit = modalEditar.querySelector('#anioLanzamientoEdit')
		let descripcionEdit = modalEditar.querySelector('#descripcionEdit')
		
		botonEnviar.setAttribute('href', "/TP_3_SplashingPopcorn_Entrega_Final2/editarPelicula.ad")
		idEdit.value = id;
		tituloEdit.value = titulo;
		lemaEdit.value 	= lema;
		precioEdit.value = precio;
		duracionEdit.value = duracion;
		stockEdit.value = stock;
		anioLanzamientoEdit.value = anioLanzamiento;
		descripcionEdit.value = descripcion;
		generoEdit.value = genero;
	});
}

if (!!document.getElementById('modalEliminarPromocion')) {

	var modalBorrarPromo = document.getElementById('modalEliminarPromocion')
	modalBorrarPromo.addEventListener('show.bs.modal', function(event) {
		
		let boton = event.relatedTarget

		let id = boton.getAttribute('data-bs-id')

		let botonEnviar = modalBorrarPromo.querySelector('#botonElim')

		botonEnviar.setAttribute('href', "/TP_3_SplashingPopcorn_Entrega_Final2/borrarPromocion.ad?id=" + id)
	});
}




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

function seleccionarPelicula(){
	let select = document.querySelector('#pelicula');
	let textAreaNombre = document.querySelector('#peliculas')
	let inputID = document.querySelector('#idPeliculas')
	
	const indice = select.selectedIndex;
	const opcionSeleccionada = select.options[indice];
	
	if(inputID.value==''){
		inputID.value = opcionSeleccionada.value;
	}else{
		inputID.value = inputID.value + ',' + opcionSeleccionada.value;
	}
	textAreaNombre.value = opcionSeleccionada.text + '\n' + textAreaNombre.value;
	select.remove(indice);
}



