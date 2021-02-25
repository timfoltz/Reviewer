<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<a href="/logout">Logout</a>
	<h1>Welcome, <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /></h1>


<!-- 	<h3>Created Events</h3> -->
<!-- 	<ul> -->
<%-- 		<c:forEach items="${user.createdEvents}" var="ev"> --%>
<%-- 			<li><c:out value="${ev.name }"/> |  --%>
<%-- 				<c:out value="${ev.location}"/> |  --%>
<%-- 				<c:out value="${ev.eventDate }"/> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	<h3>Going to Events</h3> -->
<!-- 	<ul> -->
<%-- 		<c:forEach items="${user.goingToEvent}" var="event"> --%>
<%-- 			<li><c:out value="${event.name }"/> |  --%>
<%-- 				<c:out value="${event.location}"/> |  --%>
<%-- 				<c:out value="${event.eventDate }"/> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
	<h3>All Events</h3>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Location</th>
				<th>Date</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allEvents}" var="event">
				<tr>
					<td><c:out value="${event.name }"/></td>
					<td><c:out value="${event.location}"/></td>
					<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${event.eventDate }"/></td>
					<td>
						<c:if test="${event.goers.contains(user)}">
							 Joined!! <a href="/remove/${event.id}">Cancel</a>  
						 </c:if>
						 <c:if test="${event.goers.contains(user) == false}">
						 
							 <a href="/joinEvent/<c:out value="${event.id }"/>">Join</a> | 
						 </c:if>
						 <c:if test="${user.id == event.creator.id}">
							  <a href="/editEvent/${event.id}">Edit</a> | 
						 
							 <a href="/deleteEvent/${event.id }">Delete</a>
						 </c:if>
					 </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	 <form:form method="POST" action="/events" modelAttribute="event">
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
		        <input type="submit" value="Register!"/>
		    </form:form>
		    
		    

</body>
</html>