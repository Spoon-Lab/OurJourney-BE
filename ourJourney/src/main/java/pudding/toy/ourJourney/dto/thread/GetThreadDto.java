package pudding.toy.ourJourney.dto.thread;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data @FieldDefaults(level = AccessLevel.PRIVATE)
public class GetThreadDto {
    Long threadId;
    ThreadProfileDto threadProfileDto;
    String threadImg;
    String content;
    Optional<List<String>> tagNames;
    LocalDateTime createdAt;
    public GetThreadDto(Long threadId, ThreadProfileDto threadProfileDto, String threadImg, String content, Optional<List<String>> tagNames, LocalDateTime createdAt) {
        this.threadId = threadId;
        this.threadProfileDto = threadProfileDto;
        this.threadImg = threadImg;
        this.content = content;
        this.tagNames = tagNames;
        this.createdAt = createdAt;
    }
}
