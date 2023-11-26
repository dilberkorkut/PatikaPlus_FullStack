package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IPublisherService;
import dev.patika.library.dao.PublisherRepo;
import dev.patika.library.entities.Publisher;

public class PublisherManager implements IPublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepo.save(publisher);
    }
}
