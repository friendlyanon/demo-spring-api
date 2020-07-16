package com.friendlyanon.springapi.model;

import com.friendlyanon.springapi.json.deserializer.HashDeserializer;
import com.friendlyanon.springapi.model.converter.HashValue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

@JsonDeserialize(using = HashDeserializer.class)
@Value
public class Hash {
    int id;
    int pattern;
    String extension;
    HashValue hash;
}
