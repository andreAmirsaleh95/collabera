<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="CSS/emsstylesheet.css">
<title>Update</title>
</head>
<body>
<%
System.out.println("oldId = " + session.getAttribute("oldId").toString());
session.setAttribute("oldId", session.getAttribute("oldId").toString());
%>
<div class="container-fluid">
<form method="post" action="update2">
<h1>Update Employee</h1>
		Full name:<br><input name="full_name" type="text" value="<%=session.getAttribute("p_emp_name").toString()%>" required><br><br>
		Age (10-99):<br><input name="age" type="text" name="age" pattern="0|[1-9]\d{1,1}" value="<%=session.getAttribute("p_age").toString()%>" required><br><br>
		Gender:<br>
		<input type="radio" name="gender" value="0" <% if (0 == Integer.parseInt(session.getAttribute("p_gender").toString())){out.write("checked");} %>> Male<br>
		<input type="radio" name="gender" value="1" <% if (1 == Integer.parseInt(session.getAttribute("p_gender").toString())){out.write("checked");} %>> Female<br>
		<input type="radio" name="gender" value="2" <% if (2 == Integer.parseInt(session.getAttribute("p_gender").toString())){out.write("checked");} %>> Other<br>
		Phone number (1112345678):<br><input type="text" name="contactNo" pattern="^[0-9]{10}" value="<%=session.getAttribute("p_phone_num").toString()%>" required><br><br>
		Address:<br><input name="address" value="<%=session.getAttribute("p_address").toString()%>" required><br><br>
		Employee ID (non-negative int less than 100000):<br><input name="empId" type="text" pattern="0|[1-9]\d{0,4}" value="<%=session.getAttribute("p_emp_id").toString()%>" required><br><br>
		Social security number (non-negative and 9 digits):<br><input name="ssn" type="text" pattern="^[0-9]{9}" value="<%=session.getAttribute("p_ssn").toString()%>" required><br><br>
		Email:<br><input name="email" type="text" pattern="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" value="<%=session.getAttribute("p_email").toString()%>" required><br><br>
		Job title:<br><input name="jobTitle" value="<%=session.getAttribute("p_job_title").toString()%>"><br><br>
		Department:<br>
		<input type="radio" name="department" value="0" <% if (0 == Integer.parseInt(session.getAttribute("p_department").toString())){out.write("checked");} %>> Development<br>
		<input type="radio" name="department" value="1" <% if (1 == Integer.parseInt(session.getAttribute("p_department").toString())){out.write("checked");} %>> HR<br>
		<input type="radio" name="department" value="2" <% if (2 == Integer.parseInt(session.getAttribute("p_department").toString())){out.write("checked");} %>> Sales<br><br>
		Salary (0-99999999.99):<br><input name="salary" type="text" pattern="^[0-9]+(\.[0-9]{1,2})?$" value="<%=session.getAttribute("p_salary").toString()%>" required><br><br>
		Manager ID (non-negative int up to 5 digits // temporary value 0):<br><input name="reportTo" type="text" pattern="0|[1-9]\d{4,4}" value="<%=session.getAttribute("p_reportsTo").toString()%>" required><br><br>
		Manager?<br><br>
		<input type="radio" name="isManager" value="0" <% if (0 == Integer.parseInt(session.getAttribute("p_isManager").toString())){out.write("checked");} %>> Yes<br><br>
		<input type="radio" name="isManager" value="1" <% if (1 == Integer.parseInt(session.getAttribute("p_isManager").toString())){out.write("checked");} %>> No<br><br><br><br><br>
		<input type="submit" value="Submit">
	</form>
	</div>
</body>
</html>