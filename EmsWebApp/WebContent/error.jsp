<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="CSS/emsstylesheet.css">
<title>Error</title>
</head>
<body>
<%
List<String> errMsgs = (LinkedList<String>) session.getAttribute("errorMsgs");
%>
<div class="container-fluid">
<img alt="Error" src="https://www.computerhope.com/jargon/e/error.gif">
	<h1>Error!</h1>
	<%for (String msg : errMsgs) {%>
		<p><%=msg%></p><br><br>
	<%}%>
	<br><br><br><a href="http://localhost:8080/EmsWebApp/">Home</a>
</div>
</body>
</html>