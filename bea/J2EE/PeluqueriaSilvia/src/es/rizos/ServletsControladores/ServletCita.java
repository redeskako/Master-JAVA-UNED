package es.rizos.ServletsControladores;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rizos.beansUsuario.*;
import es.rizos.librerias.*;
import es.rizos.beansCitas.*;
import java.sql.Date;


//import java.sql.*;

/**
 * Servlet implementation class for Servlet: ProcesaLogado
 *
 */
 public class ServletCita extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletCita() {
		super();
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
                    	HttpSession sesion= request.getSession();
                        sesion.setAttribute("AtributoSesionIniciada", sesion.getId());
                        String accion=(String)request.getParameter("accion");
                        if(accion!=null){
                                if (accion.equals("anadircita")){
                                	System.out.println("fechadeldia");
                                    String  fechadeldia=(String)request.getParameter("fechadeldia");
                                    sesion.setAttribute("AtributoFecha", fechadeldia);
                                    
                               
                                    response.sendRedirect("RegistraCita.jsp");
                                }
                                else if(accion.equals("registraCita")){
                                        if ((sesion.getAttribute("AtributoFecha")==null)){
                                            response.sendRedirect("GestionaAdmin.jsp");
                                        }
                                        else{
                                                String fecha=(String)sesion.getAttribute("AtributoFecha");
                                                System.out.println(fecha);
                                                String codigocita= request.getParameter("codigocita");
                                                try{
                                                    int cod=Integer.parseInt(codigocita);
                                                    String dni= request.getParameter("dni");
                                                    String tipoServicio= request.getParameter("tipoServicio");
                                                    String turno= request.getParameter("turno");
                                                    java.sql.Date variabletemporal=new Date(108,7,21);
                                                    Date dia=variabletemporal.valueOf(fecha);
                                                    Cita cita=new Cita();
                                                    cita.setCodigoCita(cod);cita.setDni(dni);cita.setFecha(dia);
                                                    cita.setTipoServicio(tipoServicio);cita.setTurno(turno);
                                                    cita.setActivo("activo");
                                                    Cita.registracita(cita);
                                                    
                                                    System.out.println(cita);
                                                    response.sendRedirect("GestionaAdmin.jsp");
                                                }catch(NumberFormatException e){
                                                    System.out.println("ERROR.Ingreso de n�mero de c�digo inv�lido");
                                                    response.sendRedirect("GestionaAdmin.jsp");
                                                }

                                        }
                                }
                                else if(accion.equals("editarCita")){
                                    String  codig=(String)request.getParameter("codigocita");
                                    int cod=Integer.parseInt(codig);
                                    sesion.setAttribute("AtributoCodigo", codig);
                                    response.sendRedirect("EditaCita.jsp");

                                }
                                else if(accion.equals("modificaCita")){
                                    String codigo=(String)sesion.getAttribute("AtributoCodigo");
                                    int cod=Integer.parseInt(codigo);
                                    String tipoServicio= request.getParameter("tipoServicio");
                                    String turno= request.getParameter("turno");
                                    Cita.editacita(cod,tipoServicio,turno);
                                    response.sendRedirect("GestionaAdmin.jsp");
                                }
                                else if(accion.equals("borrarCita")){
                                    String  codig=(String)request.getParameter("codigocita");
                                    int cod=Integer.parseInt(codig);
                                    Cita.eliminacita(cod);
                                    response.sendRedirect("GestionaAdmin.jsp");
                                }
                        }
			else{
				// Si NO OK Envio una página por out de ERROR con un link a la index.html
				response.sendRedirect("GestionaAdmin.jsp");
			}
		}catch(ErrorRizosBd e){
			response.sendRedirect("error.jsp?error=Error en la conexion a la Base Datos");

		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request,response);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request,response);
	}

}
