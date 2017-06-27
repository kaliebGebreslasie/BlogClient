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
            <h4><strong>Save Post:</strong></h4>
            <br>
            <form action="/savePost/${post.id}" method="post">
             <label>Title: </label><input class="form-control" type="text" name="title" value="${post.title}" />
             
                <label>Content: </label><textarea class="form-control"  name="content" rows="5" cols="3"> ${post.content} </textarea>
                <label>Summary: </label><input class="form-control" type="text" name="summary" value="${post.summary}" />
              
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <button class="btn btn-primary" type="submit">Go</button> <button class="btn btn-danger" type="button">Cancel</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>