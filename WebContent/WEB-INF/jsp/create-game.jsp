<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>CreateGame</title>

<!-- Bootstrap core CSS -->
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">
/**
 * Function to load a specific page based on radio button selection.
 */
function radioClickedGet(ctrl) {
   if ("blue" === ctrl.value)
      location.replace("YOUR PAGE ONE");
   else if ("white" === ctrl.value)
      location.replace("YOUR PAGE TWO")
}
 
/**
 * Function to post a form to a specific page based on radio button selection.
 */
function radioClickedPost(ctrl) {
   var strAction;
   if ("blue" === ctrl.value)
       strAction = "YOUR PAGE ONE";
   else if ("white" === ctrl.value)
       strAction = "YOUR PAGE TWO";
   var frm = document.getElementById('frmMain');
   frm.action = strAction;
   frm.submit();
}
</script>

</head>

<body>

	${message}
	<div class="container">
		<form id="frmMain" class="form-create-game" action="createGameAction.htm" commandName="createGame">
			<h2 class="form-signin-heading">Please choose chess color</h2>
			<input type="radio" name="radiobutton" value="blue" checked > Blue
			<input type="radio" name="radiobutton" value="white" > White
			<button class="btn btn-lg btn-primary btn-block" type="submit">Create a game+</button>
		</form>
	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>