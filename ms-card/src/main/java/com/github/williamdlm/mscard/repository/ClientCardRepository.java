package com.github.williamdlm.mscard.repository;

import com.github.williamdlm.mscard.model.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    List<ClientCard> findByDocument(String document);
}
