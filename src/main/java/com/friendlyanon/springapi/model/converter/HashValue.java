package com.friendlyanon.springapi.model.converter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.friendlyanon.springapi.util.serializer.HashValueSerializer;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data(staticConstructor = "of")
@JsonSerialize(using = HashValueSerializer.class)
public class HashValue implements Serializable {
    public static final int HASH_LENGTH = 32;

    @NonNull
    private String value;
}
