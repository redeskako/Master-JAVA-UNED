package rba.clientes;

import configuracion.RbaException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;

import rba.bbdd.Cliente;

public class AccionCliente extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    // creamos bd como de la clase AccesoDatos
    private Cliente bd;

    // funcion que cuando carga el proceso abre base de datos
    public void init(ServletConfig cfg) throws ServletException {
        bd = new Cliente();

    }
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
    // en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
    // una accion alta, baja, modificacion
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        RequestDispatcher requestDispatcher;
        Set<Cliente> ejecutarClientes = null;
        // como de los campos, para trabajar en la funcion

        String nombre = null;
        String apellido1 = null;
        String apellido2 = null;
        String telefono = null;
        String correo = null;
        String pass = null;
        Integer cp = null;
        int totalPaginas;
        Set<Cliente> clientes = null;
        Cliente u;
        Iterator<Cliente> it;
        

        // cargamos los datos de accion (accion a realizar alba, baja, modificacion)
        // como del codigo del registro en la que se va a realizar la accion


        String codigo2 = (String) req.getParameter("codigo");
        String accion2 = (String) req.getParameter("accion");

        Integer accion = Integer.parseInt(accion2);
        Integer codigo;


        switch (accion) {
            case 1:
                // baja cliente: se manda a formulario para confirmar eliminacion
                clientes=Cliente.ejecutaBusquedaCliente(Integer.parseInt(codigo2));
                u = null;
		it = clientes.iterator();
		while (it.hasNext()){
			u = it.next();
                }
                req.setAttribute("cliente", u);
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/bajaCliente.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 2:
                // modificacion cliente: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
                clientes=Cliente.ejecutaBusquedaCliente(Integer.parseInt(codigo2));
                u = null;
		it = clientes.iterator();
		while (it.hasNext()){
			u = it.next();
                }
                req.setAttribute("cliente", u);
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/modificarCliente.jsp"));
                requestDispatcher.forward(req, res);

                break;
            case 3:
                // alta cliente: nos lleva al formulario de captura de datos
                res.sendRedirect("zonaPrivada/clientes/altaCliente2.jsp");
                break;
            case 4:
                // alta de cliente capturamos los datos introducidos el altacliente.jsp
                nombre = (String) req.getParameter("txtNombreCliente");
                apellido1 = (String) req.getParameter("txtApellido1Cliente");
                apellido2 = (String) req.getParameter("txtApellido2Cliente");
                telefono = (String) req.getParameter("txtTelefono");
                correo = (String) req.getParameter("txtCorreo");
                pass = (String) req.getParameter("txtPass");
                // controlamos que sea solo numeros los datos que se introduzcan
                // con lo que si se introducen text dara error y nos metera en la bd un 0
                try {
                    cp = Integer.parseInt(req.getParameter("txtCp"));
                } catch (Exception e) {
                    cp = 0;
                }

                // pasamos los campos capturados al modelo para su grabacion
                try{
                bd.InsertarCliente(nombre, apellido1, apellido2, telefono, correo, pass, cp);
                req.setAttribute("operacion",0);
                }catch(RbaException e){
                    req.setAttribute("operacion",3);
                }                
                // volvemos al principio de cliente
                totalPaginas = Cliente.calculaNumeroPaginas( "correo", "");
                req.setAttribute("totalpaginas", totalPaginas);                
                ejecutarClientes = Cliente.ejecutaListaCampoCliente(0, "correo", "");
                req.setAttribute("ejecutarClientes", ejecutarClientes);                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/cliente.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 5:
                // baja de cliente
                codigo = Integer.parseInt(codigo2);
                try{
                bd.EliminarCliente(codigo);
                req.setAttribute("operacion",1);               
                }catch(RbaException e){
                    req.setAttribute("operacion",4);
                }                
                // volvemos al principio de cliente
                totalPaginas = Cliente.calculaNumeroPaginas( "correo", "");
                req.setAttribute("totalpaginas", totalPaginas);                  
                ejecutarClientes = Cliente.ejecutaListaCampoCliente(0, "correo", "");
                req.setAttribute("ejecutarClientes", ejecutarClientes);

                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/cliente.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 6:
                // modificacion cliente: han confirmado datos y
                // o modificado
                codigo = Integer.parseInt(codigo2);
                nombre = (String) req.getParameter("txtNombreCliente");
                apellido1 = (String) req.getParameter("txtApellido1Cliente");
                apellido2 = (String) req.getParameter("txtApellido2Cliente");
                telefono = (String) req.getParameter("txtTelefono");
                correo = (String) req.getParameter("txtCorreo");
                pass = (String) req.getParameter("txtPass");

                try {
                    cp = Integer.parseInt(req.getParameter("txtCp"));
                } catch (Exception e) {
                    cp = 0;
                }
                // pasamos a modelo para la grabacion de los datos introducidos
                try{
                bd.ActualizarCliente(codigo, nombre, apellido1, apellido2, telefono, correo, pass, cp);
                req.setAttribute("operacion",2);
                }catch(RbaException e){
                    req.setAttribute("operacion",5);
                }
                totalPaginas = Cliente.calculaNumeroPaginas( "correo", "");
                req.setAttribute("totalpaginas", totalPaginas);  
                ejecutarClientes = Cliente.ejecutaListaCampoCliente(0, "correo", "");
                req.setAttribute("ejecutarClientes", ejecutarClientes);                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/cliente.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 7:
                // accion de busqueda que viene de la eleccion del campo de busqueda por
                // un combo y una caja de texto en cliente.jsp
                
                
                String campo = req.getParameter("CmbCampos");
                String textoBuscado = req.getParameter("txtbuscar");
                String var_inicio = req.getParameter("paginado");

                if (campo == null) {
                    //cuando entramos por primera vez en clientes
                    ejecutarClientes = Cliente.ejecutaListaCampoCliente(0, "correo", "");


                } else {
                    if (textoBuscado != null) {
                        if (var_inicio == null) {
                            ejecutarClientes = Cliente.ejecutaListaCampoCliente(0, campo, textoBuscado);
                        } else {
                           
                            req.setAttribute("paginado", Integer.parseInt(var_inicio));
                            ejecutarClientes = Cliente.ejecutaListaCampoCliente(Integer.parseInt(var_inicio), campo, textoBuscado);
                            totalPaginas = Cliente.calculaNumeroPaginas(campo, textoBuscado);
                            req.setAttribute("totalpaginas", totalPaginas);
                            req.setAttribute("ejecutarClientes", ejecutarClientes);
                            req.setAttribute("textoBuscado", textoBuscado);
                            req.setAttribute("campoBuscado", campo);
                            requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/cliente.jsp?paginado=" + var_inicio));
                            requestDispatcher.forward(req, res);
                        }

                    }
                }



                totalPaginas = Cliente.calculaNumeroPaginas(campo, textoBuscado);
                req.setAttribute("totalpaginas", totalPaginas);
                req.setAttribute("textoBuscado", textoBuscado);
                req.setAttribute("campoBuscado", campo);

                req.setAttribute("ejecutarClientes", ejecutarClientes);
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/clientes/cliente.jsp"));
                requestDispatcher.forward(req, res);
                

                break;
            case 8:
                String columna = req.getParameter("campo");
                res.sendRedirect("zonaPrivada/clientes/cliente.jsp?campo=" + columna);
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

