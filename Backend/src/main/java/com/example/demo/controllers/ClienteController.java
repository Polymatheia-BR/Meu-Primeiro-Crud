package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.DTO.ClienteDTO;
import com.example.demo.services.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteDTO>> findAll() {
		return new ResponseEntity<List<ClienteDTO>>(service.findAll(), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<ClienteDTO> addCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<ClienteDTO>(service.save(cliente), HttpStatus.CREATED);
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<ClienteDTO>(service.findById(id), HttpStatus.FOUND);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(@RequestBody Cliente cliente,@PathVariable Long id) {
		return new ResponseEntity<ClienteDTO>(service.update(cliente, id), HttpStatus.OK);
	}
}
