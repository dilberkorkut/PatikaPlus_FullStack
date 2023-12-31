package dev.patika.library.dto.request;

import dev.patika.library.entities.Author;
import dev.patika.library.entities.Publisher;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {
    private String name;
    private int publicationYear;
    private int stock;
//    private int author_id;
//    private int publisher_id;
    private Author author;
    private Publisher publisher;

}
