package com.uned.worldhealthbank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "ROL")
public class Role {
	
	
		@Id
	    @Type(type = "long")
	    @Column(name = "ID_ROL", nullable = false)
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Type(type = "string")
	    @Column(name = "ROL", length = 45, nullable = false)
	    private String rol;

		public Role() {
			
		}

		public Role(Long id, String rol) {
			this.id = id;
			this.rol = rol;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRol() {
			return rol;
		}

		public void setRol(String rol) {
			this.rol = rol;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", rol=" + rol + "]";
		}
	    
	    
	

}
