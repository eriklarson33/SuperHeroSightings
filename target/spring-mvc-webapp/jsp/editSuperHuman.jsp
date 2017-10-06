<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Super Human</title>
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
            <div id="editSuperBody" class='row'>
                <h1>Edit Super Human</h1>
                <hr/>
                <div id="superHumanList" class="col-md-6">
                    <h3>Super Human Powers</h3>
                    <table id="organizationsTable" class="table table-hover">
                        <tr>
                            <th width="25%">Super Power</th>
                            <th width="75%">Description</th>
                        </tr>
                        <tbody id="currentSuperRows"></tbody>
                    </table>
                    <hr>
                    <div>
                        <button class="btn btn-primary" id="showEditSupersPresent">
                            Update Super Humans Powers
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
                <div id="editSuperHumanForm" class="col-md-6">
                    <h3>Super Human Profile</h3>
                    <form class="form-horizontal" role="form" modelAttribute="superProfile"
                             action="editSuper" method="POST">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-name"
                                       path="superHumanName" name="superHumanName" value="${superProfile.superHumanName}" maxlength="20"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-id" class="col-md-4 control-label">Super Power Id:</label>
                                <div class="col-md-8">
                                <input type="text" class="form-control" id="add-id"
                                       path="superHumanId" name="superHumanId" value="${superProfile.superHumanId}" readonly="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                <input type="text" rows="2" class="form-control" id="add-description"
                                       path="description" name="description" value="${superProfile.description}" maxlength="100"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Update Super Human"/>
                                </div>
                            </div>
                    </form>
                </div> <!-- editSuperHumanForm div -->
            </div><!-- editSuperBody div -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/editSuperJS.js" ></script>
    </body>
</html>