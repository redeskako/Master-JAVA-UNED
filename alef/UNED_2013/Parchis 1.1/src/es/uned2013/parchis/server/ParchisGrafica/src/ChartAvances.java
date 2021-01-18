package es.uned2013.parchis.server.ParchisGrafica.src;


 /**  
 * Esta clase muestra un diagrama de barras (BarChart) con diferentes variables que
 * reflejan aspectos del juego de cada jugador.
 * En este tipo de gr�ficos o diagramas, se muestran datos tabulados. Los datos tabulados
 * (tablas) se componen de columnas y filas que despu�s se convierten en el gr�fico 
 * en categor�as y series respectivamente.
 * Los jugadores ser�an las columnas y las filas las ocupan diferentes variables del
 * juego asignadas a cada jugador.
 * En este primer caso s�lo se "grafica" los avances de un determinado jugador durante
 * una partida, pero tambi�n se podr�an mostrar otras variables relativas al jugador,
 * como la suma total del valor de los dados de sus respectivas tiradas, el n�mero total
 * de avances que le resta a cada jugador para que todas sus casillas lleguen a la meta...
 * Todos estos datos se podr�an mostrar con una nueva "barra" asignada a cada jugador. Cada
 * nueva "barra" corresponde a una nueva fila en los datos tabulados y una nueva serie en
 * el gr�fico.
 * 
 *
 * Creating charts with JFreeChart is a three step process.
 *  You need to:
 *	� create a dataset containing the data to be displayed in the chart;
 *	� create a JFreeChart object that will be responsible for drawing the chart;
 *	� draw the chart to some output target (often, but not always, a panel on the screen);
 *
 */   

import java.awt.Color;   
import java.awt.Dimension;   
import java.awt.GradientPaint;   
import java.util.Random;
   
import javax.swing.JFrame;
import javax.swing.JPanel;   
   
import org.jfree.chart.ChartFactory;   
import org.jfree.chart.ChartPanel;   
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.axis.CategoryAxis;   
import org.jfree.chart.axis.CategoryLabelPositions;   
import org.jfree.chart.axis.NumberAxis;   
import org.jfree.chart.plot.CategoryPlot;   
import org.jfree.chart.plot.PlotOrientation;   
import org.jfree.chart.renderer.category.BarRenderer;   
import org.jfree.data.category.CategoryDataset;   
import org.jfree.data.category.DefaultCategoryDataset;      
import org.jfree.ui.RefineryUtilities;   
   
public class ChartAvances extends JFrame {   
    
	private static final long serialVersionUID = 1L;// Para que sea serializable.
	//Para evitar warning del IDE. M�s informaci�n sobre Serializable en 
	//http://chuwiki.chuidiang.org/index.php?title=Serializaci%C3%B3n_de_objetos_en_java
	
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();// Dataset (tabla) del gr�fico
	
	/**
	 * Constructor de la clase
	 * 
     * @param title: etiqueta de la ventana.
     */
	public ChartAvances(String title) throws InterruptedException {  
		
        super(title);   
        JPanel chartPanel = createPanel();// Panel en el que se crea el gr�fico 
        chartPanel.setPreferredSize(new Dimension(500, 270));// Tama�o del panel
        setContentPane(chartPanel);// A�ade el panel a la ventana   
          
    }   
   
    /**  
     * Devuelve un dataset. Conjunto de datos del gr�fico
     * que contiene los valores iniciales del gr�fico.  
     *   
     * @return dataset.  
     */   
    private DefaultCategoryDataset createDataset() {   
           
        String series1 = "Avances";// �nica serie (fila)   
           
        String category1 = "Amarillo";// Columnas   
        String category2 = "�zul";   
        String category3 = "Rojo";   
        String category4 = "Verde";   
           
        dataset.addValue(0.0, series1, category1); // Valores iniciales  
        dataset.addValue(0.0, series1, category2);   
        dataset.addValue(0.0, series1, category3);   
        dataset.addValue(0.0, series1, category4);   
                   
        return dataset;   
           
    }   
       
