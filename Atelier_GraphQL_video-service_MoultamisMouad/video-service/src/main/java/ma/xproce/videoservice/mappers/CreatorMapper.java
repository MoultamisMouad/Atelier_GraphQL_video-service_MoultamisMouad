package ma.xproce.videoservice.mappers;

import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dtos.CreatorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public Creator fromCreatorRequestToCreator(CreatorRequest creatorRequest) {
        return this.modelMapper.map(creatorRequest, Creator.class);
    }

    public CreatorRequest fromCreatorToCreatorRequest(Creator creator) {
        return this.modelMapper.map(creator, CreatorRequest.class);
    }
}