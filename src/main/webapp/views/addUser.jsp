<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/app.js"></script>
</head>
<body>
	<h1>Registration Page</h1>
	<form:form action="userAccReg" modelAttribute="user" method="post">
		<table>
			<tr>
				<th>First Name:</th>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<th>Last Name:</th>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><form:input path="userEmail" /> <font color="red">
						<div id="dupEmail"></div>
				</font></td>
			</tr>
			<tr>
				<th>Mobile:</th>
				<td><form:input path="userPhonenumber" /></td>
			</tr>
			<tr>
				<th>Date-Of-Birth:</th>
				<td><form:input path="dateOfBirth" /></td>
			</tr>
			<tr>
				<th>Gender:</th>
				<td>Male:<form:radiobutton path="gender" value="M" id="gender" />
					Female:<form:radiobutton path="gender" value="F" id="gender" />
				</td>
			</tr>
			<tr>
				<th>Country:</th>
				<td><form:select path="countryId">
						<form:option value="">-Select-</form:option>
						<form:options items="${countriesMap}" />
					</form:select></td>
			</tr>
			<tr>
				<th>State:</th>
				<td><form:select path="stateId">
						<form:option value="">-Select-</form:option>
					</form:select></td>
			</tr>
			<tr>
				<th>City:</th>
				<td><form:select path="cityId">
						<form:option value="">-Select-</form:option>
					</form:select></td>
			</tr>

			<tr>
				<th></th>
				<td><input type="submit" value="Reset">&nbsp;<input
					type="submit" value="Register"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>