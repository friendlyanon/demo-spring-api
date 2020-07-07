package com.friendlyanon.springapi.service;

import com.friendlyanon.springapi.model.Hash;
import com.friendlyanon.springapi.repository.HashRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("hash")
public class HashService {
    private final HashRepository hashRepository;

    public List<Hash> getHashes(Iterable<Integer> hashIds) {
        return hashRepository.findAllById(hashIds);
    }

    public void saveHashes(Iterable<Hash> hashes) {
        hashRepository.saveAll(hashes);
    }
}
