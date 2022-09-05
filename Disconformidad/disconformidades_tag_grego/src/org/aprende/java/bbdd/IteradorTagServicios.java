package org.aprende.java.bbdd;



import javax.servlet.jsp.tagext.TagSupport;
import java.sql.*;
import java.util.*;
import javax.servlet.jsp.JspException;


public class IteradorTagServicios extends TagSupport {
//	private Vector<Libro> libros;
	private Servicios servis;
	private int puntero;
	private Servicio elservicio;

	public final int NULO=0;


	public IteradorTagServicios(){
		//this.libros= new Vector();
		this.servis= new Servicios();
		this.elservicio=new Servicio();
		this.puntero= this.NULO;

	}

	public int doStartTag(){
		//String sql="SELECT * FROM Libro ORDER BY nombre";
 		//String sql="SELECT * FROM Servicios ORDER BY Servicio";
		//this.libros=Libro.consulta(sql);
 		this.servis=servis.AllServicios();
		this.puntero= this.NULO;
	//	return this.EVAL_BODY_INCLUDE;
		return this.SKIP_BODY;
	}

	public int doAfterBody() throws JspException{
		try{
//System.out.println("Libro:"+this.libros.get(this.puntero));
			//pageContext.getOut().print(this.libros.get(this.puntero).getLibro());
			//pageContext.getOut().print(this.servis.get(this.puntero).nombre());
			//pageContext.getOut().print(this.libros.get(this.puntero)+"<BR/>");

			elservicio.Id(this.servis.get(this.puntero).Id());
			elservicio.nombre(this.servis.get(this.puntero).nombre());

		}catch(Exception err){
			//throw new LibreriaException("Error Tag:"+err);
			throw new DisconformidadException("Error Tag.");
		}

		this.puntero++;
		if (this.puntero >= this.servis.size()){
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;


	}
	public int doEndTag(){
		try{
			for(int i=0;i<this.servis.size();i++){
				//pageContext.getOut().print("<option value="+this.libros.get(i).getId()+">"+this.libros.get(i).getLibro()+"</option>");
				pageContext.getOut().print("<option value="+this.servis.get(i).Id()+">"+this.servis.get(i).nombre()+"</option>");
			}
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:"+err);
			throw new DisconformidadException("Error Tag:");
		}
		return this.EVAL_PAGE;
	}

}
