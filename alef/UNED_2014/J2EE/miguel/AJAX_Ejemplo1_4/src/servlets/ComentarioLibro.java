package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ComentarioLibro extends HttpServlet {

	protected void service (HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		
		// Se indica que la respuesta va a ser texto plano
		response.setContentType("text/plain");
		PrintWriter out=response.getWriter();
		String [] comentarios= {"Comentario 1", "Comentario 2", "Comentario 3", "Comentario4"};
		int sel;
		sel=Integer.parseInt(request.getParameter("tit"));
		out.println(comentarios[sel]);
		out.close();	
		
				
	} // fin metodo service
	
} // fin ComentarioLibro
