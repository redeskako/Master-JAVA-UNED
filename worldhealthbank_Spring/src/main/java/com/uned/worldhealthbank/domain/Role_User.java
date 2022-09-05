package com.uned.worldhealthbank.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "ROL_USER")
public class Role_User {
	
  
	@Id
	@Column(name = "ID_ROL_USER", nullable = false)
	@Type(type = "long")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name="ID_USER")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="ID_ROL")
    private Role rol;

	public Role_User() {
		
	}

	public Role_User(Role_User rol_user) {
		this.user = rol_user.user;
		this.rol = rol_user.rol;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Role_User [user=" + user + ", rol=" + rol + "]";
	}
	
	

    
    
    
    
}
