<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%><!-- без этого jstl не работает -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link href="css/update.css" rel="stylesheet" />
<title>Catalog page</title>
</head>
<body>

	<div class="container">
		<h2>Update product</h2>

		<button type="button" class="btn btn-dark"
			onclick="window.location.href = 'catalog.jsp'">Back</button>
	</div>

	<div class="update">
		<form action="update" method="get" name="updateForm">
		<input type="hidden" name="id" value="${param.id}" />
			<div class="row">
				<div class="col">
					<input type="text" name="update-nameNew" class="form-control"
						placeholder="New product name">
				</div>
				<div class="col">
					<input type="text" name="update-priceNew" class="form-control"
						placeholder="New product price">
				</div>
			</div>
			<div>
				<button type="submit" class="btn btn-primary mb-2">Update</button>
			</div>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>