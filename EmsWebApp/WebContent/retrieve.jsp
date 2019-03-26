<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="CSS/emsstylesheet.css">
<title>Retrieved Employee</title>
</head>
<body>
<h1>Retrieved Employee</h1><br><br>
<%
Object data = request.getSession().getAttribute("emp");
if (null == data) {
	request.getRequestDispatcher("error.html").forward(request, response);
} else {
	String emp = request.getSession().getAttribute("emp").toString();
	out.write(emp);
}
%>
<br><br><br><a href="http://localhost:8080/EmsWebApp/">Home</a>
</body>
</html>