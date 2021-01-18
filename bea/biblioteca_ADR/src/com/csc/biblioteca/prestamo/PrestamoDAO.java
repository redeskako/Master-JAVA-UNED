/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 12/12/2015
 */

package com.csc.biblioteca.prestamo;

import com.csc.biblioteca.excepciones.*;
import com.csc.biblioteca.conexion.MySQLConnection;
import com.csc.biblioteca.libro.Libro;
import com.csc.biblioteca.socio.Socio;
import com.csc.biblioteca.util.DAOBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** Clase de acceso a datos de préstamos. */
public class PrestamoDAO extends DAOBase {

    /**
     * Constructor de la clase de acceso a datos de préstamos.
     * @param conexion Objeto conextado a la base de datos.
     */
    public PrestamoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Obtener los datos del préstamo indicado por su identificador.
     * @param idPrestamo Identificador del préstamo.
     * @return Préstamo instanciado con los datos de la base de datos.
     * @throws ErrorException Error al cargar los datos del préstamo.
     */
    public Prestamo obtenerPrestamo(Integer idPrestamo) throws ErrorException {
        Prestamo prestamo = null;
        PreparedStatement stmt = null;
        ResultSet rst = null;
        Socio socio;
        Libro libro;
                
        if (idPrestamo == null) throw new IllegalArgumentException("ID de préstamo no válido.");
        try {
            stmt = this.obtenerStatement("select prestamos.idsocio, prestamos.idlibro, "
                    + "prestamos.fechainicio, prestamos.fechafin, socios.nombre, socios.apellidos, "
                    + "socios.dni, socios.direccion, socios.fechaalta, libros.nombre, "
                    + "libros.autor, libros.tema "
                    + "from prestamos, socios, libros where idprestamo = ? and "
                    + "prestamos.idsocio = socios.idsocio and prestamos.idlibro = libros.idlibro");
            stmt.setInt(1, idPrestamo);
            rst = this.obtenerResultSet(stmt);
            if (rst.next()) {
                socio = new Socio(rst.getString(5), rst.getString(6), rst.getString(7), 
                            rst.getString(8), rst.getDate(9));
                socio.setIdSocio(rst.getInt(1));
                
                libro = new Libro(rst.getString(10), rst.getString(11), rst.getString(12));
                libro.setIdLibro(rst.getInt(2));
                
                prestamo = new Prestamo(socio, libro, rst.getDate(3), rst.getDate(4));
                prestamo.setIdPrestamo(idPrestamo);
            }
            
        } catch (SQLException ex) {
            throw new ErrorException(ErrorCode.ERROR_CARGAR_PRESTAMO, ex.getMessage(), ex.getErrorCode());
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
        
        return prestamo;
    }
    
    /**
     * Obtener los datos de todos los préstamos.
     * @return Listado de préstamos con los datos de la base de datos.
     * @throws ErrorException Error al cargar los datos de los préstamos.
     */
    public List<Prestamo> obtenerPrestamos() throws ErrorException {
        ArrayList<Prestamo> prestamos;
        Prestamo prestamo;
        PreparedStatement stmt = null;
        ResultSet rst = null;
        Socio socio;
        Libro libro;
        
        try {
            stmt = this.obtenerStatement("select idprestamo, idsocio, idlibro, fechainicio, fechafin "
                    + " from prestamos");
            stmt = this.obtenerStatement("select prestamos.idsocio, prestamos.idlibro, "
                    + "prestamos.fechainicio, prestamos.fechafin, socios.nombre, socios.apellidos, "
                    + "socios.dni, socios.direccion, socios.fechaalta, libros.nombre, "
                    + "libros.autor, libros.tema, prestamos.idprestamo "
                    + "from prestamos, socios, libros where prestamos.idsocio = socios.idsocio "
                    + "and prestamos.idlibro = libros.idlibro");
            rst = this.obtenerResultSet(stmt);
            
            prestamos = new ArrayList<>();
            
            while (rst.next()) {
                socio = new Socio(rst.getString(5), rst.getString(6), rst.getString(7), 
                            rst.getString(8), rst.getDate(9));
                socio.setIdSocio(rst.getInt(1));
                
                libro = new Libro(rst.getString(10), rst.getString(11), rst.getString(12));
                libro.setIdLibro(rst.getInt(2));
                
                prestamo = new Prestamo(socio, libro, rst.getDate(3), rst.getDate(4));
                prestamo.setIdPrestamo(rst.getInt(13));
                prestamos.add(prestamo);
            }
            
        } catch (SQLException ex) {
            throw new ErrorException(ErrorCode.ERROR_CARGAR_PRESTAMOS, ex.getMessage(), ex.getErrorCode());
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
        
        return prestamos;
    }
    
    /**
     * Actualizar en base de datos los datos del préstamo indicado.
     * @param prestamo Préstamo a actualizar en base de datos.
     * @throws InfoException Error por clave única duplicada.
     * @throws ErrorException Error al actualizar los datos del préstamo.
     */
    public void actualizarPrestamo(Prestamo prestamo) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        
        if (prestamo == null) throw new IllegalArgumentException("Préstamo no válido.");
        try {
            stmt = this.obtenerStatement("update prestamos set idsocio = ?, "
                    + "idlibro = ?, fechainicio = ?, fechafin = ? "
                    + "where idprestamo = ?");
            stmt.setInt(1, prestamo.getIdSocio());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, prestamo.getFechaInicio());
            stmt.setDate(4, prestamo.getFechaFin());
            stmt.setInt(5, prestamo.getIdPrestamo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_UNIQUE_KEY) {
                throw new InfoException(ErrorCode.ERROR_UNIQUE_KEY, ex.getMessage(), ex.getErrorCode());
            } else {
                throw new ErrorException(ErrorCode.ERROR_ACTUALIZAR_PRESTAMO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            this.cerrarStatement(stmt);
        }
    }
            
    /**
     * Eliminar de base de datos los datos del préstamo indicado.
     * @param prestamo Préstamo a eliminar de base de datos.
     * @throws ErrorException Error al eliminar los datos del préstamo.
     */
    public void eliminarPrestamo(Prestamo prestamo) throws ErrorException {
        PreparedStatement stmt = null;
        
        if (prestamo == null) throw new IllegalArgumentException("Préstamo no válido.");
        try {
            stmt = this.obtenerStatement("delete from prestamos where idprestamo = ?");
            stmt.setInt(1, prestamo.getIdPrestamo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // No hay dependencias por FK con otras tablas
            throw new ErrorException(ErrorCode.ERROR_ELIMINAR_PRESTAMO, ex.getMessage(), ex.getErrorCode());
        } finally {
            this.cerrarStatement(stmt);
        }
    }
    
    /**
     * Insertar en base de datos los datos del préstamo indicado.
     * @param prestamo Préstamo a insertar en base de datos.
     * @throws InfoException Error por clave única duplicada.
     * @throws ErrorException Error al guardar los datos del préstamo.
     */
    public void insertarPrestamo(Prestamo prestamo) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        if (prestamo == null) throw new IllegalArgumentException("Préstamo no válido.");
        try {
            stmt = this.obtenerStatement("insert into prestamos (idsocio, "
                    + "idlibro, fechainicio, fechafin) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, prestamo.getIdSocio());
            stmt.setInt(2, prestamo.getIdLibro());
            stmt.setDate(3, prestamo.getFechaInicio());
            stmt.setDate(4, prestamo.getFechaFin());
            stmt.executeUpdate();
            rst = stmt.getGeneratedKeys();
            if (rst != null && rst.next()) {
                prestamo.setIdPrestamo(rst.getInt(1));
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_UNIQUE_KEY) {
                throw new InfoException(ErrorCode.ERROR_UNIQUE_KEY, ex.getMessage(), ex.getErrorCode());
            } else {
                throw new ErrorException(ErrorCode.ERROR_INSERTAR_PRESTAMO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
    }
}
