package com.prueba.tomcat;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: MiPrimerServlet
 *
 */
 public class MiPrimerServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MiPrimerServlet() {
		super();
	}   	
	
		private void ejecuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String submit= request.getParameter("submit");
			PrintWriter pw=null;

			if (submit==null){
				try{
					String valor=request.getParameter("prueba");
					//System.out.println("Prueba:"+valor);
					pw= response.getWriter();
					pw.print("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
					pw.print("<html>");
					pw.print("<head>");
					pw.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
					pw.print("<title>Insert title here</title>");
					pw.print("</head>");
					pw.print("<body>");
					pw.print("<center><h1>Necesita antes ir al formulario</h1></center>");
					pw.print("<a href='index.html'>en la p&aacute;gina principal</a>");
					pw.print("</body>");
					pw.print("</html>");
				}catch(Exception e){
					
				}finally{
					if (pw !=null){
						pw.close();
					}
				}
			}else{
				pw=null;
				try{
					String valor=request.getParameter("prueba");
					//System.out.println("Prueba:"+valor);
					pw= response.getWriter();
					pw.print("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
					pw.print("<html>");
					pw.print("<head>");
					pw.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
					pw.print("<title>Insert title here</title>");
					pw.print("</head>");
					pw.print("<body>");
					pw.print("<center><h1>Prueba de primer Servlet</h1></center>");
					pw.print("El valor de prueba es "+ valor.toString());
					pw.print("</body>");
					pw.print("</html>");
				}catch(Exception e){
					
				}finally{
					if (pw !=null){
						pw.close();
					}
				}
			}
		}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ejecuta(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ejecuta(request,response);
	}   	  	    
}