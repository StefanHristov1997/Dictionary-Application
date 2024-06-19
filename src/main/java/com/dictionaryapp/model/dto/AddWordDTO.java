package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
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
    @Size(min = 2, max = 200, message = "{translation.length}")
    private String example;

    @PastOrPresent(message = "{input.date}")
    private LocalDate inputDate;

    @NonNull
    private String language;

}
