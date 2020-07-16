package com.friendlyanon.springapi.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component("quoteDao")
@RequiredArgsConstructor
public class QuoteDao {
    private final JdbcTemplate jdbcTemplate;

    @Nullable
    @SneakyThrows
    private static String extractQuoteText(ResultSet rs) {
        return rs.next() ? rs.getString(1) : null;
    }

    @Nullable
    public String findRandomQuoteText() {
        //language=MySQL
        val sql = "SELECT \"text\" FROM quotes ORDER BY RAND() LIMIT 1";

        return jdbcTemplate.query(sql, QuoteDao::extractQuoteText);
    }
}
