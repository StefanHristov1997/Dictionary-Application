package com.dictionaryapp.init;

import com.dictionaryapp.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LanguageInit implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageInit(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (languageRepository.count() > 0) {
            initMode = "never";
        }
    }
}
