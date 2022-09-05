package es.uned.sw_cliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uned.sw.CuentasStub;
import es.uned.sw.CuentasStub.Sumar;
import es.uned.sw.CuentasStub.SumarResponse;

/**
 * Servlet implementation class SumarCliente
/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */
public class SumarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SumarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		int a = Integer.parseInt(request.getParameter("suma_a"));
		int b = Integer.parseInt(request.getParameter("suma_b"));
			
		CuentasStub cs = new CuentasStub();

		Sumar s = new Sumar();
		s.setA(a);
		s.setB(b);
		
		SumarResponse resp = cs.sumar(s);
		String resultado = resp.get_return();
		
		
		System.out.println("CLIENTE: El resultado es " + resultado);
			
		
		request.setAttribute("resultado", resultado);
		
		// Se re-dirige a la página jsp:
		RequestDispatcher rd1 = request.getRequestDispatcher("resultado.jsp");
		rd1.forward(request, response);			
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
