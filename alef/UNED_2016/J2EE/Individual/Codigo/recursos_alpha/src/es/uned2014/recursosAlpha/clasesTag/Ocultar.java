package es.uned2014.recursosAlpha.clasesTag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class Ocultar extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private int rol; // obtiene su valor directamente al haber escrito la etiqueta
	private int rolOcultar; // se obtiene su valor de la sesión

	public int doStartTag(){
		HttpSession session = this.pageContext.getSession();
		if(session.getAttribute("vistaSesion") != null){
			this.rol = (Integer)session.getAttribute("vistaSesion");
		} else {
			rol = 0;
		}
		
		// Si el rol de la sesión coincide con el rol al que se desea ocultar la información, 
		// se salta el contenido del body
		if (rol == rolOcultar){
			return SKIP_BODY;
		} else {
			return EVAL_BODY_INCLUDE;
		}
	}

	/**
	 * @return the rol
	 */
	public int getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return the rolOcultar
	 */
	public int getRolOcultar() {
		return rolOcultar;
	}

	/**
	 * @param rolOcultar the rolOcultar to set
	 */
	public void setRolOcultar(int rolOcultar) {
		this.rolOcultar = rolOcultar;
	}
	

}
