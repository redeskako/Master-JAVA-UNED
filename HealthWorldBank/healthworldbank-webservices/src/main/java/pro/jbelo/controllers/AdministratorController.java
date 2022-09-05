package pro.jbelo.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pro.jbelo.persistence.CountryEntity;
import pro.jbelo.persistence.HibernateUtil;

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


        String operation = req.getParameter("operation");
       if(operation != null && operation.equals("new-country")){
           String id = req.getParameter("country-id");
           String name = req.getParameter("country-name");
           System.out.println("valores son " + id + "  " + name );

           CountryEntity country = new CountryEntity();
           country.setCountryName(name);
           country.setCountryCode(id);
           SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
           Session session = sessionFactory.openSession();

           session.getTransaction().begin();
           session.save(country);
           session.getTransaction().commit();

           session.close();






        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main/console/admin.jsp");
        requestDispatcher.forward(req,resp);
    }
}
