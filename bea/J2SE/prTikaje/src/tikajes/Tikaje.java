package tikajes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author A L E F_08
 *
 *En esta clase controlaremos la entrada y salida de los empleados.
 */
public class Tikaje {


	private Set<Empleado> cEmpleado;
	private Map<Empleado,Catjor> sistTikaje;

	//constructor donde creo las estructuras
	public Tikaje(){
		cEmpleado = new TreeSet<Empleado>();
		sistTikaje = new TreeMap<Empleado,Catjor>();


	}

	//métodos a utilizar para asignar los empleados al conjunto

	public void añadirEmpleado(Empleado e){
		//si no pertenece al conjunto, lo añado
		if(!this.cEmpleado.contains(e)){
			this.cEmpleado.add(e);
		}
		else{//lanzo una excepción, ya que no puede estar dos veces el mismo empleado
			throw new TikajeException("ya se encuentra el empleado");
		}
	}
	public void eliminarEmpleado(Empleado e){
		//si el conjunto esta vacio
		if(this.cEmpleado.isEmpty()){
			throw new TikajeException("no hay empleados");
		}//miramos si el empleado forma parte de la estructura
		else if(this.cEmpleado.contains(e)){
			this.cEmpleado.remove(e);
		}
		else{

			throw new TikajeException("no se encuentra el empleado");
		}
	}

	/**
	 * métodos a utilizar para guardar los empleados con su categoria y jornada
	 */

	public void asignarCategoriaJornada(Empleado e,Catjor cj){
		//Si no se encuentra el empleado lo agregamos, y si se encuentra, lo que
		//va a hacer es cambiar de empleo, asi que lo único que necesitamos es
		//lo siguiente:
			this.sistTikaje.put(e,cj);
	}

	/**
	 * método a utilizar para tikar
	 */






	//método para listar los empleados que trabajan en la empresa.
	//utilizamos un iterador para recorrer el conjunto, y lo vamos
	//metiendo en String llamado cad, inicialmente vacia;
	public String imprimirEmpleados(){
		String cad = "";
		Iterator it = this.cEmpleado.iterator();
		while (it.hasNext()){
			cad += it.next().toString();
		}
		return cad;
	}

	//Para iterar el Map, necesito utilizar Map.Entry, ya que el Map no dispone
	//de método Iterator. Utilizo el iterador para recorrer la estructura.
	//me irá imprimiendo la clave, que en este caso es un empleado, y el valor
	//donde tengo la categoria y la jornada
	public String toString(){
		String cad = "";
		Iterator<Map.Entry<Empleado,Catjor>> i = this.sistTikaje.entrySet().iterator();
		while (i.hasNext()){
			Map.Entry<Empleado,Catjor> me = i.next();
			cad += me.getKey()+ "\n" + me.getValue();
			cad += "\n" + "\n";
		}
		return cad;
	}

}