/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 17/11/2015
 */

package com.csc.biblioteca.socio;

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

/** Clase de acceso a datos de socios. */
public class SocioDAO extends DAOBase {
    
    /**
     * Constructor de la clase de acceso a datos de socios.
     * @param conexion Objeto conextado a la base de datos.
     */
    public SocioDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Obtener los datos del socio indicado por su identificador.
     * @param idSocio Identificador del socio.
     * @return Socio instanciado con los datos de la base de datos.
     * @throws ErrorException Error al cargar los datos del socio.
     */
    public Socio obtenerSocio(Integer idSocio) throws ErrorException {
        Socio socio = null;
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        if (idSocio == null) throw new IllegalArgumentException("ID de socio no válido.");
        try {
            stmt = this.obtenerStatement("select dni, apellidos, nombre, direccion, "
                    + "fechaalta from socios where idsocio = ?");
            stmt.setInt(1, idSocio);
            rst = this.obtenerResultSet(stmt);
            if (rst.next()) {
                socio = new Socio();
                socio.setIdSocio(idSocio);
                socio.setDni(rst.getString(1));
                socio.setApellidos(rst.getString(2));
                socio.setNombre(rst.getString(3));
                socio.setDireccion(rst.getString(4));
                socio.setFechaAlta(rst.getDate(5));
            }
            
        } catch (SQLException ex) {
            throw new ErrorException(ErrorCode.ERROR_CARGAR_SOCIO, ex.getMessage(), ex.getErrorCode());
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
        
        return socio;
    }
    
    /**
     * Obtener los datos de todos los socios.
     * @return Listado de socios con los datos de la base de datos.
     * @throws ErrorException Error al cargar los datos de los socios.
     */
    public List<Socio> obtenerSocios() throws ErrorException {
        ArrayList<Socio> socios = null;
        Socio socio;
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        try {
            stmt = this.obtenerStatement("select idsocio, dni, apellidos, nombre, direccion, "
                    + "fechaalta from socios");
            rst = this.obtenerResultSet(stmt);
            
            socios = new ArrayList<>();
            
            while (rst.next()) {
                socio = new Socio();
                socio.setIdSocio(rst.getInt(1));
                socio.setDni(rst.getString(2));
                socio.setApellidos(rst.getString(3));
                socio.setNombre(rst.getString(4));
                socio.setDireccion(rst.getString(5));
                socio.setFechaAlta(rst.getDate(6));
                socios.add(socio);
            }
            
        } catch (SQLException ex) {
            throw new ErrorException(ErrorCode.ERROR_CARGAR_SOCIOS, ex.getMessage(), ex.getErrorCode());
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
        
        return socios;
    }
    
    /**
     * Actualizar en base de datos los datos del socio indicado.
     * @param socio Socio a actualizar en base de datos.
     * @throws InfoException Error por clave única duplicada.
     * @throws ErrorException Error al actualizar los datos del socio.
     */
    public void actualizarSocio(Socio socio) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        
        if (socio == null) throw new IllegalArgumentException("Socio no válido.");
        try {
            stmt = this.obtenerStatement("update socios set dni = ?, "
                    + "apellidos = ?, nombre = ?, direccion = ?, fechaalta = ? "
                    + "where idsocio = ?");
            stmt.setString(1, socio.getDni());
            stmt.setString(2, socio.getApellidos());
            stmt.setString(3, socio.getNombre());
            stmt.setString(4, socio.getDireccion());
            stmt.setDate(5, socio.getFechaAlta());
            stmt.setInt(6, socio.getIdSocio());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_UNIQUE_KEY) {
                throw new InfoException(ErrorCode.ERROR_UNIQUE_KEY, ex.getMessage(), ex.getErrorCode());
            } else {
                throw new ErrorException(ErrorCode.ERROR_ACTUALIZAR_SOCIO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            this.cerrarStatement(stmt);
        }
    }
            
    /**
     * Eliminar de base de datos los datos del socio indicado.
     * @param socio Socio a eliminar de base de datos.
     * @throws InfoException Error por clave referenciada (FK).
     * @throws ErrorException Error al eliminar los datos del socio.
     */
    public void eliminarSocio(Socio socio) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        
        if (socio == null) throw new IllegalArgumentException("Socio no válido.");
        try {
            stmt = this.obtenerStatement("delete from socios where idsocio = ?");
            stmt.setInt(1, socio.getIdSocio());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_FK_REFERENCE) {
                throw new InfoException(ErrorCode.ERROR_FK_REFERENCE, ex.getMessage());
            } else {
                throw new ErrorException(ErrorCode.ERROR_ELIMINAR_SOCIO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            this.cerrarStatement(stmt);
        }
    }
    
    /**
     * Insertar en base de datos los datos del socio indicado.
     * @param socio Socio a insertar en base de datos.
     * @throws InfoException Error por clave única duplicada.
     * @throws ErrorException Error al guardar los datos del socio.
     */
    public void insertarSocio(Socio socio) throws InfoException, ErrorException {
        PreparedStatement stmt = null;
        ResultSet rst = null;
        
        if (socio == null) throw new IllegalArgumentException("Socio no válido.");
        try {
            stmt = this.obtenerStatement("insert into socios (dni, "
                    + "apellidos, nombre, direccion, fechaalta) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, socio.getDni());
            stmt.setString(2, socio.getApellidos());
            stmt.setString(3, socio.getNombre());
            stmt.setString(4, socio.getDireccion());
            stmt.setDate(5, socio.getFechaAlta());
            stmt.executeUpdate();
            rst = stmt.getGeneratedKeys();
            if (rst != null && rst.next()) {
                socio.setIdSocio(rst.getInt(1));
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MySQLConnection.MYSQL_ERROR_UNIQUE_KEY) {
                throw new InfoException(ErrorCode.ERROR_UNIQUE_KEY, ex.getMessage(), ex.getErrorCode());
            } else {
                throw new ErrorException(ErrorCode.ERROR_INSERTAR_SOCIO, ex.getMessage(), ex.getErrorCode());
            }
        } finally {
            if (rst != null) this.cerrarResultSet(rst);
            this.cerrarStatement(stmt);
        }
    }
}
