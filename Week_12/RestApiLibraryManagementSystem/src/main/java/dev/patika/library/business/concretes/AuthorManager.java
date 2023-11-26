package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IAuthorService;
import dev.patika.library.dao.AuthorRepo;
import dev.patika.library.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {
    @Autowired
    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }
}
