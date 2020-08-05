<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Login Here</h1>
	<font color="red">${errMsg }</font>
	<font color="red">${lockedErr }</font>

	<form:form action="login" modelAttribute="loginPage" method="POST">
		<table align="center">
			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><form:password path="pwd" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Sign In" /></td>
			</tr>
			<tr>
				<td><a href="register">Sign Up</a></td>
				<td><a href="forgotPwd">Forgot Pwd</a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>