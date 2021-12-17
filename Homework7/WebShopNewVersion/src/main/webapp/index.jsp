<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String message = (String) request.getAttribute("message");
%>
<head>
<meta charset="UTF-8" />
<title>Главная страница</title>
<link href="css/index.css" rel="stylesheet" />
</head>

<body>
	<div class="main">
		<h1>Форма входа</h1>
		<form action="logg" method="post" name="regForm">
			<div>
				<b>Введите имя:</b> <input type="text" name="name" id="name"
					class="field" />
			</div>
			<div>
				<b>Введите пароль:</b> <input type="password" name="pass" id="pass"
					class="field" />
			</div>
			<div>
				<input type="submit" name="Sign Up" id="enter" class="field"
					value="Войти" />
			</div>

		</form>

		<div id="message">
			<%if (message != null) {%>
			Message:
			<%=message%>
			<%}%>
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