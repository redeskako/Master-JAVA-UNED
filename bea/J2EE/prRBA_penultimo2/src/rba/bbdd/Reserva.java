
package rba.bbdd;

import java.sql.*;

import rba.*;
import java.util.*;
import java.lang.*;
import configuracion.RbaException;
import java.text.DateFormat;
import java.text.ParseException;

public class Reserva implements Comparable<Reserva> {

    public static final int INCREMENTO = 5;
    private int codigo;
    private String fecha;
    private String hora;
    private String turno;
    private int usuario;
    private Integer cliente;
    private int mesa;

    public Reserva() {
        this.codigo = 0;
        this.fecha = null;
        this.hora = null;
        this.turno = null;
        //this.usuario = 0;
        this.cliente = 0;
        this.mesa = 0;

    }

    //constructor2  para el tema del javabean,
    public Reserva(int codigo, String fecha, String hora, String turno, Integer cliente, int mesa) {
        this.codigo = 0;
        this.fecha = fecha;
        this.hora = hora;
        this.turno = turno;
        //this.usuario= usuario;
        this.cliente = cliente;
        this.mesa = mesa;

    }

    public int compareTo(Reserva r) {
        return this.hora.compareToIgnoreCase(r.getHora()) + this.codigo;
    }

    public int hashCode() {
        return this.hora.toUpperCase().hashCode() + this.codigo;
    }

    public boolean equals(Object o) {
        try {
            Reserva r = (Reserva) o;
            return this.hora.equalsIgnoreCase(r.hora);
        } catch (ClassCastException e) {
            throw new RbaException("Error de casting");
        }
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer c) {
        this.codigo = c;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String f) {
        this.fecha = f;
    }

    public String getHora() {
        return this.hora;
    }

    public void setHora(String h) {
        this.hora = h;
    }

    public String getTurno() {
        return this.turno;
    }

    public void setTurno(String t) {
        this.turno = t;
    }

    public void setUsuario(int u) {
        this.usuario = u;
    }

    public int getUsuario() {
        return this.usuario;
    }

    public void setCliente(Integer cl) {
        this.cliente = cl;
    }

    public Integer getCliente() {
        return this.cliente;
    }

    public void setMesa(int me) {
        this.mesa = me;
    }

    public int getMesa() {
        return this.mesa;
    }

    public String toString() {
        return "RESERVA:[" + this.codigo + "," + this.fecha + " " + this.hora + " " + this.turno + " " + this.cliente + "]";
    }

    //voy a recuperar la fecha y la hora del momento en que se realiza la reserva:

    //para ello utilizar� dos m�todos, en uno tendr� la cadena fecha
    Calendar calendario = Calendar.getInstance();

    public String recFecha() {

        return calendario.get(Calendar.DAY_OF_MONTH) + "/" +
                calendario.get(Calendar.MONTH) + "/" +
                calendario.get(Calendar.YEAR);
    }

    public String recHora() {

        return calendario.get(Calendar.HOUR_OF_DAY) + ":" +
                calendario.get(Calendar.MINUTE) + ":" +
                calendario.get(Calendar.SECOND);
    }
    String Fecha = recFecha();
    String Hora = recHora();

    private static Set<Reserva> ejecutaSQLReserva(ResultSet rst) {
        Set<Reserva> sr = new TreeSet<Reserva>();
        try {
            while (rst.next()) {
                Reserva r = new Reserva();

                r.setCodigo(rst.getInt("codigo"));
                try {
                    r.setFecha(rst.getString("fecha"));
                } catch (Exception e) {
                    r.setFecha("Fecha");
                }

                try {
                    r.setHora(rst.getString("hora"));
                } catch (Exception e) {
                    r.setHora("Hora");
                }

                try {
                    r.setTurno(rst.getString("turno"));
                } catch (Exception e) {
                    r.setTurno("");
                }

                //try{
                //	r.setUsuario(rst.getInt("usuario"));
                //}catch(Exception e){
                //	r.setUsuario(0);
                //}

                try {
                    r.setCliente(rst.getInt("cliente"));
                } catch (Exception e) {
                    r.setCliente(0);
                }

                try {
                    r.setMesa(rst.getInt("mesa"));
                } catch (Exception e) {
                    r.setMesa(0);
                }

                sr.add(r);
            }
            rst.close();
        } catch (SQLException e) {
            throw new RbaException("Problema con las bases de datos");
        }
        return sr;
    }

