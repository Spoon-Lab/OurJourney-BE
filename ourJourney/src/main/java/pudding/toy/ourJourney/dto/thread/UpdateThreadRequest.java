package pudding.toy.ourJourney.dto.thread;

import lombok.Data;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

@Data
public class UpdateThreadRequest {
    JsonNullable<String> texts;
    JsonNullable<List<Long>> tags;
    JsonNullable<String> threadImg;

    public UpdateThreadRequest(JsonNullable<String> texts, JsonNullable<List<Long>> tags, JsonNullable<String> threadImg) {
        this.texts = texts;
        this.tags = tags;
        this.threadImg = threadImg;
    }
}
