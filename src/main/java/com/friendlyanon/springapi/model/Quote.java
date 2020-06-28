package com.friendlyanon.springapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "quotes")
public class Quote {
    @Column(nullable = false)
    @Id
    private int id;

    @Column(nullable = false)
    private String text;
}
