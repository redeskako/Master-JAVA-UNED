package com.uned.springtube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Modelo de la tabla usuario de la BD.
 */

@Entity
@Table(name="usuarios")
public class Usuario
{
	@Id
	@NotEmpty
    @Column(name = "user", nullable = false)
    private String user;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    public String getUser()
    {
        return user;
    }
 
    public void setUser(String user)
    {
        this.user = user;
    }
 
    public String getPassword()
    {
        return password;
    }
 
    public void setPassword(String password)
    {
        this.password = password;
    }
}