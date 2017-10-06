<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Sightings</title>
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
                        <li role="presentation" class="active">
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
            </div> <!-- end class navbar -->

            <!-- Main Page Content Start -->
            <br/>
            <ul class="list-group" id="errorMessages"></ul>
            <div class="row">
                <form class="form-inline col-md-6" role="form" method="GET" action="searchSightings">

                    <div class="form-group ">
                        <div class="form-group">

                            <select class="btn btn-primary" id="requestedSearch" name="requestedSearch">
                                <option value="showSightings">Show All</option>
                                <option value="showSightingsLimit10" selected="selected">Latest 10 Sightings</option>
                                <option value="sightingBySuperId">Sighting By Super Id</option>
                                <option value="sightingByDate">Sighting By Date</option>
                            </select>

                            <input type="search" size="23" class="form-control" id="searchCriteria" name="searchCriteria" placeholder="Enter Search Criteria here ..."/>
                            <button class="btn btn-primary" type="submit">Search ! <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>

                        </div>

                    </div>

                </form>
                <div class="col-md-6"></div>
            </div>  <!-- end search Row -->

            <!-- 
            Add a row to our container - this will hold the summary table and the
            new contact form.
            -->


            <div class="row">
                <!--
                Add a col to hold the summary table - have it take up half the row
                -->
                <div class="col-md-6">
                    <h2>Sightings</h2>
                    <table id="sightingsTable" class="table table-hover">
                        <tr>
                            <th width="20%">Sighting Name</th>
                            <th width="50%">Description</th>
                            <th width="10%">Date</th>
                            <th width="8%"></th>
                            <th width="6%"></th>
                            <th width="6%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">


                            <tr>

                                <td id="currentSiteName">
                                    <c:out  value="${currentSighting.name}" />
                                </td>
                                <td id="currentSiteDesc">
                                    <c:out value="${currentSighting.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.sightingDate}"/>
                                </td>
                                <td>
                                    <a role="button" id="profileButton" class="btn btn-primary" data-toggle="modal" data-target="#myModal" 
                                       onclick="viewSightingProfile(${currentSighting.sightingId})" >
                                        <span class="glyphicon glyphicon-expand"></span> View Sighting
                                        <input style="text" class="hidden" id="currentSightingId" value="${currentSighting.sightingId}"/>
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
                                        <a role="button" id="editPageButton" class="btn btn-warning" href="displaySiteProfile?siteId=${currentSighting.sightingId}">
                                            <span class="glyphicon glyphicon-expand"></span> Edit</a>
                                    </td>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <td>
                                        <a href="deleteSighting?siteId=${currentSighting.sightingId}" 
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
                        <h2>Add New Sighting</h2>
                        <form class="form-horizontal"
                              role="form" method="POST"
                              action="createSighting">
                            <div class="form-group">
                                <label for="add-name" class="col-md-2 control-label">
                                    Name:
                                </label>
                                <div class="col-md-10">

                                    <input type="text" class="form-control" name="siteName"
                                           placeholder="Name or Identification" 
                                           maxlength="30"  required="required"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-2 control-label">
                                    Description:
                                </label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="description"
                                           placeholder="A Description of sighting." 
                                           maxlength="100" required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-street" class="col-md-2 control-label">
                                    Street:
                                </label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="street"
                                           placeholder="Street Address." 
                                           maxlength="30" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-2 control-label">
                                    City:
                                </label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="city"
                                           placeholder="city of the sighting" 
                                           maxlength="20" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-2 control-label">
                                    State:
                                </label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="state"
                                           placeholder="State in which the super human(s) was sited." 
                                           maxlength="20" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-zip" class="col-md-2 control-label">
                                    Zip:
                                </label>
                                <div class="col-md-10">
                                    <input type="number" class="form-control" name="zip"
                                           placeholder="zip" 
                                           maxlength="10" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-latitude" class="col-md-2 control-label">
                                    Latitude:
                                </label>
                                <div class="col-md-10">
                                    <input type="number" step="0.0001" class="form-control" name="latitude"
                                           placeholder="latitude" maxlength="9" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-longitude" class="col-md-2 control-label">
                                    Longitude:
                                </label>
                                <div class="col-md-10">
                                    <input type="number" step="0.0001" class="form-control" name="longitude"
                                           placeholder="longitude" maxlength="9" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-date" class="col-md-2 control-label">
                                    Date:
                                </label>
                                <div class="col-md-10">
                                    <input type="text" id='add-date' class="form-control" name="sightingDate" 
                                           required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
                                           placeholder="'YYYY-MM-DD'  Date of the sighting." required/>
                                    <!-- required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" -->

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <input type="submit" class="btn btn-default" 
                                           value="Create Sighting"/>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div> <!-- End col div -->

            </div> <!-- End row div -->

            <!-- Main Page Content Stop -->

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">
                                Sighting Profile: 
                            </h4>
                        </div>

                        <div class="modal-body">
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Sighting Name: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9">
                                    <h5 id="profile-site-name"></h5>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Sighting ID: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5 id="profile-site-id"></h5>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Sighting Date: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5 id="profile-site-date"></h5>
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
                                        <span id="siteStreet"></span>
                                        <span id="siteCity"></span>, <span id="siteState"></span> <span id="siteZip"></span>
                                    </h5>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <h5>
                                        <b>Latitude / Longitude: </b>
                                    </h5>
                                </div>
                                <div class="col-md-9"> 
                                    <h5>
                                        <span id="siteLatitude"></span>, <span id="siteLongitude"></span>
                                    </h5>
                                </div>
                            </div>
                            <br>
                            <div>
                                <table>

                                    <tr>
                                    <b>Super Humans Present:</b>
                                    </tr>

                                    <tbody id="currentSuperRows"></tbody>
                                </table>  
                            </div> 
                        </div> <!-- modal-body end -->

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div> <!-- end Model Content -->
                </div> <!-- end model dialogue -->

            </div> <!-- end id="myModal class="modal fade"-->

        </div>  <!-- end class container -->

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/sightingsJS.js" ></script>
    </body>
</html>
