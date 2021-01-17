package uned.java2016.apitwitterweb.tag.viewindex;

import uned.java2016.apitwitter.dao.ClinicalStudy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CrearEstudios extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<ClinicalStudy> listaEstudios;
	private static ArrayList<Integer> listaPrimeros;
	private static ArrayList<Integer> listaCuantos;
	
	private int miPagina=0;
	
	public CrearEstudios(){
		super();
	}
	
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		listaEstudios=(ArrayList<ClinicalStudy>)request.getAttribute("Estudios");
		this.pageContext.setAttribute("losEstudios",listaEstudios);

		if(listaEstudios!=null){
			//Crear los combo para elegir el primer tweet a escribir y cuantos en cada página
			String myfirst=(String)request.getAttribute("quePrimeroElegido");
			int miPrimero=Integer.parseInt(myfirst);
			String myHowMany=(String)request.getAttribute("queCuantosElegido");
			int miCuantos=Integer.parseInt(myHowMany);
			String myPagina=(String)request.getAttribute("quePagina");
			
			if(myPagina!=null){
				miPagina=Integer.parseInt(myPagina);
				this.pageContext.setAttribute("laPagina",miPagina);
			}
			
			listaPrimeros=new ArrayList<Integer>();
			listaCuantos=new ArrayList<Integer>();
			listaPrimeros=Inicializar.getListaPrimeros();
			listaCuantos=Inicializar.getListaCuantos();
			

			this.pageContext.setAttribute("laListaPrimeros",listaPrimeros);
			this.pageContext.setAttribute("laListaCuantos",listaCuantos);
			this.pageContext.setAttribute("elPrimero",miPrimero);
			this.pageContext.setAttribute("elCuantos",miCuantos);	
			
		}
		return super.EVAL_BODY_INCLUDE;
	}
}
