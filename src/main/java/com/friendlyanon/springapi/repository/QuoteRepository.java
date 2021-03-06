package com.friendlyanon.springapi.repository;

import com.friendlyanon.springapi.model.Quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository("quoteRepository")
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    /**
     * {@code RAND()} is a MySQL function, so this needs to be a native query.
     */
    @Nullable
    @Query(
        value = "SELECT text FROM quotes ORDER BY RAND() LIMIT 1",
        nativeQuery = true
    )
    String findRandomQuoteText();
}
