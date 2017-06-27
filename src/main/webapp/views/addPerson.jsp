<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Administrator Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/assets/style.css" />
</head>
<body>
<%--<a href="<c:url value='/logout'/>" class="pull-right">Logout </a>--%>
<div class="clearfix"></div>
<div class="container-fluid">
    <!-- Second navbar for profile settings -->
   <%@ include file="header.jsp" %>

    <div class="container">
        <div class = "col-md-8 col-md-offset-2">
           
            <br>
            <form action="/addPerson/${person.id}" method="post">
             <label>Name: </label><input class="form-control" type="text" name="name" value="${person.name}" />
                <label>User Name: </label><input class="form-control" type="text" name="username" value="${person.user.username}" />
                <label>Password: </label><input type="password" class="form-control" type="text" name="password" value="${person.user.password}" />
                <label>Retype Password: </label><input type="password" class="form-control" type="text" name="password2" value="${person.user.password}" />
                
                <label>Email: </label><input class="form-control" type="text" name="email" value="${person.email}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <button class="btn btn-primary" type="submit">Go</button> <button class="btn btn-danger" type="button">Cancel</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>