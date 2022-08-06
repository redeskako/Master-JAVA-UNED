package model;

public class HealthIndicator {
	
    private String indicador_code;
    private String indicador_name;
    private String source_note;
    private String source_organization;
    
    
	public HealthIndicator() {
		super();
	}


	public HealthIndicator(String indicador_code, String indicador_name, String source_note,
			String source_organization) {
		super();
		this.indicador_code = indicador_code;
		this.indicador_name = indicador_name;
		this.source_note = source_note;
		this.source_organization = source_organization;
	}


	public String getIndicator_code() {
		return indicador_code;
	}


	public void setIndicator_code(String indicador_code) {
		this.indicador_code = indicador_code;
	}


	public String getIndicator_name() {
		return indicador_name;
	}


	public void setIndicator_name(String indicador_name) {
		this.indicador_name = indicador_name;
	}


	public String getSource_note() {
		return source_note;
	}


	public void setSource_note(String source_note) {
		this.source_note = source_note;
	}


	public String getSource_organization() {
		return source_organization;
	}


	public void setSource_organization(String source_organization) {
		this.source_organization = source_organization;
	}


	@Override
	public String toString() {
		return "health_indicator [indicador_code=" + indicador_code + ", indicador_name=" + indicador_name
				+ ", source_note=" + source_note + ", source_organization=" + source_organization + "]";
	}



	

	
       
}
