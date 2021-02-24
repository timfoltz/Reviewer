<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<a href="/logout">Logout</a>
	<h1>Welcome, <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /></h1>

	<ul>
		<c:forEach items="${allEvents}">
			<li><c:out value="${ev.name }"/> | 
				<c:out value="${ev.location}"/> | 
				<c:out value="${ev.date }"/>
		</c:forEach>
	</ul>


</body>
</html>