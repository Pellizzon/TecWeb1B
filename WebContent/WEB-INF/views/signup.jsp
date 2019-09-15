<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<spring:url value="/Assets/reset_style.css" var="ResetStyleCSS" />
<link href="${ResetStyleCSS}" rel="stylesheet" />
<spring:url value="/Assets/home.css" var="HomeCSS" />
<link href="${HomeCSS}" rel="stylesheet" />
</head>
<body>
	<div class="content">
		<form action='efetuaCadastro' method='post' autocomplete="off">
			Nome: 
			<input type='text' name='name' required='required' class="input-res" maxlength="200"><br> 
			Usuário: 
			<input type='text' name='user' required='required' class="input-res" maxlength="200"><br>
			Senha: 
			<input type='password' name='password' required='required' class="input-res" maxlength="200"><br> 
			Confirmar Senha: 
			<input type='password' name='password_check' required='required' class="input-res" maxlength="200"><br> 
			<input type='submit' value='Cadastrar' class='signup'>
		</form>
	</div>
</body>
</html>