import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CrearGrafica extends JPanel
{
	private String titulo;
	private String leyendaX;
	private String leyendaY;
	public JFreeChart chart1;
	ChartPanel panel1;
	public static XYSeries datos = new XYSeries("Linea Funcion");
	
    public CrearGrafica(String titulo, String leyendaX, String leyendaY)
    {
    	this.titulo=titulo;
    	this.leyendaX=leyendaX;
    	this.leyendaY=leyendaY;
    	
        XYDataset paresDeDatos = generarDatos();
        chart1 = crearDiagrama(paresDeDatos);
        this.panel1 = new ChartPanel( chart1 );
        this.add( panel1 , java.awt.BorderLayout.WEST);
        panel1.setPreferredSize(new Dimension(780,700));
    }
 
    public XYDataset generarDatos()
    {
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        //datos.add(0,0);
        conjuntoDatos.addSeries(datos);
 
        return conjuntoDatos;
    }
    
    public JFreeChart crearDiagrama(XYDataset conjuntoDatos)
    {
    	chart1 = ChartFactory.createXYLineChart(
                                titulo, //Titulo Grafica
                                leyendaX, // Leyenda eje X
                                leyendaY, // Leyenda eje Y
                                conjuntoDatos, // Los datos
                                PlotOrientation.VERTICAL, //orientacion
                                false, // ver titulo de linea
                                false, //tooltips
                                true  //URL
        );
        return chart1;
    }
 }