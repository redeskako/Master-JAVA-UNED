package servlets;
import java.io.*;
import java.net.*;
import java.util.*;	
import javax.servlet.*;
import javax.servlet.http.*;

public class Validador extends HttpServlet{
	
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String resultado="<b>Datos correctos</b>";
	    Enumeration<String>nombres=request.getParameterNames();
	    while(nombres.hasMoreElements()){
	    	 if(request.getParameter(nombres.nextElement()).equals("")){
	    		 resultado="<b>Error en los datos. ";
	    		 resultado+="Debe rellenar todos los campos </b>";
	    	 }
	     }

	     out.println(resultado);
	     out.close();
	}
	
	
}
