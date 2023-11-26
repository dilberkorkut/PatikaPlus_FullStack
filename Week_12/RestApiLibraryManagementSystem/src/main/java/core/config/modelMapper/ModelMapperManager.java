package core.config.modelMapper;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService{
    private final ModelMapper modelMapper;

    @Autowired // islemleri otomatik yapar modelMapper atamasini yapar
    public ModelMapperManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
