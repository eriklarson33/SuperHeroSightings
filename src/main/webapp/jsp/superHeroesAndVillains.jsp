<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Super Heroes and Villains</title>
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
                        <li role="presentation"  class="active"  >
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
                </div> <!-- end container-fluid -->
            </div> <!-- end class navbar -->
        
            <!-- Main Page Content Start -->
            <br/>
            <ul class="list-group" id="errorMessages"></ul>
            <div class="row">
                <form class="form-inline col-md-6" role="form" method="GET" action="searchSupers">

                    <div class="form-group ">
                        <div class="form-group">

                            <select class="btn btn-primary" id="requestedSearch" name="requestedSearch">
                                <option value="showAllSupers" selected="selected">Show All</option>
                                <option value="findById">Find Super Human by Id</option>
                                <option value="findByOrg">Find by Organization Id</option>
                                <option value="findBySighting">Find by Sighting Id</option>
                            </select>

                            <input type="search" size="23" class="form-control" id="searchCriteria" name="searchCriteria" placeholder="Enter Search Criteria here ...">
                            <button class="btn btn-primary" type="submit">Search ! <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>

                        </div>

                    </div>

                </form>
                
            </div>  <!-- end search Row -->


            <!-- 
            Add a row to our container - this will hold the summary table and the
            new contact form.
            -->


            <div class="row">
                <!--
                Add a col to hold the summary table - have it take up half the row
                -->
                <div class="row">
                    <!--
                    Add a col to hold the summary table - have it take up half the row
                    -->
                    <div class="col-md-6">
                        <h2>Super Heroes and Villains</h2>
                        <table id="superHumanTable" class="table table-hover">
                            <tr>
                                <th width="20%">Super Human Name</th>
                                <th width="50%">Description</th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                            </tr>
                            <c:forEach var="currentSuperHuman" items="${superHumanList}">


                                <tr>

                                    <td id="currentSuperName">
                                            <c:out  value="${currentSuperHuman.superHumanName}" />
                                    </td>
                                    <td id="currentSuperDesc">
                                        <c:out value="${currentSuperHuman.description}"/>
                                    </td>
                                    <td>
                                        <a role="button" id="profileButton" class="btn btn-primary" data-toggle="modal" data-target="#myModal" 
                                           onclick="viewProfile(${currentSuperHuman.superHumanId})">
                                            <span class="glyphicon glyphicon-expand"></span> View Profile!
                                            <input style="text" class="hidden" id="currentSuperId" value="${currentSuperHuman.superHumanId}"/>
                                        </a>
                                        <!--
                                        <div class="hidden" type="text" name="superId">
                                            <a href="="></a>
                                        </div>
                                        <input type="submit" value="Find Factors!!!"/>
                                        
                                        <input type="text" class="form-control" name="displayProfile"
                                           placeholder="Name or Identity"/>
                                        <a href="#myModal" role="button" class="btn btn-warning" data-toggle="modal">
                                            <span class="glyphicon glyphicon-hand-up"></span>View Profile!</a>
                                        -->
                                    </td>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <td>
                                            <a role="button" id="editPageButton" class="btn btn-warning" href="displayProfile?superId=${currentSuperHuman.superHumanId}">
                                                <span class="glyphicon glyphicon-expand"></span> Edit</a>
                                        </td>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <td>
                                            <a href="deleteSuperHuman?superId=${currentSuperHuman.superHumanId}" 
                                               role="button" id="deleteButton" class="btn btn-danger">
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
                    <div class="col-md-6">
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <h2>Add New Super Human</h2>
                            <form class="form-horizontal"
                                  role="form" method="POST"
                                  action="createSuperHuman">
                                <div class="form-group">
                                    <label for="add-name" class="col-md-4 control-label">
                                        Name:
                                    </label>
                                    <div class="col-md-8">

                                        <input type="text" class="form-control" name="superHumanName"
                                               placeholder="Name or Identity" maxlength="20" required/>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-description" class="col-md-4 control-label">
                                        Description:
                                    </label>
                                    <div class="col-md-8">
                                        <textarea rows='2' class="form-control" name="description"
                                                  placeholder="A Description of who the super is or what they stand for." 
                                                  maxlength="100" required></textarea>
                                    </div>
                                </div>



                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" class="btn btn-default" 
                                               value="Create Super Human"/>
                                    </div>
                                </div>
                            </form>
                        </sec:authorize>



                    </div> <!-- End col div -->



                </div> <!-- End row div -->

                <!-- Modal -->


                <!-- Main Page Content Stop -->

                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Super Human Profile: 

                            </h4>

                        </div>
                        <form class="form-horizontal" role="form"
                              action="editHERO" method="POST">
                            <div class="modal-body">


                                <div class="form-group">
                                    <label for="profile-super-name" class="col-md-3 control-label">
                                        Super's Name:
                                    </label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" id="profile-super-name" placeholder="Name or Identity"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="profile-super-id" class="col-md-3 control-label">
                                        Registration ID: 
                                    </label>
                                    <div class="col-md-9"> 
                                        <input type="text" class="form-control" id="profile-super-id" placeholder="Super's Registration ID." readonly/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="profile-description" class="col-md-3 control-label">
                                        Description:
                                    </label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" id="profile-description" placeholder="Description of the Super or what they stand for."/>
                                    </div>
                                </div>

                                <div class="checkbox form-group">
                                    <label for="listOfSuperPowers" class="col-md-3 control-label">
                                        Super Powers:
                                    </label>
                                    <div class="col-md-9" id="listOfSuperPowers">
                                    </div>
                                </div>

                                <div class="checkbox form-group">
                                    <label for="profile-organizations" class="col-md-3 control-label">
                                        Organizations:
                                    </label>
                                    <div class="col-md-9" id="profileOrganizations">
                                    </div>
                                </div>

                                <div class="checkbox form-group">
                                    <label for="profile-sightings" class="col-md-3 control-label">
                                        Sightings:
                                    </label>
                                    <div class="col-md-9" id="profileSightings">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!--                                <a role="button" id="edit-button" class="btn btn-primary">
                                    Update HERO
                                </a>-->
                            </div>

                        </form>

                    </div>
                </div> <!-- end model dialogue -->

                <!--            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" 
                                           value="Update Contact"/>
                                </div>
                            </div>-->

            </div> <!-- end id="myModal class="modal fade"-->


            </div>  <!-- end class container -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
            <!-- Latest compiled and minified JavaScript -->
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
            <script src="js/superHumanPageJS.js" ></script>
    </body>
</html>
