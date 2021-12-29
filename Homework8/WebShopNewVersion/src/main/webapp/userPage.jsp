<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%><!-- без этого jstl не работает -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>User page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link href="css/ind.css" rel="stylesheet" />
</head>

<body>
	<div class="main">
		<div class="alert alert-success" role="alert">
			<h4 class="alert-heading">You are successfully logged in!</h4>
			<p>
				<c:out value="Login: ${loginName}"></c:out>	
			</p>
			<p>
				<c:out value="Password: ${loginPass}"></c:out>
			</p>
			<hr>
			<p class="mb-0">Now you can work with the catalog.</p>
		</div>
	</div>
	<div class="referencies">
		<ul>
			<li><a href="all"> Show catalog </a></li>
			<li><a href="LogoutServlet"> Exit</a></li>
		</ul>
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