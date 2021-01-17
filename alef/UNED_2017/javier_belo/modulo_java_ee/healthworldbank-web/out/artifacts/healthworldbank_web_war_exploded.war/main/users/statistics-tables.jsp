<%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 02/05/2017
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Statistics</title>
    <link rel="stylesheet" type="text/css" href="../../css/global.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mainj.js"></script>
</head>
<body onload="onLoaded()">
<div id="container" style="background: green">
    esto es el contenedor

    <div id="header" style="background: yellow">
        <ul id="menu-links">
            <li><a href="/healthworldbank-web/main/users/statistics">Statistics</a></li>
            <li><a href="/healthworldbank-web/main/users/map">Map</a></li>
            <li><a href="/healthworldbank-web/main/console/administrator">Administrator</a></li>
        </ul>
    </div>
    <div id="left-panel" style="background-color: white">
        <jsp:text>${dataBean.countryName}</jsp:text>
        <table id="table-entities">
            <caption>Countries</caption>
            <thead>
            <tr>
                <th>Countries ISO Code</th>
                <th>Country Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="country" items="${dataBean.entities}">
                <tr>
                    <td>${country.countryCode}</td>
                    <td>${country.countryName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table id = "table-indicator">
            <caption>Health Indicator</caption>
            <thead>
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Note</th>
                <th>Source</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="indicators" items="${dataBean.healthIndicators}">
                <tr>
                    <td>${indicators.indicatorCode}</td>
                    <td>${indicators.indicatorName}</td>
                    <td>${indicators.sourceNote}</td>
                    <td>${indicators.sourceOrganization}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table>
            <caption>Statistics</caption>
            <thead>
            <tr>
                <th>Indicator</th>
                <th>Country</th>
                <th>Year</th>
                <th>Value</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="results" items="${dataBean.results}">
                <tr>
                    <td>${results.indicatorCode}</td>
                    <td>${results.countryCode}</td>
                    <td>${results.year}</td>
                    <td>${results.percentage}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="right-panel" style="background-color: pink">
        este es el bloque derecho eidi lbied ged eg wedg egdoig ligedg gioydge iigedw  hiewu iwehihd diwe
        <form method="post" action="/healthworldbank-web/processStatistics">
            <b>Nombre</b>
            <input type="text" name="usuario">
            <input type="submit"  value=" Aceptar ">

        </form>
    </div>
    <div id="footer" style="background-color: red">este es el pie</div>
</div>
</body>
</html>
