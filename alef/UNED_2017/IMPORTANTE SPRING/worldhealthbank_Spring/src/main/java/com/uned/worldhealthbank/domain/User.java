package com.uned.worldhealthbank.domain;


import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
@Table(name = "USERS")
public class User {
	@Id
    @Type(type = "long")
    @Column(name = "ID_USER", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "string")
    @Column(name = "LOGIN", length = 45, nullable = false)
    private String login;
    
    @Type(type = "string")
    @Column(name = "PASS", length = 45, nullable = false)
    private String pass;
    
    @Type(type = "string")
    @Column(name = "EMAIL", length = 45, nullable = false)
    private String email;
    
    @Column(name = "ESTADO", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean estado;
    

	public User() {
	}

	public User(User user) {
		this.id = user.id;
		this.login = user.login;
		this.pass = user.pass;
		this.email = user.email;
		
	}
	
	public User(Long id, String login, String pass, String email, boolean estado) {
		this.id = id;
		this.login = login;
		this.pass = pass;
		this.email = email;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", pass=" + pass + ", email=" + email + ", estado=" + estado
				+ "]";
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	

    
}
