package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.customers.dao.ICustomersDAO;
import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.customers.dao.CustomersDAOImpl;
import es.csc.biblioteca.customers.dao.CustomersDAOException;
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
public class CustomersManagementController implements ActionListener, Observer {

    protected static final Logger logger = Logger.getLogger(CustomersManagementController.class);
    
    private final ICustomersDAO dao;
    private final CustomersManagementView view;
    private final CustomersTableModel model;
    
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";
    private static final String ACTION_PRINT = "Print";
    private static final String ACTION_PREVIEW = "Preview";
    
    public CustomersManagementController(Connection connection) throws CustomersDAOException {
        //this.model = new CustomersModel(connection);
        this.dao = new CustomersDAOImpl(connection);
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
                editCustomer();
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
        }
    }
    
    private void addListeners() {
        this.view.setAddListener(this, ACTION_ADD);
        this.view.setEditListener(this, ACTION_EDIT);
        this.view.setDeleteListener(this, ACTION_DELETE);
        this.view.setPrintListener(this, ACTION_PRINT);
        this.view.setPreviewListener(this, ACTION_PREVIEW);
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
        // Abrimos la ventana para dar de alta un nuevo usuario
        int selectedRow = this.view.getSelectedRow();
        CustomerDTO dto = this.model.getCustomerAt(selectedRow);
        CustomerDetailsController controller = new CustomerDetailsController(dto);
        if (controller.getReturnStatus() == CustomerDetailsController.RET_OK) {
            this.model.setCustomerAt(controller.getCustomer(), selectedRow);
            this.view.setSelectedRow(selectedRow);
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
    
}
