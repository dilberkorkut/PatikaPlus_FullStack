package dev.patika.library.dto.response;

import dev.patika.library.entities.Author;
import dev.patika.library.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateResponse {
    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private Author author;
    private Publisher publisher;
}
