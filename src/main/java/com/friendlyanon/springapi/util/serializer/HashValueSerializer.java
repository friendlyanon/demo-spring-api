package com.friendlyanon.springapi.util.serializer;

import com.friendlyanon.springapi.model.converter.HashValue;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class HashValueSerializer extends JsonSerializer<HashValue> {
    @Override
    public void serialize(
        HashValue value,
        JsonGenerator gen,
        SerializerProvider provider
    ) throws IOException {
        gen.writeString(value.getValue());
    }
}
