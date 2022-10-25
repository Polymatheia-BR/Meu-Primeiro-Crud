package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.DTO.ClienteDTO;
import com.example.demo.exceptions.ClienteNotFoundException;
import com.example.demo.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public ClienteDTO findById(Long id) {
		 Cliente cliente = repository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		 return new ClienteDTO(cliente);
	}
	
	public List<ClienteDTO> findAll() {
		List<Cliente> arrayCliente = repository.findAll();
		return arrayCliente
				.stream()
				.map(c -> new ClienteDTO(c))
				.toList();
	}
	
	public ClienteDTO save(Cliente cliente) {
		return new ClienteDTO(repository.save(cliente));
	}
	
	public void delete(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ClienteNotFoundException(id);
		}
	}
	
	public ClienteDTO update(Cliente clienteNew, Long id) {
		Cliente cliente = repository.findById(id).get();
		cliente.setEmail(clienteNew.getEmail());
		cliente.setName(clienteNew.getName());
		cliente.setTelephone(clienteNew.getTelephone());
		return new ClienteDTO(repository.save(cliente));
	}
	
}
