package es.csc.biblioteca.customers.dao;

import es.csc.biblioteca.jdbc.ConnectionsManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author David Atencia
 * @version 0.1
 */
public class CustomersDAOImpl implements ICustomersDAO {
    
    protected Connection iConnection;
    
    protected final String SQL_SELECT = "SELECT idsocio, nombre, apellidos, dni, direccion, fechaalta FROM " + getTableName();
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE idsocio = ?";
    
    protected static final int COLUMN_IDSOCIO = 1;
    protected static final int COLUMN_NOMBRE = 2;
    protected static final int COLUMN_APELLIDOS = 3;
    protected static final int COLUMN_DNI = 4;
    protected static final int COLUMN_DIRECCION = 5;
    protected static final int COLUMN_FECHAALTA = 6;
    
    public CustomersDAOImpl() {
    }

    public CustomersDAOImpl(Connection connection) {
        this.iConnection = connection;
    }
    
    /**
     * Inserta el socio indicado en la base de datos.
     * 
     * @param dto Datos del socio.
     * @return Devuelve la clave primaria asociada al socio.
     * @throws CustomersDAOException 
     */
    @Override
    public int insert(CustomerDTO dto) throws CustomersDAOException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia INSERT
            stmt = getInsertStatement(connection, dto);
            // Ejecutamos la sentencia SQL
            stmt.executeUpdate();
            // Obtenemos el valor del campo auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdSocio(rs.getInt(1));
            }
            reset(dto);
        } catch (SQLException ex) {
            throw new CustomersDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
        return dto.getIdSocio();
        
    }

    /**
     * Actualiza el socio indicado por su clave primaria en la base de datos
     * con los datos especificados.
     * 
     * @param idSocio Identificador único del socio a actualizar.
     * @param dto Datos del socio.
     * @throws CustomersDAOException 
     */
    @Override
    public void update(int idSocio, CustomerDTO dto) throws CustomersDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia UPDATE
            stmt = getUpdateStatement(connection, idSocio, dto);
            // Ejecutamos la sentencia SQL
            int rows = stmt.executeUpdate();
            reset(dto);
        } catch (SQLException ex) {
            throw new CustomersDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
    }

    /**
     * Elimina de la base de datos el socio indicado por su clave primaria.
     * 
     * @param idSocio Identificador único del socio.
     * @throws CustomersDAOException 
     */
    @Override
    public void delete(int idSocio) throws CustomersDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        final boolean isConnSupplied = (iConnection != null);

        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia DELETE
            stmt = connection.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idSocio);
            // Ejecutamos la sentencia SQL
            int rows = stmt.executeUpdate();
        } catch (Exception ex) {
            throw new CustomersDAOException(ex.toString());
        }
        finally {
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
	
    }

    /**
     * Recupera de la base de datos el socio indicado por su clave primaria.
     * 
     * @param idSocio Clave primaria del socio.
     * @return Devuelve los datos del socio encontrado.
     * @throws CustomersDAOException 
     */
    @Override
    public CustomerDTO findByPrimaryKey(int idSocio) throws CustomersDAOException {
	List<CustomerDTO> ret = findByDynamicSelect(SQL_SELECT + " WHERE idsocio = ?", new Object[] {idSocio});
        return ret.isEmpty() ? null : ret.get(0);
    }

    /**
     * Recupera de la base de datos todos los socios.
     * 
     * @return Devuelve una lista con cada uno de los socios de la base de datos.
     * @throws CustomersDAOException 
     */
    @Override
    public List<CustomerDTO> findAll() throws CustomersDAOException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY idsocio", null);
    }
    
    /**
     * Devuelve todas las filas de la tabla Socios que concuerdan con la consulta SQL especificada.
     * 
     * @param sql Consulta SELECT
     * @param sqlParams Parámetros Bind
     * @return Devuelve una lista con cada uno de los socios encontrados en la base de datos.
     * @throws CustomersDAOException 
     */
    private List<CustomerDTO> findByDynamicSelect(String sql, Object[] sqlParams) throws CustomersDAOException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        final boolean isConnSupplied = (iConnection != null);

        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // prepare statement
            stmt = connection.prepareStatement(sql);
            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                stmt.setObject( i+1, sqlParams[i] );
            }
            rs = stmt.executeQuery();
            // fetch the results
            return fetchMultiResults(rs);
        }
        catch (Exception ex) {
            throw new CustomersDAOException(ex.toString());
        }
        finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }

    }
    
    /**
     * Obtiene una fila desde el ResultSet.
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private CustomerDTO fetchSingleResult(ResultSet rs) throws SQLException {
        CustomerDTO dto = null;
        if (rs.next()) {
            dto = getSocioFromResultSet(rs);
        }
        return dto;
    }
    
    /**
     * Obtiene todas las filas del ResultSet.
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private List<CustomerDTO> fetchMultiResults(ResultSet rs) throws SQLException {
        List<CustomerDTO> al = new ArrayList<CustomerDTO>();
        while (rs.next()) {
            CustomerDTO dto = getSocioFromResultSet(rs);
            al.add(dto);
        }
        return al;
    }
    
    /**
     * Devuelve un socio a partir de los datos del ResultSet indicado.
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private CustomerDTO getSocioFromResultSet(ResultSet rs) throws SQLException {
        CustomerDTO dto = new CustomerDTO();
        dto.setIdSocio(rs.getInt(1));
        dto.setNombre(rs.getString(2));
        dto.setApellidos(rs.getString(3));
        dto.setDni(rs.getString(4));
        dto.setDireccion(rs.getString(5));
        dto.setFechaAlta(rs.getDate(6));
        reset(dto);
        return dto;
    }
    
    /**
     * Resetea los atributos que indican si los campos del DTO han sido modificados.
     * 
     * @param dto 
     */
    protected void reset(CustomerDTO dto) {
        dto.setIdSocioModified(false);
        dto.setNombreModified(false);
        dto.setApellidosModified(false);
        dto.setDniModified(false);
        dto.setDireccionModified(false);
        dto.setFechaAltaModified(false);
    }
    
    /**
     * Obtiene el nombre de la tabla del DAO.
     * 
     * @return 
     */
    protected String getTableName() {
        return "socios";
    }
    
    /**
     * Devuelve un objeto PreparedStatement preparado para insertar un nuevo
     * socio en la base de datos.
     * 
     * @param conn Conexión con la base de datos.
     * @param dto Datos del socio a insertar en la base de datos.
     * @return
     * @throws IllegalStateException
     * @throws SQLException 
     */
    protected PreparedStatement getInsertStatement(Connection conn, CustomerDTO dto) throws IllegalStateException, SQLException {
        int modifiedCount = 0;
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        PreparedStatement stmt = null;

        
        // Construimos la cadena con la sentencia INSERT
        sql.append( "INSERT INTO " + getTableName() + " (" );
        if (dto.isIdSocioModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idsocio");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isNombreModified()) {
            if (modifiedCount > 0) {
                sql.append( ", " );
                values.append( ", " );
            }
            sql.append("nombre");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isApellidosModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("apellidos");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isDniModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("dni");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isDireccionModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("direccion");
            values.append("?");
            modifiedCount++;
        }

        if (dto.isFechaAltaModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("fechaalta");
            values.append("?");
            modifiedCount++;
        }
        if (modifiedCount == 0) {
            // Nada que insertar
            throw new IllegalStateException("Nada que insertar");
        }
        sql.append(") VALUES (");
        sql.append(values);
        sql.append(")");
        // Construimos el objeto PreparedStatement
        int index = 1;
        stmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        if (dto.isIdSocioModified()) {
            stmt.setInt(index++, dto.getIdSocio());
        }
        if (dto.isNombreModified()) {
            stmt.setString(index++, dto.getNombre());
        }
        if (dto.isApellidosModified()) {
            stmt.setString(index++, dto.getApellidos());
        }
        if (dto.isDniModified()) {
            stmt.setString(index++, dto.getDni());
        }
        if (dto.isDireccionModified()) {
            stmt.setString(index++, dto.getDireccion());
        }
        if (dto.isFechaAltaModified()) {
            stmt.setDate(index++, dto.getFechaAlta());
        }
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }
    
    /**
     * Devuelve un objeto PreparedStatement preparado para actualizar los datos
     * de un socio en la base de datos.
     * 
     * @param conn Conexión con la base de datos.
     * @param idSocio Clave primaria del socio que se va a actualizar.
     * @param dto Datos del socio a actualizar en la base de datos.
     * @return
     * @throws IllegalStateException
     * @throws SQLException 
     */
    private PreparedStatement getUpdateStatement(Connection conn, int idSocio, CustomerDTO dto) throws IllegalStateException, SQLException {
        boolean modified = false;
        StringBuilder sql = new StringBuilder();
        PreparedStatement stmt = null;
        // Construimos la cadena con la sentencia UPDATE
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET ");
        if (dto.isIdSocioModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("idsocio = ?");
            modified = true;
        }
        if (dto.isNombreModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("nombre = ?");
            modified = true;
        }
        if (dto.isApellidosModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("apellidos = ?");
            modified = true;
        }
        if (dto.isDniModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("dni = ?");
            modified = true;
        }
        if (dto.isDireccionModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("direccion = ?");
            modified = true;
        }
        if (dto.isFechaAltaModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("fechaalta = ?");
            modified = true;
        }
        sql.append(" WHERE idsocio = ?");
        if (!modified) {
            // Nada que actualizar
            throw new IllegalStateException("Nada que actualizar");
        }
        // Construimos el objeto PreparedStatement
        int index = 1;
        stmt = conn.prepareStatement(sql.toString());
        if (dto.isIdSocioModified()) {
            stmt.setInt(index++, dto.getIdSocio());
        }
        if (dto.isNombreModified()) {
            stmt.setString(index++, dto.getNombre());
        }
        if (dto.isApellidosModified()) {
            stmt.setString(index++, dto.getApellidos());
        }
        if (dto.isDniModified()) {
            stmt.setString(index++, dto.getDni());
        }
        if (dto.isDireccionModified()) {
            stmt.setString(index++, dto.getDireccion());
        }
        if (dto.isFechaAltaModified()) {
            stmt.setDate(index++, (Date) dto.getFechaAlta());
        }
        stmt.setInt(index++, idSocio);
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }

}
