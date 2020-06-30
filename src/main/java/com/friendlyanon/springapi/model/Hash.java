package com.friendlyanon.springapi.model;

import com.friendlyanon.springapi.model.converter.HashValue;
import com.friendlyanon.springapi.model.converter.HashValueConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
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
    private String extension;

    @Column(nullable = false, unique = true)
    @Convert(converter = HashValueConverter.class)
    @NonNull
    private HashValue hash;
}
