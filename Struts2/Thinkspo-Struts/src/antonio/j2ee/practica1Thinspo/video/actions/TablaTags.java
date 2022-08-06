package antonio.j2ee.practica1Thinspo.video.actions;

import java.util.List;

import antonio.j2ee.practica1Thinspo.video.modelo.Tag;
import antonio.j2ee.practica1Thinspo.video.modelo.VideoDAOFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action de Struts2 para dar soporte a las tablas de datos de Tags(La que da acceso al JUNG)
 * Ofrece el metono listar,que es al que se llama en cada carga/cambio de pagina de tablas de tags
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class TablaTags extends ActionSupport{
	private static final long serialVersionUID = 997563468301674240L;
	private List<Tag> modelotags;
    private Integer rows= 0;
    private Integer page= 0;
	private Integer total= 0;
	private Integer records= 0;

	  /**
	   * Metodo que carga la tabla de Tagas
	   * Invocado desde los grid de tags
	   * @return
	   * @throws Exception
	   */
	public String listarTags()throws Exception {
		 int limite = (rows * page);
		 int offset= limite-rows;
         records = VideoDAOFactory.getInstancia().getVideoDAO().countTags();
         modelotags = (List<Tag>)  VideoDAOFactory.getInstancia().getVideoDAO().findTags(limite, offset);
		 total =(int) Math.ceil((double)records / (double)rows);
		 return SUCCESS;
	  }

	   //getters y setters
	  
	public List<Tag> getModelotags() {
		return modelotags;
	}

	public void setModelotags(List<Tag> modelotags) {
		this.modelotags = modelotags;
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
