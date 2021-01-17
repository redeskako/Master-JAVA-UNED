package uned.java2016.apitwitterweb.tag.viewindex;

import uned.java2016.apitwitter.dao.Tweet;
import uned.java2016.apitwitterweb.dwr.GetTweets;

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
	
	private static GetTweets miGetTweets;
	private static int miPagina=0;
	
	public CrearTweets(){
		super();
	}
	
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
		miGetTweets=new GetTweets();
		listaTweets=miGetTweets.leerTweets();
		System.out.println("Tamaño lista de tweets "+listaTweets);
		this.pageContext.setAttribute("losTweets",listaTweets);
		
		if(listaTweets!=null){
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
		}
        return super.EVAL_BODY_INCLUDE;
    }

}
