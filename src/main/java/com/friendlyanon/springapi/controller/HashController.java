package com.friendlyanon.springapi.controller;

import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.service.HashService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/hash")
@RequiredArgsConstructor
@RestController("hash")
public class HashController {
    private final HashService hashService;

    @GetMapping
    public List<Hash> getHashes(@RequestParam List<Integer> id) {
        return hashService.getHashes(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveHashes(List<Hash> hashes) {
        hashService.saveHashes(hashes);
    }
}
