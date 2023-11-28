package dev.patika.library.api;

import dev.patika.library.business.abstracts.IAuthorService;
import dev.patika.library.dto.request.AuthorSaveRequest;
import dev.patika.library.dto.request.AuthorUpdateRequest;
import dev.patika.library.entities.Author;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(IAuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getById(@PathVariable("id") int id) {
        return this.authorService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Author save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author newAuthor = this.modelMapper.map(authorSaveRequest, Author.class);
        return this.authorService.save(newAuthor);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Author update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author newAuthor = this.authorService.getById(authorUpdateRequest.getId());
        newAuthor.setName(authorUpdateRequest.getAuthorName());
        newAuthor.setBirthDate(authorUpdateRequest.getAuthorBirthdate());
        newAuthor.setCountry(authorUpdateRequest.getAuthorCountry());
        return this.authorService.update(newAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.authorService.delete(id);

    }
}
