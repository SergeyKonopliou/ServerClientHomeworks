<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="by.main.Good"%>
<!DOCTYPE html>
<html>
<%
ArrayList<Good> list = new ArrayList<Good>();
list = (ArrayList<Good>) request.getAttribute("catalog");
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Catalog page</title>
</head>
<body>
	<h1>Каталог всех товаров</h1>

	<div id="main">
		<table class="table table-bordered">
			<tr>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col"></th>
            </tr>
			<%
				for (int i = 0; i < list.size(); i++) {
				Good good = new Good();
				good = list.get(i);
			%>

			<tr>
				<td><%=good.getName()%></td>
				<td><%=good.getPrice()%></td>
				<td><button onclick="myFunction()">Buy</button></td>
			</tr>
			<%
				}
			%>

		</table>
	</div>
<script>
function myFunction() {
    alert("Add");
}
</script>

	<a href="AddOutServlet"> Назад </a>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>