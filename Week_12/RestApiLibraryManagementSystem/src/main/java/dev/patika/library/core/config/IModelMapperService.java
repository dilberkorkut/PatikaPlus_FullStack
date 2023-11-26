package dev.patika.library.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();

}
