package es.csc.biblioteca.gui.main;

import java.util.Objects;

/**
 *
 * @author David Atencia
 * @version 0.1
 * @param <T>
 */
public class TableRow<T> {
    
    private T t;
    private Boolean isSelected;

    public TableRow(T t) {
        this.t = t;
        this.isSelected = Boolean.FALSE;
    }

    public T getT() {
        return this.t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Boolean getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.t);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableRow<?> other = (TableRow<?>) obj;
        return Objects.equals(this.t, other.t);
    }
    
}
