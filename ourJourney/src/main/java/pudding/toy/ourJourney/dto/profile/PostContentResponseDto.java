package pudding.toy.ourJourney.dto.profile;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pudding.toy.ourJourney.entity.Contents;
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
    public static PostContentResponseDto from(Contents contents){
        return PostContentResponseDto.builder()
                .title(contents.getTitle())
                .nickName(contents.getProfile().getNickName())
                .postImg(contents.getImgUrl())
                .build();
    }
}
