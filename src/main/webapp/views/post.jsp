<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>My Footy Blog</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/style.css" />
</head>
<body>
<%--<a href="<c:url value='/logout'/>" class="pull-right">Logout </a>--%>
<div class="clearfix"></div>
<div class="container-fluid">
    <!-- Second navbar for profile settings -->
    <%@ include file="header.jsp"%>

    <div class="container">
        <article class="col-md-8 col-md-offset-2 contents">
            <h2>${post.title}</h2>
            <div class="col-md-12">
            <span><em> by ${postPerson.name}</em></span><br><br>
            <span><strong>Created:	</strong><fmt:formatDate type="date" value="${post.datecreated}" /></span>
            <span><c:if test="${post.dateupdated!=null}"><strong>Updated: </strong><fmt:formatDate type="date" value="${post.dateupdated}" /></c:if></span>
            <br><br>
            </div>
            <%--<h5>${blogpost.summary}</h5><br>--%>
            <p class="col-md-2">Likes: <em>${post.countlikes}</em></p>
            <div class="col-md-2 col-md-offset-8">
                <c:if test="${!post.liked}">
                    <form class="likes" action="/addLike/${post.id}" method="post">
                        <button class="btn btn-info" type="submit">
                            <span class="glyphicon glyphicon-thumbs-up"></span> Like
                        </button>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form>
                </c:if>
                <c:if test="${post.liked}">
                    <form class="likes" action="/unLike/${post.id}" method="get">
                        <button class="btn btn-warning" type="submit">
                            <span class="glyphicon glyphicon-thumbs-down"></span> Unlike
                        </button>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form>
                </c:if>
            </div>
            <p>${post.content}</p>

            <br>
            <br>
            <a href="/"><span class="glyphicon glyphicon-arrow-left"></span>
                Go Back</a>
            <sec:authorize access="hasRole('ROLE_USER')">

                <c:if test="${currPerson.id==postPerson.id }">
                    <a href="/editPost/${post.id}" class='btn btn-info btn-xs'
                       href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a>
                    <a href="/delPost/${post.id}" class='btn btn-info btn-xs' href="#"><span
                            class="glyphicon glyphicon-edit"></span> Delete</a>
                </c:if>
            </sec:authorize>

        </article>

        <article class="col-md-6 col-md-offset-3  contents">
            <form action="/addComment/${post.id}" method="post">
                <div class="well">
                    <label>Add Comment: </label>
                    <textarea class="form-control" name="content" rows="5" cols="3"></textarea>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                    <button class="btn btn-primary pull-right" type="submit">Go</button>
                    <button class="btn btn-danger pull-right" type="button" id="clear">Cancel</button>
                    <div class="clearfix" />
                </div>
            </form>
        </article>
        <section class="well col-md-6 col-md-offset-3">
            <c:forEach var="comment" items="${post.comments}">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h5>
                            <strong>${comment.person.name}</strong>
                        </h5>

                        <span> <fmt:formatDate
                                type="date" value="${comment.datecreated}" /></span>
                        <p>${comment.content}</p>
                    </div>
                </div>
            </c:forEach>
        </section>

    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/order.js"></script>
</html>