package rba.reservas;

import configuracion.RbaException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import rba.bbdd.Cliente;
import rba.bbdd.Mesa;
import rba.bbdd.Reserva;

public class AccionReserva extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    // creamos bd como de la clase AccesoDatos
    private Reserva bd;

    // funcion que cuando carga el proceso abre base de datos
    public void init(ServletConfig cfg) throws ServletException {
        bd = new Reserva();

    }
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
    // en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
    // una accion alta, baja, modificacion
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        RequestDispatcher requestDispatcher;

        // como de los campos, para trabajar en la funcion

        String Fecha = bd.recFecha();
        String Hora = bd.recHora();
        String Turno = null;
        Integer Usuario = null;
        Integer Cliente = null;
        Integer Mesa = null;
        String path = req.getContextPath();
        Set<Reserva> ejecutarReservas = null;
        int totalPaginas;
        Set<Cliente> clientes = null;
        Set<Mesa> mesas = null;
        Set<Reserva> reservas = null;

        // cargamos los datos de accion (accion a realizar alba, baja, modificacion)
        // como del codigo del registro en la que se va a realizar la accion

        String codigo2 = (String) req.getParameter("codigo");
        String accion2 = (String) req.getParameter("accion");

        Integer accion = Integer.parseInt(accion2);
        Integer codigo;
        Reserva reserva = null;;
        Iterator<Reserva> it;
        String reservado;

        //Integer.parseInt(req.getParameter("codigo"));

        switch (accion) {
            case 1:
                // baja reserva: se manda a formulario para confirmar eliminacion
                codigo = Integer.parseInt(codigo2);
                reservas=Reserva.ejecutaBusquedaReserva(codigo);

		it = reservas.iterator();
		while (it.hasNext()){
			reserva = it.next();
                }
                req.setAttribute("reserva", reserva);
                clientes = rba.bbdd.Cliente.ejecutaListaCliente(0);
                mesas = rba.bbdd.Mesa.ejecutaListaMesa(0);
                req.setAttribute("clientes", clientes);
                req.setAttribute("mesas", mesas);
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/bajaReserva.jsp"));
                requestDispatcher.forward(req, res);                

                break;
            case 2:
                // modificacion reserva: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
                codigo = Integer.parseInt(codigo2);
                reservas=Reserva.ejecutaBusquedaReserva(codigo);

		it = reservas.iterator();
		while (it.hasNext()){
			reserva = it.next();
                }
                req.setAttribute("reserva", reserva);                
                clientes = rba.bbdd.Cliente.ejecutaListaCliente(0);
                mesas = rba.bbdd.Mesa.ejecutaListaMesa(0);  
                req.setAttribute("clientes", clientes);
                req.setAttribute("mesas", mesas);               
                reservado = req.getParameter("reservado");
                if(reservado != null){
                    req.setAttribute("reservado", 1);
                }                
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/modificarReserva.jsp"));
                requestDispatcher.forward(req, res);   
                break;
            case 3:
                // alta reserva: nos lleva al formulario de captura de datos
                //datos que necesitamos en el formulario
                Reserva r = new Reserva();
                String txtReserva = r.recFecha();
                String txtReserva2 = r.recHora();
                clientes = rba.bbdd.Cliente.ejecutaListaCliente(0);
                mesas = rba.bbdd.Mesa.ejecutaListaMesa(0);
                reservado = req.getParameter("reservado");
                if(reservado != null){
                    req.setAttribute("reservado", 1);
                }
                
                req.setAttribute("clientes", clientes);
                req.setAttribute("mesas", mesas);
                req.setAttribute("fecha", txtReserva);
                req.setAttribute("hora", txtReserva2);
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/altaReserva.jsp"));
                requestDispatcher.forward(req, res);

                break;
            case 4:
                // alta de reserva capturamos los datos introducidos el altareserva.jsp
                try {
                    Fecha = (String) req.getParameter("txtFecha");
                } catch (Exception e) {
                    Fecha = bd.getFecha();
                }

                
                try {
                    Hora = (String) req.getParameter("txtHora");
                } catch (Exception e) {
                    Hora = bd.getHora();
                }



                Turno = (String) req.getParameter("CmbTurno");


                //usuario
                //Usuario  = Integer.parseInt(req.getParameter("txtUsuario"));
                //cliente
                Cliente = Integer.parseInt(req.getParameter("CmbCliente"));
                //recuperamos el correo y con la funci�n RetornarNumCliente recuperamos el c�digo y lo insertamos

                //mesa
                Mesa = Integer.parseInt(req.getParameter("CmbMesa"));
                //insertamos
                if (Reserva.estaReservado(Fecha, Hora, Turno, Cliente, Mesa)) {
                    res.sendRedirect(req.getContextPath()+"/AccionReserva?accion=3&reservado=1");
                    break;

                }
                try {
                    bd.InsertarReserva(Fecha, Hora, Turno, Cliente, Mesa);
                    req.setAttribute("operacion", 0);
                } catch (RbaException e) {
                    req.setAttribute("operacion", 3);
                }
                ejecutarReservas = Reserva.ejecutaListaCampoReserva(0, "codigo", "");
                req.setAttribute("ejecutarReservas", ejecutarReservas);
                totalPaginas = Reserva.calculaNumeroPaginas("codigo", "");
                req.setAttribute("totalpaginas", totalPaginas);


                // volvemos al principio de cliente
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/reserva.jsp"));
                requestDispatcher.forward(req, res);

                break;
            case 5:
                // baja de cliente
                codigo = Integer.parseInt(codigo2);
                try {
                    bd.EliminarReserva(codigo);
                    req.setAttribute("operacion", 1);
                } catch (RbaException e) {
                    req.setAttribute("operacion", 4);
                }
                ejecutarReservas = Reserva.ejecutaListaCampoReserva(0, "codigo", "");
                req.setAttribute("ejecutarReservas", ejecutarReservas);
                totalPaginas = Reserva.calculaNumeroPaginas("codigo", "");
                req.setAttribute("totalpaginas", totalPaginas);

                // volvemos al principio de cliente
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/reserva.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 6:
                // modificacion cliente: han confirmado datos y
                // o modificado
                codigo = Integer.parseInt(codigo2);

                try {
                    Fecha = (String) req.getParameter("txtFecha");
                } catch (Exception e) {
                    Fecha = bd.getFecha();
                }

                //no puede ser null, ya que las comparaciones las hago con la hora.Deberia recogerse
                //automaticamente....hacer
                try {
                    Hora = (String) req.getParameter("txtHora");
                } catch (Exception e) {
                    Hora = bd.getHora();
                }


                Turno = (String) req.getParameter("CmbTurno");

                //cliente
                Cliente = Integer.parseInt(req.getParameter("CmbCliente"));
                //recuperamos el correo y con la funci�n RetornarNumCliente recuperamos el c�digo y lo insertamos

                //mesa
                Mesa = Integer.parseInt(req.getParameter("CmbMesa"));


                if (Reserva.estaReservado(Fecha, Hora, Turno, Cliente, Mesa)) {

                    res.sendRedirect(req.getContextPath()+"/AccionReserva?accion=2&reservado=1&codigo="+codigo);
                    
                    break;

                }
                // pasamos a modelo para la grabacion de los datos introducidos
                try {
                    bd.ActualizarReserva(codigo, Fecha, Hora, Turno, Cliente, Mesa);
                    req.setAttribute("operacion", 2);
                } catch (RbaException e) {
                    req.setAttribute("operacion", 5);
                }

                ejecutarReservas = Reserva.ejecutaListaCampoReserva(0, "codigo", "");
                req.setAttribute("ejecutarReservas", ejecutarReservas);
                totalPaginas = Reserva.calculaNumeroPaginas("codigo", "");
                req.setAttribute("totalpaginas", totalPaginas);

                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/reserva.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 7:
                // accion de busqueda que viene de la eleccion del campo de busqueda por
                // un combo y una caja de texto en reserva.jsp
                String campo = req.getParameter("CmbCampos");
                String textoBuscado = req.getParameter("txtbuscar");
                String var_inicio = req.getParameter("paginado");

                if (campo == null) {
                    //cuando entramos por primera vez en reservas
                    ejecutarReservas = Reserva.ejecutaListaCampoReserva(0, "codigo", "");
                 

                } else {
                    if (textoBuscado != null) {
                        if (var_inicio == null) {
                           ejecutarReservas = Reserva.ejecutaListaCampoReserva(0, campo, textoBuscado);
                        } else {
                           
                            req.setAttribute("paginado", Integer.parseInt(var_inicio));
                            ejecutarReservas = Reserva.ejecutaListaCampoReserva(Integer.parseInt(var_inicio), campo, textoBuscado);
                            totalPaginas = Reserva.calculaNumeroPaginas(campo, textoBuscado);
                            req.setAttribute("totalpaginas", totalPaginas);
                            req.setAttribute("ejecutarReservas", ejecutarReservas);
                            req.setAttribute("textoBuscado", textoBuscado);
                            req.setAttribute("campoBuscado", campo);
                            requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/reserva.jsp?paginado=" + var_inicio));
                            requestDispatcher.forward(req, res);
                        }

                    }
                }

                totalPaginas = Reserva.calculaNumeroPaginas(campo, textoBuscado);
                req.setAttribute("totalpaginas", totalPaginas);
                req.setAttribute("textoBuscado", textoBuscado);
                req.setAttribute("campoBuscado", campo);

                req.setAttribute("ejecutarReservas", ejecutarReservas);
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/reservas/reserva.jsp"));
                requestDispatcher.forward(req, res);
              

                break;
            case 8:
                String columna = req.getParameter("campo");
                res.sendRedirect("zonaPrivada/reservas/reserva.jsp?campo=" + columna);
                break;
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            doPost(req, res);

        } catch (Exception e) {
        }


    }
}
