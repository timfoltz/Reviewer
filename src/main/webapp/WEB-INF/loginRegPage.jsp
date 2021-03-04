<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    
</head>
<body>
	<div style = "display:flex; justify-content: space-evenly; background-color: gray;">
		<div class="mb-3">
		    <h1>Login</h1>
		    <p style="color:red;"><c:out value="${error}" /></p>
		    <form method="post" action="/login">
		        <p class="mb-3">
		            <label for="email" class="form-label">Email</label>
		            <input style="background-color: rgb(165 157 145);" class="form-control" required type="text" id="email" name="email"/>
		        </p>
		        <p class="mb-3">
		            <label for="password" class="form-label">Password</label>
		            <input style="background-color: rgb(165 157 145);"class="form-control" required type="password" id="password" name="password"/>
		        </p>
		        <input class="btn btn-primary" type="submit" value="Login!"/>
		    </form>
		    </div>
		    <div class="mb-3"> 
		    <h1>Register!</h1>
		    
		    <p style="color:red;"><form:errors path="user.*"/></p>
		    
		    <form:form method="POST" action="/registration" modelAttribute="user">
		        <p class="mb-3">
		            <form:label class="form-label" path="firstName">First Name:</form:label>
		            <form:input style="background-color: rgb(165 157 145);" class="form-control" required="true" type="text" path="firstName"/>
		        </p>
		        <p class="mb-3">
		            <form:label class="form-label" path="lastName">Last Name:</form:label>
		            <form:input style="background-color: rgb(165 157 145);" class="form-control" required="true" type="text" path="lastName"/>
		        </p>
		        <p class="mb-3">
		            <form:label class="form-label" path="email">Email:</form:label>
		            <form:input style="background-color: rgb(165 157 145);" class="form-control" required="true" type="email" path="email"/>
		        </p>
		            <p id="emailHelp" class="form-text" style="color: black">We'll never share your email with anyone else.</p>
		        <p class="mb-3">
		            <form:label class="form-label" path="location">Location:</form:label>
		            <form:input style="background-color: rgb(165 157 145);" class="form-control" required="true" type="text" path="location"/>
		        </p>
		        <p class="mb-3">
		            <form:label class="form-label" path="password">Password:</form:label>
		            <form:password style="background-color: rgb(165 157 145);" class="form-control" required="true" path="password"/>
		        </p>
		        <p class="mb-3">
		            <form:label class="form-label" path="passwordConfirmation">Password Confirmation:</form:label>
		            <form:password style="background-color: rgb(165 157 145);" class="form-control" required="true" path="passwordConfirmation"/>
		        </p>
		        <input class="btn btn-primary" type="submit" value="Register!"/>
		    </form:form>
		    
		    <c:forEach items="${allUsers}" var="email">
		    <p><c:out value="${email}"/></p>
		    </c:forEach>
	    </div>
    </div>   
</body>
</html>