    /**  
     * Crea el gr�fico a partir del conjunto de datos incluidos en 
     * el dataset.
     *   
     * @param dataset  (conjunto de datos)  
     *   
     * @return chart (gr�fico)  
     */   
    private static JFreeChart createChart(CategoryDataset dataset) {   
        
    	// ChartFactory.createBarChart3D crea las barras en tres dimensiones.
    	// sin modificar nada m�s, aunque ser�a necesario variar el dise�o para
    	// que la apariencia mejorase.
    	// Est� misma parametrizaci�n tambi�n es v�lida para .createStackedBarChart 
    	// y .createStackedBarChart3D, est�s dos �ltimos tipos de gr�ficos 
    	// "apilan barras" en una misma "barra". En este caso no es aplicable.
        JFreeChart chart = ChartFactory.createBarChart(// Crea el gr�fico...   
            "Avances en la partida actual",	// t�tulo del gr�fico   
            "Jugadores",               		// etiqueta del eje de las columnas   
            "Avances",                  	// etiqueta del eje de las filas
            dataset,                  		// dataset con los datos   
            PlotOrientation.VERTICAL, 		// orientaci�n   
            false,                     		// no incluye leyenda (no es �til si s�lo hay una serie)
            false,                     		// sin tooltips   
            false                     		// URLs...no se utiliza
        );   
   
        // Dise�o del gr�fico. Opcional, se modifican los valores por defecto.  
   
        // Establece el color de fondo de la ventana del gr�fico   
        chart.setBackgroundPaint(Color.white);   
   
        // A trav�s del plot se pueden modificar algunos aspectos del gr�fico...   
        CategoryPlot plot = (CategoryPlot) chart.getPlot();   
        plot.setBackgroundPaint(Color.lightGray);// fondo del gr�fico   
        plot.setDomainGridlinePaint(Color.white);// l�neas del fondo del gr�fico   
        plot.setDomainGridlinesVisible(true);   
        plot.setRangeGridlinePaint(Color.white);// l�neas del rango de valores   
   
        // Establece el rango de valores para que se muestren valores enteros (no decimales)  
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();   
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
   
        // A trav�s del renderer se pueden modificar las "barras" que se muestran en el gr�fico   
        BarRenderer renderer = (BarRenderer) plot.getRenderer();   
                
        //TODO Con �ste m�todo se puede dibujar cada "barra" de un color diferente.
        //     Es un m�todo muy complejo que los mismos creadores de JFreeChart no recomiendan
        //     utilizar directamente. Lo utilizan otros m�todos internamente para construir el
        //     gr�fico.
        //renderer.drawItem(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
           
        // Establece un gradiente de color para el interior de la barra. 
        GradientPaint gp0 = new GradientPaint(   
            0.0f, 0.0f, Color.blue,    
            0.0f, 0.0f, new Color(0, 0, 64)   
        );   
        
        renderer.setSeriesPaint(0, gp0); // Asigna el gradiente a la �nica serie disponible
        
        // El eje de dominio (columnas) del gr�fico
        CategoryAxis domainAxis = plot.getDomainAxis(); 
        
        domainAxis.setCategoryLabelPositions(// �ngulo de las etiquetas de las columnas   
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)   
        );   
        
        return chart;   
           
    } 
    
    /**
     * M�todo para incluir nuevos valores en la gr�fica.
     * En este caso s�lo existe una serie pero se incluye el par�metro "serie" para posibles
     * desarrollos futuros con gr�ficos que incluyan m�s de una serie
     * 
     * @param valor: nuevo valor de la "barra"
     * @param serie: n�mero de serie (fila) del dataset (tabla)
     * @param jugador: n�mero de columna del dataset
     * @throws InterruptedException
     */
    public void nuevosDatos(double valor, int serie, int jugador) throws InterruptedException{
    	
    	dataset.setValue(valor, dataset.getRowKey(serie), dataset.getColumnKey(jugador));   
    	
    }
       
    /**  
     * Crea un panel para contener el gr�fico. En este caso un objeto ChartPanel
     * que hereda de JPanel y al que se le pasa un objeto JFreeChart.
     * Un objeto JFreeChart contiene una lista de objetos de tipo String que son
     * las diferentes etiquetas que aparecer�n en el gr�fico, un objeto Plot, que
     * es �nicamente el gr�fico y un objeto Dataset que incluye los datos que se
     * muestran en el gr�fico.
     *   
     * @return Un objeto JPanel.  
     * @throws InterruptedException 
     */   
    public JPanel createPanel() throws InterruptedException {   
    	
        DefaultCategoryDataset dataset = createDataset();// Crea los datos
        JFreeChart chart = createChart(dataset);// Crea el gr�fico
        
        return new ChartPanel(chart); 
    }   
       
    /**  
     * Main para probar la clase  
     *  
     * @param args  ignorados.  
     * @throws InterruptedException 
     */   
    public static void main(String[] args) throws InterruptedException {   
   
        ChartAvances grafico = new ChartAvances("Avances durante una partida");   
        grafico.pack();   
        RefineryUtilities.centerFrameOnScreen(grafico);   
        grafico.setVisible(true);
        
        //Prueba de diferentes valores
        int sleepTime = 1000;
    	
    	for (int i=0; i < 10; i++){
    		Thread.sleep(sleepTime);
    		grafico.nuevosDatos((Math.random()*100), 0, new Random().nextInt(4));
    	}
    	
    }   
   
}   
   
