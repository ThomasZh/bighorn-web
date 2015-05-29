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

<title>Profile</title>

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

<script src="js/spark-md5.min.js"></script>
<script src="js/async.js"></script>
<script src="js/upyun-mu.js"></script>
<style>
input,a#submit {
	display: block;
	margin: 10px;
	height: 40px;
}

#submit {
	width: 100px;
	background: #02a3c6;
	border: none;
	color: #fff;
	line-height: 40px;
	text-align: center;
	cursor: pointer;
}

#submit:hover {
	background: #09f;
}

#log {
	border: 2px solid #f8f8f8;
}

#log ul {
	list-style: none;
	font: 14px;
	line-height: 1.5;
	color: #666;
}

#log ul li strong {
	display: inline-block;
	min-width: 100px;
	color: #39b3d7;
}
</style>

</head>
<body>

	<div class="container">
		<form action="">
			<img class="img-circle" src="${avatarUrl}"
				alt="Generic placeholder image" style="width: 100px; height: 100px;">
			<input type="file" name="file" id="file"> <a id="submit">UPLOAD</a>
		</form>
		<div id="log"></div>

		<form id="frmMain" class="form-profile"
			action="modifyProfileAction.htm" commandName="modifyProfile">
			<label for="inputNickname" class="sr-only">Nickname</label> <input
				type="text" name="inputNickname" id="inputNickname"
				value="${nickname}" class="form-control" placeholder="Nickname"
				required autofocus> <input type="hidden"
				id="hidden_filename" name="hidden_filename">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form>
	</div>
	<!-- /container -->

	<script>
		document.getElementById('submit').onclick = function() {
			var ext = '.'
					+ document.getElementById('file').files[0].name.split('.')
							.pop();

			var config = {
				bucket : 'bighorn',
				expiration : parseInt((new Date().getTime() + 3600000) / 1000),

				// 尽量不要使用直接传表单 API 的方式，以防泄露造成安全隐患
				form_api_secret : 'minNcL/cabhEznMeFpYhEQFsH+k='
			};

			var instance = new Sand(config);
			var options = {
				'notify_url' : 'http://upyun.com'
			};

			var filename = '/avatar/test/'
					+ parseInt((new Date().getTime() + 3600000) / 1000) + ext;
			document.getElementById("hidden_filename").value = filename;
			instance.setOptions(options);
			instance.upload(filename);
		};

		// demo stuff
		function addLog(data) {
			var elem = document.createElement("ul");
			for ( var key in data) {
				if (key === 'path') {
					elem.innerHTML += '<li><strong>'
							+ key
							+ ':</strong>'
							+ '<a target="_blank"  href="http://bighorn.b0.upaiyun.com' + data[key] + '">'
							+ data[key] + '</a>' + '</li>';
				} else {
					elem.innerHTML += '<li><strong>' + key + ':</strong>'
							+ data[key] + '</li>';
				}

			}
			log.appendChild(elem);
		}

		document.addEventListener('uploaded', function(e) {
			var log = document.getElementById("log");
			addLog(e.detail);
		});
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>