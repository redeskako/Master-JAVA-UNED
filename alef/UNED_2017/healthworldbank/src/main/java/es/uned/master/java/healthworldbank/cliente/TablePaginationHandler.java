package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.ActionType;
import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbank.modelosTabla.CustomAbstractTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase que encapsula las tablas paginadas facilitando su gestión
 * @author jbelo
 */
public class TablePaginationHandler {

    private int tableWidth = 400;
    private int tableHeight = 90;

    private final AppletViewInterface.TABLE_ID tableId;
    private final JPanel panel;
    private final String labelTableName;
    private final JLabel fieldAmountRecords;
    private final JLabel fieldPageNumber;
    private final CustomAbstractTableModel tableModel;
    private final JTable table;
    private final JButton buttonBackward;
    private final JButton buttonForward;

    private int offset = 0;
    private int totalRecords = 0;
    private int columnValue = 0;


    /**
     * Referencia al listner de las acciones sobre la tabala y botones
     */
    private final TablePaginationHandlerListener tablePaginationHandlerListener;

    /**
     * Cosntructor
     * @param labelTableName Nombre de la tabla
     * @param tableId ID de la tabla
     * @param tableModel modelo
     * @param tablePaginationHandlerListener listner de la la tabla y botones
     */
    public TablePaginationHandler(String labelTableName,
                                  AppletViewInterface.TABLE_ID tableId,
                                  CustomAbstractTableModel tableModel,
                                  TablePaginationHandlerListener tablePaginationHandlerListener) {
        this.tableId = tableId;
        this.labelTableName = labelTableName;
        this.tableModel = tableModel;
        this.tablePaginationHandlerListener = tablePaginationHandlerListener;
        fieldPageNumber = new JLabel();
        fieldAmountRecords = new JLabel();
        panel = new JPanel();
        panel.setSize(420,150);
        table = new JTable();
        table.setModel(tableModel);
        buttonBackward = new JButton();
        buttonForward = new JButton();

        buildPanel();
        addActionListener();
        addTableListenr();
    }


    /**
     * Construye el panel
     */
    private void buildPanel(){

        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        JLabel labelName = new JLabel(labelTableName);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        panel.add(labelName, gridBagConstraints);

        JLabel labelTotal = new JLabel("Total registros: ");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        panel.add(labelTotal, gridBagConstraints);

        fieldAmountRecords.setText("test");
        fieldAmountRecords.setOpaque(true);
        fieldAmountRecords.setBackground(Color.lightGray);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        panel.add(fieldAmountRecords, gridBagConstraints);


        table.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));
        JScrollPane scrollPanePaises = new JScrollPane(table);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        panel.add(scrollPanePaises, gridBagConstraints);

        buttonBackward.setText("Retroceder");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        panel.add(buttonBackward, gridBagConstraints);

        fieldPageNumber.setText("test");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        panel.add(fieldPageNumber, gridBagConstraints);

        buttonForward.setText("Avanzar");
        //buttonForward.setActionCommand(FORWRD_ACTION_COMMAND);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        panel.add(buttonForward, gridBagConstraints);
    }

    /**
     * Establece lso listener de los botones
     */
    private void addActionListener(){
        buttonForward.addActionListener(new ForwardButtonListener());
        buttonBackward.addActionListener(new BackwardButtonListener());
    }

    /**
     * Establece los listener de la tabla
     */
    private void addTableListenr(){
        table.setRowSelectionAllowed(true);
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new TableListener());
    }

    /**
     * Estable la columna de reference para la extracción de la información
     * @param columnValue
     */
    public void setColumnValue(int columnValue) {
        this.columnValue = columnValue;
    }

    /**
     * Recupera la información de una celda según la fila seleccionada y la columna establecida
     * @return null si no hay nada seleccionado
     */
    public String getCellValue(){
        int row = table.getSelectedRow();
        if(row == -1) return null;
        String value = table.getValueAt(row,columnValue).toString();
        return value;
    }

    /**
     * Recupera el offset
     * @return
     */
    public int getOffset(){
        return offset;
    }

    /**
     * Devuelve el JPanel que contiene todos los elementos
     * @return
     */
    public JPanel getPanel() {
        return panel;
    }


    /**
     * Clase para el listener del botón atrás
     */
    private class BackwardButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tablePaginationHandlerListener.tableButtonsActionListener(tableId, ActionType.BACKWARD,offset);

        }
    }

    /**
     * Clase para el listener del botón adelante
     */
    private class ForwardButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            tablePaginationHandlerListener.tableButtonsActionListener(tableId, ActionType.FORWARD,offset);

        }

    }

    /**
     * Actualiza la tabla
     * @param mList lista de datos para actualizar la lista
     * @param filter filtro
     * @param totalRecords cantidad total de elementos en la base de datos
     * @param page página que se muestra
     * @param offset offset
     */
    public void updateTable(java.util.List<Registro> mList, String filter, int totalRecords, String page, int offset){
        tableModel.removeAllRows();
        tableModel.addAllRows((ArrayList)mList);
        tableModel.setFiltro(filter);
        this.totalRecords = totalRecords;
        this.offset = offset;
        fieldAmountRecords.setText(String.valueOf(totalRecords));
        fieldPageNumber.setText(page);
    }


    /**
     * Listener para acciones sobre la tabla
     */
    private class TableListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            tablePaginationHandlerListener.tableEventRowSelected(tableId);

        }
    }

}
