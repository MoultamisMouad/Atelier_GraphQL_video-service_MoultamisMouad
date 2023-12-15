package ma.xproce.videoservice;

import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repository.CreatorRepository;
import ma.xproce.videoservice.dao.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {



		public static void main(String[] args) {
			SpringApplication.run(ma.xproce.videoservice.VideoServiceApplication.class, args);
		}
		@Bean
		CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
			return args -> {
				List<Creator> creators =
						List.of(Creator.builder().name("mouad").email("mouad@gmail.com").build(),
								Creator.builder().name("hedraf").email("hedraf@gmail.com").build(),
								Creator.builder().name("nekach").email("nekach@gmail.com").build());
				for (Creator creator : creators) {
					creatorRepository.save(creator);
				}


				List<Video> videos =
						List.of(Video.builder().name("vFoot").url("vFoot.com").datePublication(new Date()).description("football video").creator(creators.get(1)).build(),
								Video.builder().name("vDocumentary").url("vDocumentary.com").datePublication(new Date()).description("documentary video").creator(creators.get(2)).build());
				for (Video video : videos) {
					videoRepository.save(video);

				}

			};

		}
	}
