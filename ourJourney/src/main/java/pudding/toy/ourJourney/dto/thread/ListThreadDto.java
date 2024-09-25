package pudding.toy.ourJourney.dto.thread;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListThreadDto {
    Long threadId;
    ProfileThreadDto profileThreadDto;
    String threadImg;
    String texts;
    List<String> tagNames; //todo: JsonNullable
    LocalDateTime createdAt;
    Boolean isEditable;
    Boolean isRemovable;

    public ListThreadDto(
            Long threadId,
            ProfileThreadDto profileThreadDto,
            String threadImg,
            String texts,
            List<String> tagNames,
            LocalDateTime createdAt,
            Boolean isEditable,
            Boolean isRemovable
    ) {
        this.threadId = threadId;
        this.profileThreadDto = profileThreadDto;
        this.threadImg = threadImg;
        this.texts = texts;
        this.tagNames = tagNames;
        this.createdAt = createdAt;
        this.isEditable = isEditable;
        this.isRemovable = isRemovable;
    }
}
