package dev.patika.library.dto.request;

import dev.patika.library.entities.Author;
import dev.patika.library.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private Author author;
    private Publisher publisher;

}
