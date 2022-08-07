package uned.java2016.apitwitter.clinical.crawler.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import uned.java2016.apitwitter.clinical.crawler.util.Config;

/**
 * @author Alberto Gomez Gonzalez
 *	Esta clase contiene los metodos necesarios para que el crawler se conecte con la API,
 *	descargue el fichero resultante de la consulta y posteriormente los descomprima y genere un
 *  ArrayList con la direccion absoluta de los archivos XML
 */
public class ClinicalAPIClient {
	protected ArrayList<String> listaXML;
	protected static final Config config = Config.getInstance();
	protected String directorio_unzip = config.getProperty("directory_unzip", "unzip");
	protected String server = config.getProperty("result_file", "search_result.zip");

	public void setListaXML(ArrayList<String> listaXML) {
		this.listaXML = listaXML;
	}

	public ArrayList<String> getListaXML() {
		return this.listaXML;
	}

	/**
	 * Este metodo realiza la llamada a la API de Clinical Trials a partir de la direccion 
	 * pasada. Mediante un objeto InputStream se gestiona la descarga del archivo, 
	 * almacenandose en disco. Una vez finalizada la descarga se lanza el metodo unZip
	 *
	 * @param URL Direccion de llamada a la API
	 */
	public void callApi(String URL) {
		try {
			URL url = new URL(URL);
			URLConnection conn = url.openConnection();
			String filename = server;
			byte[] buffer = new byte[8 * 1024];

			InputStream input = conn.getInputStream();
			try {
				OutputStream output = new FileOutputStream(filename);
				try {
					int bytesRead;
					while ((bytesRead = input.read(buffer)) != -1) {
						output.write(buffer, 0, bytesRead);
					}
				} finally {
					output.close();
				}
			} finally {
				input.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		unZip();
	}

	/**
	 * Metodo que se encarga de descomprimir el fichero previamente descargado a un directorio
	 * previamente designado en el archivo de configuracion. 
	 */
	public void unZip() {

		byte[] buffer = new byte[1024];
		listaXML = new ArrayList<String>()

		;
		try {
			File folder = new File(directorio_unzip);
			if (!folder.exists()) {
				folder.mkdir();
			}

			ZipInputStream zis = new ZipInputStream(new FileInputStream(server));
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(directorio_unzip + File.separator + fileName);

				//System.out.println("Archivo unzip : " + newFile.getAbsoluteFile());
				listaXML.add(newFile.getAbsolutePath());

				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
