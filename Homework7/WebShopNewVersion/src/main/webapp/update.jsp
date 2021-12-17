<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String message = (String) request.getAttribute("message");
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Изменение каталога</title>
</head>
<body>

	<h2>Добавить новый товар</h2>
	<form action="update" method="get" name="addForm">
		<input type="hidden" name="type" value="addNew" />
		<div class="row">
			<div class="col">
				<input type="text" name="update-name" class="form-control"
					placeholder="Название товара">
			</div>
			<div class="col">
				<input type="text" name="update-price" class="form-control"
					placeholder="Цена">
			</div>
		</div>
		<button type="submit" class="btn btn-primary mb-2">Добавить</button>
	</form>

	<p>
	<p>
	<h2>Удалить товар</h2>
	<form action="update" method="get" name="deleteForm">
		<input type="hidden" name="type" value="delete" />
		<div class="row">
			<div class="col">
				<input type="text" name="update-name" class="form-control"
					placeholder="Название товара">
			</div>
		</div>
		<button type="submit" class="btn btn-primary mb-2">Удалить</button>
	</form>

	<p>
	<p>
	<h2>Изменить товар</h2>
	<form action="update" method="get" name="updateForm">
		<input type="hidden" name="type" value="update" />
		<div class="row">
			<div class="col">
				<input type="text" name="update-name" class="form-control"
					placeholder="Название товара">
			</div>
			<div class="col">
				<input type="text" name="update-nameNew" class="form-control"
					placeholder="Новое название товара">
			</div>
			<div class="col">
				<input type="text" name="update-priceNew" class="form-control"
					placeholder="Новая цена">
			</div>
		</div>
		<button type="submit" class="btn btn-primary mb-2">Изменить</button>
	</form>

	<br>
	<div class="text-info bg-dark">
		<%
			if (message != null) {
		%>
		Message:
		<%=message%>
		<%
			}
		%>
	</div>
	<br>
	<a href="userPage.jsp"> Назад </a>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>