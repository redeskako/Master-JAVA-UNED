package org.Servlet;
import org.BBDD.*;
import org.Otros.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Actualizador
 */
@WebServlet("/Actualizador")
public class Actualizador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Actualizador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioAct(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioAct(request, response);
	}
	
	protected void doServicioAct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try {

			// OBTENEMOS LA SESION RADIO PORQUE ES LA VARIABLE QUE NOS VA A INDICAR CUANTOS REGISTROS TIENE LA CONSULTA
		
			HttpSession sesion= request.getSession();
			int radio=(Integer) sesion.getAttribute("radio");
			
			String control;
			// LA VARIABLE i INDICA EN QUÉ POSICIÓN DE LA TABLA SE ENCUENTRA, NO TIENE POR QUÉ COINCIDIR CON EL ID
			int i=0;
			int j=1;

			Boolean verificar=false;
			System.out.println(radio);
			
			// LOCALIZAMOS SI EXISTE UN RADIOBUTTOM ACTIVADO
			while(i<radio)
			{
				
				control=String.valueOf(request.getParameter("radioact"+i));

			
				System.out.println(control);
				if (control.equals("on"))
				{


					
					BBDD mibd=new BBDD();


					
		
					 // J es la variable que recoge el resultado de la función que da el nº de id
					 

						
					j=mibd.obtenerId(i);
					
					// CREAMOS SESIÓN CON EL Nº DE ID
					
					sesion.setAttribute("idAct", j);
							
					System.out.println("El registro a Actualizar es el numero: "+ j);
					
					//COLOCAMOS VERDADERA LA VARIABLE BBOLEANA PARA INDICAR QUE SÍ HABÍA UN RADIONUTTOM ACTIVADO
					verificar=true;
					// NOS SALIMOS
					break;
					
				}//fin if
				
				i++;
				
			}// fin while
				
				//VERIFICAMOS SI HABIA ALGUN RADIOBUTTOM MARCADO MEDIANTE LA VARIABLE BOOLEANA VERIFICAR

				if (verificar==false)
					{response.sendRedirect("actualizador.jsp");}
				else
					//PASAMOS EL Nº DE ID
					{response.sendRedirect("actualizar.jsp?id="+j);}

			}//fin try
		
		
		catch(Exception e)
		{
			throw new BBDDException("Error al en Metodo DoServicioAct: " + e.getMessage() );
		}//fin catch
				
		
	}
	

}
