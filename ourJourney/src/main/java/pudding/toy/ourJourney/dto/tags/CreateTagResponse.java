package pudding.toy.ourJourney.dto.tags;

import lombok.Data;

@Data
public class CreateTagResponse {
    Long tagId;

    public CreateTagResponse(Long tagId) {
        this.tagId = tagId;
    }
}
