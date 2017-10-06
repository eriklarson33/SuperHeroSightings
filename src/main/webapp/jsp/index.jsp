<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">    
        <!-- Optional theme -->
        <!--
        <link href="css/home.css" rel="stylesheet" type="text/css">
        -->
        <link rel="stylesheet" type="text/css" href="css/indexCss.css">
        <!-- Include Modernizr in the head, before any other Javascript -->
        <script src="js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>

        <div class="container" id="main">
            <div class="navbar navbar-inverse">
                <div class="container-fluid">

                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active">
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
                </div>
            </div> <!-- end div container -->

            <!-- Main Page Content Start -->
            <br/>
            <ul class="list-group" id="errorMessages"></ul>
            <!--
                </nav>  <!-- end class = "navbar navbar-fixed top" -->
            <div class="jumbotron">
                <h1>Hero Education and Relationship Organization (HERO)</h1>      
                <p>Get the latest information on your favorite Super Heroes and Villains.  Report the latest sightings and see their alliances.</p>
            </div>

            <div class="carousel slide" id="myCarousel" data-ride='carousel'>

                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#myCarousel"></li>
                    <li data-slide-to="1" data-target="#myCarousel"></li>
                    <li data-slide-to="2" data-target="#myCarousel"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active" id="slide1">
                        <center>
                            <img src="img/superheroes-costumes.jpg" alt='SuperHeros Picture' height="400">
                        </center>
                        <div class="carousel-caption">
                            <h3>Super Heroes and Villains</h3>
                            <h4 class="carouselText">Search for your favorite Super Heroes and Villains!</h4>
                        </div>
                        <!-- end carousel-caption -->
                    </div>
                    <!-- end item -->

                    <div class="item" id="slide2">
                        <center>
                            <img src="img/defenders-netflix-pointofgeeks.jpg" alt='Marvel Defenders Picture' height="400">
                        </center>
                        <div class="carousel-caption">
                            <h3>The Organizations behind the Super Heroes and Villains!</h3>
                            <h4 class="carouselText">Explore the Organizations and Alliances vying for control!</h4>
                        </div>
                        <!-- end carousel-caption -->
                    </div>
                    <!-- end item -->

                    <div class="item" id="slide3">
                        <center>
                            <img src="img/King-Kong-vs-Godzilla.jpg" alt='Marvel Defenders Picture' height="400">
                        </center>
                        <div class="carousel-caption">
                            <strong>
                                <h3>Sightings</h3>
                                <h4 class="carouselText">Search the database to see who's appeared most recently to the public eye!</h4>
                            </strong>
                        </div>
                        <!-- end carousel-caption -->
                    </div>
                    <!-- end item -->

                </div>
                <!-- carousel-inner -->

                <!-- Controls (arrows to move left & right) -->
                <a class="left carousel-control" data-slide="prev" href="#myCarousel"><span class="icon-prev"></span></a>

                <a class="right carousel-control" data-slide="next" href="#myCarousel"><span class="icon-next"></span></a>

            </div>
            <!-- end my carousel -->

            <br>

            <div class="well">
                <div class="page-header">
                    <h1>Latest Sightings! <small>Here are the 10 latest sightings.</small></h1>
                </div>
                <!-- end page-header -->

                <div class="container">
                    <table idclass="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th width="20%">Sighting Name</th>
                                <th width="50%">Description</th>
                                <th width="15%">Date</th>
                                <th width="15%">Coordinates</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="site" items="${latestSightingList}">
                                <tr>
                                    <td>
                                        <c:out value="${site.name}"/>
                                    </td>
                                    <td>
                                        <c:out value="${site.description}"/>
                                    </td>
                                    <td>
                                        <c:out value="${site.sightingDate}"/>
                                    </td>
                                    <td>
                                        <c:out value="${site.latitude}, ${site.longitude}"/>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>

                        <!--                        <tbody>
                                                    <tr>
                                                        <th scope="row">10-12-2015</th>
                                                        <td>40.75 N, -73.99 E</td>
                                                        <td>Godzilla on the Empire State Building Again!</td>
                                                        <td>Godzilla</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">01-01-11</th>
                                                        <td>8.85 N, 10.10 E</td>
                                                        <td>Massive thunderStorm. Storm and Thor had a falling out.</td>
                                                        <td>Storm, Thor</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">17-04-2016</th>
                                                        <td>51.62 N, -85.23 E</td>
                                                        <td>Some almost magical crop growth in Iowa</td>
                                                        <td>John Deer</td>
                                                    </tr>
                                                </tbody>-->
                    </table>
                </div>
                <br>
                <div>
                    <!-- end table container -->

                    <a href="${pageContext.request.contextPath}/sightings" class="btn btn-large btn-primary" id="alertMe">Search the Sightings Database</a>

                    <a href="${pageContext.request.contextPath}/superHeroesAndVillains" class="btn btn-large btn-link">or learn more about your favorite Super Hero or Villain</a>
                </div>
            </div>
            <!-- end well -->

        </div>
        <!-- end container, id=main -->

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    </body>
</html>

