package dev.patika.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    @NotNull
    @NotEmpty
    private String name;
    private int publicationYear;
    private int stock;
    private int author_id;
    private int publisher_id;


}
