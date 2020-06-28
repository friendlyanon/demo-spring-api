package com.friendlyanon.springapi.repository;

import com.friendlyanon.springapi.model.Hash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashRepository extends JpaRepository<Hash, Integer> {
}
