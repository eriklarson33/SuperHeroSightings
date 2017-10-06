<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Super Heroes and Villains</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
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
                    </ul> <!-- end navbar ul -->
                </div>
            </div> <!-- end div container -->

            <!-- Main Page Content Start -->
            <div>
                    <div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Modal title: 
                            <span id="profile-super-name"></span>
                        </h4>
                        <p>
                            Registration ID:<span id="profile-super-id"></span>
                        </p>
                    </div>
                    <div class="modal-body">
                        <h5>Description:</h5>
                        <p id="profile-description"></p>
                        <h5>Known Super Powers:</h5>
                        <p id="profile-superPowers"></p>
                        <h5>Organizations:</h5>
                        <p id="profile-organizations"></p>
                        <h5>Sightings:</h5>
                        <p id="profile-sightings"></p>

                        ...
                    </div>
                    <sf:form class="form-horizontal" role="form" modelAttribute="contact"
                             action="editContact" method="POST">
                        <div class="form-group">
                            <label for="add-first-name" class="col-md-4 control-label">
                                Super's Name:
                            </label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-first-name"
                                          path="firstName" placeholder="Name or Identity"/>
                                <sf:errors path="firstName" cssclass="error"></sf:errors>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">
                                Description:
                            </label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-description"
                                          path="lastName" placeholder="Description of the Super or what they stand for."/>
                                <sf:errors path="lastName" cssclass="error"></sf:errors>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-company" class="col-md-4 control-label">Company:</label>                          
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-company"
                                          path="company" placeholder="Company"/>
                                <sf:errors path="company" cssclass="error"></sf:errors>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <sf:input type="email" class="form-control" id="add-email"
                                          path="email" placeholder="Email"/>
                                <sf:errors path="email" cssclass="error"></sf:errors>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                            <div class="col-md-8">
                                <sf:input type="tel" class="form-control" id="add-phone"
                                          path="phone" placeholder="Phone"/>
                                <sf:errors path="phone" cssclass="error"></sf:errors>
                                <sf:hidden path="contactId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" 
                                       value="Update Contact"/>
                            </div>
                        </div>

                    </sf:form>
                </div> <!-- end edit container -->

            <!-- Main Page Content Stop -->
        </div>  <!-- end class container -->


                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="js/superHumansJS.js" ></script>
</body>
</html>