<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 11/04/2017
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="../../WEB-INF/custom-tags.tld"%>
<html>
<head>
    <title>Statistics</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/global.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main-jscripts.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['geochart']});
        google.charts.setOnLoadCallback(drawRegionsMap);

        function drawRegionsMap() {

            var data = google.visualization.arrayToDataTable([['Country','Percentage']]);

            var options = {};

            var chart = new google.visualization.GeoChart(document.getElementById('geo-chart'));

            chart.draw(data, options);
        }

    </script>

</head>
<body onload="onLoaded()">
<div id="container">
    <img src="../../resources/img/wb-logo.png" alt="World Bank" style="width: 100px;height: 50px"/>

    <jsp:include page="../aux/header.jsp"></jsp:include>
    <div id="left-panel">
        <form id="form-statistics" action="${pageContext.request.contextPath}/main/users/map" onsubmit="return validateForm()" method="get" autocomplete="off">
            <fieldset>
                <legend>Year</legend>
                <select id="select-year" size="5" name="year">
                    <c:forEach var="years" items="${dataBean.years}">
                        <option  value="${years.year}">${years.year}</option>
                    </c:forEach>
                </select>
            </fieldset>
            <fieldset>
                <legend>Indicator</legend>
                <select id="select-indicator" size="5" name="indicator">
                    <c:forEach var="indicators" items="${dataBean.indicatorsContainer.entities}">
                        <option value="${indicators.indicatorCode}">${indicators.indicatorName}</option>
                    </c:forEach>
                </select>
                <input id="indicator-offset" type="hidden" name="indicator-offset" value="${dataBean.indicatorsContainer.offset}">
                <ul id="indicators_page">
                    <li><a onclick="pagination('select-indicator','backward')" href=#><< back</a></li>
                    <li><a onclick="pagination('select-indicator','forward')" href=#>forward >></a></li>
                </ul>
            </fieldset>
        </form>
        <button type="button" onclick="getGeoChart()">Geo Chart</button>

    </div>
    <div id="right-panel">
        <div id="geo-chart"></div>
    </div>
    <div id="footer">
        <jsp:include page="../aux/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>
