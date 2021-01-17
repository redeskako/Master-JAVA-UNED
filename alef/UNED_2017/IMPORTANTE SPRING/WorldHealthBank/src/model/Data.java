package model;

public class Data {
	
    private String indicador_code;
    private String country_code;
    private int year;
    private float percentage;
    
    
    
	public Data() {
		super();
	}



	public Data(String indicador_code, String country_code, int year, float percentage) {
		super();
		this.indicador_code = indicador_code;
		this.country_code = country_code;
		this.year = year;
		this.percentage = percentage;
	}



	public String getIndicador_code() {
		return indicador_code;
	}



	public void setIndicador_code(String indicador_code) {
		this.indicador_code = indicador_code;
	}



	public String getCountry_code() {
		return country_code;
	}



	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public float getPercentage() {
		return percentage;
	}



	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}



	@Override
	public String toString() {
		return "Data [indicador_code=" + indicador_code + ", country_code=" + country_code + ", year=" + year
				+ ", percentage=" + percentage + "]";
	}

	
       
}
