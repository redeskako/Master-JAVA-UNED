/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 08/12/2015
 */

package com.csc.biblioteca.libro;

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

/** Clase controlador de la ventana de mantenimiento de libros. */
public class LibroControlador extends ControladorBase implements ActionListener, MouseListener {
    
    private LibroVista ventanaLibros;
    private LibroModelo modeloLibros;
    
    /**
     * Constructor del controlador del mantenimiento de libros.
     * @param controlador Clase controladora padre de la ventana principal de la aplicación.
     */
    public LibroControlador(MDIControlador controlador) {
        super(controlador);
    }
    
    /** 
     * Creación de la vista (MVC) y carga de los datos para el mantenimiento de libros.
     * @return Ventana interna generada.
     */
    @Override
    public JInternalFrame crearVista() {
        LibroDAO dao;
        
        if (this.ventanaLibros == null) {
            this.ventanaLibros = new LibroVista(Messages.getString("TITULO_LIBROS"));
            this.ventanaLibros.registrarActionListener(this);
            this.ventanaLibros.registraMouseListener(this);
            
            dao = new LibroDAO(this.controlador.getConexion());
            try {
                this.modeloLibros = new LibroModelo();
                this.modeloLibros.setListado((ArrayList<Libro>) dao.obtenerLibros());
                this.ventanaLibros.setModeloListado(this.modeloLibros);
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_CARGAR_LIBROS");
            }
            this.controlador.addVentana(this.ventanaLibros, false);
        }
        
        return this.ventanaLibros;
    }
    
    /** Método para mostrar la ventana de mantenimiento de libros (maximizada). */
    @Override
    public void mostrarVista() {
        this.controlador.mostrarVentana(this.ventanaLibros, true);
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
        Libro libro;
        int fila = this.ventanaLibros.getFilaSeleccionada();
        
        if (fila >= 0) {
            libro = this.modeloLibros.getLibroAt(fila);
            this.ventanaLibros.setLibro(libro);
            this.setEstado(Estado.EDICION);
        }
    }

    /** Realización de la acción de borrado. */
    private void accionBorrar() {
        LibroDAO dao;
        Libro libros[];
        int fila;
        
        libros = this.modeloLibros.getLibrosMarcados();
        if (libros != null && libros.length > 0) {
            if ((Messages.mensaje(Messages.Tipo.QUESTION, "MSG_PREGUNTA_BORRAR_LIBROS")) == 0) {
                dao = new LibroDAO(this.controlador.getConexion());
                for (Libro libro : libros) {
                    try {
                        dao.eliminarLibro(libro);
                        this.modeloLibros.deleteLibro(libro);
                    } catch (InfoException ex) {
                        if (ex.getErrorCode() == ErrorCode.ERROR_FK_REFERENCE)
                            Messages.mensaje(ex, "MSG_ATENCION_LIBRO_CON_PRESTAMOS", libro.getIdLibro());
                    } catch (ErrorException ex) {
                         Messages.mensaje(ex, "MSG_ERROR_ELIMINAR_LIBRO", libro.getIdLibro());
                    }
                }
                this.setEstado(Estado.LECTURA);
                if (this.modeloLibros.getRowCount() > 0) {
                    fila = this.ventanaLibros.getFilaSeleccionada();
                    if (fila < 0) this.ventanaLibros.setFilaSeleccionada(0);
                }
            }
        } else {
            Messages.mensaje(Messages.Tipo.INFORMATION, "MSG_AVISO_NO_LIBROS");
        }
    }
    
    /** Realización de la acción de aceptación de los cambios realizados. */
    private void accionAceptar() {
        LibroDAO dao;
        Libro libro;
        
        libro = this.ventanaLibros.getLibro();
        if (this.validarLibro(libro)) {
            dao = new LibroDAO(this.controlador.getConexion());
            try {
                switch (this.estado) {
                    case EDICION:
                        dao.actualizarLibro(libro);
                        this.modeloLibros.setLibroAt(this.ventanaLibros.getFilaSeleccionada(), libro);
                        break;

                    case CREACION:
                        dao.insertarLibro(libro);
                        this.modeloLibros.addLibro(libro);
                        break;
                }
                this.ventanaLibros.limpiarFicha();
                this.setEstado(Estado.LECTURA);
            } catch (InfoException ex) {
                Messages.mensaje(ex, "MSG_ATENCION_LIBRO_DUPLICADO", libro.getTitulo());
            } catch (ErrorException ex) {
                Messages.mensaje(ex, "MSG_ERROR_GUARDAR_LIBRO", libro.getIdLibro());
            }
        } else {
            Messages.mensaje(Messages.Tipo.WARNING, "MSG_ATENCION_LIBRO_VACIO");
        }
    }

    /** Realización de la acción de cancelación de los cambios realizados. */
    private void accionCancelar() {
        this.ventanaLibros.limpiarFicha();
        this.setEstado(Estado.LECTURA);
    }
    
    /** Realización de la acción de impresión del listado de libros. */
    private void accionImprimir() {
        String plantilla = Constantes.REPORTS_PATH+"/ListadoLibros.jasper";
        
        // Parámetros
        Map<String, Object> parametros = new TreeMap<>();
        parametros.put("tituloInforme", Messages.getString("LISTADO_LIBROS"));
        parametros.put("subtituloInforme", "");
        parametros.put("id", Messages.getString("HEADER_ID"));
        parametros.put("titulo", Messages.getString("HEADER_TITULO"));
        parametros.put("autor", Messages.getString("HEADER_AUTOR"));
        parametros.put("tema", Messages.getString("HEADER_TEMA"));
        
        this.generarInformeModelo(modeloLibros, plantilla, parametros);
    }
    
    /** Realización de la acción de selección de una fila del listado. */
    private void accionSeleccionar() {
        if (this.estado == Estado.LECTURA || this.estado == Estado.MARCADO) {
            this.setEstado(Estado.LECTURA);
        }
    }
    
    /**
     * Establecer el estado indicado para el mantenimiento de libros.
     * @param nuevoEstado Estado a establecer.
     */
    private void setEstado(Estado nuevoEstado) {
        if (nuevoEstado == Estado.LECTURA) {
            if (this.modeloLibros.hayLibrosMarcados()) nuevoEstado = Estado.MARCADO;
        }
        this.estado = nuevoEstado;
        this.ventanaLibros.setEstado(this.estado);
    }
    
    /**
     * Comprobar si los datos del libro indicado son válidos (control de campos vacíos).
     * @param libro Libro a validar.
     * @return Indica si es válido (TRUE) o no (FALSE).
     */
    private boolean validarLibro(Libro libro) {
        if (libro == null) throw new IllegalArgumentException("Libro no válido.");
        
        if (libro.getTitulo()== null || libro.getTitulo().isEmpty()) return false;
        
        return true;
    }
    
    /**
     * Obtener si la ventana de mantenimiento de libros está cerrada.
     * @return Indica si está cerrada (TRUE) o no (FALSE).
     */
    public boolean vistaCerrada() {
        return this.ventanaLibros.isClosed();
    }
    
    /**
     * Evento de selección de una fila del listado de libros.
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
