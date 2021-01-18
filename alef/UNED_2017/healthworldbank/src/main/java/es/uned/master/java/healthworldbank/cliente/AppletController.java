package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.*;
import es.uned.master.java.healthworldbank.datos.EstadisticaMap;
import es.uned.master.java.healthworldbank.datos.Registro;
import org.apache.commons.lang.math.NumberUtils;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapPane;
import org.opengis.feature.simple.SimpleFeature;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static es.uned.master.java.healthworldbank.cliente.AppletViewInterface.TABLE_ID.COUNTRIES;
import static es.uned.master.java.healthworldbank.cliente.AppletViewInterface.TABLE_ID.INDICATOR_GRPAH;

/**
 * Controlador del Applet
 * @author jbelo
 */
public class AppletController implements AppletControllerInterface {

    /**
     * Referencia a la vista del MVC
     */
    private AppletViewInterface appletView;
    /**
     * Referencia al modelo
     */
    private AppletModelInterface appletModel;


    /**
     * Constructor
     */
    public AppletController() {
    }

    @Override
    public void loadInitialData() {
        try{
            PreguntaSingleTable preguntaCountry = new PreguntaSingleTable(TipoPeticion.PAISES, ActionType.NEUTRAL,0);
            Respuesta respuestaCountry = appletModel.processRequest(preguntaCountry);
            List<Registro> mList = respuestaCountry.getDatos();

            PreguntaSingleTable preguntaIndicators = new PreguntaSingleTable(TipoPeticion.INDICADORES, ActionType.NEUTRAL, 0);
            Respuesta respuestaIndicators = appletModel.processRequest(preguntaIndicators);

            PreguntaYears preguntaYears = new PreguntaYears();
            Respuesta respuestaYears = appletModel.processRequest(preguntaYears);

            appletView.updateTables(COUNTRIES, mList,null,respuestaCountry.getTotalRegistros(),
                    respuestaCountry.getPage(), respuestaCountry.getOffset());
            appletView.updateTables(INDICATOR_GRPAH, respuestaIndicators.getDatos(),null,respuestaIndicators.getTotalRegistros(),
                    respuestaIndicators.getPage(),respuestaIndicators.getOffset());
            appletView.updateTables(AppletViewInterface.TABLE_ID.INDICATORS_MAP, respuestaIndicators.getDatos(),null,
                    respuestaIndicators.getTotalRegistros(), respuestaIndicators.getPage(), respuestaIndicators.getOffset());
            appletView.updateTables(AppletViewInterface.TABLE_ID.YEARS,respuestaYears.getDatos(),null,
                    respuestaYears.getTotalRegistros(),null, respuestaYears.getOffset());

        } catch (ServerConnectionException e){
            appletView.showErrorMessage("Error de datos","Se ha producido un error en la carga de datos");
        }
    }

