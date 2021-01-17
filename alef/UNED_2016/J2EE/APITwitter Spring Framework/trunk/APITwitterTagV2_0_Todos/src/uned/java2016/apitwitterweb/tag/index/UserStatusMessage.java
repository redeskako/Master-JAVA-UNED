package uned.java2016.apitwitterweb.tag.index;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class UserStatusMessage extends TagSupport {
	public int doStartTag(){
		// recuperamos el estado del usuario del request
		String estado=this.pageContext.getRequest().getParameter("estado");
		// comprobamos
		if(estado!=null)
		{
			JspWriter out=this.pageContext.getOut();			
			try{
			// abrimos el div para mostrar el error
			out.print("<div class=\"info_error\">");
			switch(estado)
			 {
				case "invalido":									
					out.print("<center><h1>Usuario inv&aacute;lido</h1></center>");
					break;
				case "logout":
					out.print("<center><h1>Has salido de la sesi&oacute;n</h1></center>");
					break;
				case "ilegal":
					out.print("<center><h1>Acceso ilegal, debe antes entrar en sesi&oacute;n</h1></center>");
					break;
				case "responsabilidad":
					out.print("<center><h1>Acceso no permitido, su rol no es administrador</h1></center>");
					break;
			 }
			out.print("</div>");
			}catch(Exception e){
			  System.out.println("Error escribiendo en la salida!"+e.toString());
			  throw new RuntimeException("Error escribiendo en la salida!",e);
			}
		}
		// evaluamos el resto de la pagina
		return EVAL_PAGE;
	}
}
