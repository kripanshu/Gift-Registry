<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update user</title>
</head>
<body>
	<form:form method = "POST" action = "formUpdate" modelAttribute = "profile" >

First Name <form:input path = "firstName"/> <br/>
Last Name<form:input path = "lastName"/> <br/>
Password  <form:input path = "password"/> <br/>
Email ID <form:input path = "email"/> <br/>
Security Question<form:input path = "securityQuestion"/> <br/>
Security Answer  <form:input path = "securityAnswer"/> <br/>

<input type = "submit" value = "Update" />

</form:form>
</body>
</html>