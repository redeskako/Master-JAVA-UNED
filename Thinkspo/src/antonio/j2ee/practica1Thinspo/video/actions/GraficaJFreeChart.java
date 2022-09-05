package antonio.j2ee.practica1Thinspo.video.actions;

import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import antonio.j2ee.practica1Thinspo.channel.modelo.ChannelDAOFactory;
import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;
import antonio.j2ee.practica1Thinspo.video.modelo.Agrupador;
import antonio.j2ee.practica1Thinspo.video.modelo.VideoDAOFactory;

import com.opensymphony.xwork2.ActionSupport;
/**
 * Action de Struts2 para dar soporte a obtencion de Graficas JFreeChart
 * Ofrece el obtenerGrafica() que es el que forma la grafica
 * El resultado lo devuelve como tipo chart
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class GraficaJFreeChart extends ActionSupport {
	private static final long serialVersionUID = -4736112844289050776L;
	protected JFreeChart chart;
	//tipo de grafica a formar,llega del combo de graficas
	protected int tipoGrafica;
	
	/**
	 * Forma la grafica JFreeChart
	 * @return
	 * @throws Exception
	 */
	public String obtenerGrafica() throws Exception{
		switch (tipoGrafica) {
		case 1://Usuarios mas visitados
			obtenerUsuariosMasVisitados();
			break;
		
		case 2://Top 10 de Videos que mas gustan
			obtenerVideosMasGustan();
			break;
		case 3://Top 10 de Videos que mas disgustan
			obtenerVideosMasDisgustan();
			break;
		case 4://Top 7 Canales mas channel views
			obtenerCanalesMasChannelViews();
			break;	
		case 5://Top 5 Canales mas subcriptores
			obtenerCanalesMasSubcriptores();
			break;	
	     }	
		return SUCCESS;
	}

	  ////////getters y setters
	public JFreeChart getChart() {
		return chart;
	}

	public void setTipoGrafica(int tipoGrafica) {
		this.tipoGrafica = tipoGrafica;
	}
          ///////////////////////
	/**
	 * Obtiene la grafica tipo "queso3D" de los 5 usuarios cuyos videos son mas visitados
	 * @throws SQLNegocioException
	 */
    protected void obtenerUsuariosMasVisitados() throws SQLNegocioException{
    	Iterator<Agrupador>itDatos=VideoDAOFactory.getInstancia().getVideoDAO().findUsuariosMasVistos().iterator();
    	DefaultPieDataset dataset=new DefaultPieDataset();
    	while (itDatos.hasNext()){
    		Agrupador av=itDatos.next();
    		dataset.setValue(av.getClave(), av.getSuma());
       	}
       	chart=ChartFactory.createPieChart3D("Top 5 Usuarios mas visitados",dataset,true,true,true);
    }
	
    /**
     * Obtiene la grafica tipo Anillo de los 10 videos que mas gustan
     * @throws SQLNegocioException
     */
    protected void obtenerVideosMasGustan() throws SQLNegocioException{
    	Iterator<Agrupador>itDatos=VideoDAOFactory.getInstancia().getVideoDAO().findVideosMasLikes().iterator();
    	DefaultPieDataset  dataset=new DefaultPieDataset ();
    	while (itDatos.hasNext()){
    		Agrupador av=itDatos.next();
    		dataset.setValue(av.getClave(), av.getSuma());
       	}
       	chart=ChartFactory.createRingChart("Top 10 Videos mas Gustan", dataset, true, true, true);
    }   
	
    /**
     * Obtiene la grafica tipo "queso de los 10 videos que mas disgustan"
     * @throws SQLNegocioException
     */
    protected void obtenerVideosMasDisgustan() throws SQLNegocioException{
    	Iterator<Agrupador>itDatos=VideoDAOFactory.getInstancia().getVideoDAO().findVideosMasDisLikes().iterator();
    	DefaultPieDataset  dataset=new DefaultPieDataset();
    	while (itDatos.hasNext()){
    		Agrupador av=itDatos.next();
    		dataset.setValue(av.getClave(), av.getSuma());
       	}
    	chart=ChartFactory.createPieChart("Top 10 Videos mas Disgustan",dataset,true,true,true);
    }   

    /**
     * Obtiene la grafica tipo "queso3D" de los 7 canales con mas channelViews
     * @throws SQLNegocioException
     */
    protected void obtenerCanalesMasChannelViews() throws SQLNegocioException{
    	Iterator<Agrupador>itDatos=ChannelDAOFactory.getInstancia().getChannelDAO().findChannelsMasViews().iterator();
    	DefaultPieDataset  dataset=new DefaultPieDataset ();
    	while (itDatos.hasNext()){
    		Agrupador av=itDatos.next();
    		dataset.setValue(av.getClave(), av.getSuma());
       	}
    	chart=ChartFactory.createPieChart3D("Top 7 Channels Mas Views",dataset,true,true,true);
    }
    
    /**
     * Obtiene la grafica tipo Anillo de los 5 channels que mas subcriptores tienen
     * @throws SQLNegocioException
     */
    protected void obtenerCanalesMasSubcriptores() throws SQLNegocioException{
    	Iterator<Agrupador>itDatos=ChannelDAOFactory.getInstancia().getChannelDAO().findChannelsMasSubscriptores().iterator();
    	DefaultPieDataset  dataset=new DefaultPieDataset ();
    	while (itDatos.hasNext()){
    		Agrupador av=itDatos.next();
    		dataset.setValue(av.getClave(), av.getSuma());
       	}
       	chart=ChartFactory.createRingChart("Top 5 Channels mas Subscriptores", dataset, true, true, true);
    }   
    
}
