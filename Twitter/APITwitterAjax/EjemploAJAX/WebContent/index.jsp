<html>
<head>
	<title>Prueba Ajax</title>
	<script type="text/javascript" src="Resources/javascript/AjaxFunctions.js"></script>
	<link href="Resources/css/stylesHeaderFooter.css" rel="stylesheet" type="text/css" />
	
	<!-- You have to include these two JavaScript files from DWR -->  
	  	<script type='text/javascript' src='/ajaxdemo/dwr/engine.js'></script> 
  		<script type='text/javascript' src='/ajaxdemo/dwr/util.js'></script> 
	<!-- This JavaScript file is generated specifically for your application -->  
	<!-- in dwr.xml we have converted GetTweets.java to GetTweets.js-->  
	<!-- so we can call java class methods using this javascript class--> 
		<script type='text/javascript' src='/ajaxdemo/dwr/interface/GetTweets.js'></script>
</head>
<body>
	<%@ include file="Resources/html/encabezado.html" %>
	<br/><br/><br/><br/>
	<input type="button" value="Mostrar Tweets del 10 al 14" onclick="mostrarTweets()"/>
	<br/><br/>
	<b>Tweets del 10 al 14:</b> <br/>
	<table id="miTabla">
	</table>
	<br/><br/><br/><br/>
	
	<%@ include file="Resources/html/footer.html" %>			
</body>
</html>