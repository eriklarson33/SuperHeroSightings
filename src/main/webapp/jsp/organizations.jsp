<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Organizations</title>
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
                        <li role="presentation" class="active" >
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

                </div> <!-- end div container -->
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

                    <h2>Organizations</h2>
                    <table id="organizationsTable" class="table table-hover">
                        <tr>
                            <th width="25%">Organization Name</th>
                            <th width="50%">Description</th>
                            <th width="15%"></th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="org" items="${organizationList}">
                            <tr>
                                <td>
                                    <c:out value="${org.organizationName}"/>                                            
                                </td>
                                <td>
                                    <c:out value="${org.description}"/>
                                </td>
                                <td>
                                    <a role="button" id="profileButton" class="btn btn-primary" data-toggle="modal" data-target="#myModal" 
                                       onclick="viewOrg(${org.organizationId})">
                                        <span class="glyphicon glyphicon-expand"></span> View Organization
                                        <input style="text" class="hidden" id="currentOrgId" value="${org.organizationId}"/>
                                    </a>
                                </td>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <td>
                                        <a href="displayOrgProfile?orgId=${org.organizationId}" 
                                           role="button" class="btn btn-warning">
                                            <span class="glyphicon glyphicon-expand"></span> Edit</a>
                                    </td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <td>
                                        <a href="deleteOrganization?orgId=${org.organizationId}" 
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
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <h2>Add New Organization</h2>
                        <form class="form-horizontal"
                              role="form" method="POST"
                              action="createOrganization">
                            <div class="form-group">
                                <label for="add-name" class="col-md-3 control-label">
                                    Name:
                                </label>
                                <div class="col-md-9">

                                    <input type="text" class="form-control" name="orgName" id="add-name"
                                           placeholder="Name or Identity" maxlength="40" required/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-3 control-label">
                                    Description:
                                </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="description" id="add-description" 
                                           maxlength="175" placeholder="Description of the org or what they stand for."/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-street" class="col-md-3 control-label">
                                    Street:
                                </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="street" id="add-street"
                                           maxlength="30" placeholder="Street Address." required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-3 control-label">
                                    City:
                                </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="city" id="add-city"
                                           maxlength="20" placeholder="city of Organization" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-3 control-label">
                                    State:
                                </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="state" id="add-state"
                                           maxlength="20" placeholder="State of Organization Headquarters." required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-zip" class="col-md-3 control-label">
                                    Zip
                                </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="zip" id="add-zip"
                                           maxlength="10" placeholder="zip" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-phone" class="col-md-3 control-label">
                                    Phone
                                </label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="phone" id="add-phone"
                                           maxlength="20" placeholder="Phone number." required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <input type="submit" class="btn btn-default" 
                                           value="Create Organization"/>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div> <!-- End col div -->

            </div> <!-- End row div -->



            <!-- Main Page Content Stop -->

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Organization Profile: 

                            </h4>

                        </div>
                        <div class="modal-body">


                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Organization Name: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9">
                                    <h5 id="profile-org-name"></h5>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Organization ID: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5 id="profile-org-id"></h5>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Description: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5 id="profile-description"></h5>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Address: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5>
                                        <span id="orgStreet"></span>
                                        <span id="orgCity"></span>, <span id="orgState"></span> <span id="orgZip"></span>
                                    </h5>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Phone #: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5 id="orgPhone"></h5>
                                </div>
                            </div>
                            <br>
                            <div>
                                <table>
                                    <tr>
                                        <th>
                                            <b>Members: </b>
                                        </th>
                                    </tr>
                                    <tbody id="currentSuperRows"></tbody>
                                </table>  
                            </div>
                        </div> <!-- modal-body end -->

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>

                    </div> <!-- end modal content -->
                </div> <!-- end model dialogue -->

            </div> <!-- end id="myModal class="modal fade"-->

        </div>  <!-- end class container main -->

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/organizationsJS.js" ></script>
    </body>
</html>