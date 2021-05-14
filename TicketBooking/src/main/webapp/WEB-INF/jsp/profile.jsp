<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js "></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js "></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js "></script>
</head>
<body style="text-align: center;">
	<h3>User Profile</h3>
	<div class="container">
		<table style="width: 80%; border: 2px solid red; margin-left: 150px"
			class="table table-hover">
			<tr class="table-primary">
				<td>Username</td>
				<td>${user.username}</td>
			</tr>
			<tr class="table-primary">
				<td>Email</td>
				<td>${user.email}</td>
			</tr>
			<tr class="table-primary">
				<td>Age</td>
				<td>${user.age}</td>
			</tr>
			<tr class="table-primary">
				<td>Contact Number</td>
				<td>${user.contactNo}</td>
			</tr>
			<tr class="table-primary">
				<td>Role</td>
				<td>${user.userRole}</td>
			</tr>
			<tr>
				<td><div class="col-2" style="text-align: center;">
						<a href="users/${userid}/edit" class="btn btn-info" role="button">Edit
						</a>
					</div></td>
				<td><div class="col-2">
						<a href="show" class="btn btn-info" role="button">Logout</a>
					</div></td>
			</tr>
		</table>
	</div>
</body>
</html>