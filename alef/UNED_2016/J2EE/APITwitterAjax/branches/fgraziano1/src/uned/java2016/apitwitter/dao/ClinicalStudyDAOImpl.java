package uned.java2016.apitwitter.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ClinicalStudyDAOImpl implements ClinicalStudyDAO {

	private Connection conn;
	private Statement stmContar;
	private String errorenPrimero;
	
	public ClinicalStudyDAOImpl(Connection conn){
		
		this.conn = conn;
		
	}

	
	@Override
	//Inserta la información del DTO Clinical Study en las distintas tablas.
	public void insertClinicalStudy (ClinicalStudy clinicalstudy) {
		Statement stm = null;
		int rs = 0;

		//Si no existe el ClinicalStudy lo insertamos en las tablas ClinicalStudy, hashtag_nct, intervention   cuales mas??????? hashtags_tweet, urls_tweet 
		if (!(this.exists(clinicalstudy.getNctId()))){
		
	    //Insertamos en la tabla apiclinicaltrials el Estudio Clinico		    
			try {
        	    stm = this.conn.createStatement();
        	    StringBuilder sql = new StringBuilder("INSERT INTO apiclinicaltrials (nct_id, brief_title, official_title, brief_summary, detailed_description, study_design, primary_outcome_measure, overall_status, verification_date, lastchanged_date, firstreceived_date, location_facility_name, organization, oversight_info_authority)");
        	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	    sql.append("VALUES ( '"+clinicalstudy.getNctId()+"','"+clinicalstudy.getBriefTitle()+"','"+clinicalstudy.getOfficialTitle()+"','"+clinicalstudy.getBriefSummary()+"','"+clinicalstudy.getDetailedDescription()+"','"+clinicalstudy.getStudyDesign()+"','"+clinicalstudy.getPrimaryOutcomeMeasure()+"','"+clinicalstudy.getOverallStatus()+"','"+df.format(clinicalstudy.getVerificationDate()).toString()+"','"+df.format(clinicalstudy.getLastChangedDate())+"','"+df.format(clinicalstudy.getFirstReceivedDate())+"','"+clinicalstudy.getLocationFacilityFame()+"','"+clinicalstudy.getOrganization()+"','"+clinicalstudy.getOversightInfoAuthority()+"' )");
        	    System.out.println(sql);
        	    rs = stm.executeUpdate(sql.toString());
        //Insertamos las interventions en la tabla intervention	
        	    Iterator<Intervention> itr = clinicalstudy.getIntervention().iterator();
        	    Intervention inter = null; 
        	    while(itr.hasNext()){
        	        inter = itr.next();	
        	        sql = new StringBuilder("INSERT INTO nct_id_interventions (nct_id, type_0, name_0)");
        	        sql.append("VALUES ( '"+clinicalstudy.getNctId()+"','"+inter.getType()+"','"+inter.getName()+"' )");
        	        System.out.println(sql);
        	        rs = stm.executeUpdate(sql.toString());	 
        	    } 	 
        //Insertamos las keywords.
        	    Iterator<String> itrstring = clinicalstudy.getKeywords().iterator();
        	    while(itrstring.hasNext()){
        	        sql = new StringBuilder("INSERT INTO ntc_id_keyword (nct_id, keyword_0)");
        	        sql.append("VALUES ( '"+clinicalstudy.getNctId()+"','"+itrstring.next()+"' )");
        	        System.out.println(sql);
            	    rs = stm.executeUpdate(sql.toString());	
        	    }
        //Insertamos las secondary outcomes.
        	    itrstring = clinicalstudy.getSecondaryOutcomes().iterator();
        	    while(itrstring.hasNext()){
        	        sql = new StringBuilder("INSERT INTO secondary_outcome_nct (nct_id, measure)");
        	        sql.append("VALUES ( '"+clinicalstudy.getNctId()+"','"+itrstring.next()+"' )");
        	        System.out.println(sql);
            	    rs = stm.executeUpdate(sql.toString());	
        	    }
        //Insertamos las conditions	    
        	    itrstring = clinicalstudy.getConditions().iterator();
        	    while(itrstring.hasNext()){
        	    	String condicion = itrstring.next();
        	    	if ( condicion.length() != 0){
        	            sql = new StringBuilder("INSERT INTO ntc_id_condition (nct_id, condition_0)");
        	            sql.append("VALUES ( '"+clinicalstudy.getNctId()+"','"+condicion+"' )");
        	            System.out.println(sql);
            	        rs = stm.executeUpdate(sql.toString());	
        	    	}
        	    }	
        //Insertamos las Responsible Party en la tabla ntc_id_responsible_party
        	    ResponsibleParty rp = clinicalstudy.getResponsibleParty();
        	    if ( rp != null){
        	        sql = new StringBuilder("INSERT INTO nct_id_responsible_party (nct_id, responsible_party_type, investigator_affiliation, investigator_full_name, investigator_title)");
        	        sql.append("VALUES ( '"+clinicalstudy.getNctId()+"','"+rp.getResponsiblePartyType()+"','"+rp.getInvestigatorAffilation()+"','"+rp.getInvestigatorFullName()+"','"+rp.getInvestigatorTitle()+"' )");
        	        System.out.println(sql);
        	        rs = stm.executeUpdate(sql.toString());
        	    }
		            	
				stm.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		    }
		
		}
	}

	@Override
	//Insertamos ntcid y hashtag en la tabla hashtags_nct
	public void insertNtcIdHashtags(String ntcId, String hashtag){
		Statement stm = null;	
		int rs = 0;
		//Si no existe el ntcId insertamos ntcid y hashtag en la tabla hashtag_nct 
		if (!(this.existsNctHashtag(hashtag,ntcId))&&(this.exists(ntcId))){
			try{
				stm = this.conn.createStatement();
				StringBuilder sql = new StringBuilder("INSERT INTO hashtags_nct (nct_id, hashtag)");
				sql.append("VALUES ( '"+ ntcId +"','"+ hashtag+"' )");
				System.out.println(sql.toString());
				rs = stm.executeUpdate(sql.toString());
				stm.close();
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
				  System.out.println("Estudio "+ntcId+" y hashtag "+hashtag+" ya existen en la tabla.");
			  }
	} 
			
	@Override
	//Devuelve un estudio seleccionado a través de su identificador nctid.
	public ClinicalStudy selectClinicalStudy(String nctId) {
		ClinicalStudy devuelveClinicalStudy = new ClinicalStudy();
		Statement stm = null;
		ResultSet rs = null;
		//Si no existe el nctId devolvemos directamente null. 
		if (this.exists(nctId)){
			devuelveClinicalStudy.setNctId(nctId);
	    //Leemos de la tabla apiclinicals la info del estudio clinico.
		    try {
		    	stm = this.conn.createStatement();
        	    StringBuilder sql = new StringBuilder("SELECT nct_id, brief_title, official_title, brief_summary, detailed_description, study_design, primary_outcome_measure, overall_status, verification_date, lastchanged_date, firstreceived_date, location_facility_name, organization, oversight_info_authority FROM apiclinicaltrials WHERE nct_id='" + nctId + "';");
        		rs = stm.executeQuery(sql.toString());
        		System.out.println("SQL="+sql.toString());
        	    if (rs.next()){
        	    devuelveClinicalStudy.setNctId(rs.getString("nct_id"));
        	    devuelveClinicalStudy.setBriefTitle(rs.getString("brief_title"));
        	    devuelveClinicalStudy.setOfficialTitle(rs.getString("official_title"));
        	    devuelveClinicalStudy.setBriefSummary(rs.getString("brief_summary"));
        	    devuelveClinicalStudy.setDetailedDescription(rs.getString("detailed_description"));
        	    devuelveClinicalStudy.setStudyDesign(rs.getString("study_design"));
        	    devuelveClinicalStudy.setPrimaryOutcomeMeasure(rs.getString("primary_outcome_measure"));
        	    devuelveClinicalStudy.setOverallStatus(rs.getString("overall_status"));
        	    devuelveClinicalStudy.setVerificationDate(rs.getDate("verification_date"));
        	    devuelveClinicalStudy.setLastChangedDate(rs.getDate("lastchanged_date"));
        	    devuelveClinicalStudy.setFirstReceivedDate(rs.getDate("firstreceived_date"));
        	    devuelveClinicalStudy.setLocationFacilityFame(rs.getString("location_facility_name"));
        	    devuelveClinicalStudy.setOrganization(rs.getString("organization"));
        	    devuelveClinicalStudy.setOversightInfoAuthority(rs.getString("oversight_info_authority"));
        	    }
       //Leemos las interventions.
        	    sql = new StringBuilder("SELECT nct_id, type_0, name_0 FROM nct_id_interventions WHERE nct_id='" + nctId + "';");
        	    rs = stm.executeQuery(sql.toString());     
        	    while (rs.next()){
        	    	Intervention intervencion = new Intervention();
        	    	intervencion.setType(rs.getString("type_0"));
        	    	intervencion.setName(rs.getString("name_0"));
        	    	devuelveClinicalStudy.setIntervention(intervencion); 
        	    }
       //Leemos las keywords.
        	    sql = new StringBuilder("SELECT nct_id, keyword_0 FROM ntc_id_keyword WHERE nct_id='" + nctId + "';");
        	    rs = stm.executeQuery(sql.toString());     
        	    while (rs.next()){
        	    	devuelveClinicalStudy.setKeywords(rs.getString("keyword_0")); 
        	    }
        //Leemos las secondary outcomes.
        	    sql = new StringBuilder("SELECT nct_id, measure FROM secondary_outcome_nct WHERE nct_id='" + nctId + "';");
        	    rs = stm.executeQuery(sql.toString());     
        	    while (rs.next()){
        	    	devuelveClinicalStudy.setSecondaryOutcomes(rs.getString("measure")); 
        	    }
        //Leemos las conditions
        	    sql = new StringBuilder("SELECT nct_id, condition_0 FROM ntc_id_condition WHERE nct_id='" + nctId + "';");
        	    rs = stm.executeQuery(sql.toString());     
        	    while (rs.next()){
        	    	devuelveClinicalStudy.setConditions(rs.getString("condition_0")); 
        	    }
        //Leemos las Responsible Party.
        	    ResponsibleParty rp = new ResponsibleParty();
        	    sql = new StringBuilder("SELECT nct_id, responsible_party_type, investigator_affiliation, investigator_full_name, investigator_title FROM nct_id_responsible_party WHERE nct_id='" + nctId + "';");
        	    rs = stm.executeQuery(sql.toString());     
        	    while (rs.next()){
        	    	rp.setResponsiblePartyType(rs.getString("responsible_party_type"));
        	    	rp.setInvestigatorAffilation(rs.getString("investigator_affiliation"));
        	    	rp.setInvestigatorFullName(rs.getString("investigator_full_name"));
        	    	rp.setInvestigatorTitle(rs.getString("investigator_title"));  
        	    	devuelveClinicalStudy.setResponsibleParty(rp); 
        	    }
        	    
        	    stm.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		    }
		    return devuelveClinicalStudy;
		}
		return null;
	}
	
	
	
	@Override
	//Devuelve el número de estudios asociados a un hashtag.
	public int contadorClinicalStudies(String hashtag) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from hashtags_nct where hashtag='" + hashtag + "';");
			if (rs.next()){
				return(rs.getInt(1));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	
	@Override
	//Confirma la existencia de un estudio en base de datos.
	public boolean exists(String nctId) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from apiclinicaltrials where nct_id='" + nctId + "';");
			if (rs.next()){
				if (rs.getInt(1) > 0){return(true);}
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	
	}
	
	@Override
	//Confirna la existencia de un hashtag en relación con los estudios.
	public boolean existsHashtag(String hashtag) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from hashtags_nct where hashtag='" + hashtag + "';");
			if (rs.next()){
				if (rs.getInt(1) > 0){return(true);}
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	//Confirma la relación entre un hashtag y un estudio.
	public boolean existsNctHashtag(String hashtag, String nctid) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from hashtags_nct where hashtag='" + hashtag + "' and nct_id='" + nctid +"';");
			if (rs.next()){
				if (rs.getInt(1) > 0){return(true);}
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	//Confirma la existencia de una intervention.
	public boolean existsNctIntervention(String name, String nctid) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from nct_id_interventions where nct_id='" + nctid + "' and name_0='" + name +"';");
			if (rs.next()){
				if (rs.getInt(1) > 0){return(true);}
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	//Devuelve una estructura de los estudios relacionados con un hashtag.
	public ArrayList<ClinicalStudy> selectClinicalStudies(String hashtag) {
		//Inicializamos el array de estudios.
		ArrayList<ClinicalStudy> losestudios = new ArrayList<ClinicalStudy>();
		//Obtengo los estudios para el hashtag primero obtengo los identificadores de estudios.
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select nct_id from hashtags_nct where hashtag='" + hashtag +"';");
        	//System.out.println("select nct_id from hashtags_nct where hashtag='" + hashtag +"';");
			while (rs.next()){
				losestudios.add(selectClinicalStudy(rs.getString(1)));
		    }
			rs.close();
			stm.close();
			return (losestudios);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	//Devuelve una estructura de los estudios relacionados con un hashtag en una ventana de búsqueda.
	public ArrayList<ClinicalStudy> selectClinicalStudies(String hashtag, int minimo, int maximo) {
		this.errorenPrimero="No";
		int totalRegistros=0;
		try {
			this.stmContar = conn.createStatement();
			ResultSet rsContar;
			String sqlContar="select nct_id from hashtags_nct where hashtag='" + hashtag+"'";
			rsContar=stmContar.executeQuery(sqlContar);
			while (rsContar.next()){
				totalRegistros++;
			}
			rsContar.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(minimo>totalRegistros){
			minimo=1;
			this.errorenPrimero="Si";
		}
		else{
			minimo=minimo;
		}
		
		
		int min=minimo-1;
		//Inicializamos el array de estudios.
		ArrayList<ClinicalStudy> losestudios = new ArrayList<ClinicalStudy>();
		//Obtengo los estudios para el hashtag primero obtengo los identificadores de estudios.
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
		    rs = stm.executeQuery("select nct_id from hashtags_nct where hashtag='" + hashtag +"' LIMIT "+min+","+maximo+";");
			while (rs.next()){
				losestudios.add(selectClinicalStudy(rs.getString(1)));
			}
			rs.close();
			stm.close();
			return (losestudios);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** Métodos de acceso I/O a los campos de la clase */
	/** El método getErrorenPrimero lee el campo errorenPrimero
	 * @ in 
	 * @ out String con errorenPrimero
	 * @ error   
	 */
	public String getErrorenPrimero(){
		return this.errorenPrimero;
	}


	@Override
	public ArrayList<String> getNCTsByHashtag(String hashtag, String cadena) {
		//Inicializamos el array de strings.
		ArrayList<String> losncts = new ArrayList<String>();
		//Obtengo los ncts que estando relacionados con el hashtag seleccionado, contienen la cadena.
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select nct_id from hashtags_nct where hashtag='" + hashtag +"' and nct_id like '" +cadena +"%';");
        	//System.out.println("select nct_id from hashtags_nct where hashtag='" + hashtag +"';");
			while (rs.next()){
				losncts.add(rs.getString(1));
		    }
			rs.close();
			stm.close();
			return (losncts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}
}
