/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.5 - 12/12/2015
 */

package com.csc.biblioteca.mdi;

import com.csc.biblioteca.util.Constantes;
import com.csc.biblioteca.conexion.ConexionBD;
import com.csc.biblioteca.libro.LibroControlador;
import com.csc.biblioteca.prestamo.PrestamoControlador;
import com.csc.biblioteca.socio.SocioControlador;
import com.csc.biblioteca.excepciones.*;
import com.csc.biblioteca.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/** Controlador principal de la aplicación. */
public class MDIControlador implements ActionListener, WindowListener, InternalFrameListener {
    private final ConexionBD conexion;
    private MDIVista ventanaMDI;
    private AcercadeVista ventanaAcercade;
    private SocioControlador socio;
    private LibroControlador libro;
    private PrestamoControlador prestamo;
    
    /** Constructor del controlador principal de la aplicación. */
    public MDIControlador() {
        Messages.setLocale(Messages.SPANISH);        // Idioma por defecto
        this.iniciarLog();
        this.conexion = new ConexionBD();
        this.crearVista();
        Messages.setVentana(ventanaMDI);
        if (!this.conectar()) salir();
    }
    
    /** Creación de la vista principal de la aplicación (MVC) que es de tipo MDI. */
    private void crearVista() {
        this.ventanaMDI = new MDIVista(Messages.getString("TITULO_APLICACION", Constantes.VERSION));
        this.ventanaMDI.registrarActionListener(this);
        this.ventanaMDI.registrarWindowListener(this);
        this.ventanaMDI.setSize(880, 680);          // Dimensiones por defecto
        this.ventanaMDI.setVisible(true);
    }
    
    /** Inicializar el log que necesita el gestor de informes (jasperReports). */
    private void iniciarLog() {
        PatternLayout pat = new PatternLayout("[%-5p][%t] (%F:%L) : %m%n");
        Logger.getRootLogger().addAppender(new ConsoleAppender(pat));
        Logger.getRootLogger().setLevel(Level.OFF);
    }
    
    /**
     * Método para conectar a la base de datos de la aplicación.
     * Los parámetros de conexión se obtienen del fichero de configuración.
     * @return Indica si la conexión fue correcta (TRUE) o no (FALSE).
     */
    private boolean conectar() {
        boolean ok = false;
        
        try {
            String serverName = Configuracion.getProperty("SERVERNAME");
            String database = Configuracion.getProperty("DATABASE");
            String user = Configuracion.getProperty("USER");
            String password = Configuracion.getProperty("PASSWORD");
            
            conexion.conectar(ConexionBD.DBMS.MYSQL, serverName, database, user, password);
            ok = true;
        } catch (InfoException ex) {
            Messages.mensaje(ex, "MSG_ATENCION_DBMS_NOVALIDO");
        } catch (ErrorException ex) {
            Messages.mensaje(ex, "MSG_ERROR_CONEXIONBD");
        }
        
        return ok;
    }
    
    /**
     * Obtener el objeto de conexión a la base de datos de la aplicación.
     * Se usa un modelo conectado.
     * @return Conexión a la base de datos.
     */
    public Connection getConexion() {
        return this.conexion.getConexion();
    }
    
    /**
     * Añadir una ventana interna al escritorio de la aplicación (MDI).
     * @param ventana Ventana interna a añadir.
     * @param centrada Si debe centrarse en el escritorio (TRUE) o no (FALSE).
     */
    public void addVentana(JInternalFrame ventana, boolean centrada) {
        this.ventanaMDI.addVentana(ventana, centrada);
    }
    
    /**
     * Mostrar la ventana interna en el escritorio principal de la aplicación.
     * @param ventana Ventana interna a mostrar.
     * @param maximizada Si debe mostrarse maximizada (TRUE) o no (FALSE).
     */
    public void mostrarVentana(JInternalFrame ventana, boolean maximizada) {
        this.ventanaMDI.mostrarVentana(ventana, maximizada);
    }
    
    /** Acción asociada al menú Ayuda > Acerca de. */
    private void menuAcercade() {
        if (this.ventanaAcercade == null || !this.ventanaAcercade.isShowing()) {
            this.ventanaAcercade = new AcercadeVista(Messages.getString("TITULO_ACERCADE", Constantes.VERSION));
            this.addVentana(ventanaAcercade, true);
            this.ventanaAcercade.addInternalFrameListener(this);
        }
        this.mostrarVentana(this.ventanaAcercade, false);
    }
    
    /** Acción asociada al menú Gestión > Socios. */
    private void menuSocios() {
        if (this.socio == null || this.socio.vistaCerrada()) {
            this.socio = new SocioControlador(this);
        } else {
            this.socio.mostrarVista();
        }
    }
    
    /** Acción asociada al menú Gestión > Libros. */
    private void menuLibros() {
        if (this.libro == null || this.libro.vistaCerrada()) {
            this.libro = new LibroControlador(this);
        } else {
            this.libro.mostrarVista();
        }
    }
    
