var options = {
	'bucket': 'bighorn',
	'save-key': 'avatar/filename.txt',
	'expiration': Math.floor(new Date().getTime() / 1000) + 86400
};
var policy = window.btoa(JSON.stringify(options));
var form_api_secret = 'minNcL/cabhEznMeFpYhEQFsH+k=';
var signature = md5(policy + '&' + form_api_secret);

var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'pickfiles', // you can pass in id...
	container: document.getElementById('container'), // ... or DOM Element itself
	flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',

	url : 'http://v0.api.upyun.com/' + options.bucket,

	multipart_params: {
		'Filename': '${filename}', // adding this to keep consistency across the runtimes
		'Content-Type': '',
		'policy': policy,
		'signature': signature,
	},

	init: {
		PostInit: function() {
			document.getElementById('filelist').innerHTML = '';

			document.getElementById('uploadfiles').onclick = function() {
				uploader.start();
				return false;
			};
		},

		FilesAdded: function(up, files) {
			plupload.each(files, function(file) {
				document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
			});
		},

		UploadProgress: function(up, file) {
			document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
		},

		FileUploaded: function(up, file, info) {
			var response = JSON.parse(info.response);
			console.log(response.url);
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError #" + err.code + ": " + err.message));
		}
	}
});

uploader.init();