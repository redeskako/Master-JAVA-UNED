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
                        String tipoUsuario= (String) sesion.getAttribute("AtributoTipoUsuario");

                        String accion=(String)request.getParameter("accion");
                        if(accion!=null){
                                if (accion.equals("anadircita")){
                                	String  fechadeldia=(String)request.getParameter("fechadeldia");
                                	String  turno=(String)request.getParameter("turno");
                                	sesion.setAttribute("AtributoTurno", turno);
                                    sesion.setAttribute("AtributoFecha", fechadeldia);
                                    if (tipoUsuario.equals("cliente")){
                                    	response.sendRedirect("RegistraCitaCliente.jsp");
                                    }else{
                                    	response.sendRedirect("RegistraCita.jsp");
                                    }	
                                }
                                else if(accion.equals("registraCita")){
                                        if ((sesion.getAttribute("AtributoFecha")==null)){
                                            response.sendRedirect("Bienvenida.jsp?valido=-4");
                                        }
                                        else{
                                        		String fecha=(String)sesion.getAttribute("AtributoFecha");
                                                try{
                                                    String dni= request.getParameter("dni");
                                                    String tipoServicio= request.getParameter("tipoServicio");
                                                    String turno= request.getParameter("turno");
                                                    java.sql.Date variabletemporal=new Date(108,7,21);
                                                    Date dia=variabletemporal.valueOf(fecha);
                                                    Cita cita=new Cita();
                                                    cita.setDni(dni);cita.setFecha(dia);
                                                    cita.setTipoServicio(tipoServicio);cita.setTurno(turno);
                                                    cita.setActivo("activo");
                                                    //comprobamos si queda algun turno libre y que ese turno no
                                                    //est� asignado
                                                    if(Cita.JornadaNoEsCompleta(dia)&(Cita.NoEstaTurnoReservado(dia,turno))){
                                                    	Cita.registracita(cita);
                                                    	response.sendRedirect("Confirmar.jsp");
                                                    }
                                                    else{
                                                    	response.sendRedirect("CitaNoValida.jsp");
                                                    }
                                                    //Habria que poner una pagina de no confirmación, en el caso
                                                    //de que la jornada esta completa, o el turno está cogido

                                                }catch(NumberFormatException e){
                                                    System.out.println("ERROR.Ingreso de numero de codigo incorrecto");
                                                    response.sendRedirect("CitaNoValida.jsp");
                                                }

                                        }
                                }
                                else if(accion.equals("registraCitaCli")){
                                	String fecha=(String)sesion.getAttribute("AtributoFecha");
                                	String dniSesion= (String) sesion.getAttribute("Atributodni");
                                	String turno= (String)sesion.getAttribute("AtributoTurno");
                                	String tipoServicio= request.getParameter("tipoServicio");
                                	java.sql.Date variabletemporal=new Date(108,7,21);
                                    Date dia=variabletemporal.valueOf(fecha);
                                    Cita cita=new Cita();
                                    cita.setDni(dniSesion);cita.setFecha(dia);
                                    cita.setTipoServicio(tipoServicio);cita.setTurno(turno);
                                    cita.setActivo("activo");
                                    System.out.println(cita);
                                    //comprobamos si queda algun turno libre y que ese turno no
                                    //est� asignado
                                    if(Cita.JornadaNoEsCompleta(dia)&(Cita.NoEstaTurnoReservado(dia,turno))){
                                    	Cita.registracita(cita);
                                    	response.sendRedirect("Confirmar.jsp");
                                    }
                                    else{
                                    	response.sendRedirect("CitaNoValida.jsp");
                                    }
                                	
                                }
                                else if(accion.equals("editarCita")){
                                	String  fechadeldia=(String)request.getParameter("fechadeldia");
                                    sesion.setAttribute("AtributoFecha", fechadeldia);
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
                                    String fecha=(String)sesion.getAttribute("AtributoFecha");
                                    java.sql.Date variabletemporal=new Date(108,7,21);
                                    Date dia=variabletemporal.valueOf(fecha);
                                    if(Cita.JornadaNoEsCompleta(dia)&(Cita.NoEstaTurnoReservado(dia,turno))){
                                    	 Cita.editacita(cod,tipoServicio,turno);
                                    	response.sendRedirect("Confirmar.jsp");
                                    }
                                    else{
                                    	response.sendRedirect("CitaNoValida.jsp");
                                    }
                                }
                                else if(accion.equals("borrarCita")){
                                    String  codig=(String)request.getParameter("codigocita");
                                    int cod=Integer.parseInt(codig);
                                    Cita.eliminacita(cod);
                                    response.sendRedirect("Confirmar.jsp");
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
