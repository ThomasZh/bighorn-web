// GomokuBoardPlaying.js
// thomas.h.zhang
// 2012-05-14

// class define
function GomokuBoardPlay() {
	this.dp = 32; // display pix
	this.dw = 15; // display width
	this.dh = 15; // display height
	this.BoardSize = 15; // default size = 15*15
	this.goGame = new GoGame(this.BoardSize);
	this.goGame.Init();
	this.goJudgement = new GomokuJudgement(this.BoardSize, this.goGame
			.GetStoneArray());
	this.goJudgement.Init();
	this.StepCursor = 0; // cursor for review history
}

// 鍦↗avaScript涓疄鐜扮畝鍗曠殑缁ф壙
// 灏咷oBoardPlay鐨勫師鍨嬫寚鍚慓oBoard鐨勪竴涓疄渚�
// 鍥犱负GoBoard鐨勫疄渚嬪彲浠ヨ皟鐢℅oBoard鍘熷瀷涓殑鏂规硶,
// 鎵�互GoBoardPlay鐨勫疄渚嬩篃鍙互璋冪敤GoBoard鍘熷瀷涓殑鎵�湁灞炴�銆�
GomokuBoardPlay.prototype = new GoBoard(this.dp, this.dw, this.dh,
		this.BoardSize);

// remove stone
GoBoard.prototype.RemoveStone = function(i, j) {
	this.DrawStone(i, j, __None_Stone);
};

// add stone
GoBoard.prototype.AddStone = function(i, j) {
	// this point is not empty
	if (this.goGame.GetColor(i, j) != __None_Stone)
		return -1;

	if (this.goGame.GetStepCount() % 2 == 0) {
		this.goGame.SetColor(i, j, __Black_Stone);
		this.DrawStone(i, j, __Black_Stone);
	} else {
		this.goGame.SetColor(i, j, __White_Stone);
		this.DrawStone(i, j, __White_Stone);
	}

	var num = this.goJudgement.Success(i, j);
	if (num == __Five_in_A_Row) {
		if (this.goGame.GetStepCount() % 2 == 0)
			alert("Black win!");
		else
			alert("White win!");
	}

	// unmark the last step. and mark this step.
	var lastStep = this.goGame.GetLastStep();
	if (lastStep != null)
		this.DrawText(lastStep[0], lastStep[1], __None_Text);
	this.DrawText(i, j, __Last_Mark);

	this.goGame.StepForward(i, j);
	this.StepCursor++;

	return 0;
};

var xmlHttp;
function createXMLHttpRequest() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}

function startRequest(i, j) {
	createXMLHttpRequest();
	try {
		xmlHttp.onreadystatechange = handleStateChange;
		//var gameId = document.getElementById("hidden_gameId").value;
		var url = "game.go?oper=playStep&id=" + gameId + "&i=" + i + "&j=" + j;
		xmlHttp.open("GET", url, true);
		xmlHttp.send();
	} catch (exception) {
		alert("xmlHttp Fail");
	}
}

function handleStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200 || xmlHttp.status == 0) {
			var result = xmlHttp.responseText;
			if (result == "true") {				
				//document.getelementbyid("txttime").value = result; 
				
				goBoard.PlaySound("clickSound");
				goBoard.AddStone(__Last_X, __Last_Y);
			} else {
				alert("Service unavaliable!");
			}

			//var json = eval("(" + result + ")");
			//alert(json.programmers[0].firstName);// 读取json数据
			// alert(json.sex);
		} else {
			//alert("Network error!");
		}
	} else {
		//alert("Network error!");
	}
}

// click on board
GoBoard.prototype.OnMouseClick = function(i, j) {
	// this point is not empty
	if (this.goGame.GetColor(i, j) != __None_Stone) {
		this.PlaySound("errorSound");
		return;
	}

	__Last_X = i;
	__Last_Y = j;
	
	startRequest(i, j);
	//this.PlaySound("clickSound");
	//this.AddStone(i, j);
};

GoBoard.prototype.RegisterMouseDownListener = function(spaceTd, i, j) {
	var thisRef = this;
	spaceTd.onmousedown = function() {
		thisRef.OnMouseClick(i, j);
	};
};

GoBoard.prototype.UnRegisterMouseDownListener = function(spaceTd, i, j) {
	spaceTd.onmousedown = function() {
	};
};
