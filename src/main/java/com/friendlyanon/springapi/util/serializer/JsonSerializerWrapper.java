package com.friendlyanon.springapi.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * This class wraps a value and knows how to serialize it using the provided
 * serializer class.
 */
@JsonSerialize(using = JsonSerializerWrapper.Serializer.class)
@RequiredArgsConstructor
public class JsonSerializerWrapper<T> {
    private final T value;
    @NonNull
    private final JsonSerializer<T> serializer;

    public class Serializer extends JsonSerializer<JsonSerializerWrapper<T>> {
        @Override
        public void serialize(
            JsonSerializerWrapper<T> instance,
            JsonGenerator gen,
            SerializerProvider serializers
        ) throws IOException {
            instance.serializer.serialize(instance.value, gen, serializers);
        }
    }
}
