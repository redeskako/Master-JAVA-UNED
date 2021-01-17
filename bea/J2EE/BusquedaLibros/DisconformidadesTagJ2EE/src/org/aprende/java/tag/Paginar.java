
package org.aprende.java.tag;

import javax.servlet.jsp.tagext.TagSupport;

import org.aprende.java.Controlador;
import org.aprende.java.bbdd.*;


public class Paginar extends TagSupport{

	private static final int lineasporpagina=4;
	private int numpag;
	private int numreg;
	private int cont;
	private Disconformidad dis;

	public Paginar (){
		dis=new Disconformidad();
		numpag=0;
		numreg=0;
		cont=1;

	}

	public int doStartTag(){

		numreg=dis.numeroRegistros();

	/*	if (numreg%Paginar.lineasporpagina!=0){
			numpag=numreg/Paginar.lineasporpagina+1;
		}else {
			numpag=numreg/Paginar.lineasporpagina;
		} */
		numpag=numreg%Paginar.lineasporpagina!=0?numreg/Paginar.lineasporpagina+1:numreg/Paginar.lineasporpagina;
		this.pageContext.setAttribute("contpag", String.valueOf(cont));
		return this.EVAL_BODY_INCLUDE;
	}

	public int doAfterBody(){
	//	int cont=0;
		if (cont<numpag){
			cont++;
			this.pageContext.setAttribute("contpag",String.valueOf(cont));

			return this.EVAL_BODY_AGAIN;
		}else{
			return this.SKIP_BODY;
		}
	}

	public int doEndTag(){
		cont=1;
		return this.EVAL_PAGE;
	}

}
