package dev.patika.library.api;

import dev.patika.library.business.abstracts.IPublisherService;
import dev.patika.library.dto.request.PublisherSaveRequest;
import dev.patika.library.entities.Publisher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final ModelMapper modelMapper;

    public PublisherController(IPublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher newPublisher = this.modelMapper.map(publisherSaveRequest, Publisher.class);
        return this.publisherService.save(newPublisher);
    }
}
