/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.2 - 07/01/2016
 */

package com.csc.biblioteca.prestamo;

import com.csc.biblioteca.util.ModeloBase;
import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/** Modelo del mantenimiento de préstamos (MVC). */
public class PrestamoModelo extends ModeloBase {
    private static final Color PRESTAMO_CUMPLIDO = new Color(255, 128, 128);
    private static final Color PRESTAMO_FINALIZANDO = Color.YELLOW;
    private static final Color PRESTAMO_CORRECTO = Color.GREEN;
    
    private ArrayList<Prestamo> listado;
    private final ArrayList<Boolean> filasMarcadas;

    /** Constructor del modelo de préstamos. */
    public PrestamoModelo() {
        // Definimos las características del modelo en el constructor
        this.filasMarcadas = new ArrayList<>();
        this.nombreColumnas = new String[]{"", "HEADER_ID", "HEADER_SOCIO", "HEADER_LIBRO", "HEADER_INICIO", "HEADER_FIN"};
        this.anchoColumnas = new int[]{20, 80, 0, 0, 80, 80};
        this.tipoColumnas = new Class[] {Boolean.class, Integer.class, String.class, String.class, Date.class, Date.class};
    }
    
    /**
     * Obtener el color de fondo de la fila de la tabla, comparando la fecha de 
     * fin del préstamo con la fecha actual. El color es:
     *      Verde si la fecha_actual es menor que 5 días antes de la fecha de fin.
     *      Amarillo si la fecha actual está entre la fecha de fin y 5 días antes.
     *      Rojo si la fecha actual es mayor que la fecha de fin del préstamo.
     * @param fila Número de fila sobre la que hay que calcular su color de fondo.
     * @return El color de fondo de la fila de la tabla.
     */
    public Color getFilaColor(int fila) {
        Color color = PRESTAMO_CUMPLIDO;
        Calendar calendar;
        java.util.Date fechaFin, hoy;
        
        if (fila >= 0 && fila < this.listado.size()) {
            fechaFin = this.listado.get(fila).getFechaFin();
            if (fechaFin != null) {
                // Obtenemos la fecha y hora actual
                calendar = Calendar.getInstance();
                
                // Establecemos a 0 el valor de horas, minutos, segundos y milisegundos
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                hoy = calendar.getTime();   // Día actual (+00:00:00.000)

                if (hoy.compareTo(fechaFin) <= 0) {
                    calendar.setTime(fechaFin);
                    calendar.add(Calendar.DAY_OF_YEAR, -5);
                    color = (hoy.compareTo(calendar.getTime()) >= 0 ? PRESTAMO_FINALIZANDO : PRESTAMO_CORRECTO);
                }
            }
        }
        return color;
    }
    
    /**
     * Establecer el listado de préstamos que contiene el modelo y se mostrará
     * en la ventana de mantenimiento.
     * @param listado Listado de préstamos.
     */
    public void setListado(ArrayList<Prestamo> listado) {
        this.listado = listado;
        this.filasMarcadas.clear();
        for (int i = 0; i < this.listado.size(); i++) {
            this.filasMarcadas.add(false);
        }
    }
    
    /**
     * Obtener el listado de préstamos que contiene el modelo.
     * @return Listado de préstamos.
     */
    @Override
    public ArrayList<Prestamo> getListado() {
        return listado;
    }
    
    /**
     * Obtener si hay préstamos marcados en el listado (casilla activada).
     * @return Indica si hay (TRUE) o no (FALSE).
     */
    public boolean hayPrestamosMarcados() {
        return this.filasMarcadas.contains(true);
    }
    
    /**
     * Obtener el listado de préstamos marcados en el listado.
     * @return Array de préstamos marcados.
     */
    public Prestamo[] getPrestamosMarcados() {
        ArrayList<Prestamo> seleccionados = new ArrayList<>();
        Prestamo prestamos[];
        int index = 0;
        
        for(Boolean seleccionado : this.filasMarcadas) {
            if (seleccionado) seleccionados.add(this.listado.get(index));
            index++;
        }
        prestamos = new Prestamo[seleccionados.size()];
        seleccionados.toArray(prestamos);
        
        return prestamos;
    }
    
    /**
     * Obtener el préstamo de la posición indicada del modelo.
     * @param index Índice del préstamo a obtener.
     * @return Préstamo en la posición indicada.
     */
    public Prestamo getPrestamoAt(int index) {
        return this.listado.get(index);
    }
    
    /**
     * Establecer un préstamo en la posición indicada del modelo.
     * @param index Índice del modelo en el que asignar el préstamo.
     * @param prestamo Préstamo a asignar.
     */
    public void setPrestamoAt(int index, Prestamo prestamo) {
        this.listado.set(index, prestamo);
        this.filasMarcadas.set(index, false);
        fireTableRowsUpdated(index, index);
    }
    
    /**
     * Añadir el préstamo indicado al listado del modelo.
     * @param prestamo Préstamo a añadir al modelo.
     */
    public void addPrestamo(Prestamo prestamo) {
        int total = this.listado.size();
        this.listado.add(prestamo);
        this.filasMarcadas.add(false);
        fireTableRowsInserted(total, total);
    }
    
    /**
     * Eliminar el préstammo del listado del modelo de la posición indicada.
     * @param index Índice del préstamo a eliminar.
     */
    public void deletePrestamo(int index) {
        this.listado.remove(index);
        this.filasMarcadas.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    /**
     * Eliminar el préstamo indicado del listado del modelo.
     * @param prestamo Préstamo a eliminar del modelo.
     */
    public void deletePrestamo(Prestamo prestamo) {
        int index = this.listado.indexOf(prestamo);
        if (index >= 0) this.deletePrestamo(index);
    }
    
    /**
     * Obtener el número de elementos que contiene el listado del modelo.
     * @return Número de préstamos.
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
        return (columnIndex < 1 || columnIndex > 3);
    }

    /**
     * Obtener el valor de la celda indicada.
     * @param rowIndex Fila de la tabla.
     * @param columnIndex Columna de la tabla.
     * @return Valor de la celda.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prestamo prestamo;
        Object valor = null;
        
        if (columnIndex == 0) {
            valor = this.filasMarcadas.get(rowIndex);
            
        } else {
            prestamo = this.listado.get(rowIndex);

            switch (columnIndex) {
                case 1:
                    valor = prestamo.getIdPrestamo();
                    break;
                case 2:
                    valor = prestamo.getApellidoNombreSocio();
                    break;
                case 3:
                    valor = prestamo.getTituloLibro();
                    break;
                case 4:
                    valor = prestamo.getFechaInicio();
                    break;
                case 5:
                    valor = prestamo.getFechaFin();
                    break;
            }
        }
        return valor;
    }

    /**
     * Se ha cambiado un valor en el listado de la ventana (columnas editables).
     * @param aValue Nuevo valor.
     * @param rowIndex Fila modificada.
     * @param columnIndex Columna modificada.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Prestamo prestamo;
        if (columnIndex == 0) {
            this.filasMarcadas.set(rowIndex, (Boolean)aValue);
            
        } else {
            prestamo = this.listado.get(rowIndex);
            
            switch (columnIndex) {
                case 1:
                    prestamo.setIdPrestamo((Integer)aValue);
                    break;
                case 4:
                    prestamo.setFechaInicio((Date)aValue);
                    this.fireTableCellUpdated(rowIndex, columnIndex);
                    break;
                case 5:
                    prestamo.setFechaFin((Date)aValue);
                    this.fireTableCellUpdated(rowIndex, columnIndex);
                    break;
            }
        }
    }
}
