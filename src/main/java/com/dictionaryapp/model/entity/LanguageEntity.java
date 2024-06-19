package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageNamesEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageNamesEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<WordEntity> words;

    public LanguageEntity() {
        this.words = new HashSet<>();
    }
}
