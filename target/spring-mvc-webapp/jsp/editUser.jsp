<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. : Users</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O. : Users</h1>
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
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li role="presentation">
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
            <h1>Edit User Clearance:</h1>
            <form class="form-horizontal" role="form" modelAttribute="userData" 
                     method="POST" action="editUserAuthority">
                <div class="form-group">
                    <label for="edit-userName" class="col-md-4 control-label">
                        Username:
                    </label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="edit-userName"
                                  path="username" value="${userData.username}" readonly="true"/>
                        </div>
                    </div> <!-- end form-group -->

                    <div class="form-group">
                        <label for="edit-userId" class="col-md-4 control-label">
                            User ID#:
                        </label>
                        <div class="col-md-8">
                        <input type="text" class="form-control" id="edit-userId"
                               name="userId" path="id" value="${userData.id}" readonly="true"/>
                        </div>
                    </div> <!-- end form-group -->

                    <div class="form-group hidden">
                        <label for="edit-password" class="col-md-4 control-label">
                            Password:
                        </label>
                        <div class="col-md-8">
                        <input type="text" class="form-control" id="edit-password"
                                  path="password" readonly="true"/>
                        </div>
                    </div> <!-- end form-group -->

                    <div class="form-group">
                        <label for="edit-authorities" class="col-md-4 control-label">
                            Add Administrative Powers?:
                        </label>
                        <div class="col-md-8">
                            <input type="checkbox" class="form-control" id="edit-authorities"
                                   path="authorities" name="isAdmin" value="yes"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" 
                                   value="Update User Authorities"/>
                        </div>
                    </div>

            </form>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
