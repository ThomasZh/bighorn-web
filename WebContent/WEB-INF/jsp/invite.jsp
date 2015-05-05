<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Invite Games</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="carousel.css" rel="stylesheet">
  </head>
<!-- NAVBAR
================================================== -->
  <body>
    <div class="navbar-wrapper">
      <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand glyphicon glyphicon-home" href="index.htm"></a>
              <a class="navbar-brand glyphicon glyphicon-plus active" href="#"></a>
              <c:if test="${sessionScope.user != null}">
                <a class="navbar-brand glyphicon glyphicon-knight" href="playing.htm"><small><span class="label label-danger">3</span></small></a>
              </c:if>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user == null}">
              	  <li><a class="glyphicon glyphicon-log-in" href="login.htm"> Sign in <span class="sr-only">(current)</span></a></li>
                </c:if> 
                <c:if test="${sessionScope.user != null}">
                  <li><a class="glyphicon glyphicon-user" href="#profile"> ${sessionScope.user.nickname}</a></li>
                  <li><a class="glyphicon glyphicon-log-out" href="logoutAction.htm"> Logout</a></li>
                </c:if>
              </ul>
            </div>
          </div>
        </nav>

      </div>
    </div>




    <!-- Invite List
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

	<div class="container page-header">
	  <h2>Invite List</h2>
	</div>
	
	
	<div class="container">
	  <div class="row" id="invite-list">
	  
	    <div class="col-xs-12 col-sm-6 block"><!-- /splitlayout -->
	      <div class="split-left" style="width: 50%; float:left" align="center">
	        <p></p>
   			<img class="img-circle" src="images/blue-plus.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>&nbsp;</h4>
            <p>Join</p>
		  </div> 
		  <div class="split-right" style="width: 50%; float:right" align="center">
		    <p></p>
   			<img class="img-circle" src="images/profile2.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>Jerry</h4>
            <p>3d</p>
		  </div>
	    </div><!-- /.splitlayout -->
		
        <div class="col-xs-12 col-sm-6 block"><!-- /splitlayout -->
	      <div class="split-left" style="width: 50%; float:left" align="center">
	        <p></p>
   			<img class="img-circle" src="images/waiting.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>&nbsp;</h4>
            <p>Waiting...</p>
		  </div> 
		  <div class="split-right" style="width: 50%; float:right" align="center">
		    <p></p>
   			<img class="img-circle" src="images/profile1.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>Tom</h4>
            <p>1k</p>
		  </div>
	    </div><!-- /.splitlayout -->
	  	
	    <div class="col-xs-12 col-sm-6 block"><!-- /splitlayout -->
	      <div class="split-left" style="width: 50%; float:left" align="center">
	        <p></p>
   			<img class="img-circle" src="images/blue-plus.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>&nbsp;</h4>
            <p>Join</p>
		  </div> 
		  <div class="split-right" style="width: 50%; float:right" align="center">
		    <p></p>
   			<img class="img-circle" src="images/profile2.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>Jerry</h4>
            <p>3d</p>
		  </div>
	    </div><!-- /.splitlayout -->
	    
	    <div class="col-xs-12 col-sm-6 block"><!-- /splitlayout -->
	      <div class="split-left" style="width: 50%; float:left" align="center">
	        <p></p>
   			<img class="img-circle" src="images/profile2.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>Jerry</h4>
            <p>3d</p>
		  </div> 
		  <div class="split-right" style="width: 50%; float:right" align="center">
		    <p></p>
   			<img class="img-circle" src="images/blue-plus.jpg" alt="Generic placeholder image" style="width: 100px; height: 100px;">
            <h4>&nbsp;</h4>
            <p>Join</p>
		  </div>
	    </div><!-- /.splitlayout -->
	  
  	  </div><!-- /.row -->
  	  
      
      <nav>
        <ul class="pagination" >
          <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
          <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
        </ul>
      </nav>

    
      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; 2015 younguard.net</p>
      </footer>
    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="../../assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>