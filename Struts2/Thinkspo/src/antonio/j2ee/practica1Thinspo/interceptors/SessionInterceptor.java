package antonio.j2ee.practica1Thinspo.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Comprueba que hay un usuario logado en la session
 * En caso negativo devuelve el result=autenticarse que provoca la carga de una pagina de error indicando tal hecho
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor
 */
 
public class SessionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 7703254356560327778L;

	@Override
	public String intercept(ActionInvocation action) throws Exception {
		 Map<String,Object>session=action.getInvocationContext().getSession();
		 //Miro si en la session esta la clave user con valor true que indica que hay usuario autenticado
		 if (session.get("user")!=null && ((Boolean)session.get("user"))){
			 System.out.println("Hay usuario logado "+session.get("user"));
			 return action.invoke();
		 }else {//usuario no logado,enviar a pagina de login
			 System.out.println("Hay usuario logado");
			 return "autenticarse";
		 }	 
	}

}
