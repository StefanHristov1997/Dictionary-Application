package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "words")
public class WordEntity extends BaseEntity {

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne (targetEntity = LanguageEntity.class, optional = false)
    private LanguageEntity languageEntity;

    @ManyToOne(targetEntity = UserEntity.class, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity addedBy;
}
