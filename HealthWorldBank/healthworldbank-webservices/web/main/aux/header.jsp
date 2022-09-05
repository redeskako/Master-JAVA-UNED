<%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 12/05/2017
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <div>
        <jsp:include page="./menu.jsp"></jsp:include>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/main/logout" method="post">
            <input type="submit" name="operation" value="logout">
        </form>
    </div>
    <div id="helper"></div>
</div>

