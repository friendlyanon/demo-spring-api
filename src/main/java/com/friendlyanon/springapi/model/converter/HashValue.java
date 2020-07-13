package com.friendlyanon.springapi.model.converter;

import com.friendlyanon.springapi.json.serializer.HashValueSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
@JsonSerialize(using = HashValueSerializer.class)
public class HashValue implements Serializable {
    public static final int HASH_LENGTH = 32;
    private static final long serialVersionUID = -1041949885379698765L;

    private String value;
}
