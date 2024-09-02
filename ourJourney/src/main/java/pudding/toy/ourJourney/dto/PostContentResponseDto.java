package pudding.toy.ourJourney.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pudding.toy.ourJourney.dto.django.ProfileAuthResponseDto;
import pudding.toy.ourJourney.entity.Post;
import pudding.toy.ourJourney.entity.PostCategory;
import pudding.toy.ourJourney.entity.Profile;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class PostContentResponseDto {
    String title;
    String nickName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    PostCategory postCategory;
    String postImg;
    String content;

    public static PostContentResponseDto from(Post post){
        return PostContentResponseDto.builder()
                .content(post.getContent())
                .nickName(post.getProfile().getNickName())
                .postImg(post.getPostImgUrl())
                .content(post.getContent())
                .build();
    }
}
