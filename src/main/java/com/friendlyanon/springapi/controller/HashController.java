package com.friendlyanon.springapi.controller;

import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.repository.HashRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/hash")
@RequiredArgsConstructor
@RestController("hash")
public class HashController {
    @NonNull
    private final HashRepository hashRepository;

    @GetMapping
    public List<Hash> getHashes(@RequestParam List<Integer> id) {
        return hashRepository.findAllById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveHashes(List<Hash> hashes) {
        hashRepository.saveAll(hashes);
    }
}
