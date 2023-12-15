package ma.xproce.videoservice.service;

import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repository.CreatorRepository;
import ma.xproce.videoservice.dao.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class VideoManager {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CreatorRepository creatorRepository;


    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    public Video saveVideo(Video video) {
        creatorRepository.save(video.getCreator());
        return videoRepository.save(video);
    }

    public Video findById(Long id) {
        return videoRepository.findById(Math.toIntExact(id)).get();
    }

    public Video updateVideo(Video video) {
        return videoRepository.save(video);

    }
}