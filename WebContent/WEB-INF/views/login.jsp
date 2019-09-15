<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<spring:url value="/Assets/reset_style.css" var="ResetStyleCSS" />
<link href="${ResetStyleCSS}" rel="stylesheet" />
<spring:url value="/Assets/home.css" var="HomeCSS" />
<link href="${HomeCSS}" rel="stylesheet" />
</head>
<body>

	<div class="content">
		<form action='efetuaLogin' method='get' autocomplete="off">
			Usuário: 
			<input type='text' name='user' required='required' class="input-res"><br> 
			Senha: 
			<input type='password' name='password' required='required' class="input-res"><br>
			<input type='submit' value='Login'>
		</form>
		<a href="cadastrar" class='signup'>Cadastrar</a>
	</div>
</body>
</html>