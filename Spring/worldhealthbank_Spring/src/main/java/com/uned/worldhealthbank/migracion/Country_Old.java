package com.uned.worldhealthbank.migracion;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY_OLD")
public class Country_Old {
	

	

	@Id
	@Type(type = "string")
	@Column(name = "COUNTRY_CODE", length = 3, nullable = false)
	private String countryCode;
	
	@Type(type = "string")
	@Column(name = "COUNTRY_NAME", length = 50, nullable = false)
	private String countryName;

	
	
	public Country_Old() {
	}

	public Country_Old(Country_Old country_Old) {
		this.countryCode = country_Old.getCountryCode();
		this.countryName = country_Old.getCountryName();
	}
	
	public Country_Old(String countryCode, String countryName) {
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country_Old [countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}

	
	
	
	
	
}

