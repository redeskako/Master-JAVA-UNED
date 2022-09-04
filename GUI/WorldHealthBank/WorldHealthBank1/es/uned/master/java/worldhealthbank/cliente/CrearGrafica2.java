package es.uned.master.java.worldhealthbank.cliente;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CrearGrafica2 extends JPanel
{
	private String titulo;
	private String leyendaX;
	private String leyendaY;
	public JFreeChart chart;
	private ChartPanel panel;
	private XYSeries datos;
	private XYSeriesCollection conjuntoDatos;
	
	public CrearGrafica2(){
		
		datos = new XYSeries("Estadisticas");
		/*datos.add(1985,5);
		datos.add(1986,67);
		datos.add(1987,43);
		datos.add(1988,34);
		datos.add(1989,73);
		datos.add(1990,37);
		datos.add(1991,6);*/
		conjuntoDatos = new XYSeriesCollection();
		conjuntoDatos.addSeries(datos);
		chart= ChartFactory.createXYLineChart(
                "Datos estadisticas", //Titulo Grafica
                "A�os", // Leyenda eje X
                "Paises", // Leyenda eje Y
                conjuntoDatos, // Los datos
                PlotOrientation.VERTICAL, //orientacion
                false, // ver titulo de linea
                false, //tooltips
                true  //URL
                );
		panel = new ChartPanel(chart);
		this.add( panel , java.awt.BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(450,350));
		System.out.println("Entra en creargrafica2");
	}
	public void modificarValores(XYSeries aux){
		conjuntoDatos.removeAllSeries();
		conjuntoDatos.addSeries(aux);
	}
    /*public CrearGrafica2(String titulo, String leyendaX, String leyendaY)
    {
    	this.titulo=titulo;
    	this.leyendaX=leyendaX;
    	this.leyendaY=leyendaY;
    	
        XYDataset paresDeDatos = generarDatos();
        chart1 = crearDiagrama(paresDeDatos);
        
        ChartPanel jfreeChartPanel = new ChartPanel(chart1);
       // chartPanel.add(jfreeChartPanel);
        
       // this.panel1 = new ChartPanel( chart1 );
        this.add( jfreeChartPanel , java.awt.BorderLayout.WEST);
        panel1.setPreferredSize(new Dimension(450,350));
    }
 
    public XYDataset IntroducirValores()
    {
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        //datos.add(0,0);
        conjuntoDatos.addSeries(datos);
 
        return conjuntoDatos;
    }
    public void setDataset(Dataset data){
    	
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
    }*/
 }