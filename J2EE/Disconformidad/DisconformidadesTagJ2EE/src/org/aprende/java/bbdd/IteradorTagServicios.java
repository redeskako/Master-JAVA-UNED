package org.aprende.java.bbdd;

import javax.servlet.jsp.tagext.TagSupport;

public class IteradorTagServicios extends TagSupport {
	private Servicios servis;

	public final int NULO=0;
	int cont;

	public IteradorTagServicios(){
	//	this.servis= new Servicios();
		cont=0;

	}

	public int doStartTag(){
		try{
			this.servis= new Servicios();
			this.servis= servis.AllServicios();
			cont=0;
			this.pageContext.setAttribute("seleccionado",false);
			return this.EVAL_BODY_INCLUDE;
		}catch(Exception err){
			throw new DisconformidadException("Error Tag:"+err);
		}
	}


	public int doAfterBody(){

		try{
			Integer servicioSelect=(Integer) this.pageContext.getSession().getAttribute("servicio");
			if (cont<servis.size()){
				Servicio ser = (Servicio) servis.get(cont);

				this.pageContext.setAttribute("numservicio",String.valueOf(ser.Id()));
				this.pageContext.setAttribute("nomservicio",ser.nombre());
				this.pageContext.setAttribute("seleccionado",false);

				if (servicioSelect.intValue()==this.servis.get(cont).Id()){
					this.pageContext.setAttribute("seleccionado",true);
				}

				cont++;

				return this.EVAL_BODY_AGAIN;
			}else{
				return this.SKIP_BODY;
			}
		}catch(Exception err){
			throw new DisconformidadException("Error Tag.");
		}
	}


/*	public int doEndTag(){
		try{
			Integer servicioSelect=(Integer) this.pageContext.getSession().getAttribute("servicio");
			for(int i=0;i<this.servis.size();i++){
				if (servicioSelect.intValue()==this.servis.get(i).Id()){
					pageContext.getOut().print("<option selected value="+this.servis.get(i).Id()+">"+this.servis.get(i).nombre()+"</option>");
				}else{
					//pageContext.getOut().print("<option value="+this.libros.get(i).getId()+">"+this.libros.get(i).getLibro()+"</option>");
					pageContext.getOut().print("<option value="+this.servis.get(i).Id()+">"+this.servis.get(i).nombre()+"</option>");
				}
			}
		}catch(Exception err){
			throw new DisconformidadException("Error Tag:"+err);
		}
		return this.EVAL_PAGE;
	} */

}
