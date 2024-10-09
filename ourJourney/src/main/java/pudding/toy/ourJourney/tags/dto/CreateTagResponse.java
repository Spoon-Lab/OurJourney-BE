package pudding.toy.ourJourney.tags.dto;

import lombok.Data;

@Data
public class CreateTagResponse {
    Long tagId;

    public CreateTagResponse(Long tagId) {
        this.tagId = tagId;
    }
}
