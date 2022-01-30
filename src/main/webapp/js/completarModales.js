$(document).ready(function() {
	//CODIGO USADO PARA CARGAR MODAL DE DETALLE DE COMPRAS
	if (!!document.getElementById('compra')) {

		var modalCompra = document.getElementById('compra')
		modalCompra.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget

			let titulo = boton.getAttribute('data-bs-titulo')
			let genero = boton.getAttribute('data-bs-genero')
			let costo = boton.getAttribute('data-bs-costo')
			let duracion = boton.getAttribute('data-bs-duracion')

			let tituloElem = modalCompra.querySelector('#modalTitulo')
			let generoElem = modalCompra.querySelector('#modalGenero')
			let precioElem = modalCompra.querySelector('#modalPrecio')
			let duracionElem = modalCompra.querySelector('#modalDuracion')

			tituloElem.innerHTML = titulo;
			generoElem.innerHTML = genero;
			precioElem.innerHTML = costo;
			duracionElem.innerHTML = duracion;
		});
	}

	if (!!document.getElementById('modalEditarDatosPersonales')) {

		var modalEditarDatosPersonales = document.getElementById('modalEditarDatosPersonales')
		modalEditarDatosPersonales.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget

			let nombre = boton.getAttribute('data-bs-nombre')
			let usuario = boton.getAttribute('data-bs-usuario')
			let preferencia = boton.getAttribute('data-bs-preferencia')

			let nombreEdit = modalEditarDatosPersonales.querySelector('#nombre')
			let usuarioEdit = modalEditarDatosPersonales.querySelector('#usuario')
			let preferenciaEdit = modalEditarDatosPersonales.querySelector('#genero')

			nombreEdit.value = nombre;
			usuarioEdit.value = usuario;
			preferenciaEdit.value = preferencia;
		});
	}

	if (!!document.getElementById('modalEliminarUsuario')) {

		var modalBorrarUsuario = document.getElementById('modalEliminarUsuario')
		modalBorrarUsuario.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget

			let id = boton.getAttribute('data-bs-id')

			let botonEnviar = modalBorrarUsuario.querySelector('#botonElim')

			botonEnviar.setAttribute('href', "./borrarUsuario.ad?id=" + id)
		});
	}

	if (!!document.getElementById('modalEditarUsuario')) {

		var modalEditarUsuario = document.getElementById('modalEditarUsuario')
		modalEditarUsuario.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget

			let id = boton.getAttribute('data-bs-id')
			let nombre = boton.getAttribute('data-bs-nombre')
			let usuario = boton.getAttribute('data-bs-usuario')
			let dineroDisp = boton.getAttribute('data-bs-dineroDisp')
			let tiempoDisp = boton.getAttribute('data-bs-tiempoDisp')
			let preferencia = boton.getAttribute('data-bs-preferencia')

			let idEdit = modalEditarUsuario.querySelector('#idEdit')
			let nombreEdit = modalEditarUsuario.querySelector('#nombreEdit')
			let usuarioEdit = modalEditarUsuario.querySelector('#usuarioEdit')
			let dineroEdit = modalEditarUsuario.querySelector('#dineroEdit')
			let tiempoEdit = modalEditarUsuario.querySelector('#tiempoEdit')
			let preferenciaEdit = modalEditarUsuario.querySelector('#generoEdit')

			idEdit.value = id;
			nombreEdit.value = nombre;
			usuarioEdit.value = usuario;
			dineroEdit.value = dineroDisp;
			tiempoEdit.value = tiempoDisp;
			preferenciaEdit.value = preferencia;
		});
	}

	/*CODIGO USADO PARA CARGAR MODALES DE ELIMINAR Y EDITAR DE PELICULAS*/

	if (!!document.getElementById('modalEliminarPelicula')) {

		var modalBorrar = document.getElementById('modalEliminarPelicula')
		modalBorrar.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget

			let id = boton.getAttribute('data-bs-id')

			let botonEnviar = modalBorrar.querySelector('#botonElim')

			botonEnviar.setAttribute('href', "./borrarPelicula.ad?id=" + id)
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


			let idEdit = modalEditar.querySelector('#idEdit')
			let tituloEdit = modalEditar.querySelector('#tituloEdit')
			let lemaEdit = modalEditar.querySelector('#lemaEdit')
			let precioEdit = modalEditar.querySelector('#precioEdit')
			let duracionEdit = modalEditar.querySelector('#duracionEdit')
			let stockEdit = modalEditar.querySelector('#stockEdit')
			let generoEdit = modalEditar.querySelector('#generoEdit')
			let anioLanzamientoEdit = modalEditar.querySelector('#anioLanzamientoEdit')
			let descripcionEdit = modalEditar.querySelector('#descripcionEdit')

			idEdit.value = id;
			tituloEdit.value = titulo;
			lemaEdit.value = lema;
			precioEdit.value = precio;
			duracionEdit.value = duracion;
			stockEdit.value = stock;
			anioLanzamientoEdit.value = anioLanzamiento;
			descripcionEdit.value = descripcion;
			generoEdit.value = genero;
		});
	}

	/*CODIGO PARA CREAR ELIMINAR Y EDITAR PROMOCIONES*/

	if (!!document.getElementById('modalEliminarPromocion')) {

		var modalBorrarPromo = document.getElementById('modalEliminarPromocion')
		modalBorrarPromo.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget

			let id = boton.getAttribute('data-bs-id')

			let botonEnviar = modalBorrarPromo.querySelector('#botonElim')

			botonEnviar.setAttribute('href', "./borrarPromocion.ad?id=" + id)
		});
	}

	if (!!document.getElementById('modalEditarPromocion')) {

		var modalEditarPromocion = document.getElementById('modalEditarPromocion')
		modalEditarPromocion.addEventListener('show.bs.modal', function(event) {

			let boton = event.relatedTarget
			let id = boton.getAttribute('data-bs-id')
			let titulo = boton.getAttribute('data-bs-titulo')
			let idPelis = boton.getAttribute('data-bs-idPelis')
			let arrayIdPelis = idPelis.split(',')
			let genero = boton.getAttribute('data-bs-genero')
			let descripcion = boton.getAttribute('data-bs-descripcion')
			let beneficio = boton.getAttribute('data-bs-beneficio')
			let tipoPromocion = boton.getAttribute('data-bs-tipoPromocion')

			let idEdit = modalEditarPromocion.querySelector('#idEdit')
			let tituloEdit = modalEditarPromocion.querySelector('#tituloEdit')
			let tipoPromocionEdit = modalEditarPromocion.querySelector('#tipoPromocionEdit')
			let beneficioEdit = modalEditarPromocion.querySelector('#beneficioEdit')
			let descripBeneficioEdit = modalEditarPromocion.querySelector('#descrip-beneficioEdit')
			let selectPeliculaEdit = modalEditarPromocion.querySelector('#selectPeliculaEdit')
			let peliculasEdit = modalEditarPromocion.querySelector('#peliculasEdit')
			let idPeliculasEdit = modalEditarPromocion.querySelector('#idPeliculasEdit')
			let descripcionEdit = modalEditarPromocion.querySelector('#descripcionEdit')

			idEdit.value = id;
			tituloEdit.value = titulo;
			descripcionEdit.value = descripcion;
			tipoPromocionEdit.value = tipoPromocion;
			beneficioEdit.value = beneficio;

			for (let x = 0; x < arrayIdPelis.length; x++) {

				selectPeliculaEdit.value = arrayIdPelis[x];
				let index = selectPeliculaEdit.selectedIndex;
				let precio = selectPeliculaEdit.options[index].dataset.precio

				peliculasEdit.value = selectPeliculaEdit.options[index].text + " ($" + precio + ')\n' + peliculasEdit.value;
				if (idPeliculasEdit.value == '') {
					idPeliculasEdit.value = selectPeliculaEdit.options[index].value;
				} else {
					idPeliculasEdit.value = idPeliculasEdit.value + ',' + selectPeliculaEdit.options[index].value;
				}
				selectPeliculaEdit.remove(index);
			}

			for (let i = selectPeliculaEdit.options.length - 1; i >= 0; i--) {

				let generoTemp = selectPeliculaEdit.options[i].attributes[1].nodeValue
				if (generoTemp != genero) {
					selectPeliculaEdit.remove(i)
				}
			}

			let indice = tipoPromocionEdit.selectedIndex
			let opcionSeleccionada = tipoPromocionEdit.options[indice];

			if (opcionSeleccionada.text == 'Super descuentos') {
				descripBeneficioEdit.innerHTML = 'Ingresar un %';
			}

			else if (opcionSeleccionada.text == 'Precios locos') {

				descripBeneficioEdit.innerHTML = 'Ingresar un monto fijo';
			}

			else {
				descripBeneficioEdit.innerHTML = 'N° de películas a cobrar';
			}

		});
	}

});

