<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.*, mvc.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notas</title>

<spring:url value="/Assets/reset_style.css" var="ResetStyleCSS" />
<link href="${ResetStyleCSS}" rel="stylesheet" />
<spring:url value="/Assets/note_style.css" var="NoteStyleCSS" />
<link href="${NoteStyleCSS}" rel="stylesheet" />

<script type="text/javascript">
	function adjust_textarea(h) {
	    h.style.width = "auto";
	    h.style.width = (h.scrollWidth)+"px";
	}
	
	function Search() {
		  // Declare variables
		  var input, filter, notes, note, i, txtValue;
		  input = document.getElementById('myInput');
		  filter = input.value.toUpperCase();
		  notes = document.getElementsByClassName("myNotes");
		
		  // Loop through all list items, and hide those who don't match the search query
		  for (i = 0; i < notes.length; i++) {
		    note = notes[i].getElementsByTagName("span")[0];
		    txtValue = note.textContent || note.innerText;
		    if (txtValue.toUpperCase().indexOf(filter) > -1) {
		      notes[i].style.display = "";
		    } else {
		      notes[i].style.display = "none";
		    }
		  }
		}
</script>
</head>

<body>
	<jsp:useBean id="dao" class="mvc.model.NoteDAO" />
	<%!String current_user;
	String color;
	String filtro;
	boolean filter;
	List<Notas> item;%>
	
	<%current_user = (String) session.getAttribute("name");
	filtro = (String) session.getAttribute("filtro");%>
	
	<div style="float:right;">Logado como <%=current_user%> <a href="logout" class="sair">Sair</a></div>
	
	<form action="cria" method="post" autocomplete="off">
		<input type="hidden" name="user" value="<%=current_user%>" readonly>
		Tarefa: 
		<input type="text" name="msg" required="required" class="input-res" maxlength="48" onkeyup="adjust_textarea(this)"> 
		Data: 
		<input type="date" name="date" class="input-res" required="required"> 
		Prioridade: 
		<select name="priority" class="input-res">
			<option value="Baixa">!</option>
			<option value="Mediana">!!</option>
			<option value="Alta">!!!</option>
		</select> 
		<input type="submit" value="Enviar" class="actionButton">
	</form>
	<form action="remove" method="post" autocomplete="off">
		<input type="hidden" name="user" value="<%=current_user%>" readonly>
		Remover nota: 
		<select name="id" class="input-res">
			<c:forEach var="note" items="<%=dao.getFiltro(filtro)%>" varStatus="id">
				<option value="${note.id}">${id.count}</option>
			</c:forEach>
		</select> 
		<input type="submit" value="Remover" class="actionButton">
	</form>
	<form action="ordernar" method="post" autocomplete="off">
		<input type="hidden" name="user" value="<%=current_user%>" readonly>
		Ordenar: 
		<input type="submit" name="variable" class="actionButton" value="Data" > 
		<input type="submit" name="variable" class="actionButton" value="Usuário"> 
		<input type="submit" name="variable" class="actionButton" value="Prioridade">
		<input type="submit" name="variable" class="actionButton" value="Remover Ordenação">
	</form><br>
	Buscar: <input type="text" id="myInput" class="input-res" onkeyup="Search()" placeholder="Procurar por tarefas">
	<div class='container'>
	<c:forEach var="note" items="<%=dao.getFiltro(filtro)%>" varStatus="id">
		<div class="myNotes">
			<c:choose>
				<c:when test="${note.priority == 'Baixa'}"><%color = "#31f54e";%></c:when>
				<c:when test="${note.priority == 'Mediana'}"><%color = "#ffec1c";%></c:when>
				<c:otherwise><%color = "#f53e31";%></c:otherwise>
			</c:choose>
			<div style="background-color:<%=color%>; border-top-color:<%=color%>" class="infos">
				N°: ${id.count}<br> 
				Usuário: ${note.user}<br>
				Data: <fmt:formatDate value="${note.date.time}" pattern="dd/MM/yyyy"/><br> 
				Prioridade: ${note.priority}<br>
				Tarefa: <span>${note.msg}</span>
			</div>
			<form action="edita" method="get" autocomplete="off">
				<input type="hidden" name="user" value="<%=current_user%>" readonly>
				<input type="hidden" name="date" value="<fmt:formatDate value="${note.date.time}" pattern="yyyy-MM-dd"/>" readonly>
				<input type="hidden" name="priority" value="${note.priority}" readonly>
				<input type="hidden" name="msg" value="${note.msg}" readonly> 
				<input type="hidden" name="id" value="${note.id}" readonly>
				<input class="button" type="submit" value="Editar nota ${id.count}">
			</form>
		</div>
	</c:forEach>
	</div>
</body>
</html>