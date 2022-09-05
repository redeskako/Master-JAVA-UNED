package uned.java2016.apitwitter.clinical.crawler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import uned.java2016.apitwitter.clinical.crawler.model.ClinicalStudy;
import uned.java2016.apitwitter.clinical.crawler.model.Intervention;
import uned.java2016.apitwitter.clinical.crawler.model.ResponsibleParty;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * @author Alberto Gomez Gonzalez
 *	Clase encargada de parsear los ficheros XML  a objetos de tipo Clinical Study 
 */
public class XMLParser {
	/**
	 * Clase  que recibe un conjunto de ruta de ficheros XML y que los procesa uno a no para 
	 * ir construyendo objetos ClinicalStudy.
	 * 
	 * @param files es un ArraylList con los nombres de los ficheros que han sido descargados.
	 * @return Un array de objetos ClinicalStudy
	 */
	public ClinicalStudy[] marshalClinicaStudies(ArrayList<String> files) {
		ClinicalStudy[] ret = null;
		ArrayList<ClinicalStudy> listaClinical = new ArrayList<ClinicalStudy>();

		// Declaramos el Iterador e imprimimos los Elementos del ArrayList
		Iterator<String> filesIterator = files.iterator();
		while (filesIterator.hasNext()) {
			String ruta = filesIterator.next();
			ClinicalStudy cs = readXML(ruta);
			listaClinical.add(cs);
		}

		int size = -1;
		if (listaClinical != null && (size = listaClinical.size()) > 0) {
			ret = new ClinicalStudy[size];
			for (int i = 0; i < size; i++)
				ret[i] = listaClinical.get(i);
		}
		return ret;
	}

