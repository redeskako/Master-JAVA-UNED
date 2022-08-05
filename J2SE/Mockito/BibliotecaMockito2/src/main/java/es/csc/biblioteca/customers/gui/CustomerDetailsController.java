package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.exceptions.MandatoryFieldException;
import es.csc.biblioteca.i18n.LocaleManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class CustomerDetailsController implements ActionListener {
    
    protected static final Logger logger = Logger.getLogger(CustomerDetailsController.class);
    protected static final LocaleManager lm = LocaleManager.getInstance();
    
    private static final String ACTION_OK = "Ok";
    private static final String ACTION_CANCEL = "Cancel";

    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    
    private int returnStatus = RET_CANCEL;
    
    private CustomerDTO model;
    private final CustomerDetailsView view;
    
    /**
     * Abre una ventana para dar de alta un nuevo socio.
     */
    public CustomerDetailsController() {
        this.view = new CustomerDetailsView();
        this.view.translate();
        this.view.setTitle(this.view.getTitle() + " [" + lm.getText("LABEL_NEW") + "]");
        this.view.setCustomer(null);
        addListeners();
        showView();
    }
    
    /**
     * Abre una ventana para editar los datos del socio indicado.
     * 
     * @param model Datos del socio.
     */
    public CustomerDetailsController(CustomerDTO model) {
        this.model = model;
        this.view = new CustomerDetailsView();
        this.view.translate();
        this.view.setCustomer(this.model);
        addListeners();
        showView();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_OK:
                try {
                    CustomerDTO dto = this.view.getCustomer();
                    checkMandatoryFields(dto);
                    this.model = dto;
                    doClose(RET_OK);
                } catch (MandatoryFieldException ex) {
                    logger.error("Faltan datos obligatorios.");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("MANDATORY_FIELDS_ERROR_TEXT"),
                                                  lm.getText("MANDATORY_FIELDS_ERROR_TITLE"),
                                                  JOptionPane.WARNING_MESSAGE);
                }
                break;
            case ACTION_CANCEL:
                doClose(RET_CANCEL);
                break;
        }
    }

    /**
     * Devuelve el valor de estado de la ventana.
     * 
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    public CustomerDTO getCustomer() {
        return model;
    }
    
    private void addListeners() {
        this.view.setOkListener(this, ACTION_OK);
        this.view.setCancelListener(this, ACTION_CANCEL);
    }
    
    /**
     * Pone visible la ventana de edición de los datos del socio.
     */
    private void showView() {
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }
    
    /**
     * Cierra la ventana con los datos del socio y establece el valor de estado de retorno.
     * 
     * @param retStatus 
     */
    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.view.setVisible(false);
        this.view.dispose();
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
