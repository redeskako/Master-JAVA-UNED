/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracion;

import java.io.*;
import java.net.*;

import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Alef'08, Servlet para el idioma, para elegir en la pantalla principal
 */
public class Idioma extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String INGLES = "eng";
        String ESPANOL = "esp";
        HttpSession sesion = null;
        ArrayList parametros = null;
        ArrayList valores;
        String idioma;
        String action = request.getParameter("action");
        sesion = request.getSession();

        if (action.equals(ESPANOL)) {

            sesion.setAttribute("idiomaPagina","es_ES");
            response.sendRedirect("index.jsp");
            
        } else if (action.equals(INGLES)) {
            sesion.setAttribute("idiomaPagina","en_EN");
            response.sendRedirect("index.jsp");
           
        } 
            sesion.removeAttribute("datosParametros");
            sesion.removeAttribute("datosValores");
        

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
