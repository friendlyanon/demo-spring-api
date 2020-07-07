package com.friendlyanon.springapi.json.serializer;

import com.friendlyanon.springapi.advice.HashGetResponseBodyAdvice.HashGetWrapper;
import com.friendlyanon.springapi.model.Hash;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.val;

import java.io.IOException;

/**
 * The list of {@link Hash} can safely be converted to an object keyed by its
 * "id" field, because it's always unique.
 */
public class ListOfHashSerializer extends JsonSerializer<HashGetWrapper> {
    /**
     * This is basically a keyBy operation.
     */
    @Override
    public void serialize(
        HashGetWrapper value,
        JsonGenerator gen,
        SerializerProvider serializers
    ) throws IOException {
        gen.writeStartObject();

        for (val hash : value.getHashes()) {
            gen.writeObjectField(Integer.toString(hash.getId()), hash);
        }

        gen.writeEndObject();
    }
}
