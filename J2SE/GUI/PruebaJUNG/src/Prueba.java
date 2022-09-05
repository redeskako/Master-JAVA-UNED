import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.collections15.functors.ConstantTransformer;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer.InsidePositioner;


/**
 * Demo de visualización de grafo
 * @author Paz Rodriguez
 * @author Marcos Bello
 * 
 */
public class Prueba {

    /**
     * Declaramos el grafo
     */
    DirectedSparseMultigraph<String, Number> graph;

    /**
     * Declaramos el componente visual para dibujar y renderizar el grafo
     */
    VisualizationImageServer<String, Number> vv;
    
    /**
     * 
     */
    public Prueba() {
        
        // Creamos el grafo
        graph = new DirectedSparseMultigraph<String, Number>();
        //Indicamos el numero de vertices que va a tener
        int numVertices=10;
        String[] v = createVertices(numVertices);
        createEdges(v,numVertices);
        
        
        // Se crea la visualización del grafo
        vv =  new VisualizationImageServer<String,Number>(new KKLayout<String,Number>(graph), new Dimension(600,600));

        vv.getRenderer().setVertexRenderer(
        		new GradientVertexRenderer<String,Number>(
        				Color.white, Color.red, 
        				Color.white, Color.blue,
        				vv.getPickedVertexState(),
        				false));
        vv.getRenderContext().setEdgeDrawPaintTransformer(new ConstantTransformer(Color.lightGray));
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
        vv.getRenderContext().setArrowDrawPaintTransformer(new ConstantTransformer(Color.lightGray));
        
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPositioner(new InsidePositioner());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);

        
        // Creamos el JFrame que va a contener al grafo
        final JFrame frame = new JFrame();
        Container content = frame.getContentPane();                

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Le damos las dimensiones al grafo
        Image im = vv.getImage(new Point2D.Double(300,300), new Dimension(500,500));
        Icon icon = new ImageIcon(im);
        JLabel label = new JLabel(icon);
        content.add(label);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Función para crear los vertices
     * @param count -> cuantos vertices creamos
     * @return el número de vertices del en un array
     */
    private String[] createVertices(int count) {
        String[] v = new String[count];
        for (int i = 0; i < count; i++) {
        	v[i] = "V"+i;
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
    				
    				graph.addEdge(new Double(Math.random()), v[i], v[j], EdgeType.DIRECTED);    				
    	    		System.out.println("Vertices: "+i+" - "+j);
    			}
    		}
    	}
    	
//    	for (int i=0;i<=limit;i++){
//    		int num = r.nextInt(limit+1);
//    		while (i==num){
//    			num = r.nextInt(limit+1);
//    		}
//    		graph.addEdge(new Double(Math.random()), v[i], v[num], EdgeType.DIRECTED);
//    		System.out.println("Vertices: "+i+" - "+num);
//    	}
    	
        /*graph.addEdge(new Double(Math.random()), v[0], v[1], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[0], v[3], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[0], v[4], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[4], v[5], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[3], v[5], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[1], v[2], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[1], v[4], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[8], v[2], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[3], v[8], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[6], v[7], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[7], v[5], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[0], v[9], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[9], v[8], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[7], v[6], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[6], v[5], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[4], v[2], EdgeType.DIRECTED);
        graph.addEdge(new Double(Math.random()), v[5], v[4], EdgeType.DIRECTED);*/
    }

    /**
     * Main para probar que se dibuja el grafo
     */
    public static void main(String[] args) 
    {
        new Prueba();
        
    }
}