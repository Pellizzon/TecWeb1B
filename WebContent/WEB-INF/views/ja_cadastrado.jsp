<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<spring:url value="/Assets/reset_style.css" var="ResetStyleCSS" />
<link href="${ResetStyleCSS}" rel="stylesheet" />
<spring:url value="/Assets/home.css" var="HomeCSS" />
<link href="${HomeCSS}" rel="stylesheet" />
<title>Já cadastrado</title>
</head>
<body>
Usuário já cadastrado. 
<a href="cadastrar" class='signup'>Voltar</a>
</body>
</html>