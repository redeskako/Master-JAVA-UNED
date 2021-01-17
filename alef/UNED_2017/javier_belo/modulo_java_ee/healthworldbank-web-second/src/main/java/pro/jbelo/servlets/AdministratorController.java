package pro.jbelo.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controlador par a admin.jsp
 * @author jbelo
 * @date 2017 04.
 */
@WebServlet("/main/console/administrator")
public class AdministratorController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main/console/admin.jsp");
        requestDispatcher.forward(req,resp);
    }
}
