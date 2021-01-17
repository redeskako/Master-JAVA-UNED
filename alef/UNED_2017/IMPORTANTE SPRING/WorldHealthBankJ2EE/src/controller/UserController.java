package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CountryDao;
import dao.DataDao;
import dao.UserDao;
import model.Data;
import model.User;

/**
 * Servlet implementation class UserController
 */
//@WebServlet(name = "/UserController",urlPatterns = {"/users"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static String LIST_USER = "/listUser.jsp";
    private UserDao dao;
    private DataDao dataDao;

    public UserController() {
        super();
        dao = new UserDao();
        dataDao = new DataDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

            forward = LIST_USER;
            
            User usuario = new User();
            usuario.setUser("luis");
            usuario.setPassword("root");
            
            System.out.println(dao.getSession(usuario));
            List<Data> listaData = dataDao.getDataPagination(30, 10);
            int i=0;
            for(Data dato : listaData){
            	System.out.println(i+"  "+dato);
            	i++;
            }
            
         
            
            
        //} 

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
