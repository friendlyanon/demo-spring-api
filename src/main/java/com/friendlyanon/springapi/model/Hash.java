package com.friendlyanon.springapi.model;

import com.friendlyanon.springapi.json.deserializer.HashDeserializer;
import com.friendlyanon.springapi.model.converter.HashValue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@JsonDeserialize(using = HashDeserializer.class)
@NoArgsConstructor
@Setter
@Table(name = "hashes")
public class Hash {
    /**
     * This isn't a generated column, because hash IDs have a corresponding
     * post ID that is always unique, so we just use the "id" column for that.
     */
    @Column(nullable = false)
    @Id
    private int id;

    @Column(nullable = false)
    private int pattern;

    @Column(nullable = false)
    @NonNull
    private String extension;

    @Column(nullable = false, unique = true)
    @NonNull
    private HashValue hash;
}
