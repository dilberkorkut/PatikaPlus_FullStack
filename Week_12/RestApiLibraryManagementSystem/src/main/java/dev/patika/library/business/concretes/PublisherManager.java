package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IPublisherService;
import dev.patika.library.dao.PublisherRepo;
import dev.patika.library.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherManager implements IPublisherService {
    @Autowired
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher getById(int id) {
        return this.publisherRepo.findById(id).orElseThrow();
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public void delete(int id) {
        this.publisherRepo.delete(this.getById(id));

    }

    @Override
    public List<Publisher> findAll() {
        return this.publisherRepo.findAll();
    }
}
