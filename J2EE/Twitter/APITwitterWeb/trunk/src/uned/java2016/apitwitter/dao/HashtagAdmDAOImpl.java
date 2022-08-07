/** HashtagAdm es la clase que encapsula la lista de todos los hashtags
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/05/12
 * @ licencia
 */
package uned.java2016.apitwitter.dao;

import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import uned.java2016.apitwitter.dao.HashtagAdm;

public class HashtagAdmDAOImpl implements HashtagAdmDAO {
	/** Atributos */
		/** Constantes */
		/** Variables */
	private Connection conn;
	private Statement stm;
	ArrayList<HashtagAdm> miListaHashtagAdm;
	ArrayList<String> miListaHashtags;
	/** Constructor por defecto, inicializa un objeto de tipo HashtagAdmDAOImpl totalmente vacío
	  *	Recoge la conexión hecha por el controlador 
	  * @ in Connection conn con la conexión del controlador
	  * @ out no procede
	  * @ error   
	  */
	public 	HashtagAdmDAOImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	/** El método insertHashtagAdm inserta un objeto HashtagAdm en la tabla administrativa.
	 * @ in El objeto HashtagAdm a insertar.
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	public void insertHashtagAdm(HashtagAdm unhashtag) throws SQLException{
		Statement stm = null;	
		int rs = 0;
		//Solo si no existe el hashtag insertamos. 
		if (!(this.exists(unhashtag.getHashtag()))){
			stm = this.conn.createStatement();
			StringBuilder sql = new StringBuilder("INSERT INTO hashtags_adm (hashtag_id,origen,fecha_entrada)");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sql.append("VALUES ( '"+unhashtag.getHashtag()+"','"+unhashtag.getOrigen()+"','"+df.format(unhashtag.getFechaEntrada()).toString()+"' )");
			System.out.println(sql.toString());
			rs = stm.executeUpdate(sql.toString());
			stm.close();
		}else {
		    System.out.println("Hashtag "+unhashtag.getHashtag()+" ya está administrado.");
		}
	}
	
	@Override
	/** El método insertHashtagAdm sobrecargado para insertar un grupo de objetos HashtagAdm en la tabla administrativa.
	 * @ in El grupo de objetos HashtagAdm a insertar.
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	public void insertHashtagAdm(ArrayList<HashtagAdm>  grupo) throws SQLException{
		Iterator<HashtagAdm> itr = grupo.iterator();
		while (itr.hasNext()){
			this.insertHashtagAdm(itr.next());
		}
	}

	@Override
	//Confirma la existencia de un estudio en base de datos.
	public boolean exists(String hashtag) throws SQLException{
		Statement stm = null;
		ResultSet rs = null;
		stm = this.conn.createStatement();
        rs = stm.executeQuery("select count(*) from hashtags_adm where hashtag_id='" + hashtag + "';");
		if (rs.next()){
			if (rs.getInt(1) > 0){return(true);}
		}
		rs.close();
		stm.close();
		return false;
	}
	
	
	@Override
	public HashtagAdm selectHashtagAdm(String hashtagid) {
		// TODO Auto-generated method stub
		return null;
	}
	/** El método selectAll es una sobrecarga de la interfaz, su función es sacar de la BBDD
	 *  la lista de todos los objetos de tipo hashtagAdm
	 * @ in 
	 * @ out ArrayList<HashtagAdm> con los objetos
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	@Override
	public ArrayList<HashtagAdm>  selectAll() throws SQLException{
		this.miListaHashtagAdm=new ArrayList<HashtagAdm>();
		this.miListaHashtagAdm=leerListaHashtagAdm("Select * from hashtags_adm");
		return this.miListaHashtagAdm;
	}

	@Override
	/** El método AlineaHashTagsAdm devuelve un único string con todos los hashtags separados por caracter "#".
	 * @ out un String con los hashtag_id.
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	public String AlineaHashTagsAdm() throws SQLException{
		StringBuilder salida = new StringBuilder();
		ArrayList<String> lista = new ArrayList<String>();
		lista = this.selectHashtagIds();
		
		int tamano = lista.size();
		
    	for (int i=0;i<tamano;i++){
    		salida.append(lista.get(i));
    		if ((tamano-i)!=1)   salida.append("#");
    	}		
		return (salida.toString());
	}
	
	@Override
	/** El método AlineaHashTagsAdm devuelve un único string con todos los hashtags administrativos separados por caracter "#".
	 * @ in un String "admin".
	 * @ out un String con los hashtag_id.
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	public String AlineaHashTagsAdm(String texto) throws SQLException{
		StringBuilder salida = new StringBuilder();
		ArrayList<String> lista = new ArrayList<String>();
		lista = this.selectHashtagIds(texto);
		
		int tamano = lista.size();
		
    	for (int i=0;i<tamano;i++){
    		salida.append(lista.get(i));
    		if ((tamano-i)!=1)   salida.append("#");
    	}		
		return (salida.toString());
	}
	
	@Override
	/** El método selectHashtagIds una estructura con los identificativos de los hashtags en BD.
	 * @ out ArrayList<String> con los hashtag_id.
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	public ArrayList<String> selectHashtagIds() throws SQLException{
		this.miListaHashtags=new ArrayList<String>();
		this.miListaHashtagAdm=new ArrayList<HashtagAdm>();
		this.miListaHashtagAdm=selectAll();
		for(int i=0;i<miListaHashtagAdm.size();++i){
			miListaHashtags.add(i,miListaHashtagAdm.get(i).getHashtag());
		}
		
		return miListaHashtags;
	}
	
	@Override
	/** El método selectHashtagIds una estructura con los identificativos de los hashtags en BD cuyo origen coincida con el String texto.
	 * @ in String coincidente con el tipo de origen.
	 * @ out ArrayList<String> con los hashtag_id.
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	public ArrayList<String> selectHashtagIds(String texto) throws SQLException{
		this.miListaHashtags=new ArrayList<String>();
		this.miListaHashtagAdm=new ArrayList<HashtagAdm>();
		this.miListaHashtagAdm=selectAll();
		for(int i=0;i<miListaHashtagAdm.size();++i){
			String literal = miListaHashtagAdm.get(i).getOrigen();
			if ( literal.equals(texto)){
				miListaHashtags.add(miListaHashtagAdm.get(i).getHashtag());
			}
		}
		
		return miListaHashtags;
	}
	
	/** El método leerListaHashtagAdm hace la consulta a la tabla de la BBDD
	 * @ in String sql con la sentencia sql a ejecutar
	 * @ out ArrayList<HashtagAdm> con los objetos
	 * @ error   throws ServletException, si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	protected ArrayList<HashtagAdm> leerListaHashtagAdm(String sql)throws SQLException{
		ArrayList<HashtagAdm> lista=new ArrayList<HashtagAdm>();
		this.stm = conn.createStatement();
		ResultSet rs;
		rs=stm.executeQuery(sql);
		while (rs.next()){
			lista.add(new HashtagAdm(rs.getString("hashtag_id"), rs.getString("origen"), rs.getDate("fecha_entrada")));
		}
		rs.close();
		return lista;
	}

	@Override
	public void deleteHahstagAdm(String hashtagid) throws SQLException {
		Statement stm = null;	
		int rs = 0;
		//Solo si no existe el hashtag insertamos. 
		if ((this.exists(hashtagid))){
			stm = this.conn.createStatement();
			StringBuilder sql = new StringBuilder("DELETE FROM hashtags_adm where hashtag_id='"+hashtagid+"'");
			System.out.println(sql.toString());
			rs = stm.executeUpdate(sql.toString());
			stm.close();
			System.out.println("Hashtag "+hashtagid+" ya no está administrado.");
		}else {
		    System.out.println("Hashtag "+hashtagid+" no existe y no está administrado.");
		}
		
	}
}
