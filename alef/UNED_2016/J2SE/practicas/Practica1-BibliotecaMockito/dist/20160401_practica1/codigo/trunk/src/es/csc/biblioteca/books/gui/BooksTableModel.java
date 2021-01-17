package es.csc.biblioteca.books.gui;

import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.books.dao.BooksDAOException;
import es.csc.biblioteca.books.dao.IBooksDAO;
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
public class BooksTableModel extends AbstractTableModel {
    
    protected static final Logger logger = Logger.getLogger(BooksTableModel.class);
    
    private List<TableRow<BookDTO>> books;
    private IBooksDAO dao;
    private String [] columnNames = {"", "Id", "Title", "Author", "Topic"};
    
    public BooksTableModel(IBooksDAO dao) {
        this.dao = dao;
        this.books = new ArrayList<>();
    }
    
    public BooksTableModel(IBooksDAO dao, List<BookDTO> books) {
        this.dao = dao;
        this.books = new ArrayList<>();
        if (books != null && books.size() > 0) {
            for (BookDTO dto : books) {
                this.books.add(new TableRow<>(dto));
            }
        }
    }

    @Override
    public int getRowCount() {
        return this.books.size();
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
            Boolean selected = this.books.get(rowIndex).getIsSelected();
            value = selected;
        } else {
            BookDTO dto = this.books.get(rowIndex).getT();
            switch (columnIndex) {
                case 1:
                    value = dto.getIdLibro();
                    break;
                case 2:
                    value = dto.getNombre();
                    break;
                case 3:
                    value = dto.getAutor();
                    break;
                case 4:
                    value = dto.getTema();
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
            TableRow<BookDTO> row = this.books.get(rowIndex);
            row.setIsSelected((Boolean) aValue);
            this.books.set(rowIndex, row);
        }
    }
    
    public BookDTO getBookAt(int rowIndex) {
        return this.books.get(rowIndex).getT();
    }

    public void setBookAt(BookDTO dto, int rowIndex) {
        try {
            this.dao.update(dto.getIdLibro(), dto);
            TableRow<BookDTO> row = this.books.get(rowIndex);
            row.setT(dto);
            this.books.set(rowIndex, row);
            this.fireTableRowsUpdated(rowIndex, rowIndex);
        } catch (BooksDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void addBook(BookDTO dto) {
        try {
            this.dao.insert(dto);
            this.books.add(new TableRow<>(dto));
            this.fireTableRowsInserted(this.books.size() - 1, this.books.size() - 1);
        } catch (BooksDAOException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void removeBook(int rowIndex) {
        int idSocio = this.books.get(rowIndex).getT().getIdLibro();
        try {
            this.dao.delete(idSocio);
        } catch (BooksDAOException ex) {
            logger.error(ex.toString());
        }
        
        this.books.remove(rowIndex);
        //this.selectedColumns.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    private List<BookDTO> getBooks() {
        List<BookDTO> al = new ArrayList<>();
        for (TableRow<BookDTO> row : this.books) {
            al.add(row.getT());
        }
        return al;
    }
    
    public void deleteSelectedBooks() {
        Iterator<TableRow<BookDTO>> it = this.books.iterator();
        while (it.hasNext()) {
            TableRow<BookDTO> row = it.next();
            if (row.getIsSelected()) {
                try {
                    this.dao.delete(row.getT().getIdLibro());
                    it.remove();
                } catch (BooksDAOException ex) {
                    logger.error(ex.toString());
                }
            }
        }
        this.fireTableDataChanged();
    }
    
    public void printBooks() {
        try {
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(getBooks());
            JasperReport reporte = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("reports/books-report.jasper")); // "reports/socios-report.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (JRException ex) {
            logger.error(ex.toString());
        }
    }
    
    public void previewBooks() {
        try {
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(getBooks());
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("reports/books-report.jasper")); // "reports/socios-report.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
            
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setVisible(true);
            
        } catch (JRException ex) {
            logger.error(ex.toString());
        }
    }
    
}
