/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.1 - 09/01/2016
 */

package com.csc.biblioteca.prestamo;

import com.csc.biblioteca.mdi.MDIControlador;
import com.csc.biblioteca.excepciones.*;
import com.csc.biblioteca.libro.Libro;
import com.csc.biblioteca.libro.LibroComboBoxRenderer;
import com.csc.biblioteca.libro.LibroDAO;
import com.csc.biblioteca.socio.Socio;
import com.csc.biblioteca.socio.SocioComboBoxRenderer;
import com.csc.biblioteca.socio.SocioDAO;
import com.csc.biblioteca.util.ArrayListComboBoxModel;
import com.csc.biblioteca.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JInternalFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/** Controlador de la ventana de mantenimiento de prestamos. */
public class PrestamoControlador extends ControladorBase implements ActionListener, MouseListener, TableModelListener {
    private PrestamoVista ventanaPrestamos;
    private PrestamoModelo modeloPrestamos;
    
    /**
     * Constructor del controlador del mantenimiento de préstamos.
     * @param controlador Clase controladora padre de la ventana principal de la aplicación.
     */
    public PrestamoControlador(MDIControlador controlador) {
        super(controlador);
    }
    
    /** 
     * Creación de la vista (MVC) y carga de los datos para el mantenimiento de préstamos.
     * @return Ventana interna generada.
     */
    @Override
    public JInternalFrame crearVista() {
        PrestamoDAO daoPrestamos;
        SocioDAO daoSocios;
        LibroDAO daoLibros;
        ArrayList<Socio> socios;
        ArrayList<Libro> libros;
        
        if (this.ventanaPrestamos == null) {
            this.ventanaPrestamos = new PrestamoVista(Messages.getString("TITULO_PRESTAMOS"));
            this.ventanaPrestamos.registrarActionListener(this);
            this.ventanaPrestamos.registraMouseListener(this);
            
            daoSocios = new SocioDAO(this.controlador.getConexion());
            try {
                socios = (ArrayList<Socio>) daoSocios.obtenerSocios();
                Collections.sort(socios);
                this.ventanaPrestamos.setComboBoxSocios(new ArrayListComboBoxModel(socios), 
                        new SocioComboBoxRenderer());
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_CARGAR_SOCIOS");
            }
            
            daoLibros = new LibroDAO(this.controlador.getConexion());
            try {
                libros = (ArrayList<Libro>) daoLibros.obtenerLibros();
                Collections.sort(libros);
                this.ventanaPrestamos.setComboBoxLibros(new ArrayListComboBoxModel(libros), 
                        new LibroComboBoxRenderer());
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_CARGAR_LIBROS");
            }
            
            daoPrestamos = new PrestamoDAO(this.controlador.getConexion());
            try {
                this.modeloPrestamos = new PrestamoModelo();
                this.modeloPrestamos.setListado((ArrayList<Prestamo>) daoPrestamos.obtenerPrestamos());
                this.modeloPrestamos.addTableModelListener(this);
                this.ventanaPrestamos.setModeloListado(this.modeloPrestamos);
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_CARGAR_PRESTAMOS");
            }
            
            this.controlador.addVentana(this.ventanaPrestamos, false);
        }
        
        return this.ventanaPrestamos;
    }
    
