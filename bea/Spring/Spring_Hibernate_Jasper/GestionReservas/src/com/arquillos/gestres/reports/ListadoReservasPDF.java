/*
 * 
 * 
 */

package com.arquillos.gestres.reports;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.web.Autorizado;
import java.util.HashMap;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.injection.web.InjectorHolder;

@SuppressWarnings("serial")
@Autorizado
public class ListadoReservasPDF extends JasperReportsPDF {
	@SpringBean	
	private ReservaDao reservaDao;

	public ListadoReservasPDF(String pathListado) {
		super(pathListado);
	  // inyección dependencias
		InjectorHolder.getInjector().inject(this); 				
	  // Parámetros para el listado
	  parametros = new HashMap<String, Object>();
	  parametros.put("Titulo", "Listado Reservas");
	}

	@Override
	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
	// Devuelve todos los registros de la tabla recursos
	  return new JRBeanCollectionDataSource(reservaDao.getReservas());				
	}  			
}