/* codigo usado para completar el modal de crear promociones*/



if (!!document.getElementById('selectPelicula')) {
	/*se crea una copia del select que contiene a todas las peliculas*/
	let nodosOptions = document.querySelector('#selectPelicula').children;
	let copia = Array();
	var copiaNodosOptions = Object.assign(copia, nodosOptions);
}




function seleccionarPelicula() {

	let select = document.querySelector('#selectPelicula');
	let textAreaNombre = document.querySelector('#peliculas')
	let inputID = document.querySelector('#idPeliculas')

	/*se obtiene el option seleccionado*/
	const indice = select.selectedIndex;
	const opcionSeleccionada = select.options[indice];
	/*y el genero de la pelicula elegida*/
	const genero = opcionSeleccionada.attributes[1].nodeValue;
	const precio = opcionSeleccionada.dataset.precio;

	select.remove(indice);

	/*si es la primera vez que se agrega una pelicula*/
	if (inputID.value == '') {
		inputID.value = opcionSeleccionada.value;
		/*se eliminan las peliculas que no coincidan con el genero de la seleccionada del select*/
		for (let i = select.options.length - 1; i >= 0; i--) {
			let generoTemp = select.options[i].attributes[1].nodeValue
			if (generoTemp != genero) {
				select.remove(i)
			}
		}
		/*si ya hay una pelicula seleccionada*/
	} else {
		inputID.value = inputID.value + ',' + opcionSeleccionada.value;
	}
	textAreaNombre.value = opcionSeleccionada.text + ' ($' + precio + ') \n' + textAreaNombre.value;
}


