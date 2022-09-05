package configuracion;

import java.io.IOException;
import rba.bbdd.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class for Servlet: ProcesaLogado
 *
 */
public class ProcesaLogado extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */

    public ProcesaLogado() {
        super();
    }

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos los datos del usuario del request
        // usuario/password/submit
        RequestDispatcher requestDispatcher = null;

        String user = request.getParameter("usuario");
        String pass = request.getParameter("password");

        //En logado tendré si se ha logado correctamente.Utilizo el método
        //logarse de la clase Usuario que se encuentra en rba.bbdd
        boolean logado = false;
        try {
            logado = Usuario.logarse(user, pass);
            if (logado) {
                //Si todo OK Enviamos una página por out de todo correcto con el IDSEsion creado.               
                HttpSession sesion;
                String ruta;
                //Recupera la cookie de sesion para mostrarla.
                sesion = request.getSession();
                sesion.setAttribute("comprobarSesion", sesion.getId());
                //String idioma = Usuario.idioma(user, pass);
                //sesion.setAttribute("idiomaPagina", idioma);
                ruta = request.getContextPath();
                sesion.setAttribute("ruta", ruta);
                //A�adimos los datos del usuario que se acaba de conectar.
                sesion.setAttribute("usuario", user);
                //enviaria a la carpeta zonaPrivada
                requestDispatcher = request.getRequestDispatcher(response.encodeURL("zonaPrivada/menu.jsp"));

            } else {
                // Si NO OK Envio una página por out de ERROR con un link a la index.html
                request.setAttribute("valido", -1);
                requestDispatcher = request.getRequestDispatcher(response.encodeURL("index.jsp"));

            }
        } catch (RbaException e) {

            request.setAttribute("error", "problemas de servidor");
            requestDispatcher = request.getRequestDispatcher(response.encodeURL("error.jsp"));


        }
        requestDispatcher.forward(request, response);
    }
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doService(request, response);
    }

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doService(request, response);
    }
}
