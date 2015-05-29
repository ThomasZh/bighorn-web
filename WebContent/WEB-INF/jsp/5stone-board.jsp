<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Five Stone</title>


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

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="jquery/1.11.2/jquery.min.js"></script>

<script type="text/javascript" src="./js/go/GoGame.js"></script>
<script type="text/javascript" src="./js/go/GoBoard.js"></script>
<script type="text/javascript" src="./js/go/GomokuJudgement.js"></script>
<script type="text/javascript" src="./js/go/GomokuBoardPlay.js"></script>

</head>
<body>

	<input type="hidden" id="hidden_gameId" name="hidden_gameId"
		value="${gameId}" />
	<input type="hidden" id="hidden_gameManual" name="hidden_gameManual"
		value="${gameManual}" />

	<div id='GoBoardPanel'></div>
	<embed name="clickSound" src="./sound/button-30.wav" autostart="false"
		mastersound hidden="true" volume=100 width=0 height=0></embed>
	<embed name="errorSound" src="./sound/beep-3.wav" autostart="false"
		mastersound hidden="true" volume=100 width=0 height=0></embed>

	<script type="text/javascript">
		// 初始化棋盘
		var goBoard = new GomokuBoardPlay();
		goBoard.Init('GoBoardPanel');
		goBoard.SetMode(__Mode_Playing_Black);
		//var gameId = '00000000-0000-0000-0000-000000000000';
		var gameId = document.getElementById("hidden_gameId").value;
		var gameManual = document.getElementById("hidden_gameManual").value;
		//alert(gameManual);

		// load chess manual
		var words = gameManual.split(',');
		for (var i = 0; i < words.length-1; ) {
			var x = words[i++];
			var y = words[i++];
			goBoard.AddStone(x, y);
		}

		// 鼠标右键被禁用
		document.oncontextmenu = stop;
	</script>



</body>
</html>