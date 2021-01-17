package PaqueteLibros;

import javax.servlet.jsp.tagext.TagSupport;
import java.sql.*;
import java.util.*;
import javax.servlet.jsp.JspException;

public class IteradorTag extends TagSupport {
	private Vector<Libro> libros;
	private int puntero;
	public final int NULO=0;
	public IteradorTag(){
		this.libros= new Vector();
		this.puntero= this.NULO;
	}

	public int doStartTag(){
		String sql="SELECT * FROM libro";
		this.libros=Libro.consulta(sql);
		this.puntero= this.NULO;
		return this.EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspException{
		try{
//System.out.println("Libro:"+this.libros.get(this.puntero));
			pageContext.getOut().print(this.libros.get(this.puntero).getLibro());
			//pageContext.getOut().print(this.libros.get(this.puntero)+"<BR/>");
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:"+err);
			throw new LibreriaException("Error Tag.");
		}
		this.puntero++;
		if (this.puntero >= this.libros.size()){
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;
	}
	public int doEndTag(){
		try{
			String color_1="#cad0e8";
			String color_2="#627df4";
			String color_3="#dc8ce6";
			String color;

			for(int i=0;i<this.libros.size();i++){
				if (i%2==0){ color=color_1;}
				else{ color=color_2;}
				pageContext.getOut().print("<tr bgcolor='"+color+"' OnMouseover="+color_3+" OnMouseout="+color+">");

				pageContext.getOut().print("<td>"+this.libros.get(i).getLibro()+"</td>");
				pageContext.getOut().print("<td  align='center'><a href ='/Proyecto_Libreria/Libreria2?accion=borrar&id="+this.libros.get(i).getId()+"'><img src='/Proyecto_Libreria/iconos/borrar.png'  border=0/></a></td>");
				pageContext.getOut().print("</tr>");


				/*pageContext.getOut().print("<option value="+this.libros.get(i).getId()+">"+this.libros.get(i).getLibro()+"</option>");*/
			}
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:"+err);
			throw new LibreriaException("Error Tag:");
		}
		return this.EVAL_PAGE;
	}
}
