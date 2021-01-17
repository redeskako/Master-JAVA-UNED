/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 19/11/2015
 */

package com.csc.biblioteca.libro;

import com.csc.biblioteca.excepciones.ErrorCode;
import com.csc.biblioteca.excepciones.ErrorException;
import com.csc.biblioteca.excepciones.InfoException;
import com.csc.biblioteca.conexion.MySQLConnection;
import com.csc.biblioteca.util.DAOBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** Clase de acceso a datos de libros. */
public class LibroDAO extends DAOBase {

    /**
     * Constructor de la clase de acceso a datos de libros.
     * @param conexion Objeto conextado a la base de datos.
     */
    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Obtener los datos del libro indicado por su identificador.
     * @param idLibro Identificador del libro.
     * @return Libro instanciado con los datos de la base de datos.
     * @throws ErrorException Error al cargar los datos del libro.
     */
    public Libro obtenerLibro(Integer idLibro) throws ErrorException {
        Libro libro = null;
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        if (idLibro == null) throw new IllegalArgumentException("ID de libro no válido.");
        try {
            stmt = this.obtenerStatement("select nombre, autor, tema "
                    + "from libros where idlibro = ?");
            stmt.setInt(1, idLibro);
            rst = this.obtenerResultSet(stmt);
            if (rst.next()) {
                libro = new Libro();
                libro.setIdLibro(idLibro);
                libro.setTitulo(rst.getString(1));
                libro.setAutor(rst.getString(2));
                libro.setTema(rst.getString(3));
            }
            
        } catch (SQLException ex) {
            throw new ErrorException(ErrorCode.ERROR_CARGAR_LIBRO, ex.getMessage(), ex.getErrorCode());
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
        
        return libro;
    }
    
    /**
     * Obtener los datos de todos los libros.
     * @return Listado de libros con los datos de la base de datos.
     * @throws ErrorException Error al cargar los datos de los libros.
     */
    public List<Libro> obtenerLibros() throws ErrorException {
        ArrayList<Libro> libros = null;
        Libro libro;
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        try {
            stmt = this.obtenerStatement("select idlibro, nombre, autor, tema "
                    + "from libros");
            rst = this.obtenerResultSet(stmt);
            
            libros = new ArrayList<>();
            
            while (rst.next()) {
                libro = new Libro();
                libro.setIdLibro(rst.getInt(1));
                libro.setTitulo(rst.getString(2));
                libro.setAutor(rst.getString(3));
                libro.setTema(rst.getString(4));
                libros.add(libro);
            }
            
        } catch (SQLException ex) {
            throw new ErrorException(ErrorCode.ERROR_CARGAR_LIBROS, ex.getMessage(), ex.getErrorCode());
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
        
        return libros;
    }
    
    /**
     * Actualizar en base de datos los datos del libro indicado.
     * @param libro Libro a actualizar en base de datos.
     * @throws InfoException Error por clave única duplicada.
     * @throws ErrorException Error al actualizar los datos del libro.
     */
    public void actualizarLibro(Libro libro) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        
        if (libro == null) throw new IllegalArgumentException("Libro no válido.");
        try {
            stmt = this.obtenerStatement("update libros set nombre = ?, "
                    + "autor = ?, tema = ? "
                    + "where idlibro = ?");
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getTema());
            stmt.setInt(4, libro.getIdLibro());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_UNIQUE_KEY) {
                throw new InfoException(ErrorCode.ERROR_UNIQUE_KEY, ex.getMessage(), ex.getErrorCode());
            } else {
                throw new ErrorException(ErrorCode.ERROR_ACTUALIZAR_LIBRO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            this.cerrarStatement(stmt);
        }
    }
            
    /**
     * Eliminar de base de datos los datos del libro indicado.
     * @param libro Libro a eliminar de base de datos.
     * @throws InfoException Error por clave referenciada (FK).
     * @throws ErrorException Error al eliminar los datos del libro.
     */
    public void eliminarLibro(Libro libro) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        
        if (libro == null) throw new IllegalArgumentException("Libro no válido.");
        try {
            stmt = this.obtenerStatement("delete from libros where idlibro = ?");
            stmt.setInt(1, libro.getIdLibro());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_FK_REFERENCE) {
                throw new InfoException(ErrorCode.ERROR_FK_REFERENCE, ex.getMessage());
            } else {
                throw new ErrorException(ErrorCode.ERROR_ELIMINAR_LIBRO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            this.cerrarStatement(stmt);
        }
    }
    
    /**
     * Insertar en base de datos los datos del libro indicado.
     * @param libro Libro a insertar en base de datos.
     * @throws InfoException Error por clave única duplicada.
     * @throws ErrorException Error al guardar los datos del libro.
     */
    public void insertarLibro(Libro libro) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        if (libro == null) throw new IllegalArgumentException("Libro no válido.");
        try {
            stmt = this.obtenerStatement("insert into libros (nombre, "
                    + "autor, tema) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getTema());
            stmt.executeUpdate();
            rst = stmt.getGeneratedKeys();
            if (rst != null && rst.next()) {
                libro.setIdLibro(rst.getInt(1));
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_UNIQUE_KEY) {
                throw new InfoException(ErrorCode.ERROR_UNIQUE_KEY, ex.getMessage(), ex.getErrorCode());
            } else {
                throw new ErrorException(ErrorCode.ERROR_INSERTAR_LIBRO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
    }
}
