<%--
  Created by IntelliJ IDEA.
  User: javierbelogarcia
  Date: 12/05/2017
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="pro.jbelo.beans.DataBean" %>
<%@ page contentType="image/png" %>
<%
    //java.io.OutputStream binaryOut = response.getOutputStream();

    DataBean dataBean = (DataBean) session.getAttribute("dataBean");
    //dataBean.getChartImage();
    //DataBean dataBean = (DataBean) request.getSession().getAttribute("dataBean");
    //byte[] bytes = dataBean.getChartImage();
    //try {

    //for (int i = 0; i < bytes.length; i++) {
    //    binaryOut.write(bytes[i]);
    //}
    //}finally {
    //    binaryOut.close();
    //}
%>
