/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 08/12/2015
 */

package com.csc.biblioteca.socio;

import com.csc.biblioteca.excepciones.*;
import com.csc.biblioteca.mdi.MDIControlador;
import com.csc.biblioteca.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JInternalFrame;

/** Controlador de la ventana de mantenimiento de socios. */
public class SocioControlador extends ControladorBase implements ActionListener, MouseListener {
    
    private SocioVista ventanaSocios;
    private SocioModelo modeloSocios;
    
    /**
     * Constructor del controlador del mantenimiento de socios.
     * @param controlador Clase controladora padre de la ventana principal de la aplicación.
     */
    public SocioControlador(MDIControlador controlador) {
        super(controlador);
    }
    
    /** 
     * Creación de la vista (MVC) y carga de los datos para el mantenimiento de socios.
     * @return Ventana interna generada.
     */
    @Override
    public JInternalFrame crearVista() {
        SocioDAO dao;
        
        if (this.ventanaSocios == null) {
            this.ventanaSocios = new SocioVista(Messages.getString("TITULO_SOCIOS"));
            this.ventanaSocios.registrarActionListener(this);
            this.ventanaSocios.registraMouseListener(this);

            dao = new SocioDAO(this.controlador.getConexion());
            try {
                this.modeloSocios = new SocioModelo();
                this.modeloSocios.setListado((ArrayList<Socio>) dao.obtenerSocios());
                this.ventanaSocios.setModeloListado(this.modeloSocios);
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_CARGAR_SOCIOS");
            }
            this.controlador.addVentana(this.ventanaSocios, false);
        }
        
        return this.ventanaSocios;
    }
    
    /** Método para mostrar la ventana de mantenimiento de socios (maximizada). */
    @Override
    public void mostrarVista() {
        this.controlador.mostrarVentana(this.ventanaSocios, true);
    }
    
    /** 
     * Gestión de las acciones de la vista (MVC).
     * @param e Acción a realizar.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.INSERTAR:
                this.accionInsertar();
                break;
            case Constantes.EDITAR:
                this.accionEditar();
                break;
            case Constantes.BORRAR:
                this.accionBorrar();
                break;
            case Constantes.ACEPTAR:
                this.accionAceptar();
                break;
            case Constantes.CANCELAR:
                this.accionCancelar();
                break;
            case Constantes.IMPRIMIR:
                this.accionImprimir();
                break;
        }
    }

    /** Realización de la acción de inserción. */
    private void accionInsertar() {
        this.setEstado(Estado.CREACION);
    }
    
    /** Realización de la acción de edición. */
    private void accionEditar() {
        Socio socio;
        int fila = this.ventanaSocios.getFilaSeleccionada();
        
        if (fila >= 0) {
            socio = this.modeloSocios.getSocioAt(fila);
            this.ventanaSocios.setSocio(socio);
            this.setEstado(Estado.EDICION);
        }
    }

    /** Realización de la acción de borrado. */
    private void accionBorrar() {
        SocioDAO dao;
        Socio socios[];
        int fila;
        
        socios = this.modeloSocios.getSociosMarcados();
        if (socios != null && socios.length > 0) {
            if ((Messages.mensaje(Messages.Tipo.QUESTION, "MSG_PREGUNTA_BORRAR_SOCIOS")) == 0) {
                dao = new SocioDAO(this.controlador.getConexion());
                for (Socio socio : socios) {
                    try {
                        dao.eliminarSocio(socio);
                        this.modeloSocios.deleteSocio(socio);
                    } catch (InfoException ex) {
                        if (ex.getErrorCode() == ErrorCode.ERROR_FK_REFERENCE)
                            Messages.mensaje(ex, "MSG_ATENCION_SOCIO_CON_PRESTAMOS", socio.getIdSocio());
                    } catch (ErrorException ex) {
                        Messages.mensaje(ex, "MSG_ERROR_ELIMINAR_SOCIO", socio.getIdSocio());
                    }
                }
                this.setEstado(Estado.LECTURA);
                if (this.modeloSocios.getRowCount() > 0) {
                    fila = this.ventanaSocios.getFilaSeleccionada();
                    if (fila < 0) this.ventanaSocios.setFilaSeleccionada(0);
                }
            }
        } else {
            Messages.mensaje(Messages.Tipo.INFORMATION, "MSG_AVISO_NO_SOCIOS");
        }
    }
    
