	$(document).ready(function() {
		if($(window).width() >= 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 3,
				items_per_page : 3,
				item_container_id : '.contenido'
			});
		  }
		
		if($(window).width() < 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 3,
				items_per_page : 9,
				item_container_id : '.contenido'
			});
		    
		  }

		if($(window).width() < 752){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 3,
				items_per_page : 10,
				item_container_id : '.contenido'
			}); 
		  }
	});
	
	$(window).resize(function() {
		
		if($(window).width() >= 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 3,
				items_per_page : 3,
				item_container_id : '.contenido'
			});
		    
		  }
		
		if($(window).width() < 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 3,
				items_per_page : 9,
				item_container_id : '.contenido'
			});
		    
		  }

		if($(window).width() < 752){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 3,
				items_per_page : 10,
				item_container_id : '.contenido'
			});
		    
		  }

	});