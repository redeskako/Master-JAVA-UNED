/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 21/12/2015
 */

package com.csc.biblioteca.libro;

import com.csc.biblioteca.util.ModeloBase;
import java.util.ArrayList;

/** Modelo del mantenimiento de libros (MVC). */
public class LibroModelo extends ModeloBase {
    private ArrayList<Libro> listado;
    private final ArrayList<Boolean> filasMarcadas;

    /** Constructor del modelo de libros. */
    public LibroModelo() {
        // Definimos las características del modelo en el constructor
        this.filasMarcadas = new ArrayList<>();
        this.nombreColumnas = new String[]{"", "HEADER_ID", "HEADER_TITULO", "HEADER_AUTOR", "HEADER_TEMA"};
        this.anchoColumnas = new int[]{20, 80, 0, 0, 0};
        this.tipoColumnas = new Class[] {Boolean.class, Integer.class, String.class, String.class, String.class};
    }
    
    /**
     * Establecer el listado de libros que contiene el modelo y se mostrará
     * en la ventana de mantenimiento.
     * @param listado Listado de libros.
     */
    public void setListado(ArrayList<Libro> listado) {
        this.listado = listado;
        this.filasMarcadas.clear();
        for (int i = 0; i < this.listado.size(); i++) {
            this.filasMarcadas.add(false);
        }
    }
    
    /**
     * Obtener el listado de libros que contiene el modelo.
     * @return Listado de libros.
     */
    @Override
    public ArrayList<Libro> getListado() {
        return listado;
    }
    
    /**
     * Obtener si hay libros marcados en el listado (casilla activada).
     * @return Indica si hay (TRUE) o no (FALSE).
     */
    public boolean hayLibrosMarcados() {
        return this.filasMarcadas.contains(true);
    }
    
    /**
     * Obtener el listado de libros marcados en el listado.
     * @return Array de libros marcados.
     */
    public Libro[] getLibrosMarcados() {
        ArrayList<Libro> seleccionados = new ArrayList<>();
        Libro libros[];
        int index = 0;
        
        for(Boolean seleccionado : this.filasMarcadas) {
            if (seleccionado) seleccionados.add(this.listado.get(index));
            index++;
        }
        libros = new Libro[seleccionados.size()];
        seleccionados.toArray(libros);
        
        return libros;
    }
    
    /**
     * Obtener el libro de la posición indicada del modelo.
     * @param index Índice del libro a obtener.
     * @return Libro en la posición indicada.
     */
    public Libro getLibroAt(int index) {
        return this.listado.get(index);
    }
    
    /**
     * Establecer un libro en la posición indicada del modelo.
     * @param index Índice del modelo en el que asignar el libro.
     * @param libro Libro a asignar.
     */
    public void setLibroAt(int index, Libro libro) {
        this.listado.set(index, libro);
        this.filasMarcadas.set(index, false);
        fireTableRowsUpdated(index, index);
    }
    
    /**
     * Añadir el libro indicado al listado del modelo.
     * @param libro Libro a añadir al modelo.
     */
    public void addLibro(Libro libro) {
        int total = this.listado.size();
        this.listado.add(libro);
        this.filasMarcadas.add(false);
        fireTableRowsInserted(total, total);
    }
    
    /**
     * Eliminar el libro del listado del modelo de la posición indicada.
     * @param index Índice del libro a eliminar.
     */
    public void deleteLibro(int index) {
        this.listado.remove(index);
        this.filasMarcadas.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    /**
     * Eliminar el libro indicado del listado del modelo.
     * @param libro Libro a eliminar del modelo.
     */
    public void deleteLibro(Libro libro) {
        int index = this.listado.indexOf(libro);
        if (index >= 0) this.deleteLibro(index);
    }
    
    /**
     * Obtener el número de elementos que contiene el listado del modelo.
     * @return Número de libros.
     */
    @Override
    public int getRowCount() {
        return this.listado.size();
    }
    
    /**
     * Obtener si una celda de la tabla es editable.
     * @param rowIndex Fila de la tabla.
     * @param columnIndex Columna de la tabla.
     * @return Indica si es editable (TRUE) o no (FALSE).
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 0);
    }
    
    /**
     * Obtener el valor de la celda indicada.
     * @param rowIndex Fila de la tabla.
     * @param columnIndex Columna de la tabla.
     * @return Valor de la celda.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Libro libro;
        Object valor = null;
        
        if (columnIndex == 0) {
            valor = this.filasMarcadas.get(rowIndex);
            
        } else {
            libro = this.listado.get(rowIndex);

            switch (columnIndex) {
                case 1:
                    valor = libro.getIdLibro();
                    break;
                case 2:
                    valor = libro.getTitulo();
                    break;
                case 3:
                    valor = libro.getAutor();
                    break;
                case 4:
                    valor = libro.getTema();
                    break;
            }
        }
        return valor;
    }

    /**
     * Establecer un valor en la celda indicada.
     * @param aValue Valor a establecer.
     * @param rowIndex Fila de la tabla.
     * @param columnIndex Columna de la tabla.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Libro libro;
        if (columnIndex == 0) {
            this.filasMarcadas.set(rowIndex, (Boolean)aValue);
            
        } else {
            libro = this.listado.get(rowIndex);
            
            switch (columnIndex) {
                case 1:
                    libro.setIdLibro((Integer)aValue);
                    break;
                case 2:
                    libro.setTitulo((String)aValue);
                    break;
                case 3:
                    libro.setAutor((String)aValue);
                    break;
                case 4:
                    libro.setTema((String)aValue);
                    break;
            }
        }
    }
}
