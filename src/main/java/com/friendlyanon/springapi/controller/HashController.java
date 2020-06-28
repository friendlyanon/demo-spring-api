package com.friendlyanon.springapi.controller;

import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.repository.HashRepository;
import com.friendlyanon.springapi.util.serializer.HashListSerializer;
import com.friendlyanon.springapi.util.serializer.JsonSerializerWrapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/hash")
@RequiredArgsConstructor
@RestController
public class HashController {
    private final HashRepository hashRepository;

    @GetMapping
    public JsonSerializerWrapper<List<Hash>> getHashes(
        @RequestParam List<Integer> id,
        HashListSerializer serializer
    ) {
        val hashes = hashRepository.findAllById(id);

        return new JsonSerializerWrapper<>(hashes, serializer);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveHashes(List<Hash> hashes) {
        hashRepository.saveAll(hashes);
    }
}
