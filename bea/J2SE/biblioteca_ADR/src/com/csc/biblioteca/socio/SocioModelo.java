/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 08/01/2016
 */

package com.csc.biblioteca.socio;

import com.csc.biblioteca.util.ModeloBase;
import java.sql.Date;
import java.util.ArrayList;

/** Modelo del mantenimiento de socios (MVC). */
public class SocioModelo extends ModeloBase {
    private ArrayList<Socio> listado;
    private final ArrayList<Boolean> filasMarcadas;

    /** Constructor del modelo de socios. */
    public SocioModelo() {
        // Definimos las características del modelo en el constructor
        this.filasMarcadas = new ArrayList<>();
        this.nombreColumnas = new String[] {"", "HEADER_ID", "HEADER_DNI", "HEADER_NOMBRE", "HEADER_APELLIDOS", "HEADER_DIRECCION", "HEADER_ALTA"};
        this.anchoColumnas = new int[]{20, 80, 90, 0, 0, 0, 80};
        this.tipoColumnas = new Class[] {Boolean.class, Integer.class, String.class, String.class, String.class, String.class, Date.class};
    }
    
    /**
     * Establecer el listado de socios que contiene el modelo y se mostrará
     * en la ventana de mantenimiento.
     * @param listado Listado de socios.
     */
    public void setListado(ArrayList<Socio> listado) {
        this.listado = listado;
        this.filasMarcadas.clear();
        for (int i = 0; i < this.listado.size(); i++) {
            this.filasMarcadas.add(false);
        }
    }
    
    /**
     * Obtener el listado de socios que contiene el modelo.
     * @return Listado de socios.
     */
    @Override
    public ArrayList<Socio> getListado() {
        return listado;
    }
    
    /**
     * Obtener si hay socios marcados en el listado (casilla activada).
     * @return Indica si hay (TRUE) o no (FALSE).
     */
    public boolean haySociosMarcados() {
        return this.filasMarcadas.contains(true);
    }
    
    /**
     * Obtener el listado de socios marcados en el listado.
     * @return Array de socios marcados.
     */
    public Socio[] getSociosMarcados() {
        ArrayList<Socio> seleccionados = new ArrayList<>();
        Socio socios[];
        int index = 0;
        
        for(Boolean seleccionado : this.filasMarcadas) {
            if (seleccionado) seleccionados.add(this.listado.get(index));
            index++;
        }
        socios = new Socio[seleccionados.size()];
        seleccionados.toArray(socios);
        
        return socios;
    }
    
    /**
     * Obtener el socio de la posición indicada del modelo.
     * @param index Índice del socio a obtener.
     * @return Socio en la posición indicada.
     */
    public Socio getSocioAt(int index) {
        return this.listado.get(index);
    }
    
    /**
     * Establecer un socio en la posición indicada del modelo.
     * @param index Índice del modelo en el que asignar el socio.
     * @param socio Socio a asignar.
     */
    public void setSocioAt(int index, Socio socio) {
        this.listado.set(index, socio);
        this.filasMarcadas.set(index, false);
        fireTableRowsUpdated(index, index);
    }
    
    /**
     * Añadir el socio indicado al listado del modelo.
     * @param socio Socio a añadir al modelo.
     */
    public void addSocio(Socio socio) {
        int total = this.listado.size();
        this.listado.add(socio);
        this.filasMarcadas.add(false);
        fireTableRowsInserted(total, total);
    }
    
    /**
     * Eliminar el socio del listado del modelo de la posición indicada.
     * @param index Índice del socio a eliminar.
     */
    public void deleteSocio(int index) {
        this.listado.remove(index);
        this.filasMarcadas.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    /**
     * Eliminar el socio indicado del listado del modelo.
     * @param socio Socio a eliminar del modelo.
     */
    public void deleteSocio(Socio socio) {
        int index = this.listado.indexOf(socio);
        if (index >= 0) this.deleteSocio(index);
    }
    
    /**
     * Obtener el número de elementos que contiene el listado del modelo.
     * @return Número de socios.
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
        Socio socio;
        Object valor = null;
        
        if (columnIndex == 0) {
            valor = this.filasMarcadas.get(rowIndex);
            
        } else {
            socio = this.listado.get(rowIndex);

            switch (columnIndex) {
                case 1:
                    valor = socio.getIdSocio();
                    break;
                case 2:
                    valor = socio.getDni();
                    break;
                case 3:
                    valor = socio.getNombre();
                    break;
                case 4:
                    valor = socio.getApellidos();
                    break;
                case 5:
                    valor = socio.getDireccion();
                    break;
                case 6:
                    valor = socio.getFechaAlta();
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
        Socio socio;
        if (columnIndex == 0) {
            this.filasMarcadas.set(rowIndex, (Boolean)aValue);
            
        } else {
            socio = this.listado.get(rowIndex);
            
            switch (columnIndex) {
                case 1:
                    socio.setIdSocio((Integer)aValue);
                    break;
                case 2:
                    socio.setDni((String)aValue);
                    break;
                case 3:
                    socio.setNombre((String)aValue);
                    break;
                case 4:
                    socio.setApellidos((String)aValue);
                    break;
                case 5:
                    socio.setDireccion((String)aValue);
                    break;
                case 6:
                    socio.setFechaAlta((Date)aValue);
                    break;
            }
        }
    }
}
