package es.uned2014.recursosAlpha.clasesTag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Define las acciones correspondientes a un tag personalizado.
 * Cambia del modo de vista de la aplicaciÛn entre vista-empleado y vista-responsable en 
 * el caso de que un responsable quiera acceder a una p·gina en el modo de vista incorrecto. 
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 2.0
 * @since	Julio 2014
 */
public class CambioVista extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private int visible; // obtiene su valor directamente al haber escrito la etiqueta
	private int rol; // se obtiene su valor de la sesi√≥n
	private int vista; // se obtiene su valor de la sesi√≥n

	public int doStartTag(){
		
		HttpSession session = this.pageContext.getSession();
		if(session.getAttribute("rolSesion") != null){
			this.rol = (Integer)session.getAttribute("rolSesion");
		} 
		if(session.getAttribute("vistaSesion") != null){
			this.vista = (Integer)session.getAttribute("vistaSesion");
		} 
		// Si el rol es de responsable (2), y intenta acceder con la vista incorrecta, se cambia de perfil
		if (rol == 2 && vista != visible){
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	/**
	 * @return the visible
	 */
	public int getVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(int visible) {
		this.visible = visible;
	}




}
