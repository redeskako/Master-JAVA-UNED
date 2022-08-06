package es.rizos.ServletsControladores;

import java.sql.Date;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.rizos.beansUsuario.*;
import es.rizos.beansCitas.*;
import es.rizos.librerias.*;

 public class ProcesaLogado2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ProcesaLogado2() {
		super();
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion= request.getSession();
		//Desactivamos las citas que sean anteriores a la fecha actual
		//============================================================
		java.util.Calendar fecha = java.util.Calendar.getInstance();
		String fechaString=(fecha.get(java.util.Calendar.YEAR) + "-" + (fecha.get(java.util.Calendar.MONTH)+1) + "-" + fecha.get(java.util.Calendar.DATE));
		sesion.setAttribute("MesActual", fecha.get(java.util.Calendar.MONTH)+1);
		java.sql.Date variabletemporal=new Date(108,7,21);
		Date dia=variabletemporal.valueOf(fechaString);
		Cita.limpiaCitas(dia);
		//==========================================================
		//Controlamos la pagina que tenemos que representar
        //=================================================
        int inicio=0;
        try{
            String cad= request.getParameter("paginado");
            inicio= Integer.parseInt(cad);
        }catch(Exception e){ inicio=0;}
        //===================================================
		//Vemos si nos ha llamado el jsp de Bienvenida, o bien el de GestionaCli, en este ultimo caso
		//no le enviamos los parametros usuario y password, estaran por tanto a null.
		if (request.getParameter("usuario")!=null){
		//Primero Recuperar los datos del usuario
		//Del request
		// usuario/password/submit
		String user= request.getParameter("usuario");
		String pass= request.getParameter("password");
        boolean logado=false;
		try{
		  logado= Usuario.logarse(user,pass);
			if (logado){
			//Si todo OK Enviamos una página por out de todo correcto con el IDSEsion creado.
			//Recupera la cookie de sesion para mostrarla.
				sesion.setAttribute("AtributoSesionIniciada", sesion.getId());
                String dni=Usuario.buscadni(user,pass);
                sesion.setAttribute("Atributodni",dni);

                //funcion q devuelve el idioma del usuario
                String idioma=Usuario.idioma(user,pass);
                sesion.setAttribute("idioma",idioma);

                if (Usuario.EsCliente(user, pass)){
				//los atributos serán,tipo de usuario y sesionIniciada
                	sesion.setAttribute("AtributoTipoUsuario","cliente");
                	int id=Usuario.buscaid(dni);
            		Cita cita=new Cita();
                	Vector citasdelusuario=cita.citasUsuario(dni,inicio);
                	int totalpaginas= Cita.calculaTotalPaginas(dni);
                	sesion.setAttribute("paginasCitasCliente",totalpaginas);
                	sesion.setAttribute("AtributoCitas", citasdelusuario);
                	sesion.setAttribute("id", id);
                	response.sendRedirect(response.encodeRedirectURL("GestionaCli.jsp?paginado="+inicio));
				}
				else {
                    sesion.setAttribute("AtributoTipoUsuario","admin");
                    response.sendRedirect("GestionaAdmin.jsp");

				}
			}
			else{
			// Si NO OK Envio una página por out de ERROR con un link a la index.html
                   response.sendRedirect("Bienvenida.jsp?valido=-1");
			}
		}catch(ErrorRizosBd e){
			response.sendRedirect("error.jsp?error=Error en la conexion a la Base Datos");
			logado=false;
		}
		}else{//fin del if. en este caso nos ha llamado GestionaCli para que representemos otra
		 //pagina de las citas que tiene reservadas el cliente
			String dni=(String)sesion.getAttribute("Atributodni");
			Cita cita=new Cita();
			Vector citasdelusuario=cita.citasUsuario(dni,inicio);
			sesion.setAttribute("AtributoCitas", citasdelusuario);
			response.sendRedirect("GestionaCli.jsp?paginado="+inicio);

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