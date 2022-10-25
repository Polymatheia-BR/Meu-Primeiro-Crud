package com.example.demo.exceptions;

public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(Long id) {
		super("Não foi encontrado o cliente com id " + id);
	}
}
