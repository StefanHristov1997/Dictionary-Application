package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "words")
public class Word extends BaseEntity {

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne (targetEntity = Language.class, optional = false)
    private Language language;

    @ManyToOne(targetEntity = UserEntity.class, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity addedBy;
}
