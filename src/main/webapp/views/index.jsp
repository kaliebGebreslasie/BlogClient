<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>My Footy Blog</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css" />
</head>
<body>
<%--<a href="<c:url value='/logout'/>" class="pull-right">Logout </a>--%>
<div class="clearfix"></div>
<div class="container-fluid">
    <!-- Second navbar for profile settings -->
   <%@ include file="header.jsp" %>
	<div class="container-full">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center v-center">
                <h1 class="title">My Footy Blog</h1>
                <h3 class="lead">My two cents on all things football</h3>
                <br><br><br>
            </div>
        </div> <!-- /row -->
    </div> <!-- /container full -->
    <div class="container">
        <c:forEach var="post" items="${posts}">
            <article class="col-md-8 col-md-offset-2 contents" >
                <h2>${post.title}</h2>
                <span>${post.datecreated}</span>
                   <span>${post.dateupdate}</span>
                <h4>${post.summary}</h4><br><br>
                <a href="post/${post.id}">Read More</a>
              
            </article>
          
            <div class="col-xs-12" style="height:50px;"><hr></div>
        </c:forEach>
        
    </div>

</div>
</body>
</html>