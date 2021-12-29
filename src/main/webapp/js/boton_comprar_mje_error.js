var alertPlaceholder = document.getElementById('mensaje_error')
var alertTrigger = document.getElementById('boton-comprar')

function alert(message, type) {
  alertPlaceholder.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
}

if (alertTrigger) {
  alertTrigger.addEventListener('click', function () {
    alert('Por favor, inicie sesión para comprar', 'warning')
  })
}