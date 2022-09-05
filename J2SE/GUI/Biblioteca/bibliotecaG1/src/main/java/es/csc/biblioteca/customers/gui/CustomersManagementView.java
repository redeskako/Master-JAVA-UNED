package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.i18n.LocaleManager;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class CustomersManagementView extends JInternalFrame {

    public CustomersManagementView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel10 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnPreview = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Customers Management");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel10.setBackground(new java.awt.Color(64, 64, 64));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/customers/gui/resources/id_cards32.png"))); // NOI18N
        jLabel10.setText("Customers Management");
        jLabel10.setFocusable(false);
        jLabel10.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        getContentPane().add(jLabel10, gridBagConstraints);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/customers/gui/resources/user1_add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAdd);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/customers/gui/resources/user1_view.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/customers/gui/resources/user1_delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnDelete);
        jToolBar1.add(jSeparator1);

        btnPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/customers/gui/resources/printer_view.png"))); // NOI18N
        btnPreview.setText("Preview");
        btnPreview.setFocusable(false);
        btnPreview.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPreview.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPreview);

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/es/csc/biblioteca/customers/gui/resources/printer.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.setFocusable(false);
        btnPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPrint);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(jToolBar1, gridBagConstraints);

        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tblCustomers);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnPreview;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblCustomers;
    // End of variables declaration//GEN-END:variables

    public void setAddListener(ActionListener l, String command) {
        this.btnAdd.addActionListener(l);
        this.btnAdd.setActionCommand(command);
    }
    
    public void setEditListener(ActionListener l, String command) {
        this.btnEdit.addActionListener(l);
        this.btnEdit.setActionCommand(command);
    }
    
    public void setDeleteListener(ActionListener l, String command) {
        this.btnDelete.addActionListener(l);
        this.btnDelete.setActionCommand(command);
    }
    
    public void setPrintListener(ActionListener l, String command) {
        this.btnPrint.addActionListener(l);
        this.btnPrint.setActionCommand(command);
    }
    
    public void setPreviewListener(ActionListener l, String command) {
        this.btnPreview.addActionListener(l);
        this.btnPreview.setActionCommand(command);
    }
    
    public void setTableModel(AbstractTableModel m) {
        this.tblCustomers.setModel(m);
    }
    
    public void setRowSorter(TableRowSorter sorter) {
        this.tblCustomers.setRowSorter(sorter);
    }
    
    public int getSelectedRow() {
        return this.tblCustomers.convertRowIndexToModel(this.tblCustomers.getSelectedRow());
    }
    
    public void setSelectedRow(int indexRow) {
        int index = this.tblCustomers.convertRowIndexToView(indexRow);
        this.tblCustomers.setRowSelectionInterval(index, index);
    }

    public void changeLanguage() {
        LocaleManager lm = LocaleManager.getInstance();
        this.setTitle(lm.getText("TITLE_CUSTOMERS_MANAGEMENT"));
        this.jLabel10.setText(lm.getText("TITLE_CUSTOMERS_MANAGEMENT"));
        this.btnAdd.setText(lm.getText("BUTTON_ADD"));
        this.btnEdit.setText(lm.getText("BUTTON_EDIT"));
        this.btnDelete.setText(lm.getText("BUTTON_DELETE"));
        this.btnPreview.setText(lm.getText("BUTTON_PREVIEW"));
        this.btnPrint.setText(lm.getText("BUTTON_PRINT"));
    }
    
}
