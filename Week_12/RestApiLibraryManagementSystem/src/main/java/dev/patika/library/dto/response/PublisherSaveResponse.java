package dev.patika.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveResponse {
    private int publisher_id;
    private String publisherName;
    private String establishmentYear;
    private String publisherAddress;
}
