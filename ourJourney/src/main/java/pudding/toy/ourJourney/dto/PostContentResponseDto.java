package pudding.toy.ourJourney.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pudding.toy.ourJourney.entity.Post;
import pudding.toy.ourJourney.entity.Category;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class PostContentResponseDto {
    String title;
    String nickName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    Category category;
    String postImg;
    public static PostContentResponseDto from(Post post){
        return PostContentResponseDto.builder()
                .title(post.getTitle())
                .nickName(post.getProfile().getNickName())
                .postImg(post.getImgUrl())
                .build();
    }
}
