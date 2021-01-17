/**
 * 
 */
package es.uned.master.java.healthworldbank.infraestructura;

import es.uned.master.java.healthworldbank.comunicacion.*;
import es.uned.master.java.healthworldbank.datos.*;
import es.uned.master.java.healthworldbank.servidor.ServerRunningException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosHealthWorldBank.
 *
 * @author HYPERION74
 * @author jbelo
 * @version 1.0
 */
// TODO: Revisar clase, hay código duplicado en muchos métodos
public class DatosHealthWorldBank {
	
	private static final String urlDatabse = new String("jdbc:sqlite:src/main/resources/databases/Datos.db");

	public static final int RECORDS_PER_PAGE = 20;

	static enum TABLES_NAME {COUNTRY, DATA, HEALTH_INDICATOR}
	
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
	 * @param tipo the tipo
	 * @param prg the prg
	 */
	public DatosHealthWorldBank(TipoPeticion tipo, Pregunta prg){
		this.tipo= tipo;
		this.preg = prg;
	}	
	
	/**
	 * Gets the obtener datos.
	 *
	 * @return the obtener datos
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	public Respuesta getObtenerDatos() throws ServerRunningException{
		ArrayList<Registro> datos = new ArrayList<Registro>();
		int totalRecords=0;
		int offset = 0;

        ActionType requestType = null;
        int newOffset = 0;
        String page;

		Respuesta respu=null;
		switch (tipo) {
            case PAISES:
                PreguntaSingleTable preguntaCountry = (PreguntaSingleTable)preg;
                offset = preguntaCountry.getOffset();
                requestType = preguntaCountry.getActionType();

                totalRecords = calculateTotalRecordsSingleTable(TABLES_NAME.COUNTRY);
                newOffset = calculateNewOffset(requestType,offset,totalRecords);
                page = calculatePage(newOffset,totalRecords);

                ArrayList<Pais> countryRecords = new ArrayList<Pais>();

                countryRecords = (ArrayList<Pais>) Paises(RECORDS_PER_PAGE,newOffset);
                for(Pais p: countryRecords){
                    datos.add(p);
                }
                
                respu = new Respuesta(TipoPeticion.PAISES, page, newOffset, totalRecords, datos);

                break;
            case INDICADORES:
                PreguntaSingleTable preguntaIndicators = (PreguntaSingleTable)preg;
                offset = preguntaIndicators.getOffset();
                requestType = preguntaIndicators.getActionType();

                totalRecords = calculateTotalRecordsSingleTable(TABLES_NAME.HEALTH_INDICATOR);
                newOffset = calculateNewOffset(requestType,offset,totalRecords);
                page = calculatePage(newOffset,totalRecords);

                ArrayList<Indicador> indicatorRecords = new ArrayList<Indicador>();

                indicatorRecords = (ArrayList<Indicador>) Indicadores(RECORDS_PER_PAGE,newOffset);
                for(Indicador i: indicatorRecords){
                    datos.add(i);
                }
                respu = new Respuesta(TipoPeticion.INDICADORES, page, newOffset, totalRecords, datos);
                break;
            case ESTADISTICAS:
                PreguntaStatistics preguntaStatistics = (PreguntaStatistics) preg;
                offset = preguntaStatistics.getOffset();
                requestType = preguntaStatistics.getActionType();

                totalRecords = GetObtenerTotalesEstadisticas();
                newOffset = calculateNewOffset(requestType,offset,totalRecords);
                page = calculatePage(newOffset,totalRecords);

                ArrayList<Estadistica> statisticsRecords = (ArrayList<Estadistica>) Estadisticas(RECORDS_PER_PAGE,newOffset,
                            preguntaStatistics.getCountryCode(), preguntaStatistics.getIndicatorCode());
                for(Estadistica e: statisticsRecords){
                    datos.add(e);
                }
                respu = new Respuesta(TipoPeticion.ESTADISTICAS, page, newOffset, totalRecords, datos);
                break;
            case YEARS:
                ArrayList<Year> years = (ArrayList<Year>) Years();
                for(Year y: years){
                    datos.add(y);
                }

                respu = new Respuesta(TipoPeticion.YEARS, null, offset, totalRecords, datos);
                break;
            case MAP_VALUES:
                PreguntaMap preguntaMap = (PreguntaMap)preg;

                ArrayList<EstadisticaMap> estadisticasMap = (ArrayList<EstadisticaMap>) estadisticasMap(preguntaMap.getIndicatorCode(),
                        preguntaMap.getYearCode());

                for(EstadisticaMap em: estadisticasMap){
                    datos.add(em);
                }
                respu = new Respuesta(TipoPeticion.MAP_VALUES, null, offset, totalRecords, datos);
                break;
            }
		
		return respu;
	}


    /**
     * Calcula el nuevo offset que se usa para pedir los datos
     * @param requestType Acción solicitada, adelnate, atrás o neutral
     * @param currentOffset Offset original que se tiene en el cliente
     * @param totalRecords cantidad total de registro presentes en la tabla
     * @return nuevo offset
     */
    private int calculateNewOffset(ActionType requestType, int currentOffset, int totalRecords){

	    int calculatedOffset = 0;

	    if(requestType == ActionType.NEUTRAL){

	        calculatedOffset = currentOffset;

        }else if(requestType == ActionType.FORWARD){

	        if(totalRecords > currentOffset + RECORDS_PER_PAGE){
	            calculatedOffset = currentOffset + RECORDS_PER_PAGE;
            } else if (totalRecords <= currentOffset + RECORDS_PER_PAGE && totalRecords > currentOffset){
	            calculatedOffset = currentOffset;
            } else if (totalRecords < currentOffset) {
                int latestRecords = totalRecords%RECORDS_PER_PAGE;
                calculatedOffset = totalRecords - latestRecords;
            }

        }else if(requestType == ActionType.BACKWARD){

            if(currentOffset - RECORDS_PER_PAGE <= 0){
                calculatedOffset = 0;
            } else {
                calculatedOffset = currentOffset - RECORDS_PER_PAGE;
            }
        }
        return calculatedOffset;
    }

