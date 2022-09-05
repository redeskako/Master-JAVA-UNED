package es.rizos.ServletsControladores;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rizos.beansUsuario.*;
import es.rizos.beansClientes.*;
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
                        		if (accion.equals("meterCitasSesion")){
                        			String dniSesion= (String) sesion.getAttribute("Atributodni");
                        			String lafecha=(String)request.getParameter("fecha");
                        			java.sql.Date variabletemporal=new Date(108,7,21);
                                    Date dia=variabletemporal.valueOf(lafecha);
                                    Cita cita=new Cita();
                        			Vector citasdeldia=cita.citasDelDia(dia);
                        			Vector citasdeldiacliente=cita.citasDelDia(dia,dniSesion);
                        			sesion.setAttribute("citasdeldia",citasdeldia);
                        			sesion.setAttribute("citasdeldiacliente",citasdeldiacliente);
                        			response.sendRedirect("MuestraCitasDelDia.jsp?fecha="+lafecha);
                        			Vector turnosNoReservados=new Vector();
                        			for (int i=1; i<=5;i++){
                        				String turno=("Turno"+i);
                        				String turnoen=("Shift"+i);
                        				String idiomaString= (String) sesion.getAttribute("idioma");
                        					if (idiomaString.equals("es_ES")){
                        						if (Cita.NoEstaTurnoReservado(dia,turno)&&(Cita.NoEstaTurnoReservado(dia,turnoen))){
                        							turnosNoReservados.add(turno);
                        						}
                        					}else{
                        						if (Cita.NoEstaTurnoReservado(dia,turnoen)&&(Cita.NoEstaTurnoReservado(dia,turno))){
                        							turnosNoReservados.add(turnoen);
                        						}
                        					}
                        			}
                        			sesion.setAttribute("turnosNoReservados",turnosNoReservados);
                        		}
                        		if (accion.equals("anadircita")){
                                	String  fechadeldia=(String)request.getParameter("fechadeldia");
                                	String  turno=(String)request.getParameter("turno");
                                	sesion.setAttribute("AtributoTurno", turno);
                                    sesion.setAttribute("AtributoFecha", fechadeldia);
                                    if (tipoUsuario.equals("cliente")){
                                    	response.sendRedirect("RegistraCitaCliente.jsp?fecha="+fechadeldia);
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
                                                    Clientes auxcli=new Clientes();
                                                    if(auxcli.existeDNI(dni)&&Cita.JornadaNoEsCompleta(dia)&(Cita.NoEstaTurnoReservado(dia,turno))){
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
                                    //comprobamos si queda algun turno libre y que ese turno no
                                    //est� asignado
                                    if(Cita.JornadaNoEsCompleta(dia)&(Cita.NoEstaTurnoReservado(dia,turno))){
                                    	Cita.registracita(cita);
                                    	int totalpaginas= Cita.calculaTotalPaginas(dniSesion);
                                    	sesion.setAttribute("paginasCitasCliente",totalpaginas);
                                    	Cita citaux=new Cita();
                                    	Vector citasdelusuario=citaux.citasUsuario(dniSesion,0);
                                    	sesion.setAttribute("AtributoCitas", citasdelusuario);
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
                                    String dniSesion= (String) sesion.getAttribute("Atributodni");
                                    int cod=Integer.parseInt(codigo);
                                    String tipoServicio= request.getParameter("tipoServicio");
                                    String turno= request.getParameter("turno");
                                    String fecha=(String)sesion.getAttribute("AtributoFecha");
                                    java.sql.Date variabletemporal=new Date(108,7,21);
                                    Date dia=variabletemporal.valueOf(fecha);
                                    if(Cita.JornadaNoEsCompleta(dia)&(Cita.NoEstaTurnoReservado(dia,turno))){
                                    	 Cita.editacita(cod,tipoServicio,turno);
                                    	 Cita citaux=new Cita();
                                    	 Vector citasdelusuario=citaux.citasUsuario(dniSesion,0);
                                    	 sesion.setAttribute("AtributoCitas", citasdelusuario);
                                    	 response.sendRedirect("Confirmar.jsp");
                                    }
                                    else{
                                    	response.sendRedirect("CitaNoValida.jsp");
                                    }
                                }
                                else if(accion.equals("borrarCita")){
                                    String  codig=(String)request.getParameter("codigocita");
                                    String dniSesion= (String) sesion.getAttribute("Atributodni");
                                    int cod=Integer.parseInt(codig);
                                    Cita.eliminacita(cod);
                                    Cita citaux=new Cita();
                                    Vector citasdelusuario=citaux.citasUsuario(dniSesion,0);
	                               	sesion.setAttribute("AtributoCitas", citasdelusuario);
                                    response.sendRedirect("Confirmar.jsp");
                                }
                        		if(accion.equals("estadistica")){
                        			TipoServicio tpser=new TipoServicio();
                        			ArrayList<TipoServicio> arltipo= new ArrayList<TipoServicio>();


                                    int totalcitas=TipoServicio.calculoTotalCitas();
                                    int totaltpserv=TipoServicio.totalCitasTipoServicio("Corte");
                        			double porcentaje=tpser.calculoPorcentaje(totalcitas,totaltpserv);

                        			tpser.TipoServicio("Corte", porcentaje, 15,"blue");
                        			arltipo.add(tpser);

                        			int totalcitas1=TipoServicio.calculoTotalCitas();
                        			int totaltpserv1=TipoServicio.totalCitasTipoServicio("Tinte");
                        			double porcentaje1=tpser.calculoPorcentaje(totalcitas1,totaltpserv1);
                        			tpser=new TipoServicio();
                        			tpser.TipoServicio("Tinte", porcentaje1, 25,"red");
                        			arltipo.add(tpser);

                        			int totalcitas2=TipoServicio.calculoTotalCitas();
                        			int totaltpserv2=TipoServicio.totalCitasTipoServicio("Mechas");
                        			double porcentaje2=tpser.calculoPorcentaje(totalcitas2,totaltpserv2);
                        			tpser=new TipoServicio();
                        			tpser.TipoServicio("Mechas", porcentaje2, 35,"green");
                        			arltipo.add(tpser);

                        			int totalcitas3=TipoServicio.calculoTotalCitas();
                        			int totaltpserv3=TipoServicio.totalCitasTipoServicio("Alisado Japones");
                        			double porcentaje3=tpser.calculoPorcentaje(totalcitas3,totaltpserv3);
                        			tpser=new TipoServicio();
                        			tpser.TipoServicio("Alisado Japones", porcentaje3,200,"orange");
                        			arltipo.add(tpser);

                           			int totalcitas4=TipoServicio.calculoTotalCitas();
                        			int totaltpserv4=TipoServicio.totalCitasTipoServicio("Extensiones");
                        			double porcentaje4=tpser.calculoPorcentaje(totalcitas4,totaltpserv4);
                        			tpser=new TipoServicio();
                        			tpser.TipoServicio("Extensiones", porcentaje4, 100,"white");
                        			arltipo.add(tpser);

                        			sesion.setAttribute("tiposervicios",arltipo);
                                    response.sendRedirect("Estadisticas.jsp");


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