$(document).ready(function() {
	$('.dataTable').DataTable({
		pageLength: 50,
		order: [0, 'asc'],
		 language: {
            url: '//cdn.datatables.net/plug-ins/1.11.4/i18n/es_es.json'
        }
        
	});
});