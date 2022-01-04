<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%><!-- без этого jstl не работает -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-" />
<title>Main page</title>
<link rel="icon" href="css/resources/cactus.png">
<link href="css/ind.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<div class="main">
			<h1>Login form</h1>
			<form action="logg" method="post" name="regForm">
				<div>
					<b>Enter name:</b> <input type="text" name="name" id="name"
						class="field" />
				</div>
				<div>
					<b>Enter password:</b> <input type="password" name="pass" id="pass"
						class="field" />
				</div>
				<div>
					<input type="submit" name="Sign Up" id="enter" class="field"
						value="Enter" />
				</div>

			</form>
		</div>

		<div id="message">
			<c:if test="${message != null}">
				<c:out value="Message: ${message}"></c:out>
			</c:if>
		</div>

	</div>

	<!-- 	<div class="referencies">
		<ul>
			<li><a href="catalog"> Показать все товары </a></li>
			<li id="hidden-element"><a href="add"> Добавить товар</a></li>
		</ul>
	</div> -->
</body>

</html>