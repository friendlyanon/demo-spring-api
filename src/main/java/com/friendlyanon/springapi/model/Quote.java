package com.friendlyanon.springapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "quotes")
public class Quote {
    @Column(nullable = false)
    @Id
    private int id;

    @Column(nullable = false)
    @NonNull
    private String text;
}
