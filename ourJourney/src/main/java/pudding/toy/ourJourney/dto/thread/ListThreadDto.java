package pudding.toy.ourJourney.dto.thread;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data @FieldDefaults(level = AccessLevel.PRIVATE)
public class ListThreadDto {
    Long threadId;
    ProfileThreadDto profileThreadDto;
    String threadImg;
    String content;
    Optional<List<String>> tagNames;
    LocalDateTime createdAt;
    public ListThreadDto(Long threadId, ProfileThreadDto profileThreadDto, String threadImg, String content, Optional<List<String>> tagNames, LocalDateTime createdAt) {
        this.threadId = threadId;
        this.profileThreadDto = profileThreadDto;
        this.threadImg = threadImg;
        this.content = content;
        this.tagNames = tagNames;
        this.createdAt = createdAt;
    }
}
