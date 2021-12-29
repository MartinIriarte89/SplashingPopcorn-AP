	$(document).ready(function() {
		if($(window).width() >= 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 5,
				items_per_page : 15,
				item_container_id : '.contenido'
			});
		  }
		
		if($(window).width() < 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 5,
				items_per_page : 9,
				item_container_id : '.contenido'
			});
		    
		  }

		if($(window).width() < 748){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 2,
				items_per_page : 6,
				item_container_id : '.contenido'
			}); 
		  }
	});
	
	$(window).resize(function() {
		
		if($(window).width() >= 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 5,
				items_per_page : 15,
				item_container_id : '.contenido'
			});
		    
		  }
		
		if($(window).width() < 1184){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 5,
				items_per_page : 9,
				item_container_id : '.contenido'
			});
		    
		  }

		if($(window).width() < 748){
		    
			$('#contenedor-paginador-xxl').pajinate({
				num_page_links_to_display : 2,
				items_per_page : 6,
				item_container_id : '.contenido'
			});
		    
		  }

	});