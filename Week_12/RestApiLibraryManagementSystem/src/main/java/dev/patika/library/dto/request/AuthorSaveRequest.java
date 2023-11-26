package dev.patika.library.dto.request;

import dev.patika.library.business.abstracts.IAuthorService;
import dev.patika.library.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    private String authorName;
    private LocalDate authorBirthdate;
    private String authorCountry;


}

