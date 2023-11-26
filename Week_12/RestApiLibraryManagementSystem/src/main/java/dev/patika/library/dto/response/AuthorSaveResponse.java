package dev.patika.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveResponse {
    private int author_id;
    private String authorName;
    private LocalDate authorBirthdate;
    private String authorCountry;
}
