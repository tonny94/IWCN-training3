package com.iwcn.training3.Models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ModelUser{
	@Id
	private String nombre;
	private String contraseña;
	//@ElementCollection(fetch = FetchType.EAGER)
	//private List<GrantedAuthority> roles;
	private List<String> roles;
	
	public ModelUser () {}
	
	public ModelUser(String name, String pass, List<String> roles) {
		this.nombre = name;
		this.contraseña = pass;
		this.roles = roles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