    /** Realización de la acción de aceptación de los cambios realizados. */
    private void accionAceptar() {
        SocioDAO dao;
        Socio socio;
        
        socio = this.ventanaSocios.getSocio();
        if (this.validarSocio(socio)) {
            dao = new SocioDAO(this.controlador.getConexion());
            try {
                switch (this.estado) {
                    case EDICION:
                        dao.actualizarSocio(socio);
                        this.modeloSocios.setSocioAt(this.ventanaSocios.getFilaSeleccionada(), socio);
                        break;

                    case CREACION:
                        dao.insertarSocio(socio);
                        this.modeloSocios.addSocio(socio);
                        break;
                }
                this.ventanaSocios.limpiarFicha();
                this.setEstado(Estado.LECTURA);
            } catch (InfoException ex) {
                Messages.mensaje(ex, "MSG_ATENCION_SOCIO_DUPLICADO", socio.getDni());
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_GUARDAR_SOCIO", socio.getIdSocio());
            }
        } else {
            Messages.mensaje(Messages.Tipo.WARNING, "MSG_ATENCION_SOCIO_VACIO");
        }
    }

    /** Realización de la acción de cancelación de los cambios realizados. */
    private void accionCancelar() {
        this.ventanaSocios.limpiarFicha();
        this.setEstado(Estado.LECTURA);
    }
    
    /** Realización de la acción de impresión del listado de libros. */
    private void accionImprimir() {
        String plantilla = Constantes.REPORTS_PATH+"/ListadoSocios.jasper";
        
        // Parámetros
        Map<String, Object> parametros = new TreeMap<>();
        parametros.put("tituloInforme", Messages.getString("LISTADO_SOCIOS"));
        parametros.put("subtituloInforme", "");
        parametros.put("id", Messages.getString("HEADER_ID"));
        parametros.put("socio", Messages.getString("HEADER_SOCIO"));
        parametros.put("dni", Messages.getString("HEADER_DNI"));
        parametros.put("direccion", Messages.getString("HEADER_DIRECCION"));
        parametros.put("alta", Messages.getString("HEADER_ALTA"));
        
        this.generarInformeModelo(modeloSocios, plantilla, parametros);
    }
    
    /** Realización de la acción de selección de una fila del listado. */
    private void accionSeleccionar() {
        if (this.estado == Estado.LECTURA || this.estado == Estado.MARCADO) {
            this.setEstado(Estado.LECTURA);
        }
    }
    
    /**
     * Establecer el estado indicado para el mantenimiento de socios.
     * @param nuevoEstado Estado a establecer.
     */
    private void setEstado(Estado nuevoEstado) {
        if (nuevoEstado == Estado.LECTURA) {
            if (this.modeloSocios.haySociosMarcados()) nuevoEstado = Estado.MARCADO;
        }
        this.estado = nuevoEstado;
        this.ventanaSocios.setEstado(this.estado);
    }
    
    /**
     * Comprobar si los datos del socio indicado son válidos (control de campos vacíos).
     * @param socio Socio a validar.
     * @return Indica si es válido (TRUE) o no (FALSE).
     */
    private boolean validarSocio(Socio socio) {
        if (socio == null) throw new IllegalArgumentException("Socio no válido.");
        
        if (socio.getNombre() == null || socio.getNombre().isEmpty()) return false;
        if (socio.getApellidos() == null || socio.getApellidos().isEmpty()) return false;
        if (socio.getFechaAlta() == null) return false;
        
        return true;
    }
    
    /**
     * Obtener si la ventana de mantenimiento de socios está cerrada.
     * @return Indica si está cerrada (TRUE) o no (FALSE).
     */
    public boolean vistaCerrada() {
        return this.ventanaSocios.isClosed();
    }
    
    /**
     * Evento de selección de una fila del listado de socios.
     * @param e Datos del evento.
     */
    @Override
    public void mouseClicked(MouseEvent e) {this.accionSeleccionar();}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
