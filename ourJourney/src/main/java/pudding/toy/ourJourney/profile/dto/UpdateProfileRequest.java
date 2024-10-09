package pudding.toy.ourJourney.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.openapitools.jackson.nullable.JsonNullable;

@Data
public class UpdateProfileRequest {
    @Schema(description = "닉네임")
    private JsonNullable<String> nickname;
    @Schema(description = "프로필 이미지 URL")
    private JsonNullable<String> imageUrl;
    @Schema(description = "한줄 소개")
    private JsonNullable<String> selfIntroduction;
}
