package antonio.j2ee.practica1Thinspo.video.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;
import antonio.j2ee.practica1Thinspo.video.modelo.Tag;
import antonio.j2ee.practica1Thinspo.video.modelo.Video;
import antonio.j2ee.practica1Thinspo.video.modelo.VideoDAOFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * Action de Struts2 para dar soporte a obtencion de Grafos JUNG
 * Ofrece el obtenGrafo() que es el que forma el grafo
 * El resultado lo devuelve con Stream
 * Tiene fijado como numero maximo de nodos 10,para evitar problemas de rendimiento
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class GrafoJUNG extends ActionSupport{
	private static final long serialVersionUID = -2610679065068242599L;
	//Aqui viene el tag Seleccionado en la vista
	protected String tagSeleccionado;
	//Aqui establecemos los datos de la imagen que se envia como stream al navegador
    protected InputStream streamImgGrafo;
    //Para limitar la profundidad del grafo se utiliza la constante MAXNODOS
    private final int MAXNODOS=10;

    /**
     * Forma el grafo JUNG para el tag seleccionado
     * Con un limite de MAXNODOS,obtiene los nodos que tienen dicho tag,en caso de no llegar al tope pide a cada nodo sus tag y repite la operacion
     * 
     * @return
     * @throws Exception
     */
	public String obtenGrafo()throws Exception {
		Graph<Video, String>grafo=new SparseMultigraph<Video, String>();
		//Busco videos que tengan entre sus tags el tad seleccionado
        Iterator<Video>itVideo=VideoDAOFactory.getInstancia().getVideoDAO().findVideosByTag(tagSeleccionado).iterator();
		 //añado los nodos del grafo
		  while(itVideo.hasNext()){
			  //Si hemos alcanzado el tope de nodos salimos
			  if(grafo.getVertexCount()>=MAXNODOS)
				  break;
			  Video v=itVideo.next();
			  System.out.println("trabajando con "+v.getVideoid());
			  if(!grafo.containsVertex(v)){
				  System.out.println("Añadpo video "+v.getVideoid());
				  grafo.addVertex(v);
				  anadirNodos(grafo,v.getTags());
			  }
		  }	  
		  System.out.println(grafo.getVertexCount());
		  //////
		  //añado las aristas
		  Iterator<Video>itNodos=grafo.getVertices().iterator();
		  while(itNodos.hasNext()){
			  Video nodoOrigen=itNodos.next();
			  Iterator<Tag> itTag=nodoOrigen.getTags().iterator();
			  while(itTag.hasNext()){
				  Tag tag=itTag.next();
				  Iterator<Video> itNodosTag=obtenerNodosByTag(grafo,tag);
				  while(itNodosTag.hasNext()){
					  Video nodoDestino=itNodosTag.next();
					  if(!nodoOrigen.equals(nodoDestino)) //compruebo que el nodo no sea el mismo
						  //Compruebo que no este añadido el edge
						  if( !grafo.containsEdge(nodoOrigen.getVideoid()+"-"+tag.getValor()+"-"+nodoDestino.getVideoid()) && !grafo.containsEdge(nodoDestino.getVideoid()+"-"+tag.getValor()+"-"+nodoOrigen.getVideoid()))
						         grafo.addEdge(nodoOrigen.getVideoid()+"-"+tag.getValor()+"-"+nodoDestino.getVideoid(),new Pair(nodoOrigen, nodoDestino),EdgeType.UNDIRECTED);
				  }
			  }
		  }
		 
		 //Una vez formado el grafo hay que representarlo 
		Layout<Video, String> layout = new CircleLayout<Video,String>(grafo);
		layout.setSize(new Dimension(800,675));
		VisualizationViewer<Video, String> vv=new VisualizationViewer<Video, String>(layout, new Dimension(800, 675));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Video>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.setSize(800, 675);
        JFrame jframe = new JFrame("Simple Graph View");
		jframe.getContentPane().add(vv,BorderLayout.CENTER);
		jframe.pack();
		jframe.setVisible(true);

		//obtengo la imagen a partir del Frame
		BufferedImage imagenGrafo=creaImagenGrafo(jframe);
	
		jframe.setVisible(false);
		jframe=null;
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(imagenGrafo,"png", bas);
		byte[] bytes = bas.toByteArray();	
		//establezo el Stream
		streamImgGrafo=new ByteArrayInputStream(bytes);
		
		return SUCCESS;
	  }

          //getters y setters
	public void setTagSeleccionado(String tagSeleccionado) {
		this.tagSeleccionado = tagSeleccionado;
	}

	public InputStream getStreamImgGrafo() {
		return streamImgGrafo;
	}
          ////////////////////////////


	/**
	 * A partir de un JFrame obtiene un BufferedImage
	 * @param panel
	 * @return
	 */
	private BufferedImage creaImagenGrafo(JFrame panel) {
	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paintComponents(g);
	    return bi;
	}
	
	/**
	 * Añade nodos al grafo de forma recursiva hasta alcanzar el limite de MAXNODOS
	 * @param grafo
	 * @param tags
	 * @throws SQLNegocioException
	 */
	private void anadirNodos(Graph<Video, String>grafo,Collection <Tag> tags) throws SQLNegocioException{
		//Si ya se ha llegado al numero de nodos retorno
		if (grafo.getVertexCount()>=MAXNODOS)
			return;
		Iterator<Tag> itTag=tags.iterator();
		while(itTag.hasNext()){
			//Si ya se ha llegado al numero de nodos retorno
			if(grafo.getVertexCount()>=MAXNODOS)
				  break;
			Tag tag=itTag.next();
			Iterator<Video> itVideo=VideoDAOFactory.getInstancia().getVideoDAO().findVideosByTag(tag.getValor()).iterator();
			while(itVideo.hasNext()){
				//Si ya se ha llegado al numero de nodos retorno
			    if(grafo.getVertexCount()>=MAXNODOS)
					  break;
				Video v=itVideo.next();
				if(!grafo.containsVertex(v)){
					  grafo.addVertex(v);
					  anadirNodos(grafo,v.getTags());
				  }
				  
			}
		}
		System.out.println("nodos "+grafo.getVertexCount());
	}
	
	/**
	 * Obtiene los nodos del grafo que tienen el tag pasado como parametro
	 * @param grafo
	 * @param tag
	 * @return
	 */
	private Iterator<Video> obtenerNodosByTag(Graph<Video, String>grafo,Tag tag){
		Collection<Video> retorno=new ArrayList<Video>();
		Iterator<Video>itNodos=grafo.getVertices().iterator();
		while (itNodos.hasNext()){
			Video vid=itNodos.next();
			if (vid.getTags().contains(tag)){
				retorno.add(vid);
			}
		}
		return retorno.iterator();
	}
}
