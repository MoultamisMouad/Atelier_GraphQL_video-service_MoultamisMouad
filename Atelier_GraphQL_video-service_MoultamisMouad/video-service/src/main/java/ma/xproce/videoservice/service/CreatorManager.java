package ma.xproce.videoservice.service;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.repository.CreatorRepository;
import ma.xproce.videoservice.dao.repository.VideoRepository;
import ma.xproce.videoservice.dtos.CreatorRequest;
import ma.xproce.videoservice.mappers.CreatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatorManager {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CreatorRepository creatorRepository ;
    @Autowired
    private CreatorMapper creatorMapper;



    public Creator creatorById(Long id) {
        return creatorRepository.findById(Math.toIntExact(id))
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }

    public Creator saveCreator( CreatorRequest CreatorRequest){
        Creator creator  = creatorMapper.fromCreatorRequestToCreator(CreatorRequest);
        return creatorRepository.save(creator);
    }

    public CreatorRequest getCreator(Long id){
        Creator creator =  creatorRepository.findById(Math.toIntExact(id)).get();
        return this.creatorMapper.fromCreatorToCreatorRequest(creator);
    }




}