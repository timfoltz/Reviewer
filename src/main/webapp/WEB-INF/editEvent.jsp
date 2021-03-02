<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

<meta charset="ISO-8859-1">
<title>Edit ${event.name}</title>
</head>
<body>
		<br>
		<br>
			<h3>Edit the <span style="font-size:40px;"><c:out  value="${event.name }"/></span> event</h3>

		<br>
		<br>
		
		 <form:form method="post" action="/events/edit/${event.id}" modelAttribute="event">
		 <input type="hidden" name="_method" value="put">
	 		<form:input type="hidden" value="${user.id}" path="creator"/>
		        <p>
		            <form:label path="name">Name:</form:label>
		            <form:input required="true" type="text" path="name"/>
		        </p>
		        <p>
		            <form:label path="eventDate">Date:</form:label>
		            <form:input required="true" type="date" path="eventDate"/>
		        </p>
		        <p>
		            <form:label path="location">Location:</form:label>
		            <form:input required="true" type="text" path="location"/>
		        </p>
		        <p>
		            <form:label path="usState">State:</form:label>
		            <form:input required="true" max="2" path="usState"/>
		        </p>
		        <input type="submit" value="Edit"/>
		    </form:form>
</body>
</html>