package com.example.demo.entities.DTO;

import java.io.Serializable;

import com.example.demo.entities.Cliente;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String email;
	private String telephone;
	
	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String name, String email, String telephone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
	}
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		name = cliente.getName();
		email = cliente.getEmail();
		telephone = cliente.getTelephone();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
