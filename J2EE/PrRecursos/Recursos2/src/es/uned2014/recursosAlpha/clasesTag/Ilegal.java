package es.uned2014.recursosAlpha.clasesTag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class Ilegal extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private int rol; // obtiene su valor directamente al haber escrito la etiqueta
	private int rolIlegal; // se obtiene su valor de la sesión

	public int doStartTag(){
		HttpSession session = this.pageContext.getSession();
		if(session.getAttribute("rolSesion") != null){
			this.rol = (Integer)session.getAttribute("rolSesion");
		} else {
			rol = 0;
		}
		// Si el rol de la sesión coincide con el rol ilegal, se ejecuta el body, que reenvía a usuarioIlegal
		if (rol == rolIlegal){
			System.out.println("Acceso ilegal (Ilegal.java)");
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
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
	 * @return the rolIlegal
	 */
	public int getRolIlegal() {
		return rolIlegal;
	}

	/**
	 * @param rolIlegal the rolIlegal to set
	 */
	public void setRolIlegal(int rolIlegal) {
		this.rolIlegal = rolIlegal;
	}

	
	
	

}
