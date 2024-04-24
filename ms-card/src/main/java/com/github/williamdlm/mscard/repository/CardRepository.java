package com.github.williamdlm.mscard.repository;

import com.github.williamdlm.mscard.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    Optional<List<Card>> findByIncomeLessThanEqual(Long income);
}
