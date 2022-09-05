package com.tomcat.pruebas;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/**
 * Servlet implementation class for Servlet: Usuarios
 *
 */
 public class Usuarios extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Usuarios() {
		super();
	} 
	
	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	    public void doGet (HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
		{

	    	String url   = "jdbc:mysql://localhost:3306/usuarios";

	    	String query = "SELECT * FROM Usuarios order by Nombre ";
							
		try {

			Class.forName  ("com.mysql.jdbc.Driver");
	      		//Class.forName("org.gjt.mm.mysql.Driver");

	      		Connection con = DriverManager.getConnection 
			  ( url, "usuario", "usuario" );

	            Statement stmt = con.createStatement ();

	            ResultSet rs = stmt.executeQuery (query);

	            printResultSet ( resp, rs );

	            rs.close();
	            stmt.close();
	            con.close();

	        }  // end try

	        catch (SQLException ex) {
	            
			PrintWriter out = resp.getWriter();
		        resp.setContentType("text/html");
				
			while (ex != null) {  
	                	out.println ("Error SQL:  " + ex.getMessage ());
	                	ex = ex.getNextException ();  
	              }  // end while

	        }  // end catch SQLException

	        catch (java.lang.Exception ex) {

	      	PrintWriter out = resp.getWriter();
			resp.setContentType("text/html");	
			out.println ("Error:  " + ex.getMessage ());
		  }

	    }  // end doGet


	    private void printResultSet ( HttpServletResponse resp, ResultSet rs )
	        throws SQLException  {

	        try  {

			PrintWriter out = resp.getWriter();

		        out.println("<html>");
		        out.println("<head><title>Servelt jdbc/mysql</title></head>");
		        out.println("<body>");
		        out.println("<center><font color=AA0000>");
		        out.println("<h3>Usuarios</h3>");
		        out.println("<h3>Datos Recogidos:</h3>");
		        
		        out.println("<table border='1'>");

	           	int numCols = rs.getMetaData().getColumnCount ();
	                while ( rs.next() ) {
			  out.println("<tr>");
	               	  for (int i=1; i<=numCols; i++) {
	                    out.print("<td>" + rs.getString(i) + "</td>" );
	                  }  // end for
	                  out.println("</tr>");
	                }  // end while

		        out.println("</table>");
		        
		        out.println("</font></center>");
		        out.println("</body>");
		        out.println("</html>");
		        out.close();

		    }  // end try
	        catch ( IOException except)  {
	        }  // end catch

	    }  // end returnHTML

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(request, response);
	}   	  
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}   
}