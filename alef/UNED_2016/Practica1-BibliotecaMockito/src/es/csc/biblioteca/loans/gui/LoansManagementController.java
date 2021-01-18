package es.csc.biblioteca.loans.gui;

import com.toedter.calendar.JDateChooserCellEditor;
import es.csc.biblioteca.i18n.LocaleManager;
import es.csc.biblioteca.jdbc.ConnectionsManager;
import es.csc.biblioteca.loans.dao.ILoansDAO;
import es.csc.biblioteca.loans.dao.LoansDAOException;
import es.csc.biblioteca.loans.dao.LoansDAOImpl;
import es.csc.biblioteca.loans.dao.LoansDAOImplOracle;
import es.csc.biblioteca.util.Config;
//import com.toedter.calendar.JDateChooser;
import es.csc.biblioteca.customers.dao.*;
import es.csc.biblioteca.books.dao.*;
import es.csc.biblioteca.loans.dao.LoanDTO;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;


/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class LoansManagementController implements ActionListener, Observer {

    protected static final Logger logger = Logger.getLogger(LoansManagementController.class);
    
    protected static final LocaleManager lm = LocaleManager.getInstance();
    
    private final ILoansDAO dao;
    private final LoansManagementView view;
    private final LoansTableModel model;
    private final ICustomersDAO custdao;
    private final IBooksDAO bookdao;
    
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";
    private static final String ACTION_PRINT = "Print";
    private static final String ACTION_PREVIEW = "Preview";
    private static final String ACTION_ACEPT = "Acept";
    private static final String ACTION_CANCEL = "Cancel";
    private enum AccionLoans { ADD,DELETE,EDIT,PRINT,PREVIEW,NO };
    private AccionLoans UltimaAccion = AccionLoans.NO;    

    public ArrayList<String> LecturaCustomers() throws CustomersDAOException {
     ArrayList<String> nombreCustomers = new ArrayList();
     List<CustomerDTO> listadoCustomers;
     listadoCustomers=this.custdao.findAll();
     for (CustomerDTO listCust : listadoCustomers) {
        nombreCustomers.add(listCust.getApellidos()+" "+listCust.getNombre()+" "+listCust.getIdSocio());
     } 
     
     Collections.sort(nombreCustomers);
     return nombreCustomers;
    }
        
    public ArrayList<String> LecturaBooks() throws BooksDAOException {
        ArrayList<String> tituloBooks = new ArrayList();
        List<BookDTO> listadoBooks; 
        listadoBooks=this.bookdao.findAll();
        for (BookDTO listB : listadoBooks) {
            tituloBooks.add(listB.getNombre()+" "+listB.getIdLibro());
        }
        
        Collections.sort(tituloBooks);
        return tituloBooks;
    }
    
    
    public LoansManagementController(Connection connection) throws LoansDAOException {
        if(ConnectionsManager.dbms.equals("oracle")){
            this.dao = new LoansDAOImplOracle(connection);
        }
        else{
            this.dao = new LoansDAOImpl(connection);         
        }                  
        this.model = new LoansTableModel(this.dao, this.dao.findAll());
        this.view = new LoansManagementView();
        
        this.view.changeLanguage();
        this.view.setTableModel(this.model); //.getTableModel());
        this.view.setRowSorter(new TableRowSorter(this.model)); //.getTableModel()));
        this.view.setVisible(true);
        
        initView();
        addListeners();
        this.view.setVisible(true);
        LocaleManager.getInstance().addObserver(this);
        this.bookdao = new BooksDAOImpl (connection);
        this.custdao = new CustomersDAOImpl (connection);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_ADD:
                UltimaAccion = AccionLoans.ADD;
                this.view.setBotones(1);
                rellenaCombos();
                break;
            case ACTION_EDIT:
                if (this.view.isSelectedRow()){
                    UltimaAccion = AccionLoans.EDIT;
                    this.view.setBotones(1);
                    editLoan();
                }
                try{
                  editLoan();
                }catch   (IndexOutOfBoundsException ex) {
                    logger.error("Debe seleccionar un registro");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("SELECTED_ROW_ERROR_TEXT"),
                                                  lm.getText("SELECTED_ROW_ERROR_TITLE"),
                                                  JOptionPane.WARNING_MESSAGE);
                }
                break;
            case ACTION_DELETE:
                deleteSelectedLoans();
                break;
            case ACTION_PRINT:
                this.model.printLoans();
                break;
            case ACTION_PREVIEW:
                this.model.previewLoans();
                break;
            case ACTION_ACEPT:
                if (this.view.isFechaValida()){
                    aceptLoan();
                    UltimaAccion = AccionLoans.NO;
                    this.view.setBotones(0);
                    this.view.limpiaFicha();
                }
                break;
            case ACTION_CANCEL:
                UltimaAccion = AccionLoans.NO;
                this.view.setBotones(0);
                this.view.limpiaFicha();
                break;
        }
    }
    
    private void initView() {
        this.view.changeLanguage();
        JTable tblLoans = this.view.getTable();
        tblLoans.setModel(this.model);
        tblLoans.setRowSorter(new TableRowSorter(this.model));
        tblLoans.getColumnModel().getColumn(4).setCellEditor(new JDateChooserCellEditor());
        tblLoans.getColumnModel().getColumn(5).setCellEditor(new JDateChooserCellEditor());
        ((JComponent) tblLoans.getDefaultRenderer(Boolean.class)).setOpaque(true);
        this.view.setBotones(0);
    }
    
    private void addListeners() {
        this.view.setAddListener(this, ACTION_ADD);
        this.view.setEditListener(this, ACTION_EDIT);
        this.view.setDeleteListener(this, ACTION_DELETE);
        
// AÃ±adimos listener para menu contextual Popup
        this.view.setPopupAddListener(this, ACTION_ADD);
        this.view.setPopupEditListener(this, ACTION_EDIT);
        this.view.setPopupDeleteListener(this, ACTION_DELETE);
        
        this.view.setPrintListener(this, ACTION_PRINT);
        this.view.setPreviewListener(this, ACTION_PREVIEW);
        this.view.setAceptListener(this, ACTION_ACEPT);
        this.view.setCancelListener(this, ACTION_CANCEL);
    }
    
    public LoansManagementView getView() {
        return this.view;
    }
    
    private void rellenaCombos() {
        try{
           this.view.setComboSocios(LecturaCustomers());
        } catch (CustomersDAOException ex) {
           logger.error(ex.toString());
        }
        try{
           this.view.setComboLibros(LecturaBooks());
        } catch (BooksDAOException ex) {
           logger.error(ex.toString());
        }         
    }

    private LoanDTO getFichaLoan() {
    
        LoanDTO getloan = new LoanDTO();
        CustomerDTO soc = new CustomerDTO();
        BookDTO book = new BookDTO();
        
        try{
            soc=this.custdao.findByPrimaryKey(this.view.getIDSocio());
            getloan.setCustomer(soc);
        } catch (CustomersDAOException ex) {
            logger.error(ex.toString());
        }
        try{
            book=this.bookdao.findByPrimaryKey(this.view.getIDLibro());
            getloan.setBook(book);
        } catch (BooksDAOException ex) {
            logger.error(ex.toString());
        }
        getloan.setFechaInicio(new java.sql.Date(this.view.getFechaInicio()));
        getloan.setFechaFin(new java.sql.Date(this.view.getFechaFin()));
        return getloan;
    }

    
    
    private void editLoan() {
        
        rellenaCombos();
        this.view.selectComboSocios(this.model.getLoanAt(this.view.getSelectedRow()).getCustomer().getIdSocio());
        this.view.selectComboLibros(this.model.getLoanAt(this.view.getSelectedRow()).getBook().getIdLibro());
        this.view.setEditFicha();
    }
    
    private void seleccionaComboCustomer(int id){
        
    }
    
    private void seleccionaComboBook(int id){
        
    }
    private void aceptLoan() {
        switch (UltimaAccion) {
            case ADD: addFichaLoan();  
                break;
            case EDIT: editFichaLoan();
                break;
        }
    }
    
    private void deleteSelectedLoans() {
        //JOptionPane p = new JOptionPane();
        this.model.deleteSelectedLoans();
    }

    private void addFichaLoan(){
        LoanDTO loan = getFichaLoan();
        this.model.addLoan(loan);
    }
    
    private void editFichaLoan(){
        LoanDTO loan = getFichaLoan();
        loan.setIdPrestamo(this.view.getIDPrestamo());
        this.model.setLoanAt(loan, this.view.getSelectedRow());
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocaleManager) {
            this.view.changeLanguage();
        }
    }
    
}
