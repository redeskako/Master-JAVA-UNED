package uned.java2016.apitwitterweb.tag.viewindex;

import uned.java2016.apitwitter.dao.Tweet;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class CrearTweets extends TagSupport{
	private static final long serialVersionUID = 1L;

	private static ArrayList<Tweet> listaTweets;
	private static ArrayList<Integer> listaPrimeros;
	private static ArrayList<Integer> listaCuantos;
	
	private int miPagina=0;
	
	public CrearTweets(){
		super();
	}
	
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
		listaTweets = (ArrayList<Tweet>)request.getAttribute("miListaTweets");
		this.pageContext.setAttribute("losTweets",listaTweets);
		
		if(listaTweets!=null){
			String ErrorenPrimero=(String)request.getAttribute("hayErrorenPrimero");
			this.pageContext.setAttribute("ErrorPrimero",ErrorenPrimero);
			
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
