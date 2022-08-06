package com.uned.worldhealthbank.migracion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Data_Old_Id  implements Serializable {
	
	@Column(name="INDICADOR_CODE")
	private String indicadorCode;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="YEAR")
	private int year;

	public Data_Old_Id() {
	}

	public Data_Old_Id(String indicadorCode, String countryCode, int year) {
		this.indicadorCode = indicadorCode;
		this.countryCode = countryCode;
		this.year = year;
	}

	public Data_Old_Id(Data_Old_Id data_Old_Id) {
		this.indicadorCode = data_Old_Id.getIndicadorCode();
		this.countryCode = data_Old_Id.getCountryCode();
		this.year = data_Old_Id.getYear();
	}

	public String getIndicadorCode() {
		return indicadorCode;
	}

	public void setIndicadorCode(String indicadorCode) {
		this.indicadorCode = indicadorCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Data_Old_Id [indicadorCode=" + indicadorCode + ", countryCode=" + countryCode + ", year=" + year + "]";
	}

	

	
	

}
