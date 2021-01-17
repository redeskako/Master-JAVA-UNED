/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.jms.*;
import ejb.*;

/**
 *
 * @author carlosl.sanchez
 */
public class PostMessage extends HttpServlet {
    @Resource(mappedName="jms/NoticiaMensajeFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName="jms/NoticiaMensaje")
    private Queue cola;
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head></head>");
        out.println("<body>");
        out.println("Servlet PostMessage at " + request.getContextPath() + "</h1>");

        // El siguiente código se añade a la página para añadir nueva noticia
        out.println("<form method='get'>");
        out.println("Noticia: <input type='text' name='noticia'><br/>");
        out.println("Descripci&oacute;n: <textarea name='descripcion'></textarea><br/>");
        out.println("<input type='submit'><br/>");
        out.println("</form>");


        String title=request.getParameter("noticia");
        String body=request.getParameter("descripcion");
        if ((title!=null) && (body!=null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(cola);

                ObjectMessage message = session.createObjectMessage();
                // qué crearemos el NoticiasEntidad, que será enviado por el JMS
                NoticiaEntidad e = new NoticiaEntidad();
                e.setNoticia(title);
                e.setDescripcion(body);

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
                response.sendRedirect("ListaNoticias");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
        out.println("</body></html>");
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
