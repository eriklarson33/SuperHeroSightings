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
        <title>Edit Sighting</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
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
                    </ul> <!-- end navbar ul -->
                </div>
            </div> <!-- end div container -->
            <div id="editSightingBody" class='row'>
                <h1>View / Edit Sighting Profile</h1>
                <hr/>
                <div id="superHumanList" class="col-md-6">
                    <h3>Super Humans Present</h3>
                    <table id="organizationsTable" class="table table-hover">
                        <tr>
                            <th width="25%">Super Human Name</th>
                            <th width="75%">Description</th>
                        </tr>
                        <tbody id="currentSuperRows"></tbody>
                    </table>
                    <hr>
                    <div>
                        <button class="btn btn-primary" id="showEditSupersPresent">
                            Update Super Humans Present
                            <span class="caret"></span></button>
                        <div id="editFormDiv" style="display: none">
                            <form id="editSupersPresentForm">
                                <div class="checkbox form-group">

                                    <div class="col-md-12" id="supersPresent">
                                    </div>
                                    <div class="col-md-12" id="supersAbsent">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='col-md-offset-4 col-md-4'>
                                        <button type="button" id='edit-cancel-button' class='btn btn-default' onclick='cancelEditForm()'>
                                            Cancel
                                        </button>
                                    </div>
                                    <div class="col-md-4">
                                        <button type='button'
                                                id='updateSupersPresent'
                                                class='btn btn-default'>
                                            Update
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="formDiv" class="col-md-6">
                    <h3>Sighting Event: ${siteProfile.name}</h3>
                    <input type="text" class="hidden" id="add-id"
                           value="${siteProfile.sightingId}" readonly="true"/>
                    
                    <sf:form class="form-horizontal" role="form" modelAttribute="siteProfile"
                             action="editSighting" method="POST">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Sighting Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-name"
                                          path="name" placeholder="Sighting Name"/>
                                <sf:errors path="name" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-id" class="col-md-4 control-label">Sighting Id:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-id"
                                          path="sightingId" placeholder="Sighting Identification Number" readonly="true"/>
                                <sf:errors path="sightingId" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                <sf:textarea type="text" rows="2" class="form-control" id="add-description"
                                             path="description" placeholder="Description of the Super Hero sighting."/>
                                <sf:errors path="description" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-street" class="col-md-4 control-label">Street Address:</label>                          
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-street"
                                          path="street" placeholder="Street Address"/>
                                <sf:errors path="street" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-City"
                                          path="city" placeholder="City"/>
                                <sf:errors path="city" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-state"
                                          path="state" placeholder="State"/>
                                <sf:errors path="state" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                                <div class="col-md-8">
                                <sf:input type="number" class="form-control" id="add-zip"
                                          path="zip" placeholder="Zip"/>
                                <sf:errors path="zip" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-latitude" class="col-md-4 control-label">
                                    Latitude:
                                </label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-latitude" name="latitude" 
                                          path="latitude" placeholder="latitude"/>
                                <sf:errors path="latitude" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-longitude" class="col-md-4 control-label">
                                    Longitude:
                                </label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-longitude" name="longitude" 
                                          path="longitude" placeholder="longitude"/>
                                <sf:errors path="longitude" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-date" class="col-md-4 control-label">Date:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-date"
                                          path="sightingDate" placeholder="Date of the Sighting: MM/DD/YYYY"/>
                                <sf:errors path="sightingDate" cssclass="error"></sf:errors>
                                <sf:hidden id="sightingId" path="sightingId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Sighting"/>
                            </div>
                        </div>
                    </sf:form> 
                    
                    
                </div> <!-- end formDIV -->
            </div>  <!-- id="end editSightingBody" -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/editSightingJS.js" ></script>
    </body>
</html>