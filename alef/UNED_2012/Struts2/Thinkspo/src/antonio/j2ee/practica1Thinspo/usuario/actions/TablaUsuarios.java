package antonio.j2ee.practica1Thinspo.usuario.actions;

import java.util.Collection;
import java.util.List;

import antonio.j2ee.practica1Thinspo.usuario.modelo.Usuario;
import antonio.j2ee.practica1Thinspo.usuario.modelo.UsuarioDAOFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action de Struts2 para dar soporte a las tablas de datos de Usuarios
 * Ofrece el metono listar,que es al que se llama en cada carga/cambio de pagina de tablas de usuarios
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class TablaUsuarios extends ActionSupport{
	private static final long serialVersionUID = -4768917993121850149L;
	 //Representa al modelo de tabla
	  private List<Usuario> gridModel;
	  //Filas de datos que quiere cargar el grid
	  private Integer rows=0;
	  //Pagina de datos que se quiere
	  private Integer page=0;
	  // Total de paginas
	  private Integer total=0;
	  // Total de registros
	  private Integer records=0;
	  
	  
	  /**
	   * Metodo que carga la tabla de Usuarios
	   * Invocado desde los grid de Usuarios
	   * @return
	   * @throws Exception
	   */
	  public String listar()throws Exception {
	    int limite = (rows * page);
	    int offset= limite-rows;
	    records = UsuarioDAOFactory.getInstancia().getUsuarioDAO().countUsuarios();
	    gridModel = (List<Usuario>) UsuarioDAOFactory.getInstancia().getUsuarioDAO().findUsuarios(limite, offset);
	    total =(int) Math.ceil((double)records / (double)rows);
	    return SUCCESS;
	  }



	  //getters y setters
	  
	public Collection<Usuario> getGridModel() {
		return gridModel;
	}


	public void setGridModel(List<Usuario> gridModel) {
		this.gridModel = gridModel;
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
