package es.csc.biblioteca.loans.gui;

import com.toedter.calendar.JDateChooserCellEditor;
import es.csc.biblioteca.i18n.LocaleManager;
import es.csc.biblioteca.loans.dao.ILoansDAO;
import es.csc.biblioteca.loans.dao.LoansDAOException;
import es.csc.biblioteca.loans.dao.LoansDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;
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
    
    private final ILoansDAO dao;
    private final LoansManagementView view;
    private final LoansTableModel model;
    
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";
    private static final String ACTION_PRINT = "Print";
    private static final String ACTION_PREVIEW = "Preview";
    
    public LoansManagementController(Connection connection) throws LoansDAOException {
        this.dao = new LoansDAOImpl(connection);
        this.model = new LoansTableModel(this.dao, this.dao.findAll());
        this.view = new LoansManagementView();
        initView();
        addListeners();
        this.view.setVisible(true);
        LocaleManager.getInstance().addObserver(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_ADD:
                addLoan();
                break;
            case ACTION_EDIT:
                editLoan();
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
    }
    
    private void addListeners() {
        this.view.setAddListener(this, ACTION_ADD);
        this.view.setEditListener(this, ACTION_EDIT);
        this.view.setDeleteListener(this, ACTION_DELETE);
        this.view.setPrintListener(this, ACTION_PRINT);
        this.view.setPreviewListener(this, ACTION_PREVIEW);
    }
    
    public LoansManagementView getView() {
        return this.view;
    }
    
    private void addLoan() {
    }
    
    private void editLoan() {
    }
    
    private void deleteSelectedLoans() {
        JOptionPane p = new JOptionPane();
        this.model.deleteSelectedLoans();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocaleManager) {
            this.view.changeLanguage();
        }
    }
    
}
