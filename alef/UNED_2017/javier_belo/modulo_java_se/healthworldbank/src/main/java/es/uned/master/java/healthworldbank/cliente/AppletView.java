package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.ActionType;
import es.uned.master.java.healthworldbank.datos.Estadistica;
import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbank.modelosTabla.*;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.swing.JMapPane;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by javierbelogarcia on 27/02/2017.
 */
public class AppletView implements AppletViewInterface, TablePaginationHandlerListener{

    /**
     * Cpntenedor principal
     */
    private Container container;
    private JTabbedPane tabbedPane;

    private JPanel panelGraph;

    /**
     * Tabla paginada de paises
     */
    private TablePaginationHandler countriesTablePaginationHandler;

    /**
     * Tabla paginada de indicadores
     */
    private TablePaginationHandler indicatorsTablePaginationHandler;

    /**
     * Tabla paginada de estadísticas
     */
    private TablePaginationHandler resultsTablePaginationHandler;

    /**
     * Modelo de años
     */
    private CustomAbstractTableModel yearsModel;

    /**
     * Tabla de años
     */
    private JTable yearsTable;

    /**
     * Gráfica
     */
    private CrearGrafica2 graph;

    /**
     * Panel de mapa
     */
    private JPanel panelMap;

    /**
     * Tabla paginada indicadores de mapa
     */
    private TablePaginationHandler indicatorsTablePaginationHandlerMap;

    /**
     * Mapa
     */
    private JMapPane mapPane;
    private MapContent mapContent;

    /**
     * Referencia al controlador
     */
    private AppletControllerInterface appletControllerInterface;

    /**
     * Constructor
     */
    public AppletView() {
        createView();
    }

