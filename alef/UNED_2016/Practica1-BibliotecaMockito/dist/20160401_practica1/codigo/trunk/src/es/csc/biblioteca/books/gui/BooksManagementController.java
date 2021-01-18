package es.csc.biblioteca.books.gui;

import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.books.gui.BooksTableModel;
import es.csc.biblioteca.books.gui.BooksManagementView;
import es.csc.biblioteca.books.dao.BooksDAOException;
import es.csc.biblioteca.books.dao.BooksDAOImpl;
import es.csc.biblioteca.books.dao.BooksDAOImplOracle;
import es.csc.biblioteca.books.dao.IBooksDAO;
import es.csc.biblioteca.i18n.LocaleManager;
import es.csc.biblioteca.jdbc.ConnectionsManager;
import es.csc.biblioteca.util.Config;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;
import es.csc.biblioteca.exceptions.MandatoryFieldException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class BooksManagementController implements ActionListener, Observer {
    protected static final LocaleManager lm = LocaleManager.getInstance();
    
    private static final String ACTION_OK = "Ok";
    private static final String ACTION_CANCEL = "Cancel";

    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    
    private int returnStatus = RET_CANCEL;
    protected static final Logger logger = Logger.getLogger(BooksManagementController.class);
    
    private final IBooksDAO dao;
    private final BooksManagementView view;
    private final BooksTableModel model;
    private BookDTO model1;
    protected Estado estado;
    
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";
    private static final String ACTION_PRINT = "Print";
    private static final String ACTION_PREVIEW = "Preview";
    
     public enum Estado {CREACION, LECTURA, EDICION, MARCADO}
     
    
    public BooksManagementController(Connection connection) throws BooksDAOException {
        //this.model = new BooksModel(connection);
        if(ConnectionsManager.dbms.equals("oracle")){
                this.dao = new BooksDAOImplOracle(connection);
        }
        else{
            this.dao = new BooksDAOImpl(connection);         
        }
        
        this.model = new BooksTableModel(this.dao, this.dao.findAll());
        this.view = new BooksManagementView();
        this.view.changeLanguage();
        this.view.setTableModel(this.model);
        this.view.setRowSorter(new TableRowSorter(this.model));
        this.view.setVisible(true);
        this.view.setSize(1080, 880);          // Dimensiones por defecto
        this.view.setVisible(true);
        this.estado = Estado.LECTURA;       
        this.view.setEstado(this.estado);
        
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
            case ACTION_OK:
                try {                
                    BookDTO dto = this.view.getBook();
                    checkMandatoryFields(dto);
                    
                    if (this.estado == Estado.CREACION){
                        this.model.addBook(dto);}
                    
                    else if (this.estado == Estado.EDICION){
                       System.out.printf("Entramos por Edicion" );
                        int selectedRow = this.view.getSelectedRow();
                        this.model.setBookAt(dto,selectedRow);   
                }
                    
                 this.estado = Estado.LECTURA;       
                 this.view.setEstado(this.estado);
                 this.view.limpiarFicha();  
                 
                } catch (MandatoryFieldException ex) {
                    logger.error("Faltan datos obligatorios.");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("MANDATORY_FIELDS_ERROR_TEXT"),
                                                  lm.getText("MANDATORY_FIELDS_ERROR_TITLE"),
                                                  JOptionPane.WARNING_MESSAGE);
                }
                break;
            case ACTION_CANCEL:
                this.estado = Estado.LECTURA;       
                this.view.setEstado(this.estado);
                this.view.limpiarFicha();
                break;
        }
    }
        
    
    private void addListeners() {
        this.view.setAddListener(this, ACTION_ADD);
        this.view.setEditListener(this, ACTION_EDIT);
        this.view.setDeleteListener(this, ACTION_DELETE);
        this.view.setPrintListener(this, ACTION_PRINT);
        this.view.setPreviewListener(this, ACTION_PREVIEW);
        this.view.setOkListener(this, ACTION_OK);
        this.view.setCancelListener(this, ACTION_CANCEL);   
        this.view.setPopupAddListener(this, ACTION_ADD);
        this.view.setPopupEditListener(this, ACTION_EDIT);
        this.view.setPopupDeleteListener(this, ACTION_DELETE);
    }
   
    public BooksManagementView getView() {
        return this.view;
    }
    
    private void addBook() {
        logger.error("Capturamos clickeo Raton.");
        this.estado = Estado.CREACION;       
        this.view.setEstado(this.estado);
    }
    
    private void editBook() {
        this.estado = Estado.EDICION;       
        this.view.setEstado(this.estado);
        int selectedRow = this.view.getSelectedRow();
        BookDTO dto = this.model.getBookAt(selectedRow); 
        
        if (selectedRow>=0){
            this.view.setBook(dto);
            this.view.setSelectedRow(selectedRow);
        }
    }
    
    private void deleteSelectedBooks() {
        JOptionPane p = new JOptionPane();
        this.model.deleteSelectedBooks();
        this.view.limpiarFicha();
    }
    
     public void BookManagementDetails() {
        this.view.translate();
        this.view.setBook(null);
        addListeners();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocaleManager) {
            this.view.changeLanguage();
        }
    }
    
    /** Realización de la acción de selección de una fila del listado. */
   
     private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.view.setVisible(false);
        this.view.dispose();
    }
    
    /**
     * Comprueba el valor de los campos obligatorios.
     * 
     * @param book
     * @throws MandatoryFieldException 
     */    
    private void checkMandatoryFields(BookDTO book) throws MandatoryFieldException {
        
        Boolean showWarnings = Boolean.FALSE;
        this.view.hideWarnings();
        
        if (book.getNombre() == null || "".equals(book.getNombre().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningTitle(Boolean.TRUE);
        }
        if (book.getAutor()== null || "".equals(book.getAutor().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningAuthor(Boolean.TRUE);
        }
        if (book.getTema()== null || "".equals(book.getTema().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningTopic(Boolean.TRUE);
        }
        if (showWarnings) {
            throw new MandatoryFieldException();
        }
        
    }
}

