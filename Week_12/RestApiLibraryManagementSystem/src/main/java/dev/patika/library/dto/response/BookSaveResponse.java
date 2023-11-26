package dev.patika.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveResponse {
    private int id;
    private String name;
    private int publicationYear;
    private int stock;
}
