/**
 * 
 */
package es.uned.master.java.healthworldbank.infraestructura;

import es.uned.master.java.healthworldbank.comunicacion.*;
import es.uned.master.java.healthworldbank.datos.*;





import java.sql.*;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosHealthWorldBank.
 *
 * @author HYPERION74
 */
public class DatosHealthWorldBank {
	
	/** The tipo. */
	private TipoPeticion tipo;
	
	/** The preg. */
	private Pregunta preg;
	
	/** The stmt. */
	private Statement stmt = null;
	
	/** The conn. */
	private Connection conn = null;
	
	/** The rs. */
	private ResultSet rs = null;
	
	/** The consulta totales. */
	private Statement consultaTotales = null;
	
	/** The rs total. */
	private ResultSet rsTotal = null;
	
	/**
	 * Instantiates a new datos health world bank.
	 *
	 * @param Tipo the tipo
	 * @param prg the prg
	 */
	public DatosHealthWorldBank(TipoPeticion Tipo, Pregunta prg){
		this.tipo= Tipo;
		this.preg = prg;
	}	
	
	/**
	 * Gets the obtener datos.
	 *
	 * @return the obtener datos
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	public Respuesta getObtenerDatos() throws SQLException,Exception{
		ArrayList<Registro> Datos = new ArrayList<Registro>();
		int intTotales=0;
		Respuesta respu=null;
		switch (tipo) {
		case PAISES:
			ArrayList<Pais> Pais = new ArrayList<Pais>();
			intTotales = GetObtenerTotalesPaises();
			if(intTotales>0){
				Pais = (ArrayList<Pais>) Paises();
				Iterator<Pais> i = Pais.iterator();
				while(i.hasNext()) {
					Datos.add(i.next());
				  }
				respu = new Respuesta(TipoPeticion.PAISES, 0, 1, intTotales, Datos);
			}else
				respu = new Respuesta(TipoPeticion.PAISES, 0, 0, 0, null);
			break;
		case INDICADORES:
			ArrayList<Indicador> Indicadores = new ArrayList<Indicador>();
			intTotales = GetObtenerTotalesIndicadores();
			if(intTotales>0){
				Indicadores = (ArrayList<Indicador>) Indicadores();
				Iterator<Indicador> i = Indicadores.iterator();
				while(i.hasNext()) {
					Datos.add(i.next());
				  }
				respu = new Respuesta(TipoPeticion.INDICADORES, 0, 1, intTotales, Datos);
			}else
				respu = new Respuesta(TipoPeticion.INDICADORES, 0, 0, 0, null);
			break;
		case ESTADISTICAS:
			ArrayList<Estadistica> Estadisticas = new ArrayList<Estadistica>();
			intTotales = GetObtenerTotalesEstadisticas();
			if(intTotales>0){
				Estadisticas = (ArrayList<Estadistica>) Estadisticas();
				Iterator<Estadistica> i = Estadisticas.iterator();
				while(i.hasNext()) {
					Datos.add(i.next());
				  }
				respu = new Respuesta(TipoPeticion.ESTADISTICAS, 0, 1, intTotales, Datos);
			}else
				respu = new Respuesta(TipoPeticion.ESTADISTICAS, 0, 0, 0, null);
			break;
		}
		
		return respu;
	}
		
	
	
	/**
	 * Paises.
	 *
	 * @return the list Paises
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private List<Pais> Paises() throws SQLException, Exception{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:Datos.db");
			ArrayList<Pais> registro = new ArrayList<Pais>();
			String sql = "SELECT * FROM  COUNTRY " + 
						"LIMIT " + preg.getPrimerRegistro() + ", " + preg.getLimite() + ";";
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()){
				Pais reg = new Pais();
				reg.setCodigo(rs.getString("COUNTRY_CODE"));
				reg.setNombre(rs.getString("COUNTRY_NAME"));
				registro.add(reg);
			}
			return registro;
		
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
	
	}
	
	
	/**
	 * Indicadores.
	 *
	 * @return the list Indicadores
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private List<Indicador> Indicadores() throws SQLException, Exception{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:Datos.db");
			ArrayList<Indicador> registro = new ArrayList<Indicador>();
			String sql = "SELECT INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION FROM HEALTH_INDICATOR ORDER BY INDICATOR_NAME " +
						"LIMIT " + preg.getPrimerRegistro() + ", " + preg.getLimite() + ";";
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()){
				Indicador reg = new Indicador();
				reg.setCodigo(rs.getString("INDICATOR_CODE"));
				reg.setNombre(rs.getString("INDICATOR_NAME"));
				reg.setNota(rs.getString("SOURCE_NOTE"));
				reg.setOrganizacion(rs.getString("SOURCE_ORGANIZATION"));
				registro.add(reg);
			}
			return registro;
		
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
	
	}
	
	/**
	 * Estadisticas.
	 *
	 * @return the list Estadisticas
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private List<Estadistica> Estadisticas() throws SQLException, Exception{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:Datos.db");
			ArrayList<Estadistica> registro = new ArrayList<Estadistica>();
			String sql = "SELECT INDICATOR_CODE, COUNTRY_CODE, YEAR, PERCENTAGE FROM DATA ";
			if( preg.getCodigoIndicador()!=null && preg.getCodigoPais()!=null)			
				sql +="WHERE  INDICATOR_CODE = '" + preg.getCodigoIndicador() + 
				   "' AND COUNTRY_CODE = '" + preg.getCodigoPais()+"'";
			else if (preg.getCodigoPais()!=null && preg.getCodigoIndicador()==null)
				sql += " WHERE COUNTRY_CODE = '" + preg.getCodigoPais()+"'";
			else if (preg.getCodigoPais()==null && preg.getCodigoIndicador()!=null)
				sql +="WHERE  INDICATOR_CODE = '" + preg.getCodigoIndicador()+"'";
			sql += " ORDER BY YEAR " +
				"LIMIT " + preg.getPrimerRegistro() + ", " + preg.getLimite() + ";";
			stmt = conn.createStatement();
			System.out.println("DEVUELVO ESTADISTICAS :"+sql);
			rs= stmt.executeQuery(sql);
			System.out.println("RESULTADO ESTADISTICAS :"+rs.toString());
			while(rs.next()){
				Estadistica reg = new Estadistica();
				reg.setCodigoIndicador(rs.getString("INDICATOR_CODE"));
				reg.setCodigoPais(rs.getString("COUNTRY_CODE"));
				reg.setAno(rs.getInt("YEAR"));
				reg.setDato(rs.getInt("PERCENTAGE"));
				registro.add(reg);
			}
			return registro;
		
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}finally{
			rs.close();
			stmt.close();
			conn.close();
		}
	
	}
	
	/**
	 * Gets the obtener totales paises.
	 *
	 * @return the int
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private int GetObtenerTotalesPaises() throws SQLException, Exception{
	try
	{
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:Datos.db");
		String Query = "SELECT COUNT(*) as totales FROM COUNTRY;";
		consultaTotales = conn.createStatement();
		rsTotal = consultaTotales.executeQuery(Query);
		rsTotal.next();
		return  rsTotal.getInt("totales");	
		}catch(SQLException ex){
			throw new SQLException(ex.getMessage());
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}finally{
			rsTotal.close();
			consultaTotales.close();
			conn.close();
		}
	
	}
	
	/**
	 * Gets the obtener totales indicadores.
	 *
	 * @return the int
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private int GetObtenerTotalesIndicadores() throws SQLException, Exception{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:Datos.db");
			String Query = "SELECT COUNT(*) as totales FROM HEALTH_INDICATOR;";
			consultaTotales = conn.createStatement();
			rsTotal = consultaTotales.executeQuery(Query);
			rsTotal.next();
			return  rsTotal.getInt("totales");	
			}catch(SQLException ex){
				throw new SQLException(ex.getMessage());
			}catch(Exception ex){
				throw new Exception(ex.getMessage());
			}finally{
				rsTotal.close();
				consultaTotales.close();
				conn.close();
			}
		
		}
	
	/**
	 * Gets the obtener totales estadisticas.
	 *
	 * @return the int
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private int GetObtenerTotalesEstadisticas() throws SQLException, Exception{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:Datos.db");
			String sql = "SELECT COUNT(*) as totales FROM DATA ";
			if( preg.getCodigoIndicador()!=null && preg.getCodigoPais()!=null)			
				sql +="WHERE  INDICATOR_CODE = '" + preg.getCodigoIndicador() + 
				   "' AND COUNTRY_CODE = '" + preg.getCodigoPais()+"'";
			else if (preg.getCodigoPais()!=null && preg.getCodigoIndicador()==null)
				sql += " WHERE COUNTRY_CODE = '" + preg.getCodigoPais()+"'";
			else if (preg.getCodigoPais()==null && preg.getCodigoIndicador()!=null)
				sql +="WHERE  INDICATOR_CODE = '" + preg.getCodigoIndicador()+"'";
			sql += " ORDER BY YEAR;";
			System.out.println("REGISTROS TOTALES: "+sql);
			consultaTotales = conn.createStatement();
			rsTotal = consultaTotales.executeQuery(sql);
			rsTotal.next();		
			return  rsTotal.getInt("totales");	
			}catch(SQLException ex){
				throw new SQLException(ex.getMessage());
			}catch(Exception ex){
				throw new Exception(ex.getMessage());
			}finally{
				rsTotal.close();
				consultaTotales.close();
				conn.close();
			}
		
		}
}
	
	
	
	
