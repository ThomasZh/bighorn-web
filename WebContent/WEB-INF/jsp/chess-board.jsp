<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>Chess</title>
  <base href="http://chessboardjs.com/" />

  <link rel="stylesheet" href="css/chessboard.css" />
</head>
<body>

<!-- start example HTML --->
<div id="board" style="width: 400px"></div>
<input type="button" id="setStartBtn" value="Start Position" />
<input type="button" id="setRuyLopezBtn" value="Ruy Lopez" />
<input type="button" id="setRookCheckmateBtn" value="Rook Checkmate" />
<!-- end example HTML --->

<script src="js/json3.min.js"></script>
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/chessboard.js"></script>
<script>
var init = function() {

//--- start example JS ---
var board = new ChessBoard('board');

$('#setStartBtn').on('click', function() {
  board.start(false);
});

$('#setRuyLopezBtn').on('click', function() {
  var ruyLopez = 'r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R';
  board.position(ruyLopez, false);
});

$('#setRookCheckmateBtn').on('click', function() {
  var rookCheckmate = {
    a4: 'bK',
    c4: 'wK',
    a7: 'wR'
  };
  board.position(rookCheckmate, false);
});
//--- end example JS ---

}; // end init()
$(document).ready(init);
</script>

</body>
</html>