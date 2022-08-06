package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CountryDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "/CountryController", urlPatterns = {"/Countrys"})
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private CountryDao dao;

    public CountryController() {
        super();
        dao = new CountryDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        //if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            
            request.setAttribute("users", dao.getAllCountry());
            
        //} 

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
