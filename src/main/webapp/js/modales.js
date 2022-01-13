//DIFERENCIA EL MODAL DE EDICION AL DE CREACION
function abrirModal(texto) {
	if (texto == 'editar') {
		let myModal = new bootstrap.Modal(document.getElementById('modalEditarPelicula'));
		myModal.show();
	}
	if (texto == 'crear') {
		let myModal = new bootstrap.Modal(document.getElementById('modalCrearPelicula'));
		myModal.show();
	}
}

function abrirModalPromo(texto) {
	if (texto == 'editar') {
		let myModal = new bootstrap.Modal(document.getElementById('modalEditarPromocion'));
		myModal.show();
	}
	if (texto == 'crear') {
		let myModal = new bootstrap.Modal(document.getElementById('modalCrearPromocion'));
		myModal.show();
	}
}

function abrirModalUsuario(texto) {
	if (texto == 'editar') {
		let myModal = new bootstrap.Modal(document.getElementById('modalEditarUsuario'));
		myModal.show();
	}
	if (texto == 'crear') {
		let myModal = new bootstrap.Modal(document.getElementById('modalCrearUsuario'));
		myModal.show();
	}
}

//MODALE DE ALERTAS VARIAS
function abrirModalFlash() {
	let myModal = new bootstrap.Modal(document.getElementById('flash'));
	myModal.show();
}

//MODALES PARA LA COMPRA
function abrirModalSuccess() {
	let myModal = new bootstrap.Modal(document.getElementById('success'));
	myModal.show();
}

function abrirModalError() {
	let myModal = new bootstrap.Modal(document.getElementById('error'));
	myModal.show();
}

function abrirModalInicioSesion() {
	let myModal = new bootstrap.Modal(document.getElementById('modalInicioSesion'));
	myModal.show();
}