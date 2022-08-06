package es.uned.master.java.healthworldbank.cliente;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * Clase que genera una grafica contenida por un elemento JPanel 
 * 
 * @author grupo alef (Hector Hernandez) 
 * @date 16/4/2012 
 */
public class CrearGrafica2 extends JPanel
{

	private static final long serialVersionUID = -6818222312173971395L;
	public JFreeChart chart;
	private ChartPanel panel;
	private XYSeries datos;
	private XYSeriesCollection conjuntoDatos;
	
	public CrearGrafica2(){
		
		datos = new XYSeries("Estadisticas");
		conjuntoDatos = new XYSeriesCollection();
		conjuntoDatos.addSeries(datos);
		chart= ChartFactory.createXYLineChart(
                "Datos estadisticas", //Titulo Grafica
                "Años", // Leyenda eje X
                "Porcentajes", // Leyenda eje Y
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
		if (aux.isEmpty()){
			conjuntoDatos.removeAllSeries();
		}else{
			conjuntoDatos.removeAllSeries();
			conjuntoDatos.addSeries(aux);
		}
	}
	public void vaciarTabla(){
		conjuntoDatos.removeAllSeries();
	}
 }