<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove Items from Registry</title>
</head>
<body>


<form:form method = "POST" action = "remove" modelAttribute = "removeItem" >

Registry ID <form:input path = "registrtyId"/> <br/>
Item ID <form:input path = "itemId"/> <br/>
Quantity <form:input path = "quantity"/> <br/>

<input type = "submit" value = "Remove" />

</form:form>

</body>
</html>