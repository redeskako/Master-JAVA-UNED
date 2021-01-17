package com.uned.worldhealthbank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "HEALTH")
public class Health {
	
	
		@Id
	    @Type(type = "long")
	    @Column(name = "ID_HEALTH", nullable = false)
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Type(type = "string")
	    @Column(name = "INDICADOR_CODE", length = 45, nullable = false)
	    private String indicadorCode;
	    
	    @Type(type = "string")
	    @Column(name = "INDICADOR_NAME", length = 150, nullable = false)
	    private String indicadorName;
	    
	    @Type(type = "string")
	    @Column(name = "SOURCE_NOTE", length = 600, nullable = false)
	    private String sourceNote;
	    
	    @Type(type = "string")
	    @Column(name = "SOURCE_ORGANIZATION", length = 900, nullable = false)
	    private String sourceOrganization;

		public Health() {
			
		}

		

		public Health(Long id, String indicadorCode, String indicadorName, String sourceNote,
				String sourceOrganization) {
			this.id = id;
			this.indicadorCode = indicadorCode;
			this.indicadorName = indicadorName;
			this.sourceNote = sourceNote;
			this.sourceOrganization = sourceOrganization;
		}



		public Health(Health health) {
			this.id = health.id;
			this.indicadorCode = health.indicadorCode;
			this.indicadorName = health.indicadorName;
			this.sourceNote = health.sourceNote;
			this.sourceOrganization = health.sourceOrganization;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
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
			return "Health [id=" + id + ", indicadorCode=" + indicadorCode + ", indicadorName=" + indicadorName
					+ ", sourceNote=" + sourceNote + ", sourceOrganization=" + sourceOrganization + "]";
		}
		
		


	

}
