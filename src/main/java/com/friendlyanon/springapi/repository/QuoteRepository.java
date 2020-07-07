package com.friendlyanon.springapi.repository;

import com.friendlyanon.springapi.model.Quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("quote")
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    /**
     * {@code RAND()} is a MySQL function, so this needs to be a native query.
     */
    @Query(
        value = "SELECT text FROM quotes ORDER BY RAND() LIMIT 1",
        nativeQuery = true
    )
    Optional<String> getRandomQuoteText();
}
