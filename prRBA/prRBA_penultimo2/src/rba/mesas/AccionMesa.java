package rba.mesas;

import configuracion.RbaException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;

import rba.bbdd.Cliente;
import rba.bbdd.Mesa;

public class AccionMesa extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    // creamos bd como de la clase AccesoDatos
    private Mesa bd;

    // funcion que cuando carga el proceso abre base de datos
    public void init(ServletConfig cfg) throws ServletException {
        bd = new Mesa();
    

    }
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
    // en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
    // una accion alta, baja, modificacion
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        RequestDispatcher requestDispatcher;

        
        Integer numsillas = null;
        String path = req.getContextPath();
        int totalPaginas;
        Set<Mesa> ejecutarMesas = null;
        Mesa u;
        Iterator<Mesa> it;
        // cargamos los datos de accion (accion a realizar alta, baja, modificacion)
        // como del codigo del registro en la que se va a realizar la accion

        String codigo2 = (String) req.getParameter("codigo");
        String accion2 = (String) req.getParameter("accion");

        Integer accion = Integer.parseInt(accion2);
        Integer codigo;

        switch (accion) {
            case 1:
                // baja mesa: se manda a formulario para confirmar eliminacion
                ejecutarMesas=Mesa.ejecutaBusquedaMesa(Integer.parseInt(codigo2));
                u = null;
		it = ejecutarMesas.iterator();
		while (it.hasNext()){
			u = it.next();
                }
                req.setAttribute("mesa", u);
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/bajaMesa.jsp"));
                requestDispatcher.forward(req, res);                
                break;
            case 2:
                // modificacion mesa: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
                ejecutarMesas=Mesa.ejecutaBusquedaMesa(Integer.parseInt(codigo2));
                u = null;
		it = ejecutarMesas.iterator();
		while (it.hasNext()){
			u = it.next();
                }
                req.setAttribute("mesa", u);
                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/modificarMesa.jsp"));
                requestDispatcher.forward(req, res);    
                break;
            case 3:
                // alta mesa: nos lleva al formulario de captura de datos
                res.sendRedirect("zonaPrivada/mesas/altaMesa.jsp");
                break;
            case 4:
                // alta de mesa capturamos los datos introducidos el altacliente.jsp

                try {
                    numsillas = Integer.parseInt(req.getParameter("txtNumSillas"));
                } catch (Exception e) {
                    numsillas = 0;
                }

                // pasamos los campos capturados al modelo para su grabacion
                try{
                bd.InsertarMesa(numsillas);
                req.setAttribute("operacion",0);
                }catch(RbaException e){
                    req.setAttribute("operacion",3);
                }
                totalPaginas = Mesa.calculaNumeroPaginas("codigo","");
                req.setAttribute("totalpaginas", totalPaginas);
                ejecutarMesas = Mesa.ejecutaListaCampoMesa(0, "codigo", "");
                req.setAttribute("ejecutarMesas", ejecutarMesas);               
                // volvemos al principio de mesas
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/mesa.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 5:
                // baja de mesa              
                codigo = Integer.parseInt(codigo2);
                try{
                bd.EliminarMesa(codigo);
                req.setAttribute("operacion",1);
                }catch(RbaException e){
                    req.setAttribute("operacion",4);
                }
                
                
                totalPaginas = Mesa.calculaNumeroPaginas("codigo","");
                req.setAttribute("totalpaginas", totalPaginas);
                ejecutarMesas = Mesa.ejecutaListaCampoMesa(0, "codigo", "");
                req.setAttribute("ejecutarMesas", ejecutarMesas);
                
                // volvemos al principio de mesa
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/mesa.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 6:
                // modificacion mesa: han confirmado datos y
                // o modificado
                //alta de mesa capturamos los datos introducidos el altamesa.jsp



                try {
                    numsillas = Integer.parseInt(req.getParameter("txtNumSillas"));
                } catch (Exception e) {
                    numsillas = 0;
                }

                // pasamos a modelo para la grabacion de los datos introducidos
                codigo = Integer.parseInt(codigo2);
                try{
                bd.ActualizarMesa(codigo, numsillas);
                req.setAttribute("operacion",2);
                }catch(RbaException e){
                    req.setAttribute("operacion",5);
                }
                totalPaginas = Mesa.calculaNumeroPaginas("codigo","");
                req.setAttribute("totalpaginas", totalPaginas);  
                ejecutarMesas = Mesa.ejecutaListaCampoMesa(0, "codigo", "");
                req.setAttribute("ejecutarMesas", ejecutarMesas);                
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/mesa.jsp"));
                requestDispatcher.forward(req, res);
                break;
            case 7:
                // accion de busqueda que viene de la eleccion del campo de busqueda por
                // un combo y una caja de texto en cliente.jsp         
                String campo = req.getParameter("CmbCampos");
                String textoBuscado = req.getParameter("txtbuscar");
                String var_inicio = req.getParameter("paginado");

                if (campo == null) {
                    //cuando entramos por primera vez en mesas
                    ejecutarMesas = Mesa.ejecutaListaCampoMesa(0, "codigo", "");
            

                } else {
                    if (textoBuscado != null) {
                        if (var_inicio == null) {
                            ejecutarMesas = Mesa.ejecutaListaCampoMesa(0, campo, textoBuscado);
                        } else {
                         
                            req.setAttribute("paginado", Integer.parseInt(var_inicio));
                            ejecutarMesas = Mesa.ejecutaListaCampoMesa(Integer.parseInt(var_inicio), campo, textoBuscado);
                            totalPaginas = Mesa.calculaNumeroPaginas(campo, textoBuscado);
                            req.setAttribute("totalpaginas", totalPaginas);
                            req.setAttribute("ejecutarMesas", ejecutarMesas);
                            req.setAttribute("textoBuscado", textoBuscado);
                            req.setAttribute("campoBuscado", campo);
                            requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/mesa.jsp?paginado=" + var_inicio));
                            requestDispatcher.forward(req, res);
                        }

                    }
                }



                totalPaginas = Mesa.calculaNumeroPaginas(campo, textoBuscado);
                req.setAttribute("totalpaginas", totalPaginas);
                req.setAttribute("textoBuscado", textoBuscado);
                req.setAttribute("campoBuscado", campo);

                req.setAttribute("ejecutarMesas", ejecutarMesas);
                requestDispatcher = req.getRequestDispatcher(res.encodeURL("zonaPrivada/mesas/mesa.jsp"));
                requestDispatcher.forward(req, res);
                //res.sendRedirect ("zonaPrivada/clientes/cliente.jsp");

                break;
            case 8:
                String columna = req.getParameter("campo");
                res.sendRedirect("zonaPrivada/mesas/mesa.jsp?campo=" + columna);
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
