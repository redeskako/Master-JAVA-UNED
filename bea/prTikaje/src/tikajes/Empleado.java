package tikajes;

import java.sql.Date;
import java.text.SimpleDateFormat;





/**
 *
 * @author A L E F_08
 *
 *En esta clase tendremos toda la información necesaria de un empleado
 */
public class Empleado implements Comparable{

    //atributos
    private String nombre;
    private String apellidos;
    private String direccion;
    private Date fechaNacimiento;
    private int sueldo;

    //constructor
    public Empleado(String n,String a,String d,Date f,int s){
        this.nombre = n;
        this.apellidos = a;
        this.direccion = d;
        this.fechaNacimiento = f;
        this.sueldo = s;
    }

    //métodos
    public String nombre(){
        return this.nombre;
    }

    public String apellidos(){
        return this.apellidos;
    }

    public String direccion(){
        return this.direccion;
    }

    public Date fechaNacimiento(){
        return this.fechaNacimiento;
    }

    public double sueldo(){
        return this.sueldo;
    }


    //métodos a redefinir
   /*public boolean equals(Object o){

            return o instanceof Empleado &&
                   this.nombre.equalsIgnoreCase(((Empleado) o).nombre) ;

    }*/

    public boolean equals(Empleado e){
    	return (this.nombre.equals(e.nombre)&&
    	       this.apellidos.equals(e.apellidos)&&
    	       this.direccion.equals(e.direccion) &&
    	       this.fechaNacimiento.equals(e.fechaNacimiento) &&
    	       (this.sueldo==e.sueldo));
    }


//  voy a utilizar un método privado para obtener la fecha ordenada.
    //Para ello utilizo la clase SimpleDateFormat
	private String ordenarFecha(Date f){
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return formatoFecha.format(f);
	}

    public String toString(){
    	String cad = "";
         cad += "nombre: " + this.nombre + " " + this.apellidos + "\n" + "direccion: " + this.direccion + "\n";
         cad += "Fecha de nacimiento: " + ordenarFecha(this.fechaNacimiento);
        cad +=   "\n" + "Sueldo: " + this.sueldo;
        return cad;
    }


	public int compareTo(Object o) {

		Empleado e = (Empleado)o;
		return e.nombre.compareTo(this.nombre);
	}
}
