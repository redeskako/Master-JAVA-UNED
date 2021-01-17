package es.uned2013.parchis.server.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.WindowConstants;

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


/**
 * Este metodo se encarga de generar la pantalla de estadisticas del servidor. Se le envía el numero de partida a mostrar y genera las 
 * consultas necesarias para mostrar los datos de la partida
 * @author alef
 */
public class marcoEstadistico extends JFrame implements ActionListener {
	
	   
	   private JLabel[] etiquetas; // arreglo de etiquetas
	   private String[] dataEtiquetas = {"ID partida: ", "Hora Inicial: ", "Hora Final: ", "Num. Jugadores: ", "% Completado: "};
	   private String numP;
	   private DefaultCategoryDataset dataset = new DefaultCategoryDataset();// Dataset (tabla) del gr�fico

	   
	   /**
	    * Lanza el marco de las estadisticas
	    * define un timer para el cual se irá actualizando los datos de la partida
	    * @param numPartida
	    * @throws ClassNotFoundException
	    * @throws SQLException
	    */
	   public marcoEstadistico(String numPartida) throws ClassNotFoundException, SQLException{
		   Timer temporizador = new Timer(500, this);
		   this.CreaMarcoEstadistico(numPartida);
		   this.obtieneDataEtiquetas();
		   temporizador.start();
		   
	   }
	   
	   
	   
	   
	  /**
	   * crea la ventana del marco e inserta cada uno de los componentes
	   * @param numPartida
	   * @throws ClassNotFoundException
	   * @throws SQLException
	   */
	   private void CreaMarcoEstadistico(String numPartida) throws ClassNotFoundException, SQLException
	   /*
	    * Constructor de la clase
	    */
	   {
		  JPanel panelEtiquetas = new JPanel();  // panel que contiene las etiquetas
	      this.numP = numPartida;
	      etiquetas = new JLabel[ 5 ]; // crea el arreglo etiquetas
	      DefaultCategoryDataset dataset = createDataset();// Crea los datos para el grafico
	      JFreeChart chart = createChart(dataset);// Crea el gr�fico
	      ChartPanel CP = new ChartPanel(chart);
	      panelEtiquetas.setLayout( new GridLayout( 1, etiquetas.length ) );
	      JPanel panelGrafico = new JPanel(); //establece el panel del grafico
	      panelGrafico.add(CP);
	      //consultamos en base de datos la info de la partida para introducirla en las etiquetas
	      // crea y agrega las etiquetas
	      for ( int cuenta = 0; cuenta < etiquetas.length; cuenta++ ) 
	      {
	         etiquetas[ cuenta ] = new JLabel( dataEtiquetas[cuenta]);
	         panelEtiquetas.add( etiquetas[ cuenta ] ); // agrega el botón al panel
	      } // fin de for
	      add (panelGrafico, BorderLayout.CENTER);
	      add( panelEtiquetas, BorderLayout.SOUTH ); // agrega el panel a JFrame
	      //this.obtieneDataEtiquetas();
	      
	   } // fin del constructor de MarcoEstadistico
	   
	   /**
	    * Consulta en base de datos la información de cada uno de los elementos del marco de estadisticas, 
	    * incluyendo la información de la grafica
	    * @throws ClassNotFoundException
	    * @throws SQLException
	    */
	   private void obtieneDataEtiquetas() throws ClassNotFoundException, SQLException{
		   Class.forName("org.sqlite.JDBC"); 
		   Connection conexion=null;
		   Statement comando = null;
		   ResultSet resultados= null;
		   String aux= null;
		   int enteroAux;
		   
		   try {
	        	conexion = DriverManager.getConnection("jdbc:sqlite:dbparchis.db");
				comando = conexion.createStatement();
				//obtenemos el id de la partida
				resultados = comando.executeQuery("select p.id_partida, fecha_inicio, fecha_fin, count(jp.id_partida) as numJug from partida as p, jugadorPartida as jp where p.id_partida = jp.id_partida and jp.id_partida = " + this.numP +" group by jp.id_partida ;");
				for (int i=0; i<4; i++){
					aux = resultados.getString(i+1);
					if (aux != null){
						etiquetas[i].setText(dataEtiquetas[i] + aux);
					}else{
						etiquetas[i].setText(dataEtiquetas[i] + "sin definir");
					}
				}
				//en esta consulta vamos a obtener el numero de fichas que se encuentran en las casillas finales para luego calcular el porcentaje de completado de la partida
				resultados = comando.executeQuery("select count(id_casilla_fin) from tirada where tirada.id_partida = "+ this.numP  +" and tirada.id_casilla_fin in (108, 208, 308, 408);");
				//calculamos el porcentaje de partida calculando el % de fichas introducidas en la casa
				if (Integer.parseInt(resultados.getString(1)) == 0){
					etiquetas[4].setText(dataEtiquetas[4] +  resultados.getString(1));
				}else{
					enteroAux = (Integer.parseInt(resultados.getString(1)) / 16) * 100;
					etiquetas[4].setText(dataEtiquetas[4] + String.valueOf(enteroAux));
				}

				//obtenemos los datos de la tirada de cada jugador				
				resultados = comando.executeQuery("select sum(valor_tirada) as total_tirada from tirada where tirada.id_partida="+ this.numP  +" group by (id_jugador) order by (id_jugador);");
				enteroAux=0;
				while(resultados.next()){
					//sumaTirada.add(enteroAux, Integer.parseInt(resultados.getString(1)));
					dataset.setValue(Integer.parseInt(resultados.getString(1)), dataset.getRowKey(0), dataset.getColumnKey(enteroAux)); 
					enteroAux++;
				}
				 // CERRAR conexión

		        resultados.close();
		        comando.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		   
	   }//fin de obtieneDataEtiquetas
	/**
	 * * Devuelve un dataset. Conjunto de datos del gr�fico
     * que contiene los valores iniciales del gr�fico.  
	 * @return dataset
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


		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				this.obtieneDataEtiquetas();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} 
}// fin de la clase