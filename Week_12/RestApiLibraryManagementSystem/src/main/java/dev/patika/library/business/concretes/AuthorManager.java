package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IAuthorService;
import dev.patika.library.dao.AuthorRepo;
import dev.patika.library.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements IAuthorService {
    @Autowired //dependency injection ioc ye devrediyoruz
    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author getById(int id) {
        return this.authorRepo.findById(id).orElseThrow();
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public void delete(int id) {
        this.authorRepo.delete(this.getById(id));

    }

    @Override
    public List<Author> findAll() {
        return this.authorRepo.findAll();
    }
}
