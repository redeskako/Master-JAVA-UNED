import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
 
public class JungApplet extends JApplet {
	
	 /**
     * Declaramos el grafo
     */
	DirectedSparseGraph<String, String> graph = new DirectedSparseGraph<String, String>();
	
	/**
     * Declaramos el componente visual para dibujar y renderizar el grafo
     */
	VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(new CircleLayout<String, String>(graph), new Dimension(800,400));
	
	/**
	 * Declaramos un enumerado para las figuras
	 */
	private static enum Figura {Square, Rectangle, Circle}
	
	/**
	 * Declaramos los parámetros que vamos a recoger
	 */
	private String tag;
	private String numero;
	private String videos;
	private String[] idVideo;
  public void inicioApplet(){
    
    
  //Indicamos el numero de vertices que va a tener
    int numVertices= idVideo.length;//Integer.parseInt(numero);
    String[] v = createVertices(numVertices);
    createEdges(v,numVertices);
    
    /*g.addVertex("Square");
    g.addVertex("Rectangle");
    g.addVertex("Circle");
    g.addEdge("Edge1", "Square", "Rectangle");
    g.addEdge("Edge2", "Square", "Circle");
    g.addEdge("Edge3", "Circle", "Square");*/
    
    Transformer<String, String> transformer = new Transformer<String, String>() {
      @Override public String transform(String arg0) { return arg0; }
    };
    vv.getRenderContext().setVertexLabelTransformer(transformer);
    transformer = new Transformer<String, String>() {
      @Override public String transform(String arg0) { return arg0; }
    };
    vv.getRenderContext().setEdgeLabelTransformer(transformer);
    vv.getRenderer().setVertexRenderer(new MyRenderer());
 
    // The following code adds capability for mouse picking of vertices/edges. Vertices can even be moved!
    final DefaultModalGraphMouse<String,Number> graphMouse = new DefaultModalGraphMouse<String,Number>();
    vv.setGraphMouse(graphMouse);
    graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
 
    JFrame frame = new JFrame();
    frame.getContentPane().add(vv);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
 
  static class MyRenderer implements Renderer.Vertex<String, String> {
    @Override public void paintVertex(RenderContext<String, String> rc,
        Layout<String, String> layout, String vertex) {
      GraphicsDecorator graphicsContext = rc.getGraphicsContext();
      Point2D center = layout.transform(vertex);
      Shape shape = null;
      Color color = null;
      if(vertex.startsWith("Square")) {
        shape = new Rectangle((int)center.getX()+10, (int)center.getY()-10, 20, 20);
        color = new Color(127, 127, 0);
      } else if(vertex.startsWith("Rectangle")) {
        shape = new Rectangle((int)center.getX()+30, (int)center.getY()+20, 20, 40);
        color = new Color(127, 0, 127);
      } else if(vertex.startsWith("Circle")) {
        shape = new Ellipse2D.Double(center.getX()+20, center.getY()-10, 20, 20);
        color = new Color(0, 127, 127);
      }
      graphicsContext.setPaint(color);
      graphicsContext.fill(shape);
    }
  }
  
  /**
   * Función para crear los vertices
   * @param count -> cuantos vertices creamos
   * @return el número de vertices del en un array
   */
  private String[] createVertices(int count) {
      String[] v = new String[count];
      Figura[] fir = Figura.values();
      for (int i = 0; i < count; i++) {
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
  				
  				graph.addEdge("TAG-"+i+"-"+j, v[i], v[j]);    				
  	    		System.out.println("Vertices: "+i+" - "+j);
  			}
  		}
  	}  	
  }
  
  /**
   * Main para probar que se dibuja el grafo
   */
  public void init() 
  {
	  super.init();
	  //Recogemos los parámetros que se le pasan al applet
	  tag = getParameter("tag");
	  numero = getParameter("numero");
	  videos = "idVideo1,idVideo2,idVideo3,idVideo4,idVideo5";//getParameter("videos");
	  //Extraemos los ids de los videos de la cadena de ids de videos que se le pasan
	  String delimitador = "[ .,?!]+";
	  idVideo = videos.split(delimitador);
	  
      inicioApplet();
      
  }
}