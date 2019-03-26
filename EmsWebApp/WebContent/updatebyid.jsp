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
<title>Update by ID</title>
</head>
<body>
<div class="container-fluid">
<form action="populater" method="post">
<h1>Enter ID of Employee to Update</h1>
Employee ID (non-negative int up to 5 digits): <select name="empId">
<%
List<String> idList = (LinkedList<String>) session.getAttribute("idList");
for (String id : idList) {
%>
<option value="<%=id%>"><%=id%></option>
<%
}
%>
</select>
<input type="submit" value="Submit">
</form>
</div>
</body>
</html>