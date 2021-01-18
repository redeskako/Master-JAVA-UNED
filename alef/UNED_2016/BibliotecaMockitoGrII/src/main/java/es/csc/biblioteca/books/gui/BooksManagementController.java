package es.csc.biblioteca.books.gui;

import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.books.dao.BooksDAOException;
import es.csc.biblioteca.books.dao.BooksDAOImpl;
import es.csc.biblioteca.books.dao.IBooksDAO;
import es.csc.biblioteca.i18n.LocaleManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class BooksManagementController implements ActionListener, Observer {

    protected static final Logger logger = Logger.getLogger(BooksManagementController.class);
    
    private final IBooksDAO dao;
    private final BooksManagementView view;
    private final BooksTableModel model;
    
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";
    private static final String ACTION_PRINT = "Print";
    private static final String ACTION_PREVIEW = "Preview";
    
    public BooksManagementController(Connection connection) throws BooksDAOException {
        //this.model = new BooksModel(connection);
        this.dao = new BooksDAOImpl(connection);
        this.model = new BooksTableModel(this.dao, this.dao.findAll());
        this.view = new BooksManagementView();
        this.view.changeLanguage();
        this.view.setTableModel(this.model); //.getTableModel());
        this.view.setRowSorter(new TableRowSorter(this.model)); //.getTableModel()));
        this.view.setVisible(true);
        addListeners();
        LocaleManager.getInstance().addObserver(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_ADD:
                addBook();
                break;
            case ACTION_EDIT:
                editBook();
                break;
            case ACTION_DELETE:
                deleteSelectedBooks();
                break;
            case ACTION_PRINT:
                this.model.printBooks();
                break;
            case ACTION_PREVIEW:
                this.model.previewBooks();
                break;
        }
    }
    
    private void addListeners() {
        this.view.setAddListener(this, ACTION_ADD);
        this.view.setEditListener(this, ACTION_EDIT);
        this.view.setDeleteListener(this, ACTION_DELETE);
        this.view.setPrintListener(this, ACTION_PRINT);
        this.view.setPreviewListener(this, ACTION_PREVIEW);
    }
    
    public BooksManagementView getView() {
        return this.view;
    }
    
    private void addBook() {
        // Abrimos la ventana para dar de alta un nuevo usuario
        BookDetailsController controller = new BookDetailsController();
        if (controller.getReturnStatus() == BookDetailsController.RET_OK) {
            this.model.addBook(controller.getBook());
            int index = this.model.getRowCount();
            this.view.setSelectedRow(index - 1);
        }
    }
    
    private void editBook() {
        // Abrimos la ventana para dar de alta un nuevo usuario
        int selectedRow = this.view.getSelectedRow();
        BookDTO dto = this.model.getBookAt(selectedRow);
        BookDetailsController controller = new BookDetailsController(dto);
        if (controller.getReturnStatus() == BookDetailsController.RET_OK) {
            this.model.setBookAt(controller.getBook(), selectedRow);
            this.view.setSelectedRow(selectedRow);
        }
    }
    
    private void deleteSelectedBooks() {
        JOptionPane p = new JOptionPane();
        this.model.deleteSelectedBooks();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocaleManager) {
            this.view.changeLanguage();
        }
    }
    
}
