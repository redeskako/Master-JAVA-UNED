package com.uned.worldhealthbank.migracion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "HEALTH_OLD")
public class Health_Old {
	
	@Id
	@Type(type = "string")
	@Column(name = "INDICADOR_CODE", length = 20, nullable = false)
	private String indicadorCode;
	
	@Type(type = "string")
	@Column(name = "INDICADOR_NAME", length = 150)
	private String indicadorName;
	
	@Type(type = "string")
	@Column(name = "SOURCE_NOTE", length = 600)
	private String sourceNote;
	
	@Type(type = "string")
	@Column(name = "SOURCE_ORGANIZATION", length = 900)
	private String sourceOrganization;

	public Health_Old() {
	}

	public Health_Old(String indicadorCode, String indicadorName, String sourceNote, String sourceOrganization) {
		this.indicadorCode = indicadorCode;
		this.indicadorName = indicadorName;
		this.sourceNote = sourceNote;
		this.sourceOrganization = sourceOrganization;
	}

	public Health_Old(Health_Old health_Old) {
		this.indicadorCode = health_Old.getIndicadorCode();
		this.indicadorName = health_Old.getIndicadorName();
		this.sourceNote = health_Old.getSourceNote();
		this.sourceOrganization = health_Old.getSourceOrganization();
	}

	public String getIndicadorCode() {
		return indicadorCode;
	}

	public void setIndicadorCode(String indicadorCode) {
		this.indicadorCode = indicadorCode;
	}

	public String getIndicadorName() {
		return indicadorName;
	}

	public void setIndicadorName(String indicadorName) {
		this.indicadorName = indicadorName;
	}

	public String getSourceNote() {
		return sourceNote;
	}

	public void setSourceNote(String sourceNote) {
		this.sourceNote = sourceNote;
	}

	public String getSourceOrganization() {
		return sourceOrganization;
	}

	public void setSourceOrganization(String sourceOrganization) {
		this.sourceOrganization = sourceOrganization;
	}

	@Override
	public String toString() {
		return "Health_Old [indicadorCode=" + indicadorCode + ", indicadorName=" + indicadorName + ", sourceNote="
				+ sourceNote + ", sourceOrganization=" + sourceOrganization + "]";
	}
	
	

}
