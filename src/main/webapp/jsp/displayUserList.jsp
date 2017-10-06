<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <title>H.E.R.O. : Sidekicks</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/tabPages.css">
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O. : Sidekicks</h1>
            <hr/>
            <div class="navbar navbar-inverse">
                <div class="container-fluid">

                    <ul class="nav nav-tabs">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/">
                                Home
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/superHeroesAndVillains">
                                Super Heroes and Villains
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/organizations">
                                Organizations
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/sightings">
                                Sightings
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/superPowers">
                                Super Powers
                            </a>
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li role="presentation" class="active">
                                <a href="${pageContext.request.contextPath}/displayUserList">
                                    User Admin
                                </a>
                            </li>                        
                        </sec:authorize>
                    </ul> <!-- end navbar ul -->
                </div>
            </div> <!-- end div container -->

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <h1>Sidekicks</h1>
            <a href="displayUserForm">Add a new Sidekick</a><br/>
            <hr/>
            <c:forEach var="user" items="${users}">
                <div>
                    <c:out value="${user.username}"/> |
                    <a href="displayEditUser?userId=${user.id}">Edit</a> | 
                    <a href="deleteUser?username=${user.username}">Delete</a><br/>
                </div>
                <hr/>
            </c:forEach>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
