<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="preds" type="predictions.Predictions" class="predictions.Predictions">
              
<%      
String verb = request.getMethod();
if (!verb.equalsIgnoreCase("GET")){
	response.sendError(response.SC_METHOD_NOT_ALLOWED,"Only GET requests are allowed");
}else{
	preds.setServletContext(application);
	out.println(preds.getPredictions());
}
%>
</jsp:useBean> 
</body>
</html>