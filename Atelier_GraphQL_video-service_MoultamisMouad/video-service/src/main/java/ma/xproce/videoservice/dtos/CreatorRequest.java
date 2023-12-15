package ma.xproce.videoservice.dtos;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatorRequest {

    String name ;
    String email ;
}
