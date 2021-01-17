package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.customers.dao.ICustomersDAO;
import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.customers.dao.CustomersDAOException;
import es.csc.biblioteca.gui.main.TableRow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class CustomersTableModel extends AbstractTableModel {

    protected static final Logger logger = Logger.getLogger(CustomersTableModel.class);
    
    private List<TableRow<CustomerDTO>> customers;
    private final ICustomersDAO dao;
    private String [] columnNames = {"", "Id", "First Name", "Last Name", "DNI", "Address", "Creation Date"};

    public CustomersTableModel(ICustomersDAO dao) {
        this.dao = dao;
        this.customers = new ArrayList<>();
    }

    public CustomersTableModel(ICustomersDAO dao, List<CustomerDTO> customers) {
        this.dao = dao;
        this.customers = new ArrayList<>();
        if (customers != null && customers.size() > 0) {
            for (CustomerDTO dto : customers) {
                this.customers.add(new TableRow<>(dto));
            }
        }
    }
    
    @Override
    public int getRowCount() {
        return this.customers.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if (columnIndex == 0) {
            Boolean selected = this.customers.get(rowIndex).getIsSelected();
            value = selected;
        } else {
            CustomerDTO dto = this.customers.get(rowIndex).getT();
            switch (columnIndex) {
                case 1:
                    value = dto.getIdSocio();
                    break;
                case 2:
                    value = dto.getNombre();
                    break;
                case 3:
                    value = dto.getApellidos();
                    break;
                case 4:
                    value = dto.getDni();
                    break;
                case 5:
                    value = dto.getDireccion();
                    break;
                case 6:
                    value = dto.getFechaAlta();
                    break;
                default:
                    throw new IndexOutOfBoundsException("Nº de columna no válido.");
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            TableRow<CustomerDTO> row = this.customers.get(rowIndex);
            row.setIsSelected((Boolean) aValue);
            this.customers.set(rowIndex, row);
        }
    }
    
    public CustomerDTO getCustomerAt(int rowIndex) {
        return this.customers.get(rowIndex).getT();
    }

    public void setCustomerAt(CustomerDTO dto, int rowIndex) {
        try {
            this.dao.update(dto.getIdSocio(), dto);
            TableRow<CustomerDTO> row = this.customers.get(rowIndex);
            row.setT(dto);
            this.customers.set(rowIndex, row);
            this.fireTableRowsUpdated(rowIndex, rowIndex);
        } catch (CustomersDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void addCustomer(CustomerDTO dto) {
        try {
            this.dao.insert(dto);
            this.customers.add(new TableRow<>(dto));
            this.fireTableRowsInserted(this.customers.size() - 1, this.customers.size() - 1);
        } catch (CustomersDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void removeCustomer(int rowIndex) {
        int idSocio = this.customers.get(rowIndex).getT().getIdSocio();
        try {
            this.dao.delete(idSocio);
        } catch (CustomersDAOException ex) {
            logger.error(ex.toString());
        }
        this.customers.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    private List<CustomerDTO> getCustomers() {
        List<CustomerDTO> al = new ArrayList<>();
        for (TableRow<CustomerDTO> row : this.customers) {
            al.add(row.getT());
        }
        return al;
    }
    
    public void deleteSelectedCustomers() {
        Iterator<TableRow<CustomerDTO>> it = this.customers.iterator();
        while (it.hasNext()) {
            TableRow<CustomerDTO> row = it.next();
            if (row.getIsSelected()) {
                try {
                    this.dao.delete(row.getT().getIdSocio());
                    it.remove();
                } catch (CustomersDAOException ex) {
                    logger.error(ex.toString());
                }
            }
        }
        this.fireTableDataChanged();
    }
    
    public void printCustomers() {
        try {
            //JRBeanCollectionDataSource
            //JRJpaDataSource
            //JRBeanArrayDataSource
            CustomersDataSource datasource = new CustomersDataSource(getCustomers());
            JasperReport reporte = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("reports/socios-report.jasper")); // "reports/socios-report.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (JRException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void previewCustomers() {
        try {
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(getCustomers());
            //CustomersDataSource datasource = new CustomersDataSource(this.model.getTableModel().getCustomers());
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("reports/socios-report.jasper")); // "reports/socios-report.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
            //JasperViewer.viewReport(jasperPrint, false);
            
        } catch (JRException ex) {
            logger.error(ex.toString());
        }
    }
    
}
