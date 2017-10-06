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
        <title>Edit Organization</title>
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

            <div id="editOrganizationBody" class='row'>
                <h1>View / Edit Organization Profile</h1>
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
                    <h3>Organization Summary: ${orgProfile.organizationName}</h3>
                    <input type="text" class="hidden" id="add-id"
                           value="${orgProfile.organizationId}" readonly="true"/>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <sf:form class="form-horizontal" role="form" modelAttribute="orgProfile"
                                 action="editOrganization" method="POST">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Organization Name:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-name"
                                              path="organizationName" placeholder="Organization Name"/>
                                    <sf:errors path="organizationName" cssclass="error"></sf:errors>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-id" class="col-md-4 control-label">Organization Id:</label>
                                    <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-id"
                                              path="organizationId" placeholder="Organization Identification Number" readonly="true"/>
                                    <sf:errors path="organizationId" cssclass="error"></sf:errors>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-description" class="col-md-4 control-label">Description:</label>
                                    <div class="col-md-8">
                                    <sf:input type="text" class="form-control" id="add-description"
                                              path="description" placeholder="Description of the Organization and its mission."/>
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
                                    <sf:input type="text" class="form-control" id="add-zip"
                                              path="zip" placeholder="Zip"/>
                                    <sf:errors path="zip" cssclass="error"></sf:errors>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                                    <div class="col-md-8">
                                    <sf:input type="tel" class="form-control" id="add-phone"
                                              path="phone" placeholder="Phone"/>
                                    <sf:errors path="phone" cssclass="error"></sf:errors>
                                    <sf:hidden id="organizationId" path="organizationId"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Update Organization"/>
                                </div>
                            </div>
                        </sf:form>   
                    </sec:authorize>

                </div>  <!-- end formDiv -->
            </div> <!-- end editOrganizationBody Div -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/editOrganizationsJS.js" ></script>
    </body>
</html>
