package com.prueba;
import java.sql.*;                  /*JDBC Classes              */
import java.sql.Connection;
import java.sql.Statement;

import com.mysql.jdbc.*;       		/*MySQL JDBC classes   */
import org.w3c.dom.*;               /*W3C Interfaces            */
import org.apache.xerces.dom.*;     /*Xerces DOM Classes        */
import org.apache.xml.serialize.*;  /*Xerces serializer         */
import java.io.*;                   /*Java io classes for file reading/writing*/

public class CD {
	  public static final String JDBCURL="jdbc:mysql://localhost/cdcol?user=curso&password=curso";
	  public static final String JDBCDRIVER="com.mysql.jdbc.Driver";
	  public static final String OUTPUTFILE="/cd.xml";
	  public StringBuffer sql = new StringBuffer();
	  
	  public CD(){
		  sql.append("SELECT ");
		  sql.append("  id,  ");
		  sql.append("  titulo,  ");
		  sql.append(" interprete, ");
		  sql.append(" anyo   ");		
		  sql.append(" FROM cds ");
		  sql.append(" order by titulo ");
	  }
	  public Document devuelveCD(){
		  Document xmlDoc= null;
		  try{
			  ResultSet cdRS= devuelveCDRS();
			  
			  File fichero = new File(OUTPUTFILE);
			  
			  xmlDoc=creaCDXML(cdRS);
			  
			  printDOM(xmlDoc,fichero);
			  cdRS.close();
		  }catch(Exception e){System.out.println(e.toString());}
		  return xmlDoc;
	  }
	  	private ResultSet devuelveCDRS(){
	  		ResultSet cdRS= null;
	  		try{
	  			Class.forName(JDBCDRIVER).newInstance();
	  			Connection con= DriverManager.getConnection(JDBCURL);
	  			
	  			Statement stm= con.createStatement();
	  			cdRS = stm.executeQuery(sql.toString());
	  		}catch(Exception e){
	  			System.out.println("Error en la obtenci—n de datos:"+ e.toString());
	  		}
	  		return cdRS;
	  	}
	  	/*
	  	 
	  	 	<CDS>
				<CD id="valor" fecha="2008">
					<TITULO></TITULO>
					<INTERPRETE></INTERPRETE>
				</CD>
				....
			</CDS>
	  	 */
	  	private Document creaCDXML(ResultSet cdRS){
	  		Document xmlDoc = new DocumentImpl();
	  			Element documentRoot = xmlDoc.createElement("CDS");
	  		xmlDoc.appendChild(documentRoot);
	  		try{
	  			while (cdRS.next()){
	  				Element documentCD= xmlDoc.createElement("CD");
	  				documentCD.setAttribute("id", cdRS.getString("id"));
	  				documentCD.setAttribute("fecha", new Integer(cdRS.getInt("anyo")).toString());
	  				
	  				documentCD.appendChild(creaDBElement(xmlDoc,"TITULO",cdRS.getString("titulo")));
	  				documentCD.appendChild(creaDBElement(xmlDoc,"INTERPRETE",cdRS.getString("interprete")));
	  				
	  				documentRoot.appendChild(documentCD);
	  				
	  			}
	  		}catch(Exception e){System.out.println("Error en la creaci—n XML");}
	  		return xmlDoc;
	  	}
	  		private static Element creaDBElement(Document xmlDoc, String nombreElemento, String valorElemento)
	  			throws Exception{
	  			
	  			Element item = xmlDoc.createElement(nombreElemento);
	  			item.appendChild(xmlDoc.createTextNode(valorElemento));
	  			return item;
	  		}
	  	private static void printDOM(Document xmlDoc, File fichero) throws Exception{
	  		OutputFormat outputFormat = new OutputFormat("XML","UTF-8",true);
	  		FileWriter fileWriter = new FileWriter(fichero);
	  		
	  		XMLSerializer xmlSerializer = new XMLSerializer(fileWriter, outputFormat);
	  		xmlSerializer.asDOMSerializer();
	  		xmlSerializer.serialize(xmlDoc.getDocumentElement());
	  	}
	public static void main(String arg[]){
		CD cd = new CD();
		cd.devuelveCD();
	}
}
