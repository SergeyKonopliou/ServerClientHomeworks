<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Главная страница</title>
  <link href="css/index.css" rel="stylesheet"/> 
</head>

<body>
<div class="main">
        <h1>Форма входа</h1>
        <form action="" method="get" name="regForm">
            <div>
                <b>Введите имя:</b> 
                  <input type="text" name="email" id="email" class="field"/>
            </div>
            <div>
                <b>Введите пароль:</b>                       
                <input type="password" name="pass" id="pass" class="field"/>
            </div>
            <div>
                <input type="submit" name="Sign Up" id="submit" class="field" value="Войти" />
            </div>

        </form>
    </div>
    <div class="referencies">
    	<ul>
            <li><a href="all"> Показать все товары </a></li>
            <li><a href="all?price=700"> Показать 700  </a></li>
            <li><a href="buy"> Корзина</a></li>
            <li><a href="add"> Добавить товар</a></li>
        </ul>
	</div>

</body>

</html>