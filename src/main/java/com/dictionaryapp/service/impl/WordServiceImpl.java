package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.repository.LanguageRepository;
import com.dictionaryapp.repository.UserRepository;
import com.dictionaryapp.repository.WordRepository;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final CurrentUserSession currentUserSession;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository, WordRepository wordRepository1, ModelMapper modelMapper, CurrentUserSession currentUserSession, LanguageRepository languageRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository1;
        this.modelMapper = modelMapper;
        this.currentUserSession = currentUserSession;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addWord(AddWordDTO addWordDTO) {

        WordEntity wordToSave = modelMapper.map(addWordDTO, WordEntity.class);

        UserEntity currentUser = userRepository.findById(currentUserSession.getId()).get();
        LanguageEntity language = languageRepository.findLanguageEntityByName(addWordDTO.getLanguage());

        wordToSave.setAddedBy(currentUser);
        wordToSave.setLanguage(language);

        wordRepository.save(wordToSave);
    }
}
