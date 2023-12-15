package ma.xproce.videoservice.web;

import ma.xproce.videoservice.dao.repository.CreatorRepository;
import ma.xproce.videoservice.dao.repository.VideoRepository;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dtos.CreatorRequest;
import ma.xproce.videoservice.dtos.VideoRequest;
import ma.xproce.videoservice.service.CreatorManager;
import ma.xproce.videoservice.service.VideoManager;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
public class VideoGraphQlController {
    private CreatorManager creatorManager;
    private VideoManager videoManager;


    @QueryMapping
    public List<Video> videoList() {
        return videoManager.videoList();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorManager.creatorById(id);
    }

    @MutationMapping
    public Video saveVideo(@Argument Video video) {
        return videoManager.saveVideo(video);
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest CreatorRequest) {
        return creatorManager.saveCreator(CreatorRequest);
    }

    @QueryMapping
    public CreatorRequest getCreator(@Argument Long id) {
        return creatorManager.getCreator(id);
    }

    @SubscriptionMapping("notifyVideoChange")
    public Flux<Video> notifyVideoChange() {

        // A flux is the publisher of data
        return Flux.fromStream(
                Stream.generate(() -> {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Random random = new Random();

                    CreatorRequest creatorRequest = CreatorRequest.builder().name("x" + random.nextInt())
                            .email("x@gmail.com").build();

                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);

                    return video;
                }));
    }
}