    /**
     * clacula la página en la que se encuentran los datos que se están devolviendo
     * @param offset offset calculado para los neuvos datos
     * @param totalRecords cantidad total de registros
     * @return devuelve la página en la que se encuentran
     */
    private String calculatePage(int offset, int totalRecords){
	    String totalPages;
	    String currrentPage;
	    int current;

	    int total = totalRecords%RECORDS_PER_PAGE > 0?totalRecords/RECORDS_PER_PAGE + 1:totalRecords/RECORDS_PER_PAGE;
        totalPages = String.valueOf(total);

        if(totalRecords > 0) {
            current = offset/RECORDS_PER_PAGE + 1;
        } else {
            current = 0;
        }

        currrentPage = String.valueOf(current);

        return currrentPage + "-" + totalPages;

    }

    /**
     * Devuelve los registros totales para una única tbala
     * @param tables_name nombre de la tabla
     * @return
     * @throws ServerRunningException
     */
    private int calculateTotalRecordsSingleTable(TABLES_NAME tables_name) throws ServerRunningException{
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(urlDatabse);
            String Query = "SELECT COUNT(*) as totales FROM " + tables_name.toString() + " ;";
            consultaTotales = conn.createStatement();
            rsTotal = consultaTotales.executeQuery(Query);
            rsTotal.next();
            return  rsTotal.getInt("totales");
        }catch(SQLException ex) {
            throw new ServerRunningException(ex.getLocalizedMessage());
        }catch (ClassNotFoundException e){
            throw new ServerRunningException(e.getLocalizedMessage());

       }finally{
            try {
                rsTotal.close();
                consultaTotales.close();
                conn.close();
            }catch (SQLException ec){
                // do nothing
            }
        }
    }

	/**
	 * Paises.
	 *
	 * @return the list Paises
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private List<Pais> Paises(int limit, int offset) throws ServerRunningException{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection(urlDatabse);
			ArrayList<Pais> registro = new ArrayList<Pais>();
			String sql = "SELECT * FROM  COUNTRY " + 
						"LIMIT " + limit + " OFFSET " + offset + ";";
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()){
				Pais reg = new Pais();
				reg.setCodigo(rs.getString("COUNTRY_CODE"));
				reg.setNombre(rs.getString("COUNTRY_NAME"));
				registro.add(reg);
			}
			return registro;
		
		}catch(SQLException ex) {
            throw new ServerRunningException(ex.getLocalizedMessage());
        }catch (ClassNotFoundException ex){
            throw new ServerRunningException(ex.getLocalizedMessage());
		}finally{
            try {
                rs.close();
                stmt.close();
                conn.close();
            }catch (SQLException ex){
                // do nothing
            }
		}
	
	}

    /**
     * Devuelve los años
     * @return
     * @throws ServerRunningException
     */
    private List<Year> Years() throws ServerRunningException {
        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(urlDatabse);
            ArrayList<Year> registro = new ArrayList<>();

            String sql = "SELECT DISTINCT YEAR FROM DATA ORDER BY YEAR ASC;";


            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next()){
                Year reg = new Year();
                reg.setYear(rs.getInt("YEAR"));
                //reg.setCodigo(rs.getString("COUNTRY_CODE"));
                //reg.setNombre(rs.getString("COUNTRY_NAME"));
                registro.add(reg);
            }
            return registro;

        }catch(SQLException ex){
            throw new ServerRunningException(ex.getMessage());
        }catch(Exception ex){
            throw new ServerRunningException(ex.getMessage());
        }finally{
            try {
                rs.close();
                stmt.close();
                conn.close();
            }catch (SQLException ex){
                // do nothing
            }
        }

    }
	
	
	/**
	 * Indicadores.
	 *
	 * @return the list Indicadores
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private List<Indicador> Indicadores(int limit, int offset) throws ServerRunningException {
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection(urlDatabse);
			ArrayList<Indicador> registro = new ArrayList<Indicador>();
			String sql = "SELECT INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION FROM HEALTH_INDICATOR ORDER BY INDICATOR_NAME " +
						"LIMIT " + limit + " OFFSET " + offset + ";";
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
			throw new ServerRunningException(ex.getMessage());
		}catch(Exception ex){
			throw new ServerRunningException(ex.getMessage());
		}finally{
		    try {
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException ex){
			    // do nothing
            }
		}
	
	}
	
	/**
	 * Estadisticas.
	 *
	 * @return the list Estadisticas
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private List<Estadistica> Estadisticas(int limit, int offset,String countryCode, String indicatorCode) throws ServerRunningException{
		try {
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection(urlDatabse);
			ArrayList<Estadistica> registro = new ArrayList<Estadistica>();
			String sql = "SELECT INDICATOR_CODE, COUNTRY_CODE, YEAR, PERCENTAGE FROM DATA ";
			if( indicatorCode !=null && countryCode!=null)
				sql +="WHERE  INDICATOR_CODE = '" + indicatorCode +
				   "' AND COUNTRY_CODE = '" + countryCode + "'";
			else if (countryCode != null && indicatorCode == null)
				sql += " WHERE COUNTRY_CODE = '" + countryCode + "'";
			else if (countryCode == null && indicatorCode!=null)
				sql +="WHERE  INDICATOR_CODE = '" + indicatorCode + "'";
			sql += " ORDER BY YEAR " +
				"LIMIT " + limit + " OFFSET " + offset + ";";
			stmt = conn.createStatement();

			rs= stmt.executeQuery(sql);
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
			throw new ServerRunningException(ex.getLocalizedMessage());
		}catch(Exception ex){
			throw new ServerRunningException(ex.getLocalizedMessage());
		}finally{
		    try {
                rs.close();
                stmt.close();
                conn.close();
            }catch (SQLException ex){
		        // do nothing
            }
		}
	
	}

	private List<EstadisticaMap> estadisticasMap(String indicatorCode, String yearCode) throws ServerRunningException{
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(urlDatabse);
            ArrayList<EstadisticaMap> registro = new ArrayList<>();

            String sql = "SELECT COUNTRY_CODE, PERCENTAGE FROM DATA WHERE ";
            sql = sql + "INDICATOR_CODE = " + "'" + indicatorCode + "'" + " AND ";
            sql = sql + "YEAR = " + "'" + yearCode + "'" + ";";

            stmt = conn.createStatement();

            rs= stmt.executeQuery(sql);
            int n = 0;
            while(rs.next()){
                EstadisticaMap reg = new EstadisticaMap();
                reg.setCodigoPais(rs.getString("COUNTRY_CODE"));
                reg.setDato(rs.getDouble("PERCENTAGE"));
                registro.add(reg);
                n++;
            }
            return registro;

        }catch(SQLException ex){
            throw new ServerRunningException(ex.getMessage());
        }catch(ClassNotFoundException ex){
            throw new ServerRunningException(ex.getMessage());
        }finally{
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
    
    
	/**
	 * Gets the obtener totales estadisticas.
	 *
	 * @return the int
	 * @throws SQLException the sQL exception
	 * @throws Exception the exception
	 */
	private int GetObtenerTotalesEstadisticas() throws ServerRunningException{
		try
		{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection(urlDatabse);
			String sql = "SELECT COUNT(*) as totales FROM DATA ";
			if( ((PreguntaStatistics)preg).getIndicatorCode()!=null && ((PreguntaStatistics)preg).getCountryCode()!=null)
				sql +="WHERE  INDICATOR_CODE = '" + ((PreguntaStatistics)preg).getIndicatorCode() +
				   "' AND COUNTRY_CODE = '" + ((PreguntaStatistics)preg).getCountryCode()+"'";
			else if (((PreguntaStatistics)preg).getCountryCode()!=null && ((PreguntaStatistics)preg).getIndicatorCode()==null)
				sql += " WHERE COUNTRY_CODE = '" + ((PreguntaStatistics)preg).getCountryCode()+"'";
			else if (((PreguntaStatistics)preg).getCountryCode()==null && ((PreguntaStatistics)preg).getIndicatorCode()!=null)
				sql +="WHERE  INDICATOR_CODE = '" + ((PreguntaStatistics)preg).getIndicatorCode()+"'";
			sql += " ORDER BY YEAR;";
			System.out.println("REGISTROS TOTALES: "+sql);
			consultaTotales = conn.createStatement();
			rsTotal = consultaTotales.executeQuery(sql);
			rsTotal.next();		
			return  rsTotal.getInt("totales");	
			}catch(SQLException ex){
				throw new ServerRunningException(ex.getMessage());
			}catch(ClassNotFoundException ex){
				throw new ServerRunningException(ex.getMessage());
			}finally{
            try {
                rsTotal.close();
                consultaTotales.close();
                conn.close();
            } catch (SQLException e) {
                // do nothing
            }
        }
		
		}
}
	
	
	
	
