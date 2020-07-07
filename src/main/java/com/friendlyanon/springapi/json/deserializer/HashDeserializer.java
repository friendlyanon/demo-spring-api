package com.friendlyanon.springapi.json.deserializer;

import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.model.converter.HashValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

public class HashDeserializer extends JsonDeserializer<Hash> {
    @Override
    @SneakyThrows
    public Hash deserialize(JsonParser p, DeserializationContext ctx) {
        return deserialize(p.getCodec().readTree(p));
    }

    public Hash deserialize(ObjectNode node) {
        return new Hash(
            node.get("id").intValue(),
            node.get("pattern").intValue(),
            node.get("extension").asText(),
            new HashValue(node.get("hash").asText())
        );
    }
}
