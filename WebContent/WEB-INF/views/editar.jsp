<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Nota</title>

<spring:url value="/Assets/reset_style.css" var="ResetStyleCSS" />
<link href="${ResetStyleCSS}" rel="stylesheet" />
<spring:url value="/Assets/note_style.css" var="NoteStyleCSS" />
<link href="${NoteStyleCSS}" rel="stylesheet" />

<script type="text/javascript">
	function adjust_textarea(h) {
	    h.style.width = "auto";
	    h.style.width = (h.scrollWidth)+"px";
	}
</script>

</head>
<body>
	<%!String current_user;
	Calendar current_date;
	String current_priority;
	String current_msg;
	Integer id;
	String data;%>

	<%current_user = (String) session.getAttribute("name");
	current_date = (Calendar) session.getAttribute("date");
	current_priority = (String) session.getAttribute("priority");
	current_msg = (String) session.getAttribute("msg");
	id = (Integer) session.getAttribute("id");

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	dateFormat.setTimeZone(current_date.getTimeZone());
	data = dateFormat.format(current_date.getTime());%>

	<div style="float:right;">Logado como <%=current_user%> <a href="logout" class="sair">Sair</a></div>
	<form action='edita_post' method='post' autocomplete="off">
		<input type="hidden" name="user" value="<%=current_user%>" readonly>
		<input type="hidden" name="id" value="<%=id%>" readonly>
		Tarefa: 
		<input type="text" name="msg" value="<%=current_msg%>" class="input-res" maxlength="48" onkeyup="adjust_textarea(this)"> 
		Data:
		<input type="date" name="date" value="<%=data%>" class="input-res" required="required"> 
		Prioridade:
		<select name='priority' class="input-res">
			<option value='Baixa'>!</option>
			<option value='Mediana'>!!</option>
			<option value='Alta'>!!!</option>
		</select> 
		<input type='submit' value='Confirmar edições' class='input-res'>
	</form>
</body>
</html>