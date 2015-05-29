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

    <title>Chess Homepage</title>

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
              <a class="navbar-brand glyphicon glyphicon-home active" href="#"></a>
              <a class="navbar-brand glyphicon glyphicon-plus" href="invite.htm"></a>
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
                  <li><a class="glyphicon glyphicon-user" href="profile.htm"> ${sessionScope.user.nickname}</a></li>
                  <li><a class="glyphicon glyphicon-log-out" href="logoutAction.htm"> Logout</a></li>
                </c:if>
              </ul>
            </div>
          </div>
        </nav>

      </div>
    </div>


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img src="images/Chess_1024.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Taiji Chess</h1>
              <p>You can play chess <code>slowly</code>! Yes, one day one step, if you want. You can playing <code>multiply</code> games on the same time.</p>
              <c:if test="${sessionScope.user == null}">
                <p><a class="btn btn-lg btn-primary" href="signup.htm" role="button">Sign up today</a></p>
              </c:if>
              <c:if test="${sessionScope.user != null}">
                <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
              </c:if>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images/Magnus-Carlsen-in-Chess-Championship-HD-Wallpaper.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Invite</h1>
              <p>You can create a new game, setup opponent's rank and playing time duration, and <code>waiting</code> other player join. Or you can <code>join</code> other's challenge directly.</p>
              <c:if test="${sessionScope.user == null}">
                <p><a class="btn btn-lg btn-primary" href="login.htm" role="button">Create a new game +</a></p>
              </c:if>
              <c:if test="${sessionScope.user != null}">
                <p><a class="btn btn-lg btn-primary" href="invite.htm" role="button">Create a new game +</a></p>
              </c:if>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="data:image/gif;base64,R0lGODlhAQABAIAAAFVVVQAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Mobile</h1>
              <p>Coming soon...</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Download iOS</a></p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Download Android</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->

    <!-- Invite List
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

	
	<div class="container marketing">
	
	  <!-- START THE FEATURETTES -->


      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">Berlin Endgame. <span class="text-muted">It'll blow your mind.</span></h2>
          <p class="lead">Test your knowledge: which opening did Vladimir Kramnik lean on to equalize his chances as Black in the 2000 world chess championship, where he would eventually defeat the greatest chess player of all time?
Fill in the blank: The 2014 world chess championship between Magnus Carlsen and Viswanathan Anand featured the _______________ in four of 11 games.
If you found the answer to these extremely complex riddles, then we will assume you also see the pattern here. 
True or false: All four Berlin Wall games in the 2014 championship went to an endgame. TRUE!</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" src="images/berlin-endgame.jpg" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-5">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" src="images/5books.jpg" alt="Generic placeholder image">
        </div>
        <div class="col-md-7">
          <h2 class="featurette-heading">5 Great Chess Books For Beginners. <span class="text-muted">See for yourself.</span></h2>
          <p class="lead">Like it or not, holiday gift-shopping season is now upon us, at least according to the ubiquitous calendar of commerce. 
Instead of being trampled by unruly deal-seekers at big box retail stores, you can shop online and still get something thoughtful for the chess beginner on your list.
For our list, we polled the Chess.com content team for its choice of the best chess books for beginners.</p>
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">2014 World Championship. <span class="text-muted">Highlights from Rounds 9 & 10.</span></h2>
          <p class="lead">IM Daniel Rensch and GM Georg Meier take the helm of Chess.com’s highlights coverage for the world chess championship between Magnus Carlsen and Vishy Anand. Games nine and ten were crucial for the eventual outcome of the match, as Carlsen was able to secure draws despite Anand’s best attempts to convert any winning chances he could create. Stick around for an in-depth look at only the important stuff in this informative video.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive" data-src="holder.js/500x500/auto" src="images/StudentChessMove_forWeb.jpg" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->
      
          
      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; 2015 younguard.net</a></p>
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
