package com.marciocazevedo.cltcrdapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		Client cli = optCli.orElseThrow(() -> new NonexistentIdException("Nonexistent id in database"));
		return new ClientDTO(cli);
	}

}
