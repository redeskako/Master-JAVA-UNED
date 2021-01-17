package com.uned.worldhealthbank.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "DATA")
public class Data {
	
  
	@Id
	@Column(name = "ID_DATA", nullable = false)
	@Type(type = "long")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idData;
	
    @ManyToOne
    @JoinColumn(name="ID_COUNTRY")
    private Country country;
    
    @ManyToOne
    @JoinColumn(name="ID_HEALTH")
    private Health health;
    
    
    @Type(type = "integer")
    @Column(name = "YEAR")
    private int year;
    
    @Type(type = "float")
    @Column(name = "PERCENTAGE")
    private float percentage;

	public Data() {
		
	}

	public Data(Long idData, Country country, Health health, int year, float percentage) {
		super();
		this.idData = idData;
		this.country = country;
		this.health = health;
		this.year = year;
		this.percentage = percentage;
	}

	public Data(Data data) {
		this.idData = data.idData;
		this.country = data.country;
		this.health = data.health;
		this.year = data.year;
		this.percentage = data.percentage;
	}

	public Long getIdData() {
		return idData;
	}

	public void setIdData(Long idData) {
		this.idData = idData;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
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
		return "Data [idData=" + idData + ", idCountry=" + country + ", idHealth=" + health + ", year=" + year
				+ ", percentage=" + percentage + "]";
	}

	
	
	
	
	
	
	
	

    
    
    
    
}
