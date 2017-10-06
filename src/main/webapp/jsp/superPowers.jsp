<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Super Powers</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/tabPages.css">
    </head>
    <body>
        <div class="container" id="main">
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
                        <li role="presentation" class="active" >
                            <a href="${pageContext.request.contextPath}/superPowers">
                                Super Powers
                            </a>
                        </li>
                        
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/displayUserList">
                                    User Administration
                                </a>
                            </li>
                        </sec:authorize>
                        
                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.name != null}">
                                <p style="color: whitesmoke; float: right">Hello : ${pageContext.request.userPrincipal.name}
                                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                                </p>

                            </c:when>
                            <c:otherwise>
                                <p style="color: whitesmoke; float: right">
                                    <a href="<c:url value="/login" />" > Login</a>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </ul> <!-- end navbar ul -->

                </div> <!-- end div container-fluid-->
            </div>  <!-- end navbar -->
            <!-- Main Page Content Start -->
            <br/>
            <!-- 
            Add a row to our container - this will hold the summary table and the
            new contact form.
            -->


            <div class="row">
                <!--
                Add a col to hold the summary table - have it take up half the row
                -->
                <div class="col-md-7">
                    <h2>Super Powers</h2>
                    <table id="organizationsTable" class="table table-hover">
                        <tr>
                            <th width="25%">Super Power Name</th>
                            <th width="50%">Description</th>
                            <th width="15%"></th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="power" items="${superPowerList}">


                            <tr>

                                <td>
                                    <c:out value="${power.name}"/>                                            
                                </td>
                                <td>
                                    <c:out value="${power.description}"/>
                                </td>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                <td>
                                    <a href="displayPowerProfile?powerTypeId=${power.powerTypeId}" 
                                       role="button" class="btn btn-warning">
                                        <span class="glyphicon glyphicon-expand"></span> Edit Power</a>
                                </td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td>
                                    <a href="deleteSuperPower?powerTypeId=${power.powerTypeId}" 
                                       role="button" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-alert"></span> Delete</a>
                                </td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                    </table>
                </div> <!-- end col div -->

                <!--
                Add col to hold the new contact form - have it take up the other 
                half of the row
                -->
                <div class="col-md-5">
                    <sec:authorize access="hasRole('ROLE_USER')">
                    <h2>Add New Super Power</h2>
                    <form class="form-horizontal"
                          role="form" method="POST"
                          action="createSuperPower">
                        <div class="form-group">
                            <label for="add-name" class="col-md-3 control-label">
                                Name:
                            </label>
                            <div class="col-md-9">

                                <input id="add-name" type="text" class="form-control" name="powerName"
                                       placeholder="Super Power Name" required/>

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-3 control-label">
                                Description:
                            </label>
                            <div class="col-md-9">
                                <input id="add-description" type="text" class="form-control" name="powerDescription"
                                       placeholder="Description of the Super Power."/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input type="submit" class="btn btn-default" 
                                       value="Create Super Power"/>
                            </div>
                        </div>
                    </form>
                    </sec:authorize>
                </div> <!-- End col div -->

            </div> <!-- End row div -->



            <!-- Main Page Content Stop -->
        </div>  <!-- end class container -->

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>