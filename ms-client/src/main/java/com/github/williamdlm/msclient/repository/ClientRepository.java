package com.github.williamdlm.msclient.repository;

import com.github.williamdlm.msclient.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdDocument(String idDocument);
}
