package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.customers.dao.ICustomersDAO;
import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.customers.dao.CustomersDAOImpl;
import es.csc.biblioteca.customers.dao.CustomersDAOException;
import es.csc.biblioteca.customers.dao.CustomersDAOImplOracle;
import static es.csc.biblioteca.customers.gui.CustomerDetailsController.RET_OK;
import static es.csc.biblioteca.customers.gui.CustomerDetailsController.lm;
import es.csc.biblioteca.exceptions.MandatoryFieldException;
import es.csc.biblioteca.gui.main.TableRow;
import es.csc.biblioteca.i18n.LocaleManager;
import es.csc.biblioteca.util.Config;
import es.csc.biblioteca.jdbc.ConnectionsManager;
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
public class CustomersManagementController implements ActionListener, Observer {

    protected static final Logger logger = Logger.getLogger(CustomersManagementController.class);
	protected static final LocaleManager lm = LocaleManager.getInstance();
    protected static final Config config = Config.getInstance();    
    
    private final ICustomersDAO dao;
    private final CustomersManagementView view;
    private final CustomersTableModel model;
    private CustomerDTO modelDTO;
    
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";
    private static final String ACTION_PRINT = "Print";
    private static final String ACTION_PREVIEW = "Preview";
    //Añadimos los nuevos
    private static final String ACTION_OK = "Ok";
    private static final String ACTION_CANCEL = "Cancel";
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    
    private int selectedRow;
	
    
    public CustomersManagementController(Connection connection) throws CustomersDAOException {
        //this.model = new CustomersModel(connection);
        
        if(ConnectionsManager.dbms.equals("oracle")){
                this.dao = new CustomersDAOImplOracle(connection);
        }
        else{
            this.dao = new CustomersDAOImpl(connection);         
        }        

        this.model = new CustomersTableModel(this.dao, this.dao.findAll());
        this.view = new CustomersManagementView();
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
                addCustomer();
                break;
            case ACTION_EDIT:
                try{
                editCustomer();
                }catch (IndexOutOfBoundsException ex) {
                    logger.error("Debe seleccionar un registro");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("SELECTED_ROW_ERROR_TEXT"),
                                                  lm.getText("SELECTED_ROW_ERROR_TITLE"),
                                                  JOptionPane.WARNING_MESSAGE);
                }
                break;
            case ACTION_DELETE:
                deleteSelectedCustomers();
                break;
            case ACTION_PRINT:
                this.model.printCustomers();
                break;
            case ACTION_PREVIEW:
                this.model.previewCustomers();
                break;
            //Añadimos los dos controles
            case ACTION_OK:
                try {
                    CustomerDTO dto = this.view.getCustomer();
                    checkMandatoryFields(dto);
                    this.modelDTO = dto;
                    this.model.setCustomerAt(this.getCustomer(),this.selectedRow); 
                    this.view.borrar();
                } catch (MandatoryFieldException ex) {
                    logger.error("Faltan datos obligatorios.");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("MANDATORY_FIELDS_ERROR_TEXT"),
                                                  lm.getText("MANDATORY_FIELDS_ERROR_TITLE"),
                                                  JOptionPane.WARNING_MESSAGE);
                }
                break;
            case ACTION_CANCEL:
                this.view.borrar();
                break;
        }
    }
    
    private void addListeners() {
        this.view.setAddListener(this, ACTION_ADD);
        this.view.setEditListener(this, ACTION_EDIT);
        this.view.setDeleteListener(this, ACTION_DELETE);
        // Añadimos listener para menu contextual
        this.view.setPopupAddListener(this, ACTION_ADD);
        this.view.setPopupEditListener(this, ACTION_EDIT);
        this.view.setPopupDeleteListener(this, ACTION_DELETE);
        
        this.view.setPrintListener(this, ACTION_PRINT);
        this.view.setPreviewListener(this, ACTION_PREVIEW);
        //Añadimos los de la nueva zona
        this.view.setOkListener(this, ACTION_OK);
        this.view.setCancelListener(this, ACTION_CANCEL);
    }
    
    public CustomersManagementView getView() {
        return this.view;
    }
    
    private void addCustomer() {
        // Abrimos la ventana para dar de alta un nuevo usuario
        CustomerDetailsController controller = new CustomerDetailsController();
        if (controller.getReturnStatus() == CustomerDetailsController.RET_OK) {
            this.model.addCustomer(controller.getCustomer());
            int index = this.model.getRowCount();
            this.view.setSelectedRow(index - 1);
        }
    }
    
    private void editCustomer() {
        if(this.view.getSelectedRow()!=-1)
        {
            this.selectedRow = this.view.getSelectedRow();
            CustomerDTO dto = this.model.getCustomerAt(selectedRow);
            this.view.setCustomer(dto);
        }
        else
        {
            logger.error("No has seleccionado ningún usuario");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("NO_SELECTION_ERROR_TITLE"),
                                                  lm.getText("NO_SELECTION_ERROR_TEXT"),
                                                  JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void deleteSelectedCustomers() {
        JOptionPane p = new JOptionPane();
        this.model.deleteSelectedCustomers();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocaleManager) {
            this.view.changeLanguage();
        }
    }
    //Añadimos las funciones
    /**
     * Devuelve el valor de estado de la ventana.
     * 
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    /*public int getReturnStatus() {
        return returnStatus;
    }*/
    
    public CustomerDTO getCustomer() {
        return modelDTO;
    }  
    /**
     * Comprueba el valor de los campos obligatorios.
     * 
     * @param customer
     * @throws MandatoryFieldException 
     */
    private void checkMandatoryFields(CustomerDTO customer) throws MandatoryFieldException {
        
        Boolean showWarnings = Boolean.FALSE;
        
        this.view.hideWarnings();
        
        if (customer.getApellidos() == null || "".equals(customer.getApellidos().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningLastName(Boolean.TRUE);
        }
        if (customer.getDireccion() == null || "".equals(customer.getDireccion().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningAddress(Boolean.TRUE);
        }
        if (customer.getDni() == null || "".equals(customer.getDni().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningNIC(Boolean.TRUE);
        }
        if (customer.getNombre() == null || "".equals(customer.getNombre().trim())) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningFirstName(Boolean.TRUE);
        }
        if (customer.getFechaAlta() == null) {
            showWarnings = Boolean.TRUE;
            this.view.showWarningDate(Boolean.TRUE);
        }
        if (showWarnings) {
            throw new MandatoryFieldException();
        }
        
    }
}