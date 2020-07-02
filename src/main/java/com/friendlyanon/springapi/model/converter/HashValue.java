package com.friendlyanon.springapi.model.converter;

import com.friendlyanon.springapi.util.serializer.HashValueSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@JsonSerialize(using = HashValueSerializer.class)
public class HashValue implements Serializable {
    public static final int HASH_LENGTH = 32;

    @NonNull
    private String value;
}
