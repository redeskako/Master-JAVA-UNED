/*
 * 
 * 
 */

package com.arquillos.gestres.reports;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.web.Autorizado;
import java.util.HashMap;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

@SuppressWarnings("serial")
@Autorizado
public class ListadoUsuariosPDF extends JasperReportsPDF {
	@SpringBean	
	private UsuarioDao usuarioDao;	

	public ListadoUsuariosPDF(String pathListado) {
		super(pathListado);
	  // inyección dependencias
		InjectorHolder.getInjector().inject(this); 		
	  // Parámetros para el listado
	  parametros = new HashMap<String, Object>();
	  parametros.put("Titulo", "Listado Usuarios");
	}

	@Override
	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
	// Devuelve todos los registros de la tabla recursos
	  return new JRBeanCollectionDataSource(usuarioDao.getUsuarios());				
	}  		
}