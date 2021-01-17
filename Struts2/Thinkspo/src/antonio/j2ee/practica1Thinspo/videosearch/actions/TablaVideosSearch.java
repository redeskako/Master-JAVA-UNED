package antonio.j2ee.practica1Thinspo.videosearch.actions;

import java.util.List;



import antonio.j2ee.practica1Thinspo.videosearch.modelo.VideoSearch;
import antonio.j2ee.practica1Thinspo.videosearch.modelo.VideoSearchDAOFactory;

import com.opensymphony.xwork2.ActionSupport;
/**
 * Action de Struts2 para dar soporte a las tablas de datos de VideoSearch
 * Ofrece el metono listar,que es al que se llama en cada carga/cambio de pagina de tablas de videoSearch
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class TablaVideosSearch extends ActionSupport{
	private static final long serialVersionUID = 8474713943167589598L;
	private List<VideoSearch> modeloTablaVideoSearch;
    private Integer rows= 0;
    private Integer page= 0;
	private Integer total= 0;
	private Integer records= 0;
	  
	  
	  /**
	   * Metodo que carga la tabla de Videosearch
	   * Invocado desde los grid de videoserach
	   * @return
	   * @throws Exception
	   */  
    public String listar()throws Exception   {
	    int limite = (rows * page);
	    int offset= limite-rows;
	    records = VideoSearchDAOFactory.getInstancia().getVideoSearchDAO().countVideoSearchs();
	    modeloTablaVideoSearch = (List<VideoSearch>)  VideoSearchDAOFactory.getInstancia().getVideoSearchDAO().findVideoSearch(limite, offset);
	    total =(int) Math.ceil((double)records / (double)rows);
	    return SUCCESS;
	  }

         //getters y setters
    public List<VideoSearch> getModeloTablaVideoSearch() {
		return modeloTablaVideoSearch;
	}

	public void setModeloTablaVideoSearch(List<VideoSearch> modeloTablaVideoSearch) {
		this.modeloTablaVideoSearch = modeloTablaVideoSearch;
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