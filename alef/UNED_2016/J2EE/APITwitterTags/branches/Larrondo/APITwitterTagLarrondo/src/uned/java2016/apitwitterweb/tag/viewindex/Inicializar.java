package uned.java2016.apitwitterweb.tag.viewindex;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class Inicializar extends TagSupport{
	private static final long serialVersionUID = 1L;

	private static String usuario;
	
	private static ArrayList<String> listaHashtags;
	private static String miHashtag;
	private static ArrayList<Integer> listaPrimeros;
	private static ArrayList<Integer> listaCuantos;
	private HttpServletResponse response;
    
	public Inicializar(){
		super();
	}

    @Override
	public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse res=(HttpServletResponse)response;
        Inicializar.usuario=(String) session.getAttribute("idSesion");

        if(usuario != null){
        	Inicializar.listaHashtags=(ArrayList<String>)request.getAttribute("miListaHashtagsHTML");
        	Inicializar.miHashtag=(String)request.getAttribute("queHashtagElegido");
    		
    		//Crear los combo para elegir el primer tweet a presentar y cuantos en cada página.
    		listaPrimeros=new ArrayList<Integer>();
    		listaPrimeros.add(0,1);
    		listaPrimeros.add(1,40);
    		listaPrimeros.add(2,80);
    		listaPrimeros.add(3,120);
    		listaPrimeros.add(4,160);
    		listaPrimeros.add(5,200);
    		listaCuantos=new ArrayList<Integer>();
    		listaCuantos.add(0,5);
    		listaCuantos.add(1,25);
    		listaCuantos.add(2,50);
    		listaCuantos.add(3,75);
    		listaCuantos.add(4,100);

    		//Si no hay hashtags, porque es la primera vez que se carga la página se cargan de la BBDD
    		if(listaHashtags==null){
    			try {
					this.pageContext.getOut().print("<script type='text/javascript'>window.location='APITwitterView';</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else{
    			 return EVAL_PAGE;
			}	
        }
        else{
    		try {
				res.sendRedirect("./index.jsp?estado=ilegal");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        return SKIP_BODY;
    }
    
    public static ArrayList<String> getListaHashtags(){
    	return Inicializar.listaHashtags;
    }
    
    public static String getMiHashtag(){
    	return Inicializar.miHashtag;
    }
    
    public static String getUsuario(){
    	return Inicializar.usuario;
    }
    
    public static ArrayList<Integer> getListaPrimeros(){
    	return Inicializar.listaPrimeros;
    }
    
    public static ArrayList<Integer> getListaCuantos(){
    	return Inicializar.listaCuantos;
    }
}

