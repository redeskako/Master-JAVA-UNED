package org.basedatos;

import org.basedatos.BBDD;
import org.basedatos.ErrorException;

	//Componentes del listado de usuarios de la base de datos
public class Usuario implements Comparable<Object> {
		private int IdUsuario;
		private String NombreUsuario;
		private String Nombre;
		private String Apellidos;
		private String Contrasena;
		private int Rol;

		public Usuario (int IdUsuario, String NombreUsuario, String Nombre, String Apellidos, String Contraseña,int Rol){
		this.IdUsuario = IdUsuario; 
		this.NombreUsuario = NombreUsuario;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Contrasena = Contraseña;
		this.Rol = Rol;
		}

		public Usuario (){
		this.IdUsuario = 0; 
		this.NombreUsuario = "";
		this.Nombre = "";
		this.Apellidos = "";
		this.Contrasena = "";
		this.Rol = 0;
		}

		
		//Declaración de getters y setters de la base de datos de usuarios
		public int IdUsuario() {
			return IdUsuario;
		}

		public void IdUsuario(int idUsuario) {
			IdUsuario = idUsuario;
		}

		public String NombreUsuario() {
			return NombreUsuario;
		}

		public void NombreUsuario(String nombreUsuario) {
			NombreUsuario = nombreUsuario;
		}

		public String Nombre() {
			return Nombre;
		}

		public void Nombre(String nombre) {
			Nombre = nombre;
		}

		public String Apellidos() {
			return Apellidos;
		}

		public void Apellidos(String apellidos) {
			Apellidos = apellidos;
		}

		public String Contrasena() {
			return Contrasena;
		}

		public void Contraseña(String contraseña) {
			Contrasena = Contrasena();
		}

		public void Rol(int rol) {
			Rol = rol;
		}



		public String toString (){
		return "(Usuario " + this.IdUsuario + ":" + this.NombreUsuario + ":" + this.Contrasena + ")";
		}

		public int compareTo (Object o){
		Usuario u = (Usuario) o;
		return this.compareToIgnoreCase(u.NombreUsuario);
		}



		private int compareToIgnoreCase(String nombreUsuario2) {
			// TODO Auto-generated method stub
			return 0;
		}

		public boolean equals (Object o){
		Usuario u = (Usuario) o; 
		return this.compareTo(u) == 0;
		}

		public int getRol(){
		return Rol;
		}

		public void SetRol(int Rol){
		this.Rol = Rol;
		}

		//Para obtener el nombre de usuario e id de la base de datos
		public String getNameUser (int iduser){
		String nombreUsuario = "";
		try {

		BBDD miBd = new BBDD();
		miBd.abrirConexion();
		nombreUsuario = miBd.getNameUser ("Select NombreUsuario from usuarios where IdUsuario=" + iduser);
		miBd.cerrarConexion();

		}catch (ErrorException e){

		System.out.println("Error en la aplicación " + e.getMessage());

		}catch (Exception e){
		System.out.println("Error desconocido" + e.getMessage());
		}
		
		return nombreUsuario;
		}

		//Para validar los usuarios
		public Usuario validarUser (String user, String passw){
		Usuario usu = new Usuario();
		try {

			BBDD miBd = new BBDD();
			miBd.abrirConexion();
			usu = miBd.getUsuario ("Select * from usuarios where NombreUsuario='" + user + "' and Contrasena='" + passw + "'");
			miBd.cerrarConexion();
		}catch (ErrorException e){
			System.out.println("Error en aplicación " + e.getMessage());
		}catch (Exception e){
			System.out.println("Error desconocido " + e.getMessage());
		}
		return usu;
		}

		}
