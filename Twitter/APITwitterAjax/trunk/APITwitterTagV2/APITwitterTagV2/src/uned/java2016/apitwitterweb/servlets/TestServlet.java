package uned.java2016.apitwitterweb.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class TestServlet extends HttpServlet {

	/**
	 * Usamos este metodo para forzar a q se cree el pool de conexiones en el arraque
	 * del servidor. Si no se hace asi, la primera solicitud al Pool sera la q 
	 * creara las conexiones
	 */
	@Override
	public void init(){
		Context initCtx=null;
		Context envCtx=null;
		DataSource ds=null;
		try{
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds=(DataSource) envCtx.lookup("jdbc/apitwitterDB");
			if(ds.getConnection()==null)
				throw new RuntimeException("Problemas con la conexion");
			System.out.println("Pool inicializado");
		}catch(Exception e){
			System.out.println("Problemas inicializando el pool:"+e.getMessage());
		}
	}
	
	/**
	 * Test sencillo para obtener datos de la BBDD usando una conexión del pool
	 */
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse response){
		Context initCtx=null;
		Context envCtx=null;
		DataSource ds=null;
		Connection c=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");
		ds=(DataSource) envCtx.lookup("jdbc/apitwitterDB");
		if(ds!=null)
		{
			response.getWriter().print("HAY POOOL!");
			c=ds.getConnection();
			if(c.isClosed())
				throw new RuntimeException("Conexion Cerrada!");
			String[] hashs=this.getSimilarHashtags("dia", c);
			if(hashs!=null && hashs.length>0)
				for(int i=0;i<hashs.length;i++)
					response.getWriter().println(hashs[i]);
			
		}
		}catch(Exception e){
			try{response.getWriter().print("ERROR");}catch(Exception ee){}
		}finally{
			ds=null;
			try{c.close();}catch(Exception ee){System.out.println("Error cerrando recursos");}
			System.out.println("Recursos liberados con exito");
		}
		
		
	}
	
	
	/**
	 * Obtiene un array con todos los hashtag's que empiezan por la cadena 'comp' usando
	 * la conexion 'c'. El metodo NO cierra la conexion, pero SI libera los recursos
	 * asociados a la consulta
	 * @param comp Cadena a comparar
	 * @param c Conexion
	 * @return null si no hay, y un array con los hashtags solicitados si los hay
	 */
	protected String[] getSimilarHashtags(String comp,Connection c){
		String[] ret=null;
		ResultSet rs=null;
		Statement p=null;
		try{
		p=c.createStatement();	
		String command="SELECT hashtag_id FROM hashtags_adm WHERE LEFT(UPPER(hashtag_id),LENGTH('"+comp+"'))=UPPER('"+comp+"') ORDER BY hashtag_id ASC";
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
		}finally{
			try{if(rs!=null) rs.close();}catch(Exception ee){System.out.println("Problemas liberando recursos");ee.printStackTrace(System.out);}finally{rs=null;}
			try{if(p!=null) p.close();}catch(Exception ee){System.out.println("Problemas liberando recursos");ee.printStackTrace(System.out);}finally{p=null;}
		}
		return ret;
	}
}
