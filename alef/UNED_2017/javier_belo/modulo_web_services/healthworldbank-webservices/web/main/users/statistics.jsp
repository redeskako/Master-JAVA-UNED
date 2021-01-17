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

</head>
<body onload="onLoaded()">

    <div id="container">
        <img src="../../resources/img/wb-logo.png" alt="World Bank" style="width: 100px;height: 50px"/>
        <jsp:include page="../aux/header.jsp"></jsp:include>
        <div id="left-panel">
            <form id="form-statistics" action="${pageContext.request.contextPath}/main/users/statistics" onsubmit="return validateForm()" method="get" autocomplete="off">
                <fieldset>
                    <legend>Countries</legend>
                    <select id="select-country" size="5" name="country">

                        <c:forEach var="country" items="${dataBean.countryContainer.entities}">
                            <option  value="${country.countryCode}">${country.countryName}</option>
                        </c:forEach>
                    </select>
                    <input id="country-offset" type="hidden" name="country-offset" value="${dataBean.countryContainer.offset}">
                    <ul id="countries_page">
                        <li><a onclick="pagination('select-country','backward')" href=#><< back</a></li>
                        <li><a onclick="pagination('select-country','forward')" href=#>forward >></a></li>
                    </ul>
                </fieldset>

                <fieldset>
                    <legend>Indicator</legend>
                    <select id="select-indicator" size="5" name="indicator">
                        <c:forEach var="indicators" items="${dataBean.indicatorsContainer.entities}">
                            <option value="${indicators.indicatorCode}">${indicators.indicatorName}</option>
                        </c:forEach>
                    </select>
                    <input id ="indicator-offset" type="hidden" name="indicator-offset" value="${dataBean.indicatorsContainer.offset}">
                    <ul id="indicators_page">
                        <li><a onclick="pagination('select-indicator','backward')" href=#><< back</a></li>
                        <li><a onclick="pagination('select-indicator','forward')" href=#>forward >></a></li>
                    </ul>
                </fieldset>
                <input type="submit" name="operation" value="health-statistic">
            </form>
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
                    <c:forEach var="results" items="${dataBean.statisticsContainer.entities}">
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
        <div id="right-panel">
            <div id="photo-panel">
                <custom:photo height="400" width="400"></custom:photo>
            </div>
        </div>
        <div id="footer">
            <jsp:include page="../aux/footer.jsp"></jsp:include>
        </div>
    </div>
</body>
</html>
