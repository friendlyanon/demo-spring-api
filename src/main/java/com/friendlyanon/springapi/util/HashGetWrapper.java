package com.friendlyanon.springapi.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.util.serializer.HashListSerializer;
import lombok.Value;

import java.util.List;

@JsonSerialize(using = HashListSerializer.class)
@Value
public class HashGetWrapper {
    List<Hash> hashes;
}
