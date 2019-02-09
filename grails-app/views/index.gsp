<!DOCTYPE html>
<html>
	<head>
		<title>Welcome to my library</title>
    	<meta name="layout" content="main"/>

	  
	  <%-- <asset:javascript src="jquery-3.3.1.min.js"/>
	  <asset:javascript src="popper.min.js"/>
	  <asset:javascript src="cmeditor.js"/>
	  <asset:stylesheet href="cmeditor.css"/>  --%>
    <style>
	.nav-item > a {
		text-decoration: none;
	}
	.dropdown-menu > a {
		text-decoration: none;
	}
	#cmeditor-book-centerpane > div.CodeMirror.cm-s-default.CodeMirror-wrap > div.CodeMirror-scroll{
		width: 1058px;
	}
	</style>
	</head>
	<body>
	<div class='container'>
	<h1>My Library!</h1>

	<cmeditor:tabs
			name="book"
			ajax="[listURL:'library/listFiles', deleteURL:'library/deleteFile', updateURL:'library/saveFile', getURL:'library/loadFile']">

		<label for="author"> <g:message code="myLibrary.author.label" default="Author" /></label>
		<g:textField name="author.name" class="cmeditor-field" /><br /><br />

	</cmeditor:tabs>
	<button id="compilebtn" type="button" class="btn btn-primary btn-lg btn-block">View Below</button>
	<br>
	<br>
	</div>
	<div id='iframe' src="https://getbootstrap.com/docs/4.0/components/buttons/"></div>


	<script>
		$('#compilebtn').click(function(){
			var text = '';
			var result = "<h1><b>Hello!!!!</b></h1>";
			var iframe = document.getElementById('iframe');
			$('.CodeMirror-line').each(function(){
  				text += $(this).text();
			});
			iframe.innerHTML = text;
		})
	</script>
	</body>
	
</html>
