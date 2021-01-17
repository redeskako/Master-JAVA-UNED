package es.csc.biblioteca.loans.dao;

import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.jdbc.ConnectionsManager;
import java.sql.Connection;
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
public class LoansDAOImpl implements ILoansDAO {

    protected Connection iConnection;

    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE idprestamo = ?";

    
    public LoansDAOImpl() {
    }
    
    public LoansDAOImpl(Connection connection) {
        this.iConnection = connection;
    }
    
    /**
     * Inserta el prestamo indicado en la base de datos.
     * 
     * @param dto Datos del prestamo.
     * @return Devuelve la clave primaria asociada al prestamo.
     * @throws LoansDAOException 
     */
    @Override
    public int insert(LoanDTO dto) throws LoansDAOException {
        
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
                dto.setIdPrestamo(rs.getInt(1));
            }
            reset(dto);
        } catch (SQLException ex) {
            throw new LoansDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
        return dto.getIdPrestamo();
        
    }

    /**
     * Actualiza el prestamo indicado por su clave primaria en la base de datos
     * con los datos especificados.
     * 
     * @param idPrestamo Clave primaria del prestamo a actualizar.
     * @param dto Datos del prestamo.
     * @throws LoansDAOException 
     */
    @Override
    public void update(int idPrestamo, LoanDTO dto) throws LoansDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia UPDATE
            stmt = getUpdateStatement(connection, idPrestamo, dto);
            // Ejecutamos la sentencia SQL
            int rows = stmt.executeUpdate();
            reset(dto);
        } catch (SQLException ex) {
            throw new LoansDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
    }

    /**
     * Elimina de la base de datos el prestamo indicado por su clave primaria.
     * 
     * @param idPrestamo Clave primaria del prestamo.
     * @throws LoansDAOException 
     */
    @Override
    public void delete(int idPrestamo) throws LoansDAOException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        final boolean isConnSupplied = (iConnection != null);

        try {
            // Obtenemos una conexión a la base de datos
            conn = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia DELETE
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPrestamo);
            // Ejecutamos la sentencia SQL
            int rows = stmt.executeUpdate();
        } catch (Exception ex) {
            throw new LoansDAOException(ex.toString());
        }
        finally {
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(conn);
            }
        }
        
    }

    /**
     * Recupera de la base de datos el prestamo indicado por su clave primaria.
     * 
     * @param idPrestamo Clave primaria del prestamo.
     * @return Devuelve los datos del prestamo encontrado.
     * @throws LoansDAOException 
     */
    @Override
    public LoanDTO findByPrimaryKey(int idPrestamo) throws LoansDAOException {
	List<LoanDTO> ret = findByDynamicSelect(getSQLSelect() + " WHERE idprestamo = ?", new Object[] {idPrestamo});
        return ret.isEmpty() ? null : ret.get(0);
    }

    /**
     * Recupera de la base de datos todos los prestamos.
     * 
     * @return Devuelve una lista con todos los prestamos de la base de datos.
     * @throws LoansDAOException 
     */
    @Override
    public List<LoanDTO> findAll() throws LoansDAOException {
        return findByDynamicSelect(getSQLSelect() + " ORDER BY idprestamo", null);
    }

    /**
     * Devuelve todas las filas de la tabla Prestamos que concuerdan con la consulta SQL especificada.
     * 
     * @param sql Consulta SELECT
     * @param sqlParams Parámetros Bind
     * @return Devuelve una lista con los prestamos encontrados en la base de datos.
     * @throws LoansDAOException 
     */
    private List<LoanDTO> findByDynamicSelect(String sql, Object[] sqlParams) throws LoansDAOException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            conn = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // prepare statement
            stmt = conn.prepareStatement(sql);
            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                stmt.setObject( i+1, sqlParams[i] );
            }
            rs = stmt.executeQuery();
            // fetch the results
            return fetchMultiResults(rs);
        }
        catch (Exception ex) {
            throw new LoansDAOException(ex.getMessage());
        }
        finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(conn);
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
    private LoanDTO fetchSingleResult(ResultSet rs) throws SQLException {
        LoanDTO dto = null;
        if (rs.next()) {
            dto = getPrestamoFromResultSet(rs);
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
    private List<LoanDTO> fetchMultiResults(ResultSet rs) throws SQLException {
        List<LoanDTO> al = new ArrayList<>();
        while (rs.next()) {
            LoanDTO dto = getPrestamoFromResultSet(rs);
            al.add(dto);
        }
        return al;
    }
    
    /**
     * Devuelve un prestamo a partir de los datos del ResultSet indicado.
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private LoanDTO getPrestamoFromResultSet(ResultSet rs) throws SQLException {
        
        BookDTO book = new BookDTO();
        
        book.setIdLibro(rs.getInt(2));
        book.setNombre(rs.getString(3));
        
        CustomerDTO customer = new CustomerDTO();
        customer.setIdSocio(rs.getInt(4));
        customer.setNombre(rs.getString(5));
        customer.setApellidos(rs.getString(6));
        
        LoanDTO dto = new LoanDTO();
        dto.setIdPrestamo(rs.getInt(1));
        dto.setBook(book);
        dto.setCustomer(customer);
        dto.setFechaInicio(rs.getDate(7));
        dto.setFechaFin(rs.getDate(8));
        reset(dto);
        
        return dto;
        
    }
    
    /**
     * Resetea los atributos que indican si los campos del DTO han sido modificados.
     * 
     * @param dto 
     */
    private void reset(LoanDTO dto) {
        dto.setIdPrestamoModified(false);
        dto.setIdLibroModified(false);
        dto.setIdSocioModified(false);
        dto.setFechaInicioModified(false);
        dto.setFechaFinModified(false);
    }
    
    /**
     * Obtiene el nombre de la tabla del DAO.
     * 
     * @return 
     */
    private String getTableName() {
        return "prestamos";
    }
    
    /**
     * Devuelve un objeto PreparedStatement preparado para insertar un nuevo
     * prestamo en la base de datos.
     * 
     * @param conn Conexión con la base de datos.
     * @param dto Datos del prestamo a insertar en la base de datos.
     * @return
     * @throws IllegalStateException
     * @throws SQLException 
     */
    private PreparedStatement getInsertStatement(Connection conn, LoanDTO dto) throws IllegalStateException, SQLException {
        int modifiedCount = 0;
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        PreparedStatement stmt = null;
        // Construimos la cadena con la sentencia INSERT
        sql.append( "INSERT INTO " + getTableName() + " (" );
        if (dto.isIdPrestamoModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idprestamo");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isIdLibroModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idlibro");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isIdSocioModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idsocio");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isFechaInicioModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("fechainicio");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isFechaFinModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("fechafin");
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
        if (dto.isIdPrestamoModified()) {
            stmt.setInt(index++, dto.getIdPrestamo());
        }
        if (dto.isIdLibroModified()) {
            stmt.setInt(index++, dto.getBook().getIdLibro());
        }
        if (dto.isIdSocioModified()) {
            stmt.setInt(index++, dto.getCustomer().getIdSocio());
        }
        if (dto.isFechaInicioModified()) {
            stmt.setDate(index++, dto.getFechaInicio());
        }
        if (dto.isFechaFinModified()) {
            stmt.setDate(index++, dto.getFechaFin());
        }
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }
    
    /**
     * Devuelve un objeto PreparedStatement preparado para actualizar los datos
     * de un prestamo en la base de datos.
     * 
     * @param conn Conexión con la base de datos.
     * @param idPrestamo Clave primaria del prestamo que se va a actualizar.
     * @param dto Datos del prestamo a actualizar en la base de datos.
     * @return
     * @throws IllegalStateException
     * @throws SQLException 
     */
    private PreparedStatement getUpdateStatement(Connection conn, int idPrestamo, LoanDTO dto) throws IllegalStateException, SQLException {
        boolean modified = false;
        StringBuilder sql = new StringBuilder();
        PreparedStatement stmt = null;
        // Construimos la cadena con la sentencia UPDATE
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET ");
        if (dto.isIdPrestamoModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("idprestamo = ?");
            modified = true;
        }
        if (dto.isIdLibroModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("idlibro = ?");
            modified = true;
        }
        if (dto.isIdSocioModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("idsocio = ?");
            modified = true;
        }
        if (dto.isFechaInicioModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("fechainicio = ?");
            modified = true;
        }
        if (dto.isFechaFinModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("fechafin = ?");
            modified = true;
        }
        sql.append(" WHERE idprestamo = ?");
        if (!modified) {
            // Nada que actualizar
            throw new IllegalStateException("Nada que actualizar");
        }
        // Construimos el objeto PreparedStatement
        int index = 1;
        stmt = conn.prepareStatement(sql.toString());
        if (dto.isIdPrestamoModified()) {
            stmt.setInt(index++, dto.getIdPrestamo());
        }
        if (dto.isIdLibroModified()) {
            stmt.setInt(index++, dto.getBook().getIdLibro());
        }
        if (dto.isIdSocioModified()) {
            stmt.setInt(index++, dto.getCustomer().getIdSocio());
        }
        if (dto.isFechaInicioModified()) {
            stmt.setDate(index++, dto.getFechaInicio());
        }
        if (dto.isFechaFinModified()) {
            stmt.setDate(index++, dto.getFechaFin());
        }
        stmt.setInt(index++, idPrestamo);
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }
    
    
    private String getSQLSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" prestamos.idprestamo, ");
        sb.append(" prestamos.idlibro, ");
        sb.append(" libros.nombre, ");
        sb.append(" prestamos.idsocio, ");
        sb.append(" socios.nombre, ");
        sb.append(" socios.apellidos, ");
        sb.append(" prestamos.fechainicio, ");
        sb.append(" prestamos.fechafin ");
        sb.append("FROM");
        sb.append(" prestamos");
        sb.append(" JOIN socios ON prestamos.idsocio = socios.idsocio ");
        sb.append(" JOIN libros ON prestamos.idlibro = libros.idlibro ");
        return sb.toString();
    }
    
}
