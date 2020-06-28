package com.friendlyanon.springapi.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.friendlyanon.springapi.model.Hash;
import lombok.val;

import java.io.IOException;
import java.util.List;

/**
 * The list of {@link Hash} can safely be converted to an object keyed by its
 * "id" field, because it's always unique.
 */
public class HashListSerializer extends JsonSerializer<List<Hash>> {
    /**
     * This is basically a keyBy operation.
     */
    @Override
    public void serialize(
        List<Hash> value,
        JsonGenerator gen,
        SerializerProvider serializers
    ) throws IOException {
        gen.writeStartObject();

        for (val hash : value) {
            gen.writeObjectField(Integer.toString(hash.getId()), hash);
        }

        gen.writeEndObject();
    }
}
