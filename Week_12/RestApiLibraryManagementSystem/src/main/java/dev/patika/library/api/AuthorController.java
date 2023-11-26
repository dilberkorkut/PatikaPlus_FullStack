package dev.patika.library.api;

import dev.patika.library.business.abstracts.IAuthorService;
import dev.patika.library.core.config.IModelMapperService;
import dev.patika.library.dto.request.AuthorSaveRequest;
import dev.patika.library.dto.response.AuthorSaveResponse;
import dev.patika.library.entities.Author;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorSaveResponse save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        return this.modelMapper.forResponse().map(saveAuthor, AuthorSaveResponse.class);
    }
}
