package uned.java2016.spring.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
	@NamedQuery(query="Select p from GrupoJava2016 p",name="Mostrar todos"),
	@NamedQuery(query="Select p from GrupoJava2016 p where p.edad<:edad",name="Mostrar menores de")
})

public class GrupoJava2016 implements Personas{
	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO) 
	   private int id;
	   private String nombre;
	   private String rol;
	   private int edad;
	   
	   public GrupoJava2016(int id,String nombre,String rol,int edad) 
	   {
	      super( );
	      this.id=id;
	      this.nombre=nombre;
	      this.rol=rol;
	      this.edad=edad;
	   }

	   public GrupoJava2016( ) 
	   {
	      super();
	   }

	   /**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
	
		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
	
		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}
	
		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	
		/**
		 * @return the rol
		 */
		public String getRol() {
			return rol;
		}
	
		/**
		 * @param rol the rol to set
		 */
		public void setRol(String rol) {
			this.rol = rol;
		}
	
		/**
		 * @return the edad
		 */
		public int getEdad() {
			return edad;
		}
	
		/**
		 * @param edad the edad to set
		 */
		public void setEdad(int edad) {
			this.edad = edad;
		}

		@Override
		public String toString()
		{
			return "Compañero [Id="+id+", Nombre="+nombre+", rol="+rol+", edad="+edad+"]";
		}
}
