/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 17/12/2015
 */

package com.csc.biblioteca.util;

import com.csc.biblioteca.mdi.MDIControlador;
import java.util.Map;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 * Clase base de la que deben heredar los controladores de las ventanas internas
 * de la aplicación (JInternalFrame).
 */
public abstract class ControladorBase {
    public enum Estado {CREACION, LECTURA, EDICION, MARCADO}
    
    protected MDIControlador controlador;
    protected Estado estado;
    
    /**
     * Constructor del controlador.
     * @param controlador Clase controladora padre de la ventana principal de la aplicación.
     */
    public ControladorBase(MDIControlador controlador) {
        this.controlador = controlador;
        this.estado = Estado.LECTURA;
        this.iniciarControlador();
    }
    
    /** Acciones a realizar al inicializar el controlador base. */
    private void iniciarControlador() {
        this.crearVista().addInternalFrameListener(controlador);
        this.mostrarVista();
    }
    
    /**
     * Generar la vista asociada al controlador.
     * @return Vista asociada al controlador.
     */
    protected abstract JInternalFrame crearVista();
    
    /** Mostrar la vista asociada al controlador. */
    public abstract void mostrarVista();
    
    /**
     * Método para generar un informe del listado que contiene el modelo modelo
     * con la plantilla y parámetros indicados.
     * @param modelo Modelo del que obtener los datos del listado.
     * @param plantilla Plantilla a utilizar para elaborar el informe.
     * @param parametros Parámetros a utilizar en la generación del informe.
     */
    protected void generarInformeModelo(ModeloBase modelo, String plantilla, Map<String, Object> parametros) {
        try {
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(modelo.getListado());
            
            // Cargar datos
            JasperPrint print = JasperFillManager.fillReport(plantilla, parametros, datasource);
            
            // Visualizar el informe
            JRViewer visor = new JRViewer(print, Messages.getLocale());
            
            JInternalFrame ventanaInforme = new JInternalFrame(Messages.getString("TITULO_VISOR_LISTADOS", parametros.get("tituloInforme")), true, true, true, false);
            ventanaInforme.getContentPane().add(visor);
            ventanaInforme.pack();
            ventanaInforme.setSize(700, 400);
            this.controlador.addVentana(ventanaInforme, false);
            this.controlador.mostrarVentana(ventanaInforme, true);
            
        } catch (JRException ex) {
            Messages.mensaje(Messages.Tipo.ERROR, "MSG_ERROR_GENERAR_INFORME", ex.getMessage());
        }        
    }
}
