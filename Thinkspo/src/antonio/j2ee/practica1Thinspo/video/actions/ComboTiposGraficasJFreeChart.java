package antonio.j2ee.practica1Thinspo.video.actions;

import java.util.ArrayList;
import java.util.List;

import antonio.j2ee.practica1Thinspo.video.modelo.TipoGraficaJFree;

import com.opensymphony.xwork2.ActionSupport;
/**
 * Action de Struts2 para dar soporte a obtencion de Tipos Graficas JFreeChart soportadas
 * Ofrece el listar() que es el que forma el combo de tipos de graficas
 * El resultado lo devuelve como tipo json
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class ComboTiposGraficasJFreeChart extends ActionSupport {
	private static final long serialVersionUID = -8641690835923149322L;

	protected List<TipoGraficaJFree> tiposGraficas;
	
	public String listar(){
		if(tiposGraficas==null)
			 tiposGraficas=new ArrayList<TipoGraficaJFree>();
		tiposGraficas.add(new TipoGraficaJFree(1, "Usuarios con Videos mas visitados"));
		tiposGraficas.add(new TipoGraficaJFree(2, "Videos mas gustan"));
		tiposGraficas.add(new TipoGraficaJFree(3, "Videos menos gustan"));
		tiposGraficas.add(new TipoGraficaJFree(4, "Canales mas channel views"));
		tiposGraficas.add(new TipoGraficaJFree(5, "Canales mas suscriptores"));
		return SUCCESS;
	}

	public List<TipoGraficaJFree> getTiposGraficas() {
		return tiposGraficas;
	}

	public void setTiposGraficas(List<TipoGraficaJFree> tiposGraficas) {
		this.tiposGraficas = tiposGraficas;
	}


	
}
