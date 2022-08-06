package antonio.j2ee.practica1Thinspo.channel.actions;

import java.util.List;
import antonio.j2ee.practica1Thinspo.channel.modelo.Channel;
import antonio.j2ee.practica1Thinspo.channel.modelo.ChannelDAOFactory;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action de Struts2 para dar soporte a las tablas de datos de Canales
 * Ofrece el metono listar,que es al que se llama en cada carga/cambio de pagina de tablas de canales
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class TablaChannels extends ActionSupport{
  	 private static final long serialVersionUID = -3102968499293734510L;
  	 //Representa al modelo de tabla
     private List<Channel> modeloTabla;
     //Representa las filas que se piden
     private Integer rows= 0;
     //Representa la pagina
     private Integer page= 0;
     //Representa el  total de paginas
	 private Integer total= 0;
	 //Representa el total de registros
	 private Integer records= 0;
	  
	  
	  /**
	   * Metodo que carga la tabla de Canales
	   * Invocado desde los grid de Canales
	   * @return
	   * @throws Exception
	   */
	  public String listar()throws Exception
	  {
		 //Calculo el numero de registros y el offset para pedirselos al DAO 
    	 int limite = (rows * page);
		 int offset= limite-rows;
	     //Obtenemos el numero total de registros
	    records = ChannelDAOFactory.getInstancia().getChannelDAO().countChannels();
        //Buscamos los canales que nos indican rows filas y to offfset
	    modeloTabla = (List<Channel>)  ChannelDAOFactory.getInstancia().getChannelDAO().findChannels(limite, offset);
	    //Indicamos el total de paginas de datos que hay
	    total =(int) Math.ceil((double)records / (double)rows);
	    return SUCCESS;
	  }

	  //Getters y Setters
	  
	public List<Channel> getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(List<Channel> modeloTabla) {
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
