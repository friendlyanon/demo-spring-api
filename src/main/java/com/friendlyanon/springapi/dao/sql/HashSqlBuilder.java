package com.friendlyanon.springapi.dao.sql;

import com.friendlyanon.springapi.model.Hash;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@Component("hashSqlBuilder")
@NoArgsConstructor
public class HashSqlBuilder {
    @SuppressWarnings("StringRepeatCanBeUsed")
    private static String makeArrayPlaceholders(int size) {
        if (size == 1) {
            return "(?)";
        }

        // String#repeat is not used here because we already allocated the
        // necessary space for the string with the constructor
        val sb = new StringBuilder(3 * size).append("(?");
        for (int i = 0; i < size; i++) {
            sb.append(", ?");
        }
        return sb.append(')').toString();
    }

    public String selectWhereHashIds(Collection<Integer> ids) {
        val placeholders = makeArrayPlaceholders(ids.size());

        return "SELECT * FROM hashes WHERE id IN " + placeholders;
    }

    private static Consumer<Hash> buildInsertSqlInto(StringBuilder sql) {
        val initialLength = sql.length();

        return (hash) -> {
            val sb = sql.length() > initialLength ? sql.append(", ") : sql;
            sb.append("(?, ?, ?, ?)");
        };
    }

    private Object[] prepareHashesForInsert(
        Collection<Hash> hashes,
        StringBuilder sql,
        Function<Hash, Stream<Object>> hashFlattener
    ) {
        return hashes.stream()
            .peek(buildInsertSqlInto(sql))
            .flatMap(hashFlattener)
            .toArray();
    }

    public PreparedSql insertHashes(
        Collection<Hash> hashes,
        Function<Hash, Stream<Object>> hashFlattener
    ) {
        val sql = new StringBuilder("INSERT INTO hashes VALUES ");
        val values = prepareHashesForInsert(hashes, sql, hashFlattener);

        return new PreparedSql(sql.toString(), values);
    }

    @Value
    public static class PreparedSql {
        String sql;
        Object[] bindings;
    }
}
