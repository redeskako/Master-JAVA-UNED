<%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 11/04/2017
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Health Data of World Bank</title>
</head>
<body>
<jsp:include page="/main/aux/header.jsp"></jsp:include>
    <div  align='center' style="font-family:Palatino;font-size:small">
        <h3>Por favor identifíquese</h3>
        <br/>
        <table bgcolor="white" cellspacing="4">
            <tbody>
            <tr>
                <td>
                    <form name="loginForm" method="POST" action="j_security_check">
                        <p><strong>Introduzca su nombre de usuario: </strong>
                            <input type="text" name="j_username" size="15"/></p>
                        <p><strong>Introduzca su contraseña: </strong>
                            <input type="password" size="15" name="j_password"/></p>
                        <p>
                            <input type="submit" value="Submit"/>
                            <input type="reset" value="Reset"/></p>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <p>USUARIO : user user</p>
                    <p>ADMINISTRADOR: admin admin</p>
                </td>
            </tr>
            </tbody>
        </table>
        <br/>
    </div>
<jsp:include page="/main/aux/footer.jsp"></jsp:include>
</body>
</html>
