package es.uned2014.recursosAlpha.clasesTag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class Mostrar extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private int rol; // obtiene su valor directamente al haber escrito la etiqueta
	private int rolMostrar; // se obtiene su valor de la sesión

	public int doStartTag(){
		HttpSession session = this.pageContext.getSession();
		if(session.getAttribute("vistaSesion") != null){
			this.rol = (Integer)session.getAttribute("vistaSesion");
		} else {
			rol = 0;
		}
		
		// Si el rol de la sesión coincide con el rol al que se desea mostrar la información, 
		// se evalúa el body
		if (rol == rolMostrar){
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	/**
	 * @return the rolMostrar
	 */
	public int getRolMostrar() {
		return rolMostrar;
	}

	/**
	 * @param rolMostrar the rolMostrar to set
	 */
	public void setRolMostrar(int rolMostrar) {
		this.rolMostrar = rolMostrar;
	}



	
	
	

}