    @Override
    public void loadBaseMap() {

        ClassLoader classLoader = getClass().getClassLoader();
        ArrayList<FeatureLayer> featureLayers = new ArrayList<>();

        try {
            File file = new File(classLoader.getResource("maps/countries.shp").getFile());
            FileDataStore dataStore = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource simpleFeatureSource = dataStore.getFeatureSource();

            SimpleFeatureIterator sfi = simpleFeatureSource.getFeatures().features();

            Style style = SLD.createSimpleStyle(simpleFeatureSource.getSchema());

            while(sfi.hasNext()) {
                SimpleFeature sf = sfi.next();
                Object object = sf.getAttribute("ISO3");
                DefaultFeatureCollection defaultFeatureCollection = new DefaultFeatureCollection();
                defaultFeatureCollection.add(sf);

                FeatureLayer fl = new FeatureLayer(defaultFeatureCollection, style);
                fl.setTitle(object.toString());

                fl.setVisible(true);
                fl.setTitle(object.toString());
                featureLayers.add(fl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        appletView.addMapLayer(featureLayers);

    }

    private void setMap(ArrayList<EstadisticaMap> estadisticasMap){

        JMapPane mapPane = appletView.getMapPane();
        MapContent mapContent = mapPane.getMapContent();

        List<Layer> layersList =  mapContent.layers();
        StyleLab styleLab = new StyleLab();
        Style styleWhite = styleLab.createPolygonStyle(Color.WHITE);
        mapPane.setIgnoreRepaint(true);

        if(estadisticasMap.isEmpty()){
            for(Layer layer:layersList){
                FeatureLayer featureLayer = (FeatureLayer)layer;
                featureLayer.setStyle(styleWhite);
            }
            mapPane.setIgnoreRepaint(false);
            ((FeatureLayer)layersList.get(0)).setStyle(styleWhite);
        } else {
            for(Layer layer:layersList){
                Style styleApplied = styleLab.createPolygonStyle(Color.WHITE);
                String countryLayer = layer.getTitle();
                for(EstadisticaMap estadisticaMap:estadisticasMap){
                    String estadisticaCountry = estadisticaMap.getCodigoPais();
                    if(countryLayer.equalsIgnoreCase(estadisticaCountry)){
                        double a = NumberUtils.isNumber(String.valueOf(estadisticaMap.getDato()))?estadisticaMap.getDato():0.0d;
                        int n = new Double (255 * (100-a) / 100).intValue();
                        if(n < 0) n = 0;
                        if(n > 255) n = 255;
                        Color color = new Color(n, n, 255);
                        styleApplied = styleLab.createPolygonStyle(color);
                        break;
                    }
                }
                ((FeatureLayer)layer).setStyle(styleApplied);
            }
            mapPane.setIgnoreRepaint(false);
            // TODO empleado para forzar el repintado
            FeatureLayer fl = (FeatureLayer) layersList.get(0);
            Style ms = fl.getStyle();
            fl.setStyle(ms);
            mapPane.repaint();
        }

    }

    @Override
    public void setView(AppletViewInterface appletViewInterface) {
        appletView = appletViewInterface;
    }

    @Override
    public void setModel(AppletModelInterface appletModelInterface) {
        appletModel = appletModelInterface;
    }

    @Override
    public void requestStatistics(ActionType actionType, int offset, String country, String indicator) {
        try {
          PreguntaStatistics preguntaStatistics = new PreguntaStatistics(actionType, offset,country,indicator);
          Respuesta respuesta = appletModel.processRequest(preguntaStatistics);
          appletView.updateTables(AppletViewInterface.TABLE_ID.STATISTICS,respuesta.getDatos(),null,
                  respuesta.getTotalRegistros(),respuesta.getPage(), respuesta.getOffset());
        } catch (ServerConnectionException e){
            appletView.showErrorMessage("Error de datos","Se ha producido un error en la carga de datos");
        }
    }

    @Override
    public void requestMapData(String indicatorCode, String yearCode) {
        try{
            PreguntaMap preguntaMap = new PreguntaMap(indicatorCode,yearCode);
            Respuesta respuesta = appletModel.processRequest(preguntaMap);
            ArrayList<Registro> mList = (ArrayList) respuesta.getDatos();
            ArrayList<EstadisticaMap> aem = (ArrayList<EstadisticaMap>)(ArrayList<?>)mList;
            setMap(aem);

        } catch (ServerConnectionException e ){
            appletView.showErrorMessage("Error de datos","Se ha producido un error en la carga de datos");

        }
    }

    @Override
    public void requestDataSingleTable(AppletViewInterface.TABLE_ID tableID, ActionType actionButton, int offset) {
        try{
            performRequestTable(tableID,actionButton,offset);
        } catch (ServerConnectionException e){
            appletView.showErrorMessage("Error de datos","Se ha producido un error en la carga de datos");
        }

    }


    private void performRequestTable(AppletViewInterface.TABLE_ID tableId, ActionType actionType, int offset) throws ServerConnectionException {
        switch (tableId){
            case COUNTRIES:
                PreguntaSingleTable preguntaCountry = new PreguntaSingleTable(TipoPeticion.PAISES, actionType,offset);
                Respuesta respuestaCountry = appletModel.processRequest(preguntaCountry);
                List<Registro> mList = respuestaCountry.getDatos();
                appletView.updateTables(COUNTRIES, mList,null,respuestaCountry.getTotalRegistros(), respuestaCountry.getPage(), respuestaCountry.getOffset());
                break;
            case INDICATOR_GRPAH:
                PreguntaSingleTable preguntaIndicators = new PreguntaSingleTable(TipoPeticion.INDICADORES, actionType, offset);
                Respuesta respuestaIndicators = appletModel.processRequest(preguntaIndicators);
                appletView.updateTables(INDICATOR_GRPAH, respuestaIndicators.getDatos(),null,respuestaIndicators.getTotalRegistros(),
                        respuestaIndicators.getPage(), respuestaIndicators.getOffset());
                break;
            case INDICATORS_MAP:
                PreguntaSingleTable preguntaIndicatorsMap = new PreguntaSingleTable(TipoPeticion.INDICADORES, actionType, offset);
                Respuesta respuestaIndicatorsMap = appletModel.processRequest(preguntaIndicatorsMap);
                appletView.updateTables(AppletViewInterface.TABLE_ID.INDICATORS_MAP, respuestaIndicatorsMap.getDatos(),
                        null,respuestaIndicatorsMap.getTotalRegistros(), respuestaIndicatorsMap.getPage(),respuestaIndicatorsMap.getOffset());
                break;
        }

    }

}
