<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "dao.DataDao, model.Data, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="data/estiloData.css"/>
<script src="jquery-3.2.1.min.js"></script>
<title>Data</title>
<%
	HttpSession sesion = request.getSession(true); 
%>
</head>
<body>
<div >
<%
        List<Data> listData = (List<Data>)sesion.getAttribute("datas");

		int nPaginas = (int)sesion.getAttribute("numeroPaginas");
		int paginaActual = (int)sesion.getAttribute("paginaActual");
        //for(Data data: listData)
        	//System.err.println(listData);
        %>
        <a id="boton" href="data?action=edit&Indicador=&code=&year=">Añadir</a>
        
<table id="contenido" class="tabla" border=1>
        <thead>
            <tr class="header">
                <td>Indicador Code</td>
                <td>Country Code</td>
                <td>Year</td>
                <td>Percentage</td>
                <td>Editar</td>
                <td>Borrar</td>
            </tr>
        </thead>
        <tbody>
            	<% for (Data data : listData) { %>
                <tr>
                	<td><% out.print(data.getIndicador_code()); %></td>
					<td><% out.print(data.getCountry_code()); %></td>
					<td><% out.print(data.getYear()); %></td>
					<td><% out.print(data.getPercentage()); %></td>
					<td><a href="data?action=edit&Indicador=<%out.print(data.getIndicador_code());%>&code=<%out.print(data.getCountry_code());%>&year=<%out.print(data.getYear());%>">
						<img src="img/edit.png" height="35" width="35">
						</a></td>
					<td><a href="data?action=delete&Indicador=<%out.print(data.getIndicador_code());%>&code=<%out.print(data.getCountry_code());%>&year=<%out.print(data.getYear());%>">
							<img src="img/Delete-icon.png" height="35" width="35">
						</a></td>
				</tr>
                <%} %>
            
        </tbody>
        
    </table>
    
    <%if(paginaActual<3){
        	%><div id="pag">
        	<p>pag: 
        	
        	<a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=1" name="pag" value="1">1</a>
        	<a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=2" name="pag" value="2">2</a>
        	<a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=3" name="pag" value="3">3</a>
        	...
        	<a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(nPaginas);%>" name="pag" value="<%out.print(nPaginas);%>"><%out.print(nPaginas);%></a>
        	<a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual+1);%>" name="pag" value="<%out.print(paginaActual+1);%>">>></a></p>
        	<%
        }else if(paginaActual >= nPaginas-1){
        	 %>
             <p>pag: 
             <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual-1);%>" name="pag" value="<%out.print(paginaActual-1);%>"><<</a>
             <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=1" name="pag" value="1">1</a>
             ...
             <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(nPaginas-2);%>" name="pag" value="<%out.print(nPaginas-2);%>"><%out.print(nPaginas-2);%></a>
             <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(nPaginas-1);%>" name="pag" value="<%out.print(nPaginas-1);%>"><%out.print(nPaginas-1);%></a>
             <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(nPaginas);%>" name="pag" value="<%out.print(nPaginas);%>"><%out.print(nPaginas);%></a></p>
             
             <%
    
    
		}else{
        %>
        <p>pag: 
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual-1);%>" name="pag" value="<%out.print(paginaActual-1);%>"><<</a>
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=1" name="pag" value="1">1</a>
        ...
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual-1);%>" name="pag" value="<%out.print(paginaActual-1);%>"><%out.print(paginaActual-1);%></a>
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual);%>" name="pag" value="1"><%out.print(paginaActual);%></a>
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual+1);%>" name="pag" value="<%out.print(paginaActual+1);%>"><%out.print(paginaActual+1);%></a>
        ...
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(nPaginas);%>" name="pag" value="<%out.print(nPaginas);%>"><%out.print(nPaginas);%></a>
        <a href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=<%out.print(paginaActual+1);%>" name="pag" value="<%out.print(paginaActual+1);%>">>></a></p>
        
        <%
        }
        %>
        </div>
        
        </div>
        
        <script type="text/javascript">


var x = document.getElementById('contenido');
    x.style.display = 'none';
   
    <%
    if(sesion.getAttribute("usuarioAcceso").equals("admin")){%>
    	x.style.display ="block";;<%
    }else{ %>
    	console.log("hola2");
    	x.style.display ="none";
    	<%
    }
    %>

</script>
        
        
        
        

</body>
</html>