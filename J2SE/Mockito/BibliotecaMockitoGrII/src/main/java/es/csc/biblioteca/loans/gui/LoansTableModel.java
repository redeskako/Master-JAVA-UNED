package es.csc.biblioteca.loans.gui;

import es.csc.biblioteca.loans.dao.LoanDTO;
import es.csc.biblioteca.gui.main.TableRow;
import es.csc.biblioteca.loans.dao.ILoansDAO;
import es.csc.biblioteca.loans.dao.LoansDAOException;
import java.awt.Color;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class LoansTableModel extends AbstractTableModel {
    
    protected static final Logger logger = Logger.getLogger(LoansTableModel.class);
    
    private List<TableRow<LoanDTO>> loans;
    private ILoansDAO dao;
    private String [] columnNames = new String [] {"", "Id", "Title", "Customer", "Start Date", "End Date"};
    private Class [] columnClass = new Class [] {Boolean.class, Integer.class, String.class, String.class, Date.class, Date.class};
    

    private static final Color RED = new Color(255, 120, 120);
    private static final Color YELLOW = Color.YELLOW;
    private static final Color GREEN = new Color(120, 255, 120);
    
    public LoansTableModel(ILoansDAO dao) {
        this.dao = dao;
        this.loans = new ArrayList<>();
    }
    
    public LoansTableModel(ILoansDAO dao, List<LoanDTO> loans) {
        this.dao = dao;
        this.loans = new ArrayList<>();
        if (loans != null && loans.size() > 0) {
            for (LoanDTO dto : loans) {
                this.loans.add(new TableRow<>(dto));
            }
        }
    }

    @Override
    public int getRowCount() {
        return this.loans.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 0) || (columnIndex == 4) || (columnIndex == 5);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.columnClass[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if (columnIndex == 0) {
            value = this.loans.get(rowIndex).getIsSelected();
        } else {
            LoanDTO dto = this.loans.get(rowIndex).getT();
            switch (columnIndex) {
                case 1:
                    value = dto.getIdPrestamo();
                    break;
                case 2:
                    value = dto.getBook().getNombre();
                    break;
                case 3:
                    value = dto.getCustomer().getFullName();
                    break;
                case 4:
                    value = dto.getFechaInicio();
                    break;
                case 5:
                    value = dto.getFechaFin();
                    break;
                default:
                    throw new IndexOutOfBoundsException("Nº de columna no válido.");
            }
        }
        return value;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                this.loans.get(rowIndex).setIsSelected((Boolean) aValue);
                break;
            case 4:
            case 5:
                LoanDTO t = this.loans.get(rowIndex).getT();
                if (aValue != null) {
                    long value = ((java.util.Date) aValue).getTime();
                    if (columnIndex == 4) {
                        t.setFechaInicio(new Date(value));
                    } else {
                        t.setFechaFin(new Date(value));
                    }
                    try {
                        this.dao.update(t.getIdPrestamo(), t);
                        this.fireTableRowsUpdated(rowIndex, rowIndex);
                    } catch (LoansDAOException ex) {
                        logger.error(ex.toString());
                    }
                }
                break;
        }
    }
    
    /**
     * Devuelve el color de un prestamo atendiendo al número de días
     * que faltan para que termine el prestamo.
     * 
     * @param row
     * @return 
     */
    public Color getRowColour(int row) {

        // Nº de días y color por defecto
        int days = 5;
        Color color = Color.WHITE;
        
        // Obtenemos la fecha actual y la fecha de finalización del prestamo
        java.util.Date hoy = new java.util.Date();
        java.util.Date fechaFin = null;
        try {
            fechaFin = new java.util.Date(this.getLoanAt(row).getFechaFin().getTime());
        } catch (NullPointerException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("El prestamo {0} no tiene fecha de finalización", row));
            }
        }

        // Fila con fondo en color verde si fecha_actual < fecha_fin - 5.
        // Fila con fondo en color amarillo si fecha_actual >= fecha_fin - 5.
        // Fila con fondo en color rojo si fecha_actual > fecha_fin.
        if (fechaFin != null) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaFin);
            calendar.add(Calendar.DAY_OF_YEAR, -5);
            java.util.Date fechaAlerta = calendar.getTime();
            
            if (hoy.before(fechaAlerta)) {
                color = GREEN;
            } else if (hoy.before(fechaFin) && hoy.after(fechaAlerta)) {
                color = YELLOW;
            } else {
                color = RED;
            }
        }
        
        return color;
        
    }
    
    public LoanDTO getLoanAt(int rowIndex) {
        return this.loans.get(rowIndex).getT();
    }

    public void setLoanAt(LoanDTO dto, int rowIndex) {
        try {
            this.dao.update(dto.getIdPrestamo(), dto);
            TableRow<LoanDTO> row = this.loans.get(rowIndex);
            row.setT(dto);
            this.loans.set(rowIndex, row);
            this.fireTableRowsUpdated(rowIndex, rowIndex);
        } catch (LoansDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void addLoan(LoanDTO dto) {
        try {
            this.dao.insert(dto);
            this.loans.add(new TableRow<>(dto));
            this.fireTableRowsInserted(this.loans.size() - 1, this.loans.size() - 1);
        } catch (LoansDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void removeLoan(int rowIndex) {
        int idSocio = this.loans.get(rowIndex).getT().getIdPrestamo();
        try {
            this.dao.delete(idSocio);
        } catch (LoansDAOException ex) {
            logger.error(ex.toString());
        }
        
        this.loans.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    private List<LoanDTO> getLoans() {
        List<LoanDTO> al = new ArrayList<>();
        for (TableRow<LoanDTO> row : this.loans) {
            al.add(row.getT());
        }
        return al;
    }
    
    public void deleteSelectedLoans() {
        Iterator<TableRow<LoanDTO>> it = this.loans.iterator();
        while (it.hasNext()) {
            TableRow<LoanDTO> row = it.next();
            if (row.getIsSelected()) {
                try {
                    this.dao.delete(row.getT().getIdPrestamo());
                    it.remove();
                } catch (LoansDAOException ex) {
                    logger.error(ex.toString());
                }
            }
        }
        this.fireTableDataChanged();
    }
    
    public void printLoans() {
        // TODO
    }
    
    public void previewLoans() {
        // TODO
    }

}
