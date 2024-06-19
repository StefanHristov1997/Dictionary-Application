package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.enums.LanguageNamesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddWordDTO {

    @NotBlank(message = "{not.blank}")
    @Size(min = 2, max = 40, message = "{term.length}")
    private String term;

    @NotBlank(message = "{not.blank}")
    @Size(min = 2, max = 80, message = "{translation.length}")
    private String translation;

    @NotBlank(message = "{not.blank}")
    @Size(min = 2, max = 200, message = "{example.length}")
    private String example;

    @PastOrPresent(message = "{input.date}")
    private LocalDate inputDate;

    @NotNull(message = "{language_not_null}")
    private LanguageNamesEnum language;

}
