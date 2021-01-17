package es.csc.biblioteca.books.dao;

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
public class BooksDAOImpl implements IBooksDAO {
    
    protected Connection iConnection;
    
    protected final String SQL_SELECT = "SELECT idlibro, nombre, autor, tema FROM " + getTableName();
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE idlibro = ?";
    
    public BooksDAOImpl() {
    }

    public BooksDAOImpl(Connection connection) {
        this.iConnection = connection;
    }

    /**
     * Inserta el libro indicado en la base de datos.
     * 
     * @param dto Datos del libro.
     * @return Devuelve la clave primaria asociada al libro.
     * @throws BooksDAOException 
     */
    @Override
    public int insert(BookDTO dto) throws BooksDAOException {
        
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
                dto.setIdLibro(rs.getInt(1));
            }
            reset(dto);
        } catch (SQLException ex) {
            throw new BooksDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
        return dto.getIdLibro();
        
    }

    /**
     * Actualiza el libro indicado por su clave primaria en la base de datos
     * con los datos especificados.
     * 
     * @param idLibro Clave primaria del libro a actualizar.
     * @param dto Datos del libro.
     * @throws BooksDAOException 
     */
    @Override
    public void update(int idLibro, BookDTO dto) throws BooksDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia UPDATE
            stmt = getUpdateStatement(connection, idLibro, dto);
            // Ejecutamos la sentencia SQL
            int rows = stmt.executeUpdate();
            reset(dto);
        } catch (SQLException ex) {
            throw new BooksDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
    }

    /**
     * Elimina de la base de datos el libro indicado por su clave primaria.
     * 
     * @param idLibro Clave primaria del libro a eliminar.
     * @throws BooksDAOException 
     */
    @Override
    public void delete(int idLibro) throws BooksDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        final boolean isConnSupplied = (iConnection != null);

        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Preparamos la sentencia DELETE
            stmt = connection.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idLibro);
            // Ejecutamos la sentencia SQL
            int rows = stmt.executeUpdate();
        } catch (Exception ex) {
            throw new BooksDAOException(ex.toString());
        }
        finally {
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
    }

    /**
     * Recupera de la base de datos el libro indicado por su clave primaria.
     * 
     * @param idLibro Clave primaria del libro.
     * @return Devuelve los datos del libro encontrado.
     * @throws BooksDAOException 
     */
    @Override
    public BookDTO findByPrimaryKey(int idLibro) throws BooksDAOException {
        List<BookDTO> ret = findByDynamicSelect(SQL_SELECT + " WHERE idlibro = ?", new Object[] {idLibro});
        return ret.isEmpty() ? null : ret.get(0);
    }

    /**
     * Recupera de la base de datos todos los libros.
     * 
     * @return Devuelve una lista todos los libros de la base de datos.
     * @throws BooksDAOException 
     */
    @Override
    public List<BookDTO> findAll() throws BooksDAOException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY idlibro", null);
    }

    /**
     * Devuelve todas las filas de la tabla Libros que concuerdan con la consulta SQL especificada.
     * 
     * @param sql Consulta SELECT
     * @param sqlParams Parámetros Bind
     * @return Devuelve una lista con los libros encontrados en la base de datos.
     * @throws BooksDAOException 
     */
    private List<BookDTO> findByDynamicSelect(String sql, Object[] sqlParams) throws BooksDAOException {
        
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
            throw new BooksDAOException(ex.toString());
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
    private BookDTO fetchSingleResult(ResultSet rs) throws SQLException {
        BookDTO dto = null;
        if (rs.next()) {
            dto = getLibroFromResultSet(rs);
        }
        return dto;
    }
    
    /**
     * Obtiene todas las filas del ResultSet.
     * @param rs
     * @return
     * @throws SQLException 
     */
    private List<BookDTO> fetchMultiResults(ResultSet rs) throws SQLException {
        List<BookDTO> al = new ArrayList<>();
        while (rs.next()) {
            BookDTO dto = getLibroFromResultSet(rs);
            al.add(dto);
        }
        return al;
    }
    
    /**
     * Devuelve un libro a partir de los datos del ResultSet indicado.
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    private BookDTO getLibroFromResultSet(ResultSet rs) throws SQLException {
        BookDTO dto = new BookDTO();
        dto.setIdLibro(rs.getInt(1));
        dto.setNombre(rs.getString(2));
        dto.setAutor(rs.getString(3));
        dto.setTema(rs.getString(4));
        reset(dto);
        return dto;
    }
    
    /**
     * Resetea los atributos que indican si los campos del DTO han sido modificados.
     * 
     * @param dto 
     */
    private void reset(BookDTO dto) {
        dto.setIdLibroModified(false);
        dto.setNombreModified(false);
        dto.setAutorModified(false);
        dto.setTemaModified(false);
    }
    
    /**
     * Obtiene el nombre de la tabla del DAO.
     * 
     * @return 
     */
    private String getTableName() {
        return "libros";
    }
    
    /**
     * Devuelve un objeto PreparedStatement preparado para insertar un nuevo
     * libro en la base de datos.
     * 
     * @param conn Conexión con la base de datos.
     * @param dto Datos del libro a insertar en la base de datos.
     * @return
     * @throws IllegalStateException
     * @throws SQLException 
     */
    private PreparedStatement getInsertStatement(Connection conn, BookDTO dto) throws IllegalStateException, SQLException {
        int modifiedCount = 0;
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        PreparedStatement stmt = null;
        // Construimos la cadena con la sentencia INSERT
        sql.append( "INSERT INTO " + getTableName() + " (" );
        if (dto.isIdLibroModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idlibro");
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
        if (dto.isAutorModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("autor");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isTemaModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("tema");
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
        if (dto.isIdLibroModified()) {
            stmt.setInt(index++, dto.getIdLibro());
        }
        if (dto.isNombreModified()) {
            stmt.setString(index++, dto.getNombre());
        }
        if (dto.isAutorModified()) {
            stmt.setString(index++, dto.getAutor());
        }
        if (dto.isTemaModified()) {
            stmt.setString(index++, dto.getTema());
        }
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }
    
    /**
     * Devuelve un objeto PreparedStatement preparado para actualizar los datos
     * de un libro en la base de datos.
     * 
     * @param conn Conexión con la base de datos.
     * @param idLibro Clave primaria del libro que se va a actualizar.
     * @param dto Datos del libro a actualizar en la base de datos.
     * @return
     * @throws IllegalStateException
     * @throws SQLException 
     */
    private PreparedStatement getUpdateStatement(Connection conn, int idLibro, BookDTO dto) throws IllegalStateException, SQLException {
        boolean modified = false;
        StringBuilder sql = new StringBuilder();
        PreparedStatement stmt = null;
        // Construimos la cadena con la sentencia UPDATE
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET ");
        if (dto.isIdLibroModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("idlibro = ?");
            modified = true;
        }
        if (dto.isNombreModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("nombre = ?");
            modified = true;
        }
        if (dto.isAutorModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("autor = ?");
            modified = true;
        }
        if (dto.isTemaModified()) {
            if (modified) {
                sql.append(", ");
            }
            sql.append("tema = ?");
            modified = true;
        }
        sql.append(" WHERE idlibro = ?");
        if (!modified) {
            // Nada que actualizar
            throw new IllegalStateException("Nada que actualizar");
        }
        // Construimos el objeto PreparedStatement
        int index = 1;
        stmt = conn.prepareStatement(sql.toString());
        if (dto.isIdLibroModified()) {
            stmt.setInt(index++, dto.getIdLibro());
        }
        if (dto.isNombreModified()) {
            stmt.setString(index++, dto.getNombre());
        }
        if (dto.isAutorModified()) {
            stmt.setString(index++, dto.getAutor());
        }
        if (dto.isTemaModified()) {
            stmt.setString(index++, dto.getTema());
        }
        stmt.setInt(index++, idLibro);
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }
    
}
