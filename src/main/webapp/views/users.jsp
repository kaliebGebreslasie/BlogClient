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
</head>
<body>
<%--<a href="<c:url value='/logout'/>" class="pull-right">Logout </a>--%>
<div class="clearfix"></div>
<div class="container-fluid">
    <!-- Second navbar for profile settings -->
 <%@ include file="header.jsp" %>

    <div class="container">
        <div class="row col-md-12 custyle">
        
    <table class="table table-striped custab">
    <thead>
  
        <tr>
            <th >Name</th>
            <th>Username</th>
            <th>Email</th>
             <th>Posts</th>
            
             <th>Roles</th>
          	<th></th>
            <th class="text-center">Action</th>
        </tr>
         </thead>
        
      <c:forEach items="${persons}" var="person">
      <tr><td  >${person.name}</td><td >${person.user.username}</td>  
      <td >${person.email}</td>
      <td>
      <c:forEach items="${person.posts}" var="post">
      <li > <a href="post/${post.id}"> ${post.title }</a></li>
      </c:forEach>
      </td>
        <td > 
      
       <li style="list-style:none;"> Admin</li> 
       </td>
       <form action="assignRole/${person.id}" method="post" >
       <td>
     	<input type="hidden" name="userRoles" value=""/>
      	<input type="checkbox" name="userRoles" value="ROLE_ADMIN" <c:if test = "${(person.user.userRoles[0].role=='ROLE_ADMIN') ||(person.user.userRoles[1].role=='ROLE_ADMIN')}" > checked="true"  </c:if>  />
     	</td>
     	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
       <td    > <button  class="btn btn-success btn-xs"><span class="glyphicon glyphicon-ok"></span>Assign Role</button>
 <a href="delPerson/${person.id}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td></tr>
 </form>
      </c:forEach>
      </table>
        </div>
    </div>

</div>
</body>
</html>