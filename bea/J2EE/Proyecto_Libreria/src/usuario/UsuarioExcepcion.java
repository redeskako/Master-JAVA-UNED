package usuario;


public class UsuarioExcepcion extends RuntimeException{
	public UsuarioExcepcion(){
		super("Usuario Excepcion");
	}
	public UsuarioExcepcion(String err){
		super("Usuario Excepcion:\n\t"+err);
	}
}