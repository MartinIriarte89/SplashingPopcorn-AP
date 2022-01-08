<%@page import="servicios.ServicioPelicula"%>
<%@page import="servicios.ServicioPromocion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="servicioPelicula" class="servicios.ServicioPelicula" />
<jsp:useBean id="servicioPromocion" class="servicios.ServicioPromocion" />
<%
int id = Integer.parseInt(request.getParameter("id"));
boolean esPromo = Boolean.parseBoolean(request.getParameter("esPromo"));

if (esPromo) {
	pageContext.setAttribute("promo", servicioPromocion.buscarPor(id));
} else {
	pageContext.setAttribute("pelicula", servicioPelicula.buscarPor(id));
}
%>

<%
if (esPromo) {
%>
{"titulo":"<c:out value="${promo.titulo}"></c:out>","genero":"<c:out value="${promo.genero.nombre}"></c:out>","costo":"<c:out value="${promo.getPrecio()}"></c:out>","duracion":"<c:out value="${promo.getDuracion()}"></c:out>"}
<%
} else {
%>
{"titulo":"<c:out value="${pelicula.titulo}"></c:out>","genero":"<c:out value="${pelicula.genero.nombre}"></c:out>","costo":"<c:out value="${pelicula.precio}"></c:out>","duracion":"<c:out value="${pelicula.duracion}"></c:out>"}
<%
}
%>
