/*
 * 
 * 
 */

package com.arquillos.gestres.web;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */

import java.io.ByteArrayOutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.apache.wicket.markup.html.DynamicWebResource;

@SuppressWarnings("serial")
public class JasperReportsResource extends DynamicWebResource {
	/**
	 * 
	 */
	private JasperPrint print;
	public JasperReportsResource(JasperPrint print) {
		this.print = print;
	}

	@Override
	protected ResourceState getResourceState() {
		return new ResourceState() {
      public String getContentType() {
        return "application/pdf";
      }
      public byte[] getData() {
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