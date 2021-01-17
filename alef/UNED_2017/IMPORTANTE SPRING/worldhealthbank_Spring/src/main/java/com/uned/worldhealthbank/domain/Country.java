package com.uned.worldhealthbank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "COUNTRY")
public class Country {
	
	
		@Id
	    @Type(type = "long")
	    @Column(name = "ID_COUNTRY", nullable = false)
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Type(type = "string")
	    @Column(name = "COUNTRY_CODE", length = 3, nullable = false)
	    private String countryCode;
	    
	    @Type(type = "string")
	    @Column(name = "COUNTRY_NAME", length = 50, nullable = false)
	    private String countryName;

		public Country() {
			
		}

		

		public Country(Long id, String countrycode, String countryName) {
			super();
			this.id = id;
			this.countryCode = countrycode;
			this.countryName = countryName;
		}

		public Country(Country country) {
			super();
			this.id = country.id;
			this.countryCode = country.countryCode;
			this.countryName = country.countryName;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
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
			return "Country [id=" + id + ", countryCode=" + countryCode + ", countryName=" + countryName + "]";
		}

		
	    
	

}
