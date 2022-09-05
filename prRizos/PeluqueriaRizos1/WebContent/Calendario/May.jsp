<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<HTML LANG="en">
<link rel="stylesheet" href="../css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../templatecalendario/cabecera.html" %>
<%@ include file="../templatecalendario/informacion.html" %>  
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="Author" CONTENT="D. Brent Herring">
<META NAME="Reply-To" CONTENT="webmaster">
<META NAME="Formatter" CONTENT="HTML Calendar Generator, Version 4.02 05 February 2002 http://www.dbhsoftware.com/">
<META HTTP-EQUIV="Description" NAME="Title" CONTENT="Calendar">
<META HTTP-EQUIV="Keywords" CONTENT="
">
<div id="main">
<TITLE>Calendar - Mayo</TITLE>
</HEAD>
<br>
<BODY LEFTMARGIN="0" TOPMARGIN="0" BGCOLOR="#F9D299"">
<CENTER>
<text3>2008</text3>
</CENTER>
<br>
<center>
<FONT FACE="Verdana"><A HREF="January.jsp">Enero &nbsp</A> <A HREF="February.jsp">Febrero &nbsp</A> <A HREF="March.jsp">Marzo &nbsp</A> <A HREF="April.jsp">Abril &nbsp</A> <A HREF="May.jsp">Mayo &nbsp</A> <A HREF="June.jsp">Junio &nbsp</A> <A HREF="July.jsp">Julio &nbsp</A> <A HREF="August.jsp">Agosto &nbsp</A> <A HREF="September.jsp">Septiembre &nbsp</A> <A HREF="October.jsp">Octubre &nbsp</A> <A HREF="November.jsp">Noviembre &nbsp</A> <A HREF="December.jsp">Diciembre &nbsp</A> </FONT>
</CENTER>
<P ALIGN="RIGHT">
<TABLE BORDER="5" WIDTH="75%" align="center" >
<TR BGCOLOR="#008080" ALIGN="CENTER" VALIGN="TOP">
<TD ALIGN="CENTER" COLSPAN="7"><H3><FONT FACE="Verdana" COLOR="#004000"> 
<FONT SIZE="2"><a href="July.jsp">Abril</a></FONT> < Mayo><FONT SIZE="2"><a href="September.jsp">Junio</a></FONT></FONT></H3></TD></TR>
<TR BGCOLOR="#FFFF80" ALIGN="LEFT" VALIGN="TOP">
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">Domingo</FONT></TD>
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">Lunes</FONT></TD>
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">Martes</FONT></TD>
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">Mi&eacute;rcoles</FONT></TD>
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">Jueves</FONT></TD>
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">Viernes</FONT></TD>
<TD ALIGN="CENTER"><FONT FACE="Verdana" COLOR="#000000" SIZE="2">S&aacute;bado</FONT></TD>
</TR>
<%  java.sql.Date dia1 =new Date(108,4,1);String dia1String=dia1.toString();java.sql.Date dia2 =new Date(108,4,2);String dia2String=dia2.toString();
    java.sql.Date dia3 =new Date(108,4,3);String dia3String=dia3.toString();java.sql.Date dia4 =new Date(108,4,4);String dia4String=dia4.toString();
    java.sql.Date dia5 =new Date(108,4,5);String dia5String=dia5.toString();java.sql.Date dia6 =new Date(108,4,6);String dia6String=dia6.toString();
    java.sql.Date dia7 =new Date(108,4,7);String dia7String=dia7.toString();java.sql.Date dia8 =new Date(108,4,8);String dia8String=dia8.toString();
    java.sql.Date dia9 =new Date(108,4,9);String dia9String=dia9.toString();java.sql.Date dia10 =new Date(108,4,10);String dia10String=dia10.toString();
    java.sql.Date dia11 =new Date(108,4,11);String dia11String=dia11.toString();java.sql.Date dia12 =new Date(108,4,12);String dia12String=dia12.toString();
    java.sql.Date dia13 =new Date(108,4,13);String dia13String=dia13.toString();java.sql.Date dia14 =new Date(108,4,14);String dia14String=dia14.toString();
    java.sql.Date dia15 =new Date(108,4,15);String dia15String=dia15.toString();java.sql.Date dia16 =new Date(108,4,16);String dia16String=dia16.toString();
    java.sql.Date dia17 =new Date(108,4,17);String dia17String=dia17.toString();java.sql.Date dia18 =new Date(108,4,18);String dia18String=dia18.toString();
    java.sql.Date dia19 =new Date(108,4,19);String dia19String=dia19.toString();java.sql.Date dia20 =new Date(108,4,20);String dia20String=dia20.toString();
    java.sql.Date dia21 =new Date(108,4,21);String dia21String=dia21.toString();java.sql.Date dia22 =new Date(108,4,22);String dia22String=dia22.toString();
    java.sql.Date dia23 =new Date(108,4,23);String dia23String=dia23.toString();java.sql.Date dia24 =new Date(108,4,24);String dia24String=dia24.toString();
    java.sql.Date dia25 =new Date(108,4,25);String dia25String=dia25.toString();java.sql.Date dia26 =new Date(108,4,26);String dia26String=dia26.toString();
    java.sql.Date dia27 =new Date(108,4,27);String dia27String=dia27.toString();java.sql.Date dia28 =new Date(108,4,28);String dia28String=dia28.toString();
    java.sql.Date dia29 =new Date(108,4,29);String dia29String=dia29.toString();java.sql.Date dia30 =new Date(108,4,30);String dia30String=dia30.toString();
    java.sql.Date dia31 =new Date(108,4,30);String dia31String=dia31.toString();
    %>
<TR BGCOLOR="#FF8000" ALIGN="RIGHT" VALIGN="TOP">
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia1String%>">1</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia2String%>">2</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia3String%>">3</a><BR><BR></FONT></TD>
</TR>
<TR BGCOLOR="#FF8000" ALIGN="RIGHT" VALIGN="TOP">
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia4String%>">4</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia5String%>">5</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia6String%>">6</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia7String%>">7</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia8String%>">8</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia9String%>">9</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia10String%>">10</a><BR><BR></FONT></TD>
</TR>
<TR BGCOLOR="#FF8000" ALIGN="RIGHT" VALIGN="TOP">
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia11String%>">11</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia12String%>">12</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia13String%>">13</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia14String%>">14</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia15String%>">15</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia16String%>">16</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia17String%>">17</a><BR><BR></FONT></TD>
</TR>
<TR BGCOLOR="#FF8000" ALIGN="RIGHT" VALIGN="TOP">
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia18String%>">18</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia19String%>">19</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia20String%>">20</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia21String%>">21</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia22String%>">22</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia23String%>">23</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia24String%>">24</a><BR><BR></FONT></TD>
</TR>
<TR BGCOLOR="#FF8000" ALIGN="RIGHT" VALIGN="TOP">
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia25String%>">25</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia26String%>">26</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia27String%>">27</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia28String%>">28</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia29String%>">29</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia30String%>">30</a><BR><BR></FONT></TD>
<TD><FONT FACE="Verdana" SIZE="1" COLOR="#000000"><a href="../MuestraCitasDelDia.jsp?fecha=<%=dia31String%>">31</a><BR><BR></FONT></TD>
</TR>
<TR BGCOLOR="#FF8000" ALIGN="RIGHT" VALIGN="TOP">
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
  <TD BGCOLOR="#808080"><FONT FACE="Verdana" SIZE="1">&nbsp;<BR></TD>
</TR>
</TABLE>
</P>

</FONT>
</BODY></div>
<%@ include file="../templatecalendario/cerrarsesion.html" %>
</HTML>    