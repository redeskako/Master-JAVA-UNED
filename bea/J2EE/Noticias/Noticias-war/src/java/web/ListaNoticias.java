/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import ejb.NoticiaEntidadFacadeLocal;
import ejb.NoticiaEntidad;
import java.io.*;
import java.net.*;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author carlosl.sanchez
 */
public class ListaNoticias extends HttpServlet {
    @EJB
    private NoticiaEntidadFacadeLocal noticiaEntidadFacade;
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaNoticias</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListaNoticias  en" + request.getContextPath () + "</h1>");
            List<NoticiaEntidad> noticias= noticiaEntidadFacade.findAll();
            for (Iterator it= noticias.iterator(); it.hasNext();){
                NoticiaEntidad elem= (NoticiaEntidad) it.next();
                out.println(" <b>"+elem.getNoticia()+"</b><br />");
                out.println(elem.getDescripcion()+"<br />");
            }
     
            out.println("<a href='PostMessage'>Nuevo mensaje</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