	/**
	 * Clase realiza el parseao del archivo convirtiendolo en objeto. Es el motor del crawler y trabaja 
	 * convirtiendo el objeto XML en un arbol DOM el cual recorre por cada una de sus ramas y extrae de 
	 * ellas las el valor de nodos solicitados
	 *   
	 * @param file Ruta del fichero a procesar
	 * @return Objeto construido a partir de la ruta enviada como paramentro
	 */
	public ClinicalStudy readXML(String file) {
		ClinicalStudy cs = new ClinicalStudy();
		try {

			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node instanceof Element) {
					String content = node.getLastChild().getTextContent().trim().replace("'", " ");

					switch (node.getNodeName()) {
					case "id_info":
						NodeList IDInfoNodes = node.getChildNodes();
						for (int j = 0; j < IDInfoNodes.getLength(); j++) {
							Node nodeID = IDInfoNodes.item(j);
							switch (nodeID.getNodeName()) {
							case "nct_id":
								cs.setNctId(nodeID.getTextContent());
								nodeID.getTextContent();
								break;
							}
						}
					case "brief_title":

						cs.setBriefTitle(content);
						break;
					case "official_title":
						cs.setOfficialTitle(content);
						break;
					case "brief_summary":
						NodeList briefSummaryNodes = node.getChildNodes();
						for (int j = 0; j < briefSummaryNodes.getLength(); j++) {
							Node nodeTexto = briefSummaryNodes.item(j);
							switch (nodeTexto.getNodeName()) {
							case "textblock":
								cs.setBriefSummary(nodeTexto.getTextContent().replace("'", " "));
								break;
							}
						}
						break;
					case "detailed_description":
						NodeList detailedDescriptionNodes = node.getChildNodes();
						for (int j = 0; j < detailedDescriptionNodes.getLength(); j++) {
							Node nodeTexto = detailedDescriptionNodes.item(j);
							switch (nodeTexto.getNodeName()) {
							case "textblock":
								cs.setDetailedDescription(nodeTexto.getTextContent().replace("'", " "));
								break;
							}
						}
					case "study_design":
						cs.setStudyDesign(content);
						break;
					case "primary_outcome":
						NodeList primaryOutcomeNodes = node.getChildNodes();
						for (int j = 0; j < primaryOutcomeNodes.getLength(); j++) {
							Node nodePO = primaryOutcomeNodes.item(j);
							switch (nodePO.getNodeName()) {
							case "measure":
								cs.setPrimaryOutcomeMeasure(nodePO.getTextContent().replace("'", " "));
								break;
							}
						}
					case "overall_status":
						cs.setOverallStatus(content);
						break;
					case "verification_date":
						// cs.setVerificationDate(content);
						SimpleDateFormat formatVerification = new SimpleDateFormat("MMMM yyyy", Locale.US);
						Date fechaVerification;
						try {
							fechaVerification = formatVerification.parse(content);
							cs.setVerificationDate(fechaVerification);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "lastchanged_date":
						// cs.setLastChangedDate(content);
						SimpleDateFormat formatLast = new SimpleDateFormat("MMMMM d, yyyy", Locale.US);
						Date fechaLast;
						try {
							fechaLast = formatLast.parse(content);
							cs.setLastChangedDate(fechaLast);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "firstreceived_date":
						// cs.setFirstReceivedDate(content);
						SimpleDateFormat formatFirst = new SimpleDateFormat("MMMMM d, yyyy", Locale.US);
						Date fechaFirst;
						try {
							fechaFirst = formatFirst.parse(content);
							cs.setFirstReceivedDate(fechaFirst);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "location":
						NodeList locationNodes = node.getChildNodes();
						for (int j = 0; j < locationNodes.getLength(); j++) {
							Node nodeFacility = locationNodes.item(j);
							switch (nodeFacility.getNodeName()) {
							case "facility":
								NodeList facilityNodes = nodeFacility.getChildNodes();
								for (int k = 0; k < facilityNodes.getLength(); k++) {
									Node nodeFacilityName = facilityNodes.item(k);
									switch (nodeFacilityName.getNodeName()) {
									case "name":
										cs.setLocationFacilityFame(nodeFacilityName.getTextContent().replace("'", " "));
										break;
									}
								}
							}
						}
					case "oversight_info":
						NodeList oversightNodes = node.getChildNodes();
						for (int j = 0; j < oversightNodes.getLength(); j++) {
							Node nodeOverSight = oversightNodes.item(j);
							switch (nodeOverSight.getNodeName()) {
							case "authority":
								cs.setOversightInfoAuthority(nodeOverSight.getTextContent().replace("'", " "));
								break;
							}
						}
					case "secondary_outcome":
						NodeList secondaryOutcomeNodes = node.getChildNodes();
						for (int j = 0; j < secondaryOutcomeNodes.getLength(); j++) {
							Node nodeSO = secondaryOutcomeNodes.item(j);
							switch (nodeSO.getNodeName()) {
							case "measure":
								cs.setSecondaryOutcomes(nodeSO.getTextContent().replace("'", " "));
								break;
							}
						}
					case "condition":
						cs.setConditions(content);
						break;
					case "keyword":
						cs.setKeywords(content);
						break;
					case "responsible_party":
						NodeList responsiblePartiesNodes = node.getChildNodes();
						ResponsibleParty rp = new ResponsibleParty();
						for (int j = 0; j < responsiblePartiesNodes.getLength(); j++) {
							Node nodeRP = responsiblePartiesNodes.item(j);
							switch (nodeRP.getNodeName()) {
							case "responsible_party_type":
								rp.setResponsiblePartyType(nodeRP.getTextContent().replace("'", " "));
								break;
							case "investigator_affiliation":
								rp.setInvestigatorAffilation(nodeRP.getTextContent().replace("'", " "));
								break;
							case "investigator_full_name":
								rp.setInvestigatorFullName(nodeRP.getTextContent().replace("'", " "));
								break;
							case "investigator_title":
								rp.setInvestigatorTitle(nodeRP.getTextContent().replace("'", " "));
								break;
							}
						}
						cs.setResponsibleParty(rp);
						break;
					case "intervention":
						NodeList interventionNodes = node.getChildNodes();
						Intervention intervention = new Intervention();
						for (int j = 0; j < interventionNodes.getLength(); j++) {
							Node nodeIntervention = interventionNodes.item(j);
							switch (nodeIntervention.getNodeName()) {
							case "intervention_type":
								intervention.setType(nodeIntervention.getTextContent().replace("'", " "));
								break;
							case "intervention_name":
								intervention.setName(nodeIntervention.getTextContent().replace("'", " "));
								break;
							}
						}
						cs.setIntervention(intervention);
						break;

					/*
					  case "clinical_results":
						NodeList clinicalResultNodes = node.getChildNodes();
						for (int j = 0; j < clinicalResultNodes.getLength(); j++) {
							Node nodeReportedEvent = clinicalResultNodes.item(j);
							switch (nodeReportedEvent.getNodeName()) {
							case "reported_events":
								NodeList reportedEventNodes = nodeReportedEvent.getChildNodes();
								for (int k = 0; k < reportedEventNodes.getLength(); k++) {
									Node nodeSeriousEvent = reportedEventNodes.item(k);
									switch (nodeSeriousEvent.getNodeName()) {
									case "serious_events":
										NodeList SeriousEventNodes = nodeSeriousEvent.getChildNodes();
										for (int l = 0; l < SeriousEventNodes.getLength(); l++) {
											Node nodeCategoryList = SeriousEventNodes.item(l);
											switch (nodeCategoryList.getNodeName()) {
											case "category_list":
												NodeList categoryListNodes = nodeCategoryList.getChildNodes();
												for (int m = 0; m < categoryListNodes.getLength(); m++) {
													Node nodeCategory = categoryListNodes.item(m);
													switch (nodeCategory.getNodeName()) {
													case "category":
														NodeList categoryNodes = nodeCategory.getChildNodes();
														for (int n = 0; n < categoryNodes.getLength(); n++) {
															Node nodeTitle = categoryNodes.item(m);
															switch (nodeTitle.getNodeName()) {
															case "title":
																cs.setSeriousEvent(nodeTitle.getTextContent());
																// System.out.println(nodeTitle.getTextContent());
																// break;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}*/

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;

	}

}