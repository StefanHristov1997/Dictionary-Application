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
public class Language extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageNamesEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany
    @JoinTable(name = "languages_words", joinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "word_id", referencedColumnName = "id"))
    private Set<Word> words;

    public Language() {
        this.words = new HashSet<>();
    }
}