    /** Método para mostrar la ventana de mantenimiento de préstamos (maximizada). */
    @Override
    public void mostrarVista() {
        this.controlador.mostrarVentana(this.ventanaPrestamos, true);
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
            case Constantes.MENU_SALIR:
                break;
        }
    }

    /** Realización de la acción de inserción. */
    private void accionInsertar() {
        this.setEstado(Estado.CREACION);
    }
    
    /** Realización de la acción de edición. */
    private void accionEditar() {
        Prestamo prestamo;
        int fila = this.ventanaPrestamos.getFilaSeleccionada();
        
        if (fila >= 0) {
            prestamo = this.modeloPrestamos.getPrestamoAt(fila);
            this.ventanaPrestamos.setPrestamo(prestamo);
            this.setEstado(Estado.EDICION);
        }
    }

    /** Realización de la acción de borrado. */
    private void accionBorrar() {
        PrestamoDAO dao;
        Prestamo prestamos[];
        int fila;
        
        prestamos = this.modeloPrestamos.getPrestamosMarcados();
        if (prestamos != null && prestamos.length > 0) {
            if ((Messages.mensaje(Messages.Tipo.QUESTION, "MSG_PREGUNTA_BORRAR_PRESTAMOS")) == 0) {
                dao = new PrestamoDAO(this.controlador.getConexion());
                for (Prestamo prestamo : prestamos) {
                    try {
                        dao.eliminarPrestamo(prestamo);
                        this.modeloPrestamos.deletePrestamo(prestamo);
                    } catch (ErrorException ex) {
                        Messages.mensaje(ex, "MSG_ERROR_ELIMINAR_PRESTAMO", prestamo.getIdPrestamo());
                    }
                }
                this.setEstado(Estado.LECTURA);
                if (this.modeloPrestamos.getRowCount() > 0) {
                    fila = this.ventanaPrestamos.getFilaSeleccionada();
                    if (fila < 0) this.ventanaPrestamos.setFilaSeleccionada(0);
                }
            }
        } else {
            Messages.mensaje(Messages.Tipo.INFORMATION, "MSG_AVISO_NO_PRESTAMOS");
        }
    }
    
    /** Realización de la acción de aceptación de los cambios realizados. */
    private void accionAceptar() {
        PrestamoDAO dao;
        Prestamo prestamo;
        
        prestamo = this.ventanaPrestamos.getPrestamo();
        if (this.validarPrestamo(prestamo)) {
            dao = new PrestamoDAO(this.controlador.getConexion());
            try {
                switch (this.estado) {
                    case EDICION:
                        dao.actualizarPrestamo(prestamo);
                        this.modeloPrestamos.setPrestamoAt(this.ventanaPrestamos.getFilaSeleccionada(), prestamo);
                        break;

                    case CREACION:
                        dao.insertarPrestamo(prestamo);
                        this.modeloPrestamos.addPrestamo(prestamo);
                        break;
                }
                this.ventanaPrestamos.limpiarFicha();
                this.setEstado(Estado.LECTURA);
            } catch (InfoException ex) {
                Messages.mensaje(ex, "MSG_ATENCION_PRESTAMO_DUPLICADO");
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_GUARDAR_PRESTAMO", prestamo.getIdPrestamo());
            }
        } else {
            Messages.mensaje(Messages.Tipo.WARNING, "MSG_ATENCION_PRESTAMO_VACIO");
        }
    }

    /** Realización de la acción de cancelación de los cambios realizados. */
    private void accionCancelar() {
        this.ventanaPrestamos.limpiarFicha();
        this.setEstado(Estado.LECTURA);
    }
    
    /** Realización de la acción de impresión del listado de libros. */
    private void accionImprimir() {
        String plantilla = Constantes.REPORTS_PATH+"/ListadoPrestamos.jasper";
        
        // Parámetros
        Map<String, Object> parametros = new TreeMap<>();
        parametros.put("tituloInforme", Messages.getString("LISTADO_PRESTAMOS"));
        parametros.put("subtituloInforme", "");
        parametros.put("id", Messages.getString("HEADER_ID"));
        parametros.put("socio", Messages.getString("HEADER_SOCIO"));
        parametros.put("libro", Messages.getString("HEADER_LIBRO"));
        parametros.put("fechaInicio", Messages.getString("HEADER_INICIO"));
        parametros.put("fechaFin", Messages.getString("HEADER_FIN"));
        
        this.generarInformeModelo(modeloPrestamos, plantilla, parametros);
    }
    
    /** Realización de la acción de selección de una fila del listado. */
    private void accionSeleccionar() {
        if (this.estado == Estado.LECTURA || this.estado == Estado.MARCADO) {
            this.setEstado(Estado.LECTURA);
        }
    }
    
    /**
     * Realización de la acción al editar una fecha directamente en el listado
     * de préstamos: validar los cambios y guardarlos en la base de datos.
     * @param index Índice del modelo del préstamo que ha sido modificado.
     */
    private void accionEditarFecha(int index) {
        PrestamoDAO dao;
        Prestamo prestamo;
        
        prestamo = this.modeloPrestamos.getPrestamoAt(index);
        if (this.validarPrestamo(prestamo)) {
            dao = new PrestamoDAO(this.controlador.getConexion());
            try {
                dao.actualizarPrestamo(prestamo);
                //this.modeloPrestamos.setPrestamoAt(this.ventanaPrestamos.getFilaSeleccionada(), prestamo);
            } catch (InfoException ex) {
                Messages.mensaje(ex, "MSG_ATENCION_PRESTAMO_DUPLICADO");
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_GUARDAR_PRESTAMO", prestamo.getIdPrestamo());
            }
        } else {
            Messages.mensaje(Messages.Tipo.WARNING, "MSG_ATENCION_PRESTAMO_VACIO");
        }
    }
    
    /**
     * Establecer el estado indicado para el mantenimiento de préstamos.
     * @param nuevoEstado Estado a establecer.
     */
    private void setEstado(Estado nuevoEstado) {
        if (nuevoEstado == Estado.LECTURA) {
            if (this.modeloPrestamos.hayPrestamosMarcados()) nuevoEstado = Estado.MARCADO;
        }
        this.estado = nuevoEstado;
        this.ventanaPrestamos.setEstado(this.estado);
    }
    
    /**
     * Comprobar si los datos del préstamo indicado son válidos (control de campos vacíos).
     * @param prestamo Préstamo a validar.
     * @return Indica si es válido (TRUE) o no (FALSE).
     */
    private boolean validarPrestamo(Prestamo prestamo) {
        if (prestamo == null) throw new IllegalArgumentException("Prestamo no válido.");
        
        if (prestamo.getIdSocio()== null) return false;
        if (prestamo.getIdLibro()== null) return false;
        if (prestamo.getFechaInicio()== null) return false;
        if (prestamo.getFechaFin()== null) return false;
        
        return true;
    }
    
    /**
     * Obtener si la ventana de mantenimiento de préstamos está cerrada.
     * @return Indica si está cerrada (TRUE) o no (FALSE).
     */
    public boolean vistaCerrada() {
        return this.ventanaPrestamos.isClosed();
    }
    
    /**
     * Evento de selección de una fila del listado de préstamos.
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

    /**
     * Evento de edición de los datos del listado de préstamos.
     * @param e Datos del evento.
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        // Guardamos en BD la edición de las fechas de inicio y fin
        if (e.getColumn() > 3) this.accionEditarFecha(e.getFirstRow());
    }
}
