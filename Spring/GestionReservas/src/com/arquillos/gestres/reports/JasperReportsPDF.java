/*
 * 
 * 
 */

package com.arquillos.gestres.reports;

import java.io.ByteArrayOutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import java.util.HashMap;
import java.util.List;
import org.apache.wicket.markup.html.DynamicWebResource;

@SuppressWarnings("serial")
public abstract class JasperReportsPDF extends DynamicWebResource {
	private String pathListado;
	HashMap<String, Object> parametros;

	public JasperReportsPDF(String pathListado) {
		this.pathListado = pathListado;
	}

	@SuppressWarnings("rawtypes")
	public void setJasperPrint(List infoListado, HashMap parametros) {
	}

	public abstract JRBeanCollectionDataSource getJRBeanCollectionDataSource();

	@Override
	protected ResourceState getResourceState() {
		return new ResourceState() {
      public String getContentType() {
        return "application/pdf";
      }
      public byte[] getData() {
      	JasperPrint print = null;
    		try {
    		  // Cargamos el listado desde el XML en al objeto JasperDesign
    		  JasperDesign design = JRXmlLoader.load(pathListado);
    		  // Compilamos el listado en memoria almacenándolo en un objeto JasperReport
    			JasperReport listado = JasperCompileManager.compileReport(design);
    			// Rellenamos el listado mediante un JRBeanCollectionDataSource
    			print =
    				JasperFillManager.
    					fillReport(listado, parametros, getJRBeanCollectionDataSource());		
    		} catch (JRException jre) {
    				jre.printStackTrace();
    		}	

      	JRPdfExporter exporter = new JRPdfExporter();
      	ByteArrayOutputStream os = new ByteArrayOutputStream();
      	exporter.setExporterInput(new SimpleExporterInput(print));
      	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
      	try {
      		exporter.exportReport();
				}
      	catch (JRException e) {
      		e.printStackTrace();
				}
	    	return os.toByteArray();
	    }
	  };
  }
}