    /**
     * Crea la vista
     */
    public void createView(){

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    container = new Container();
                    setTabbedPane();
                    setGraphTab();
                    setTabMap();
                    container.add(tabbedPane);
                }
            });
        }catch (InterruptedException e){
            // do nothing

        }catch (InvocationTargetException e){
            // do nothing
        }
    }


    /**
     * Hace el setting de los tabbedpane
     */
    private void setTabbedPane(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setSize(CONTAINER_W,CONTAINER_H);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO add action here
            }
        });
    }


    /**
     * Hace la pestaña de la gráfica
     */
    private void setGraphTab() {

        panelGraph = new JPanel();
        panelGraph.setLayout(new BorderLayout());
        panelGraph.setSize(890,500);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setSize(440,490);
        GridBagLayout leftGridBagLayout = new GridBagLayout();
        leftPanel.setLayout(leftGridBagLayout);
        GridBagConstraints leftGridConstraint = new GridBagConstraints();
        leftGridConstraint.insets = new Insets(3,3,3,3);

        // Table countries
        CustomAbstractTableModel countryModel = new ModeloPaises();
        countriesTablePaginationHandler = new TablePaginationHandler("Paises",
                TABLE_ID.COUNTRIES, countryModel,this);
        leftGridConstraint.gridx = 0;
        leftGridConstraint.gridy = 0;
        leftGridConstraint.gridheight = 1;
        leftGridConstraint.gridwidth = 1;
        leftGridConstraint.anchor = GridBagConstraints.WEST;
        leftPanel.add(countriesTablePaginationHandler.getPanel(),leftGridConstraint);

        // Table indicators
        CustomAbstractTableModel indicatorsModel = new ModeloIndicadores();
        indicatorsTablePaginationHandler = new TablePaginationHandler("Indicadores",
                TABLE_ID.INDICATOR_GRPAH, indicatorsModel,this);
        leftGridConstraint.gridx = 0;
        leftGridConstraint.gridy = 1;
        leftGridConstraint.gridheight = 1;
        leftGridConstraint.gridwidth = 1;
        leftGridConstraint.anchor = GridBagConstraints.WEST;
        leftPanel.add(indicatorsTablePaginationHandler.getPanel(),leftGridConstraint);

        // Table results
        CustomAbstractTableModel resultsModel = new ModeloEstadistica();
        resultsTablePaginationHandler = new TablePaginationHandler("Results",
                TABLE_ID.STATISTICS, resultsModel,this);
        leftGridConstraint.gridx = 0;
        leftGridConstraint.gridy = 2;
        leftGridConstraint.gridheight = 1;
        leftGridConstraint.gridwidth = 1;
        leftGridConstraint.anchor = GridBagConstraints.WEST;
        leftPanel.add(resultsTablePaginationHandler.getPanel(),leftGridConstraint);

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setSize(440,490);
        GridBagLayout rightGridBagLayout = new GridBagLayout();
        rightPanel.setLayout(rightGridBagLayout);
        GridBagConstraints rightGridConstraint = new GridBagConstraints();
        rightGridConstraint.insets = new Insets(5,5,5,5);

        // Graph
        graph = new CrearGrafica2();
        rightGridConstraint.gridx = 0;
        rightGridConstraint.gridy = 0;
        rightGridConstraint.gridheight = 1;
        rightGridConstraint.gridwidth = 1;
        rightGridConstraint.anchor = GridBagConstraints.CENTER;
        rightPanel.add(graph,rightGridConstraint);

        // Add to tabbed pane
        panelGraph.add(leftPanel,BorderLayout.WEST);
        panelGraph.add(rightPanel,BorderLayout.EAST);
        tabbedPane.add("Graph", panelGraph);

    }

    /**
     * Hace la pestaña del mapa
     */
    private void setTabMap(){
        //Contiene lo relativo a la tabla de datos
        panelMap = new JPanel();
        panelMap.setLayout(new BorderLayout());
        panelMap.setSize(890,650);

        // top panel
        JPanel topPanel = new JPanel();
        topPanel.setSize(880,150);
        //topPanel.setBackground(Color.BLUE);
        GridBagLayout topGridBagLayout = new GridBagLayout();
        topPanel.setLayout(topGridBagLayout);
        GridBagConstraints topGridConstraint = new GridBagConstraints();
        topGridConstraint.insets = new Insets(3,3,3,3);

       // Table Indicators
        CustomAbstractTableModel indicatorsModel = new ModeloIndicadores();
        indicatorsTablePaginationHandlerMap = new TablePaginationHandler("Indicadores",
                TABLE_ID.INDICATORS_MAP, indicatorsModel,this);
        topGridConstraint.gridx = 0;
        topGridConstraint.gridy = 0;
        topGridConstraint.gridheight = 1;
        topGridConstraint.gridwidth = 1;
        topGridConstraint.weightx = 1.0;
        topGridConstraint.anchor = GridBagConstraints.NORTHEAST;
        topPanel.add(indicatorsTablePaginationHandlerMap.getPanel(),topGridConstraint);

        // TODO modificar la dimensión de la tabla

        // Panel Years
        JPanel yearsPanel = new JPanel();
        yearsPanel.setSize(100,140);
        GridBagLayout yearsGridBagLayout = new GridBagLayout();
        yearsPanel.setLayout(yearsGridBagLayout);
        GridBagConstraints yearsGridConstraint = new GridBagConstraints();
        yearsGridConstraint.insets = new Insets(5,5,5,5);

        JLabel yearsMap = new JLabel("Años ");
        yearsGridConstraint.gridx = 0;
        yearsGridConstraint.gridy = 0;
        yearsGridConstraint.gridwidth = 1;
        yearsGridConstraint.gridheight = 1;
        yearsGridConstraint.anchor = GridBagConstraints.NORTHWEST;
        yearsPanel.add(yearsMap, yearsGridConstraint);

        yearsModel = new YearModel();
        yearsTable = new JTable(yearsModel);
        yearsTable.setRowSelectionAllowed(true);
        ListSelectionModel listSelectionModel = yearsTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new TableYearsListener());
        yearsTable.setPreferredScrollableViewportSize(new Dimension(90, 90));
        JScrollPane scrollPaneIndicadoresA = new JScrollPane(yearsTable);
        yearsGridConstraint.gridx = 0;
        yearsGridConstraint.gridy = 1;
        yearsGridConstraint.gridwidth = 1;
        yearsGridConstraint.gridheight = 1;
        yearsGridConstraint.anchor = GridBagConstraints.NORTH;
        yearsGridConstraint.fill = GridBagConstraints.NORTH;
        yearsGridConstraint.insets = new 	Insets(5, 5, 5, 5);
        yearsPanel.add(scrollPaneIndicadoresA, yearsGridConstraint);

        topGridConstraint.gridx = 1;
        topGridConstraint.gridy = 0;
        topGridConstraint.gridheight = 1;
        topGridConstraint.gridwidth = 1;
        topGridConstraint.anchor = GridBagConstraints.NORTHWEST;
        topPanel.add(yearsPanel,topGridConstraint);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setSize(880,500);
        //bottomPanel.setBackground(Color.RED);
        GridBagLayout bottomGridBagLayout = new GridBagLayout();
        bottomPanel.setLayout(bottomGridBagLayout);
        GridBagConstraints bottomGridConstraint = new GridBagConstraints();
        bottomGridConstraint.insets = new Insets(3,3,3,3);

        mapPane = new JMapPane();
        //mapPane.setBackground(Color.CYAN);
        mapPane.setPreferredSize(new Dimension(600, 450));
        GTRenderer renderer= new StreamingRenderer();
        mapPane.setRenderer(renderer);

        mapContent = new MapContent();
        mapContent.setTitle("WHB Data");
        mapPane.setMapContent(mapContent);
        //mapPane.addMapPaneListener();

        bottomGridConstraint.gridx = 0;
        bottomGridConstraint.gridy = 0;
        bottomGridConstraint.anchor = GridBagConstraints.NORTH;
        bottomGridConstraint.insets = new Insets(0, 0, 5, 0);
        bottomPanel.add(mapPane,bottomGridConstraint);

        //Set tab
        panelMap.add(topPanel,BorderLayout.NORTH);
        panelMap.add(bottomPanel,BorderLayout.SOUTH);

        tabbedPane.add("Map", panelMap);

    }


    /**
     * Getter del contendor
     * @return
     */
    public Container getContainer(){
        return container;
    }

    @Override
    public void addMapLayer(ArrayList<FeatureLayer> featureLayers) {
        mapPane.setIgnoreRepaint(true);
        for(FeatureLayer fl:featureLayers){
            mapContent.layers().add(fl);
        }
        ReferencedEnvelope re = mapPane.getDisplayArea();
        mapPane.setDisplayArea(new ReferencedEnvelope(-170,170,-60,60, re.getCoordinateReferenceSystem()));
        mapPane.setIgnoreRepaint(false);
        mapPane.repaint();
    }

    @Override
    public void setController(AppletControllerInterface appletControllerInterface){
        this.appletControllerInterface = appletControllerInterface;
    }

    @Override
    public void tableEventRowSelected(TABLE_ID tableId) {
        switch (tableId){
            case COUNTRIES:
            case INDICATOR_GRPAH:
                String country = countriesTablePaginationHandler.getCellValue();
                String indicator = indicatorsTablePaginationHandler.getCellValue();
                System.out.println("los datos son " + country + indicator);
                if(country != null && indicator != null){
                    appletControllerInterface.requestStatistics(ActionType.NEUTRAL,0, country, indicator);
                }
                break;
            case INDICATORS_MAP:
            case YEARS:
                String indicatorMap = indicatorsTablePaginationHandlerMap.getCellValue();
                String yearCode = getYearTableValue();
                System.out.println("los datos son " + indicatorMap + yearCode);
                if(yearCode != null && indicatorMap != null)
                    appletControllerInterface.requestMapData(indicatorMap,yearCode);
                break;
        }
    }

    @Override
    public void tableButtonsActionListener(TABLE_ID tableId, ActionType actionType, int offset) {
        switch (tableId){
            case COUNTRIES:
            case INDICATORS_MAP:
            case INDICATOR_GRPAH:
                appletControllerInterface.requestDataSingleTable(tableId, actionType, offset);
                break;
            case STATISTICS:
                String country = countriesTablePaginationHandler.getCellValue();
                String indicator = indicatorsTablePaginationHandler.getCellValue();
                if(country != null && indicator != null){
                    appletControllerInterface.requestStatistics(actionType,offset, country, indicator);
                }
        }
    }

    @Override
    public void updateTables (TABLE_ID tableId, java.util.List<Registro> mList, String filter, int totalRecords, String page, int offset) {

        switch (tableId){
            case COUNTRIES:
                countriesTablePaginationHandler.updateTable(mList, filter, totalRecords, page, offset);
                break;
            case INDICATOR_GRPAH:
                indicatorsTablePaginationHandler.updateTable(mList,filter,totalRecords, page, offset);
                break;
            case INDICATORS_MAP:
                indicatorsTablePaginationHandlerMap.updateTable(mList,filter,totalRecords, page, offset);
                break;
            case STATISTICS:
                resultsTablePaginationHandler.updateTable(mList,filter,totalRecords, page, offset);
                XYSeries datos = new XYSeries("Estadisticas");
                for(Registro r:mList){
                    Estadistica s = (Estadistica)r;
                    datos.add(s.getAno(),s.getDato());
                }
                graph.modificarValores(datos);
            case YEARS:
                yearsModel.addAllRows((ArrayList<Registro>) mList);
        }
    }

    @Override
    public JMapPane getMapPane() {
        return mapPane;
    }


    /**
     * Devuelve el año señeccionado
     * @return null si no hay ningún año seleccionado
     */
    private String getYearTableValue(){
        int row = yearsTable.getSelectedRow();
        if(row == -1) return null;
        String value = yearsTable.getValueAt(row,0).toString();
        return value;
    }

    @Override
    public void showErrorMessage(String error, String mensaje){
        JOptionPane.showMessageDialog(null,  mensaje+"\n"+error, "Alerta",JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Clase interna para escuchar las acciones sobre la tabla de años
     */
    private class TableYearsListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            tableEventRowSelected(TABLE_ID.YEARS);
        }
    }

}
