<%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 11/04/2017
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/global.css"/>
    <title>W B H</title>
</head>
<body>
    <div align="center">
        <h3>Health Data from World Bank</h3>
        <br/>
        <br/>
        <div style="margin: 0 auto">
            <img src="${pageContext.request.contextPath}/resources/img/wb-logo.png" alt="World Bank" style="width: 400px;height: 200px"/>
        </div>
        <ul id="menu-links">
            <li><a href="${pageContext.request.contextPath}/main/users/statistics">Statistics</a></li>
            <li><a href="${pageContext.request.contextPath}/main/users/map">Map</a></li>
            <li><a href="${pageContext.request.contextPath}/main/console/administrator">Administrator</a></li>
        </ul>
    </div>
</body>
</html>
