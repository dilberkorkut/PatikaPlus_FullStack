package dev.patika.library.api;

import dev.patika.library.business.abstracts.IAuthorService;
import dev.patika.library.dto.request.AuthorSaveRequest;
import dev.patika.library.entities.Author;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(IAuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Author save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author newAuthor = this.modelMapper.map(authorSaveRequest, Author.class);
        return this.authorService.save(newAuthor);

    }
}
