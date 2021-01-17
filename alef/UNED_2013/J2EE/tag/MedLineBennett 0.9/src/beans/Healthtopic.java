package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/*
public class Healthtopic implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String healthTopic;
	
	private String language;

	private Date dateCreated;

	private String url;
	
	private String summary;
	
	private String primaryInst;

	private String primaryInstURL;

	private List<Alsocalled> alsocalleds;

	private List<Group> groups;

	private List<Languagemappedtopic> languagemappedtopics;

	private List<Meshheading> meshheadings;

	private List<Otherlanguage> otherlanguages;

	private List<Relatedtopic> relatedtopics;

	private List<Seereference> seereferences;

	private List<Site> sites;
	
	private int filasHT; //Necesaria en la búsqueda en BBDD para saber el número de registros.

	public Healthtopic() {
	}
	
	public Healthtopic(int id, String healthTopic, String language,
			Date dateCreated, String url, String summary, String primaryInst,
			String primaryInstURL) {
		this.id = id;
		this.healthTopic = healthTopic;
		this.language = language;
		this.dateCreated = dateCreated;
		this.url = url;
		this.summary = summary;
		this.primaryInst = primaryInst;
		this.primaryInstURL = primaryInstURL;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getHealthTopic() {
		return this.healthTopic;
	}

	public void setHealthTopic(String healthTopic) {
		this.healthTopic = healthTopic;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPrimaryInst() {
		return this.primaryInst;
	}

	public void setPrimaryInst(String primaryInst) {
		this.primaryInst = primaryInst;
	}

	public String getPrimaryInstURL() {
		return this.primaryInstURL;
	}

	public void setPrimaryInstURL(String primaryInstURL) {
		this.primaryInstURL = primaryInstURL;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Alsocalled> getAlsocalleds() {
		return this.alsocalleds;
	}

	public void setAlsocalleds(List<Alsocalled> alsocalleds) {
		this.alsocalleds = alsocalleds;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Languagemappedtopic> getLanguagemappedtopics() {
		return this.languagemappedtopics;
	}

	public void setLanguagemappedtopics(List<Languagemappedtopic> languagemappedtopics) {
		this.languagemappedtopics = languagemappedtopics;
	}

	public List<Meshheading> getMeshheadings() {
		return this.meshheadings;
	}

	public void setMeshheadings(List<Meshheading> meshheadings) {
		this.meshheadings = meshheadings;
	}

	public List<Otherlanguage> getOtherlanguages() {
		return this.otherlanguages;
	}

	public void setOtherlanguages(List<Otherlanguage> otherlanguages) {
		this.otherlanguages = otherlanguages;
	}

	public List<Relatedtopic> getRelatedtopics() {
		return this.relatedtopics;
	}

	public void setRelatedtopics(List<Relatedtopic> relatedtopics) {
		this.relatedtopics = relatedtopics;
	}

	public List<Seereference> getSeereferences() {
		return this.seereferences;
	}

	public void setSeereferences(List<Seereference> seereferences) {
		this.seereferences = seereferences;
	}

	public List<Site> getSites() {
		return this.sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public int getFilasHT() {
		return filasHT;
	}

	public void setFilasHT(int filasHT) {
		this.filasHT = filasHT;
	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD Bennett según el
	//criterio de búsqueda palabra.
	//La búsqueda se realiza en todos los campos de la BBDD.
	public ArrayList<Healthtopic> listadoHealthTopic(String palabra, int offset, int noOfRecords) throws Exception{
		ArrayList<Healthtopic> miLista = new ArrayList<Healthtopic>();
		BBDD miBd = new BBDD();
		int num_filas;
		
		miBd.abrirConexion(); 
		//Utilizamos PrepareStatement para una mayor seguridad.
		miLista=miBd.listadoHealthtopic("Select * from healthtopic where Healthtopic LIKE ? or Language LIKE ? or URL LIKE ? or Summary LIKE ? or PrimaryInst LIKE ? or PrimaryInstURL LIKE ? limit " + offset + ", " + noOfRecords, palabra);
		num_filas = miBd.getFilas();
		miBd.cerrarConexion();
		//Recorremos la lista que acabamos de generar para ir rellenando lo que nos falta.
		if (!miLista.isEmpty())
		{
			Iterator<Healthtopic> i = miLista.iterator();
			Healthtopic elemento;
			Alsocalled ac = new Alsocalled();
			Group group = new Group();
			Languagemappedtopic langu = new Languagemappedtopic();
			//Para cada uno de los HealthTopic que hemos conseguido, debemos rellenarle
			//su lista AlsoCalled.
			while(i.hasNext()){
				elemento = i.next();
				//Asignamos los alsocalled
				elemento.setAlsocalleds(ac.listadoAlsoCalled(elemento.getId()));
				//Le asignamos los groups
				elemento.setGroups(group.listadoGroup(elemento.getId()));
				//Le asignamos los languagemappedtopic
				elemento.setLanguagemappedtopics(langu.listadoMapped(elemento.getId()));
				//Le asignamos el número de elementos de la búsqueda.
				elemento.setFilasHT(num_filas);
			}
		}
		return miLista;		
	}
	*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Healthtopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param offset: int -> Desplazamiento sobre el resultado de la búsqueda de la 
	 * 						  primera fila que se va a mostrar en la página 
	 *                     correspondiente de la vista.
	 * @param noOfRecords: int -> Número de filas que se van a mostrar en la página
	 *                      correspondiente de la vista.
	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra que se busca.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
/*
	public ArrayList<Healthtopic> listadoHealthTopicCampo(String campo, String palabra, int offset, int noOfRecords) throws Exception{
		ArrayList<Healthtopic> miLista = new ArrayList<Healthtopic>();
		BBDD miBd = new BBDD();
		int num_filas;
		
		
		//Utilizamos PrepareStatement para una mayor seguridad.
		if (!palabra.equals("")){
			miBd.abrirConexion(); 
			if (campo.equals("*")){
				miLista=miBd.listadoHealthtopic("Select * from healthtopic where Healthtopic LIKE ? or Language LIKE ? or URL LIKE ? or Summary LIKE ? or PrimaryInst LIKE ? or PrimaryInstURL LIKE ? limit " + offset + ", " + noOfRecords, palabra);
			}else{
				miLista=miBd.listadoHealthtopicCampo("Select SQL_CALC_FOUND_ROWS * from healthtopic where " + campo + " LIKE ? limit " + offset + ", " + noOfRecords, palabra);
			}
			num_filas = miBd.getFilas();
			miBd.cerrarConexion();
			//Recorremos la lista que acabamos de generar para ir rellenando lo que nos falta.
			if (!miLista.isEmpty())
			{
				Iterator<Healthtopic> i = miLista.iterator();
				Healthtopic elemento;
				Alsocalled ac = new Alsocalled();
				Group group = new Group();
				Languagemappedtopic langu = new Languagemappedtopic();
				//Para cada uno de los HealthTopic que hemos conseguido, debemos rellenarle
				//su lista AlsoCalled.
				while(i.hasNext()){
					elemento = i.next();
					//Asignamos los alsocalled
					elemento.setAlsocalleds(ac.listadoAlsoCalled(elemento.getId()));
					//Le asignamos los groups
					elemento.setGroups(group.listadoGroup(elemento.getId()));
					//Le asignamos los languagemappedtopic
					elemento.setLanguagemappedtopics(langu.listadoMapped(elemento.getId()));
					//Le asignamos el número de elementos de la búsqueda.
					elemento.setFilasHT(num_filas);
				}
			}
		}
		return miLista;
	}
	
	//Método sobrecargado que devuelve una lista con los resultados de la búsqueda en la BBDD Bennett según el
	//criterio de búsqueda palabr en el campo indicado.
	public ArrayList<Healthtopic> listadoHealthTopicCampo(String campo, int id, int offset, int noOfRecords) throws Exception{
		ArrayList<Healthtopic> miLista = new ArrayList<Healthtopic>();
		BBDD miBd = new BBDD();
		int num_filas;

		miBd.abrirConexion(); 
		//Utilizamos PrepareStatement para una mayor seguridad.
		miLista=miBd.listadoHealthtopicCampo("Select SQL_CALC_FOUND_ROWS * from healthtopic where " + campo + " = ? limit " + offset + ", " + noOfRecords, id);
		num_filas = miBd.getFilas();
		miBd.cerrarConexion();
		//Recorremos la lista que acabamos de generar para ir rellenando lo que nos falta.
		if (!miLista.isEmpty())
		{
			Iterator<Healthtopic> i = miLista.iterator();
			Healthtopic elemento;
			Alsocalled ac = new Alsocalled();
			Group group = new Group();
			Languagemappedtopic langu = new Languagemappedtopic();
			//Para cada uno de los HealthTopic que hemos conseguido, debemos rellenarle
			//su lista AlsoCalled.
			while(i.hasNext()){
				elemento = i.next();
				//Asignamos los alsocalled
				elemento.setAlsocalleds(ac.listadoAlsoCalled(elemento.getId()));
				//Le asignamos los groups
				elemento.setGroups(group.listadoGroup(elemento.getId()));
				//Le asignamos los languagemappedtopic
				elemento.setLanguagemappedtopics(langu.listadoMapped(elemento.getId()));
				//Le asignamos el número de elementos de la búsqueda.
				elemento.setFilasHT(num_filas);
			}
		}
		return miLista;		
	}
	
	//Método sobrecargado que devuelve una lista con los resultados de la búsqueda en la BBDD Bennett según el
	//criterio de búsqueda palabr en el campo indicado.
	public ArrayList<Healthtopic> listadoHealthTopicCampo(String campo, java.sql.Date date, int offset, int noOfRecords) throws Exception{
		ArrayList<Healthtopic> miLista = new ArrayList<Healthtopic>();
		BBDD miBd = new BBDD();
		int num_filas;
		
		miBd.abrirConexion(); 
		//Utilizamos PrepareStatement para una mayor seguridad.
		miLista=miBd.listadoHealthtopicCampo("Select SQL_CALC_FOUND_ROWS * from healthtopic where " + campo + " = ? limit " + offset + ", " + noOfRecords, date);
		num_filas = miBd.getFilas();
		miBd.cerrarConexion();
		//Recorremos la lista que acabamos de generar para ir rellenando lo que nos falta.
		if (!miLista.isEmpty())
		{
			Iterator<Healthtopic> i = miLista.iterator();
			Healthtopic elemento;
			Alsocalled ac = new Alsocalled();
			Group group = new Group();
			Languagemappedtopic langu = new Languagemappedtopic();
			//Para cada uno de los HealthTopic que hemos conseguido, debemos rellenarle
			//su lista AlsoCalled.
			while(i.hasNext()){
				elemento = i.next();
				//Asignamos los alsocalled
				elemento.setAlsocalleds(ac.listadoAlsoCalled(elemento.getId()));
				//Le asignamos los groups
				elemento.setGroups(group.listadoGroup(elemento.getId()));
				//Le asignamos los languagemappedtopic
				elemento.setLanguagemappedtopics(langu.listadoMapped(elemento.getId()));
				//Le asignamos el número de elementos de la búsqueda.
				elemento.setFilasHT(num_filas);
			}
		}
		return miLista;		
	}
}
*/
