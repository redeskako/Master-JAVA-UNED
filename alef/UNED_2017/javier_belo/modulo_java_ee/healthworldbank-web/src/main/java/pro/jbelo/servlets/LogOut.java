package pro.jbelo.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet encargado de realizar el logout. Dependiendo del contenedor hacer un session invalidate podría no
 * so ser suficiente para realizar el logout así que se debe de hacer invalidat() y logout()
 * @author jbelo
 * @date 2017 05.
 */
@WebServlet(name = "LogOutServlet", urlPatterns = {"/main/logout"})
@ServletSecurity(httpMethodConstraints = {
    @HttpMethodConstraint(
            value = "POST",
            rolesAllowed = {"USER","ADMIN"},
            transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
})
public class LogOut extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.logout();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }

}
