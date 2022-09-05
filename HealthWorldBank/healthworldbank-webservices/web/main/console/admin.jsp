<%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 11/04/2017
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/global.css"/>
    <title>Admin WB</title>
</head>
<body>
    <div id="container">
        <img src="../../resources/img/wb-logo.png" alt="W B H" style="width: 100px;height: 50px"/>
        <jsp:include page="../aux/header.jsp"></jsp:include>
        <div style="margin: 100px auto">
            <h5 style="text-align: center">SI ESTÁS AQUÍ ES QUE TIENES ACCESO COMO ADMINISTRADOR</h5>
        </div>
        <form id="form-statistics" action="${pageContext.request.contextPath}/main/console/administrator" autocomplete="off">
            <fieldset>
               <legend>Country Id</legend>
               <input type="text" name="country-id">
            </fieldset>
            <fieldset>
                <legend>Country Name</legend>
                <input type="text" name="country-name">
            </fieldset>
            <input type="submit" name="operation" value="new-country">
        </form>
    </div>
</body>
</html>
