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
		
		if(listaTweets!=null){
			try {
				this.pageContext.getOut().print("<script type='text/javascript'>borrarTweetsAnteriores();</script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String ErrorenPrimero=(String)request.getAttribute("hayErrorenPrimero");
			//Si hay error de que buscamos el primer tweet que no tenemos
			if(ErrorenPrimero.equals("Si")){
				try {
					this.pageContext.getOut().print("<div id='capa_errores1' class='misZonas'>");
					this.pageContext.getOut().print("<p>ERROR: El primer Registro ha mostrar es superior al número total de registos</p>");
					this.pageContext.getOut().print("<p>Se muestran los registros desde el primero</p>");
					this.pageContext.getOut().print("</div>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			//Crear los combo para elegir el primer tweet a escribir y cuantos en cada página
			String myfirst=(String)request.getAttribute("quePrimeroElegido");
			int miPrimero=Integer.parseInt(myfirst);
			String myHowMany=(String)request.getAttribute("queCuantosElegido");
			int miCuantos=Integer.parseInt(myHowMany);
			
			String myPagina=(String)request.getAttribute("quePagina");
			if(myPagina!=null){
				miPagina=Integer.parseInt(myPagina);
			}
			try {
				this.pageContext.getOut().print("<div id='capa_principal' class='misZonas'>");
				this.pageContext.getOut().print("<div id='zonaResultadosTitulo'>");
				this.pageContext.getOut().print("<div id='zonaResultadosTituloTexto'><h5>RESULTADOS</h5></div>");
				this.pageContext.getOut().print("<div id='zonaResultadosTituloSelect'>");
				this.pageContext.getOut().print("<p>Primer Tweet</p>&#160;");
				this.pageContext.getOut().print("<select id='selectPrimero'>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			listaPrimeros=new ArrayList<Integer>();
			listaCuantos=new ArrayList<Integer>();
			listaPrimeros=Inicializar.getListaPrimeros();
			listaCuantos=Inicializar.getListaCuantos();
			
			for(int i=0;i<listaPrimeros.size();++i){
				if(miPrimero==listaPrimeros.get(i)){
					try {
						this.pageContext.getOut().print("<option value='"+listaPrimeros.get(i)+"' selected='selected'>"+listaPrimeros.get(i)+"</option>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					try {
						this.pageContext.getOut().print("<option value='"+listaPrimeros.get(i)+"'>"+listaPrimeros.get(i)+"</option>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}										
			try {
				this.pageContext.getOut().print("</select>");
				this.pageContext.getOut().print("&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<p>Número Tweets</p>&#160;");
				this.pageContext.getOut().print("<select id='selectNumeroTweets'>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for(int i=0;i<listaCuantos.size();++i){
				if(miCuantos==listaCuantos.get(i)){
					try {
						this.pageContext.getOut().print("<option value='"+listaCuantos.get(i)+"' selected='selected'>"+listaCuantos.get(i)+"</option>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					try {
						this.pageContext.getOut().print("<option value='"+listaCuantos.get(i)+"'>"+listaCuantos.get(i)+"</option>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
			try {
				this.pageContext.getOut().print("</select>");
				this.pageContext.getOut().print("</div>");
				this.pageContext.getOut().print("</div>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				//Poner los Tweets en pantalla
				try {
					this.pageContext.getOut().print("<div id='zonaResultadosGeneral'>");
					for(int i=miPagina*20;i<(miPagina+1)*20;++i) {
						if(i<listaTweets.size()){
							this.pageContext.getOut().print("<div id='zonaResultadoParticular"+i+"' class='zonaResultadoParticular'>");
							this.pageContext.getOut().print("<div id='zonaID"+i+"' class='zonasID'><h5>TweetID:&#160;&#160;</h5><p>"+listaTweets.get(i).getId()+"</p></div>");
							this.pageContext.getOut().print("<div id='zonaAutor"+i+"' class='zonasAutor'><h5>Autor:&#160;&#160;</h5><p>"+listaTweets.get(i).getUser().getName()+"</p></div>");
							this.pageContext.getOut().print("<div id='zonaUrl"+i+"' class='zonasUrl'><h5>URL:&#160;&#160;</h5><a href='"+listaTweets.get(i).getUrl().getUrl()+"' target='_blank'>"+listaTweets.get(i).getUrl().getUrl()+"</a></div>");
							this.pageContext.getOut().print("<div id='zonaTexto"+i+"' class='zonasTexto'><h5>Texto:&#160;&#160;</h5><p>"+listaTweets.get(i).getText()+"</p></div>");
							this.pageContext.getOut().print("</div>");
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				//Paginación
				try {
					this.pageContext.getOut().print("<script type='text/javascript'>crearPaginacion();</script>");
					this.pageContext.getOut().print("</div>");
					this.pageContext.getOut().print("</div>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
        return SKIP_BODY;
    }

}
