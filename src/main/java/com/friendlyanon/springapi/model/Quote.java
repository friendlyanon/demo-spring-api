package com.friendlyanon.springapi.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Setter
@Table(name = "quotes")
public class Quote {
    @Column(nullable = false)
    @Id
    private int id;

    @Column(nullable = false)
    @NonNull
    private String text;
}
