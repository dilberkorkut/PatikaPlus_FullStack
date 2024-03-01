package dev.patika.library.api;

import dev.patika.library.business.abstracts.IPublisherService;
import dev.patika.library.dto.request.AuthorUpdateRequest;
import dev.patika.library.dto.request.PublisherSaveRequest;
import dev.patika.library.dto.request.PublisherUpdateRequest;
import dev.patika.library.entities.Author;
import dev.patika.library.entities.Publisher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final ModelMapper modelMapper;

    public PublisherController(IPublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getById(@PathVariable("id") int id) {
        return this.publisherService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> findAll() {
        return this.publisherService.findAll();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher newPublisher = this.modelMapper.map(publisherSaveRequest, Publisher.class);
        return this.publisherService.save(newPublisher);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher update(@PathVariable int id, @Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher newPublisher = this.publisherService.getById(id);
        newPublisher.setName(publisherUpdateRequest.getName());
        newPublisher.setEstablishmentYear(publisherUpdateRequest.getEstablishmentYear());
        return this.publisherService.update(newPublisher);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.publisherService.delete(id);

    }
}
