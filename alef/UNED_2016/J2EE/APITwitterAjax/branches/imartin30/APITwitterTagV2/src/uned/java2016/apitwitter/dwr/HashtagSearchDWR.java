package uned.java2016.apitwitter.dwr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * Clase DWR que expone un metodo para poder buscar hashtag's que empiezan por una cadena arbitraria,
 * que se pasa como parametro
 *
 */
public class HashtagSearchDWR {

	/**
	 * Constructor
	 */
	public HashtagSearchDWR(){
		
	}
	
	
	/**
	 * Metodo expuesto a DWR mediante el que un cliente DWR puede solicitar la lista
	 * de hasthag's que empiezan por el texto 'text'
	 * @param text Texto sobre el que buscar hashtags
	 * @return Array con los hashtag's presentes en la BBDD y q empiezan por 'text', o null
	 * si no se encuentra ninguno.
	 */
	public String[] getSimilarHashtag(String text){
		String[] ret=null;
		// obtenemos la conexion
		Connection c=this.getConnection();
		if(c!=null)
		{
			// buscamos
			ret=this.searchSimilarHashtag(text,c);
			//y liberamos la conexion
			try{c.close();}catch(Exception e){System.out.println("Error liberando recursos");e.printStackTrace(System.out);}finally{c=null;}
		}
		return ret;
	}
	
	
	/**
	 * Intenta obtener una conexion libre del pool en el JNDI jdbc/apitwitterDB
	 * @return la conexion, o null si hay problemas.
	 */
	protected Connection getConnection(){
		Connection ret=null;

		Context initCtx=null;
		Context envCtx=null;
		DataSource ds=null;
		try{
			// obtenemos el contexto JNDI
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			// y el datasource
			ds=(DataSource) envCtx.lookup("jdbc/apitwitterDB");
			ret=ds.getConnection();
		}catch(Exception e){
			System.out.println("Problemas obteniendo la conexion:"+e.getMessage());
			e.printStackTrace(System.out);
		}finally{
			try{if(initCtx!=null) initCtx.close();}catch(Exception ee){System.out.println("Problemas liberando recursos");}finally{initCtx=null;}
			envCtx=null;
			ds=null;
		}
		return ret;
	}
	
	/**
	 * Realiza la busqueda de hashtags que empiecen por la cadena 'hashtag' contra la BBDD
	 * @param hashtag Cadena con los caracteres inicio del hashtag
	 * @param c Conexion válida contra la BBDD
	 * @return Array de hashtag's que empiezan por 'hashtag'
	 */
	protected String[] searchSimilarHashtag(String hashtag,Connection c){		
		String[] ret=null;
		ResultSet rs=null;
		Statement p=null;
		try{
		p=c.createStatement();	
		String command="SELECT hashtag_id FROM hashtags_adm WHERE LEFT(UPPER(hashtag_id),LENGTH('"+hashtag+"'))=UPPER('"+hashtag+"') ORDER BY hashtag_id ASC";
		if(p.execute(command) && (rs=p.getResultSet())!=null)
		{
			ArrayList<String> ar=new ArrayList<String>();
			while(rs.next())
				ar.add(rs.getString(1));
			if(!ar.isEmpty())
				ret=ar.toArray(new String[0]);			
		}
		}catch(Exception e){
			System.out.println("Problemas buscando similares");
			e.printStackTrace(System.out);
		}finally{
			try{
				if(rs!=null) rs.close();
				}catch(Exception ee){
					System.out.println("Problemas liberando recursos");
					ee.printStackTrace(System.out);
				}finally{rs=null;}
			try{
				if(p!=null) p.close();
				}catch(Exception ee){
					System.out.println("Problemas liberando recursos");
					ee.printStackTrace(System.out);
				}finally{p=null;}				
		}
		return ret;				
	}
		
}