    /** Acción asociada al menú Gestión > Préstamos. */
    private void menuPrestamos() {
        if (this.prestamo == null || this.prestamo.vistaCerrada()) {
            this.prestamo = new PrestamoControlador(this);
        } else {
            this.prestamo.mostrarVista();
        }
    }
    
    /**
     * Acción asociada a los submenús del menú Informes.
     * @param opcion Submenú: Préstamos por socio / Préstamos por libro.
     */
    private void menuInformePrestamos(String opcion) {
        String plantilla = null;
        String titulo;
        String subtitulo = "";
        Map<String, Object> parametros = new TreeMap<>();
        
        titulo = Messages.getString("INFORME_PRESTAMOS_TITULO");
        
        switch (opcion) {
            case Constantes.MENU_PRESTAMOS_SOCIO:
                subtitulo = Messages.getString("INFORME_PRESTAMOS_SUBTITULO_SOCIO");
                plantilla = Constantes.REPORTS_PATH+"/PrestamosSocio.jasper";
                parametros.put("libro", Messages.getString("HEADER_LIBRO"));
                break;

            case Constantes.MENU_PRESTAMOS_LIBRO:
                subtitulo = Messages.getString("INFORME_PRESTAMOS_SUBTITULO_LIBRO");
                plantilla = Constantes.REPORTS_PATH+"/PrestamosLibro.jasper";
                parametros.put("socio", Messages.getString("HEADER_SOCIO"));
                
                break;
        }
        
        try {
            // Parámetros
            parametros.put("id", Messages.getString("HEADER_ID"));
            parametros.put("fechaInicio", Messages.getString("HEADER_INICIO"));
            parametros.put("fechaFin", Messages.getString("HEADER_FIN"));
            parametros.put("tituloInforme", titulo);
            parametros.put("subtituloInforme", subtitulo);
            
            // Cargar datos
            JasperPrint print = JasperFillManager.fillReport(plantilla, parametros, this.getConexion());
            
            // Visualizar el informe
            JRViewer visor = new JRViewer(print, Messages.getLocale());
            
            JInternalFrame ventanaInforme = new JInternalFrame(Messages.getString("TITULO_VISOR_INFORMES", titulo, subtitulo), true, true, true, false);
            ventanaInforme.getContentPane().add(visor);
            ventanaInforme.pack();
            ventanaInforme.setSize(700, 400);
            this.addVentana(ventanaInforme, false);
            this.mostrarVentana(ventanaInforme, true);
            
        } catch (JRException ex) {
            Messages.mensaje(Messages.Tipo.ERROR, "MSG_ERROR_GENERAR_INFORME", ex.getMessage());
        }        
    }
    
    /** Acción asociada al menú Gestión > Salir. */
    private void menuSalir() {
        if ((Messages.mensaje(Messages.Tipo.QUESTION, "MSG_PREGUNTA_SALIR")) == 0) {
            try {
                conexion.desconectar();
            } catch (ErrorException ex) {
                System.out.println(ex.getMessage());
            } finally {
                salir();
            }
        }
    }
    
    /** Método para salir de la aplicación. */
    private void salir() {
        this.ventanaMDI.setVisible(false);
        this.ventanaMDI.dispose();
        System.exit(0);
    }
    
    /** 
     * Gestión de las acciones de la vista (MVC).
     * @param e Acción a realizar.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.MENU_ACERCADE:
                this.menuAcercade();
                break;
            case Constantes.MENU_SOCIOS:
                this.menuSocios();
                break;
            case Constantes.MENU_LIBROS:
                this.menuLibros();
                break;
            case Constantes.MENU_PRESTAMOS:
                this.menuPrestamos();
                break;
            case Constantes.MENU_PRESTAMOS_SOCIO:
            case Constantes.MENU_PRESTAMOS_LIBRO:
                this.menuInformePrestamos(e.getActionCommand());
                break;
            case Constantes.MENU_SPANISH:
                Messages.setLocale(Messages.SPANISH);
                this.ventanaMDI.cambiarIdioma();
                break;
            case Constantes.MENU_ENGLISH:
                Messages.setLocale(Messages.ENGLISH);
                this.ventanaMDI.cambiarIdioma();
                break;
            case Constantes.MENU_SALIR:
                this.menuSalir();
                break;
        }
    }
    
    /**
     * Evento de cierre de la ventana de la aplicación (aspa).
     * @param e Datos del evento.
     */
    @Override
    public void windowClosing(WindowEvent e) {this.menuSalir();}

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    /**
     * Apertura de una ventana interna.
     * @param e Datos del evento.
     */
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        // Impedimos el cambio de idioma si hay ventanas abiertas
        this.ventanaMDI.setEstadoMenuIdioma(false);
    }

    /**
     * Cierre de una ventana interna.
     * @param e Datos del evento.
     */
    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        // Restauramos el cambio de idioma
        if (!this.ventanaMDI.tieneVentanas()) this.ventanaMDI.setEstadoMenuIdioma(true);
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {}

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {}

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {}

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {}

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {}
}
