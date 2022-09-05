package es.uned.master.java.practicayoutube.jung;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer.InsidePositioner;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

/**
 * @author Grupo Alef (Marcos Bello)
 * @author Grupo Alef (Paz Rodríguez)
 * 
 * Clase encargada de dibujar el grafo a partir de un tag y los ids de los vídeos 
 * relacionados por ese tag
 */
 
public class JungApplet extends JApplet {
	
	 /**
     * Declaracion el grafo
     */
	private DirectedSparseGraph<String, String> graph = new DirectedSparseGraph<String, String>();
	
	
	
	/**
	 * Enumerado para las figuras
	 */
	private static enum Figura {Square, Rectangle, Circle}
	
	/**
	 * Parametros que vamos a recoger
	 */
	private String tag;	
	private String videos;
	
	/**
	 * Array que contendrá los ids de los videos  
	 */
	private String[] idVideo;
	
	/**
	 * Funcion encargada de dibujar el grafo de videos
	 */
  public void inicioApplet(){
    
    
  //Indicamos el numero de vertices que va a tener
    int numVertices= idVideo.length;
    String[] v = createVertices(numVertices);
    createEdges(v,numVertices);
    
    //Componente visual para dibujar y renderizar el grafo
	VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(new CircleLayout<String, String>(graph), new Dimension(800,600));
	
    
    vv.setPreferredSize(new Dimension(800,600));
    
    Transformer<String, String> transformer = new Transformer<String, String>() {
      @Override public String transform(String arg0) { return arg0; }
    };
    vv.getRenderContext().setVertexLabelTransformer(transformer);
    
    transformer = new Transformer<String, String>() {
        @Override public String transform(String arg0) { return arg0; }
      };
    
    vv.getRenderContext().setEdgeLabelTransformer(transformer);
    vv.getRenderer().setVertexRenderer(new MyRenderer());
    
    //Damos color gris a los arcos
    vv.getRenderContext().setEdgeDrawPaintTransformer(new ConstantTransformer(Color.lightGray));
    vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
    vv.getRenderContext().setArrowDrawPaintTransformer(new ConstantTransformer(Color.lightGray));
    
    // Damos la capacidad de poder mover los vertices con el raton
    final DefaultModalGraphMouse<String,Number> graphMouse = new DefaultModalGraphMouse<String,Number>();
    vv.setGraphMouse(graphMouse);
    graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
    
   
    
    //Se crea el JFrame y se le añade el componente visual con el grafo
    JFrame frame = new JFrame();
    
    frame.getContentPane().add(vv);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
 
  /**
   * Clase encargada de dibujar rectangulo, cuadrado o circulo
   * dependiendo de el comienzo de la cadena de identificacion de 
   * cada vertice.
   *
   */
  static class MyRenderer implements Renderer.Vertex<String, String> {
    @Override public void paintVertex(RenderContext<String, String> rc,
        Layout<String, String> layout, String vertex) {
      GraphicsDecorator graphicsContext = rc.getGraphicsContext();
      Point2D center = layout.transform(vertex);
      Shape shape = null;
      Color color = null;
      
      //Dependiendo de la palabra por la que empiece el id del vertice dibujamos una figura u otra
      if(vertex.startsWith("Square")) {
        shape = new Rectangle((int)center.getX()-10, (int)center.getY()-10, 20, 20);
        color = new Color(127, 127, 0);
      } else if(vertex.startsWith("Rectangle")) {
        shape = new Rectangle((int)center.getX()-10, (int)center.getY()-20, 20, 40);
        color = new Color(127, 0, 127);
      } else if(vertex.startsWith("Circle")) {
        shape = new Ellipse2D.Double(center.getX()-10, center.getY()-10, 20, 20);
        color = new Color(0, 127, 127);
      }
      graphicsContext.setPaint(color);
      graphicsContext.fill(shape);    
    }
  }
  
  /**
   * Función para crear los vertices
   * @param count -> cuantos vertices creamos
   * @return el número de vertices del grafo en un array
   */
  private String[] createVertices(int count) {
      String[] v = new String[count];
      Figura[] fir = Figura.values();
      for (int i = 0; i < count; i++) {
    	//Concatenamos al inicio del id del video square, rectangle o circle dependiendo de la figura que se vaya
    	//a dibujar de manera aleatoria  
      	v[i] = fir[(new Random()).nextInt(fir.length)]+"-"+idVideo[i];
        graph.addVertex(v[i]);        
      }
      return v;
  }

  /**
   * Función que crea los arcos entre los vertices
   * @param v el array con el número de vertices que se le han pasado
   */
  void createEdges(String[] v, int numVertices) {
  	
  	Random r = new Random();
  	int limit=numVertices;
  	int limite=1;
  	int[][] m;
  	m = new int[limit][limit];
  	
  	//guardamos valor en matriz de adyacencia
  	for (int i=0;i<limit;i++){
  		for (int j=0;j<limit;j++){
  			// Para evitar recursividad en el mismo vertice le damos valor 0
  			if(i==j){
  				m[i][j]=0;
  			}else{
  				m[i][j]=r.nextInt(limite+1);
  				// Si los dos vertices ya tenían un arco no le ponemos otro 
  				if ((m[i][j]==1)&&(m[i][j]==m[j][i])){
  					m[i][j]=0;
  				}
  				System.out.println("Valor:"+m[i][j]);
  			}
  		}
  	}
  	
  	//recorremos matriz para pintar grafo
  	for (int i=0;i<limit;i++){
  		for (int j=0;j<limit;j++){
  			if (m[i][j]==1){  				
  				graph.addEdge(tag+"-"+i+"-"+j, v[i], v[j],EdgeType.DIRECTED);      				
  	    		System.out.println("Vertices: "+i+" - "+j);
  			}
  		}
  	}  	
  }
  
  /**
   * Método sobreescrito que inicia el applet
   * 
   */
  @Override
  public void init() 
  {
	  super.init();
	  
	  //Recogemos los parámetros que se le pasan al applet
	  tag = getParameter("tag");
	  videos = getParameter("videos");
	  //"idVideo1,idVideo2,idVideo3,idVideo4,idVideo5";
	  
	  //Extraemos los ids de los videos de la cadena de ids de videos que se le pasan
	  String delimitador = "[ .,?!]+";
	  idVideo = videos.split(delimitador);
	  
	  //Dibujamos el grafo
      inicioApplet();
      
  }
}