function resetear() {
	let select = document.querySelector('#selectPelicula');
	let textAreaNombre = document.querySelector('#peliculas');
	let inputID = document.querySelector('#idPeliculas');
	/*se borra todo el contenido del select de peliculas */
	for (let i = select.options.length - 1; i >= 0; i--) {
		select.remove(i);
	}
	/*con la copia del select se reestablecen los valores por defecto*/
	for (let i = 0; i < copiaNodosOptions.length; i++) {
		select.appendChild(copiaNodosOptions[i]);
	}

	/*se limpia el textArea y el input que envia los id de las peliculas al servlet*/
	textAreaNombre.value = '';
	inputID.value = '';
}

function cambiar() {

	var descripBeneficio = document.querySelector('#descrip-beneficio');
	var beneficio = document.querySelector('#tipoPromocion');

	let indice = beneficio.selectedIndex
	let opcionSeleccionada = beneficio.options[indice];

	if (opcionSeleccionada.text == 'Super descuentos') {
		descripBeneficio.innerHTML = 'Ingresar un %';
	}

	else if (opcionSeleccionada.text == 'Precios locos') {

		descripBeneficio.innerHTML = 'Ingresar un monto fijo';
	}

	else {
		descripBeneficio.innerHTML = 'N° de películas a cobrar';
	}
};



/* Codigo para completar el modal editar Promocion */



if (!!document.getElementById('selectPeliculaEdit')) {
	/*se crea una copia del select que contiene a todas las peliculas*/
	let nodosOptions = document.querySelector('#selectPeliculaEdit').children;
	let copia = Array();
	var copiaNodosOptionsEdit = Object.assign(copia, nodosOptions);
}


function seleccionarPeliculaEdit() {
	let select = document.querySelector('#selectPeliculaEdit');
	let textAreaNombre = document.querySelector('#peliculasEdit')
	let inputID = document.querySelector('#idPeliculasEdit')

	/*se obtiene el option seleccionado*/
	const indice = select.selectedIndex;
	const opcionSeleccionada = select.options[indice];
	/*y el genero de la pelicula elegida*/
	const genero = opcionSeleccionada.attributes[1].nodeValue
	const precio = opcionSeleccionada.dataset.precio

	select.remove(indice);

	/*si es la primera vez que se agrega una pelicula*/
	if (inputID.value == '') {
		inputID.value = opcionSeleccionada.value;
		/*se eliminan las peliculas que no coincidan con el genero de la seleccionada del select*/
		for (let i = select.options.length - 1; i >= 0; i--) {
			let generoTemp = select.options[i].attributes[1].nodeValue
			if (generoTemp != genero) {
				select.remove(i)
			}
		}
		/*si ya hay una pelicula seleccionada*/
	} else {
		inputID.value = inputID.value + ',' + opcionSeleccionada.value;
	}
	textAreaNombre.value = opcionSeleccionada.text + " ($" + precio + ')\n' + textAreaNombre.value;
}

function resetearEdit() {
	let select = document.querySelector('#selectPeliculaEdit');
	let textAreaNombre = document.querySelector('#peliculasEdit');
	let inputID = document.querySelector('#idPeliculasEdit');
	/*se borra todo el contenido del select de peliculas */
	for (let i = select.options.length - 1; i >= 0; i--) {
		select.remove(i);
	}

	/*con la copia del select se reestablecen los valores por defecto*/
	for (let i = 0; i < copiaNodosOptionsEdit.length; i++) {
		select.appendChild(copiaNodosOptionsEdit[i]);
	}

	/*se limpia el textArea y el input que envia los id de las peliculas al servlet*/
	textAreaNombre.value = '';
	inputID.value = '';
}


function cambiarEdit() {

	var descripBeneficio = document.querySelector('#descrip-beneficioEdit');
	var beneficio = document.querySelector('#tipoPromocionEdit');

	let indice = beneficio.selectedIndex
	let opcionSeleccionada = beneficio.options[indice];

	if (opcionSeleccionada.text == 'Super descuentos') {
		descripBeneficio.innerHTML = 'Ingresar un %';
	}

	else if (opcionSeleccionada.text == 'Precios locos') {

		descripBeneficio.innerHTML = 'Ingresar un monto fijo';
	}

	else {
		descripBeneficio.innerHTML = 'N° de películas a cobrar';
	}
};