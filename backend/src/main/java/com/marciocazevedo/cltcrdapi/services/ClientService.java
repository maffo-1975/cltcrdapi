package com.marciocazevedo.cltcrdapi.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.marciocazevedo.cltcrdapi.dto.ClientDTO;
import com.marciocazevedo.cltcrdapi.entities.Client;
import com.marciocazevedo.cltcrdapi.repositories.ClientRepository;
import com.marciocazevedo.cltcrdapi.services.exceptions.NonexistentIdException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		Page<Client> pageCli = repository.findAll(pageRequest);
		return pageCli.map(cli -> new ClientDTO(cli));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(@PathVariable Long id) {
		Optional<Client> optCli = repository.findById(id);
		Client cli = optCli.orElseThrow(() -> new NonexistentIdException("Nonexistent id in database: " + id));
		return new ClientDTO(cli);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client cli = new Client();
		copyDtoToCli(dto, cli);
		cli = repository.save(cli);
		return new ClientDTO(cli);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client cli = repository.getOne(id);
			copyDtoToCli(dto, cli);
			cli = repository.save(cli);
			return new ClientDTO(cli);
		} catch (EntityNotFoundException e) {
			throw new NonexistentIdException("Nonexistent id in database: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NonexistentIdException("Nonexistent id in database: " + id);
		}
	}

	private void copyDtoToCli(ClientDTO dto, Client cli) {
		cli.setName(dto.getName());
		cli.setCpf(dto.getCpf());
		cli.setIncome(dto.getIncome());
		cli.setBirthDate(dto.getBirthDate());
		cli.setChildren(dto.getChildren());
	}

}
