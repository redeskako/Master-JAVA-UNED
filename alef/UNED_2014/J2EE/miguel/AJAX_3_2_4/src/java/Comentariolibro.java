/*
 * Comentariolibro.java
 *
 * Created on 9 de abril de 2007, 15:27
 */
package java;
import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class Comentariolibro extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //comentarios
        String [] comentarios=
        {"Requiere conocimientos basicos de programacion orientada a objetos",
        "Puede construir facilmente aplicaciones para la Web",
        "Aprendera rapidamente los principales trucos de Ajax",
        "Introduce las principales tecnologias de la plataforma"};
        //precios
        String [] precios={"23.5","31.4","32.0","27.5"};
        int seleccionado=Integer.parseInt(request.getParameter("tit"));
        //formación del documento XML de respuesta
        String textoXML="<?xml version='1.0'?>";
        textoXML+="<libro>";
        textoXML+="<comentario>"+comentarios[seleccionado]+"</comentario>";
        textoXML+="<precio>"+precios[seleccionado]+"</precio>";
        textoXML+="</libro>";
        out.println(textoXML);
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
