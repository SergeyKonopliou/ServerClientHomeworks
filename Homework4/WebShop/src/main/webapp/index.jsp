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
        <form action="logg" method="post" name="regForm">
            <div>
                <b>Введите имя:</b> 
                  <input type="text" name="name" id="name" class="field"/>
            </div>
            <div>
                <b>Введите пароль:</b>                       
                <input type="password" name="pass" id="pass" class="field"/>
            </div>
            <div>
                <input type="submit" name="Sign Up" id="enter" class="field" value="Войти" />
            </div>

        </form>
    </div>
    <div class="referencies">
    	<ul>
            <li><a href="all"> Показать все товары </a></li>
            <li id="hidden-element"><a href="add"> Добавить товар</a></li>
        </ul>
	</div>

<!-- <script>
  document.getElementById('enter').addEventListener("click", hiddenCloseclick);
	function hiddenCloseclick() {
	let x = document.getElementById('hidden-element');
      if (x.style.display == "none"){
	  x.style.display = "block";
	  } else {
	 x.style.display = "none"}
    };
	
  </script> -->

</body>

</html>