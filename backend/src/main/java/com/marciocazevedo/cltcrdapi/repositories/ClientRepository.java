package com.marciocazevedo.cltcrdapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marciocazevedo.cltcrdapi.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
