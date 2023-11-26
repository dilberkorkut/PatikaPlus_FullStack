package core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    //her projem basladiginda bir modelmapper nesnesi uretip bana versin.

    // her projem basladiginmda bu bean olusur bir nesne olusur ioc konteyner icine atacak.
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
