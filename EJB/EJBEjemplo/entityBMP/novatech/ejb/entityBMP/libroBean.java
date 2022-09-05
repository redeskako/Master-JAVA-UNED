package novatech.ejb.entityBMP;

import javax.ejb.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class libroBean implements EntityBean {

    static DataSource ds ;

    String isbn ;
    String titulo ;
    String autor ;
    String anyo ;
    String edicion ;
    String editorial ;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }


    public void ejbActivate() throws EJBException, RemoteException {}

	public void ejbLoad() throws EJBException, RemoteException {
        Connection conn = null ;
        try {
            conn = ds.getConnection() ;
            Statement st = conn.createStatement() ;
            String query = "select * from libros where ISBN='" + this.getIsbn() + "'" ;
            ResultSet rs = st.executeQuery (query) ;

            if (rs.next()) {
                this.setIsbn (rs.getString ("isbn"));
                this.setTitulo (rs.getString ("titulo")) ;
                this.setAutor (rs.getString ("autor"));
                this.setAnyo (rs.getString ("anyo"));
                this.setEdicion (rs.getString ("edicion"));
                this.setEditorial (rs.getString ("editorial"));
            }
            else {
                throw new Exception ("No existe el objeto") ;
            }
            rs.close() ;
            st.close() ;
        }
        catch (Exception e) {
            System.out.println (e.getMessage()) ;
            throw new EJBException (e.getMessage()) ;
        }
        finally {
            try {
                conn.close() ;
            }
            catch (Exception ae) {}
        }

   }

	public void ejbPassivate() throws EJBException, RemoteException {}

	public void ejbRemove() throws RemoveException, EJBException, RemoteException {
        Connection conn = null ;
         try {
             conn = ds.getConnection() ;
             Statement st = conn.createStatement() ;
             String query = "delete libros where ISBN='" + this.getIsbn() + "'" ;
             int iNum = st.executeUpdate (query) ;
             st.close() ;
             if (iNum == 0) throw new Exception ("No se puede borrar lo que no existe") ;
         }
         catch (Exception e) {
             System.out.println (e.getMessage()) ;
             throw new EJBException (e.getMessage()) ;
         }
         finally {
             try {
                 conn.close() ;
             }
             catch (Exception ae) {}
         }
    }

	public void ejbStore() throws EJBException, RemoteException {
        Connection conn = null ;
        try {
            conn = ds.getConnection() ;
            String query = "update libros " +
                           "set titulo = ?, autor = ?, anyo = ?, edicion = ?, editorial = ? " +
                           "where isbn = ? " ;
            PreparedStatement pst = conn.prepareStatement (query) ;
            pst.setString (1, this.getTitulo()) ;
            pst.setString (2, this.getAutor()) ;
            pst.setString (3, this.getAnyo()) ;
            pst.setString (4, this.getEdicion()) ;
            pst.setString (5, this.getEditorial()) ;
            pst.setString (6, this.getIsbn()) ;

            int iNum = pst.executeUpdate (query) ;
            pst.close() ;
            if (iNum == 0) throw new Exception ("No se ha actualizado nada") ;
        }
        catch (Exception e) {
             System.out.println (e.getMessage()) ;
             throw new EJBException (e.getMessage()) ;
        }
        finally {
            try {
                conn.close() ;
            }
            catch (Exception ae) {}
        }
    }

	public void setEntityContext(EntityContext arg0) throws EJBException, RemoteException {}

	public void unsetEntityContext() throws EJBException, RemoteException {}

	public String ejbCreate (String ISBN, String autor, String titulo, String anyo, String edicion, String editorial) throws RemoteException {
        this.setAutor (autor) ;
        this.setTitulo (titulo) ;
        this.setAnyo (anyo) ;
        this.setEdicion (edicion) ;
        this.setEditorial (editorial) ;
        this.setIsbn (ISBN) ;

        Connection conn = null ;
        try {
            conn = ds.getConnection() ;
            String query = "insert into libros (isbn, titulo, autor, anyo, edicion, editorial) " +
                           "values (?, ?, ?, ?, ?, ?) " ;
            PreparedStatement pst = conn.prepareStatement (query) ;
            pst.setString (1, this.getIsbn()) ;
            pst.setString (2, this.getTitulo()) ;
            pst.setString (3, this.getAutor()) ;
            pst.setString (4, this.getAnyo()) ;
            pst.setString (5, this.getEdicion()) ;
            pst.setString (6, this.getEditorial()) ;

            int iNum = pst.executeUpdate (query) ;
            pst.close() ;
            if (iNum == 0) throw new Exception ("No se ha actualizado nada") ;
        }
        catch (Exception e) {
             System.out.println (e.getMessage()) ;
             throw new EJBException (e.getMessage()) ;
        }
        finally {
            try {
                conn.close() ;
            }
            catch (Exception ae) {}
        }
        return this.getIsbn() ;
	}

    public void ejbPostCreate (String ISBN, String autor, String titulo, String anyo, String edicion, String editorial) throws RemoteException {}

    public String ejbFindByPrimaryKey (String ISBN) throws RemoteException, FinderException {
        Connection conn = null ;
        try {
            conn = ds.getConnection() ;
            Statement st = conn.createStatement() ;
            String query = "select isbn from libros where ISBN='" + this.getIsbn() + "'" ;
            ResultSet rs = st.executeQuery (query) ;
            boolean b = rs.next() ;
            rs.close() ;
            st.close() ;

            if (b)
                return ISBN ;
            else
                throw new Exception ("No se ha encontrado Nada") ;
        }
        catch (Exception e) {
            System.out.println (e.getMessage()) ;
            throw new FinderException(e.getMessage()) ;
        }
        finally {
            try {
                conn.close() ;
            }
            catch (Exception ae) {}
        }


    }

    static {
        try {
            InitialContext ic = new InitialContext() ;
            ds = (DataSource) ic.lookup ("CursoDataSource") ;
        }
        catch (Exception e) {}
    }

}
