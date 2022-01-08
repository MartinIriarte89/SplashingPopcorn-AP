<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!-- MODAL SUCCESS -->
	<div class="modal fade" id="success" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content bg-success">
				<div class="modal-body mx-auto my-4 text-white fw-bold fs-5">${success}</div>
				<div class="modal-footer d-flex border-0">
					<button type="button" class="btn btn-outline-light mx-auto"
						data-bs-dismiss="modal">Aceptar</button>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL ERROR -->
	<div class="modal fade" id="error" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content bg-warning">
				<div class="modal-header border-0">
					<h5 class="modal-title mx-auto fw-bold" id="exampleModalLabel"
						style="text-decoration: underline;">¡ATENCIÓN!</h5>
				</div>
				<div class="modal-body mx-auto">

					<div class="fw-bold" style="text-decoration: underline;">Se
						encontraron errores:</div>
					<c:if test='${error.get("usuario") != null}'>
						<div>${error.get("usuario")}</div>
					</c:if>
					<c:if test='${error.get("producto") != null}'>
						<div>${error.get("producto")}</div>
					</c:if>
					<c:if test='${error.get("fallo") != null}'>
						<div>${error.get("fallo")}</div>
					</c:if>
				</div>
				<div class="modal-footer d-flex border-0">
					<button type="button" class="btn btn-outline-dark mx-auto"
						data-bs-dismiss="modal">Aceptar</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>