    private static Set<Reserva> ejecutaSQLReserva(String sql) {
        Set<Reserva> sr = null;
        Connection conn = null;
        Statement stm = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
            stm = conn.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            //Proceso el select
            sr = ejecutaSQLReserva(rst);
            conn.close();
        } catch (ClassCastException e) {
            throw new RbaException("No est� cargado el driver");
        } catch (SQLException e) {
            throw new RbaException("No puede conectar con la base de datos");
        } catch (Exception e) {
            throw new RbaException("Error Indefinido");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return sr;
    }


    //INSERTA UNA RESERVA
    public void InsertarReserva(String Fecha, String Hora, String turno, int cliente, int mesa) {
        Connection conn = null;
        Statement stm = null;


        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rba", "usuario", "usuario");

            String sql = "INSERT INTO RESERVAS (fecha,hora,turno,cliente,mesa)VALUES ('" + Fecha + "','" + Hora + "','" + turno + "'," + cliente + "," + mesa + ")";

            PreparedStatement rst = conn.prepareStatement(sql);
            rst.executeUpdate();



        } catch (ClassCastException e) {
            throw new RbaException("No est� cargado el driver");
        } catch (SQLException e) {

            throw new RbaException("No puede conectar con la base de datos");
        } catch (Exception e) {
            throw new RbaException("Error Indefinido");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    //definimos una funci�n para retornar el n�mero del cliente

    // ACTUALIZA LA RESERVA
    public void ActualizarReserva(Integer codigo, String fecha, String hora, String turno, int cliente, int mesa) {

        Connection conn = null;
        Statement stm = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");

            //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            //Las M en may�sculas o interpretar� minutos!!

            //try{
            //	java.util.Date fecha2 = sdf.parse(fecha);
            //}catch(ParseException e) {
            //   // TODO Auto-generated catch block
            //   e.printStackTrace();}

            String sql = "UPDATE RESERVAS SET fecha = '" + fecha + "' ,hora = '" + hora + "' ,turno = '" + turno + "' ,cliente = " + cliente + " ,mesa = " + mesa + " WHERE codigo=" + codigo + ";";
            //Proceso el select
            PreparedStatement rst = conn.prepareStatement(sql);
            rst.executeUpdate();


        } catch (ClassCastException e) {
            throw new RbaException("No est� cargado el driver");
        } catch (SQLException e) {
            throw new RbaException("No puede conectar con la base de datos");
        } catch (Exception e) {
            throw new RbaException("Error Indefinido");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    //ELIMINAR UNA RESERVA
    public void EliminarReserva(Integer codigo) {

        Connection conn = null;
        Statement stm = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
            stm = conn.createStatement();

            String sql = "DELETE FROM RESERVAS WHERE codigo=" + codigo + ";";
            //Proceso el select
            PreparedStatement rst = conn.prepareStatement(sql);
            rst.executeUpdate();


        } catch (ClassCastException e) {
            throw new RbaException("No est� cargado el driver");
        } catch (SQLException e) {
            throw new RbaException("No puede conectar con la base de datos");
        } catch (Exception e) {
            throw new RbaException("Error Indefinido");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    //PARA EL LISTADO DE RESERVAS
    public static int calculafilasReservas() {

        Connection conn = null;
        Statement stm = null;
        int total = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
            stm = conn.createStatement();
            ResultSet rst = stm.executeQuery("SELECT COUNT(*) AS Total FROM RESERVAS");
            //Proceso el select
            rst.next();
            total = rst.getInt("Total");
            conn.close();
        } catch (ClassCastException e) {
            throw new RbaException("No est� cargado el driver");
        } catch (SQLException e) {
            throw new RbaException("No puede conectar con la base de datos");
        } catch (Exception e) {
            throw new RbaException("Error Indefinido");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return total;
    }

    public static int calculaTotalPaginasR() {
        return calculafilasReservas() / INCREMENTO;
    }
  //Método que hará que me de solo el número de filas que cumplen las condiciones
	//pasadas por parámetros
    public static int calculaNumeroPaginas(String campo, String texto) {
        Set<Reserva> listaReservas;
        String sql = "SELECT * FROM RESERVAS";
        System.out.println("CAMPO: " + campo + "/TEXTO: " + texto);
        if (texto != null && !texto.equals("")) {
            sql = "SELECT * FROM RESERVAS WHERE " + campo + " like '%" + texto + "%'";
        }
        listaReservas = ejecutaSQLReserva(sql);
        if ((listaReservas.size() % INCREMENTO) == 0) {
            return (listaReservas.size() / INCREMENTO) - 1;
        } else {
            return listaReservas.size() / INCREMENTO;
        }
    }

    //hay que ordenar por fecha y hora
    public static Set<Reserva> ejecutaListaReserva(int i) {
        String sql = "SELECT * FROM RESERVAS ORDER BY fecha,hora LIMIT 0," + INCREMENTO;
        if (i > 0) {
            sql = "SELECT * FROM RESERVAS ORDER BY fecha,hora LIMIT " + i * 5 + "," + INCREMENTO;
        }
        return ejecutaSQLReserva(sql);
    }

    // funcion para crear select segun unos parametros
    // campo: con este parametro podemos elegir el campo de ordenacion y la opcion de busqueda
    // esta se activaria si en la caja de texto de busqueda hubiera algo que no sea blanco o null
    // texto: proviene de la caja de texto, que nos sirve para introducir los datos de busqueda
    // i: datos de inicio para paginar la seleccion
    public static Set<Reserva> ejecutaListaCampoReserva(int i, String campo, String texto) {
        String sql = "SELECT * FROM RESERVAS ORDER BY " + campo + " LIMIT 0," + INCREMENTO;

        if (!texto.equals("")) {
            sql = "SELECT * FROM RESERVAS where " + campo + " like '%" + texto + "%' order BY fecha,hora LIMIT 0," + INCREMENTO;
            if (i > 0) {
                sql = "SELECT * FROM RESERVAS where " + campo + " like '%" + texto + "%' ORDER BY fecha,hora LIMIT " + i * 5 + "," + INCREMENTO;
            }

        } else {
            if (i > 0) {
                sql = "SELECT * FROM RESERVAS ORDER BY " + campo + " LIMIT  " + i * 5 + "," + INCREMENTO;
            }
        }

        return ejecutaSQLReserva(sql);
    }

    // esta funcion crea una select para buscar un solo cliente
    public static Set<Reserva> ejecutaBusquedaReserva(int i) {
        String sql = "SELECT * FROM RESERVAS	WHERE codigo = " + i;
        return ejecutaSQLReserva(sql);
    }

    public static boolean estaReservado(String Fecha, String Hora, String Turno, Integer Cliente, Integer Mesa) {
        boolean estaReservado = false;
        Connection conn = null;
        Statement stm = null;
        try {
            String sql = "SELECT count(*) as Total FROM RESERVAS WHERE (fecha='" + Fecha + "')  AND (turno='" + Turno + "') AND (mesa=" + Mesa + ")";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
            stm = conn.createStatement();

            ResultSet rst = stm.executeQuery(sql);
            rst.next();
            estaReservado = (rst.getInt("Total") == 1);
            conn.close();
        } catch (ClassCastException e) {
            throw new RbaException("No est� cargado el Driver");
        } catch (SQLException e) {
            throw new RbaException("No puede conectar con la BBDD");
        } catch (Exception e) {
            throw new RbaException("Error Indefinido");
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return estaReservado;
    }
}
