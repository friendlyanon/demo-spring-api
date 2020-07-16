package com.friendlyanon.springapi.dao;

import com.friendlyanon.springapi.dao.sql.HashSqlBuilder;
import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.model.converter.HashValueConverter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component("hashDao")
@RequiredArgsConstructor
public class HashDao {
    private final JdbcTemplate jdbcTemplate;
    private final HashSqlBuilder hashSqlBuilder;
    private final HashValueConverter hashValueConverter;

    private static List<Hash> emptyList() {
        return new ArrayList<>();
    }

    @SneakyThrows
    private List<Hash> extractGetAll(ResultSet rs) {
        val hashes = emptyList();

        while (rs.next()) {
            val hash = new Hash(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                hashValueConverter.convertToEntityAttribute(rs.getBytes(4))
            );

            hashes.add(hash);
        }

        return hashes;
    }

    public List<Hash> findAllById(Collection<Integer> ids) {
        if (ids.size() == 0) {
            return emptyList();
        }

        val sql = hashSqlBuilder.selectWhereHashIds(ids);
        val result = jdbcTemplate.query(sql, ids.toArray(), this::extractGetAll);

        return result == null ? emptyList() : result;
    }

    private Stream<Object> flattenHash(Hash hash) {
        return Stream.of(hash.getId(), hash.getPattern(), hash.getExtension(),
            hashValueConverter.convertToDatabaseColumn(hash.getHash()));
    }

    public void saveAll(Collection<Hash> hashes) {
        if (hashes.size() == 0) {
            return;
        }

        val insert = hashSqlBuilder.insertHashes(hashes, this::flattenHash);
        jdbcTemplate.update(insert.getSql(), insert.getBindings());
    }
}
