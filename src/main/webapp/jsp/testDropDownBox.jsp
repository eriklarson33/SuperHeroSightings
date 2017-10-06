<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O.: Login</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>H.E.R.O.</h1>
            <hr/>
            <h2>TESTING Page</h2>


            <!-- Main Page Content Start -->
            <br/>
            <div class="row">
                <form class="form-inline col-md-6" role="form" method="GET"
                      action="searchSupers">
                    <div class="form-group">
                        <select class="btn btn-primary" name="requestedSearch">
                            <option value="showAllSupers" selected="selected">Show All</option>
                            <option value="findById">Find Super Human by Id</option>
                            <option value="findByOrg">Find Super by Organization</option>
                            <option value="findBySighting">Find Super by Sighting</option>
                        </select>

                        <input type="text" size="23" class="form-control" id="searchCriteria" name="searchCriteria" placeholder="Enter Search Criteria here ...">

                        <button class="btn btn-primary" type="submit">Search ! <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>

                    </div>

                </form>
            </div>  <!-- end search Row -->


            <!--            <div class="row">
                            <form class="form-inline col-md-6" onsubmit="return false" role="form">
            
                                <div class="form-group ">
                                    <div class="form-group">
            
                                        <select class="btn btn-primary" id="requestedSearch" name="Search">
                                            <option value="showAllSupers" selected="selected">Show All</option>
                                            <option value="nameSearch">Find by Exact Name</option>
                                            <option value="findById">Find Super Human by Id</option>
                                            <option value="findByOrg">Find by Organization Id</option>
                                            <option value="findBySighting">Find by Sighting Id</option>
                                        </select>
            
                                        <input type="search" size="23" class="form-control" id="searchCriteria" name="searchCriteria" placeholder="Enter Search Criteria here ...">
                                        <button class="btn btn-primary" type="submit" onclick="getSearchRequest()">Search ! <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
            
                                    </div>
            
                                </div>
            
                            </form>
                            <div class="col-md-6"></div>-->

            <!-- 
            Add a row to our container - this will hold the summary table and the
            new contact form.
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
                                    <a href="displayContactDetails?contactId=${currentSuperHuman.superHumanId}">
                                        <c:out  value="${currentSuperHuman.superHumanName}" />                                            
                                    </a>
                                    <div class="hidden"></div>
                                </td>
                                <td id="currentSuperDesc">
                                    <c:out value="${currentSuperHuman.description}"/>
                                </td>
                                <td>
                                    <a role="button" id="profileButton" class="btn btn-warning" data-toggle="modal" data-target="#myModal">
                                        <span class="glyphicon glyphicon-expand"></span> View Profile!
                                        <div class="hidden" id="currentSuperId" value="${currentSuperHuman.superHumanId}"></div>
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
                                <td>
                                    <a role="button" id="editPageButton" class="btn btn-warning" href="displayProfile?superId=${currentSuperHuman.superHumanId}">
                                        <span class="glyphicon glyphicon-expand"></span> Edit</a>
                                </td>
                                <td>
                                    <a href="deleteSuperHuman?superId=${currentSuperHuman.superHumanId}" 
                                       role="button" id="deleteButton" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-alert"></span> Delete</a>
                                </td>

                            </tr>


                        </c:forEach>
                    </table>
                </div> <!-- end col div -->

                <!-- Main Page Content Stop -->

                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Super Human Profile: 
                                    <c:out value="${currentSuperHuman.superHumanName}"></c:out>
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
                                            <input type="text" class="form-control" id="profile-super-name"placeholder="Name or Identity"/>
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








                <!--            <form action="searchHeroes">
                                <select name="search">
                                    <option value="showAll">Show All</option>
                                    <option value="findSHbyId">Find SuperHuman by ID</option>
                                </select>
                                <input type="text" name="searchCriteria"/>
                                <input type="submit" value="Submit"/>
                            </form>-->


            </div>
            <<!-- Placed at the end of the document so the pages load faster -->
            <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
            <!-- Latest compiled and minified JavaScript -->
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    </body>
</html>
