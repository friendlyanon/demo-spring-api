package com.friendlyanon.springapi.service;

import com.friendlyanon.springapi.dao.HashDao;
import com.friendlyanon.springapi.model.Hash;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service("hashService")
public class HashService {
    private final HashDao hashDao;

    public List<Hash> getHashes(Collection<Integer> hashIds) {
        return hashDao.findAllById(hashIds);
    }

    public void saveHashes(Collection<Hash> hashes) {
        hashDao.saveAll(hashes);
    }
}
