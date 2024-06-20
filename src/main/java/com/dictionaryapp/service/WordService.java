package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNamesEnum;

import java.util.List;

public interface WordService {

    void addWord(AddWordDTO addWordDTO);

    List<WordEntity> findWordsByLanguageName(LanguageNamesEnum name);

    void removeWord(Long id);

    void removeAllWords();

}
