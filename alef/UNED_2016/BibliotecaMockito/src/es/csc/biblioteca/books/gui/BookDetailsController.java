package es.csc.biblioteca.books.gui;

import es.csc.biblioteca.books.dao.BookDTO;
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
public class BookDetailsController implements ActionListener {
    
    protected static final Logger logger = Logger.getLogger(BookDetailsController.class);
    protected static final LocaleManager lm = LocaleManager.getInstance();
    
    private static final String ACTION_OK = "Ok";
    private static final String ACTION_CANCEL = "Cancel";

    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    
    private int returnStatus = RET_CANCEL;
    
    private BookDTO model;
    private final BookDetailsView view;
    
    /**
     * Abre una ventana para dar de alta un nuevo libro.
     */
    public BookDetailsController() {
        this.view = new BookDetailsView();
        this.view.translate();
        this.view.setTitle(this.view.getTitle() + " [" + lm.getText("LABEL_NEW") + "]");
        this.view.setBook(null);
        addListeners();
        showView();
    }
    
    /**
     * Abre una ventana para editar los datos del libro indicado.
     * 
     * @param model 
     */
    public BookDetailsController(BookDTO model) {
        this.model = model;
        this.view = new BookDetailsView();
        this.view.translate();
        this.view.setBook(this.model);
        addListeners();
        showView();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_OK:
                try {
                    BookDTO dto = this.view.getBook();
                    checkMandatoryFields(dto);
                    this.model = dto;
                    doClose(RET_OK);
                } catch (MandatoryFieldException ex) {
                    logger.error("Faltan datos obligatorios.");
                    JOptionPane.showMessageDialog(this.view,
                                                  lm.getText("SELECTED_ROW_ERROR_TEXT"),
                                                  lm.getText("SELECTED_ROW_ERROR_TITLE"),
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
    
    public BookDTO getBook() {
        return model;
    }
    
    private void addListeners() {
        this.view.setOkListener(this, ACTION_OK);
        this.view.setCancelListener(this, ACTION_CANCEL);
    }
    
    /**
     * Pone visible la ventana de edición de los datos del libro.
     */
    private void showView() {
        this.view.setLocationRelativeTo(this.view);
        this.view.setVisible(true);
    }
    
    /**
     * Cierra la ventana con los datos del libro y establece el valor de estado de retorno.
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
