// index.js

// request environment variables on server
xhrGet("/api/hello", function(responseText){
	// retrieve data
	var data = parseJson(responseText);

	// build environment variable table
	var table = [
		'<table border="0" cellspacing="0" cellpadding="0">',
		'<thead><tr><th class="env-var">Name</th><th>Value</th></tr></thead>',
		'<tbody>'
	];
	for(var name in data){
		var val = data[name];
		table.push("<tr><td class='env-var'>", name, "</td><td><pre>",
			// if the environment variable is JSON, print it in a prettier format
			/^{/.test(val) ? prettyJson(val) : val,
			"</pre></td></tr>");
	}
	table.push('</tbody></table>');

	// add to document
	var div = document.createElement('div');
	div.innerHTML = table.join('');
	document.body.appendChild(div.firstChild);
}, function(err){
	console.log(err);
});

//utilities
function createXHR(){
	if(typeof XMLHttpRequest != 'undefined'){
		return new XMLHttpRequest();
	}else{
		try{
			return new ActiveXObject('Msxml2.XMLHTTP');
		}catch(e){
			try{
				return new ActiveXObject('Microsoft.XMLHTTP');
			}catch(e){}
		}
	}
	return null;
}
function xhrGet(url, callback, errback){
	var xhr = new createXHR();
	xhr.open("GET", url, true);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				callback(xhr.responseText);
			}else{
				errback('service not available');
			}
		}
	};
	xhr.timeout = 3000;
	xhr.ontimeout = errback;
	xhr.send();
}
function parseJson(str){
	return window.JSON ? JSON.parse(str) : eval('(' + str + ')');
}
function prettyJson(str){
	// If browser does not have JSON utilities, just print the raw string value.
	return window.JSON ? JSON.stringify(JSON.parse(str), null, '  ') : str;
}

