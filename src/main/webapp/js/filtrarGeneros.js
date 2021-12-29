function reescalar() {
	if ($(window).width() >= 1184) {

		$('#contenedor-paginador-xxl').pajinate({
			num_page_links_to_display: 5,
			items_per_page: 15,
			item_container_id: '.contenido'
		});
	}

	if ($(window).width() < 1184) {

		$('#contenedor-paginador-xxl').pajinate({
			num_page_links_to_display: 5,
			items_per_page: 9,
			item_container_id: '.contenido'
		});

	}

	if ($(window).width() < 748) {

		$('#contenedor-paginador-xxl').pajinate({
			num_page_links_to_display: 3,
			items_per_page: 6,
			item_container_id: '.contenido'
		});
	}
};

$(document).ready(function() {

	var nodos = document.querySelector('.contenido').children;
	let copia = Array();
	let copiaNodos = Object.assign(copia, nodos);
	let padre = document.querySelector('.contenido');

	// AGREGANDO CLASE ACTIVE AL PRIMER ENLACE ====================
	console.log($('#todos'));
	$('.lista-generos .genero-item[data-type="todos"]').click();
	
	
	// AGREGANDO CLASE AL BOTON PULSADO ================================
	$('.boton').click(function(){
		$('.boton').removeClass('animate__animated animate__tada');
		$(this).addClass('animate__animated animate__tada');
		
	});	

	// FILTRANDO PRODUCTOS  ============================================
	$('.genero-item').click(function() {
		var genero = $(this).attr('data-type');

		function eliminar() {
			for (let i = 0; i < nodos.length; i++) {
				padre.removeChild(nodos[i]);
				i--;
			}
		} eliminar();

		// AGREGANDO CLASE ACTIVE AL ENLACE SELECCIONADO

		// MOSTRANDO PRODUCTOS =========================
		function showCard() {
			for (let i = 0; i < copiaNodos.length; i++) {
				if (copiaNodos[i].dataset.type == genero) {
					padre.appendChild(copiaNodos[i])
				}
			}
		} showCard();
		reescalar();
	});

	// MOSTRANDO TODOS LOS PRODUCTOS =======================

	$('.genero-item[data-type="todos"]').click(function() {
		function showAll() {
			for (let i = 0; i < copiaNodos.length; i++) {
				padre.appendChild(copiaNodos[i])
			}
		} showAll();
		reescalar();
	});
});
