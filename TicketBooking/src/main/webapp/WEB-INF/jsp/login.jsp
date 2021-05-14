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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<style type="text/css">
body {
	text-align: center;
	border-style: solid;
	border-width: 5px;
	border-color: red;
	margin-left: 350px;
	margin-right: 350px;
	margin-top: 175px
}
</style>
</head>
<body>
	<h2>User Login</h2>
	<form:form modelAttribute="login" action="login" method="POST">
		<table style="width: 50%; padding: 10px" align="center" class="table">
			<tr>
				<td>Username</td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username" style="color:red;" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" style="color:red;" cssClass="error"></form:errors>
				<td>
			</tr>
		</table>
		<input type="Submit" value="Login" />&nbsp;&nbsp;&nbsp;
	<input type="Reset" value="Reset" />
	</form:form>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js "
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj "
		crossorigin="anonymous "></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js "
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN "
		crossorigin="anonymous "></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js "
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV "
		crossorigin="anonymous "></script>
</body>
</html>