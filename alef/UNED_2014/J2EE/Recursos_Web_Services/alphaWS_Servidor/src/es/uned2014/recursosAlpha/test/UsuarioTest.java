/**
 * 
 */
package es.uned2014.recursosAlpha.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Clase UsuarioTest para realizar pruebas unitarias sobre Usuario
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @since	Junio 2014
 */
public class UsuarioTest {

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#trasformarContrasena(java.lang.String)}.
	 */
/*	@Test
	public void testTrasformarContrasena() {
	}*/

	/**
	 * Se comprueba que el método devuelve null para usuarios no encontrados en BBDD.
	 * Se comprueba que NO devuelve null para usuarios sí encontrados en BBDD
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#validarUsuario(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testValidarUsuario() {
		Usuario u = new Usuario();
		
		Usuario uRespuesta1 = u.validarUsuario("", "");
		assertNull(uRespuesta1);
		
		Usuario uRespuesta2 = u.validarUsuario("loliva", "contraseñaMal");
		assertNull(uRespuesta2);
		
		Usuario uRespuesta3 = u.validarUsuario("soevering", "alpha");
		assertEquals(uRespuesta3.getNombreUsuario(), "soevering");
	}

	/**
	 * Tal como está la base de datos actual, podemos probar que en la página 100 del 
	 * listado de usuarios no hay nada, y en la 1 hay usuarios.
	 * Si ponemos 0 a la página, debe devolver todos los usuarios.
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#usuariosListado(int)}.
	 */
	@Test
	public void testUsuariosListado() {
		Usuario u = new Usuario();		
		ArrayList<Usuario> a = new ArrayList<Usuario>();
		
		a = u.usuariosListado(100);
		assertTrue(a.size() == 0);
		
		a = u.usuariosListado(1);
		assertTrue(a.size() != 0);
		
		a = u.usuariosListado(0);
		int i = u.totalFilasUsuario();
		assertTrue(a.size() == i);
	}

	/**
	 * Tal como está diseñada la aplicación, los dos únicos roles son "empleado" y "responsable".
	 * Vamos a comprobar que son los que obtiene el método.
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#usuariosRoles()}.
	 */
	@Test
	public void testUsuariosRoles() {
		Usuario u = new Usuario();
		ArrayList<Usuario> a = u.usuariosRoles();

		assertEquals(a.get(0).getRol(), "Empleado");
		assertEquals(a.get(1).getRol(), "Responsable");
	}

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#totalFilasUsuario()}.
	 */
/*	@Test
	public void testTotalFilasUsuario() {
	}*/
	
	/**
	 * Se comprueba que si se intenta crear un usuario con nombreUsuario ya existente da error.
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#nuevo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */	
	@Test
	public void testNuevo() {
		Usuario u = new Usuario();
		int id = u.nuevo("cmorales", "Carlos", "Morales", "xxx", 2);
		
		assertTrue (id == 0);
	}

	/**
	 * Se comprueba que si se intenta editar un usuario y darle un nombreUsuario ya existente da error.
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#editarUsuario(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testEditarUsuario() {
		Usuario u = new Usuario();
		int id = u.editarUsuario(8, "loliva", "Laura", "Oliva", "xxx", 1);
		
		assertTrue (id == 0);
	}
	
	/**
	 * Se comprueba que si se intenta editar un usuario y darle un nombreUsuario no existente, no da error.
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#editarUsuario(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testEditarUsuario2() {
		Usuario u = new Usuario();
		int i = u.editarUsuario(8, "pmartin", "Paula", "Martin Sanz", "yyy", 2);
		
		assertTrue(i!=0);
		
		u.eliminar(8); // Se elimina el usuario de prueba
	}

	/**
	 * Se comrpueba que una vez eliminado un usuario, no se encuentra si se realiza una búsqueda
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#eliminar(int)}.
	 */
	@Test
	public void testEliminar() {
		Usuario u = new Usuario();
		int i = u.nuevo("pmartin", "Paula", "Martin Sanz", "yyy", 2); // se crea usuario
		
		u.eliminar(i); // se elimina el usuario
		
		String sql = " WHERE IdUsuario=" + i;
		ArrayList<Usuario> a = u.usuariosBuscar(0, sql); // se busca si existe
		
		assertTrue(a.size() == 0);	
	}

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#sqlBuscadorUsuarios(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
/*	@Test
	public void testSqlBuscadorUsuarios() {
	}*/

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#usuariosBuscar(int, java.lang.String)}.
	 */
/*	@Test
	public void testUsuariosBuscar() {
	}*/

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.usuario.Usuario#numeroFilasBuscador(java.lang.String)}.
	 */
/*	@Test
	public void testNumeroFilasBuscador() {
	}*/

}
