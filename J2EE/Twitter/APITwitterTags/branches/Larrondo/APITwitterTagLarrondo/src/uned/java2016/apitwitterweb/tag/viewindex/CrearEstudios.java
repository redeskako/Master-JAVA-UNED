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
		
		listaPrimeros=new ArrayList<Integer>();
		listaCuantos=new ArrayList<Integer>();
		listaPrimeros=Inicializar.getListaPrimeros();
		listaCuantos=Inicializar.getListaCuantos();
		
		if(listaEstudios!=null){
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
				this.pageContext.getOut().print("<p>Primer Estudio</p>&#160;");
				this.pageContext.getOut().print("<select id='selectPrimeroEstudio'>");
				for(int i=0;i<listaPrimeros.size();++i){
					if(miPrimero==listaPrimeros.get(i)){
						this.pageContext.getOut().print("<option value='"+listaPrimeros.get(i)+"' selected='selected'>"+listaPrimeros.get(i)+"</option>");
					}
					else{
						this.pageContext.getOut().print("<option value='"+listaPrimeros.get(i)+"'>"+listaPrimeros.get(i)+"</option>");
					}
				}										
				this.pageContext.getOut().print("</select>");
				this.pageContext.getOut().print("&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<p>Número Estudios</p>&#160;");
				this.pageContext.getOut().print("<select id='selectNumeroEstudios'>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				//Poniendo los estudios en pantalla
				this.pageContext.getOut().print("<div id='zonaResultadosGeneral'>");
				for(int i=miPagina*20;i<(miPagina+1)*20;++i) {
					if(i<listaEstudios.size()){
						this.pageContext.getOut().print("<div id='zonaResultadoParticular"+i+"' class='zonaResultadoParticularEstudios'>");
						this.pageContext.getOut().print("<div id='zonaNTCID"+i+"' class='zonasNTCID'><h5>NTC_ID:&#160;&#160;</h5><a href='APITwitterOBJ?queEstudio="+listaEstudios.get(i).getNctId()+"'>"+listaEstudios.get(i).getNctId()+"</a></div>");
						this.pageContext.getOut().print("<div id='zonaTituloInforme"+i+"' class='zonasTituloInforme'><h5>Título:&#160;&#160;</h5><p>"+listaEstudios.get(i).getOfficialTitle()+"</p></div>");
						this.pageContext.getOut().print("<div id='zonaResumen"+i+"' class='zonasResumen'><h5>Resumen:&#160;&#160;</h5><p>"+listaEstudios.get(i).getBriefSummary()+"</p></div>");
						this.pageContext.getOut().print("<div id='zonaFecha"+i+"' class='zonasFecha'><h5>Fecha:&#160;&#160;</h5><p>"+listaEstudios.get(i).getLastChangedDate()+"</p></div>");
						this.pageContext.getOut().print("<div id='zonaOrganizacion"+i+"' class='zonasOrganizacion'><h5>Organización:&#160;&#160;</h5><p>"+listaEstudios.get(i).getOrganization()+"</p></div>");
						this.pageContext.getOut().print("</div>");
					}
				}
				this.pageContext.getOut().print("<script type='text/javascript'>crearPaginacionEstudios();</script>");
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
