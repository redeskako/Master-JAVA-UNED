package pro.jbelo.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pro.jbelo.persistence.DataEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Genera los gráficos usando JFrreChart
 * @author jbelo
 * @date 2017 05.
 */
public class Charts {

    /**
     * Instancia de JFreeChart
     */
    private JFreeChart jFreeChart;
    private XYSeries xySeries;
    private XYSeriesCollection xySeriesCollection;

    public Charts() {
        xySeriesCollection = new XYSeriesCollection();
        xySeries = new XYSeries("Statistics");
        xySeriesCollection.addSeries(xySeries);
        jFreeChart = ChartFactory.createXYLineChart(
                "Datos Estadísticos",
                "Years",
                "Percentage",
                xySeriesCollection,
                        PlotOrientation.VERTICAL,
                false,
                false,
                true
        );

    }

    /**
     * Cambia el set de datos para mostrar un nuevo gráfico
     * @param newData
     */
    public void changeDataSet(ArrayList<DataEntity> newData){
        xySeriesCollection.removeAllSeries();
        xySeries = new XYSeries("Statistics");
        for(DataEntity d:newData){
            xySeries.add(d.getDataId().getYear(),d.getPercentage());
            System.out.println(d.getDataId().getYear() + "  " + d.getPercentage() );
        }
        xySeriesCollection.addSeries(xySeries);

    }

    /**
     * Crea la image del chart devolviendo un array de bytes que despues será enviado a la vista desde
     * un custom tag
     * @return
     */
    public byte[] createImage(){
        BufferedImage bufferedImage = jFreeChart.createBufferedImage(500,300);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            ImageIO.write(bufferedImage,"png",byteArrayOutputStream);
        } catch (IOException e){
            // manejar la excepción
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;
    }
}
