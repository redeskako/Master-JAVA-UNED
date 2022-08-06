package antonio.j2ee.practica1Thinspo.video.actions;

import java.util.List;

import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;
import antonio.j2ee.practica1Thinspo.video.modelo.Video;
import antonio.j2ee.practica1Thinspo.video.modelo.VideoDAOFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action de Struts2 para dar soporte a las tablas de datos de Videos
 * Ofrece el metono listar,que es al que se llama en cada carga/cambio de pagina de tablas de videos
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class TablaVideos extends ActionSupport{
	private static final long serialVersionUID = 8474713943167589598L;
	private List<Video> modeloTabla;
    private Integer rows= 0;
    private Integer page= 0;
	private Integer total= 0;
	private Integer records= 0;
	  
	  /**
	   * Metodo que carga la tabla de Videos
	   * Invocado desde los grid de videos
	   * @return
	   * @throws Exception
	   */
	  public String listar()throws Exception
	  {
		int limite = (rows * page);
		int offset= limite-rows;
	    records = VideoDAOFactory.getInstancia().getVideoDAO().countVideos();
	    modeloTabla = (List<Video>)  VideoDAOFactory.getInstancia().getVideoDAO().findVideos(limite, offset);
	    total =(int) Math.ceil((double)records / (double)rows);
	    return SUCCESS;
	  }

    
       // getters y setters
	public List<Video> getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(List<Video> modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}
}